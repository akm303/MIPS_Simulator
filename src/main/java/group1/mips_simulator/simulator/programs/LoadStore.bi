000000	000000	LOC	6			;BEGIN AT LOCATION 6
000006	000012	Data	10			;PUT 10 AT LOCATION 6
000007	000003	Data 	3			;PUT 3 AT LOCATION 7
000010	002000	Data	End			;PUT 1024 AT LOCATION
000011	000000	Data	0
000012	000014	Data	12
000013	000011	Data	9
000014	000022	Data	18
000015	000014	Data	12
000016	010207	LDX	2,7   			; X2 GETS 3
000017	003412	LDR	3,0,10			;R3 GETS 12
000020	003212	LDR	2,2,10			;R2 GETS 12
000021	002652	LDR	1,2,10,1		;R1 GETS 18
000022	006000	LDA	0,0,0			;R0 GETS 0
000023	010110	LDX	1,8			;X1 GETS 1024
000026	000000	LOC	1024
002000 	000000 End:	HLT				;STOP
