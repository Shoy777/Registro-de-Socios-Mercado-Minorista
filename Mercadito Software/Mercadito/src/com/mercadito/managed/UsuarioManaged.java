package com.mercadito.managed;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.binary.Base64;

import com.mercadito.dao.UbigeoDAO;
import com.mercadito.dao.UsuarioDAO;

import bean.Ubigeo;
import bean.Usuario;

@SessionScoped
@ManagedBean(name="usuariomanaged")
public class UsuarioManaged {
	
	private List<Usuario> usuarios;
	private Usuario usuarioS = new Usuario();
	private Usuario usuarioG = new Usuario();
	private Usuario usuarioM = new Usuario();
	private String apellidos, nombres, firma, foto;
	private String user, password;
	String msg = "";
	
	private String departamento2, provincia2, distrito2, confirmar;
	private String tipoBusUsu, tipoCriterio, tipoActualizacion;
	
	private String fotoEn64;
	private String fotoEn642;
	
	UsuarioDAO dao=new UsuarioDAO();
	
	private List<Ubigeo> listaDepartamentos;
	private List<Ubigeo> listaProvincias;
	private List<Ubigeo> listaDistritos;
	
	private String departamento;
	private String distrito;
	private String provincia;
	
	UbigeoDAO daoU = new UbigeoDAO();
	
	public String Login(){
		String ruta = "";
		
		FacesMessage msg = null;
		try {
			usuarioS = dao.valida(user, password);
			int id = usuarioS.getUbigeo().getIdUbigeo();
			Ubigeo u = usuarioS.getUbigeo();
			u = daoU.buscarUbigeo(id);
			if(usuarioS!=null){
				apellidos = usuarioS.getApellidos();
				nombres = usuarioS.getNombres();
				byte[] fotoM = usuarioS.getFotoUsuario();
				byte[] firmaM = usuarioS.getFirmaUsuario();
				
				if(fotoM==null){
					if(usuarioS.getSexo().equals("MASCULINO")){
						foto = "/bootstrap/img/masculino.jpg";
					}else{
						foto = "/bootstrap/img/femenino.jpg";
					}
				}else{
					foto = usuarioS.getMostrarFotoUsuario();
				}
				if(firmaM==null){
					firma = "/bootstrap/img/firma.png";
				}else{
					firma = usuarioS.getMostrarFirmaUsuario();
				}
				
				departamento2 = u.getDepartamento();
				provincia2 = u.getProvincia();
				distrito2 = u.getDistrito();
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioS", usuarioS);
				return ruta = "/pages/index?faces-redirect=true";
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario Invalido", "Invalido");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return ruta = "/pages/Login?faces-redirect=true";	
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", 
					"Se ha producido un inconveniente, vuelva a presionar 'Iniciar Sesion'"));
		} finally{
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		return ruta;		
	}
	
