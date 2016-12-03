/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radardgt.iteracion4.pagosancion.dominio;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.radardgt.iteracion4.pagosancion.persistencia.ConexionDB;

/*********************************************************************
* Class Name: GestorVehiculo
* Author/s name: Carlos
* Release/Creation date: 5/11/2016
* Class version: 1.2.0
* Class description: Clase que gestionara todas las acciones relacionadas
* 	con vehiculos
**********************************************************************
*/

public class GestorVehiculo {

	/**
	 * Metodo que actualizara la bbdd con el nuevo propietario del vehiculo pasado
	 * @param prop
	 * @param coche
	 * @param bd
	 */
	public void cambiarPropietario(Propietario prop, Vehiculo coche,ConexionDB bd){
		/*Estas variables seran sustituidas cuando se tenga la BBDD diseñada*/
		String nombreTabla="PROPIETARIOS_VEHICULOS", columna ="Dni_propietario";
		String sentencia="update "+nombreTabla+" set "+columna+"="+prop.getId()+
				" where Matricula_vehiculo ="+coche.getMatricula();
		bd.ejecutarConsulta(sentencia);
		bd.commit();
	}
	/**
	 * Metodo que devuelve un vehiculo segun la matricula pasada
	 * devuelve null en caso de error.
	 * @param matricula
	 * @param bd
	 * @return
	 */
	public Vehiculo obtenerVehiculo(String matricula, ConexionDB bd){
		Vehiculo coche = null;
		/*Estas variables seran sustituidas cuando se tenga la BBDD diseñada*/
		String nombretabla="VEHICULOS", colmatricula ="Matricula";
		String sentencia ="select * from "+nombretabla+" where "+colmatricula+"="+matricula;
		bd.ejecutarConsulta(sentencia);
		ResultSet result = bd.getResultSet();
		try {
			result.next();
			coche = new Vehiculo(result.getInt(1),result.getString(2), result.getString(3).charAt(0));
		} catch (SQLException e) {
			System.out.println("ERROR --> "+e.getMessage());
		}
		return coche;
	}
	public static Vehiculo obtenerVehiculo(Propietario prop, ConexionDB bd){
		Vehiculo coche = null;
		/*Estas variables seran sustituidas cuando se tenga la BBDD diseñada*/
		String sentencia = "SELECT * FROM VEHICULOS WHERE Matricula ="+
				"(SELECT Matricula_vehiculo FROM PROPIETARIOS_VEHICULOS WHERE Dni_propietario = "+prop.getDni()+")";
		bd.ejecutarConsulta(sentencia);
		ResultSet result = bd.getResultSet();
		try {
			result.next();
			coche = new Vehiculo(result.getInt(1),result.getString(2), result.getString(3).charAt(0));
		} catch (SQLException e) {
			System.out.println("ERROR --> "+e.getMessage());
		}
		return coche;
	}
}