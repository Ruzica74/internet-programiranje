package org.unibl.etf.dto;

import java.util.Date;
import java.util.Objects;

public class Transakcija {
	
	private Integer id;
	private Float iznos;
	private Date datum;

	public Transakcija() {
		// TODO Auto-generated constructor stub
	}
	

	public Transakcija(Integer id, Float iznos, Date datum) {
		super();
		this.id = id;
		
		this.iznos = iznos;
		this.datum = datum;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Float getIznos() {
		return iznos;
	}

	public void setIznos(Float iznos) {
		this.iznos = iznos;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(datum, id, iznos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transakcija other = (Transakcija) obj;
		return Objects.equals(datum, other.datum) && Objects.equals(id, other.id) && Objects.equals(iznos, other.iznos)
				;
	}

	@Override
	public String toString() {
		return "Transakcija [id=" + id + ", iznos=" + iznos + ", datum=" + datum + "]";
	}
	
	

}
