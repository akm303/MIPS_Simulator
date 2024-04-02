000000	000006	 LOC 6
000006	000001	 data 1        ;6 constant value 1
000007	000041	 Data 000033   ;7 ptr to the start "Please input number in range [0, 65535]"
000010	000170	 Data 120      ;8 ptr to start of "You entered: "
000011	000207	 data 135      ;9 ptr to start of "Enter target number: "
000012	000240	 data 160      ;10 ptr to start of "Closest number: "
000013	000014	 LOC 12
000014	000060	 data 48       ;12 ascii bit map 11_0000
000015	000003	 data 3        ;13 main loop counter
000016	000000	 data 0        ;14 value of target number
000017	001604	 data 900      ;15 location of final characters to print
000020	001060	 data 560      ;16 Best value found so far
000021	0077777	 data 32767    ;17 BestDelta   delta of closest number found so far. effectively all 1s
000022	000000	 Data 0        ;18 Generic swap space
000023	000000	 Data 0        ;19 Swap space for return address
000024	001000	 data 512      ;20 Pointer to "print string" Args: Load x(1) with mem pointer to first character
000025	001015	 data 525      ;21 Address to start of Read Number function. Value will be put in r(2)
000026	001060	 data 560      ;22 Address to start of Saved Digits. reserved until LOC 626
000027	001060	 data 560      ;23 Address to start of Saved Digits. Increment this by 6 to find next one. Used in Find Closest Function
000030	001332	 data 730      ;24 Address of Find Closest function.
000031	001356	 data 750      ;25 Address of loop for Find Closest function
000032	002000	 data 1024     ;26 Address of main function
000033	001522	 data 850      ;27 Address of print closest number function
000034	000036	 LOC 30
000036	001440	 data 800      ;30 Address of user entered VALUES
000037	001440	 data 800      ;31 Address of user entered VALUES  USED IN FIND CLOSEST
000040	000310	 data 200      ;32 Extra memory page
000041	000310	 LOC 200
000310	001604	 data 900     ; 200 + 0   location to store
000311	000000	 data 0       ; 200 + 1
000312	000000	 data 0       ; 200 + 2
000313	000041	 LOC 33		   ;BEGIN AT LOCATION 33
000041	000012	 data 10       ; \n
000042	000120	 Data 000080   ;Data Please input number in range [0, 65535]
000043	000154	 Data 000108   ;Data lease input number in range [0, 65535]
000044	000145	 Data 000101   ;Data ease input number in range [0, 65535]
000045	000141	 Data 000097   ;Data ase input number in range [0, 65535]
000046	000163	 Data 000115   ;Data se input number in range [0, 65535]
000047	000145	 Data 000101   ;Data e input number in range [0, 65535]
000050	000040	 Data 000032   ;Data  input number in range [0, 65535]
000051	000151	 Data 000105   ;Data input number in range [0, 65535]
000052	000156	 Data 000110   ;Data nput number in range [0, 65535]
000053	000160	 Data 000112   ;Data put number in range [0, 65535]
000054	000165	 Data 000117   ;Data ut number in range [0, 65535]
000055	000164	 Data 000116   ;Data t number in range [0, 65535]
000056	000040	 Data 000032   ;Data  number in range [0, 65535]
000057	000156	 Data 000110   ;Data number in range [0, 65535]
000060	000165	 Data 000117   ;Data umber in range [0, 65535]
000061	000155	 Data 000109   ;Data mber in range [0, 65535]
000062	000142	 Data 000098   ;Data ber in range [0, 65535]
000063	000145	 Data 000101   ;Data er in range [0, 65535]
000064	000162	 Data 000114   ;Data r in range [0, 65535]
000065	000040	 Data 000032   ;Data  in range [0, 65535]
000066	000151	 Data 000105   ;Data in range [0, 65535]
000067	000156	 Data 000110   ;Data n range [0, 65535]
000070	000040	 Data 000032   ;Data  range [0, 65535]
000071	000162	 Data 000114   ;Data range [0, 65535]
000072	000141	 Data 000097   ;Data ange [0, 65535]
000073	000156	 Data 000110   ;Data nge [0, 65535]
000074	000147	 Data 000103   ;Data ge [0, 65535]
000075	000145	 Data 000101   ;Data e [0, 65535]
000076	000040	 Data 000032   ;Data  [0, 65535]
000077	000133	 Data 000091   ;Data [0, 65535]
000100	000060	 Data 000048   ;Data 0, 65535]
000101	000054	 Data 000044   ;Data , 65535]
000102	000040	 Data 000032   ;Data  65535]
000103	000066	 Data 000054   ;Data 65535]
000104	000065	 Data 000053   ;Data 5535]
000105	000065	 Data 000053   ;Data 535]
000106	000063	 Data 000051   ;Data 35]
000107	000065	 Data 000053   ;Data 5]
000110	000135	 Data 000093   ;Data ]
000111	000012	 data 10       ; \n
000112	000000	 Data 000000   ;74 Data end of string
000113	000170	 LOC 120
000170	000131	 DATA 89       ;You entered:
000171	000157	 DATA 111      ;ou entered:
000172	000165	 DATA 117      ;u entered:
000173	000040	 DATA 32       ; entered:
000174	000145	 DATA 101      ;entered:
000175	000156	 DATA 110      ;ntered:
000176	000164	 DATA 116      ;tered:
000177	000145	 DATA 101      ;ered:
000200	000162	 DATA 114      ;red:
000201	000145	 DATA 101      ;ed:
000202	000144	 DATA 100      ;d:
000203	000072	 DATA 58       ;:
000204	000040	 DATA 32       ;
000205	000000	 Data 000000   ;Data end of string
000206	000207	 LOC 135
000207	000012	 DATA 10      ;\n
000210	000105	 DATA 69      ;Enter target number:
000211	000156	 DATA 110     ;nter target number:
000212	000164	 DATA 116     ;ter target number:
000213	000145	 DATA 101     ;er target number:
000214	000162	 DATA 114     ;r target number:
000215	000040	 DATA 32      ; target number:
000216	000164	 DATA 116     ;target number:
000217	000141	 DATA 97      ;arget number:
000220	000162	 DATA 114     ;rget number:
000221	000147	 DATA 103     ;get number:
000222	000145	 DATA 101     ;et number:
000223	000164	 DATA 116     ;t number:
000224	000040	 DATA 32      ; number:
000225	000156	 DATA 110     ;number:
000226	000165	 DATA 117     ;umber:
000227	000155	 DATA 109     ; mber:
000230	000142	 DATA 98      ;ber:
000231	000145	 DATA 101     ;er:
000232	000162	 DATA 114     ;r:
000233	000072	 DATA 58      ;:
000234	000000	 Data 000000  ;Data end of string
000235	000240	 LOC 160
000240	000012	 DATA 10      ;\n
000241	000103	 DATA 67      ;Closest number:
000242	000154	 DATA 108     ;losest number:
000243	000157	 DATA 111     ;osest number:
000244	000163	 DATA 115     ;sest number:
000245	000145	 DATA 101     ;est number:
000246	000163	 DATA 115     ;st number:
000247	000164	 DATA 116     ;t number:
000250	000040	 DATA 32      ; number:
000251	000156	 DATA 110     ;number:
000252	000165	 DATA 117     ;umber:
000253	000155	 DATA 109     ;mber:
000254	000142	 DATA 98      ;ber:
000255	000145	 DATA 101     ;er:
000256	000162	 DATA 114     ;r:
000257	000072	 DATA 58      ;:
000260	000040	 DATA 32      ;
000261	000000	 Data 000000  ;Data end of string
000262	001000	 LOC 512      ;Print string function. Set x(1) to be the pointer to the first character of the string
001000	005423	 STR 3,0,19   ; 512 Save return address to well known swap space
001001	002500	 LDR 1,1,0    ; 514 r(1) <- c(x(1) + 0)   Read first character into register 1
001002	003100	 LDR 2,1,0    ; 515 r(1) <- c(x(1) + 0)   Read first character into register 2
001003	066401	 OUT 1,1      ; 516 print character in r(1)
001004	116500	 X2R 1,1      ; 517 r(1) <- x(1)   Load the character ptr into r(1)
001005	040401	 AIR 1,1      ; 518 r(1) <- r(1) + 1  increment the character ptr by 1
001006	114500	 R2X 1,1      ; 519 Put the value in r1 into x(1)
001007	111000	 SETCCE 2     ; 520 Check if the character in r(2) == 0 (the character we just printed)
001010	017064	 JNE 2,0,20,1 ; 521 If the character printed was NOT 0, print loop back
001011	003423	 LDR 3,0,19   ; 522 Return from subroutine, total value is stored in r2
001012	026000	 RFS 0        ; 523
001013	001015	 LOC 525     ; Read number from keyboard (multiple characters). Save result (value) to r(2)
001015	005423	 STR 3,0,19  ; 525 Save return address to well known swap space
001016	010125	 LDX 1,21    ; 526  c(21) => first line of this program => 525
001017	010226	 LDX 2,22    ; 527  c(22) => first spot to save the number value
001020	010326	 LDX 3,22    ; 528  c(22) + 1 => first spot to save the number character string
001021	120301	 AIX 3,1     ; 529
001022	005022	 STR 2,0,18  ; 530 Clear reg 2
001023	037022	 SMR 2,0,18  ; 531
001024	064400	 IN 1,0      ; 532 read ascii character from keyboard into reg 1
001025	004700	 STR 1,3,0   ; 533 Save character into ptr at x(3)
001026	120301	 AIX 3,1     ; 534 increment ptr at x(3)
001027	110400	 SETCCE 1    ; 535 if character (in r1) is null (0000), break
001030	014131	 JZ 1,25     ; 536 550 = x(1) + 25 = 525 + 25
001031	004022	 STR 0,0,18  ; 537 Load immediate value 0000_0000_0000_1111 (15d) into r0
001032	036022	 SMR 0,0,18  ; 538
001033	040017	 AIR 0,15    ; 539
001034	052400	 AND 1,0     ; 540 Bitwise & on c and msk => get value
001035	004022	 STR 0,0,18  ; 541 Load immediate value 10(decimal) into r0
001036	036022	 SMR 0,0,18  ; 542
001037	040012	 AIR 0,10    ; 543
001040	045000	 MLT 2,0     ; 544 Multiply r(2) by r(0), store results to r(2), r(3)
001041	005422	 STR 3,0,18  ; 545 Copy R3 into R2
001042	003022	 LDR 2,0,18  ; 546
001043	004422	 STR 1,0,18  ; 547 Add r1 to r2, store into r2
001044	035022	 AMR 2,0,18  ; 548
001045	022107	 JMA 1,7     ; 549 Loop back and read the next character. 532 = x(1) + 7 = 525 + 7
001046	003423	 LDR 3,0,19  ; 550 Return from subroutine, total value is stored in r2
001047	026000	 RFS 0       ; 551
001050	001060	 LOC 560
001060	000000	 data 00000
001061	000000	 data 00000
001062	000000	 data 00000
001063	000000	 data 00000
001064	000000	 data 00000
001065	000000	 data 00000
001066	001332	 LOC 730        ; Find Closest Function
001332	010130	 ldx 1,24       ;730 x(1) <-- ptr to start of this function = 730
001333	010331	 ldx 3,25       ;731 x(1) <-- ptr to loop subsection
001334	004022	 STR 0,0,18     ;732 r(0) <-- 20
001335	036022	 SMR 0,0,18     ;733
001336	040024	 AIR 0,20       ;734
001337	002416	 LDR 1,0,14     ;735 r(1) <-- targetValue
001340	036477	 SMR 1,0,31,1   ;736 r(1) <-- currentDelta     r(1) <- r(1)-c(c(31)) = target-currentValue
001341	004422	 STR 1,0,18     ;737 Save currentDelta into swap space c(18)
001342	003422	 LDR 3,0,18     ;738 r3 <-- currentDelta    load currentDelta into r(3)
001343	061617	 SRC 3,15,0,1   ;739 Shift r3 >>> 15 to calculate 'y' (effectively full of it's sign bit)
001344	122700	 XOR 1,3        ;740 r1 <- r1 ^ r3  = r1^y
001345	005422	 STR 3,0,18     ;741 c(18) <- r(3)  = y   store y into swap space
001346	034422	 AMR 1,0,18     ;742 r(1) <- r(1)+c(18)  = r(1)+y   Now GPR1 holds currentAbsDelta
001347	004422	 STR 1,0,18     ;743 c(18) <-- currentAbsDelta
001350	003422	 LDR 3,0,18     ;744 r(3)  <-- currentAbsDelta        load currentAbsDelta into r3
001351	037421	 SMR 3,0,17     ;745 r(3) <-- r(3) - BestDelta  = currentAbsDelta-bestDelta
001352	033700	 JGE 3,3,0      ;746 if r(3) >= 0, jump down to SOB command (which will take us to the top of the func)
001353	004421	 STR 1,0,17     ;747 c(17) <-- currentAbsDelta    save currentAbsDelta as BestDelta
001354	002477	 LDR 1,0,31,1   ;748 r(1) <-- current value
001355	004420	 STR 1,0,16     ;749 c(16) <-- current value     save current value as bestSoFar
001356	002437	 LDR 1,0,31     ;750 Increment the current pointer by one
001357	040401	 AIR 1,1        ;751
001360	004437	 STR 1,0,31     ;752
001361	030105	 SOB 0,1,5      ;753 Jump back up to the "top" of function 20 times (skipping the part where we reset r0)
001362	003423	 LDR 3,0,19     ;754 Return from subroutine
001363	041401	 AIR 3,1        ;
001364	026000	 RFS 0          ;755
001365	001440	 LOC 800
001440	000000	 data 0
001441	001522	 LOC 850
001522	002020	 LDR 0,0,16   ; r(0) <-- closest number
001523	005022	 STR 2,0,18   ; Load 10 into r2
001524	037022	 SMR 2,0,18   ;
001525	041012	 AIR 2,10     ;
001526	003414	 LDR 3,0,12   ; r(2) <-- ascii bit mask
001527	010117	 LDX 1,15     ; x(1) <-- 900 (start of character stack)
001530	046200	 DVD 0,2      ; r(0) <-- number/10  r(1) <-- number%10 Divide by 10 to get the remainder
001531	054700	 ORR 1,3      ; r(1) <-- r(1) & r(3)  =  remainder & mask  Bitwise & remainder with ascii map
001532	004500	 STR 1,1,0    ; Store character to stack somewhere loc [900, 904]?
001533	046200	 DVD 0,2      ; r(0) <-- number/10  r(1) <-- number%10 Divide by 10 to get the remainder
001534	054700	 ORR 1,3      ; r(1) <-- r(1) & r(3)  =  remainder & mask  Bitwise & remainder with ascii map
001535	004501	 STR 1,1,1    ; Store character to stack somewhere loc [900, 904]?
001536	046200	 DVD 0,2      ; r(0) <-- number/10  r(1) <-- number%10 Divide by 10 to get the remainder
001537	054700	 ORR 1,3      ; r(1) <-- r(1) & r(2)  =  remainder & mask  Bitwise & remainder with ascii map
001540	004502	 STR 1,1,2    ; Store character to stack somewhere loc [900, 904]?
001541	046200	 DVD 0,2      ; r(0) <-- number/10  r(1) <-- number%10 Divide by 10 to get the remainder
001542	054700	 ORR 1,3      ; r(1) <-- r(1) & r(3)  =  remainder & mask  Bitwise & remainder with ascii map
001543	004503	 STR 1,1,3    ; Store character to stack somewhere loc [900, 904]?
001544	046200	 DVD 0,2      ; r(0) <-- number/10  r(1) <-- number%10 Divide by 10 to get the remainder
001545	054700	 ORR 1,3      ; r(1) <-- r(1) & r(3)  =  remainder & mask  Bitwise & remainder with ascii map
001546	004504	 STR 1,1,4    ; Store character to stack somewhere loc [900, 904]?
001547	002504	 LDR 1,1,4    ; Print the character at 904, 903, 902, 901, 900
001550	066401	 OUT 1,1
001551	002503	 LDR 1,1,3    ; Print the character at 904, 903, 902, 901, 900
001552	066401	 OUT 1,1
001553	002502	 LDR 1,1,2    ; Print the character at 904, 903, 902, 901, 900
001554	066401	 OUT 1,1
001555	002501	 LDR 1,1,1    ; Print the character at 904, 903, 902, 901, 900
001556	066401	 OUT 1,1
001557	002500	 LDR 1,1,0    ; Print the character at 904, 903, 902, 901, 900
001560	066401	 OUT 1,1
001561	000000	 hlt          ; done
001562	002000	 LOC 1024
002000	004022	 STR 0,0,18     ; r(0) <-- 20
002001	036022	 SMR 0,0,18     ;
002002	040003	 AIR 0,3        ;
002003	004015	 STR 0,0,13
002004	010107	 LDX 1,7    ; load x(1) with the address of the first character of the string
002005	024064	 JSR 0,20,1 ; Print string function
002006	024065	 JSR 0,21,1 ; Read in number from console
002007	010336	 LDX 3,30   ; x(2) <- location of user entered VALUES
002010	005300	 STR 2,3,0  ; save r(2) to well known memory location
002011	120301	 AIX 3,1    ;
002012	012336	 STX 3,30   ;
002013	010226	 LDX 2,22   ; x(2) <- location of saved digits
002014	005200	 STR 2,2,0  ; save r(2) to well known memory location
002015	010110	 LDX 1,8    ; Location of first char in "You Entered:"
002016	024064	 JSR 0,20,1 ; print string function
002017	010126	 LDX 1,22   ; Location of saved digits
002020	120101	 AIX 1,1    ;
002021	024064	 JSR 0,20,1 ; print string function
002022	120105	 AIX 1,5    ; Set up pointer to next spot in memory to save a digit: current + 1 + 5
002023	012126	 STX 1,22   ; Store ptr back to c(22)
002024	002015	 LDR 0,0,13
002025	010132	 LDX 1,26
002026	030103	 SOB 0,1,3
002027	010111	 LDX 1,9    ; load x(1) with address of first character "Enter target number: "
002030	024064	 JSR 0,20,1 ; Print string function
002031	024065	 JSR 0,21,1 ; Read number from console
002032	005016	 STR 2,0,14 ; save r(2) to well known memory location: c(14) = target number
002033	010226	 LDX 2,22   ; x(2) <- location of saved digits
002034	005200	 STR 2,2,0  ; save r(2) to well known memory location
002035	010110	 LDX 1,8    ; Location of first char in "You Entered:"
002036	024064	 JSR 0,20,1 ; print string function
002037	010126	 LDX 1,22   ; x(1) <-- Location of saved digits
002040	120101	 AIX 1,1    ; x(1) <-- x(1) + 1  (start of number as characters)
002041	024064	 JSR 0,20,1 ; print string function
002042	024070	 JSR 0,24,1  ; Find closest number function
002043	010112	 LDX 1,10    ; load x(1) with address of first character "closest number":
002044	024064	 JSR 0,20,1  ; print string function
002045	002420	 LDR 1,0,16  ;
002046	024073	 JSR 0,27,1  ; Print closest number function
002047	000000	 hlt
