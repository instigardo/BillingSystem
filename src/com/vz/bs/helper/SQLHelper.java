package com.vz.bs.helper;

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
