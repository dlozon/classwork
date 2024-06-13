/*----------------------------------------------------------------------------
    CMSIS RTOS Example
*----------------------------------------------------------------------------*/
#include "STM32F10x.h"
#include "cmsis_os.h"
#include "string.h"
#include "response_generator.c"

osMessageQId Q_UART;
osMessageQDef(Q_UART, 0x16, unsigned char);
osEvent result;
osSemaphoreId uart_semaphore;
osSemaphoreDef(uart_semaphore);

void keepaliveThread(void const *argument);
void handleInputThread(void const *argument);
void respond(uint8_t *inputBuffer);
void echoMessage(uint8_t *inputBuffer);
extern char* generateResponse(char *inputString);

osThreadDef(keepaliveThread, osPriorityNormal, 1, 0);
osThreadDef(handleInputThread, osPriorityNormal, 1, 0);
osThreadId T_keepaliveThread;
osThreadId T_handleInputThread;

uint8_t inputBuffer[256]; // Buffer to store user input and system responses
uint8_t bufferIndex = 0; // Index to keep track of buffer position
uint8_t keepaliveMessage[] = "still alive\r\n"; // String to hold the keepalive message
uint32_t lastMessageTime = 0; // Time of the last sent message

/** @brief Sends a string over uart. */
void SendText(uint8_t *text) {
    while (*text) {
        SendChar(*text++);
    }
}

/** @brief Sends a message every 30 seconds so the user knows the system is still alive. */
void keepaliveThread(void const *argument) {
    for (;;) {
		osDelay(300000); // Wait 30 seconds between keepalive
		osSemaphoreWait(uart_semaphore, osWaitForever);
			SendText("\r\x1b[2K"); // Clear the current line
			SendText(keepaliveMessage); // Send the keepalive message
			SendText(inputBuffer); // Restore the user's current input
		osSemaphoreRelease(uart_semaphore);
    }
}

/** @brief Reads characters from uart and decides what to do with them. */
void handleInputThread(void const *argument) {
	for (;;) {
		result = osMessageGet(Q_UART, osWaitForever);
		uint8_t input = result.value.v;
		if (input == 13) { // Enter key pressed
			inputBuffer[bufferIndex] = '\0'; // Null-terminate the string
			echoMessage(inputBuffer); // Echo the message that was recieved
			respond(inputBuffer); // Generate and send response
			bufferIndex = 0; // Reset buffer index
			memset(inputBuffer, 0, sizeof(inputBuffer)); // Empty the buffer
		} else if (input == 0x08) { // Backspace key pressed
			if (bufferIndex > 0) {
				bufferIndex--;
				osSemaphoreWait(uart_semaphore, osWaitForever);
					SendText(" \x1b[1D"); // Move cursor back one position and erase
				osSemaphoreRelease(uart_semaphore);
			}
		} else {
			inputBuffer[bufferIndex++] = input; // Store input in buffer
			if (bufferIndex >= sizeof(inputBuffer) - 1) {
				bufferIndex = 0; // Reset buffer if it's full
			}
		}
	}
}

/** @brief Echos the recieved message to the terminal */
void echoMessage(uint8_t *message) {
	osSemaphoreWait(uart_semaphore, osWaitForever);
		SendText("Received: ");
		SendText(message);
		SendText("\r\n");
	osSemaphoreRelease(uart_semaphore);
}

/** @brief Generates a response to a question and sends it to the terminal
    @param buffer A pointer to the question. This will be overwritten by the response. */
void respond(uint8_t *buffer) {
	buffer = generateResponse((char*)buffer);
	
	osSemaphoreWait(uart_semaphore, osWaitForever);
		SendText("Response: ");
		SendText(buffer);
		SendText("\r\n");
	osSemaphoreRelease(uart_semaphore);
}

 int main(void) {
    osKernelInitialize();
    USART1_Init();
    // Configure and enable USART1 interrupt
    NVIC->ICPR[USART1_IRQn/32] = 1UL << (USART1_IRQn%32); // Clear any previous pending interrupt flag
    NVIC->IP[USART1_IRQn] = 0x80; // Set priority to 0x80
    NVIC->ISER[USART1_IRQn/32] = 1UL << (USART1_IRQn%32); // Set interrupt enable bit
    USART1->CR1 |= USART_CR1_RXNEIE; // Enable USART receiver not empty interrupt
    Q_UART = osMessageCreate(osMessageQ(Q_UART), NULL);
    uart_semaphore = osSemaphoreCreate(osSemaphore(uart_semaphore), 1);
    T_keepaliveThread = osThreadCreate(osThread(keepaliveThread), NULL);
    T_handleInputThread = osThreadCreate(osThread(handleInputThread), NULL);
    osKernelStart();
}

/** @brief Puts a character recieved over uart into the message queue and then echos it. */ 
void USART1_IRQHandler(void) {
    uint8_t intKey = (uint8_t)(USART1->DR & 0x1FF);
    osMessagePut(Q_UART, intKey, 0);
    SendChar(intKey);
}