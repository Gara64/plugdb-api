package org.cozy.plug;

public class EP_ACL {

	/* QEPs for insertion */
	public static String EP_ACL_INSERT =
		"/*EP \u0005 " + 
	"2 5 5 -1 1 ?3 # " + /* CI_LOOKUP ref_tab:Docs(1) key_col_id:4 ka_id:1	 key:?3(R0) -->R1 */
	"2 6 6 -1 0 ?2 # " + /* CI_LOOKUP ref_tab:Users(0) key_col_id:0 ka_id:0	 key:?2(R2) -->R3 */
	"7 4 4 5 6 # " + /* FLOW_X */
	"2 7 7 -1 2 ?1 # " + /* CI_LOOKUP ref_tab:Shares(2) key_col_id:8 ka_id:2	 key:?1(R4) -->R5 */
	"7 3 3 4 7 # " + /* FLOW_X */
	"6 2 2 3 3 v30 # " + /* CI_INSERT ref_tab:ACL(3), key_tab:ACL(3), col:IdGlobal(11), ka_id:3, key:v30(R6) */
	"5 1 1 2 3 0 0 0 r1 1 r3 2 r5 # " + /* TABLE_INSERT 3 cols, skt ACL(0), is_table: 0, [col_id value] */
	"5 0 0 1 6 3 1 12 ?1 13 ?2 14 ?3 15 ?4 16 ?5 11 r6 # " + /* TABLE_INSERT 6 cols, table ACL(3), is_table: 1, [col_id value] */
	"\u0000*/";

	public static String EP_USERS_INSERT =
		"/*EP \u0003 6 1 1 -1 0 v30 # 5 0 0 1 4 0 1 1 ?1 2 ?2 3 ?3 0 r0 # \u0000*/";

	public static String EP_DOCS_INSERT =
		"/*EP \u0003 6 1 1 -1 1 v30 # 5 0 0 1 4 1 1 5 ?1 6 ?2 7 ?3 4 r0 # \u0000*/";

	public static String EP_SHARES_INSERT =
		"/*EP \u0002 6 1 1 -1 2 v30 # 5 0 0 1 3 2 1 9 ?1 10 ?2 8 r0 # \u0000*/";

	/* QEPs for select star */
	public static String EP_ACL_SELECT_STAR =
		"/*EP \u0000 0 1 1 3 # 1 0 0 1 r0 6 3 1 11 12 13 14 15 16 # \u0000 6 1 1 IdGlobal 1 2 Share 1 3 User 1 4 Doc 0 5 Right 0 6 Auth # \u0000*/";

	public static String EP_USERS_SELECT_STAR =
		"/*EP \u0000 0 1 1 0 # 1 0 0 1 r0 4 0 1 0 1 2 3 # \u0000 4 1 1 IdGlobal 0 2 UserID 0 3 SharingRule 0 4 UserParam # \u0000*/";

	public static String EP_DOCS_SELECT_STAR =
		"/*EP \u0000 0 1 1 1 # 1 0 0 1 r0 4 1 1 4 5 6 7 # \u0000 4 1 1 IdGlobal 0 2 DocID 0 3 SharingRule 0 4 UserParam # \u0000*/";

	public static String EP_SHARES_SELECT_STAR =
		"/*EP \u0000 0 1 1 2 # 1 0 0 1 r0 3 2 1 8 9 10 # \u0000 3 1 1 IdGlobal 0 2 ShareID 0 3 Desc # \u0000*/";

	/* QEPs useful for reduce */
	public static String EP_DOCS_SELECT_DOCID_BY_SHARINGRULE =
		"/*EP \u0001 " + 
	"0 2 2 1 # " + /* SCAN, Table Docs (1) -->R0 */
	"1 1 1 2 r0 4 1 1 4 5 7 6 # " + /* TABLE_LOOKUP pos:R0, 4 cols, table Docs(1), is_table: 1  -->R1 - R4 */
	"4 0 0 1 6 0 ?1 r4 # " + /* SELECT, att: SharingRule(6), comparator: 0, parameter: ?1 (R5), from pos: R4*/
	"\u0000 3 1 1 IdGlobal 0 2 DocID 0 3 UserParam # " + /* META_RESULT, 3 cols, type(0-char 1-num 2-date) [out_result name] */
	"\u0000*/";

