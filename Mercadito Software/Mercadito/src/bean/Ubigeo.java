package bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ubigeo database table.
 * 
 */
@Entity
@NamedQuery(name="Ubigeo.findAll", query="SELECT u FROM Ubigeo u")
public class Ubigeo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idUbigeo;

	private String departamento;

	private String distrito;

	private String provincia;

	//bi-directional many-to-one association to Socio
	@OneToMany(mappedBy="ubigeo")
	private List<Socio> socios;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="ubigeo")
	private List<Usuario> usuarios;

	public Ubigeo() {
	}

	public int getIdUbigeo() {
		return this.idUbigeo;
	}

	public void setIdUbigeo(int idUbigeo) {
		this.idUbigeo = idUbigeo;
	}

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDistrito() {
		return this.distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public List<Socio> getSocios() {
		return this.socios;
	}

	public void setSocios(List<Socio> socios) {
		this.socios = socios;
	}

	public Socio addSocio(Socio socio) {
		getSocios().add(socio);
		socio.setUbigeo(this);

		return socio;
	}

	public Socio removeSocio(Socio socio) {
		getSocios().remove(socio);
		socio.setUbigeo(null);

		return socio;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setUbigeo(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setUbigeo(null);

		return usuario;
	}

}