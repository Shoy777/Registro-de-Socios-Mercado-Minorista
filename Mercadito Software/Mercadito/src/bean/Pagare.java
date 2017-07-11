package bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pagare database table.
 * 
 */
@Entity
@NamedQuery(name="Pagare.findAll", query="SELECT p FROM Pagare p")
public class Pagare implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idPagare;

	@Column(name="estado_pagare")
	private String estadoPagare;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_generada")
	private Date fechaGenerada;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_pago")
	private Date fechaPago;

	@Column(name="monto_total")
	private double montoTotal;

	//bi-directional many-to-one association to DetallePagare
	@OneToMany(mappedBy="pagare")
	private List<DetallePagare> detallePagares;

	//bi-directional many-to-one association to Alquiler
	@ManyToOne
	@JoinColumn(name="idAlquiler")
	private Alquiler alquiler;

	public Pagare() {
	}

	public int getIdPagare() {
		return this.idPagare;
	}

	public void setIdPagare(int idPagare) {
		this.idPagare = idPagare;
	}

	public String getEstadoPagare() {
		return this.estadoPagare;
	}

	public void setEstadoPagare(String estadoPagare) {
		this.estadoPagare = estadoPagare;
	}

	public Date getFechaGenerada() {
		return this.fechaGenerada;
	}

	public void setFechaGenerada(Date fechaGenerada) {
		this.fechaGenerada = fechaGenerada;
	}

	public Date getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public double getMontoTotal() {
		return this.montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public List<DetallePagare> getDetallePagares() {
		return this.detallePagares;
	}

	public void setDetallePagares(List<DetallePagare> detallePagares) {
		this.detallePagares = detallePagares;
	}

	public DetallePagare addDetallePagare(DetallePagare detallePagare) {
		getDetallePagares().add(detallePagare);
		detallePagare.setPagare(this);

		return detallePagare;
	}

	public DetallePagare removeDetallePagare(DetallePagare detallePagare) {
		getDetallePagares().remove(detallePagare);
		detallePagare.setPagare(null);

		return detallePagare;
	}

	public Alquiler getAlquiler() {
		return this.alquiler;
	}

	public void setAlquiler(Alquiler alquiler) {
		this.alquiler = alquiler;
	}

}