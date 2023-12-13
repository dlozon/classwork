#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <pwd.h>
#include <unistd.h>
#include <time.h>
#include <sys/sysinfo.h>

/*
Function Name: isInteger
Input to the method: the name of a directory
Output(Return value): 1 or 0 (true or false)
Brief description of the task: 
    Check if the directory's name is numeric (an integer)
*/
int isInteger(const char* dirName) {
    for (int i = 0; dirName[i]; i++) {
        if (!isdigit(dirName[i])) {
            return 0;
        }
    }
    return 1;
}

/*
Function Name: removeParentheses
Input to the method: A string to be modified
Output(Return value): n/a
Brief description of the task: 
    Removes any occurance of parentheses and their contents from a string
*/
void removeParentheses(char* input) {
    size_t input_length = strlen(input);

    size_t result_length = 0; // Length of the modified string
    int inside_parentheses = 0; // Flag to track if we are inside parentheses

    // Remove parentheses, as well as any characters between them
    for (size_t i = 0; i < input_length; i++) {
        if (input[i] == '(') {
            inside_parentheses++;
        } else if (input[i] == ')' && inside_parentheses) {
            inside_parentheses--;
        } else if (!inside_parentheses) {
            input[result_length] = input[i];
            result_length++;
        }
    }

    // Null-terminate the modified string
    input[result_length] = '\0';
}

/*
Function Name: getProcessExe
Input to the method: Array to store result, PID, LWP
Output(Return value): process exe in [square brackets]
Brief description of the task: 
    Gets the exe that started a process from the process' stat file
    in the case that cmdline was empty
*/
static char* getProcessExe(char* exe, int pid, int lwp) {
    // Assemble the path to the status file for given pid
    char path[256];
    snprintf(path, sizeof(path), "/proc/%d/task/%d/stat", pid, lwp);

    // Open the status file
    FILE *file = fopen(path, "r");
    // Handle "no such file" exception
    if (file == NULL) {
        fprintf(stderr, "Failed to open file: /proc/%d/task/%d/stat\n", pid, lwp);
        perror("fopen");
        exit(1);
    }

    char line[256];

    // There should only be one line in the file. Read it, close the file, and parse the contents
    fgets(line, sizeof(line), file);
    fclose(file);
    // Skip the first token and save the second (comm)
    sscanf(line, "%*d %s", exe);

    if (strcmp(exe, "EMPTY") == 0) {
        fprintf(stderr, "Error getting the comm token from process %d stat file\n", pid);
        exit(1);
    }

    // Replace all occurrences of '(' with '[' in the exe string
    char *pos;
    while ((pos = strchr(exe, '(')) != NULL) {
        *pos = '[';
    }
    while ((pos = strchr(exe, ')')) != NULL) {
        *pos = ']';
    }
   
    return exe;
}

/*
Function Name: getProcessCommand
Input to the method: PID, LWP
Output(Return value): The contents of cmdline
Brief description of the task: 
    Reads the cmdline file and collects its contents
*/
char* getProcessCommand(int pid, int lwp) {
    // Assemble the path to the cmdline file for given pid
    char path[256];
    snprintf(path, sizeof(path), "/proc/%d/task/%d/cmdline", pid, lwp);

    // Open the cmdline file
    FILE *file = fopen(path, "r");
    // Handle "no such file" exception
    if (file == NULL) {
        fprintf(stderr, "Failed to open file: /proc/%d/task/%d/cmdline\n", pid, lwp);
        perror("fopen");
        exit(1);
    }

    // Allocate memory to store the command
    char* cmdline;
    int bufsize = 256;
    cmdline = malloc(bufsize);

    // Initialize cmdline to an invalid value
    sprintf(cmdline, "EMPTY");

    // Read until the first space in the input file and store the result in cmdline
    fscanf(file, "%s", cmdline);
    fclose(file);

    // If true, cmdline file was empty and we need to get the executable from stat
    if (strcmp(cmdline, "EMPTY") == 0 ) {
        return getProcessExe(cmdline, pid, lwp);
    }

    return cmdline;
}

