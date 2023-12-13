# Define the compiler and flags
CC = gcc
CFLAGS = -Iinclude

# Define the source directory
SRC_DIR = src

# Get a list of all source files
SRC_FILES = $(wildcard $(SRC_DIR)/*.c)

# Make a list of executable names
EXECUTABLES = $(patsubst $(SRC_DIR)/%.c, %, $(SRC_FILES))

# Add .exe to executables
EXECUTABLES := $(addsuffix .exe, $(EXECUTABLES))

# Default target to build all executables
all: $(EXECUTABLES)

# Build an executable for each source file
%.exe: $(SRC_DIR)/%.c
	$(CC) $(CFLAGS) $< -o $@

# Clean up all generated executables
clean:
	rm -f $(EXECUTABLES)

.PHONY: all clean
