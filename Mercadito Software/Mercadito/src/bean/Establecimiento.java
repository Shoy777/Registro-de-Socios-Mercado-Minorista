package bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the establecimiento database table.
 * 
 */
@Entity
@NamedQuery(name="Establecimiento.findAll", query="SELECT e FROM Establecimiento e")
public class Establecimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@EmbeddedId
	private int idEstablecimiento;

	@Column(name="estado_est")
	private String estadoEst = "DISPONIBLE";

	@Column(name="giro_negocio")
	private String giroNegocio;

	private String numero;

	private String pasaje;

	@Column(name="precio_alquiler")
	private double precioAlquiler;

	private String tipo;

	//bi-directional many-to-one association to DetalleAlquiler
	@OneToMany(mappedBy="establecimiento")
	private List<DetalleAlquiler> detalleAlquilers;

	//bi-directional many-to-one association to DetallePagare
	@OneToMany(mappedBy="establecimiento")
	private List<DetallePagare> detallePagares;

	public Establecimiento() {
	}

	public int getIdEstablecimiento() {
		return this.idEstablecimiento;
	}

	public void setIdEstablecimiento(int idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}

	public String getEstadoEst() {
		return this.estadoEst;
	}

	public void setEstadoEst(String estadoEst) {
		this.estadoEst = estadoEst;
	}

	public String getGiroNegocio() {
		return this.giroNegocio;
	}

	public void setGiroNegocio(String giroNegocio) {
		this.giroNegocio = giroNegocio;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPasaje() {
		return this.pasaje;
	}

	public void setPasaje(String pasaje) {
		this.pasaje = pasaje;
	}

	public double getPrecioAlquiler() {
		return this.precioAlquiler;
	}

	public void setPrecioAlquiler(double precioAlquiler) {
		this.precioAlquiler = precioAlquiler;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<DetalleAlquiler> getDetalleAlquilers() {
		return this.detalleAlquilers;
	}

	public void setDetalleAlquilers(List<DetalleAlquiler> detalleAlquilers) {
		this.detalleAlquilers = detalleAlquilers;
	}

	public DetalleAlquiler addDetalleAlquiler(DetalleAlquiler detalleAlquiler) {
		getDetalleAlquilers().add(detalleAlquiler);
		detalleAlquiler.setEstablecimiento(this);

		return detalleAlquiler;
	}

	public DetalleAlquiler removeDetalleAlquiler(DetalleAlquiler detalleAlquiler) {
		getDetalleAlquilers().remove(detalleAlquiler);
		detalleAlquiler.setEstablecimiento(null);

		return detalleAlquiler;
	}

	public List<DetallePagare> getDetallePagares() {
		return this.detallePagares;
	}

	public void setDetallePagares(List<DetallePagare> detallePagares) {
		this.detallePagares = detallePagares;
	}

	public DetallePagare addDetallePagare(DetallePagare detallePagare) {
		getDetallePagares().add(detallePagare);
		detallePagare.setEstablecimiento(this);

		return detallePagare;
	}

	public DetallePagare removeDetallePagare(DetallePagare detallePagare) {
		getDetallePagares().remove(detallePagare);
		detallePagare.setEstablecimiento(null);

		return detallePagare;
	}

	@Transient
	private Long id;
	public Long getId(){
	    return id;
	}
	public void setId(Long id){
	    this.id = id;
	}
}