package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.unibl.etf.dto.Transakcija;



public class TransakcijaDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_KARTICA_ID = "SELECT * FROM transakcija where kartica_id=?";

	public TransakcijaDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static ArrayList<Transakcija> select(int id) {
		ArrayList<Transakcija> retVal = new ArrayList<Transakcija>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_SELECT_BY_KARTICA_ID, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new Transakcija(rs.getInt("id"), rs.getFloat("iznos"),rs.getDate("datum")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}

}
