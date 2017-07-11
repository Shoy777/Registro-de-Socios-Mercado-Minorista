package bean;

import java.io.FileOutputStream;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.persistence.*;
import javax.servlet.ServletContext;

import java.util.Date;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idUsuario;

	private String apellidos;

	private String correo;

	@Column(name="direccion_residencia")
	private String direccionResidencia;

	private String dni;

	@Column(name="estado_civil")
	private String estadoCivil;

	@Column(name="estado_usuario")
	private String estadoUsuario = "ACTIVO";

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nac")
	private Date fechaNac;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_reg")
	private Date fechaReg;

	@Lob
	@Column(name="firma_usuario")
	private byte[] firmaUsuario;

	@Lob
	@Column(name="foto_usuario")
	private byte[] fotoUsuario;

	@Column(name="nombre_conyuge")
	private String nombreConyuge;

	private String nombres;

	private String password;

	private String sexo;

	@Column(name="telefono_fijo")
	private String telefonoFijo;

	@Column(name="telefono_movil")
	private String telefonoMovil;

	@Column(name="tipo_usuario")
	private String tipoUsuario;

	private String usuario;

	//bi-directional many-to-one association to Ubigeo
	@ManyToOne
	@JoinColumn(name="idUbigeo")
	private Ubigeo ubigeo;

	public Usuario() {
		ubigeo = new Ubigeo();
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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

	public String getEstadoUsuario() {
		return this.estadoUsuario;
	}

	public void setEstadoUsuario(String estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public Date getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Date getFechaReg() {
		return this.fechaReg;
	}

	public void setFechaReg(Date fechaReg) {
		this.fechaReg = fechaReg;
	}

	public byte[] getFirmaUsuario() {
		return this.firmaUsuario;
	}

	public void setFirmaUsuario(byte[] firmaUsuario) {
		this.firmaUsuario = firmaUsuario;
	}

	public byte[] getFotoUsuario() {
		return this.fotoUsuario;
	}

	public void setFotoUsuario(byte[] fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Ubigeo getUbigeo() {
		return this.ubigeo;
	}

	public void setUbigeo(Ubigeo ubigeo) {
		this.ubigeo = ubigeo;
	}
	
	@Transient
	private String mostrarFotoUsuario;
	
	@Transient
	private String mostrarFirmaUsuario;

	public String getMostrarFotoUsuario() {
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String realPath=(String) servletContext.getRealPath("/"); 
		
		try {
			String rutaFile = realPath+"\\"+ idUsuario +"Portada.jpg";
			
			System.out.println("Portada -> " + rutaFile);
			mostrarFotoUsuario= idUsuario +"Portada.jpg";
			/*
			if(realPath == null){
				String firma;
				firma = "/bootstrap/img/firma.png";
				FileOutputStream fileOuputStream = new FileOutputStream(firma);
				fileOuputStream.write(fotoUsuario);
				fileOuputStream.close();
			}else{*/
			FileOutputStream fileOuputStream = new FileOutputStream(rutaFile);
			fileOuputStream.write(fotoUsuario);
			fileOuputStream.close();
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mostrarFotoUsuario;
	}

	public void setMostrarFotoUsuario(String mostrarFotoUsuario) {
		this.mostrarFotoUsuario = mostrarFotoUsuario;
	}

	public String getMostrarFirmaUsuario() {
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String realPath=(String) servletContext.getRealPath("/"); 
		 
		try {
			String rutaFile = realPath+"\\"+ idUsuario +".jpg";
			mostrarFirmaUsuario= idUsuario +".jpg";
			
			System.out.println("Imagen -> " + rutaFile);
			/*
			if(realPath==null){
				String foto;
				if(sexo.equals("MASCULINO")){
					foto = "/bootstrap/img/masculino.jpg";
					FileOutputStream fileOuputStream = new FileOutputStream(foto);
					fileOuputStream.write(firmaUsuario);
					fileOuputStream.close();
				}
				else{
					foto = "/bootstrap/img/femenino.jpg";
					FileOutputStream fileOuputStream = new FileOutputStream(foto);
					fileOuputStream.write(firmaUsuario);
					fileOuputStream.close();
				}
			}else{
				*/
				FileOutputStream fileOuputStream = new FileOutputStream(rutaFile);
				fileOuputStream.write(firmaUsuario);
				fileOuputStream.close();
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mostrarFirmaUsuario;
	}

	public void setMostrarFirmaUsuario(String mostrarFirmaUsuario) {
		this.mostrarFirmaUsuario = mostrarFirmaUsuario;
	}

}