	public static String EP_USERS_SELECT_USERID_BY_SHARINGRULE =
		"/*EP \u0001 0 2 2 0 # 1 1 1 2 r0 4 0 1 0 1 3 2 # 4 0 0 1 2 0 ?1 r4 # \u0000 3 1 1 IdGlobal 0 2 UserID 0 3 UserParam # \u0000*/";

	public static String EP_DOCS_SELECT_USERPARAMS_BY_DOCID =
		"/*EP \u0002 " + 
	"0 3 3 1 # " + /* SCAN, Table Docs (1) -->R0 */
	"1 2 2 3 r0 4 1 1 4 7 5 6 # " + /* TABLE_LOOKUP pos:R0, 4 cols, table Docs(1), is_table: 1  -->R1 - R4 */
	"4 1 1 2 5 0 ?1 r3 # " + /* SELECT, att: DocID(5), comparator: 0, parameter: ?1 (R5), from pos: R3*/
	"4 0 0 1 6 0 ?2 r4 # " + /* SELECT, att: SharingRule(6), comparator: 0, parameter: ?2 (R6), from pos: R4*/
	"\u0000 2 1 1 IdGlobal 0 2 UserParam # " + /* META_RESULT, 2 cols, type(0-char 1-num 2-date) [out_result name] */
	"\u0000*/";

	public static String EP_USERS_SELECT_USERPARAMS_BY_USERID =
		"/*EP \u0002 " + 
	"0 3 3 0 # " + /* SCAN, Table Users (0) -->R0 */
	"1 2 2 3 r0 4 0 1 0 3 1 2 # " + /* TABLE_LOOKUP pos:R0, 4 cols, table Users(0), is_table: 1  -->R1 - R4 */
	"4 1 1 2 1 0 ?1 r3 # " + /* SELECT, att: UserID(1), comparator: 0, parameter: ?1 (R5), from pos: R3*/
	"4 0 0 1 2 0 ?2 r4 # " + /* SELECT, att: SharingRule(2), comparator: 0, parameter: ?2 (R6), from pos: R4*/
	"\u0000 2 1 1 IdGlobal 0 2 UserParam # " + /* META_RESULT, 2 cols, type(0-char 1-num 2-date) [out_result name] */
	"\u0000*/";

	/* QEP for ACL comme les autres'selection */
	public static String EP_ACL_SELECT_BY_SHAREID =
		"/*EP \u0001 " + 
	"0 5 5 3 # " + /* SCAN, Table ACL (3) -->R0 */
	"1 4 4 5 r0 3 0 0 1 0 2 # " + /* TABLE_LOOKUP pos:R0, 3 cols, skt ACL(0), is_table: 0  -->R1 - R3 */
	"1 3 3 4 r3 1 2 1 9 # " + /* TABLE_LOOKUP pos:R3, 1 cols, table Shares(2), is_table: 1  -->R4 - R4 */
	"4 2 2 3 9 0 ?1 r4 # " + /* SELECT, att: ShareID(9), comparator: 0, parameter: ?1 (R5), from pos: R4*/
	"1 1 1 2 r1 1 0 1 1 # " + /* TABLE_LOOKUP pos:R1, 1 cols, table Users(0), is_table: 1  -->R6 - R6 */
	"1 0 0 1 r2 1 1 1 5 # " + /* TABLE_LOOKUP pos:R2, 1 cols, table Docs(1), is_table: 1  -->R7 - R7 */
	"\u0000 2 0 6 UserID 0 7 DocID # " + /* META_RESULT, 2 cols, type(0-char 1-num 2-date) [out_result name] */
	"\u0000*/";

