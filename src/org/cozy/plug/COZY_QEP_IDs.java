package org.cozy.plug;

public class COZY_QEP_IDs {
	
	public static class EP_QEP
	{
		public static final int EP_QEP_INSERT = 0;
	}
	
	public static class EP_ACL
	{
		static int cpt = 1;
		
		public static final int EP_ACL_INSERT = cpt++;
		public static final int EP_USERS_INSERT = cpt++;
		public static final int EP_DOCS_INSERT = cpt++;
		public static final int EP_SHARES_INSERT = cpt++;
		public static final int EP_ACL_SELECT_STAR = cpt++;
		public static final int EP_USERS_SELECT_STAR = cpt++;
		public static final int EP_DOCS_SELECT_STAR = cpt++;
		public static final int EP_SHARES_SELECT_STAR = cpt++;
		public static final int EP_DOCS_SELECT_DOCID_BY_SHARINGRULE = cpt++;
		public static final int EP_USERS_SELECT_USERID_BY_SHARINGRULE = cpt++;
		public static final int EP_DOCS_SELECT_USERPARAMS_BY_DOCID = cpt++;
		public static final int EP_USERS_SELECT_USERPARAMS_BY_USERID = cpt++;
		public static final int EP_ACL_SELECT_BY_SHAREID = cpt++;
		
		public static final int EP_ACL_SELECT_STAR_BY_USER = cpt++;
		public static final int EP_ACL_SELECT_DOCID_BY_USERID = cpt++;
		public static final int EP_ACL_SELECT_CREDS_BY_USERID  = cpt++;
		public static final int EP_DOCS_SELECT_BY_DOCID = cpt++;
		public static final int EP_USERS_SELECT_BY_USERID = cpt++;
		public static final int EP_MATCH_DOC = cpt++;
		public final static int EP_TEST = cpt++;
		public final static int EP_INSERT_MATCH_DOCS = cpt++;
		public final static int EP_INSERT_MATCH_USERS = cpt++;
		public final static int EP_INSERT_SELECT_MATCH_DOCS = cpt++;
		public final static int EP_INSERT_SELECT_MATCH_USERS = cpt++;
		public final static int EP_SELECT_STAR_SKT_ACL = cpt++;
		
		public final static int EP_DELETE_DOC = cpt++;
		public final static int EP_DELETE_USER = cpt++;
		public final static int EP_DELETE_SHARE = cpt++;
		
		public final static int EP_DELETE_MATCH_DOCS = cpt++;
		public final static int EP_DELETE_MATCH_USERS = cpt++;
	}

}
