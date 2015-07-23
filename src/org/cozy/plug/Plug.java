package org.cozy.plug;


import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import test.jdbc.Tools;
import test.jdbc.schemaIndexInfo.Tools_schemaIndexInfo;
import test.runner.ITest;

public class Plug extends Tools implements ITest
{
	Queries q;
	PreparedStatement ps;
	Tools t;
	PrintWriter output;
	Tools_schemaIndexInfo sii;
	
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
				q.queryInsert(Constants.INSERT_DOC, docIds[i]);
			
			// Insert the rules
			for(int i=0; i<docIds.length; i++)
				q.queryInsert(Constants.INSERT_RULE, "1", String.valueOf((i+2)), "w", "none" );
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* Insert in Docs table  */ 
	public void plugInsertDoc(String docId, String[] userParams) throws Exception
	{
		if (userParams != null) {
			for(int i=0; i<userParams.length; i++)
				q.queryInsert(Constants.INSERT_DOC, docId, userParams[i]);
		}
		else
			q.queryInsert(Constants.INSERT_DOC, docId, "");
	}
	
	/* Insert in Users table  */ 
	public void plugInsertUser(String userID, String[] userParams) throws Exception
	{
		if (userParams != null) {
			for(int i=0; i<userParams.length; i++)
				q.queryInsert(Constants.INSERT_USER, userID, userParams[i]);
		}
		else
			q.queryInsert(Constants.INSERT_USER, userID, "");
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
			rs = q.querySelect(Constants.SELECT_DOCS_SELECT_BY_DOCID, docID);
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
			rs = q.querySelect(Constants.SELECT_USERS_SELECT_BY_USERID, userID);
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
	
	public int plugFPAuthentication()
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
	}
	
	
	
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
		
		q = new Queries(out, ps, db, perf);
		
		//test();
		

	}

	public void test() throws Exception {
		
		plugInsertUser("test1", null);
		plugInsertUser("test2", null);
		String[] str = plugSelectSingleUser("test1");
		if (str != null) {
		for(int i=0;i<str.length;i++)
			System.out.println("user : " + str[i]);
		}
		else
			System.out.println("nop");
		
		lireResultSet(q.querySelect(Constants.SELECT_STAR_USERS), out);
		lireResultSet(q.querySelect(Constants.SELECT_STAR_DOCS), out);
		
		/*String[] str = new String[1];
		str[0] = "test";
		plugInsert(str);
		String[] res = plugSelect();
		System.out.println("taille tableau : " + res.length);
		for(int i=0;i<res.length;i++)
			out.println("res : " + res[i]);
		//lireResultSet(q.querySelect(Constants.SELECT_STAR_DOCS, ""), out);
		*/
		
		int ret = Match("sharetest", "idtest");
		System.out.println("match ret : " + ret);
		
		Save_DBMS_on_disk();
		Shutdown_DBMS();
		

	}
}
