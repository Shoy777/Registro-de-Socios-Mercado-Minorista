package com.mercadito.dao;

import java.sql.DriverManager;

import java.sql.Connection;

public class Conexion {
	public static Connection getConexion(){
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/mercadito","root","mysql");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}