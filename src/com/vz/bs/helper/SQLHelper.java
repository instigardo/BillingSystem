package com.vz.bs.helper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLHelper {
	Connection con;
	int r = 0;

	public SQLHelper() {
		ConnectionGetter cg = new ConnectionGetter();
		con = cg.getConnection();
	}

	public boolean EXEC_bill_details(String pName, String accno, String billInput, String billGen, double billAmt, String dateTS, String P1, String P2) {
		//	String s = "exec " + pName + " ( " + parameters + ")";
			String s1="begin "+ pName+" (?,?,?,?,?,?,?); end;";
			
			System.out.println(s1);
			try {
				CallableStatement st = con.prepareCall(s1);
				st.setString(1, accno);
				st.setString(2, billInput);
				st.setString(3, billGen);
				st.setDouble(4, billAmt);
				st.setString(5, dateTS);
				st.setString(6, P1);
				st.setString(7, P2);
				boolean i = st.execute();
				return i;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
			
		}
	
	
	public boolean EXEC_bill_details_update(String pName, String accno, String billInput, double billAmt, String dateTS) {
			//	String s = "exec " + pName + " ( " + parameters + ")";
			String s1="begin "+ pName+" (?,?,?,?); end;";
			
			System.out.println(s1);
			try {
				CallableStatement st = con.prepareCall(s1);
				st.setString(1, accno);
				st.setString(2, billInput);
				st.setDouble(3, billAmt);
				st.setString(4, dateTS);
				
				
				boolean i = st.execute();
				return i;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}

	
	public boolean EXEC_bill_cycle_update1(String pName, String accno, String billGen, String p1, String p2) {
		//	String s = "exec " + pName + " ( " + parameters + ")";
		String s1="begin "+ pName+" (?,?,?,?); end;";
		
		System.out.println(s1);
		try {
			CallableStatement st = con.prepareCall(s1);
			st.setString(1, accno);
			st.setString(2, billGen);
			st.setString(3, p1);
			st.setString(4, p2);
			
			
			boolean i = st.execute();
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ResultSet SELECT(String tName, String cName, String where) {
		if (where == null || where == "") {
			where = "1";
		}

		String s = "select " + cName + " from " + tName + " where " + where;
		//System.out.println(s);
		try {
			Statement st = con.createStatement();
			ResultSet rs1 = st.executeQuery(s);
			return rs1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int UPDATE(String tName, String cName, String where) {
		if (where == null || where == "") {
			where = "1=1";
		}
		String s = "update " + tName + " set " + cName + " where " + where;
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(s);
			int i = ps.executeUpdate();
			r = i;
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public int INSERT(String tName, String values) {
		String s = "insert into " + tName + " values(" + values + ")";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(s);
			int i = ps.executeUpdate();
			r = i;
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int DELETE(String tName, String where) {
		String s = "delete from " + tName + " where " + where;
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(s);
			int i = ps.executeUpdate();
			r = i;
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

}