	/* Others QEPs */
	public static String EP_ACL_SELECT_STAR_BY_USER =
		"/*EP \u0001 0 2 2 3 # 1 1 1 2 r0 6 3 1 11 12 13 14 15 16 # 4 0 0 1 13 0 ?1 r3 # \u0000 6 1 1 IdGlobal 1 2 Share 1 3 User 1 4 Doc 0 5 Right 0 6 Auth # \u0000*/";

	public static String EP_ACL_SELECT_DOCID_BY_USERID =
	//a tester, diverge avec précedent qep (# 1 3 3 4 r0 2 0 0 2 1 au lieu de # 1 3 3 4 r0 2 0 0 1 0)
		"/*EP \u0001 " + 
	"0 4 4 3 # " + /* SCAN, Table ACL (3) -->R0 */
	"1 3 3 4 r0 2 0 0 0 1 # " + /* TABLE_LOOKUP pos:R0, 2 cols, skt ACL(0), is_table: 0  -->R1 - R2 */
	"1 2 2 3 r2 1 0 1 1 # " + /* TABLE_LOOKUP pos:R2, 1 cols, table Users(0), is_table: 1  -->R3 - R3 */
	"4 1 1 2 1 0 ?1 r3 # " + /* SELECT, att: UserID(1), comparator: 0, parameter: ?1 (R4), from pos: R3*/
	"1 0 0 1 r1 1 1 1 5 # " + /* TABLE_LOOKUP pos:R1, 1 cols, table Docs(1), is_table: 1  -->R5 - R5 */
	"\u0000 1 0 5 DocID # " + /* META_RESULT, 1 cols, type(0-char 1-num 2-date) [out_result name] */
	"\u0000*/";

	public static String EP_ACL_SELECT_CREDS_BY_USERID =
	//a tester, diverge avec précedent qep (# # 1 4 4 5 r0 2 0 0 2 1 au lieu de # # 1 4 4 5 r0 2 0 0 1 0)
		"/*EP \u0001 " + 
	"0 5 5 3 # " + /* SCAN, Table ACL (3) -->R0 */
	"1 4 4 5 r0 2 0 0 0 1 # " + /* TABLE_LOOKUP pos:R0, 2 cols, skt ACL(0), is_table: 0  -->R1 - R2 */
	"1 3 3 4 r2 1 0 1 1 # " + /* TABLE_LOOKUP pos:R2, 1 cols, table Users(0), is_table: 1  -->R3 - R3 */
	"4 2 2 3 1 0 ?1 r3 # " + /* SELECT, att: UserID(1), comparator: 0, parameter: ?1 (R4), from pos: R3*/
	"1 1 1 2 r1 1 1 1 5 # " + /* TABLE_LOOKUP pos:R1, 1 cols, table Docs(1), is_table: 1  -->R5 - R5 */
	"1 0 0 1 r0 2 3 1 15 16 # " + /* TABLE_LOOKUP pos:R0, 2 cols, table ACL(3), is_table: 1  -->R6 - R7 */
	"\u0000 3 0 5 DocID 0 6 right 0 7 auth # " + /* META_RESULT, 3 cols, type(0-char 1-num 2-date) [out_result name] */
	"\u0000*/";

	public static String EP_DOCS_SELECT_BY_DOCID =
		"/*EP \u0001 0 2 2 1 # 1 1 1 2 r0 4 1 1 4 5 6 7 # 4 0 0 1 5 0 ?1 r2 # \u0000 4 1 1 IdGlobal 0 2 DocID 0 3 SharingRule 0 4 UserParam # \u0000*/";

