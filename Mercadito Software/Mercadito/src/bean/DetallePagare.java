package bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the detalle_pagare database table.
 * 
 */
@Entity
@Table(name="detalle_pagare")
@NamedQuery(name="DetallePagare.findAll", query="SELECT d FROM DetallePagare d")
public class DetallePagare implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetallePagarePK id;

	@Column(name="precio_alquiler")
	private double precioAlquiler;

	//bi-directional many-to-one association to Establecimiento
	@ManyToOne
	@JoinColumn(name="idEstablecimiento")
	private Establecimiento establecimiento;

	//bi-directional many-to-one association to Pagare
	@ManyToOne
	@JoinColumn(name="idPagare")
	private Pagare pagare;

	public DetallePagare() {
	}

	public DetallePagarePK getId() {
		return this.id;
	}

	public void setId(DetallePagarePK id) {
		this.id = id;
	}

	public double getPrecioAlquiler() {
		return this.precioAlquiler;
	}

	public void setPrecioAlquiler(double precioAlquiler) {
		this.precioAlquiler = precioAlquiler;
	}

	public Establecimiento getEstablecimiento() {
		return this.establecimiento;
	}

	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}

	public Pagare getPagare() {
		return this.pagare;
	}

	public void setPagare(Pagare pagare) {
		this.pagare = pagare;
	}

}