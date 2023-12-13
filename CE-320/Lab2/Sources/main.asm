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

 ldx #$3000
 ldaa 0,X

 ldx #$3001
 ldab 0,X

 aba

 ldx #$3002
 staa 0,X

 ldx #$3003
 ldaa 0,X

 ldx #$3004
 ldab 0,X

 sba

 ldx #$3005
 staa 0,X   