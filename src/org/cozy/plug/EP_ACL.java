package org.cozy.plug;

public class EP_ACL {

	/* QEPs for insertion */
	public static String EP_ACL_INSERT =
			"/*EP \u0005 2 5 5 -1 2 ?1 # 2 6 6 -1 0 ?2 # 7 4 4 5 6 # 2 7 7 -1 1 ?3 # 7 3 3 4 7 # 6 2 2 3 3 v30 # 5 1 1 2 3 0 0 0 r1 1 r3 2 r5 # 5 0 0 1 6 3 1 12 ?1 13 ?2 14 ?3 15 ?4 16 ?5 11 r6 # \u0000*/";
	
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
		"/*EP \u0001 0 2 2 1 # 1 1 1 2 r0 4 1 1 4 5 7 6 # 4 0 0 1 6 0 ?1 r4 # \u0000 3 1 1 IdGlobal 0 2 DocID 0 3 UserParam # \u0000*/";

	public static String EP_USERS_SELECT_USERID_BY_SHARINGRULE =
		"/*EP \u0001 0 2 2 0 # 1 1 1 2 r0 4 0 1 0 1 3 2 # 4 0 0 1 2 0 ?1 r4 # \u0000 3 1 1 IdGlobal 0 2 UserID 0 3 UserParam # \u0000*/";

	public static String EP_DOCS_SELECT_USERPARAMS_BY_DOCID =
		"/*EP \u0002 0 3 3 1 # 1 2 2 3 r0 4 1 1 4 7 5 6 # 4 1 1 2 5 0 ?1 r3 # 4 0 0 1 6 0 ?2 r4 # \u0000 2 1 1 IdGlobal 0 2 UserParam # \u0000*/";

	public static String EP_USERS_SELECT_USERPARAMS_BY_USERID =
		"/*EP \u0002 0 3 3 0 # 1 2 2 3 r0 4 0 1 0 3 1 2 # 4 1 1 2 1 0 ?1 r3 # 4 0 0 1 2 0 ?2 r4 # \u0000 2 1 1 IdGlobal 0 2 UserParam # \u0000*/";

	 /* QEP for ACL selection */
	public static String EP_ACL_SELECT_BY_SHAREID =
			"/*EP \u0001 0 5 5 3 # 1 4 4 5 r0 3 0 0 2 1 0 # 1 3 3 4 r3 1 2 1 9 # 4 2 2 3 9 0 ?1 r4 # 1 1 1 2 r1 1 1 1 5 # 1 0 0 1 r2 1 0 1 1 # \u0000 2 0 6 DocID 0 7 UserID # \u0000*/";
	
	 /* Others QEPs */
	public static String EP_ACL_SELECT_STAR_BY_USER =
		"/*EP \u0001 0 2 2 3 # 1 1 1 2 r0 6 3 1 11 12 13 14 15 16 # 4 0 0 1 13 0 ?1 r3 # \u0000 6 1 1 IdGlobal 1 2 Share 1 3 User 1 4 Doc 0 5 Right 0 6 Auth # \u0000*/";

	public static String EP_ACL_SELECT_DOCID_BY_USERID =
		"/*EP \u0001 0 4 4 3 # 1 3 3 4 r0 2 0 0 1 0 # 1 2 2 3 r2 1 0 1 1 # 4 1 1 2 1 0 ?1 r3 # 1 0 0 1 r1 1 1 1 5 # \u0000 1 0 5 DocID # \u0000*/";

	public static String EP_ACL_SELECT_CREDS_BY_USERID =
		"/*EP \u0001 0 5 5 3 # 1 4 4 5 r0 2 0 0 1 0 # 1 3 3 4 r2 1 0 1 1 # 4 2 2 3 1 0 ?1 r3 # 1 1 1 2 r1 1 1 1 5 # 1 0 0 1 r0 2 3 1 15 16 # \u0000 3 0 5 DocID 0 6 right 0 7 auth # \u0000*/";

	public static String EP_DOCS_SELECT_BY_DOCID =
		"/*EP \u0001 0 2 2 1 # 1 1 1 2 r0 4 1 1 4 5 6 7 # 4 0 0 1 5 0 ?1 r2 # \u0000 4 1 1 IdGlobal 0 2 DocID 0 3 SharingRule 0 4 UserParam # \u0000*/";

	public static String EP_USERS_SELECT_BY_USERID =
		"/*EP \u0001 0 2 2 0 # 1 1 1 2 r0 4 0 1 0 1 2 3 # 4 0 0 1 1 0 ?1 r2 # \u0000 4 1 1 IdGlobal 0 2 UserID 0 3 SharingRule 0 4 UserParam # \u0000*/";
	
	//test ep
	public static String EP_TEST_SELECT_USERDOC =
			"/*EP \u0004 " + 
		"0 7 7 0 # " + /* SCAN, Table Users (0) -->R0 */
		"1 6 6 7 r0 4 0 1 0 3 1 2 # " + /* TABLE_LOOKUP pos:R0, 4 cols, table Users(0), is_table: 1  -->R1 - R4 */
		"4 5 5 6 1 0 ?1 r3 # " + /* SELECT, att: UserID(1), comparator: 0, parameter: ?1 (R5), from pos: R3*/
		"4 4 4 5 2 0 ?2 r4 # " + /* SELECT, att: SharingRule(2), comparator: 0, parameter: ?2 (R6), from pos: R4*/
		"1 3 3 4 r0 1 -2 0 -2 # " + /* TABLE_LOOKUP pos:R0, 1 cols, skt (-2), is_table: 0  -->R7 - R7 */
		"1 2 2 3 r7 4 1 1 4 7 5 6 # " + /* TABLE_LOOKUP pos:R7, 4 cols, table Docs(1), is_table: 1  -->R8 - R11 */
		"4 1 1 2 5 0 ?3 r10 # " + /* SELECT, att: DocID(5), comparator: 0, parameter: ?3 (R12), from pos: R10*/
		"4 0 0 1 6 0 ?4 r11 # " + /* SELECT, att: SharingRule(6), comparator: 0, parameter: ?4 (R13), from pos: R11*/
		"\u0000 4 1 8 IdGlobal 0 9 UserParam 1 1 IdGlobal 0 2 UserParam # " + /* META_RESULT, 4 cols, type(0-char 1-num 2-date) [out_result name] */
		"\u0000*/";
	
	
	//manual QEP
	//params : uid, shareid, idglobal share (tmp)
	int cpt = 0;
	public static String EP_MATCH_DOC = 

			/* TODO : faire le même plan en inversant select doc et user avant le flow x */
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
	
	public static String EP_MATCH_DOC_INSERT = 

			
			
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
		
				/* TODO : faire le même plan en inversant select doc et user avant le flow x */
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
				"4 13 13 12 9 0 ?2 r14 # " + // SELECT IDGLOBAL SHARE --> r15
				
				"5 14 14 13 3 0 0 0 r13 1 r2 2 r8 # " + // TABLE_INSERT 3 cols, skt ACL(0), is_table: 0, [col_id value]  
				"5 0 0 14 6 3 1 12 r13 13 r3 14 r9 15 v0'rw' 16 v0'auth'11 v30 # " + // TABLE_INSERT 6 cols, table ACL(3), is_table: 1, [col_id value] 
				"\u0000*/";
	

		
		//TODO add idglobal share dans select u.idglobal, s.idglobal from users where u.shareid = x
}
