package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.unibl.etf.dao.ConnectionPool;
import org.unibl.etf.dto.Kartica;
import org.unibl.etf.dto.Korisnik;

public class KorisnikDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM korisnik where id=?";
	

	public KorisnikDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static Korisnik getkartica(int i) {
		Korisnik retVal=null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {i};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retVal=new Korisnik(rs.getString("ime"), rs.getString("prezime"));
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
