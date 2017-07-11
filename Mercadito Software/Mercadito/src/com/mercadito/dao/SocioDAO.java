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

import bean.Socio;

public class SocioDAO {
	
	EntityManagerFactory emf=null;
	EntityManager em=null;
	
	public void Grabar(Socio obj){
		try {
			Open();
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}finally{
			Close();
		}
	}
	
	public void Editar(Socio obj){
		try {
			Open();
			em.getTransaction().begin();
			em.merge(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}finally{
			Close();
		}
	}
	public String Eliminar(String id){
		String result="";
		try {
			Open();
			em.getTransaction().begin();
			Socio obj = em.find(Socio.class, id);
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
	public Socio Buscar(int id){
		Socio obj=null;
		try {
			Open();		
			obj = em.find(Socio.class, id);			
		} catch (Exception e) {
			e.printStackTrace();	
		}finally{
			Close();
		}
		return obj;		
	}
	
	@SuppressWarnings("unchecked")
	public List<Socio> Listado(){
		List<Socio> lista=null;
		try {
			Open();
			Query q= em.createQuery("select s from Socio s");
			lista = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Close();
		}
		return lista;
	}
	
	//BUSCAR SOCIO POR DNI
	public Socio buscarSocio(String dni) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		Socio bean = null;
		ResultSet rs = null;
		try {
			new Conexion();
			conn = Conexion.getConexion();
			String sql = "select * from socio where dni=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, dni);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new Socio();
				
				bean.setIdSocio(rs.getInt("idSocio"));
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
				bean.setFechaIns(rs.getDate("fecha_ins"));
				bean.setFechaReins(rs.getDate("fecha_reins"));
				bean.setFechaIng(rs.getDate("fecha_ing"));
				bean.setCargoAnterior(rs.getString("cargo_anterior"));
				bean.setFotoSocio(rs.getBytes("foto_socio"));
				bean.setFirmaSocio(rs.getBytes("firma_socio"));
				bean.setEstadoSocio(rs.getString("estado_socio"));
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
	
	//BUSCAR SOCIO POR NOMBRES Y APELLIDOS
	public Socio buscarSocioN(String nombresCompleto) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		Socio bean = null;
		ResultSet rs = null;
		try {
			new Conexion();
			conn = Conexion.getConexion();
			String sql = "select * from socio where CONCAT_WS(' ',nombres,apellidos) LIKE ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%"+nombresCompleto+"%");
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new Socio();
				
				bean.setIdSocio(rs.getInt("idSocio"));
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
				bean.setFechaIns(rs.getDate("fecha_ins"));
				bean.setFechaReins(rs.getDate("fecha_reins"));
				bean.setFechaIng(rs.getDate("fecha_ing"));
				bean.setCargoAnterior(rs.getString("cargo_anterior"));
				bean.setFotoSocio(rs.getBytes("foto_socio"));
				bean.setFirmaSocio(rs.getBytes("firma_socio"));
				bean.setEstadoSocio(rs.getString("estado_socio"));
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
	
	//BUSCAR SOCIO POR NOMBRES, APELLIDOS Y DNI
	public Socio buscarSocio(String nombres, String apellidos, String dni) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		Socio bean = null;
		ResultSet rs = null;
		try {
			new Conexion();
			conn = Conexion.getConexion();
			String sql = "select * from socio where nombres=? and apellidos=? and dni=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nombres);
			pstm.setString(2, apellidos);
			pstm.setString(3, dni);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new Socio();
				
				bean.setIdSocio(rs.getInt("idSocio"));
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
				bean.setFechaIns(rs.getDate("fecha_ins"));
				bean.setFechaReins(rs.getDate("fecha_reins"));
				bean.setFechaIng(rs.getDate("fecha_ing"));
				bean.setCargoAnterior(rs.getString("cargo_anterior"));
				bean.setFotoSocio(rs.getBytes("foto_socio"));
				bean.setFirmaSocio(rs.getBytes("firma_socio"));
				bean.setEstadoSocio(rs.getString("estado_socio"));
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
	
	//VERIFICAR SOCIO
	public int verificarSocio(String nombres, String apellidos, String dni){
		int cod = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = Conexion.getConexion();
			String sql = "select * from socio where nombres=? and apellidos=? and dni=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nombres);
			pstm.setString(2, apellidos);
			pstm.setString(3, dni);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				rs.getString("nombres");
				rs.getString("apellidos");
				rs.getString("dni");
				cod = rs.getInt("idSocio");
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
	
	public int verificarSocio(String nombres, String apellidos){
		int cod = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = Conexion.getConexion();
			String sql = "select * from socio where nombres=? and apellidos=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nombres);
			pstm.setString(2, apellidos);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				rs.getString("nombres");
				rs.getString("apellidos");
				rs.getString("dni");
				cod = rs.getInt("idSocio");
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
	
	public int verificarSocio(String dni){
		int cod = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = Conexion.getConexion();
			String sql = "select * from socio where dni=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, dni);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				rs.getString("nombres");
				rs.getString("apellidos");
				rs.getString("dni");
				cod = rs.getInt("idSocio");
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
