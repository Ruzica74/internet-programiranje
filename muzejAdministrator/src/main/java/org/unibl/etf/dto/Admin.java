package org.unibl.etf.dto;

public class Admin {
	
	private int id;
	private String ime;
	private String prezime;
	private String korisnickoIma;
	private String lozinka;
	private String mail;
	private String token;
	private boolean aktivan;

	public Admin() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Admin(int id, String ime, String prezime, String korisnickoIma, String lozinka, String mail, String token,
			boolean aktivan) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIma = korisnickoIma;
		this.lozinka = lozinka;
		this.mail = mail;
		this.token = token;
		this.aktivan = aktivan;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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

	public String getKorisnickoIma() {
		return korisnickoIma;
	}

	public void setKorisnickoIma(String korisnickoIma) {
		this.korisnickoIma = korisnickoIma;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	
}
