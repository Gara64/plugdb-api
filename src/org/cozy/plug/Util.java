package org.cozy.plug;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




public class Util
{
	public static ArrayList<ArrayList<String>> getTuples(ResultSet rs) throws SQLException
	{
		ArrayList<ArrayList<String>> tuples = new ArrayList<ArrayList<String>>();
		ArrayList<String> tuple = new ArrayList<String>();

		if(rs != null)
		{
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) 
			{
				tuple = new ArrayList<String>(); 
				for(int i=0;i<rsmd.getColumnCount();i++)
				{
					if(rsmd.getColumnType(i+1) == java.sql.Types.INTEGER)
						tuple.add(String.valueOf(rs.getInt(i+1)));
					else if(rsmd.getColumnType(i+1) == java.sql.Types.CHAR)
						tuple.add(rs.getString(i+1));
					else if(rsmd.getColumnType(i+1) == java.sql.Types.DATE)
						tuple.add(String.valueOf(rs.getDate(i+1)));
				}
				tuples.add(tuple);
			}
		}
		return tuples;
	}
	


	public static ArrayList<String> getTuplesWithSingleAttribute(ResultSet rs) throws SQLException
	{
		ArrayList<String> tuple = new ArrayList<String>();

		if(rs != null)
		{
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			if( rsmd.getColumnCount() != 1){
				System.err.println("Column number incorrect");
				return null;
			}

			while (rs.next()) 
			{
				System.out.println("column type : " + rsmd.getColumnType(1));
				if(rsmd.getColumnType(1) == java.sql.Types.INTEGER)
					tuple.add(String.valueOf(rs.getInt(1)));
				else if(rsmd.getColumnType(1) == java.sql.Types.CHAR)
					tuple.add(rs.getString(1));
				else if(rsmd.getColumnType(1) == java.sql.Types.DATE)
					tuple.add(String.valueOf(rs.getDate(1)));
			}
		}
		return tuple;
	}
	
	public static int checksPlugState(org.inria.jdbc.Connection db) throws SQLException
	{
		int ts_spt = db.getGlobalTimestamp();
		if(ts_spt == 1)
			return Constants.PLUG_NOT_INITIALIZED;
		else if(ts_spt > 1)
			return Constants.PLUG_INITIALIZED;
		else
			return Constants.PLUG_TIMESTAMP_ERROR;
			
	}
	
	public static void makesPlugStateInit(org.inria.jdbc.Connection db) throws SQLException
	{
		db.setGlobalTimestamp(2);
	}
	
	public static String[] convertArrayListIntoString(ArrayList<String> list)
	{
		return list.toArray(new String[list.size()]);
	}
	
	public static String[][] convertDoubleArrayListIntoString(ArrayList<ArrayList<String>> list) 
	{
		String[][] strArr = new String[list.size()][];
		
		for(int i=0;i<list.size();i++)
		{
			String str[] = convertArrayListIntoString(list.get(i));
			strArr[i] = str;
		}
		
		return strArr;
	}

}
