package bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the detalle_pagare database table.
 * 
 */
@Embeddable
public class DetallePagarePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//@Column(insertable=false, updatable=false)
	private int idPagare;

	//@Column(insertable=false, updatable=false)
	private int idEstablecimiento;

	public DetallePagarePK() {
	}
	public int getIdPagare() {
		return this.idPagare;
	}
	public void setIdPagare(int idPagare) {
		this.idPagare = idPagare;
	}
	public int getIdEstablecimiento() {
		return this.idEstablecimiento;
	}
	public void setIdEstablecimiento(int idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetallePagarePK)) {
			return false;
		}
		DetallePagarePK castOther = (DetallePagarePK)other;
		return 
			(this.idPagare == castOther.idPagare)
			&& (this.idEstablecimiento == castOther.idEstablecimiento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPagare;
		hash = hash * prime + this.idEstablecimiento;
		
		return hash;
	}
}