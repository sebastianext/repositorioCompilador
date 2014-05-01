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

/**
 * Clase que representa un cuerpo clase
 */
public class CuerpoClase implements ISintactica {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
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
	public CuerpoClase(ArrayList<DeclaracionVariable> bloqueVariables,
			ArrayList<DeclaracionMetodo> bloqueMetodos) {
		
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


	/**
	 * Metodo que permite obtener bloqueVariables
	 * @return el bloqueVariables
	 */
	public ArrayList<DeclaracionVariable> getBloqueVariables() {
		return bloqueVariables;
	}


	/**
	 * Metodo que permite asignar bloqueVariables.
	 * @param bloqueVariables: el bloqueVariables a asignar.
	 */
	public void setBloqueVariables(ArrayList<DeclaracionVariable> bloqueVariables) {
		this.bloqueVariables = bloqueVariables;
	}


	/**
	 * Metodo que permite obtener bloqueMetodos
	 * @return el bloqueMetodos
	 */
	public ArrayList<DeclaracionMetodo> getBloqueMetodos() {
		return bloqueMetodos;
	}


	/**
	 * Metodo que permite asignar bloqueMetodos.
	 * @param bloqueMetodos: el bloqueMetodos a asignar.
	 */
	public void setBloqueMetodos(ArrayList<DeclaracionMetodo> bloqueMetodos) {
		this.bloqueMetodos = bloqueMetodos;
	}
	
}
