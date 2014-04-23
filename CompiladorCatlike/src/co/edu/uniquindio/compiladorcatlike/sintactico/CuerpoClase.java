/**
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * Universidad del Quindío (Armenia - Colombia)
 * Programa de Ingeniería de Sistemas y Computación
 *
 * Asignatura: Compiladores
 * Aplicacion: Compilador
 * @author Johan Sebastian Quintero
 * @author Universidad del Quindio
 * @vesion 2.0
 * 11/04/2014
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 */
package co.edu.uniquindio.compiladorcatlike.sintactico;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import co.edu.uniquindio.compiladorcatlike.lexico.TokenCatlike;

/**
 * Clase que representa un cuerpo clase
 */
public class CuerpoClase implements ISintactica {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 *  token llave de pertura
	 */
	private TokenCatlike llaveApertura;

	/**
	 *  token llave de cierre
	 */
	private TokenCatlike llaveCierre;

	/**
	 *  lista de declaracion de variables
	 */
	private ArrayList<DeclaracionVariable> bloqueVariables;

	/**
	 *  lista de declaracion de metodos
	 */
	private ArrayList<DeclaracionMetodo> bloqueMetodos;





	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Metodo Costructor que inicializa las variables
	 * @param llaveApertura
	 * @param llaveCierre
	 * @param bloqueVariables
	 * @param bloqueMetodos
	 */
	public CuerpoClase(TokenCatlike llaveApertura, TokenCatlike llaveCierre,
			ArrayList<DeclaracionVariable> bloqueVariables,
			ArrayList<DeclaracionMetodo> bloqueMetodos) {
		super();
		this.llaveApertura = llaveApertura;
		this.llaveCierre = llaveCierre;
		this.bloqueVariables = bloqueVariables;
		this.bloqueMetodos = bloqueMetodos;
	}


	/* (non-Javadoc)
	 * @see co.edu.uniquindio.sintactico.catlike.ISintactica#getArbolVisual()
	 */
	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Cuerpo Clase");
		for (DeclaracionVariable declaracionVariable : bloqueVariables) {
			raiz.add(declaracionVariable.getArbolVisual());
		}
		for (DeclaracionMetodo declaracionMetodo : bloqueMetodos) {
			raiz.add(declaracionMetodo.getArbolVisual());
		}
		return raiz;	
	}
}
