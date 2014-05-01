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
 * Clase que representa un parametro.
 */
public class Parametro implements ISintactica {

	private TokenCatlike tipo;
	private TokenCatlike identificador;



	/**
	 * @param tipo
	 * @param identificador
	 */
	public Parametro(TokenCatlike tipo, TokenCatlike identificador) {

		this.tipo = tipo;
		this.identificador = identificador;
	}
	public Parametro( TokenCatlike identificador) {
		this.identificador = identificador;
	}

	/* (non-Javadoc)
	 * @see co.edu.uniquindio.sintactico.catlike.ISintactica#getArbolVisual()
	 */
	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Parametro");

		if (tipo!=null) {
			raiz.add(new DefaultMutableTreeNode("Tipo: "+tipo.getLexema()));
		}else {
			raiz.add(new DefaultMutableTreeNode("Tipo: null"));
		}
			if (identificador!=null) {
				raiz.add(new DefaultMutableTreeNode("Nombre: "+identificador.getLexema()));
			}else {
				raiz.add(new DefaultMutableTreeNode("Nombre: null"));
			}
		

		return raiz;
	}

}
