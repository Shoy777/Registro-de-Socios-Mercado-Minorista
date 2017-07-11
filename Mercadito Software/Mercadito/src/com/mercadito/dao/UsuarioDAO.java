package com.mercadito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import bean.Usuario;

public class UsuarioDAO {
	EntityManagerFactory emf=null;
	EntityManager em=null;
	
	@SuppressWarnings("static-access")
	public Usuario valida(String login, String clave){
		Usuario bean = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = new Conexion().getConexion();
			String sql ="select * from usuario where usuario=? and password =?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, login);
			pstm.setString(2, clave);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				bean = new Usuario();

				bean.setTipoUsuario(rs.getString("tipo_usuario"));
				bean.setIdUsuario(rs.getInt("idUsuario"));
				bean.setNombres(rs.getString("nombres"));
				bean.setApellidos(rs.getString("apellidos"));
				bean.setDni(rs.getString("dni"));
				bean.setDireccionResidencia(rs.getString("direccion_residencia"));
				bean.setTelefonoFijo(rs.getString("telefono_fijo"));
				bean.setTelefonoMovil(rs.getString("telefono_movil"));
				bean.setCorreo(rs.getString("correo"));
				bean.setFechaNac(rs.getDate("fecha_nac"));
				bean.setSexo(rs.getString("sexo"));
				bean.setEstadoCivil(rs.getString("estado_civil"));
				bean.setNombreConyuge(rs.getString("nombre_conyuge"));
				bean.getUbigeo().setIdUbigeo(rs.getInt("idUbigeo"));
				bean.setFotoUsuario(rs.getBytes("foto_usuario"));
				bean.setFirmaUsuario(rs.getBytes("firma_usuario"));
				bean.setUsuario(rs.getString("usuario"));
				bean.setPassword(rs.getString("password"));
				bean.setFechaReg(rs.getDate("fecha_reg"));
				bean.setEstadoUsuario(rs.getString("estado_usuario"));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally{
			try {
				conn.close();
				pstm.close();
			} catch (SQLException e) {
			}
		}
		return bean;
	}
	
	public String Grabar(Usuario obj){
		String result="";
		try {
			Open();
			em.getTransaction().begin();
			em.persist(obj);
			result="Registro Grabado";
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			result = e.getMessage();
		}finally{
			Close();
		}
		return result;		
	}
	
	public String Editar(Usuario obj){
		String result="";
		try {
			Open();
			em.getTransaction().begin();
			em.merge(obj);
			result="Registro Actualizado";
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			result = e.getMessage();
		}finally{
			Close();
		}
		return result;
	}
	public String Eliminar(String id){
		String result="";
		try {
			Open();
			em.getTransaction().begin();
			Usuario obj = em.find(Usuario.class, id);
			if(obj!=null){
				em.remove(obj);
				result="Registro Eliminado";
			}else{
				result="No se encontro";
			}			
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			result = e.getMessage();
		}finally{
			Close();
		}
		return result;		
	}
	public Usuario Buscar(int id){
		Usuario obj=null;
		try {
			Open();		
			obj = em.find(Usuario.class, id);			
		} catch (Exception e) {
			e.printStackTrace();	
		}finally{
			Close();
		}
		return obj;		
	}
	@SuppressWarnings("unchecked")
	public List<Usuario> Listado(){
		List<Usuario> lista=null;
		try {
			Open();
			Query q= em.createQuery("select u from Usuario u");
			lista = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Close();
		}
		return lista;
	}
	
