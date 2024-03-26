 LOC 6
 data 1        ;6 constant value 1
 Data 000033   ;7 ptr to the start "Please input number in range [0, 65535]"
 Data 120      ;8 ptr to start of "You entered: "
 data 135      ;9 ptr to start of "Enter target number: "
 data 160      ;10 ptr to start of "Closest number: "
 LOC 18
 Data 0        ;18 Generic swap space
 Data 0        ;19 Swap space for return address
 data 512      ;20 Pointer to "print string" Args: Load x(1) with mem pointer to first character
 data 525      ;21 Address to start of Read Number function. Value will be put in r(2)
 data 550      ;22 Address to start of Saved Digits
 LOC 33		   ;BEGIN AT LOCATION 33
 Data 000080   ;Data Please input number in range [0, 65535]
 Data 000108   ;Data lease input number in range [0, 65535]
 Data 000101   ;Data ease input number in range [0, 65535]
 Data 000000   ; 000097   ;Data ase input number in range [0, 65535]
 Data 000115   ;Data se input number in range [0, 65535]
 Data 000101   ;Data e input number in range [0, 65535]
 Data 000032   ;Data  input number in range [0, 65535]
 Data 000105   ;Data input number in range [0, 65535]
 Data 000110   ;Data nput number in range [0, 65535]
 Data 000112   ;Data put number in range [0, 65535]
 Data 000117   ;Data ut number in range [0, 65535]
 Data 000116   ;Data t number in range [0, 65535]
 Data 000032   ;Data  number in range [0, 65535]
 Data 000110   ;Data number in range [0, 65535]
 Data 000117   ;Data umber in range [0, 65535]
 Data 000109   ;Data mber in range [0, 65535]
 Data 000098   ;Data ber in range [0, 65535]
 Data 000101   ;Data er in range [0, 65535]
 Data 000114   ;Data r in range [0, 65535]
 Data 000032   ;Data  in range [0, 65535]
 Data 000105   ;Data in range [0, 65535]
 Data 000110   ;Data n range [0, 65535]
 Data 000032   ;Data  range [0, 65535]
 Data 000114   ;Data range [0, 65535]
 Data 000097   ;Data ange [0, 65535]
 Data 000110   ;Data nge [0, 65535]
 Data 000103   ;Data ge [0, 65535]
 Data 000101   ;Data e [0, 65535]
 Data 000032   ;Data  [0, 65535]
 Data 000091   ;Data [0, 65535]
 Data 000048   ;Data 0, 65535]
 Data 000044   ;Data , 65535]
 Data 000032   ;Data  65535]
 Data 000054   ;Data 65535]
 Data 000053   ;Data 5535]
 Data 000053   ;Data 535]
 Data 000051   ;Data 35]
 Data 000053   ;Data 5]
 Data 000093   ;Data ]
 Data 000000   ;Data end of string
 LOC 120
 DATA 89       ;You entered: 
 DATA 111      ;ou entered: 
 DATA 117      ;u entered: 
 DATA 32       ; entered: 
 DATA 101      ;entered: 
 DATA 110      ;ntered: 
 DATA 116      ;tered: 
 DATA 101      ;ered: 
 DATA 114      ;red: 
 DATA 101      ;ed: 
 DATA 100      ;d: 
 DATA 58       ;: 
 DATA 32       ; 
 Data 000000   ;Data end of string
 LOC 135      
 DATA 69      ;Enter target number: 
 DATA 110     ;nter target number: 
 DATA 116     ;ter target number: 
 DATA 101     ;er target number: 
 DATA 114     ;r target number: 
 DATA 32      ; target number: 
 DATA 116     ;target number: 
 DATA 97      ;arget number: 
 DATA 114     ;rget number: 
 DATA 103     ;get number: 
 DATA 101     ;et number: 
 DATA 116     ;t number: 
 DATA 32      ; number: 
 DATA 110     ;number: 
 DATA 117     ;umber: 
 DATA 109     ;mber: 
 DATA 98      ;ber: 
 DATA 101     ;er: 
 DATA 114     ;r: 
 DATA 58      ;: 
 DATA 32      ; 
 Data 000000  ;Data end of string
 LOC 160
 DATA 67      ;Closest number: 
 DATA 108     ;losest number: 
 DATA 111     ;osest number: 
 DATA 115     ;sest number: 
 DATA 101     ;est number: 
 DATA 115     ;st number: 
 DATA 116     ;t number: 
 DATA 32      ; number: 
 DATA 110     ;number: 
 DATA 117     ;umber: 
 DATA 109     ;mber: 
 DATA 98      ;ber: 
 DATA 101     ;er: 
 DATA 114     ;r: 
 DATA 58      ;: 
 DATA 32      ; 
 Data 000000  ;Data end of string

 LOC 512      ;Print string function. Set x(1) to be the pointer to the first character of the string
 STR 3,0,19   ; 512 Save return address to well known swap space 
 LDR 1,1,0    ; 514 r(1) <- c(x(1) + 0)   Read first character into register 1
 LDR 2,1,0    ; 515 r(1) <- c(x(1) + 0)   Read first character into register 2
 OUT 1,1      ; 516 print character in r(1)
 X2R 1,1      ; 517 r(1) <- x(1)   Load the character ptr into r(1)
 AIR 1,1      ; 518 r(1) <- r(1) + 1  increment the character ptr by 1
 R2X 1,1      ; 519 Put the value in r1 into x(1)
 SETCCE 2     ; 520 Check if the character in r(2) == 0 (the character we just printed)
 JNE 2,0,20,1 ; 521 If the character printed was NOT 0, print loop back
 LDR 3,0,19   ; 522 Return from subroutine, total value is stored in r2
 RFS 0        ; 523 






 LOC 525     ; Read number from keyboard (multiple characters). Save result (value) to r(2)
 STR 3,0,19  ; 525 Save return address to well known swap space
 LDX 1,21    ; 526  c(21) => first line of this program => 525

 STR 2,0,18  ; 527 Clear reg 2
 SMR 2,0,18  ; 528

 IN 1,0      ; 529 read ascii character from keyboard into reg 1
 SETCCE 1    ; 530 if character (in r1) is null, break
 JZ 1,20     ; 531 545 = x(1) + 20 = 525 + 20
 STR 0,0,18  ; 532 Load immediate value 0000_0000_0000_1111 (15d) into r0
 SMR 0,0,18
 AIR 0,15
 AND 1,0     ; 535 Bitwise & on c and msk => get value
 STR 0,0,18  ; 536 Load immediate value 10(decimal) into r0
 SMR 0,0,18
 AIR 0,10
 MLT 2,0     ; 539 Multiply r(2) by r(0), store results to r(2), r(3)
 STR 3,0,18  ; 540 Copy R3 into R2 
 LDR 2,0,18
 STR 1,0,18   ; 542 Add r1 to r2, store into r2
 AMR 2,0,18     
 JMA 1,4     ; 544 Loop back and read the next character. 529 = x(1) + 4 = 525 + 4

 LDR 3,0,19  ; 545 Return from subroutine, total value is stored in r2
 RFS 0       ; 546





 LOC 1024
 LDX 1,7    ; load x(1) with the address of the first character of the string
 JSR 0,20,1 ; Print string function
 JSR 0,21,1 ; Read in number from console
 LDX 2,22   ; x(2) <- location of saved digits
 STR 2,2,0  ; save r(2) to well known memory location
 LDX 1,8    ; Location of first char in "You Entered:"
 JSR 0,21,1 ; print string function
 LDX 1,22   ; Location of saved digits
 JSR 0,21,1 ; print string function
 hlt