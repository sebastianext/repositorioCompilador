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

import com.sun.org.apache.xpath.internal.axes.OneStepIterator;

import co.edu.uniquindio.compiladorcatlike.lexico.TokenCatlike;

/**
 * Clase que representa una expresion relacional.
 */
public class ExpresionRelacional implements ISintactica {
	
	private Object expresionIz;
	private TokenCatlike operadorRelacional;
	private Object expresionDer;

	
	
	/**
	 * Metodo Costructor que inicializa las variables
	 * @param expresionIz
	 * @param operadorRelacional
	 * @param expresionDer
	 */
	public ExpresionRelacional(Object expresionIz,
			TokenCatlike operadorRelacional, Object expresionDer) {
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
		
		if (expresionIz instanceof ExpresionAritmetica) {
			raiz.add(((ExpresionAritmetica)expresionIz).getArbolVisual());
		}else {
			if (expresionIz!=null) {
				raiz.add(new DefaultMutableTreeNode("Factor: "+((TokenCatlike) expresionIz).getLexema()));
			}
		}
		
		raiz.add(new DefaultMutableTreeNode("Operador Relacional: "+operadorRelacional.getLexema()));
		if (expresionDer instanceof ExpresionAritmetica) {
			raiz.add(((ExpresionAritmetica) expresionDer).getArbolVisual());
		}else {
			if (expresionDer!=null) {
				raiz.add(new DefaultMutableTreeNode("Factor: "+((TokenCatlike) expresionDer).getLexema()));
			}
			
		}
		
		return raiz;
	}

}
