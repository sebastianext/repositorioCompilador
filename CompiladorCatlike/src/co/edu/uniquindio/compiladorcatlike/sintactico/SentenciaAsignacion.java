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
	private ExpresionAritmetica expresion;
	private TokenCatlike factor;
	
	
	
	/**
	 * Metodo Costructor que inicializa las variables
	 * @param identificador
	 * @param expresion
	 */
	public SentenciaAsignacion(TokenCatlike identificador, ExpresionAritmetica expresion) {
		super();
		this.identificador = identificador;
		this.expresion = expresion;
	}
	public SentenciaAsignacion(TokenCatlike identificador, TokenCatlike factor) {
		super();
		this.identificador = identificador;
		this.factor = factor;
	}



	/* (non-Javadoc)
	 * @see co.edu.uniquindio.sintactico.catlike.ISintactica#getArbolVisual()
	 */
	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz= new DefaultMutableTreeNode("Sentencia Asignacion");
		raiz.add(new DefaultMutableTreeNode("Nombre: "+identificador.getLexema()));
		if (expresion!=null) {
			raiz.add(expresion.getArbolVisual());
		}
		else {
			raiz.add(new DefaultMutableTreeNode("Factor:"+factor.getLexema()));
		}
		
		return raiz;
	}

}
