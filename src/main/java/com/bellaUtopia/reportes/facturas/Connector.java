package com.bellaUtopia.reportes.facturas;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
	
	Connection conn;
	
	public Connector(){
		try{
			Class.forName("org.postgresql.Driver");
			//String url = "jdbc:postgresql://desa02.konecta.com.py:5532/mm";
	        //conn = DriverManager.getConnection(url,"postgres","mmMN18FPUNA");

			String url = "jdbc:postgresql://localhost:5432/bella_utopia";
	        conn = DriverManager.getConnection(url,"postgres","postgres");
		} catch (Exception e) {
	        System.err.println("Got an exception! ");
	        System.err.println(e.getMessage());
	    }
	}
	
	public Connection getConnection(){
		return conn;
	}
}