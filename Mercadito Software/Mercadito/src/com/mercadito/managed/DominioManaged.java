package com.mercadito.managed;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.mercadito.dao.DominioDAO;

import bean.Dominio;

@ViewScoped
@ManagedBean(name="dominiomanaged")
public class DominioManaged {
	
	private List<Dominio> listadoPasajes;
	private List<Dominio> listadoTipo;
	private List<Dominio> listadoNumero;
	private List<Dominio> listadoSexo;
	private List<Dominio> listadoEstadoCivil;
	
	public List<Dominio> getListadoPasajes() {
		DominioDAO dao = new DominioDAO();
		listadoPasajes = dao.ListadoPasajes();
		return listadoPasajes;
	}

	public void setListadoPasajes(List<Dominio> listadoPasajes) {
		this.listadoPasajes = listadoPasajes;
	}

	public List<Dominio> getListadoTipo() {
		DominioDAO dao = new DominioDAO();
		listadoTipo = dao.ListadoTipos();
		return listadoTipo;
	}

	public void setListadoTipo(List<Dominio> listadoTipo) {
		this.listadoTipo = listadoTipo;
	}

	public List<Dominio> getListadoNumero() {
		DominioDAO dao = new DominioDAO();
		listadoNumero = dao.ListadoNumeros();
		return listadoNumero;
	}

	public void setListadoNumero(List<Dominio> listadoNumero) {
		this.listadoNumero = listadoNumero;
	}

	public List<Dominio> getListadoSexo() {
		DominioDAO dao = new DominioDAO();
		listadoSexo = dao.ListadoSexo();
		return listadoSexo;
	}

	public void setListadoSexo(List<Dominio> listadoSexo) {
		this.listadoSexo = listadoSexo;
	}

	public List<Dominio> getListadoEstadoCivil() {
		DominioDAO dao = new DominioDAO();
		listadoEstadoCivil = dao.ListadoEstadoCivil();
		return listadoEstadoCivil;
	}

	public void setListadoEstadoCivil(List<Dominio> listadoEstadoCivil) {
		this.listadoEstadoCivil = listadoEstadoCivil;
	}

}
