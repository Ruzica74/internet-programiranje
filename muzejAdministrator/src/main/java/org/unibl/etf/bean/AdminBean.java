package org.unibl.etf.bean;

import java.util.ArrayList;

import org.unibl.etf.dao.AdminDAO;
import org.unibl.etf.dto.Admin;

public class AdminBean {

	Admin ulogovan;
	Admin izmjena;
	
	
	public AdminBean() {
		// TODO Auto-generated constructor stub
	}
	
	public Admin getById(int id) {
		return AdminDAO.selectById(id);
	}
	
	public ArrayList<Admin> getAll(){
		return AdminDAO.selectAll();
	}
	
	public boolean delete(int id) {
		return AdminDAO.delete(id);
	}
	
	public boolean update(Admin a) {
		return AdminDAO.update(a);
	}
	
	public boolean updateToken(String token, int id) {
		return AdminDAO.updateToken(token, id);
	}
	
	public Admin getAdmin(String token) {
		return AdminDAO.selectByToken(token);
	}
	
	public boolean insert(Admin a) {
		return AdminDAO.insert(a);
	}
	
	public boolean provjeraKImena(String kIme) {
		ArrayList<String> imena=AdminDAO.selectKIme();
		for(String i : imena) {
			if(i.equals(kIme)) {
				return false;
			}
		}
		return true;
	}

}