	public static String EP_USERS_SELECT_BY_USERID =
		"/*EP \u0001 0 2 2 0 # 1 1 1 2 r0 4 0 1 0 1 2 3 # 4 0 0 1 1 0 ?1 r2 # \u0000 4 1 1 IdGlobal 0 2 UserID 0 3 SharingRule 0 4 UserParam # \u0000*/";
	
	
	public static String EP_DELETE_DOC =
			"/*EP \u0001 " + 
		"2 2 2 -1 1 ?1 # " + /* CI_LOOKUP ref_tab:Docs(1) key_col_id:4 ka_id:1	 key:?1(R0) -->R1 */
		"5 1 1 2 3 4 1 17 v11 18 r1 19 v10 # " + /* TABLE_INSERT 3 cols, table LogDeleted(4), is_table: 1, [col_id value] -->R2 - R3*/
		"9 0 0 1 1 r1 # " + /* TABLE DELETE, table Docs(1), tuple pos: R1 */
		"\u0000*/";

	public static String EP_DELETE_USER =
		"/*EP \u0001 " + 
	"2 2 2 -1 0 ?1 # " + /* CI_LOOKUP ref_tab:Users(0) key_col_id:0 ka_id:0	 key:?1(R0) -->R1 */
	"5 1 1 2 3 4 1 17 v10 18 r1 19 v10 # " + /* TABLE_INSERT 3 cols, table LogDeleted(4), is_table: 1, [col_id value] -->R2 - R3*/
	"9 0 0 1 0 r1 # " + /* TABLE DELETE, table Users(0), tuple pos: R1 */
	"\u0000*/";
	
	public static String EP_DELETE_SHARE =
			"/*EP \u0001 " + 
		"2 2 2 -1 2 ?1 # " + /* CI_LOOKUP ref_tab:Shares(2) key_col_id:8 ka_id:2	 key:?1(R0) -->R1 */
		"5 1 1 2 3 4 1 17 v12 18 r1 19 v10 # " + /* TABLE_INSERT 3 cols, table LogDeleted(4), is_table: 1, [col_id value] -->R2 - R3*/
		"9 0 0 1 2 r1 # " + /* TABLE DELETE, table Shares(2), tuple pos: R1 */
		"\u0000*/";
	
	
	//test ep
	public static String EP_TEST =
			"/*EP \u0001 " + 
					"0 4 4 3 # " + /* SCAN, Table ACL (3) -->R0 */
					"1 3 3 4 r0 1 3 1 12 # " + /* TABLE_LOOKUP pos:R0, 1 cols, table ACL(3), is_table: 1  -->R1 - R1 */
					"4 2 2 3 12 0 ?1 r1 # " + /* SELECT, att: Share(12), comparator: 0, parameter: ?1 (R2), from pos: R1*/
					"1 1 1 2 r0 1 0 0 1 # " + /* TABLE_LOOKUP pos:R0, 1 cols, skt ACL(0), is_table: 0  -->R3 - R3 */
					"1 0 0 1 r3 2 0 1 0 1 # " + /* TABLE_LOOKUP pos:R3, 2 cols, table Users(0), is_table: 1  -->R4 - R5 */
					"\u0000 2 1 4 IdGlobal 0 5 UserID # " + /* META_RESULT, 2 cols, type(0-char 1-num 2-date) [out_result name] */
					"\u0000*/";
	
	
	//manual QEP
	//params : uid, shareid, idglobal share (tmp)
	int cpt = 0;
	public static String EP_MATCH_DOC = 

		"/*EP \u0002 " +
		"0 7 7 0 # " + //SCAN USER --> r0
		"0 6 6 1 # " + //SCAN DOC --> r1
		"0 10 10 2 # " + //SCAN SHARE -> r2
		"1 4 4 7 r0 4 0 1 0 3 1 2 # " + // T_LOOKUP USER --> r3-r6
		"4 3 3 4 1 0 ?1 r5 # " + //SELECT USER UID --> r7
		"4 2 2 3 2 0 ?2 r6 # " + //SELECT USER SHAREID --> r8
		"7 5 5 2 6 # " +	//FLOW_X  --> X
		"1 1 1 5 r1 3 1 1 4 7 6 # " +  // T_LOOKUP DOC--> r9-r11
		"4 8 8 1 6 0 ?2 r11 # " + //SELECT DOC SHAREID --> r12
		"4 9 9 8 3 0 r10 r4 # " + //SELECT U PARAMS --> X (no '?', therefore no register produced)
		
