package com.mercadito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Ubigeo;

public class UbigeoDAO {
	
	public ArrayList<Ubigeo> listaDepartamento() {
		ArrayList<Ubigeo> collection = new ArrayList<Ubigeo>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cn = Conexion.getConexion();
			ps = cn.prepareStatement("select distinct departamento from ubigeo");
			rs = ps.executeQuery();
			while ( rs.next() ) {
				Ubigeo x = new Ubigeo();
				x.setDepartamento(rs.getString(1));
				collection.add(x);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if ( cn != null ) cn.close();
				if ( ps != null ) ps.close();
				if ( rs != null ) rs.close();
			}
			catch (Exception e2) { e2.printStackTrace(); }
		}
		return collection;
	}
	
	public List<Ubigeo> listarProvincia(String departamento ){
		ArrayList<Ubigeo> data = new ArrayList<Ubigeo>();
	
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet  rs = null;
		try {
			new Conexion();
			con = Conexion.getConexion();
			
			String sql ="select distinct provincia from ubigeo where departamento like ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, departamento + "%");
			rs = pstm.executeQuery();
			Ubigeo mat = null;
			while(rs.next()){
				mat = new Ubigeo();
				mat.setProvincia(rs.getString("provincia"));
				
				data.add(mat);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
	public List<Ubigeo> listarDistrito(String provincia ){
		ArrayList<Ubigeo> data = new ArrayList<Ubigeo>();
	
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet  rs = null;
		try {
			new Conexion();
			con = Conexion.getConexion();
			
			String sql ="select distinct distrito, idubigeo from ubigeo where provincia like ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, provincia + "%");
			rs = pstm.executeQuery();
			Ubigeo mat = null;
			while(rs.next()){
				mat = new Ubigeo();
				mat.setDistrito(rs.getString("distrito"));
				mat.setIdUbigeo(rs.getInt("idUbigeo"));
				
				data.add(mat);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}

	public Ubigeo buscarUbigeo(int id) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Ubigeo c = null;
		
		try {
			new Conexion();
			con = Conexion.getConexion();
			String sql ="select * from ubigeo where idUbigeo=?";  
			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			System.out.println("---> " + pstm);
			
			while(rs.next()){
				c = new Ubigeo();
				c.setIdUbigeo(rs.getInt("idUbigeo"));
				c.setDistrito(rs.getString("distrito"));
				c.setProvincia(rs.getString("provincia"));
				c.setDepartamento(rs.getString("departamento"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}
	
}