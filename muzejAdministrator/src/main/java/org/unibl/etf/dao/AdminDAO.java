package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.unibl.etf.dto.Admin;



public class AdminDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_TOKEN = "SELECT * FROM korisnik INNER JOIN admin ON admin.korisnik_id=korisnik.id WHERE token=?";
	private static final String SQL_INSERT = "INSERT INTO korisnik (id,ime, prezime,korisnicko_ime, lozinka, mail, token, aktivan) VALUES (null,?,?,?,?,?,?,0)";
	private static final String SQL_INSERT_ADMIN="INSERT INTO admin VALUES (?)";
	private static final String SQL_UPDATE_TOKEN="UPDATE korisnik SET token=? where id=?";
	private static final String SQL_UPDATE="UPDATE korisnik SET ime=?, prezime=?, korisnicko_ime=?, lozinka=?, mail=? WHERE id=?";
	private static final String SQL_SELECT_ALL="SELECT * FROM korisnik INNER JOIN admin ON admin.korisnik_id=korisnik.id";
	private static final String SQL_DELETE="DELETE FROM admin WHERE korisnik_id=?";
	private static final String SQL_SELECT_KORISNICKO_IME="SELECT korisnicko_ime FROM korisnik";
	private static final String SQL_SELECT_BY_ID="SELECT * FROM korisnik INNER JOIN admin ON admin.korisnik_id=korisnik.id WHERE id=?";
	
	public AdminDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static Admin selectByToken(String token){
		Admin admin = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {token};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_SELECT_BY_TOKEN, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				admin = new Admin(rs.getInt("id"), rs.getString("ime"), rs.getString("prezime"), rs.getString("korisnicko_ime"), rs.getString("lozinka"), rs.getString("mail"), rs.getString("token"), rs.getBoolean("aktivan"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return admin;
	}
	
	public static Admin selectById(int id){
		Admin admin = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_SELECT_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				admin = new Admin(rs.getInt("id"), rs.getString("ime"), rs.getString("prezime"), rs.getString("korisnicko_ime"), rs.getString("lozinka"), rs.getString("mail"), rs.getString("token"), rs.getBoolean("aktivan"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return admin;
	}
	
	public static boolean insert(Admin admin) {
		boolean result = false;
		boolean res=false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { admin.getIme(), admin.getPrezime(), admin.getKorisnickoIma(), admin.getLozinka(), admin.getMail(),"" };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			if (generatedKeys.next())
				admin.setId(generatedKeys.getInt(1));
			pstmt.close();
			if(result==true) {
				res=insertAdmin(admin.getId());
				updateToken("admin"+admin.getId(),admin.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return res;
	}
	
	public static boolean insertAdmin(int id) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_ADMIN, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	public static boolean updateToken(String token, int id) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = {token,id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE_TOKEN, true, values);
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
		return result;
	}
	
	public static boolean update(Admin admin) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = {admin.getIme(), admin.getPrezime(),admin.getKorisnickoIma(), admin.getLozinka(), admin.getMail(), admin.getId()};
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
	
	public static ArrayList<Admin> selectAll() {
		ArrayList<Admin> retVal = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new Admin(rs.getInt("id"), rs.getString("ime"), rs.getString("prezime"), rs.getString("korisnicko_ime"), 
						rs.getString("lozinka"), rs.getString("mail"), rs.getString("token"), rs.getBoolean("aktivan")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static ArrayList<String> selectKIme() {
		ArrayList<String> retVal = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_KORISNICKO_IME, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(rs.getString("korisnicko_ime"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static boolean delete(int id) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_DELETE, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			pstmt.close();
		}catch(Exception exp) {
			exp.printStackTrace();
		}finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	
}
