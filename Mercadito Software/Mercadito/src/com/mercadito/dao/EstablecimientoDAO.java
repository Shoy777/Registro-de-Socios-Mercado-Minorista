package com.mercadito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.Establecimiento;

public class EstablecimientoDAO {
	
	EntityManagerFactory emf=null;
	@PersistenceContext
	EntityManager em=null;
	
	//REGISTRAR ESTABLECIMIENTO
	public void Grabar(Establecimiento obj){
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
	
	//MODIFICAR ESTABLECIMIENTO
	public void Editar(Establecimiento obj){
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
	
	//BUSCAR ESTABLECIMIENTO
	public Establecimiento buscarEstablecimiento(String pasaje, String tipo, String numero) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		Establecimiento e = null;
		ResultSet rs = null;
		try {
			new Conexion();
			conn = Conexion.getConexion();
			String sql = "select * from establecimiento where pasaje=? and tipo=? and numero=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, pasaje);
			pstm.setString(2, tipo);
			pstm.setString(3, numero);
			rs = pstm.executeQuery();
			while (rs.next()) {
				e = new Establecimiento();
				e.setIdEstablecimiento(rs.getInt("idEstablecimiento"));
				e.setPasaje(rs.getString("pasaje"));
				e.setTipo(rs.getString("tipo"));
				e.setNumero(rs.getString("numero"));
				e.setPrecioAlquiler(rs.getDouble("precio_alquiler"));
				e.setGiroNegocio(rs.getString("giro_negocio"));
				e.setEstadoEst(rs.getString("estado_est"));
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
		return e;
	}
	
	//LISTADO DE ESTABLECIMIENTOS
	@SuppressWarnings("unchecked")
	public List<Establecimiento> Listado(){
		List<Establecimiento> lista=null;
		try {
			Open();
			Query q= 
			em.createQuery("select e from Establecimiento e where e.estadoEst='DISPONIBLE'");
			lista = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Close();
		}
		return lista;
	}
	
	//VERIFICAR ESTABLECIMIENTO
	public int verificarEstablecimiento(String pasaje, String tipo, String numero) {
		int cod = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			new Conexion();
			conn = Conexion.getConexion();
			String sql = "select * from establecimiento where pasaje=? and tipo=? and numero=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, pasaje);
			pstm.setString(2, tipo);
			pstm.setString(3, numero);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				rs.getString("pasaje");
				rs.getString("tipo");
				rs.getString("numero");
				rs.getDouble("precio_alquiler");
				cod = rs.getInt("idEstablecimiento");
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
	
	public Establecimiento Buscar(int id){
		Establecimiento obj=null;
		try {
			Open();		
			obj = em.find(Establecimiento.class, id);			
		} catch (Exception e) {
			e.printStackTrace();	
		}finally{
			Close();
		}
		return obj;		
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