	@SuppressWarnings("static-access")
	public void verificarSesion(){
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Usuario us = (Usuario)context.getExternalContext().getSessionMap().get("usuarioS");
			if(us == null){
				context.getCurrentInstance().getExternalContext().redirect("/Mercadito/pages/Login.jsf");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void cerrarSesion(){
		FacesMessage msg = null;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Salio", "Salio");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String Grabar(){
		
		int buscarUsuario1 = dao.verificarUsuario(usuarioG.getNombres(), usuarioG.getApellidos());
		int buscarUsuario2 = dao.verificarUsuario(usuarioG.getDni());
		
		boolean emailCorrecto;
        emailCorrecto=usuarioG.getCorreo().matches("^[A-Za-z0-9-]+([_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        
        try{
        	if(usuarioG.getTipoUsuario().equals("")){
				msg = "Seleccione tipo de usuario";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioG.getNombres().equals("") || usuarioG.getNombres().length()<2){
				msg = "Ingrese Nombres del usuario correctamente";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioG.getApellidos().equals("") || usuarioG.getApellidos().length()<2){
				msg = "Ingrese Apellidos del usuario correctamente";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioG.getDni().equals("") || usuarioG.getDni().length()<8){
				msg = "Ingrese DNI del usuario, 8 digitos";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioG.getDireccionResidencia().equals("") || usuarioG.getDireccionResidencia().length()<10){
				msg = "Ingrese Residencia del usuario";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioG.getTelefonoFijo().equals("") || usuarioG.getTelefonoFijo().length()<7){
				msg = "Ingrese un telefono fijo, 7 digitos";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioG.getTelefonoMovil().equals("") || usuarioG.getTelefonoMovil().length()<9){
				msg = "Telefono movil, 9 digitos";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioG.getCorreo().equals("")){
				msg = "Ingrese un correo electronico ejm: micorreo@hot.es";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(emailCorrecto == false){
				msg = "El correo tiene formato incorrecto, no se admiten guiones ni puntos al inicio";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioG.getUsuario().equals("")){
				msg = "Ingrese un usuario";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioG.getPassword().equals("") || confirmar.equals("")){
				msg = "Ingrese un password";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioG.getFechaNac() == null){
				msg = "Ingrese Fecha Nacimiento del usuario";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioG.getSexo().equals("")){
				msg = "Seleccione Sexo";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioG.getEstadoCivil().equals("")){
				msg = "Seleccione EstadoCivil";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioG.getEstadoCivil().equals("CASADO") && usuarioG.getNombreConyuge().equals("")){
				msg = "Ingrese nombre de conyuge";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(departamento.equals("")){
				msg = "Seleccione Departamento";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(provincia.equals("")){
				msg = "Seleccione Provincia";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioG.getUbigeo().getIdUbigeo()==0){
				msg = "Seleccione Distrito";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(fotoEn64.equals("")){
				msg = "Dibuje Firma del usuario";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(fotoEn642.equals("")){
				msg = "Tome Foto del usuario";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else{
				if(buscarUsuario1 != 0){
					msg = "Ya existe usuario con este nombre y apellido";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				}else if(buscarUsuario2 != 0){
					msg = "Ya existe usuario con este DNI";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				}else{
					System.out.println("Antes de Cortar: " + fotoEn64);
					System.out.println("Antes de Cortar: " + fotoEn642);
					
					fotoEn64 = fotoEn64.substring(fotoEn64.indexOf(',')+1, fotoEn64.length()-1);
					fotoEn642 = fotoEn642.substring(fotoEn642.indexOf(',')+1, fotoEn642.length()-1);
					
					System.out.println("Despues de Cortar: " + fotoEn64);
					System.out.println("Despues de Cortar: " + fotoEn642);
					
					byte[] bytes = new Base64().decode(fotoEn64);
					usuarioG.setFirmaUsuario(bytes);
					byte[] bytes2 = new Base64().decode(fotoEn642);
					usuarioG.setFotoUsuario(bytes2);
					
					msg = "Registro de Usuario Grabado";
					
					dao.Grabar(usuarioG);
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
					return "registrar-usuario?faces-redirect=true";
				}
			}
        	
		}catch(Exception e){
			msg = "Se ha producido un inconveniente";
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
		}finally{
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		return null;
	}

	public void Buscar(){		
		usuarioM = dao.Buscar(usuarioM.getIdUsuario());
	}
	
	public String Editar(){
		try{
			if(tipoActualizacion.equals("0")){
				//NINGUNO
				msg = "Seleccione un tipo de actualizacion";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				
			}else if(tipoActualizacion.equals("1")){
				//EDITAR DATOS DE USUARIO
				EditarDatosUsuario();
				msg = "Registro de Usuario Actualizado con Exito";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				return "modificar-usuario?faces-redirect=true";
				
			}else if(tipoActualizacion.equals("2")){
				//EDITAR FIRMA
				if(fotoEn64.equals("")){
					msg = "Dibuje Firma del usuario";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				}else{
					System.out.println("Antes de Cortar: " + fotoEn64);
					fotoEn64 = fotoEn64.substring(fotoEn64.indexOf(',')+1, fotoEn64.length()-1);
					System.out.println("Despues de Cortar: " + fotoEn64);
					byte[] bytes = new Base64().decode(fotoEn64);
					usuarioM.setFirmaUsuario(bytes);
					
					EditarDatosUsuario();
					msg = "Registro de Usuario Actualizado con Exito";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
					return "modificar-usuario?faces-redirect=true";
				}
			}else if(tipoActualizacion.equals("3")){
				//EDITAR FOTO
				if(fotoEn642.equals("")){
					msg = "Tome Foto del usuario";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				}else{
					System.out.println("Antes de Cortar: " + fotoEn642);
					fotoEn642 = fotoEn642.substring(fotoEn642.indexOf(',')+1, fotoEn642.length()-1);
					System.out.println("Despues de Cortar: " + fotoEn642);
					byte[] bytes = new Base64().decode(fotoEn642);
					usuarioM.setFotoUsuario(bytes);
					
					EditarDatosUsuario();
					msg = "Registro de Usuario Actualizado con Exito";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
					return "modificar-usuario?faces-redirect=true";
				}
			}else if(tipoActualizacion.equals("4")){
				//EDITAR FIRMA Y FOTO
				if(fotoEn64.equals("")){
					msg = "Dibuje Firma del usuario";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				}else if(fotoEn642.equals("")){
					msg = "Tome Foto del usuario";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				}else{
					System.out.println("Antes de Cortar: " + fotoEn64);
					System.out.println("Antes de Cortar: " + fotoEn642);
					fotoEn64 = fotoEn64.substring(fotoEn64.indexOf(',')+1, fotoEn64.length()-1);
					fotoEn642 = fotoEn642.substring(fotoEn642.indexOf(',')+1, fotoEn642.length()-1);
					System.out.println("Despues de Cortar: " + fotoEn64);
					System.out.println("Despues de Cortar: " + fotoEn642);
					byte[] bytes = new Base64().decode(fotoEn64);
					usuarioM.setFirmaUsuario(bytes);
					byte[] bytes2 = new Base64().decode(fotoEn642);
					usuarioM.setFotoUsuario(bytes2);
					
					EditarDatosUsuario();
					msg = "Registro de Usuario Actualizado con Exito";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
					return "modificar-usuario?faces-redirect=true";
				}
				
			}else if(tipoActualizacion.equals("5")){
				//ELIMINAR SOCIO
				usuarioM.setEstadoUsuario("INACTIVO");
				EditarDatosUsuario();
				msg = "Registro de Usuario Actualizado con Exito";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				return "modificar-usuario?faces-redirect=true";
				
			}
		}catch(Exception e){
			msg = "Se ha producido un inconveniente, recargue la pagina y vuelva a intentarlo";
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
		}finally{
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		return null;
	}
	
	public void EditarDatosUsuario(){
		
		boolean emailCorrecto;
        emailCorrecto=usuarioM.getCorreo().matches("^[A-Za-z0-9-]+([_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        
		try{
			if(usuarioM.getTipoUsuario().equals("")){
				msg = "Seleccione tipo de usuario";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioM.getNombres().equals("") || usuarioM.getNombres().length()<2){
				msg = "Ingrese Nombres del usuario correctamente";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioM.getApellidos().equals("") || usuarioM.getApellidos().length()<2){
				msg = "Ingrese Apellidos del usuario correctamente";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioM.getDni().equals("") || usuarioM.getDni().length()<8){
				msg = "Ingrese DNI del usuario, 8 digitos";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioM.getDireccionResidencia().equals("") || usuarioM.getDireccionResidencia().length()<10){
				msg = "Ingrese Residencia del usuario";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioM.getTelefonoFijo().equals("") || usuarioM.getTelefonoFijo().length()<7){
				msg = "Ingrese un telefono fijo, 7 digitos";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioM.getTelefonoMovil().equals("") || usuarioM.getTelefonoMovil().length()<9){
				msg = "Telefono movil, 9 digitos";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioM.getCorreo().equals("")){
				msg = "Ingrese un correo electronico ejm: micorreo@hot.es";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(emailCorrecto == false){
				msg = "El correo tiene formato incorrecto, no se admiten guiones ni puntos al inicio";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioM.getUsuario().equals("")){
				msg = "Ingrese un usuario";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioM.getPassword().equals("")){
				msg = "Ingrese un password";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioM.getFechaNac() == null){
				msg = "Ingrese Fecha Nacimiento del usuario";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioM.getSexo().equals("")){
				msg = "Seleccione Sexo";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioM.getEstadoCivil().equals("")){
				msg = "Seleccione EstadoCivil";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(usuarioM.getEstadoCivil().equals("CASADO") && usuarioM.getNombreConyuge().equals("")){
				msg = "Ingrese nombre de conyuge";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else{
				dao.Editar(usuarioM);
			}
		
		}catch(Exception e){
			msg = "Se ha producido un inconveniente";
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
		}finally{
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		
	}
	
	public void buscarSocio(){
		
		if(tipoBusUsu.equals("0")){
			msg = "Seleccione tipo de busqueda";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
		}else if(tipoBusUsu.equals("1")){
				
			if(tipoCriterio.equals("")){
				msg = "Ingrese Nombre y apellido del Usuario";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else{

				usuarioM = dao.buscarUsuarioN(tipoCriterio);
				
				if(usuarioM!=null){
						int id = usuarioM.getUbigeo().getIdUbigeo();
						Ubigeo u = usuarioM.getUbigeo();
						u = daoU.buscarUbigeo(id);
					
						departamento2 = u.getDepartamento();
						provincia2 = u.getProvincia();
						distrito2 = u.getDistrito();
						
						firma = usuarioM.getMostrarFirmaUsuario();
						foto = usuarioM.getMostrarFotoUsuario();
						confirmar = usuarioM.getPassword();
						
						msg = "Usuario Número: " + usuarioM.getIdUsuario() + " - " + usuarioM.getNombres() + " " + usuarioM.getApellidos();
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
						//return "/pages/socio/modificar?faces-redirect=true";
					} else{

						msg = "No existe Usuario: "+tipoCriterio;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
						
					}
				}
			}else if(tipoBusUsu.equals("2")){
				
				if(tipoCriterio.equals("")){
					
					msg = "Ingrese DNI";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
					
				} else{
					usuarioM = dao.buscarDNI(tipoCriterio);
					if(usuarioM!=null){
						int id = usuarioM.getUbigeo().getIdUbigeo();
						Ubigeo u = usuarioM.getUbigeo();
						u = daoU.buscarUbigeo(id);
						
						departamento2 = u.getDepartamento();
						provincia2 = u.getProvincia();
						distrito2 = u.getDistrito();
						
						firma = usuarioM.getMostrarFirmaUsuario();
						foto = usuarioM.getMostrarFotoUsuario();
						
						msg = "Usuario Número: " + usuarioM.getIdUsuario() + " - " + usuarioM.getNombres() + " " + usuarioM.getApellidos();
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
						//return "/pages/seguridad/modificar-usuario?faces-redirect=true";
					} else{
						msg = "No existe Usuario con DNI: "+ tipoCriterio;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
					}
				}
			}
		/*
		} catch (Exception e) {
			msg = "Se ha producido un inconveniente";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			e.printStackTrace();
		}
		*/
	}
	
	public List<Usuario> getUsuarios() {
		usuarios = dao.Listado();
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public Usuario getUsuarioS() {
		return usuarioS;
	}
	public void setUsuarioS(Usuario usuarioS) {
		this.usuarioS = usuarioS;
	}

	public Usuario getUsuarioG() {
		return usuarioG;
	}
	public void setUsuarioG(Usuario usuarioG) {
		this.usuarioG = usuarioG;
	}

	public Usuario getUsuarioM() {
		return usuarioM;
	}
	public void setUsuarioM(Usuario usuarioM) {
		this.usuarioM = usuarioM;
	}
	
	public String getFotoEn64() {
		return fotoEn64;
	}
	public void setFotoEn64(String fotoEn64) {
		this.fotoEn64 = fotoEn64;
	}

	public String getFotoEn642() {
		return fotoEn642;
	}
	public void setFotoEn642(String fotoEn642) {
		this.fotoEn642 = fotoEn642;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getFirma() {
		return firma;
	}
	public void setFirma(String firma) {
		this.firma = firma;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public void listarProvincia(){
		listaProvincias = daoU.listarProvincia(departamento);
	}

	public void listarDistrito(){
		listaDistritos = daoU.listarDistrito(provincia);
	}
	
	public List<Ubigeo> getListaDepartamentos() {
		listaDepartamentos = daoU.listaDepartamento();
		return listaDepartamentos;
	}
	public void setListaDepartamentos(List<Ubigeo> listaDepartamentos) {
		this.listaDepartamentos = listaDepartamentos;
	}
	public List<Ubigeo> getListaProvincias() {
		return listaProvincias;
	}
	public void setListaProvincias(List<Ubigeo> listaProvincias) {
		this.listaProvincias = listaProvincias;
	}
	public List<Ubigeo> getListaDistritos() {
		return listaDistritos;
	}
	public void setListaDistritos(List<Ubigeo> listaDistritos) {
		this.listaDistritos = listaDistritos;
	}
	
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDepartamento2() {
		return departamento2;
	}
	public void setDepartamento2(String departamento2) {
		this.departamento2 = departamento2;
	}
	public String getProvincia2() {
		return provincia2;
	}
	public void setProvincia2(String provincia2) {
		this.provincia2 = provincia2;
	}
	public String getDistrito2() {
		return distrito2;
	}
	public void setDistrito2(String distrito2) {
		this.distrito2 = distrito2;
	}
	
	public String getConfirmar() {
		return confirmar;
	}
	public void setConfirmar(String confirmar) {
		this.confirmar = confirmar;
	}

	public String getTipoBusUsu() {
		return tipoBusUsu;
	}
	public void setTipoBusUsu(String tipoBusUsu) {
		this.tipoBusUsu = tipoBusUsu;
	}
	public String getTipoCriterio() {
		return tipoCriterio;
	}
	public void setTipoCriterio(String tipoCriterio) {
		this.tipoCriterio = tipoCriterio;
	}
	public String getTipoActualizacion() {
		return tipoActualizacion;
	}
	public void setTipoActualizacion(String tipoActualizacion) {
		this.tipoActualizacion = tipoActualizacion;
	}
	
}