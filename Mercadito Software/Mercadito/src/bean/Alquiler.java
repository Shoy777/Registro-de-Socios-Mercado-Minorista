package bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the alquiler database table.
 * 
 */
@Entity
@NamedQuery(name="Alquiler.findAll", query="SELECT a FROM Alquiler a")
public class Alquiler implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int idAlquiler;

	@Column(name="estado_alquiler")
	private String estadoAlquiler;

	@Lob
	@Column(name="firma_presidente")
	private byte[] firmaPresidente;

	@Lob
	@Column(name="firma_secretaria")
	private byte[] firmaSecretaria;

	//bi-directional many-to-one association to Socio
	@ManyToOne
	@JoinColumn(name="idSocio")
	private Socio socio;

	//bi-directional many-to-one association to DetalleAlquiler
	@OneToMany(mappedBy="alquiler")
	private List<DetalleAlquiler> detalleAlquilers;

	//bi-directional many-to-one association to Pagare
	@OneToMany(mappedBy="alquiler")
	private List<Pagare> pagares;

	public Alquiler() {
	}

	public int getIdAlquiler() {
		return this.idAlquiler;
	}

	public void setIdAlquiler(int idAlquiler) {
		this.idAlquiler = idAlquiler;
	}

	public String getEstadoAlquiler() {
		return this.estadoAlquiler;
	}

	public void setEstadoAlquiler(String estadoAlquiler) {
		this.estadoAlquiler = estadoAlquiler;
	}

	public byte[] getFirmaPresidente() {
		return this.firmaPresidente;
	}

	public void setFirmaPresidente(byte[] firmaPresidente) {
		this.firmaPresidente = firmaPresidente;
	}

	public byte[] getFirmaSecretaria() {
		return this.firmaSecretaria;
	}

	public void setFirmaSecretaria(byte[] firmaSecretaria) {
		this.firmaSecretaria = firmaSecretaria;
	}

	public Socio getSocio() {
		return this.socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public List<DetalleAlquiler> getDetalleAlquilers() {
		return this.detalleAlquilers;
	}

	public void setDetalleAlquilers(List<DetalleAlquiler> detalleAlquilers) {
		this.detalleAlquilers = detalleAlquilers;
	}

	public DetalleAlquiler addDetalleAlquiler(DetalleAlquiler detalleAlquiler) {
		getDetalleAlquilers().add(detalleAlquiler);
		detalleAlquiler.setAlquiler(this);

		return detalleAlquiler;
	}

	public DetalleAlquiler removeDetalleAlquiler(DetalleAlquiler detalleAlquiler) {
		getDetalleAlquilers().remove(detalleAlquiler);
		detalleAlquiler.setAlquiler(null);

		return detalleAlquiler;
	}

	public List<Pagare> getPagares() {
		return this.pagares;
	}

	public void setPagares(List<Pagare> pagares) {
		this.pagares = pagares;
	}

	public Pagare addPagare(Pagare pagare) {
		getPagares().add(pagare);
		pagare.setAlquiler(this);

		return pagare;
	}

	public Pagare removePagare(Pagare pagare) {
		getPagares().remove(pagare);
		pagare.setAlquiler(null);

		return pagare;
	}

}