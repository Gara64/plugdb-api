package org.cozy.plug;

public class Constants 
{
	public final static int INSERT_ACL = 0;
	public final static int INSERT_USER = 1;
	public final static int INSERT_DOC = 2;
	public final static int INSERT_SHARE = 3;

	
	public final static int SELECT_STAR_ACL = 12;
	public final static int SELECT_STAR_USERS = 13;
	public final static int SELECT_STAR_DOCS = 14;
	public final static int SELECT_STAR_SHARES = 15;
	
	public final static int SELECT_DOCID_BY_SHARINGRULE = 16;
	public final static int SELECT_USERID_BY_SHARINGRULE = 17;
	public final static int SELECT_USERPARAMS_BY_DOCID = 18;
	public final static int SELECT_USERPARAMS_BY_USERID = 19;
	
	public final static int SELECT_ACL_BY_SHAREID = 11;
	
	public final static int SELECT_STAR_ACL_BY_USER = 20;
	public final static int SELECT_DOCID_BY_USERID = 21;
	public final static int SELECT_ACL_CREDS_BY_USERID = 22;
	public final static int SELECT_DOCS_BY_DOCID = 23;
	public final static int SELECT_USERS_BY_USERID = 24;
	
	public final static int PLUG_NOT_INITIALIZED = 50;
	public final static int PLUG_INITIALIZED = 51;
	public final static int PLUG_TIMESTAMP_ERROR = 52;
	
	public final static int MATCH_USERS = 0;
	public final static int MATCH_DOCS = 1;
	
	static public final int FULL_ACCESS_ID = 0;
	static public final int RESTRICTED_ACCESS_ID = 0;
	
	
	
	
}