		"7 11 11 9 10 # " +	//FLOW_X  --> X
		"1 12 12 11 r2 2 2 1 8 9 # " + // T_LOOKUP SHARE --> r13-r14
		"4 0 0 12 9 0 ?2 r14 # " + // SELECT IDGLOBAL SHARE --> r15
				
		"\u0000 4 1 3 IdUser 1 9 IdDoc 1 13 Share 0 11 ShareId # \u0000*/";
	
	public static String EP_INSERT_SELECT_MATCH_DOCS = 
	/*"/*EP \u0003 " +
		"0 7 7 0 # " + //SCAN USER --> r0
		"0 6 6 1 # " + //SCAN DOC --> r1
		"1 4 4 7 r0 4 0 1 0 3 1 2 # " + // T_LOOKUP USER --> r2-r5
		"4 3 3 4 1 0 ?1 r4 # " + //SELECT USER UID --> X
		"4 2 2 3 2 0 ?2 r5 # " + //SELECT USER SHAREID --> X
		"1 1 1 6 r1 3 1 1 4 7 6 # " +  // T_LOOKUP DOC--> r8-r10
		"4 5 5 1 6 0 ?2 r10 # " + //SELECT DOC SHAREID --> X
		"7 0 0 5 2 # " +	//FLOW_X  --> X
		"\u0000 3 1 2 IdUser 1 8 IdDoc 0 10 ShareId # \u0000/";*/

		/* TODO : do the same plan with doc and user switched */
		/* Warning : the IdGlobal is the same for inserted values as it is computed during qep parsing inside plugdb */
			
		"/*EP \u0002 " +
		"0 7 7 0 # " + //SCAN USER --> r0
		"0 6 6 1 # " + //SCAN DOC --> r1
		"0 10 10 2 # " + //SCAN SHARE -> r2
		"1 4 4 7 r0 4 0 1 0 3 1 2 # " + // T_LOOKUP USER --> r3-r6 (IdGlobal, UserParam, UserID, SharingRule)
		"4 3 3 4 1 0 ?1 r5 # " + //SELECT USER UID --> r7
		"4 2 2 3 2 0 ?2 r6 # " + //SELECT USER SHAREID --> r8
		"7 5 5 2 6 # " +	//FLOW_X  --> X
		"1 1 1 5 r1 4 1 1 4 5 7 6 # " +  // T_LOOKUP DOC--> r9-r12 (IdGlobal, DocID, UserParam, SharingRule)
		"4 8 8 1 6 0 ?2 r12 # " + //SELECT DOC SHAREID --> r13
		"4 9 9 8 3 0 r11 r4 # " + //SELECT U PARAMS --> X (no '?', therefore no register produced)
		
		"7 11 11 9 10 # " +	//FLOW_X  --> X
		"1 12 12 11 r2 2 2 1 8 9 # " + // T_LOOKUP SHARE --> r14-r15
		"4 13 13 12 9 0 ?2 r15 # " + // SELECT IDGLOBAL SHARE --> r16
		
		"5 14 14 13 3 0 0 0 r1 1 r0 2 r2 # " + // TABLE_INSERT 3 cols, skt ACL(0), is_table: 0, [col_id value]  
		"5 0 0 14 6 3 1 12 r14 13 r3 14 r9 15 v0'rw' 16 v0'auth'11 v30 # " + // TABLE_INSERT 6 cols, table ACL(3), is_table: 1, [col_id value] 
		
		"\u0000 2 0 5 UserID 0 10 DocID # \u0000*/";
	
	public static String EP_INSERT_SELECT_MATCH_USERS = 

		/* Warning : the IdGlobal is the same for inserted values as it is computed during qep parsing inside plugdb */
			
