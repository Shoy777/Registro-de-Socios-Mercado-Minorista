package bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the detalle_alquiler database table.
 * 
 */
@Entity
@Table(name="detalle_alquiler")
@NamedQuery(name="DetalleAlquiler.findAll", query="SELECT d FROM DetalleAlquiler d")
public class DetalleAlquiler implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleAlquilerPK id;

	private String estado;

	//bi-directional many-to-one association to Alquiler
	@ManyToOne
	@JoinColumn(name="idAlquiler")
	private Alquiler alquiler;

	//bi-directional many-to-one association to Establecimiento
	@ManyToOne
	@JoinColumn(name="idEstablecimiento")
	private Establecimiento establecimiento;

	public DetalleAlquiler() {
	}

	public DetalleAlquilerPK getId() {
		return this.id;
	}

	public void setId(DetalleAlquilerPK id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Alquiler getAlquiler() {
		return this.alquiler;
	}

	public void setAlquiler(Alquiler alquiler) {
		this.alquiler = alquiler;
	}

	public Establecimiento getEstablecimiento() {
		return this.establecimiento;
	}

	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}

}