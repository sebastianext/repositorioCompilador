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
 * Clase que representa una expresion relacional.
 */
public class ExpresionRelacional implements ISintactica {
	
	private Expresion expresionIz;
	private TokenCatlike operadorRelacional;
	private Expresion expresionDer;

	
	
	/**
	 * Metodo Costructor que inicializa las variables
	 * @param expresionIz
	 * @param operadorRelacional
	 * @param expresionDer
	 */
	public ExpresionRelacional(Expresion expresionIz,
			TokenCatlike operadorRelacional, Expresion expresionDer) {
		super();
		this.expresionIz = expresionIz;
		this.operadorRelacional = operadorRelacional;
		this.expresionDer = expresionDer;
	}



	/* (non-Javadoc)
	 * @see co.edu.uniquindio.sintactico.catlike.ISintactica#getArbolVisual()
	 */
	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Expresion Relacional");
		raiz.add(expresionIz.getArbolVisual());
		raiz.add(new DefaultMutableTreeNode("Operador Relacional: "+operadorRelacional.getLexema()));
		raiz.add(expresionDer.getArbolVisual());
		return raiz;
	}

}