		"/*EP \u0002 " +
		"0 7 7 1 # " + //SCAN DOC --> r0
		"0 6 6 0 # " + //SCAN USER --> r1
		"0 10 10 2 # " + //SCAN SHARE -> r2
		"1 4 4 7 r0 4 1 1 4 7 5 6 # " + // T_LOOKUP Doc --> r3-r6 (IdGlobal, UserParam, DocID, SharingRule)
		"4 3 3 4 5 0 ?1 r5 # " + //SELECT DOC DOCID --> r7
		"4 2 2 3 6 0 ?2 r6 # " + //SELECT DOC SHAREID --> r8
		"7 5 5 2 6 # " +	//FLOW_X  --> X
		"1 1 1 5 r1 4 0 1 0 1 3 2 # " +  // T_LOOKUP USER--> r9-r12 (IdGlobal, UserID, UserParam, SharingRule)
		"4 8 8 1 2 0 ?2 r12 # " + //SELECT USER SHAREID --> r13
		"4 9 9 8 7 0 r11 r4 # " + //SELECT U PARAMS --> X (no '?', therefore no register produced)
		
		"7 11 11 9 10 # " +	//FLOW_X  --> X
		"1 12 12 11 r2 2 2 1 8 9 # " + // T_LOOKUP SHARE --> r14-r15
		"4 13 13 12 9 0 ?2 r15 # " + // SELECT IDGLOBAL SHARE --> r16
		
		"5 14 14 13 3 0 0 0 r0 1 r1 2 r2 # " + // TABLE_INSERT 3 cols, skt ACL(0), is_table: 0, [col_id value]  
		"5 0 0 14 6 3 1 12 r14 13 r9 14 r3 15 v0'rw' 16 v0'auth'11 v30 # " + // TABLE_INSERT 6 cols, table ACL(3), is_table: 1, [col_id value] 
		
		"\u0000 2 0 10 UserID 0 5 DocID # \u0000*/";
	
	public static String EP_INSERT_MATCH_DOCS = 
		"/*EP \u0002 " +
		"0 7 7 0 # " + //SCAN USER --> r0
		"0 6 6 1 # " + //SCAN DOC --> r1
		"0 10 10 2 # " + //SCAN SHARE -> r2
		"1 4 4 7 r0 4 0 1 0 3 1 2 # " + // T_LOOKUP USER --> r3-r6 (IdGlobal, UserParam, UserID, SharingRule)
		"4 3 3 4 1 0 ?1 r5 # " + //SELECT USER UID --> r7
		"4 2 2 3 2 0 ?2 r6 # " + //SELECT USER SHAREID --> r8
		"7 5 5 2 6 # " +	//FLOW_X  --> X
		"1 1 1 5 r1 3 1 1 4 7 6 # " +  // T_LOOKUP DOC--> r9-r11 (IdGlobal, UserParam, SharingRule)
		"4 8 8 1 6 0 ?2 r11 # " + //SELECT DOC SHAREID --> r12
		"4 9 9 8 3 0 r10 r4 # " + //SELECT U PARAMS --> X (no '?', therefore no register produced)
		
		"7 11 11 9 10 # " +	//FLOW_X  --> X
		"1 12 12 11 r2 2 2 1 8 9 # " + // T_LOOKUP SHARE --> r13-r14
		"4 13 13 12 9 0 ?2 r14 # " + // SELECT IDGLOBAL SHARE --> r15
		
		"5 14 14 13 3 0 0 0 r1 1 r0 2 r2 # " + // TABLE_INSERT 3 cols, skt ACL(0), is_table: 0, [col_id value]  
		"5 0 0 14 6 3 1 12 r13 13 r3 14 r9 15 v0'rw' 16 v0'auth'11 v30 # " + // TABLE_INSERT 6 cols, table ACL(3), is_table: 1, [col_id value] 
 		"\u0000*/";
	
	public static String EP_INSERT_MATCH_USERS = 

