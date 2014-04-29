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
 * Clase que representa una expresion.
 */
public class ExpresionAritmetica implements ISintactica {
	
	private TokenCatlike expresionIz=null;
	private TokenCatlike operadorAritmetico=null;
	private TokenCatlike expresionDer=null;

	
	
	/**
	 * Metodo Costructor que inicializa las variables
	 * @param expresionIz
	 * @param operadorAritmetico
	 * @param expresionDer
	 */
	public ExpresionAritmetica(TokenCatlike expresionIz, TokenCatlike operadorAritmetico,
			TokenCatlike expresionDer) {
		super();
		this.expresionIz = expresionIz;
		this.operadorAritmetico = operadorAritmetico;
		this.expresionDer = expresionDer;
	}



	/* (non-Javadoc)
	 * @see co.edu.uniquindio.sintactico.catlike.ISintactica#getArbolVisual()
	 */
	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Expresion Aritmetica");
		raiz.add(new DefaultMutableTreeNode(expresionIz.getLexema()));
		raiz.add(new DefaultMutableTreeNode(operadorAritmetico.getLexema()));
		raiz.add(new DefaultMutableTreeNode(expresionDer.getLexema()));
		return raiz;	
	}

}
