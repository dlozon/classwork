#include <string.h>
#include <stdint.h>
#include <ctype.h>

// Define a struct to store key-value pairs in the hashmap
typedef struct {
    char *key;
    char *value;
} KeyValuePair;

// Define the hashmap
static KeyValuePair responseMap[] = {
    {"hello", "Hello there user! What can I assist you with Kettering University today?"},
    {"programs", "Kettering University offers programs in engineering, computer science, business, Pre-med and more"},
	{"major", "Kettering University offers programs in engineering, computer science, business, Pre-med and more"},
    {"where", "Kettering University is located at 1700 University Ave, Flint Michigan, 48506."},
    {"admission", "Admission requirements for Kettering university includes a strong academic record, standardized test scores (SAT/ACT), and extracurricular activities."},
    {"tuition", "The tuition fee varies by program, year and other factors. For the most accurate count, please refer to the official Kettering Website https://www.kettering.edu/admissions-aid/financial-aid/tuition-and-cost-attendance/undergraduate-cost-attendance"},
    {"cost", "The tuition fee varies by program, year and other factors. For the most accurate count, please refer to the official Kettering Website https://www.kettering.edu/admissions-aid/financial-aid/tuition-and-cost-attendance/undergraduate-cost-attendance"},
	{"apply", "You can apply to Kettering University through this offical site -> https://www.kettering.edu/apply Go Bulldogs!"},
    {"whenwaskettering", "Kettering University was founded in 1919."},
	{"ketteringfounder", "Kettering University was founded by Albert Sobey."},
    {"foundedkettering", "Kettering University was founded by Albert Sobey."},
    {"clocktowerhistory", "Long story short, Kettering faculty asked students if they wanted a pool or a clock tower. Most students voted pool, but Kettering still put in the clock tower. So if you ever hear a student refer to the \"Pool\" they might really meant the clock tower!"},
	{"historyoftheclock", "Long story short, Kettering faculty asked students if they wanted a pool or a clock tower. Most students voted pool, but Kettering still put in the clock tower. So if you ever hear a student refer to the \"Pool\" they might really meant the clock tower!"}
};

static const uint32_t numEntries = sizeof(responseMap) / sizeof(KeyValuePair);

static void sanitizeInput(char *input) {
    char *p = input;
    while (*input) {
        if (isalpha(*input)) {
            *p++ = tolower(*input);
        }
        input++;
    }
    *p = '\0';
}

// Static function to generate a response
static char* generateResponse(char *inputString) {
	sanitizeInput(inputString);
	
    for (uint32_t i = 0; i < numEntries; i++) {
        if (strstr(inputString, responseMap[i].key) != NULL) {
            return responseMap[i].value;
        }
    }
    return "I'm sorry, I don't have a response for that.";
}