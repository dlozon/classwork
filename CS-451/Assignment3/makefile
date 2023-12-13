CC = gcc
CFLAGS = -Iinclude
SRC_DIR = src

# List of source files
SRCS = $(wildcard $(SRC_DIR)/*.c)

# Output binary
TARGET = MottLozon_Elevator.exe

# Main target
all: $(TARGET)

# Rule to build the executable
$(TARGET): $(SRCS)
	$(CC) $(CFLAGS) $^ -o $@

# Rule to clean generated files
clean:
	rm -f $(TARGET)
