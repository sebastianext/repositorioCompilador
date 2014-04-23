/**
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * 
 * Universidad del Quindío (Armenia - Colombia)
 * Programa de Ingeniería de Sistemas y Computación
 *
 * Asignatura: Compiladores
 * Aplicacion: AnalizadorLexico
 * @author Johan Sebastian Quintero Orozco
 * @vesion 1.0
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * 
 */
package co.edu.uniquindio.compiladorcatlike.lexico;

/**
 * Clase que representa un Token.
 */
public class TokenCatlike {


	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	
	/**
	 * Lexema del token
	 */
	private String lexema;

	/**
	 * tipo del token
	 */
	private String tipo;

	/**
	 * posición del siguiente lexema
	 */
	private int indiceSiguiente;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------
	
	/**
	 *Constructor de TokenCatlike. Metodo que inicializa las variables.
	 */
	public TokenCatlike(String lexema, String tipo, int indiceSiguiente) {
		this.lexema = lexema;
		this.tipo = tipo;
		this.indiceSiguiente = indiceSiguiente;
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------
	
	/**
	 * Metodo GET del lexema.Retorna el valor del lexema de la clase
	 * @return: el lexema
	 */
	public String getLexema() {
		return lexema;
	}
	/**
	 * Metodo GET del tipo.Retorna el valor del tipo de la clase
	 * @return: el tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * Metodo GET del indiceSiguiente.Retorna el valor del indiceSiguiente de la clase
	 * @return: el indiceSiguiente
	 */
	public int getIndiceSiguiente() {
		return indiceSiguiente;
	}
}
