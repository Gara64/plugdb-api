package org.cozy.plug;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.inria.database.QEPng;

import test.jdbc.Tools;

public class Queries extends Tools
{
	java.sql.PreparedStatement ps;
	int res, perf;
	Connection dbase;
	Statement stmt;
	
	
	public Queries(int plugState, PrintWriter out, java.sql.PreparedStatement ps, Connection dbase, int perf)
	{
		this.out = out;
		this.ps = ps;
		this.dbase = dbase;
		this.perf = perf;
		
		
		Class<?>[] execPlans = new Class[] {EP_ACL.class};
		try
		{
			QEPng.loadExecutionPlans(COZY_QEP_IDs.class, execPlans);
			if( plugState == Constants.PLUG_NOT_INITIALIZED )
				QEPng.installExecutionPlans(dbase); //do this only on install
			stmt = dbase.createStatement();
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet querySelect(int idQuery, String... param) throws Exception
	{
		ResultSet rs = null;
		String query = "", p1="", p2=""; //p3="",p4="", 
	
		if(param!=null){
			p1 = param.length > 0 ? param[0] : "";
		    p2 = param.length > 1 ? param[1] : "";
		    /*
		    p3 = param.length > 2 ? param[2] : "";
		    p4 = param.length > 3 ? param[3] : "";
			*/
		}
		
		try{
		
			switch(idQuery)
			{
				 case Constants.SELECT_STAR_ACL:
					 query = "SELECT * FROM ACL;";
					rs = ((org.inria.jdbc.Statement)stmt).executeQuery(COZY_QEP_IDs.EP_ACL.EP_ACL_SELECT_STAR);
					break; 
				 case Constants.SELECT_STAR_USERS:
					 query = "SELECT * FROM Users;";
					 rs = ((org.inria.jdbc.Statement)stmt).executeQuery(COZY_QEP_IDs.EP_ACL.EP_USERS_SELECT_STAR);
					break; 
				 case Constants.SELECT_STAR_DOCS:
					 query = "SELECT * FROM Docs;";
					 rs = ((org.inria.jdbc.Statement)stmt).executeQuery(COZY_QEP_IDs.EP_ACL.EP_DOCS_SELECT_STAR);
					break; 
				 case Constants.SELECT_STAR_SHARES:
					 query = "SELECT * FROM Shares;";
					 rs = ((org.inria.jdbc.Statement)stmt).executeQuery(COZY_QEP_IDs.EP_ACL.EP_SHARES_SELECT_STAR);
					break; 
					
				 case Constants.SELECT_DOCID_BY_SHARINGRULE:
					query = "SELECT IdGlobal, DocID, UserParam FROM Docs WHERE SharingRule = ?";
					ps = ((org.inria.jdbc.Connection)dbase).prepareStatement(COZY_QEP_IDs.EP_ACL.EP_DOCS_SELECT_DOCID_BY_SHARINGRULE);
					rs = Tools_dmsp.Test_SELECT_BY_STRING(p1, ps);
					break;
				 case Constants.SELECT_USERID_BY_SHARINGRULE:
						query = "SELECT IdGlobal, UserID, UserParam FROM Users WHERE SharingRule = ?";
						ps = ((org.inria.jdbc.Connection)dbase).prepareStatement(COZY_QEP_IDs.EP_ACL.EP_USERS_SELECT_USERID_BY_SHARINGRULE);
						rs = Tools_dmsp.Test_SELECT_BY_STRING(p1, ps);
						break;
				 case Constants.SELECT_USERPARAMS_BY_DOCID:
						query = "SSELECT IdGlobal, UserParam FROM Docs WHERE DocID = ? AND SharingRule = ?";
						ps = ((org.inria.jdbc.Connection)dbase).prepareStatement(COZY_QEP_IDs.EP_ACL.EP_DOCS_SELECT_USERPARAMS_BY_DOCID);
						rs = Tools_dmsp.Test_SELECT_BY_STRING_AND_STRING(p1, p2, ps);
						break;
				 case Constants.SELECT_USERPARAMS_BY_USERID:
						query = "SELECT IdGlobal, UserParam FROM Users WHERE UserID = ? AND SharingRule = ?";
						ps = ((org.inria.jdbc.Connection)dbase).prepareStatement(COZY_QEP_IDs.EP_ACL.EP_USERS_SELECT_USERPARAMS_BY_USERID);
						rs = Tools_dmsp.Test_SELECT_BY_STRING_AND_STRING(p1, p2, ps);
						break;
				 case Constants.SELECT_ACL_BY_SHAREID:
						query = "SELECT d.DocID, u.UserID FROM ACL a, Docs d, Users u, Shares s WHERE s.ShareID = ? AND s.IdGlobal = a.Share and a.User = u.IdGlobal AND a.Doc = d.IdGlobal";
						ps = ((org.inria.jdbc.Connection)dbase).prepareStatement(COZY_QEP_IDs.EP_ACL.EP_ACL_SELECT_BY_SHAREID);
						rs = Tools_dmsp.Test_SELECT_BY_STRING(p1, ps);
						break;
						
				 case Constants.SELECT_STAR_ACL_BY_USER:
						query = "SELECT * FROM ACL r WHERE r.User = ? ";
						ps = ((org.inria.jdbc.Connection)dbase).prepareStatement(COZY_QEP_IDs.EP_ACL.EP_ACL_SELECT_STAR_BY_USER);
						rs = Tools_dmsp.Test_SELECT_BY_INT(Integer.parseInt(p1), ps);
						
				 case Constants.SELECT_ACL_CREDS_BY_USERID:
					 query = "SELECT d.DocID, a.right, a.auth FROM ACL a, Docs d, Users u WHERE u.UserID = ? AND u.IdGlobal = a.User AND a.Doc = d.IdGlobal";
					 ps = ((org.inria.jdbc.Connection)dbase).prepareStatement(COZY_QEP_IDs.EP_ACL.EP_ACL_SELECT_CREDS_BY_USERID);
					 rs = Tools_dmsp.Test_SELECT_BY_STRING(p1, ps);
					 break;
					 
				 case Constants.SELECT_DOCID_BY_USERID:
					 
					 query = "SELECT d.DocID FROM ACL a, Docs d, Users u WHERE u.UserID = ? AND u.IdGlobal = a.User AND a.Doc = d.IdGlobal";
					 ps = ((org.inria.jdbc.Connection)dbase).prepareStatement(COZY_QEP_IDs.EP_ACL.EP_ACL_SELECT_DOCID_BY_USERID);
					 rs = Tools_dmsp.Test_SELECT_BY_STRING(p1, ps);
					 break;
					 
				 case Constants.SELECT_DOCS_BY_DOCID:
					 query = "SELECT * FROM DOCS WHERE DocID = ?";
					 ps = ((org.inria.jdbc.Connection)dbase).prepareStatement(COZY_QEP_IDs.EP_ACL.EP_DOCS_SELECT_BY_DOCID);
					 rs = Tools_dmsp.Test_SELECT_BY_STRING(p1, ps);
					 break;
					 
				 case Constants.SELECT_USERS_BY_USERID:
					 query = "SELECT * FROM USERS WHERE UserID = ?";
					 ps = ((org.inria.jdbc.Connection)dbase).prepareStatement(COZY_QEP_IDs.EP_ACL.EP_USERS_SELECT_BY_USERID);
					 rs = Tools_dmsp.Test_SELECT_BY_STRING(p1, ps);
					 break;
					 
			}
		}catch(Exception e)
		{
			System.out.println("Query error : " + query);
			e.printStackTrace();
		}

		return rs;
	}
	
	public void queryInsert(int idInsert, String...param) throws Exception
	{
		String p1 = param.length > 0 ? param[0] : "";
	    String p2 = param.length > 1 ? param[1] : "";
	    String p3 = param.length > 2 ? param[2] : "";
	    String p4 = param.length > 3 ? param[3] : "";
	    String p5 = param.length > 4 ? param[4] : "";
	    String query = "";

	    try
	    {
		    switch(idInsert)
			{
				case Constants.INSERT_ACL:
					query = "INSERT INTO ACL (Share, User, Doc, Right, Auth) VALUES (?,?,?,?,?)";
					ps = ((org.inria.jdbc.Connection)dbase).prepareStatement(COZY_QEP_IDs.EP_ACL.EP_ACL_INSERT);
					Tools_dmsp.INSERT_ACL(Integer.parseInt(p1),Integer.parseInt(p2), Integer.parseInt(p3)
							,p4,p5, ps);
					break;
				case Constants.INSERT_USER:
					query = "INSERT INTO Users (UserID, SharingRule, UserParam) VALUES(?,?,?)";
					ps = ((org.inria.jdbc.Connection)dbase).prepareStatement(COZY_QEP_IDs.EP_ACL.EP_USERS_INSERT);
					Tools_dmsp.INSERT_USER(p1, p2, p3, ps);
					break;
				case Constants.INSERT_DOC:
					query = "INSERT INTO Docs (DocID, SharingRule, UserParam) VALUES(?,?,?)";
					ps = ((org.inria.jdbc.Connection)dbase).prepareStatement(COZY_QEP_IDs.EP_ACL.EP_DOCS_INSERT);
					Tools_dmsp.INSERT_DOC(p1, p2, p3, ps);
					break;
				case Constants.INSERT_SHARE:
					query = "INSERT INTO Shares (ShareID, Desc) VALUES(?,?)";
					ps = ((org.inria.jdbc.Connection)dbase).prepareStatement(COZY_QEP_IDs.EP_ACL.EP_SHARES_INSERT);
					Tools_dmsp.INSERT_SHARE(p1, p2, ps);
					break;
				default:
					break;
			}
		    
		//in case there is a problem with the params
	    }catch(Exception e)
	    {
	    	System.out.println("Query error : " + query);
	    	e.printStackTrace();
	    	return;
	    }
	}
	
	
	
	public String printResult(java.sql.ResultSet rs ) throws Exception
	{
		String res = "";
		String[] rowValues;
		String[] columnNames;
		
		if (rs == null) {
			return null;
		}
		//Get the Metadata of the ResultSet
		java.sql.ResultSetMetaData rsmd = rs.getMetaData();
		int row_i = 1;

		if(perf==0){
			res += "Row\t";
		}

		columnNames = new String[rsmd.getColumnCount()];
		
		//display the names of each column
		if(perf==0){
			for (int i = 0 ;i < rsmd.getColumnCount() ;i++) {
				res += rsmd.getColumnName(i + 1) + "\t";
				columnNames[i] = new String(rsmd.getColumnName(i + 1));
			}
		}
		//UI.buildColumnNames(columnNames);
		
		if(perf==0){
			res += "\n";
		}
		

		//work on each line of the ResultSet
		// so call next to have the next line of the ResultSet
		while (rs.next()) {
			//res = "";
			if(perf==0){
				res += row_i;
			}
			
			//reset rowValues
			rowValues = new String[rsmd.getColumnCount()];
			
			for (int i = 0 ;i < rsmd.getColumnCount() ;i++) 
			{
				//System.out.println("rs : " + rs.getString(i+1)+ "column type : " + rsmd.getColumnType(i+1));
				switch (rsmd.getColumnType(i + 1)) {

				case java.sql.Types.VARCHAR :
				case java.sql.Types.CHAR :
					String s = rs.getString(i + 1);
					rowValues[i] = s;
					if(perf==0){
						if (rs.wasNull()) {
							res += "\tNULL";
						} else {
							res += "\t\t\t" + s;
						}
					}
					break;

				case java.sql.Types.DATE :
					java.sql.Date d = rs.getDate(i + 1);
					rowValues[i] = String.valueOf(d);
					if(perf==0){
						if (rs.wasNull()) {
							res += "\tNULL";
						} else {
							res += "\t\t\t" + d;
						}
					}
					break;

				case java.sql.Types.INTEGER :
					int k = rs.getInt(i + 1);
					if(perf==0){
						if (rs.wasNull()) {
							res += "\tNULL";
						} else {
								
								res += "\t\t\t" + k;
								rowValues[i] = String.valueOf(k);
						}
					}
					
					break;

				case java.sql.Types.BINARY :
					byte[] b = rs.getBytes(i + 1);
					if(perf==0){
						if (rs.wasNull()) {
							res += "\tNULL";
						} else {
							res += "\t0x";
							//out.print("\t");
							for (int j = 0;j < b.length; j++) {
								res += byteTo02x(b[j]);
								//out.print(b[j]);
							}
						}
					}
					break;


				default :
					throw new SQLException("ERROR : Type unknown in MetaData : column = "
							+ i + 1 + ", type = " + rsmd.getColumnType(i + 1) + ".");
				}
			}
			row_i++;
			//UI.addTableRow(rowValues);
			
			if(perf==0){
				res += "\n";
				//out.flush();
			}
			//out.println(res);
		}
		if(perf==0){
			//out.println("reach EndOfFile\n");
			//out.flush();
		}
		
		//UI.resizeTable();
		//UI.tableOutput.redraw();
		 
		

		
		return res;
	}
	
	

	
	
}
