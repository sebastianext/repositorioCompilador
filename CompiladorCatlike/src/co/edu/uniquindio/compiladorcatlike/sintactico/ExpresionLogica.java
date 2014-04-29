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
 * 28/04/2014
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 */
package co.edu.uniquindio.compiladorcatlike.sintactico;

import javax.swing.tree.DefaultMutableTreeNode;

import co.edu.uniquindio.compiladorcatlike.lexico.TokenCatlike;

/**
 * Clase que implementa ExpresionLogica.java
 */
public class ExpresionLogica implements ISintactica {

	private ExpresionRelacional expresionRelacionalIzq;
	private TokenCatlike operadorLogico;
	private ExpresionRelacional expresionRelacionalDer;

	/**
	 * Metodo Costructor que inicializa las variables
	 * @param expresionRelacionalIzp
	 * @param operadorLogico
	 * @param expresionRelacionalDer
	 */
	public ExpresionLogica(ExpresionRelacional expresionRelacionalIzq,
			TokenCatlike operadorLogico,
			ExpresionRelacional expresionRelacionalDer) {
		super();
		this.expresionRelacionalIzq= expresionRelacionalIzq;
		this.operadorLogico = operadorLogico;
		this.expresionRelacionalDer = expresionRelacionalDer;
	}

	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Expresion Logica");
		raiz.add(expresionRelacionalIzq.getArbolVisual());
		raiz.add(new DefaultMutableTreeNode("Operador Relacional: "+operadorLogico.getLexema()));
		raiz.add(expresionRelacionalDer.getArbolVisual());

		return raiz;
	}



}
