package bean;

import java.io.FileOutputStream;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.persistence.*;
import javax.servlet.ServletContext;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the socio database table.
 * 
 */
@Entity
@NamedQuery(name="Socio.findAll", query="SELECT s FROM Socio s")
public class Socio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idSocio;

	private String apellidos;

	@Column(name="cargo_anterior")
	private String cargoAnterior;

	private String correo;

	@Column(name="direccion_residencia")
	private String direccionResidencia;

	private String dni;

	@Column(name="estado_civil")
	private String estadoCivil;

	@Column(name="estado_socio")
	private String estadoSocio = "ACTIVO";

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ing")
	private Date fechaIng = new Date();

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ins")
	private Date fechaIns = new Date();

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nac")
	private Date fechaNac;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_reins")
	private Date fechaReins;

	@Lob
	@Column(name="firma_socio")
	private byte[] firmaSocio;

	@Lob
	@Column(name="foto_socio")
	private byte[] fotoSocio;

	@Column(name="nombre_conyuge")
	private String nombreConyuge;

	private String nombres;

	private String sexo;

	@Column(name="telefono_fijo")
	private String telefonoFijo;

	@Column(name="telefono_movil")
	private String telefonoMovil;

	//bi-directional many-to-one association to Alquiler
	@OneToMany(mappedBy="socio")
	private List<Alquiler> alquilers;

	//bi-directional many-to-one association to Ubigeo
	@ManyToOne
	@JoinColumn(name="idUbigeo")
	private Ubigeo ubigeo;

	public Socio() {
		ubigeo = new Ubigeo();
	}

	public int getIdSocio() {
		return this.idSocio;
	}

	public void setIdSocio(int idSocio) {
		this.idSocio = idSocio;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCargoAnterior() {
		return this.cargoAnterior;
	}

	public void setCargoAnterior(String cargoAnterior) {
		this.cargoAnterior = cargoAnterior;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccionResidencia() {
		return this.direccionResidencia;
	}

	public void setDireccionResidencia(String direccionResidencia) {
		this.direccionResidencia = direccionResidencia;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getEstadoSocio() {
		return this.estadoSocio;
	}

	public void setEstadoSocio(String estadoSocio) {
		this.estadoSocio = estadoSocio;
	}

	public Date getFechaIng() {
		return this.fechaIng;
	}

	public void setFechaIng(Date fechaIng) {
		this.fechaIng = fechaIng;
	}

	public Date getFechaIns() {
		return this.fechaIns;
	}

	public void setFechaIns(Date fechaIns) {
		this.fechaIns = fechaIns;
	}

	public Date getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Date getFechaReins() {
		return this.fechaReins;
	}

	public void setFechaReins(Date fechaReins) {
		this.fechaReins = fechaReins;
	}

	public byte[] getFirmaSocio() {
		return this.firmaSocio;
	}

	public void setFirmaSocio(byte[] firmaSocio) {
		this.firmaSocio = firmaSocio;
	}

	public byte[] getFotoSocio() {
		return this.fotoSocio;
	}

	public void setFotoSocio(byte[] fotoSocio) {
		this.fotoSocio = fotoSocio;
	}

	public String getNombreConyuge() {
		return this.nombreConyuge;
	}

	public void setNombreConyuge(String nombreConyuge) {
		this.nombreConyuge = nombreConyuge;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefonoFijo() {
		return this.telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public String getTelefonoMovil() {
		return this.telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public List<Alquiler> getAlquilers() {
		return this.alquilers;
	}

	public void setAlquilers(List<Alquiler> alquilers) {
		this.alquilers = alquilers;
	}

	public Alquiler addAlquiler(Alquiler alquiler) {
		getAlquilers().add(alquiler);
		alquiler.setSocio(this);

		return alquiler;
	}

	public Alquiler removeAlquiler(Alquiler alquiler) {
		getAlquilers().remove(alquiler);
		alquiler.setSocio(null);

		return alquiler;
	}

	public Ubigeo getUbigeo() {
		return this.ubigeo;
	}
	
	public void setUbigeo(Ubigeo ubigeo) {
		this.ubigeo = ubigeo;
	}
	
	@Transient
	private String mostrarFotoSocio;
	
	@Transient
	private String mostrarFirmaSocio;

	public String getMostrarFotoSocio() {
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String realPath=(String) servletContext.getRealPath("/"); 
		
		try {
			String rutaFile = realPath+"\\"+ idSocio +"Portada.jpg";
			
			System.out.println("Portada -> " + rutaFile);
			
			mostrarFotoSocio= idSocio +"Portada.jpg";
			FileOutputStream fileOuputStream = new FileOutputStream(rutaFile);
			fileOuputStream.write(fotoSocio);
			fileOuputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mostrarFotoSocio;
	}

	public void setMostrarFotoSocio(String mostrarFotoSocio) {
		this.mostrarFotoSocio = mostrarFotoSocio;
	}

	public String getMostrarFirmaSocio() {
		
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String realPath=(String) servletContext.getRealPath("/"); 
		 
		try {
			String rutaFile = realPath+"\\"+ idSocio +".jpg";
			mostrarFirmaSocio= idSocio +".jpg";
			
			System.out.println("Imagen -> " + rutaFile);
			
			FileOutputStream fileOuputStream = new FileOutputStream(rutaFile);
			fileOuputStream.write(firmaSocio);
			fileOuputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mostrarFirmaSocio;
	}

	public void setMostrarFirmaSocio(String mostrarFirmaSocio) {
		this.mostrarFirmaSocio = mostrarFirmaSocio;
	}
	
	@Transient
	private String socioNA = nombres + " "+ apellidos;

	@Transient
	private String socioDNI = dni;

	public String getSocioNA() {
		return socioNA;
	}

	public void setSocioNA(String socioNA) {
		this.socioNA = socioNA;
	}

	public String getSocioDNI() {
		return socioDNI;
	}

	public void setSocioDNI(String socioDNI) {
		this.socioDNI = socioDNI;
	}
	
}