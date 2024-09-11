package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.unibl.etf.dto.Kartica;



public class KarticaDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_BROJ_KARTICE = "SELECT * FROM kartica where broj_kartice=?";
	private static final String SQL_UPDATE ="UPDATE kartica SET omoguceno_placanje=? where id=?";

	public KarticaDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static Kartica getkartica(String broj) {
		Kartica retVal=null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {broj};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_BROJ_KARTICE, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retVal=new Kartica(rs.getInt("registrovani_korisnik_korisnik_id"), rs.getString("broj_kartice"), rs.getString("pin"),
						rs.getFloat("stanje_racuna"), rs.getBoolean("omoguceno_placanje"), rs.getInt("id"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static boolean setkartica(int id, int i) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = {i,id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		System.out.println("update: "+result);
		return result;
	}
	
	

}
