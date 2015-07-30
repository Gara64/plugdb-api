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
}
