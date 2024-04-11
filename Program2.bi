000000	000006	 LOC 6
000006	000041	 data 33   ;6 Ptr to start of "Please enter a sentence "
000007	000074	 data 60   ;7 Ptr to start of "Please enter a target word "
000010	000144	 data 100  ;8 Const Ptr to start of user input paragraph
000011	000127	 data 87   ;9 Const ptr to start of user target word
000012	000000	 data 0    ;10
000013	000000	 data 0    ;11 Word count
000014	000000	 data 0    ;12 Sentence count
000015	000144	 data 100  ;13 variable ptr to start of user input paragraph
000016	000040	 data 32   ;14 Const " " space character
000017	000056	 data 46   ;15 const "." period character
000020	000000	 data 0    ;16 Variable current paragraph character
000021	000060	 data 48   ;17 const bit mask for number-to-ascii
000022	000000	 data 0    ;18  Swap space
000023	000000	 data 0    ;19  Storage space for return address
000024	000012	 data 10   ;20  newline character \n
000025	002000	 data 1024 ;21 const Pointer to print string function
000026	002020	 data 1040 ;22 const pointer to read paragraph function
000027	002037	 data 1055 ;23 const pointer to Find_Match function
000030	002114	 data 1100 ;24 const pointer to Print_Found function
000031	000000	 data 0    ;25
000032	000000	 data 0    ;26
000033	000000	 data 0    ;27
000034	000000	 data 0    ;28
000035	000000	 data 0    ;29
000036	000000	 data 0    ;30
000037	000000	 data 0    ;31
000040	000000	 data 0    ;32
000041	000041	 LOC 33
000041	000120	 Data 80     ; "Please enter a sentence "
000042	000154	 Data 108
000043	000145	 Data 101
000044	000141	 Data 97
000045	000163	 Data 115
000046	000145	 Data 101
000047	000040	 Data 32
000050	000145	 Data 101    ; 40
000051	000156	 Data 110
000052	000164	 Data 116
000053	000145	 Data 101
000054	000162	 Data 114
000055	000040	 Data 32
000056	000141	 Data 97
000057	000040	 Data 32
000060	000163	 Data 115
000061	000145	 Data 101
000062	000156	 Data 110    ; 50
000063	000164	 Data 116
000064	000145	 Data 101
000065	000156	 Data 110
000066	000143	 Data 99
000067	000145	 Data 101
000070	000056	 Data 46
000071	000040	 Data 32
000072	000000	 data 0    ;end of string  Mem = 58
000073	000074	 LOC 60
000074	000012	 data 10     ;  \n
000075	000120	 Data 80    ; String "Please enter target word "
000076	000154	 Data 108
000077	000145	 Data 101
000100	000141	 Data 97
000101	000163	 Data 115
000102	000145	 Data 101
000103	000040	 Data 32
000104	000145	 Data 101
000105	000156	 Data 110
000106	000164	 Data 116     ; 70
000107	000145	 Data 101
000110	000162	 Data 114
000111	000040	 Data 32
000112	000164	 Data 116
000113	000141	 Data 97
000114	000162	 Data 114
000115	000147	 Data 103
000116	000145	 Data 101
000117	000164	 Data 116
000120	000040	 Data 32     ; 80
000121	000167	 Data 119
000122	000157	 Data 111
000123	000162	 Data 114
000124	000144	 data 100
000125	000040	 Data 32
000126	000000	 data 0    ;end of string  Mem = 86
000127	000127	 LOC 87    ; Start of user input target word [87, 98] 11 characters long
000127	000143	 LOC 99
000143	000000	 data 0    ; end of string
000144	000144	 LOC 100   ; Stat of use input paragraph [100, 1023]
000144	002000	 LOC 1024     ; Octal 2000 ; Print String function. Set x(1) to pointer of first character
002000	005423	 STR 3,0,19   ; 1024 Save return address to well known swap space
002001	002500	 LDR 1,1,0    ; 1025 r(1) <- c(x(1) + 0)   Read first character into register 1
002002	003100	 LDR 2,1,0    ; 1026 r(1) <- c(x(1) + 0)   Read first character into register 2
002003	066401	 OUT 1,1      ; 1027 print character in r(1)
002004	116500	 X2R 1,1      ; 1028 r(1) <- x(1)   Load the character ptr into r(1)
002005	040401	 AIR 1,1      ; 1029 r(1) <- r(1) + 1  increment the character ptr by 1
002006	114500	 R2X 1,1      ; 1030 Put the value in r1 into x(1)
002007	111000	 SETCCE 2     ; 1031 Check if the character in r(2) == 0 (the character we just printed)
002010	016065	 JNE 0,21,1   ; 1032 If the character printed was NOT 0, loop back
002011	003423	 LDR 3,0,19   ; 1033 Return from subroutine
002012	026000	 RFS 0        ; 1034
002013	002020	 LOC 1040     ; Read from keyboard function. Set x(3) to pointer of where in mem to save string
002020	005423	 STR 3,0,19   ; 1040 Save return address to well known swap space
002021	010126	 LDX 1,22     ; 1041 x(1) <- c(22)   load x1 with ptr to this function
002022	064400	 IN 1,0       ; 1042  read ascii character from keyboard into reg 1
002023	004700	 STR 1,3,0    ; 1043  Save character into memory at x(3)
002024	120301	 AIX 3,1      ; 1044  x(3) += 1  increment x3 by 1
002025	110400	 SETCCE 1     ; 1045  if character (in r1) is null (0000), break
002026	014110	 JZ 1,8       ; 1046     PC = x(1) + 8 = 1040 + 8 = 1048
002027	022102	 JMA 1,2      ; 1047  Else: Loop back and read the next character. PC = x(1) + 2 = 1040 + 2 = 1042
002030	003423	 LDR 3,0,19   ; 1048 Return from subroutine, total value is stored in r2
002031	026000	 RFS 0        ; 1049
002032	002037	 LOC 1055     ; Find word in paragraph function
002037	010115	 LDX 1,13     ;1055 x(1) <- c(13)   load x1 with ptr to start of user input paragraph
002040	010211	 LDX 2,9      ;1056 x(2) <- c(9)    load x2 with ptr to start of target word
002041	010327	 LDX 3,23     ;1057 x(3) <- c(23)   load x3 with ptr to start of this function
002042	120304	 AIX 3,4      ;1058 x(3) <- x(3) + 4   1055+4 = 1059
002043	002200	 LDR 0,2,0    ;1059 r(0) <- c(x(2))  Load r0 with the target_word character
002044	002500	 LDR 1,1,0    ;1060 r(1) <- c(x(1))  Load r1 with the paragraph character
002045	110000	 SETCCE 0     ;1061 check if r(0) is null terminator
002046	016306	 JNE 3,6      ;1062 If the character is NOT 0000 then jump to 1059+6 = 1065
002047	010330	 LDX 3,24     ;1063 x(3) <- c(24)      load ptr to Print_Found function
002050	022300	 JMA 3,0      ;1064 Unconditional jump to Print_Found function
002051	036100	 SMR 0,1,0    ;1065 Compare character at x(1) to char in r(0)  (IE compare paragraph char to target_word char)
002052	120101	 AIX 1,1      ;1066    Increment x(1) by 1
002053	120201	 AIX 2,1      ;1067    Increment x(2) by 1
002054	110000	 SETCCE 0     ;1068 If they are the same character, then r(0) will hold value 0
002055	014300	 JZ 3,0       ;1069 jump to x(3) = <Start of this function> = 1059
002056	004420	 STR 1,0,16   ;1070 c(16) <- r(1)          Save current paragraph character into c(16)
002057	036416	 SMR 1,0,14   ;1071 r(1) <- r(1) - c(14)   compare current char to " " space character
002060	110400	 SETCCE 1     ;1072
002061	016322	 JNE 3,18     ;1073 If current char != " ", then jump to 1077(=1059+18). Else...
002062	003013	 LDR 2,0,11   ;1074 r(2) <- c(11)      load word count into r2
002063	041001	 AIR 2,1      ;1075 r(2) <- r(2) + 1   increment word count by 1
002064	005013	 STR 2,0,11   ;1076 c(11) <- r(2)      Store word count back to memory
002065	002420	 LDR 1,0,16   ;1077 r(1) <- c(16)          load r(1) with current character (again)
002066	036417	 SMR 1,0,15   ;1078 r(1) <- r(1) - c(15)   compare current char to "." period character
002067	110400	 SETCCE 1     ;1079
002070	016334	 JNE 3,28     ;1080 If current char != "." then jump to 1087(=1059+28)
002071	003013	 LDR 2,0,11   ;1081 r(2) <- c(11)          load word count into r2
002072	037013	 SMR 2,0,11   ;1082 r(2) <- r(2) - c(11)   set r(2) to 0
002073	005013	 STR 2,0,11   ;1083 c(11) <- r(2)          set word count to 0
002074	003014	 LDR 2,0,12   ;1084 r(2) <- c(12)          load sentence count into r2
002075	041001	 AIR 2,1      ;1085 r(2) <- r(2) + 1       increment sentence count by 1
002076	005014	 STR 2,0,12   ;1086 c(12) <- r(2)
002077	002077	 LOC 1087
002077	003015	 LDR 2,0,13    ;1087 r(2) <- c(13)     load r2 with ptr to current paragraph character
002100	041001	 AIR 2,1       ;1088 r(2) <- r(2) + 1  Add 1 to r2
002101	005015	 STR 2,0,13    ;1089 c(13) <- r(2)     Store r2 back to memory
002102	010115	 LDX 1,13      ;1090 x(1) <- c(13)     load x1 with ptr to start of user input paragraph
002103	010211	 LDX 2,9       ;1091 x(2) <- c(9)      load x2 with ptr to start of target word
002104	022300	 JMA 3,0       ;1092 Unconditional jump to top of function again (but with incremented paragraph character target)
002105	002114	 LOC 1100     ; Print_Found function
002114	003424	 LDR 3,0,20   ; load newline character into r3
002115	067401	 OUT 3,1      ; print newline character
002116	010111	 LDX 1,9      ;1100 const ptr to target word
002117	024065	 JSR 0,21,1   ;1101 Print target word
002120	003424	 LDR 3,0,20   ; load newline character into r3
002121	067401	 OUT 3,1      ; print newline character
002122	002021	 LDR 0,0,17   ;1102 r(0) <- c(17)        load r0 with bitmask for number to ascii conversion
002123	002414	 LDR 1,0,12   ;1103r(1) <- c(12)         load r1 with sentence count
002124	054400	 ORR 1,0      ;1104 r(1) <- r(1) & r(0)  convert r1 to ascii character
002125	066401	 OUT 1,1      ;1105 print character in r(1)
002126	067401	 OUT 3,1      ; print newline character
002127	002413	 LDR 1,0,11   ;1106 r(1) <- c(11)        load reg 1 with word count
002130	054400	 ORR 1,0      ;1107 r(1) <- r(1) & r(0)  convert r1 to ascii character
002131	066401	 OUT 1,1      ;1108 print character in r(1)
002132	000000	 hlt
002133	003000	 LOC 1536     ; Octal 3000 main function
003000	010106	 LDX 1,6      ; x(1) <- c(6)   load x1 with ptr to string "Please enter a sentence "
003001	024065	 JSR 0,21,1   ; Print string function
003002	010310	 LDX 3,8      ; 1042 x(3) <- c(8)  load x3 with ptr to start of user input space
003003	024066	 JSR 0,22,1   ; Read in paragraph
003004	010110	 LDX 1,8      ; x(1) <- c(8)   load x1 with ptr to string  <user inputted sentence>
003005	024065	 JSR 0,21,1   ; Print paragraph back to them
003006	010107	 LDX 1,7      ; x(1) <- c(7)   load x1 with ptr to string "Please enter target word "
003007	024065	 JSR 0,21,1   ; Print string function
003010	010311	 LDX 3,9      ; 1042 x(3) <- c(9)  load x3 with ptr to start of user target word
003011	024066	 JSR 0,22,1   ; Read in paragraph
003012	010111	 LDX 1,9      ; x(1) <- c(9)   load x1 with ptr to string  <user inputted sentence>
003013	024065	 JSR 0,21,1   ; Print user target word back to them
003014	024067	 JSR 0,23,1   ; Find target word in paragraph function
003015	000000	 hlt
