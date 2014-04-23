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
 * Clase que representa una invocacion a metodo.
 */
public class InvocacionMetodo implements ISintactica {
	
	private TokenCatlike identificador;
	private ArrayList<Parametro> parametros;
	
	

	/**
	 * Metodo Costructor que inicializa las variables
	 * @param identificador
	 * @param parametros
	 */
	public InvocacionMetodo(TokenCatlike identificador,
			ArrayList<Parametro> parametros) {
		super();
		this.identificador = identificador;
		this.parametros = parametros;
	}



	/* (non-Javadoc)
	 * @see co.edu.uniquindio.sintactico.catlike.ISintactica#getArbolVisual()
	 */
	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Invocacion a Metodo");
	
		raiz.add(new DefaultMutableTreeNode("Nombre: "+identificador.getLexema()));
		for (Parametro parametro : parametros) {
			raiz.add(parametro.getArbolVisual());
		}
	
		return raiz;
	}

}
