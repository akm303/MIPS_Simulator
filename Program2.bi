000000	000006	 LOC 6
000006	000041	 data 33   ;6 Ptr to start of "Please enter a sentence "
000007	000074	 data 60   ;7 Ptr to start of "Please enter a target word "
000010	000144	 data 100  ;8 Const Ptr to start of user input paragraph
000011	000127	 data 87   ;9 Const ptr to start of user target word
000012	000000	 data 0    ;10 const value 0
000013	000001	 data 1    ;11 Word count
000014	000001	 data 1    ;12 Sentence count
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
000031	002734	 data 1500 ;25 const pointer to Print not found function
000032	002260	 data 1200 ;26 const pointer to start of "Search for word: "
000033	002303	 data 1219 ;27 const ptr to start of "Word not found."
000034	002324	 data 1236 ;28 const ptr to start of "Match for: "
000035	002341	 data 1249 ;29 const ptr to start of "Sentence #"
000036	002355	 data 1261 ;30 const ptr to start of "Word #"
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
002045	110000	 SETCCE 0     ;1061 check if r(0) target_word character is null terminator
002046	016306	 JNE 3,6      ;1062 If the character is NOT 0000 then jump to 1059+6 = 1065
002047	010330	 LDX 3,24     ;1063 x(3) <- c(24)      load ptr to Print_Found function
002050	022300	 JMA 3,0      ;1064 Unconditional jump to Print_Found function
002051	110400	 SETCCE 1     ;1065 check if r(1) paragraph character is null terminator
002052	016312	 JNE 3,10     ;1066 If the character is NOT 0000 then jump to 1059+6 = 1069
002053	010331	 LDX 3,25     ;1067 x(3) <- c(25)       load x3 with ptr to Print Not Found func
002054	022300	 JMA 3,0      ;1068 Unconditional jump to not found func
002055	036100	 SMR 0,1,0    ;1069 Compare character at x(1) to char in r(0)  (IE compare paragraph char to target_word char)
002056	120101	 AIX 1,1      ;1070    Increment x(1) by 1
002057	120201	 AIX 2,1      ;1071    Increment x(2) by 1
002060	110000	 SETCCE 0     ;1072 If they are the same character, then r(0) will hold value 0
002061	014300	 JZ 3,0       ;1073 jump to x(3) = <Start of this function> = 1059
002062	004420	 STR 1,0,16   ;1074 c(16) <- r(1)          Save current paragraph character into c(16)
002063	036416	 SMR 1,0,14   ;1075 r(1) <- r(1) - c(14)   compare current char to " " space character
002064	110400	 SETCCE 1     ;1076
002065	016326	 JNE 3,22     ;1077 If current char != " ", then jump to 1081(=1059+22). Else...
002066	003013	 LDR 2,0,11   ;1078 r(2) <- c(11)      load word count into r2
002067	041001	 AIR 2,1      ;1079 r(2) <- r(2) + 1   increment word count by 1
002070	005013	 STR 2,0,11   ;1080 c(11) <- r(2)      Store word count back to memory
002071	002420	 LDR 1,0,16   ;1081 r(1) <- c(16)          load r(1) with current character (again)
002072	036417	 SMR 1,0,15   ;1082 r(1) <- r(1) - c(15)   compare current char to "." period character
002073	110400	 SETCCE 1     ;1083
002074	016337	 JNE 3,31     ;1084 If current char != "." then jump to 1090(=1059+31)
002075	003012	 LDR 2,0,10   ;1085 r(2) <- c(10)          load value 0 into r2
002076	005013	 STR 2,0,11   ;1086 c(11) <- r(2)          set word count to 0
002077	003014	 LDR 2,0,12   ;1087 r(2) <- c(12)          load sentence count into r2
002100	041001	 AIR 2,1      ;1088 r(2) <- r(2) + 1       increment sentence count by 1
002101	005014	 STR 2,0,12   ;1089 c(12) <- r(2)
002102	003015	 LDR 2,0,13    ;1090 r(2) <- c(13)     load r2 with ptr to current paragraph character
002103	041001	 AIR 2,1       ;1091 r(2) <- r(2) + 1  Add 1 to r2
002104	005015	 STR 2,0,13    ;1092 c(13) <- r(2)     Store r2 back to memory
002105	010115	 LDX 1,13      ;1093 x(1) <- c(13)     load x1 with ptr to start of user input paragraph
002106	010211	 LDX 2,9       ;1094 x(2) <- c(9)      load x2 with ptr to start of target word
002107	022300	 JMA 3,0       ;1095 Unconditional jump to top of function again (but with incremented paragraph character target)
002110	002114	 LOC 1100     ; Print_Found function
002114	003424	 LDR 3,0,20   ; load newline character into r3
002115	067401	 OUT 3,1      ; print newline character
002116	010134	 LDX 1,28     ; x(1) <- c(28)  load x3 with ptr to "Match for: "
002117	024065	 JSR 0,21,1   ; Print string function
002120	010111	 LDX 1,9      ; const ptr to target word
002121	024065	 JSR 0,21,1   ; Print target word
002122	003424	 LDR 3,0,20   ; load newline character into r3
002123	067401	 OUT 3,1      ; print newline character
002124	010135	 LDX 1,29     ; x(1) <- c(29)         load x1 with ptr to Sentence #"
002125	024065	 JSR 0,21,1   ; print string function
002126	002021	 LDR 0,0,17   ; r(0) <- c(17)         load r0 with bitmask for number to ascii conversion
002127	002414	 LDR 1,0,12   ; r(1) <- c(12)         load r1 with sentence count
002130	054400	 ORR 1,0      ; r(1) <- r(1) | r(0)   convert r1 to ascii character
002131	066401	 OUT 1,1      ; print character in r(1)
002132	003424	 LDR 3,0,20   ; load newline character into r3
002133	067401	 OUT 3,1      ; print newline character
002134	010136	 LDX 1,30     ; x(1) <- c(30)        load x1 with ptr to "Word #"
002135	024065	 JSR 0,21,1   ; print string function
002136	002021	 LDR 0,0,17   ; r(0) <- c(17)         load r0 with bitmask for number to ascii conversion
002137	002413	 LDR 1,0,11   ; r(1) <- c(11)        load reg 1 with word count
002140	054400	 ORR 1,0      ; r(1) <- r(1) | r(0)  convert r1 to ascii character
002141	066401	 OUT 1,1      ; print character in r(1)
002142	000000	 hlt
002143	002260	 LOC 1200
002260	000123	 data 83                 ;1200 [S] earch for word:
002261	000145	 data 101                ;1201 [e] arch for word:
002262	000141	 data 97                 ;1202 [a] rch for word:
002263	000162	 data 114                ;1203 [r] ch for word:
002264	000143	 data 99                 ;1204 [c] h for word:
002265	000150	 data 104                ;1205 [h]  for word:
002266	000040	 data 32                 ;1206 [ ] for word:
002267	000146	 data 102                ;1207 [f] or word:
002270	000157	 data 111                ;1208 [o] r word:
002271	000162	 data 114                ;1209 [r]  word:
002272	000040	 data 32                 ;1210 [ ] word:
002273	000167	 data 119                ;1211 [w] ord:
002274	000157	 data 111                ;1212 [o] rd:
002275	000162	 data 114                ;1213 [r] d:
002276	000144	 data 100                ;1214 [d] :
002277	000072	 data 58                 ;1215 [:]
002300	000040	 data 32                 ;1216 [ ]
002301	000012	 data 10                 ;1217 \n
002302	000000	 data 000000             ;1218 end of string
002303	002303	 LOC 1219
002303	000127	 data 87                 ;1219 [W] ord not found.
002304	000157	 data 111                ;1220 [o] rd not found.
002305	000162	 data 114                ;1221 [r] d not found.
002306	000144	 data 100                ;1222 [d]  not found.
002307	000040	 data 32                 ;1223 [ ] not found.
002310	000156	 data 110                ;1224 [n] ot found.
002311	000157	 data 111                ;1225 [o] t found.
002312	000164	 data 116                ;1226 [t]  found.
002313	000040	 data 32                 ;1227 [ ] found.
002314	000146	 data 102                ;1228 [f] ound.
002315	000157	 data 111                ;1229 [o] und.
002316	000165	 data 117                ;1230 [u] nd.
002317	000156	 data 110                ;1231 [n] d.
002320	000144	 data 100                ;1232 [d] .
002321	000056	 data 46                 ;1233 [.]
002322	000012	 data 10                 ;1234 \n
002323	000000	 data 000000             ;1235 end of string
002324	002324	 LOC 1236
002324	000115	 data 77                 ;1236 [M] atch for:
002325	000141	 data 97                 ;1237 [a] tch for:
002326	000164	 data 116                ;1238 [t] ch for:
002327	000143	 data 99                 ;1239 [c] h for:
002330	000150	 data 104                ;1240 [h]  for:
002331	000040	 data 32                 ;1241 [ ] for:
002332	000146	 data 102                ;1242 [f] or:
002333	000157	 data 111                ;1243 [o] r:
002334	000162	 data 114                ;1244 [r] :
002335	000072	 data 58                 ;1245 [:]
002336	000040	 data 32                 ;1246 [ ]
002337	000012	 data 10                 ;1247 \n
002340	000000	 data 000000             ;1248 end of string
002341	002341	 LOC 1249
002341	000123	 data 83                 ;1249 [S] entence #
002342	000145	 data 101                ;1250 [e] ntence #
002343	000156	 data 110                ;1251 [n] tence #
002344	000164	 data 116                ;1252 [t] ence #
002345	000145	 data 101                ;1253 [e] nce #
002346	000156	 data 110                ;1254 [n] ce #
002347	000143	 data 99                 ;1255 [c] e #
002350	000145	 data 101                ;1256 [e]  #
002351	000040	 data 32                 ;1257 [ ] #
002352	000043	 data 35                 ;1258 [#]
002353	000012	 data 10                 ;1259 \n
002354	000000	 data 000000             ;1260 end of string
002355	002355	 LOC 1261
002355	000127	 data 87                 ;1261 [W] ord #
002356	000157	 data 111                ;1262 [o] rd #
002357	000162	 data 114                ;1263 [r] d #
002360	000144	 data 100                ;1264 [d]  #
002361	000040	 data 32                 ;1265 [ ] #
002362	000043	 data 35                 ;1266 [#]
002363	000012	 data 10                 ;1267 \n
002364	000000	 data 000000             ;1268 end of string
002365	002734	 LOC 1500     ; Print not found function
002734	010133	 LDX 1,27     ; x(1) <- c(27)  load x1 with ptr to stirng "Word not found"
002735	024065	 JSR 0,21,1   ; print string function
002736	000000	 hlt
002737	003000	 LOC 1536     ; Octal 3000 main function
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
