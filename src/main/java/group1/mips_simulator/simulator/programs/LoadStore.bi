000016	010207	14	LDX	2,7         ; X2 GETS 3
000017	003412	15	LDR	3,0,10      ;R3 GETS 12
000020	003212	16	LDR	2,2,10      ;R2 GETS 12
000021	002652	17	LDR	1,2,10,1    ;R1 GETS 18
000022	006000	18	LDA	0,0,0   	;R0 GETS 0
000023	010110	19	LDX	1,8         ;X1 GETS 1024
000024	002124	20  LDR 0,1,20      ;R0 <- c(20)
000025	004524	21  STR 1,1,20      ;Memory(EA) <- R1
000026	007264	22  LDA 2,2,20,1    ; R2 <- EA
000027	010364	23  LDX 3,20,1      ; IX3 <- c(EA)
000030	012164	24  STX 1,20,1      ; Memory(EA) <- c(IX1)
