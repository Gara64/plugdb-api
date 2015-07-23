package org.cozy.plug;

public class EP_ACL {
	public static String EP_RULES_INSERT =
			"/*EP \u0004 2 4 4 -1 0 ?1 # 2 5 5 -1 1 ?2 # 7 3 3 4 5 # 6 2 2 3 2 v30 # 5 1 1 2 2 0 0 0 r1 1 r3 # 5 0 0 1 5 2 1 7 ?1 8 ?2 9 ?3 10 ?4 6 r4 # \u0000*/";

		public static String EP_USERS_INSERT =
			"/*EP \u0002 6 1 1 -1 0 v30 # 5 0 0 1 3 0 1 1 ?1 2 ?2 0 r0 # \u0000*/";

		public static String EP_DOCS_INSERT =
			"/*EP \u0002 6 1 1 -1 1 v30 # 5 0 0 1 3 1 1 4 ?1 5 ?2 3 r0 # \u0000*/";

		public static String EP_RULES_SELECT_STAR =
			"/*EP \u0000 0 1 1 2 # 1 0 0 1 r0 5 2 1 6 7 8 9 10 # \u0000 5 1 1 IdGlobal 1 2 UserID 1 3 DocID 0 4 Right 0 5 Auth # \u0000*/";

		public static String EP_USERS_SELECT_STAR =
			"/*EP \u0000 0 1 1 0 # 1 0 0 1 r0 3 0 1 0 1 2 # \u0000 3 1 1 IdGlobal 0 2 UserID 0 3 UserDesc # \u0000*/";

		public static String EP_DOCS_SELECT_STAR =
			"/*EP \u0000 0 1 1 1 # 1 0 0 1 r0 3 1 1 3 4 5 # \u0000 3 1 1 IdGlobal 0 2 DocID 0 3 UserDesc # \u0000*/";

		public static String EP_RULES_SELECT_STAR_BY_USERID =
			"/*EP \u0001 0 2 2 2 # 1 1 1 2 r0 5 2 1 6 7 8 9 10 # 4 0 0 1 7 0 ?1 r2 # \u0000 5 1 1 IdGlobal 1 2 UserID 1 3 DocID 0 4 Right 0 5 Auth # \u0000*/";

		public static String EP_RULES_SELECT_DOCID_BY_USERID =
			"/*EP \u0001 0 4 4 2 # 1 3 3 4 r0 2 0 0 1 0 # 1 2 2 3 r2 1 0 1 1 # 4 1 1 2 1 0 ?1 r3 # 1 0 0 1 r1 1 1 1 4 # \u0000 1 0 5 DocID # \u0000*/";

		public static String EP_RULES_SELECT_CREDS_BY_USERID =
			"/*EP \u0001 0 5 5 2 # 1 4 4 5 r0 2 0 0 1 0 # 1 3 3 4 r2 1 0 1 1 # 4 2 2 3 1 0 ?1 r3 # 1 1 1 2 r1 1 1 1 4 # 1 0 0 1 r0 2 2 1 9 10 # \u0000 3 0 5 DocID 0 6 right 0 7 auth # \u0000*/";

		public static String EP_DOCS_SELECT_BY_DOCID =
				"/*EP \u0001 0 2 2 1 # 1 1 1 2 r0 3 1 1 3 4 5 # 4 0 0 1 4 0 ?1 r2 # \u0000 3 1 1 IdGlobal 0 2 DocID 0 3 UserDesc # \u0000*/";

		public static String EP_USERS_SELECT_BY_USERID =
				"/*EP \u0001 0 2 2 0 # 1 1 1 2 r0 3 0 1 0 1 2 # 4 0 0 1 1 0 ?1 r2 # \u0000 3 1 1 IdGlobal 0 2 UserID 0 3 UserDesc # \u0000*/";

			public static String EP_FAKE_SELECT =
				"/*EP \u0000 0 1 1 0 # 1 0 0 1 r0 3 0 1 0 1 2 # \u0000 3 1 1 IdGlobal 0 2 UserID 0 3 UserDesc # \u0000*/";

}
