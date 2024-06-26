;*****************************************************************
;* This stationery serves as the framework for a                 *
;* user application (single file, absolute assembly application) *
;* For a more comprehensive program that                         *
;* demonstrates the more advanced functionality of this          *
;* processor, please see the demonstration applications          *
;* located in the examples subdirectory of the                   *
;* Freescale CodeWarrior for the HC12 Program directory          *
;*****************************************************************

; export symbols
            XDEF Entry, _Startup  ; export 'Entry' symbol
            ABSENTRY Entry        ; for absolute assembly: mark this as application entry point



; Include derivative-specific definitions 
		INCLUDE 'derivative.inc' 

ROMStart    EQU  $4000  ; absolute address to place my code/constant data

; variable/data section

 ifdef _HCS12_SERIALMON
            ORG $3FFF - (RAMEnd - RAMStart)
 else
            ORG RAMStart
 endif
 
 ; ************* Enter your data here:

  org $3000		; starting at address $3000, insert data
   
  operandA: dc.b $9C
  operandB: dc.b $B5
  sum: dc.b 1
  operandC: dc.b $3E
  operandD: dc.b $F7
  diff: dc.b 1
 

; code section
            ORG   ROMStart


Entry:
_Startup:
            ; remap the RAM &amp; EEPROM here. See EB386.pdf
 ifdef _HCS12_SERIALMON
            ; set registers at $0000
            CLR   $11                   ; INITRG= $0
            ; set ram to end at $3FFF
            LDAB  #$39
            STAB  $10                   ; INITRM= $39

            ; set eeprom to end at $0FFF
            LDAA  #$9
            STAA  $12                   ; INITEE= $9


            LDS   #$3FFF+1              ; See EB386.pdf, initialize the stack pointer
 else
            LDS   #RAMEnd+1             ; initialize the stack pointer
 endif

; ************* Enter your code here:

 ldx #operandA
 
 ldaa 0,X
 ldab 1,X
 
 aba
 
 staa 2,X
 
 ldaa 3,X
 ldab 4,X
 
 sba
 
 staa 5,X
 
 
 