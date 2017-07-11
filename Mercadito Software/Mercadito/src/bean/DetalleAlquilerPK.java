package bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the detalle_alquiler database table.
 * 
 */
@Embeddable
public class DetalleAlquilerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//@Column(insertable=false, updatable=false)
	private int idAlquiler;

	//@Column(insertable=false, updatable=false)
	private int idEstablecimiento;

	public DetalleAlquilerPK() {
	}
	public int getIdAlquiler() {
		return this.idAlquiler;
	}
	public void setIdAlquiler(int idAlquiler) {
		this.idAlquiler = idAlquiler;
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
		if (!(other instanceof DetalleAlquilerPK)) {
			return false;
		}
		DetalleAlquilerPK castOther = (DetalleAlquilerPK)other;
		return 
			(this.idAlquiler == castOther.idAlquiler)
			&& (this.idEstablecimiento == castOther.idEstablecimiento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idAlquiler;
		hash = hash * prime + this.idEstablecimiento;
		
		return hash;
	}
}