/*
Function Name: getPPID
Input to the method: PID, LWP
Output(Return value): PPID
Brief description of the task: 
   Reads the stat file and grabs the PPID
*/
int getPPID(int pid, int lwp) {
    // Assemble the path to the status file for given pid
    char path[256];
    snprintf(path, sizeof(path), "/proc/%d/task/%d/stat", pid, lwp);

    // Open the status file
    FILE *file = fopen(path, "r");
    // Handle "no such file" exception
    if (file == NULL) {
        fprintf(stderr, "Failed to open file: /proc/%d/task/%d/stat\n", pid, lwp);
        perror("fopen");
        exit(1);
    }

    char line[256];
    int ppid = -1; // Initialize ppid to an invalid value

    // There should only be one line in the file. Read it, close the file, and parse the contents
    fgets(line, sizeof(line), file);
    fclose(file);
    // Skip the first 3 tokens, but save the 4th (ppid)
    removeParentheses(line);
    sscanf(line, "%*d %*c %d", &ppid);

    if (ppid == -1) {
        fprintf(stderr, "Error getting the ppid of process %d (LWP %d)\n", pid, lwp);
        exit(1);
    }
   
    return ppid;
}

/*
Function Name: getProcessUID
Input to the method: PID, LWP
Output(Return value): UID
Brief description of the task: 
   Reads the status file and grabs the UID
*/
int getProcessUID(int pid, int lwp) {
    // Assemble the path to the status file for given pid
    char path[256];
    snprintf(path, sizeof(path), "/proc/%d/task/%d/status", pid, lwp);

    // Open the status file
    FILE *file = fopen(path, "r");
    // Handle "no such file" exception
    if (file == NULL) {
        fprintf(stderr, "Failed to open file: /proc/%d/task/%d/status\n", pid, lwp);
        perror("fopen");
        exit(1);
    }

    char line[256];
    int uid = -1; // Initialize uid to an invalid value
    
    // Read the status file line by line
    while (fgets(line, sizeof(line), file)) {
        // Search for the "Uid:" line
        if (strstr(line, "Uid:") == line) {
            // Parse the first matching line
            int real_uid, effective_uid, saved_set_uid, filesystem_uid;
            sscanf(line, "Uid:\t%d\t%d\t%d\t%d", &real_uid, &effective_uid, &saved_set_uid, &filesystem_uid);
            // Return the first of the 4 values
            uid = real_uid;
            break;
        }
    }

    // Close the file
    fclose(file);

    // Handle error where uid was not changed from invalid default vaule
    if(uid == -1) {
        fprintf(stderr, "Error getting UID for process %d\n", pid);
        exit(1);
    }

    return uid;
}

/*
Function Name: getUsernameUID
Input to the method: UID
Output(Return value): uname
Brief description of the task: 
   Converts a UID into a username
*/char* getUsernameUID(int uid) {
    struct passwd *pws = getpwuid(uid);

    if (pws != NULL) {
        return pws->pw_name;
    } 
    // Handle NULL user error
    else {
        fprintf(stderr, "Error getting username for UID %d\n", uid);
        exit(1);
    }
}

/*
Function Name: getScheduledTime
Input to the method: PID, LWP
Output(Return value): process uptime in clk cycles
Brief description of the task: 
    Reads the stat file for utime and stime, then adds them
*/
unsigned long long getScheduledTime(int pid, int lwp) {
    // Assemble the path to the status file for given pid
    char path[256];
    snprintf(path, sizeof(path), "/proc/%d/task/%d/stat", pid, lwp);

    // Open the status file
    FILE *file = fopen(path, "r");
    // Handle "no such file" exception
    if (file == NULL) {
        fprintf(stderr, "Failed to open file: /proc/%d/task/%d/stat\n", pid, lwp);
        perror("fopen");
        exit(1);
    }

    char line[256];
    unsigned long utime = -1; // Initialize utime to an invalid value
    unsigned long stime = -1; // Initialize stime to an invalid value

    // There should only be one line in the file. Read it, close the file, and parse the contents
    fgets(line, sizeof(line), file);
    fclose(file);
    // Skip the first 13 tokens, but save the 14th (utime) and 15th (stime)
    removeParentheses(line);
    sscanf(line, "%*d %*c %*d %*d %*d %*d %*d %*u %*u %*u %*u %*u %lu %lu", &utime, &stime);

    if (utime == -1) {
        fprintf(stderr, "Error getting the utime of process %d (LWP %d)\n", pid, lwp);
        exit(1);
    }

    if (stime == -1) {
        fprintf(stderr, "Error getting the stime of process %d (LWP %d)\n", pid, lwp);
        exit(1);
    }
   
    return utime+stime;
}

