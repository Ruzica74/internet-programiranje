package org.unibl.etf.dto;

import java.util.Date;
import java.util.Objects;

public class Kartica {
	
	private int idKorisnika;
	private String brojKartice;
	private String pin;
	private Float stanjeRacuna;
	private boolean omogucenoPlacanje;
	private int id_kartice;
	

	public Kartica() {
		// TODO Auto-generated constructor stub
	}
	
	
	


	public Kartica(int idKorisnika, String brojKartice, String pin, Float stanjeRacuna, boolean omogucenoPlacanje,
			int id_kartice) {
		super();
		this.idKorisnika = idKorisnika;
		this.brojKartice = brojKartice;
		this.pin = pin;
		this.stanjeRacuna = stanjeRacuna;
		this.omogucenoPlacanje = omogucenoPlacanje;
		this.id_kartice = id_kartice;
	}

	
	


	public int getId_kartice() {
		return id_kartice;
	}





	public void setId_kartice(int id_kartice) {
		this.id_kartice = id_kartice;
	}





	public int getIdKorisnika() {
		return idKorisnika;
	}




	public void setIdKorisnika(int idKorisnika) {
		this.idKorisnika = idKorisnika;
	}




	public String getBrojKartice() {
		return brojKartice;
	}


	public void setBrojKartice(String brojKartice) {
		this.brojKartice = brojKartice;
	}


	public String getPin() {
		return pin;
	}


	public void setPin(String pin) {
		this.pin = pin;
	}



	public Float getStanjeRacuna() {
		return stanjeRacuna;
	}


	public void setStanjeRacuna(Float stanjeRacuna) {
		this.stanjeRacuna = stanjeRacuna;
	}


	public boolean isOmogucenoPlacanje() {
		return omogucenoPlacanje;
	}


	public void setOmogucenoPlacanje(boolean omogucenoPlacanje) {
		this.omogucenoPlacanje = omogucenoPlacanje;
	}





	@Override
	public int hashCode() {
		return Objects.hash(brojKartice, idKorisnika, id_kartice, omogucenoPlacanje, pin, stanjeRacuna);
	}





	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kartica other = (Kartica) obj;
		return Objects.equals(brojKartice, other.brojKartice) && idKorisnika == other.idKorisnika
				&& id_kartice == other.id_kartice && omogucenoPlacanje == other.omogucenoPlacanje
				&& Objects.equals(pin, other.pin) && Objects.equals(stanjeRacuna, other.stanjeRacuna);
	}




	

	
	
	

}
