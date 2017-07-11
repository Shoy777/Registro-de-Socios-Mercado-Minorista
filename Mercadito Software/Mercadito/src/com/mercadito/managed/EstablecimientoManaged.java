package com.mercadito.managed;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.mercadito.dao.EstablecimientoDAO;

import bean.Establecimiento;

@ViewScoped
@ManagedBean(name="establecimientomanaged")
public class EstablecimientoManaged {
	
	private List<Establecimiento> establecimientos;
	private Establecimiento establecimiento = new Establecimiento();
	String numero = "", pasaje = "", tipo = "", msg = "";
	double precio = 0.0;
	
	private String pasajeB, tipoB, numeroB;
	
	EstablecimientoDAO dao = new EstablecimientoDAO();
	
	public String Grabar(){
		
		instanciar();
		int buscarEstablecimiento = dao.verificarEstablecimiento(pasaje,tipo,numero);
		
		try {
			if(pasaje.equals("")){
				msg = "Seleccione un Pasaje";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(tipo.equals("")){
				msg = "Seleccione un Tipo de Establecimiento";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(numero.equals("")){
				msg = "Seleccione un numero de establecimiento";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			}else if(precio == 0 || precio == 0.0 || precio == 0.00 || String.valueOf(precio).equals("")){
				msg = "Ingrese precio para establecimiento";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			} else if(precio <= 500){
				msg = "Ingrese un precio correcto para el establecimiento";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			} else{
				if(buscarEstablecimiento==0){
					msg = "Registro Grabado";
					dao.Grabar(establecimiento);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
					return "registrar?faces-redirect=true";
				}else{
					msg = "El establecimiento ya ha sido registrado anteriormente";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				}
			}
			
		} catch (Exception e) {
			msg = "Se ha producido un inconveniente";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
		} finally{
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		return null;
	}
	
	public void buscarEstablecimiento(){
		
		establecimiento = dao.buscarEstablecimiento(pasajeB, tipoB, numeroB);
		
		if(pasajeB.equals("")){
			msg = "Seleccione Pasaje";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
		} else if(tipoB.equals("")){
			msg = "Seleccione Tipo";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
		} else if(numeroB.equals("")){
			msg = "Seleccione Numero";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
		} else if(establecimiento==null){
			msg = "El establecimiento que está buscando aún no ha sido registrado";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			pasajeB = null;tipoB = null;numeroB = null;
		}else {
			msg = "Establecimiento Número " + establecimiento.getIdEstablecimiento();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			pasajeB = null;tipoB = null;numeroB = null;
		}
	}
	
	public String Editar(){
		instanciar();
		int buscarEstablecimiento = dao.verificarEstablecimiento(pasaje,tipo,numero);
		try {
			if(buscarEstablecimiento==0){
				msg = "Búsque un establecimiento "+establecimiento.getIdEstablecimiento();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			} else if(String.valueOf(precio).equals("")){
				msg = "Ingrese precio para establecimiento";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			} else if(precio <= 500){
				msg = "Ingrese un precio correcto para el establecimiento número "+ establecimiento.getIdEstablecimiento();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
			} else{
				msg = "Establecimiento Modificado";
				dao.Editar(establecimiento);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
				return "modificar?faces-redirect=true";
			}
		} catch (Exception e) {
			msg = "Se ha producido un inconveniente";
			FacesContext.getCurrentInstance().addMessage(null,
			new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
		}
		finally{
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		return null;
	}
	
	public String Buscar(){
		establecimiento = dao.Buscar(establecimiento.getIdEstablecimiento());
		return "pages/establecimiento/modificar?faces-redirect=true";
	}
	
	public void instanciar(){
		tipo = establecimiento.getTipo();
		pasaje = establecimiento.getPasaje();
		numero = establecimiento.getNumero();
		precio = establecimiento.getPrecioAlquiler();
	}
	
	private Boolean check = false;
	public Boolean getCheck() {
		return check;
	}
	
	public void setEstablecimientos(List<Establecimiento> establecimientos) {
		this.establecimientos = establecimientos;
	}
	public List<Establecimiento> getEstablecimientos() {
		establecimientos = dao.Listado();
		return establecimientos;
	}
	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}
	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}
	
	public String getPasajeB() {
		return pasajeB;
	}

	public void setPasajeB(String pasajeB) {
		this.pasajeB = pasajeB;
	}

	public String getTipoB() {
		return tipoB;
	}

	public void setTipoB(String tipoB) {
		this.tipoB = tipoB;
	}

	public String getNumeroB() {
		return numeroB;
	}

	public void setNumeroB(String numeroB) {
		this.numeroB = numeroB;
	}
	
	private String GIRO;

	public String getGIRO() {
		return GIRO;
	}

	public void setGIRO(String gIRO) {
		GIRO = gIRO;
	}
	
}