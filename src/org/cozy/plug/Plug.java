package org.cozy.plug;


import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import test.jdbc.Tools;
import test.runner.ITest;


//import org.inria.jdbc.DBMS;

/*import test.jdbc.Tools;
import test.jdbc.schemaIndexInfo.Tools_schemaIndexInfo;
import test.runnerTCP.ITest;
*/

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
			q.queryInsert(Constants.INSERT_DOC, docId, sharingRule, "");
		Save_DBMS_on_disk();
	}
	
	/* Insert in Users table  */ 
	public void plugInsertUser(String userID, String sharingRule, String[] userParams) throws Exception
	{
		if (userParams != null) {
			for(int i=0; i<userParams.length; i++)
				q.queryInsert(Constants.INSERT_USER, userID, sharingRule, userParams[i]);
		}
		else
			q.queryInsert(Constants.INSERT_USER, userID, sharingRule, "");
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
			//For the moment, just select star on the docs to get the id
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
	
	public String[][] plugMatch(int matchingType, String shareID, String matchID) throws Exception
	{
		String acl[][] = null;
		
		int ret = Match( matchingType, shareID, matchID );
		if( ret <= 0)
			return null;
		else {
			Save_DBMS_on_disk();
			acl = plugSelectACL(shareID);
			return acl;
		}
			
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
			mStorage.bypassInitialization();
		}
		else
		{
			System.err.println("Timestamp error. Exit.");
			System.exit(1);
		}
		
		q = new Queries(plugState, out, ps, db, perf);
		
		

	}

	public void test() throws Exception {
		
		plugInsertUser("test1", "1", null);
		plugInsertUser("test2", "2", null);
		plugInsertDoc("doc1", "1", null);
		plugInsertDoc("doc2", "2", null);
		
		lireResultSet(q.querySelect(Constants.SELECT_STAR_USERS), out);
		lireResultSet(q.querySelect(Constants.SELECT_STAR_DOCS), out);
				
		//int ret = Match("sharetest", "idtest");
		//System.out.println("match ret : " + ret);
		
		Save_DBMS_on_disk();
		Shutdown_DBMS();
	}
	
	public void testMatch() throws Exception {
		
		plugInsertShare("sharingid1", "blah");
		
		for(int i=0;i<10;i++) {
			plugInsertUser("test1", "sharingid1", new String[]{"toto"});
			plugInsertUser("test2", "sharingid1", new String[]{"tata"});
		}
		
		plugInsertDoc("doctest", "sharingid1", new String[]{"toto"});
		
		
		int ret = Match(Constants.MATCH_USERS, "sharingid1", "doctest");
		System.out.println("match ret : " + ret);
		
		
		String[][] acl = plugMatch(Constants.MATCH_USERS, "sharingid1", "doctest");
		if(acl != null) {
			for(int i=0;i<acl.length;i++) {
				System.out.println("doc id : " + acl[i][0]);
				System.out.println("user id : " + acl[i][1]);
	
			}
		}
		
		/*lireResultSet(q.querySelect(Constants.SELECT_ACL_BY_SHAREID, "sharingid1"), out);
		 * lireResultSet(q.querySelect(Constants.SELECT_STAR_ACL ), out);
		lireResultSet(q.querySelect(Constants.SELECT_STAR_SHARES ), out);
		lireResultSet(q.querySelect(Constants.SELECT_STAR_USERS ), out);
		lireResultSet(q.querySelect(Constants.SELECT_STAR_DOCS ), out);
	*/
		
		//Save_DBMS_on_disk();
		Shutdown_DBMS();
	}
}
