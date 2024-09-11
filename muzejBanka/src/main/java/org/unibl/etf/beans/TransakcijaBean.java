package org.unibl.etf.beans;

import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.dao.TransakcijaDAO;
import org.unibl.etf.dto.*;

public class TransakcijaBean {
	
	
	public TransakcijaBean() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Transakcija> getTransakcije(int id){
		return TransakcijaDAO.select(id);
	}

	
	
	

}