		"/*EP \u0002 " +
		"0 7 7 1 # " + //SCAN DOC --> r0
		"0 6 6 0 # " + //SCAN USER --> r1
		"0 10 10 2 # " + //SCAN SHARE -> r2
		"1 4 4 7 r0 4 1 1 4 7 5 6 # " + // T_LOOKUP Doc --> r3-r6 (IdGlobal, UserParam, DocID, SharingRule)
		"4 3 3 4 5 0 ?1 r5 # " + //SELECT DOC DOCID --> r7
		"4 2 2 3 6 0 ?2 r6 # " + //SELECT DOC SHAREID --> r8
		"7 5 5 2 6 # " +	//FLOW_X  --> X
		"1 1 1 5 r1 3 0 1 0 3 2 # " +  // T_LOOKUP USER--> r9-r11 (IdGlobal, UserParam, SharingRule)
		"4 8 8 1 2 0 ?2 r11 # " + //SELECT USER SHAREID --> r12
		"4 9 9 8 7 0 r10 r4 # " + //SELECT U PARAMS --> X (no '?', therefore no register produced)
		
		"7 11 11 9 10 # " +	//FLOW_X  --> X
		"1 12 12 11 r2 2 2 1 8 9 # " + // T_LOOKUP SHARE --> r13-r14
		"4 13 13 12 9 0 ?2 r14 # " + // SELECT IDGLOBAL SHARE --> r15
		
		"5 14 14 13 3 0 0 0 r0 1 r1 2 r2 # " + // TABLE_INSERT 3 cols, skt ACL(0), is_table: 0, [col_id value]  
		"5 0 0 14 6 3 1 12 r13 13 r9 14 r3 15 v0'rw' 16 v0'auth'11 v30 # " + // TABLE_INSERT 6 cols, table ACL(3), is_table: 1, [col_id value] 
		"\u0000*/";
	
	public static String EP_DELETE_MATCH_DOCS = 
			"/*EP \u0002 " +
			"0 7 7 0 # " + //SCAN USER --> r0
			"0 6 6 1 # " + //SCAN DOC --> r1
			"0 10 10 2 # " + //SCAN SHARE -> r2
			"1 4 4 7 r0 4 0 1 0 3 1 2 # " + // T_LOOKUP USER --> r3-r6 (IdGlobal, UserParam, UserID, SharingRule)
			"4 3 3 4 1 0 ?1 r5 # " + //SELECT USER UID --> r7
			"4 2 2 3 2 0 ?2 r6 # " + //SELECT USER SHAREID --> r8
			"7 5 5 2 6 # " +	//FLOW_X  --> X
			"1 1 1 5 r1 3 1 1 4 7 6 # " +  // T_LOOKUP DOC--> r9-r11 (IdGlobal, UserParam, SharingRule)
			"4 8 8 1 6 0 ?2 r11 # " + //SELECT DOC SHAREID --> r12
			"4 9 9 8 3 0 r10 r4 # " + //SELECT U PARAMS --> X (no '?', therefore no register produced)
			
			"7 11 11 9 10 # " +	//FLOW_X  --> X
			"1 12 12 11 r2 2 2 1 8 9 # " + // T_LOOKUP SHARE --> r13-r14
			"4 13 13 12 9 0 ?2 r14 # " + // SELECT IDGLOBAL SHARE --> r15
			
			"5 14 14 13 3 4 1 17 v13 18 r0 2 r2 # " + // TABLE_INSERT 3 cols, skt ACL(0), is_table: 0, [col_id value]  
			"9 0 0 14 3 r1" + // TABLE DELETE, table ACL(3), tuple pos: R1
	 		"\u0000*/";
	

	
	
	
	public static String EP_SELECT_STAR_SKT_ACL = 
		"/*EP \u0000 " +
		"0 1 1 3 # " +
		"1 0 0 1 r0 3 0 0 0 1 2 # " +
		"\u0000 3 1 1 IdDoc 1 2 IdUser 1 3 IdShare # \u0000*/";
	

	
}