	public Usuario buscarUsuario(String nombres, String apellidos, String dni) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		Usuario bean = null;
		ResultSet rs = null;
		try {
			new Conexion();
			conn = Conexion.getConexion();
			String sql = "select * from usuario where nombres=? and apellidos=? and dni=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nombres);
			pstm.setString(2, apellidos);
			pstm.setString(3, dni);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new Usuario();
				
				bean.setTipoUsuario(rs.getString("tipo_usuario"));
				bean.setIdUsuario(rs.getInt("idUsuario"));
				bean.setNombres(rs.getString("nombres"));
				bean.setApellidos(rs.getString("apellidos"));
				bean.setDni(rs.getString("dni"));
				bean.setDireccionResidencia(rs.getString("direccion_residencia"));
				bean.setTelefonoFijo(rs.getString("telefono_fijo"));
				bean.setTelefonoMovil(rs.getString("telefono_movil"));
				bean.setCorreo(rs.getString("correo"));
				bean.setFechaNac(rs.getDate("fecha_nac"));
				bean.setSexo(rs.getString("sexo"));
				bean.setEstadoCivil(rs.getString("estado_civil"));
				bean.setNombreConyuge(rs.getString("nombre_conyuge"));
				bean.getUbigeo().setIdUbigeo(rs.getInt("idUbigeo"));
				bean.setFotoUsuario(rs.getBytes("foto_usuario"));
				bean.setFirmaUsuario(rs.getBytes("firma_usuario"));
				bean.setUsuario(rs.getString("usuario"));
				bean.setPassword(rs.getString("password"));
				bean.setFechaReg(rs.getDate("fecha_reg"));
				bean.setEstadoUsuario(rs.getString("estado_usuario"));
			}
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return bean;
	}
	
	public Usuario buscarUsuarioN(String nombresCompleto) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		Usuario bean = null;
		ResultSet rs = null;
		try {
			new Conexion();
			conn = Conexion.getConexion();
			String sql = "select * from usuario where CONCAT_WS(' ',nombres,apellidos) LIKE ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%"+nombresCompleto+"%");
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new Usuario();
				bean.setTipoUsuario(rs.getString("tipo_usuario"));
				bean.setIdUsuario(rs.getInt("idUsuario"));
				bean.setNombres(rs.getString("nombres"));
				bean.setApellidos(rs.getString("apellidos"));
				bean.setDni(rs.getString("dni"));
				bean.setDireccionResidencia(rs.getString("direccion_residencia"));
				bean.setTelefonoFijo(rs.getString("telefono_fijo"));
				bean.setTelefonoMovil(rs.getString("telefono_movil"));
				bean.setCorreo(rs.getString("correo"));
				bean.setFechaNac(rs.getDate("fecha_nac"));
				bean.setSexo(rs.getString("sexo"));
				bean.setEstadoCivil(rs.getString("estado_civil"));
				bean.setNombreConyuge(rs.getString("nombre_conyuge"));
				bean.getUbigeo().setIdUbigeo(rs.getInt("idUbigeo"));
				bean.setFotoUsuario(rs.getBytes("foto_usuario"));
				bean.setFirmaUsuario(rs.getBytes("firma_usuario"));
				bean.setUsuario(rs.getString("usuario"));
				bean.setPassword(rs.getString("password"));
				bean.setFechaReg(rs.getDate("fecha_reg"));
				bean.setEstadoUsuario(rs.getString("estado_usuario"));
			}
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return bean;
	}
	
	public Usuario buscarDNI(String dni) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		Usuario bean = null;
		ResultSet rs = null;
		try {
			new Conexion();
			conn = Conexion.getConexion();
			String sql = "select * from usuario where dni=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, dni);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new Usuario();
				
				bean.setTipoUsuario(rs.getString("tipo_usuario"));
				bean.setIdUsuario(rs.getInt("idUsuario"));
				bean.setNombres(rs.getString("nombres"));
				bean.setApellidos(rs.getString("apellidos"));
				bean.setDni(rs.getString("dni"));
				bean.setDireccionResidencia(rs.getString("direccion_residencia"));
				bean.setTelefonoFijo(rs.getString("telefono_fijo"));
				bean.setTelefonoMovil(rs.getString("telefono_movil"));
				bean.setCorreo(rs.getString("correo"));
				bean.setFechaNac(rs.getDate("fecha_nac"));
				bean.setSexo(rs.getString("sexo"));
				bean.setEstadoCivil(rs.getString("estado_civil"));
				bean.setNombreConyuge(rs.getString("nombre_conyuge"));
				bean.getUbigeo().setIdUbigeo(rs.getInt("idUbigeo"));
				bean.setFotoUsuario(rs.getBytes("foto_usuario"));
				bean.setFirmaUsuario(rs.getBytes("firma_usuario"));
				bean.setUsuario(rs.getString("usuario"));
				bean.setPassword(rs.getString("password"));
				bean.setFechaReg(rs.getDate("fecha_reg"));
				bean.setEstadoUsuario(rs.getString("estado_usuario"));
			}
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return bean;
	}
	
	public int verificarUsuario(String nombres, String apellidos, String dni){
		int cod = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = Conexion.getConexion();
			String sql = "select * from usuario where nombres=? and apellidos=? and dni=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nombres);
			pstm.setString(2, apellidos);
			pstm.setString(3, dni);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				rs.getString("nombres");
				rs.getString("apellidos");
				rs.getString("dni");
				cod = rs.getInt("idUsuario");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cod;
	}
	
	public int verificarUsuario(String nombres, String apellidos){
		int cod = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = Conexion.getConexion();
			String sql = "select * from usuario where nombres=? and apellidos=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nombres);
			pstm.setString(2, apellidos);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				rs.getString("nombres");
				rs.getString("apellidos");
				rs.getString("dni");
				cod = rs.getInt("idUsuario");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cod;
	}
	
	public int verificarUsuario(String dni){
		int cod = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = Conexion.getConexion();
			String sql = "select * from usuario where dni=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, dni);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				rs.getString("nombres");
				rs.getString("apellidos");
				rs.getString("dni");
				cod = rs.getInt("idUsuario");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cod;
	}
	
	private void Open(){
		emf=Persistence.
		createEntityManagerFactory("Mercadito");
		em = emf.createEntityManager();
	}
	private void Close(){
		em.close();
		emf.close();
	}

}
