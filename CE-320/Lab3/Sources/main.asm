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

  org      $2000

  dc.b     5           ; Number of students
  dc.b     8           ; Number of grades for the first student
  dc.b     19, 15, 20, 17, 12, 16, 15, 10    ; 1st student’s grades
  ds.b     2           ; the averages is stored here
  
  dc.b     6           ; Number of grades for the 2nd student
  dc.b     14, 20, 17, 18, 12, 19 		; 2nd student’s grades
  ds.b     2           ; the averages is stored here

  dc.b     5           ; Number of grades for the third student
  dc.b     19, 15, 20, 13, 15 			; 3rd student’s grades
  ds.b     2           ; the averages is stored here

  dc.b     6           ; Number of grades for the 4th student
  dc.b     16, 15, 19, 19, 18, 17  		; 4th student’s grades
  ds.b     2           ; the averages is stored here

  dc.b     7           ; Number of grades for the 5th student
  dc.b     20, 14, 19, 16, 20, 18, 18 		; 5th student’s grades
  ds.b     2           ; the averages is stored here 

  sCounter:    ds.b 1
  NoOfGradesH: dc.b 0
  NoOfGradesL: ds.b 1

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

 
  ldy #$2000
  
  ldaa 1,Y+
  staa sCounter
  
  outer:
    ldaa 1,Y+
    staa NoOfGradesL
    ldab 0
  
    inner:
      addb 1,Y+
      deca
    bne inner
    
    ldx NoOfGradesH
    
    idiv
    
    stx 1,Y+
    ldaa 1,Y+
    
    dec sCounter
  bne outer
  
  done: bra done
 
   