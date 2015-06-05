package org.cozy.plug;


import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


import test.jdbc.Tools;
import test.runner.ITest;

public class Plug extends Tools implements ITest
{
	Queries q;
	PreparedStatement ps;
	Tools t;
	PrintWriter output;
	
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
	public void plugInsert(List<String> docIds)
	{
		try 
		{
			// Insert the generated docs ids in plugdb 
			for(int i=0; i<docIds.size(); i++)
				q.queryInsert(Constants.INSERT_DOCID, docIds.get(i));
			
			// Insert the rules
			for(int i=0; i<docIds.size(); i++)
				q.queryInsert(Constants.INSERT_RULE, "1", String.valueOf((i+2)), "w", "none" );
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/* Select all the doc ids */
	public String[] plugSelect()
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
			Install_DBMS_MetaData(QEPCozy.META.getBytes(), 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void plugFPAuthentication()
	{
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
		int authId = fp.auth_fp();
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
			plugReset(); //also desinstalls metadata, in case the state is reliable
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
		
	}

	
}
