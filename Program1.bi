000000	000006	 LOC 6
000006	000041	 Data 000033   ;Data Pointer to the start of the string
000007	000041	 LOC 33			;BEGIN AT LOCATION 33
000041	000120	 Data 000080   ;Data Please input number in range [0, 65535]
000042	000154	 Data 000108   ;Data lease input number in range [0, 65535]
000043	000145	 Data 000101   ;Data ease input number in range [0, 65535]
000044	000141	 Data 000097   ;Data ase input number in range [0, 65535]
000045	000163	 Data 000115   ;Data se input number in range [0, 65535]
000046	000145	 Data 000101   ;Data e input number in range [0, 65535]
000047	000040	 Data 000032   ;Data  input number in range [0, 65535]
000050	000151	 Data 000105   ;Data input number in range [0, 65535]
000051	000156	 Data 000110   ;Data nput number in range [0, 65535]
000052	000160	 Data 000112   ;Data put number in range [0, 65535]
000053	000165	 Data 000117   ;Data ut number in range [0, 65535]
000054	000164	 Data 000116   ;Data t number in range [0, 65535]
000055	000040	 Data 000032   ;Data  number in range [0, 65535]
000056	000156	 Data 000110   ;Data number in range [0, 65535]
000057	000165	 Data 000117   ;Data umber in range [0, 65535]
000060	000155	 Data 000109   ;Data mber in range [0, 65535]
000061	000142	 Data 000098   ;Data ber in range [0, 65535]
000062	000145	 Data 000101   ;Data er in range [0, 65535]
000063	000162	 Data 000114   ;Data r in range [0, 65535]
000064	000040	 Data 000032   ;Data  in range [0, 65535]
000065	000151	 Data 000105   ;Data in range [0, 65535]
000066	000156	 Data 000110   ;Data n range [0, 65535]
000067	000040	 Data 000032   ;Data  range [0, 65535]
000070	000162	 Data 000114   ;Data range [0, 65535]
000071	000141	 Data 000097   ;Data ange [0, 65535]
000072	000156	 Data 000110   ;Data nge [0, 65535]
000073	000147	 Data 000103   ;Data ge [0, 65535]
000074	000145	 Data 000101   ;Data e [0, 65535]
000075	000040	 Data 000032   ;Data  [0, 65535]
000076	000133	 Data 000091   ;Data [0, 65535]
000077	000060	 Data 000048   ;Data 0, 65535]
000100	000054	 Data 000044   ;Data , 65535]
000101	000040	 Data 000032   ;Data  65535]
000102	000066	 Data 000054   ;Data 65535]
000103	000065	 Data 000053   ;Data 5535]
000104	000065	 Data 000053   ;Data 535]
000105	000063	 Data 000051   ;Data 35]
000106	000065	 Data 000053   ;Data 5]
000107	000135	 Data 000093   ;Data ]
000110	000000	 Data 000000   ;Data end of string
000111	001000	 LOC 512    ;Begin instruction code
001000	010106	 LDX 1,6
001001	002500	 LDR 1,1,0
001002	066401	 OUT 1,1
001003	012106	 STX 1,6
001004	002406	 LDR 1,0,6
001005	040401	 AIR 1,1
001006	004406	 STR 1,0,6
001007	002501	 LDR 1,1,1
001010	066401	 OUT 1,1
001011	012106	 STX 1,6
001012	002406	 LDR 1,0,6
001013	040401	 AIR 1,1
001014	004406	 STR 1,0,6
001015	002502	 LDR 1,1,2
001016	066401	 OUT 1,1
001017	012106	 STX 1,6
001020	002406	 LDR 1,0,6
001021	040401	 AIR 1,1
001022	004406	 STR 1,0,6
001023	002503	 LDR 1,1,3
001024	066401	 OUT 1,1
001025	012106	 STX 1,6
001026	002406	 LDR 1,0,6
001027	040401	 AIR 1,1
001030	004406	 STR 1,0,6
001031	000000	 HLT