package org.unibl.etf.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Korisnik {
	
	private String ime;
	private String prezime;
	
	

	public Korisnik() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Korisnik(String ime, String prezime) {
		super();
		this.ime = ime;
		this.prezime = prezime;
	}



	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}



	@Override
	public int hashCode() {
		return Objects.hash(ime, prezime);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korisnik other = (Korisnik) obj;
		return Objects.equals(ime, other.ime) && Objects.equals(prezime, other.prezime);
	}



	@Override
	public String toString() {
		return "Korisnik [ime=" + ime + ", prezime=" + prezime + "]";
	}
	
	
	

}
