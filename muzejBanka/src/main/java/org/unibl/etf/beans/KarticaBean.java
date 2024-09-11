package org.unibl.etf.beans;

import org.unibl.etf.dao.KarticaDAO;
import org.unibl.etf.dao.KorisnikDAO;
import org.unibl.etf.dto.Kartica;
import org.unibl.etf.dto.Korisnik;

public class KarticaBean {
	
	Korisnik korisnik;
	Kartica kartica;

	public KarticaBean() {
		// TODO Auto-generated constructor stub
	}
	
	public void update(int i) {
		KarticaDAO.setkartica(kartica.getId_kartice(), i);
	}
	
	public boolean getkartica(String broj) {
		Kartica k=KarticaDAO.getkartica(broj);
		if(k!=null) {
			kartica=k;
			Korisnik kor=KorisnikDAO.getkartica(kartica.getIdKorisnika());
			if(kor!=null) {
				korisnik=kor;
				return true;
			}
		}
		return false;
	}
	
	public boolean provjera(String ime, String prezime, String pin) {
		if(korisnik.getIme().equals(ime) && korisnik.getPrezime().equals(prezime) && kartica.getPin().equals(pin)) {
			return true;
		}else {
			return false;
		}
	}
	
	public void odlogujSe() {
		korisnik=null;
		kartica=null;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Kartica getKartica() {
		return kartica;
	}

	public void setKartica(Kartica kartica) {
		this.kartica = kartica;
	}
	
	

}
