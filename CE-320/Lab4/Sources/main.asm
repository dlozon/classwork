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

  org      $3000

  dividend: dc.b $FF,$FF   ; 65535 in decimal
 

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


 ldy #$3017
 
 ldd dividend
 beq end       ; end if dividend is 0
 
 start:
 
   ldx #10         
   idiv        ; D/10 -> X
 
   stab 1,Y-   ; store dec remainder in mem-Y
   addb #$30   ; convert remainder to ASCII
   stab 1,Y-   ; store ASCII remainder in mem-Y
 
   cpx 0
   beq end     ; end if dividend is 0
 
   tfr X,D     ; transfer dividend into D
   ldx #10         
   idiv        ; D/10 -> X
 
   addb #$30   ; convert remainder to ASCII
   stab 1,Y-   ; store ASCII remainder in mem-Y
 
   lslb        ; perform 4 bitwise left shifts on B
   lslb
   lslb
   lslb
 
   orab 3,Y    ; bitwise or with b and Y-3
   stab 3,Y    ; store second dec digit in same mem as first
 
   tfr X,D     ; transfer dividend into D
 
   cpx 0
   bne start   ; repeat the process if dividend is non-zero
 
 
 
 end: bra end  ; infinite loop
 
 
 