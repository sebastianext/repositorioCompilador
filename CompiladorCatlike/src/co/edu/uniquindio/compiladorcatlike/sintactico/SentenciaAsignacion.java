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

import javax.swing.tree.DefaultMutableTreeNode;

import co.edu.uniquindio.compiladorcatlike.lexico.TokenCatlike;

/**
 * Clase que representa una sentencia de asignacion.
 */
public class SentenciaAsignacion implements ISintactica {

	private TokenCatlike identificador;
	private Expresion expresion;
	
	
	
	/**
	 * Metodo Costructor que inicializa las variables
	 * @param identificador
	 * @param expresion
	 */
	public SentenciaAsignacion(TokenCatlike identificador, Expresion expresion) {
		super();
		this.identificador = identificador;
		this.expresion = expresion;
	}



	/* (non-Javadoc)
	 * @see co.edu.uniquindio.sintactico.catlike.ISintactica#getArbolVisual()
	 */
	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz= new DefaultMutableTreeNode("Sentencia Asignacion");
		raiz.add(new DefaultMutableTreeNode("Nombre:"+identificador.getLexema()));
		raiz.add(expresion.getArbolVisual());
		return raiz;
	}

}
