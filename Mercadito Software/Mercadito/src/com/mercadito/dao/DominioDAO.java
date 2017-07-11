package com.mercadito.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import bean.Dominio;

public class DominioDAO {
	EntityManagerFactory emf=null;
	EntityManager em=null;

	@SuppressWarnings("unchecked")
	public List<Dominio> ListadoPasajes(){
		List<Dominio> lista=null;
		try {
			Open();
			Query q= em.createQuery("select d from Dominio d where d.tabla = 'establecimiento' and d.tipo = 'pasaje'");
			lista = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Close();
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Dominio> ListadoTipos(){
		List<Dominio> lista=null;
		try {
			Open();
			Query q= em.createQuery("select d from Dominio d where d.tabla = 'establecimiento' and d.tipo = 'tipo'");
			lista = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Close();
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Dominio> ListadoNumeros(){
		List<Dominio> lista=null;
		try {
			Open();
			Query q= em.createQuery("select d from Dominio d where d.tabla = 'establecimiento' and d.tipo = 'numero'");
			lista = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Close();
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Dominio> ListadoSexo(){
		List<Dominio> lista=null;
		try {
			Open();
			Query q= em.createQuery("select d from Dominio d where d.tabla = 'sexo' and d.tipo = 'tipo'");
			lista = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Close();
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Dominio> ListadoEstadoCivil(){
		List<Dominio> lista=null;
		try {
			Open();
			Query q= em.createQuery("select d from Dominio d where d.tabla = 'estado_civil' and d.tipo = 'tipo'");
			lista = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Close();
		}
		return lista;
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
