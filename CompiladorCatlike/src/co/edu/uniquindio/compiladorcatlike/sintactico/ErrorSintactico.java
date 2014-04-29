/**
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * Universidad del Quindío (Armenia - Colombia)
 * Programa de Ingeniería de Sistemas y Computación
 *
 * Asignatura: 
 * Aplicacion: 
 * @author Johan Sebastian Quintero
 * @author Universidad del Quindio
 * @vesion 1.0
 * 06/04/2014
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 */
package co.edu.uniquindio.compiladorcatlike.sintactico;

import co.edu.uniquindio.compiladorcatlike.lexico.TokenCatlike;

/**
 * Clase que implementa ErrorSintactico.java
 */
public class ErrorSintactico {
	private String mensaje;
	private int indice;
	private TokenCatlike token;
	
	public ErrorSintactico(String mensaje, int indice, TokenCatlike token) {
		
		this.mensaje = mensaje;
		this.indice = indice;
		this.token = token;
	}

	/**
	 * Metodo que permite obtener mensaje
	 * @return el mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Metodo que permite asignar mensaje.
	 * @param mensaje: el mensaje a asignar.
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Metodo que permite obtener indice
	 * @return el indice
	 */
	public int getIndice() {
		return indice;
	}

	/**
	 * Metodo que permite asignar indice.
	 * @param indice: el indice a asignar.
	 */
	public void setIndice(int indice) {
		this.indice = indice;
	}

	/**
	 * Metodo que permite obtener token
	 * @return el token
	 */
	public TokenCatlike getToken() {
		return token;
	}

	/**
	 * Metodo que permite asignar token.
	 * @param token: el token a asignar.
	 */
	public void setToken(TokenCatlike token) {
		this.token = token;
	}
	
	
	
}