/*
Function Name: getProcessStartTime
Input to the method: PID, LWP
Output(Return value): process' start time in clk cycles since boot
Brief description of the task: 
    Reads the stat file for starttime
*/
unsigned long long getProcessStartTime(int pid, int lwp) {
    // Assemble the path to the status file for given pid
    char path[256];
    snprintf(path, sizeof(path), "/proc/%d/task/%d/stat", pid, lwp);

    // Open the status file
    FILE *file = fopen(path, "r");
    // Handle "no such file" exception
    if (file == NULL) {
        fprintf(stderr, "Failed to open file: /proc/%d/task/%d/stat\n", pid, lwp);
        perror("fopen");
        exit(1);
    }

    char line[256];
    unsigned long long proc_start = -1; // Initialize proc_start to an invalid value

    // There should only be one line in the file. Read it, close the file, and parse the contents
    fgets(line, sizeof(line), file);
    fclose(file);
    // Skip the first 21 tokens, but save the 22nd (starttime)
    removeParentheses(line);
    sscanf(line, "%*d %*c %*d %*d %*d %*d %*d %*u %*u %*u %*u %*u %*u %*u %*d %*d %*d %*d %*d %*d %llu", &proc_start);

    if (proc_start == -1) {
        fprintf(stderr, "Error getting the start time of process %d (LWP %d)\n", pid, lwp);
        exit(1);
    }
   
    return proc_start;
}

/*
Function Name: convertClockTime
Input to the method: clk cycles
Output(Return value): seconds
Brief description of the task: 
    Converts clock cycles to seconds
*/
unsigned long convertClockTime(unsigned long clock_cycles) {
    long clock_cycles_per_sec = sysconf(_SC_CLK_TCK);
    
    if (clock_cycles_per_sec <= 0) {
        fprintf(stderr, "There was an error determining the number of clock cycles per second, value retrieved is negative.");
        return 0;
    }
    
    return (unsigned long) (clock_cycles / clock_cycles_per_sec);
}

/*
Function Name: getSystemStartTime
Input to the method: n/a
Output(Return value): boot time
Brief description of the task: 
    Gets the time the system was booted and returns it in HH:MM:SS
*/
char* getSystemStartTime() {
    struct sysinfo info;
    char *formatted_time = (char *)malloc(10);

    // Calculate the system's startup time in seconds since epoch
    // Calculated from sysinfo instead of reading btime because it saves the trouble of file handling
    sysinfo(&info);
    time_t system_start_time = time(NULL) - info.uptime;

    // Convert the process' start time to a human readable format
    struct tm *time_info = localtime(&system_start_time);
    strftime(formatted_time, 10, "%H:%M:%S", time_info);

    return formatted_time;
}

/*
Function Name: formatProcessStartTime
Input to the method: seconds since boot
Output(Return value): process start time
Brief description of the task: 
    Gets the time a process was started and returns it in HH:MM:SS
*/
char* formatProcessStartTime(int seconds_after_startup) {
    struct sysinfo info;
    char *formatted_time = (char *)malloc(10);

    // Calculate the system's startup time in seconds since epoch
    // Calculated from sysinfo instead of reading btime because it saves the trouble of file handling
    sysinfo(&info);
    time_t system_start_time = time(NULL) - info.uptime;
    // Calculate the process' start time
    time_t process_start_time = system_start_time + seconds_after_startup; 

    // Convert the process' start time to a human readable format
    struct tm *time_info = localtime(&process_start_time);
    strftime(formatted_time, 10, "%H:%M:%S", time_info);

    return formatted_time;
}

/*
Function Name: formatTime
Input to the method: seconds
Output(Return value): time in HH:MM:SS
Brief description of the task: 
    Converts a time in seconds to (HH:MM:SS)
*/
char* formatTime(int seconds) {
    int hours = seconds / 3600;
    int minutes = (seconds % 3600) / 60;
    seconds %= 60;

    char* formatted_time = (char*)malloc(10); // HH:MM:SS\0

    if (formatted_time == NULL) {
        // Memory allocation error
        return NULL;
    }

    snprintf(formatted_time, 10, "%02d:%02d:%02d", hours, minutes, seconds);

    return formatted_time;
}