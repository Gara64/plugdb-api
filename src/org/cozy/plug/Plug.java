package org.cozy.plug;


import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import test.jdbc.Tools;

//import test.runnerTCP.ITest;
import test.runner.ITest;

//import org.inria.jdbc.DBMS;
import test.jdbc.Tools;
//import test.jdbc.schemaIndexInfo.Tools_schemaIndexInfo;


public class Plug extends Tools implements ITest
{
	Queries q;
	PreparedStatement ps;
	Tools t;
	PrintWriter output;
	//Tools_schemaIndexInfo sii;
	
	public Plug()
	{
		t =  new Tools();
		output = new PrintWriter(java.lang.System.out);
		
	}
	
	public void plugInit(String dbmsHost) throws Exception
	{	
		run(output, dbmsHost);
	}
	
	/* Insert doc ids and sharing rules */ 
	public void plugInsertDocs(String[] docIds)
	{
		try 
		{
			// Insert the generated docs ids in plugdb 
			for(int i=0; i<docIds.length; i++)
				q.queryInsert(Constants.INSERT_DOC, docIds[i], "", "");
			
			Save_DBMS_on_disk();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* Insert in Docs table  */ 
	public void plugInsertDoc(String docId, String sharingRule, String[] userParams) throws Exception
	{
		
		if (userParams != null) {
			for(int i=0; i<userParams.length; i++)
				q.queryInsert(Constants.INSERT_DOC, docId, sharingRule, userParams[i]);
		}
		else
			q.queryInsert(Constants.INSERT_DOC, docId, sharingRule, "null"); //null (or any value) is needed; empty value are not compared
		Save_DBMS_on_disk();
	}
	
	/* Insert in Users table  */ 
	public void plugInsertUser(String userID, String sharingRule, String[] userParams) throws Exception
	{
		
		if (userParams != null) {
			for(int i=0; i<userParams.length; i++)
				q.queryInsert(Constants.INSERT_USER, userID, sharingRule, userParams[i]);
		}
		else {
			q.queryInsert(Constants.INSERT_USER, userID, sharingRule, "null"); //null (or any value) is needed; empty value are not compared
			System.out.println("inserted " + userID + " - " + sharingRule);
		}
		Save_DBMS_on_disk();
	}
	
	/* Insert in Shares table  */ 
	public void plugInsertShare(String shareID, String desc) throws Exception
	{
		q.queryInsert(Constants.INSERT_SHARE, shareID, desc);
		Save_DBMS_on_disk();
	}
	
	
	/* Select all the doc ids */
	public String[] plugSelectDocs()
	{
		ResultSet rs;
		ArrayList<ArrayList<String>> tuples = new ArrayList<ArrayList<String>>();
		String[] docIds = null;

		try 
		{
			//For the moment, just select star on the docs to get the id
			rs = q.querySelect(Constants.SELECT_STAR_DOCS);
			tuples = Util.getTuples(rs);
			
			docIds = new String[tuples.size()];
			for(int i=0;i<tuples.size();i++){
				docIds[i] = tuples.get(i).get(1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return docIds;
	}
	
	/* Select 1 Doc */
	public String[] plugSelectSingleDoc(String docID)
	{
		ResultSet rs;
		ArrayList<ArrayList<String>> tuples = new ArrayList<ArrayList<String>>();
		try 
		{
			//For the moment, just select star on the docs to get the id
			rs = q.querySelect(Constants.SELECT_DOCS_BY_DOCID, docID);
			tuples = Util.getTuples(rs);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(tuples == null || tuples.isEmpty())
			return null;
		return Util.convertArrayListIntoString(tuples.get(0));
	}
	
	/* Select 1 User */
	public String[] plugSelectSingleUser(String userID)
	{
		ResultSet rs;
		ArrayList<ArrayList<String>> tuples = new ArrayList<ArrayList<String>>();
		try 
		{
			//For the moment, just select star on the docs to get the id
			rs = q.querySelect(Constants.SELECT_USERS_BY_USERID, userID);
			tuples = Util.getTuples(rs);
			System.out.println("size : " + tuples.size());
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(tuples == null || tuples.isEmpty())
			return null;
		return Util.convertArrayListIntoString(tuples.get(0));
	}
	
	/* Select all the users */
	public String[] plugSelectUsers()
	{
		ResultSet rs;
		ArrayList<ArrayList<String>> tuples = new ArrayList<ArrayList<String>>();
		String[] userIds = null;

		try 
		{
			//For the moment, just select star on the docs to get the id
			rs = q.querySelect(Constants.SELECT_STAR_USERS);
			tuples = Util.getTuples(rs);
			
			userIds = new String[tuples.size()];
			for(int i=0;i<tuples.size();i++){
				userIds[i] = tuples.get(i).get(1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return userIds;
	}
	
	public String[][] plugSelectACL(String shareID)
	{
		ResultSet rs;
		ArrayList<ArrayList<String>> tuples = new ArrayList<ArrayList<String>>();
		String[][] acl = null;

		try 
		{
			//select userid, docid drom acl, doc, user
			rs = q.querySelect(Constants.SELECT_ACL_BY_SHAREID, shareID); 
			tuples = Util.getTuples(rs);
			
			acl = new String[tuples.size()][2];
			for(int i=0;i<tuples.size();i++){
				acl[i][0] = tuples.get(i).get(0);
				acl[i][1] = tuples.get(i).get(1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return acl;
	}
	
	/* Match a table for a specified doc/user id + shareid.
	 * Returns all the [userids, docids] for this share in acl.
	 * Note that a version that calls INSERT_SELECT_MATCH_... should be implemented
	 * to returns only the inserted [userids, docids].
	 */
	public String[][] plugMatchAll(int matchingType, String id, String shareID) throws Exception
	{
		String acl[][] = null;
		int res = 0;
		
		/* Version lost with computer...
		 * int ret = ((DBMS) db).Match( matchingType, shareID, matchID );
		if( ret <= 0)
			return null;
		else {
			Save_DBMS_on_disk();
			acl = plugSelectACL(shareID);
			return acl;
		}*/
		
		if(matchingType == Constants.MATCH_USERS) 
			res = q.queryInsert(Constants.INSERT_MATCH_USERS, id, shareID);
			
		else if(matchingType == Constants.MATCH_DOCS)
			res = q.queryInsert(Constants.INSERT_MATCH_DOCS, id, shareID);

		else
			return null;
		
		System.out.println("inserted " + res + " acl" );
		
		// If the match has inserted acl, select all the userid, docid for this share and commit
		if ( res > 0 ) {
			Save_DBMS_on_disk();
			acl = plugSelectACL(shareID);
			for(int i=0;i<acl.length;i++)
				System.out.println("userid : " + acl[i][0] + " , docid : " + acl[i][1]);
		}
		
		return acl;
	}
	
	/* Match a table for a specified doc/user id + shareid
	 * Returns only the inserted [userids, docids] in ACL
	 * Returns null if nothing has been inserted
	 */
	public String[][] plugMatch(int matchingType, String id, String shareID) throws Exception
	{
		String acl[][] = null;
		ResultSet rs;
		
		if(matchingType == Constants.MATCH_USERS) 
			rs = q.querySelect(Constants.INSERT_SELECT_MATCH_USERS, id, shareID);
			
		else if(matchingType == Constants.MATCH_DOCS)
			rs = q.querySelect(Constants.INSERT_SELECT_MATCH_DOCS, id, shareID);
		else
			return null;
		
		ArrayList<ArrayList<String>> tuples = Util.getTuples(rs);
		
		// If the match has inserted acl, select the inserted userid, docid and commit
		if (tuples.size() > 0 ) {
			Save_DBMS_on_disk();
			acl = Util.convertDoubleArrayListIntoString(tuples);
		}
		
		return acl;
	}
	
	public void plugClose()
	{
	    try 
	    {
			Save_DBMS_on_disk();
			Shutdown_DBMS();
		
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void plugReset()
	{
		try {
			Desinstall_DBMS_MetaData();
			Install_DBMS_MetaData(SchemaCozy.META.getBytes(), 0);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* COMMENT BECAUSE OF JDBC */
	/*public int plugFPAuthentication()
	{
		int authId = -1;
		FingerPrint fp = new FingerPrint(this);
		fp.unauthenticate_fp();
		try {
			Thread.sleep( 200 );
			fp.desactivate_fp();
			Thread.sleep( 200 );
			fp.activate_fp();
			Thread.sleep( 200 );
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		authId = fp.auth_fp();
		if(authId == Constants.FULL_ACCESS_ID){
			Globals.FULL_ACCESS = true;
			System.out.println("You have been granted full access");
		}
		else if(authId == Constants.RESTRICTED_ACCESS_ID){
			Globals.FULL_ACCESS = false;
			System.out.println("You have been granted restricted access");
		}
		else
			System.out.println("Authentication timed out, please try again");
		
		return authId;
	}*/
	
	
	
	@Override
	public void run(PrintWriter out, String dbmsHost) throws Exception {
		// TODO Auto-generated method stub
		this.out = out;
		
		init();
		openConnection(dbmsHost, null);
		
		int plugState = Util.checksPlugState((org.inria.jdbc.Connection)db);
		System.out.println("plug state : " + plugState);
		
		if(plugState == Constants.PLUG_NOT_INITIALIZED){
			plugReset(); //also desinstalls metadata, in case the state is not reliable
			Util.makesPlugStateInit((org.inria.jdbc.Connection)db);
		}
		else if(plugState == Constants.PLUG_INITIALIZED)
		{
				//((DBMS) db).bypassInitialization();
				mStorage.bypassInitialization();
		}
		else
		{
			System.err.println("Timestamp error. Exit.");
			System.exit(1);
		}
		
		q = new Queries(plugState, out, ps, db, perf);
		
		//select_stars();
		//test();
		//testMatch();

	}
	

	public void test() throws Exception {

		plugInsertUser("user1", "share1", null);
		plugInsertUser("user2", "share2", null);
		for(int i=0;i<10;i++)
			plugInsertDoc("doc"+i, "share1", null);
		plugInsertDoc("doc2", "share2", null);
		
		plugInsertShare("share1", "blah");
		plugInsertShare("share2", "blah");

		lireResultSet(q.querySelect(Constants.SELECT_STAR_SHARES), out);
		//q.queryInsert(Constants.INSERT_ACL, "15", "2", "5", "bl", "bloh");
		//lireResultSet(q.querySelect(Constants.SELECT_ACL_BY_SHAREID, "share1"), out);
		//lireResultSet(q.querySelect(Constants.SELECT_STAR_ACL), out);
		
		/*lireResultSet(q.querySelect(Constants.SELECT_STAR_USERS), out);
		lireResultSet(q.querySelect(Constants.SELECT_STAR_DOCS), out);
		lireResultSet(q.querySelect(Constants.SELECT_STAR_SHARES), out);
		*/
		
		
		//q.queryInsert(Constants.INSERT_ACL, "15", "2", "5", "bl", "bloh");
		//lireResultSet(q.querySelect(Constants.SELECT_STAR_SKT_ACL), out);
		
		/*String[][] acl = plugMatch(Constants.MATCH_USERS, "doc1", "share1");
		for(int i=0;i<acl.length;i++)
		{
			System.out.println("userid : " + acl[i][0] + " , docid : " + acl[i][1]);
		}*/
	
		
		
		// SELECT :
		//lireResultSet( q.querySelect(Constants.MATCH_DOC, "user1", "share1"), out);
				
		// INSERT AS SELECT :
		lireResultSet( q.querySelect(Constants.INSERT_SELECT_MATCH_DOCS, "user1", "share1"), out);
		//lireResultSet( q.querySelect(Constants.INSERT_SELECT_MATCH_USERS, "doc1", "share1"), out);
	//	System.out.println("n acl inserted : " + res);
	 
		
		//int ret = Match("sharetest", "idtest");
		//System.out.println("match ret : " + ret);
		
		//Save_DBMS_on_disk();
		Shutdown_DBMS();
	}
	
	public void testMatch() throws Exception {
		
		//shareid f129788c94c18b3148adb24122025e4
		//userid c056eb163853e0e4544224261100d568
		//docid c056eb163853e0e4544224261100eb01
	
		String[][] acl = plugMatch(Constants.MATCH_USERS, "f129788c94c18b3148adb24122025e43", "f61e75131ee0df2aa74754e52e00014a");
		if(acl != null) {
			for(int i=0;i<acl.length;i++) {
				System.out.println("doc id : " + acl[i][0]);
				System.out.println("user id : " + acl[i][1]);
	
			}
		}
		
		lireResultSet(q.querySelect(Constants.SELECT_STAR_ACL ), out);
		lireResultSet(q.querySelect(Constants.SELECT_STAR_SHARES ), out);
		lireResultSet(q.querySelect(Constants.SELECT_STAR_USERS ), out);
		lireResultSet(q.querySelect(Constants.SELECT_STAR_DOCS ), out);
		
		/*plugInsertShare("f129788c94c18b3148adb24122025e4", "blah");
		
		for(int i=0;i<1;i++) {
			plugInsertUser("c056eb163853e0e4544224261100d568", "f129788c94c18b3148adb24122025e4", new String[]{});
			plugInsertUser("c056eb163853e0e4544224261100d569", "f129788c94c18b3148adb24122025e4", new String[]{"tata"});
		}
	
		plugInsertDoc("c056eb163853e0e4544224261100eb01", "f129788c94c18b3148adb24122025e4", new String[]{});
		
		
		int ret = Match(Constants.MATCH_USERS, "sharingid1", "doctest");
		System.out.println("match ret : " + ret);
		
		
		String[][] acl = plugMatch(Constants.MATCH_USERS, "f129788c94c18b3148adb24122025e4", "c056eb163853e0e4544224261100eb01");
		if(acl != null) {
			for(int i=0;i<acl.length;i++) {
				System.out.println("doc id : " + acl[i][0]);
				System.out.println("user id : " + acl[i][1]);
	
			}
		}*/

	/*
		lireResultSet(q.querySelect(Constants.SELECT_ACL_BY_SHAREID, "sharingid1"), out);
		lireResultSet(q.querySelect(Constants.SELECT_ACL_BY_SHAREID, "sharingid1"), out);
		lireResultSet(q.querySelect(Constants.SELECT_STAR_ACL ), out);
		lireResultSet(q.querySelect(Constants.SELECT_STAR_SHARES ), out);
		lireResultSet(q.querySelect(Constants.SELECT_STAR_USERS ), out);
		lireResultSet(q.querySelect(Constants.SELECT_STAR_DOCS ), out);
	*/
		
		//Save_DBMS_on_disk();
		//Shutdown_DBMS();
	}
	
	public void select_stars() throws Exception {
		lireResultSet(q.querySelect(Constants.SELECT_STAR_SHARES ), out);
		lireResultSet(q.querySelect(Constants.SELECT_STAR_USERS ), out);
		lireResultSet(q.querySelect(Constants.SELECT_STAR_DOCS ), out);
		lireResultSet(q.querySelect(Constants.SELECT_STAR_ACL ), out);
		
		Save_DBMS_on_disk();
		Shutdown_DBMS();
	}
}
