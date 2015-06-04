package org.cozy.plug;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;


import test.jdbc.Tools;

public class Queries extends Tools
{
	java.sql.PreparedStatement ps;
	int res, perf;
	Connection dbase;
	
	
	public Queries(PrintWriter out, java.sql.PreparedStatement ps, Connection dbase, int perf)
	{
		this.out = out;
		this.ps = ps;
		this.dbase = dbase;
		this.perf = perf;
	}
	
	public ResultSet querySelect(int idQuery, String... param) throws Exception
	{
		ResultSet rs = null;
		String query = "", p1="",p2="",p3="",p4="";
	
		if(param!=null){
			p1 = param.length > 0 ? param[0] : "";
		    p2 = param.length > 1 ? param[1] : "";
		    p3 = param.length > 2 ? param[2] : "";
		    p4 = param.length > 3 ? param[3] : "";
		}
		
		try{
		
			switch(idQuery)
			{
						
				 case Constants.SELECT_STAR_BY_USERRULE:
					query = "SELECT * FROM Rules r WHERE r.UserID = ? ";
					ps = dbase.prepareStatement(QEPCozy.EP_SELECT_STAR_BY_USERRULE);
					rs = Tools_dmsp.Test_SELECT_BY_INT(Integer.parseInt(p1), ps);
					break;
					
				 case Constants.SELECT_STAR_RULES:
					 query = "SELECT * FROM Rules;";
					ps = dbase.prepareStatement(QEPCozy.EP_SELECT_STAR_RULES);
					rs = Tools_dmsp.Test_SELECT( ps);
					break; 
				 case Constants.SELECT_STAR_USERS:
					 query = "SELECT * FROM Users;";
					ps = dbase.prepareStatement(QEPCozy.EP_SELECT_STAR_USERS);
					rs = Tools_dmsp.Test_SELECT( ps);
					break; 
				 case Constants.SELECT_STAR_DOCS:
					 query = "SELECT * FROM Docs;";
					ps = dbase.prepareStatement(QEPCozy.EP_SELECT_STAR_DOCS);
					rs = Tools_dmsp.Test_SELECT( ps);
					break; 
					
				 case Constants.SELECT_RULES_BY_USERID:
					 query = "SELECT d.DocID, r.right, r.auth FROM RULES r, Docs d, Users u "
					 		+ "WHERE u.UserID = ? AND u.IdGlobal = r.UserID AND r.DocID = d.IdGlobal";
					 ps = dbase.prepareStatement(QEPCozy.EP_RULES_BY_USERID);
					 rs = Tools_dmsp.Test_SELECT_BY_STRING(p1, ps);
					 break;
					 
				 case Constants.SELECT_DOCID_BY_USERID:
					 
					 query = "SELECT d.DocID FROM RULES r, Docs d, Users u "
					 		+ "WHERE u.UserID = ? AND u.IdGlobal = r.UserID AND r.DocID = d.IdGlobal";
					 ps = dbase.prepareStatement(QEPCozy.EP_DOCID_BY_USERID);
					 rs = Tools_dmsp.Test_SELECT_BY_STRING(p1, ps);
					 break;
			}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Query error");
			e.printStackTrace();
		}

		return rs;
	}
	
	public void queryInsert(int idInsert, String...param) throws Exception
	{
		boolean insert_ok = true;
		
		String p1 = param.length > 0 ? param[0] : "";
	    String p2 = param.length > 1 ? param[1] : "";
	    String p3 = param.length > 2 ? param[2] : "";
	    String p4 = param.length > 3 ? param[3] : "";
	    String query = "";

	    try
	    {
		    switch(idInsert)
			{
				case Constants.INSERT_RULE:
					ps = dbase.prepareStatement(QEPCozy.EP_RULE_INSERT);
					Tools_dmsp.INSERT_RULE(Integer.parseInt(p1),Integer.parseInt(p2)
							,p3,p4, ps);
					break;
				case Constants.INSERT_USERID:
					ps = dbase.prepareStatement(QEPCozy.EP_USER_INSERT);
					Tools_dmsp.INSERT_USER(p1, ps);
					break;
				case Constants.INSERT_DOCID:
					ps = dbase.prepareStatement(QEPCozy.EP_DOC_INSERT);
					Tools_dmsp.INSERT_DOC(p1, ps);
					break;
				default:
					break;
			}
		    
		//in case there is a problem with the params
	    }catch(Exception e)
	    {
	    	JOptionPane.showMessageDialog(null, "incorrect query format");
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
