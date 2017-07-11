package bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dominio database table.
 * 
 */
@Entity
@NamedQuery(name="Dominio.findAll", query="SELECT d FROM Dominio d")
public class Dominio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idDominio;

	private String descripcion;

	private String tabla;

	private String tipo;

	public Dominio() {
	}

	public int getIdDominio() {
		return this.idDominio;
	}

	public void setIdDominio(int idDominio) {
		this.idDominio = idDominio;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTabla() {
		return this.tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}