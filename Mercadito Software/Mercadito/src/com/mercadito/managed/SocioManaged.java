package com.mercadito.managed;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.binary.Base64;

import com.mercadito.dao.SocioDAO;
import com.mercadito.dao.UbigeoDAO;

import bean.Socio;
import bean.Ubigeo;

@ViewScoped
@ManagedBean(name="sociomanaged")
public class SocioManaged {
	
	private List<Socio> socios;
	private Socio socio = new Socio();

	private String fotoEn64;
	private String fotoEn642;
	
	private String firma = "/bootstrap/img/firma.png", foto = "/bootstrap/img/masculino.jpg";
	private String tipoBusSocio, tipoCriterio, tipoActualizacion;
	SocioDAO dao = new SocioDAO();
	String msg ="";
	
	private List<Ubigeo> listaDepartamentos;
	private List<Ubigeo> listaProvincias;
	private List<Ubigeo> listaDistritos;

	private String departamento;
	private String distrito;
	private String provincia;
	
	UbigeoDAO daoU = new UbigeoDAO();
	private String departamento2, provincia2, distrito2;
	
	//METODO REGISTRAR SOCIO
	public String Grabar(){
		
		int buscarSocio1 = dao.verificarSocio(socio.getNombres(), socio.getApellidos());
		int buscarSocio2 = dao.verificarSocio(socio.getDni());
		
		boolean emailCorrecto;
        emailCorrecto=socio.getCorreo().matches("^[A-Za-z0-9-]+([_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        
        try{
			if(socio.getNombres().equals("") || socio.getNombres().length()<2){
				msg = "Ingrese Nombres del socio correctamente";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getApellidos().equals("") || socio.getApellidos().length()<2){
				msg = "Ingrese Apellidos del socio correctamente";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getDni().equals("") || socio.getDni().length()<8){
				msg = "Ingrese DNI del socio, 8 digitos";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getDireccionResidencia().equals("") || socio.getDireccionResidencia().length()<10){
				msg = "Ingrese Residencia del socio";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getTelefonoFijo().equals("") || socio.getTelefonoFijo().length()<7){
				msg = "Ingrese un telefono fijo, 7 digitos";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getTelefonoMovil().equals("") || socio.getTelefonoMovil().length()<9){
				msg = "Telefono movil, 9 digitos";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getCorreo().equals("")){
				msg = "Ingrese un correo electronico ejm: micorreo@hot.es";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(emailCorrecto == false){
				msg = "El correo tiene formato incorrecto, no se admiten guiones ni puntos al inicio";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getFechaNac() == null){
				msg = "Ingrese Fecha Nacimiento del socio";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getSexo().equals("")){
				msg = "Seleccione Sexo";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getEstadoCivil().equals("")){
				msg = "Seleccione EstadoCivil";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getEstadoCivil().equals("CASADO") && socio.getNombreConyuge().equals("")){
				msg = "Ingrese nombre de conyuge";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(departamento.equals("")){
				msg = "Seleccione Departamento";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(provincia.equals("")){
				msg = "Seleccione Provincia";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getUbigeo().getIdUbigeo()==0){
				msg = "Seleccione Distrito";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getFechaIns() == null){
				msg = "Ingrese Fecha Ingreso del socio";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(fotoEn64.equals("")){
				msg = "Dibuje Firma del socio";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(fotoEn642.equals("")){
				msg = "Tome Foto del socio";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else{
				if(buscarSocio1 != 0){
					msg = "Ya existe socio con este nombre y apellido";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				}else if(buscarSocio2 != 0){
					msg = "Ya existe socio con este DNI";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				}else{
					System.out.println("Antes de Cortar: " + fotoEn64);
					System.out.println("Antes de Cortar: " + fotoEn642);
					fotoEn64 = fotoEn64.substring(fotoEn64.indexOf(',')+1, fotoEn64.length()-1);
					fotoEn642 = fotoEn642.substring(fotoEn642.indexOf(',')+1, fotoEn642.length()-1);
					System.out.println("Despues de Cortar: " + fotoEn64);
					System.out.println("Despues de Cortar: " + fotoEn642);
					byte[] bytes = new Base64().decode(fotoEn64);
					socio.setFirmaSocio(bytes);
					byte[] bytes2 = new Base64().decode(fotoEn642);
					socio.setFotoSocio(bytes2);
					
					msg = "Registro de Socio Grabado";
					
					dao.Grabar(socio);
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
					return "registrar?faces-redirect=true";
					
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
	
	public String Editar(){
		try{
			if(tipoActualizacion.equals("0")){
				//NINGUNO
				msg = "Seleccione un tipo de actualizacion";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				
			}else if(tipoActualizacion.equals("1")){
				//EDITAR DATOS DE SOCIO
				EditarDatosSocio();
				msg = "Registro de Socio Actualizado con Exito";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(tipoActualizacion.equals("2")){
				//EDITAR FIRMA
				if(fotoEn64.equals("")){
					msg = "Dibuje Firma del socio";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				}else{
					System.out.println("Antes de Cortar: " + fotoEn64);
					fotoEn64 = fotoEn64.substring(fotoEn64.indexOf(',')+1, fotoEn64.length()-1);
					System.out.println("Despues de Cortar: " + fotoEn64);
					byte[] bytes = new Base64().decode(fotoEn64);
					socio.setFirmaSocio(bytes);
					
					EditarDatosSocio();
					msg = "Registro de Socio Actualizado con Exito";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
					return "modificar?faces-redirect=true";
				}
			}else if(tipoActualizacion.equals("3")){
				//EDITAR FOTO
				if(fotoEn642.equals("")){
					msg = "Tome Foto del socio";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				}else{
					System.out.println("Antes de Cortar: " + fotoEn642);
					fotoEn642 = fotoEn642.substring(fotoEn642.indexOf(',')+1, fotoEn642.length()-1);
					System.out.println("Despues de Cortar: " + fotoEn642);
					byte[] bytes = new Base64().decode(fotoEn642);
					socio.setFotoSocio(bytes);
					
					EditarDatosSocio();
					msg = "Registro de Socio Actualizado con Exito";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
					return "modificar?faces-redirect=true";
				}
			}else if(tipoActualizacion.equals("4")){
				//EDITAR FIRMA Y FOTO
				if(fotoEn64.equals("")){
					msg = "Dibuje Firma del socio";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				}else if(fotoEn642.equals("")){
					msg = "Tome Foto del socio";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				}else{
					System.out.println("Antes de Cortar: " + fotoEn64);
					System.out.println("Antes de Cortar: " + fotoEn642);
					fotoEn64 = fotoEn64.substring(fotoEn64.indexOf(',')+1, fotoEn64.length()-1);
					fotoEn642 = fotoEn642.substring(fotoEn642.indexOf(',')+1, fotoEn642.length()-1);
					System.out.println("Despues de Cortar: " + fotoEn64);
					System.out.println("Despues de Cortar: " + fotoEn642);
					byte[] bytes = new Base64().decode(fotoEn64);
					socio.setFirmaSocio(bytes);
					byte[] bytes2 = new Base64().decode(fotoEn642);
					socio.setFotoSocio(bytes2);
					
					EditarDatosSocio();
					msg = "Registro de Socio Actualizado con Exito";
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
					return "modificar?faces-redirect=true";
				}
				
			}else if(tipoActualizacion.equals("5")){
				//ELIMINAR SOCIO
				socio.setEstadoSocio("INACTIVO");
				EditarDatosSocio();
				msg = "Registro de Socio Actualizado con Exito";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				return "modificar?faces-redirect=true";
				
			}else if(tipoActualizacion.equals("6")){
				
			}
		}catch(Exception e){
			msg = "Se ha producido un inconveniente, recargue la pagina y vuelva a intentarlo";
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
		}finally{
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		return null;
	}
	
	public void EditarDatosSocio(){
		
		boolean emailCorrecto;
        emailCorrecto=socio.getCorreo().matches("^[A-Za-z0-9-]+([_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        
		try{
			if(socio.getNombres().equals("") || socio.getNombres().length()<2){
				msg = "Ingrese Nombres del socio correctamente";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getApellidos().equals("") || socio.getApellidos().length()<2){
				msg = "Ingrese Apellidos del socio correctamente";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getDni().equals("") || socio.getDni().length()<8){
				msg = "Ingrese DNI del socio, 8 digitos";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getDireccionResidencia().equals("") || socio.getDireccionResidencia().length()<10){
				msg = "Ingrese Residencia del socio";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getTelefonoFijo().equals("") || socio.getTelefonoFijo().length()<7){
				msg = "Ingrese un telefono fijo, 7 digitos";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getTelefonoMovil().equals("") || socio.getTelefonoMovil().length()<9){
				msg = "Telefono movil, 9 digitos";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getCorreo().equals("")){
				msg = "Ingrese un correo electronico ejm: micorreo@hot.es";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(emailCorrecto == false){
				msg = "El correo tiene formato incorrecto, no se admiten guiones ni puntos al inicio";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getFechaNac() == null){
				msg = "Ingrese Fecha Nacimiento del socio";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getSexo().equals("")){
				msg = "Seleccione Sexo";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getEstadoCivil().equals("")){
				msg = "Seleccione EstadoCivil";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getEstadoCivil().equals("CASADO") && socio.getNombreConyuge().equals("")){
				msg = "Ingrese nombre de conyuge";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(socio.getFechaIns() == null){
				msg = "Ingrese Fecha Ingreso del socio";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else{
				dao.Editar(socio);
			}
		
		}catch(Exception e){
			msg = "Se ha producido un inconveniente";
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
		}finally{
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		
	}
	
	//METODO EDITAR SOCIO
	public String EditarFirma(){
		
		System.out.println("Antes de Cortar: " + fotoEn64);
		
		fotoEn64 = fotoEn64.substring(fotoEn64.indexOf(',')+1, fotoEn64.length()-1);
		
		System.out.println("Despues de Cortar: " + fotoEn64);
		
		byte[] bytes = new Base64().decode(fotoEn64);
		socio.setFirmaSocio(bytes);
		
		msg = "Registro de Socio Modificado";

		dao.Editar(socio);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
		return "editar-firma?faces-redirect=true";
	}
	
	//BUSCAR SOCIO
	public void buscarSocio(){
		
		SocioDAO dao = new SocioDAO();
		
		//try {
			
			if(tipoBusSocio.equals("0")){
				msg = "Seleccione tipo de busqueda";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(tipoBusSocio.equals("1")){
				
				if(tipoCriterio.equals("")){
					msg = "Ingrese Nombre y apellido del Socio";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				}else{

					socio = dao.buscarSocioN(tipoCriterio);
					
					if(socio!=null){
						int id = socio.getUbigeo().getIdUbigeo();
						Ubigeo u = socio.getUbigeo();
						u = daoU.buscarUbigeo(id);
					
						departamento2 = u.getDepartamento();
						provincia2 = u.getProvincia();
						distrito2 = u.getDistrito();
						
						firma = socio.getMostrarFirmaSocio();
						foto = socio.getMostrarFotoSocio();
						
						msg = "Socio Número: " + socio.getIdSocio() + " - " + socio.getNombres() + " " + socio.getApellidos();
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
						//return "/pages/socio/modificar?faces-redirect=true";
					} else{

						msg = "No existe Socio: "+tipoCriterio;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
						
					}
				}
			}else if(tipoBusSocio.equals("2")){
				
				if(tipoCriterio.equals("")){
					
					msg = "Ingrese DNI";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
					
				} else{
					socio = dao.buscarSocio(tipoCriterio);
					if(socio!=null){
						int id = socio.getUbigeo().getIdUbigeo();
						Ubigeo u = socio.getUbigeo();
						u = daoU.buscarUbigeo(id);
						
						departamento2 = u.getDepartamento();
						provincia2 = u.getProvincia();
						distrito2 = u.getDistrito();
						
						firma = socio.getMostrarFirmaSocio();
						foto = socio.getMostrarFotoSocio();
						
						msg = "Socio Número: " + socio.getIdSocio() + " - " + socio.getNombres() + " " + socio.getApellidos();
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
						//return "/pages/socio/modificar?faces-redirect=true";
					} else{
						msg = "No existe Socio con DNI: "+ tipoCriterio;
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
	
	public void setSocios(List<Socio> Socios) {
		this.socios = Socios;
	}
	public List<Socio> getSocios() {
		socios = dao.Listado();
		return socios;
	}
	public void setSocio(Socio Socio) {
		this.socio = Socio;
	}
	public Socio getSocio() {
		return socio;
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
	
	public String getFirma() {
		return firma;
	}
	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
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

	public Date getMinAge() {
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.YEAR, -18);
	    return currentDate.getTime();
	}
	
	public Date getMaxAge() {
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.YEAR, -100);
		return currentDate.getTime();
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

	public String getTipoBusSocio() {
		return tipoBusSocio;
	}
	public void setTipoBusSocio(String tipoBusSocio) {
		this.tipoBusSocio = tipoBusSocio;
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
