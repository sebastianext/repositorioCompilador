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
 * Clase que representa una sentencia si.
 */
public class SentenciaSi implements ISintactica {

	private ArrayList<ExpresionRelacional> expresionesRelacionales;
	private CuerpoSi cuerpoSi;
	
	
	public SentenciaSi(ArrayList<ExpresionRelacional> expresionesRelacionales,
			CuerpoSi cuerpoSi) {
		super();
		this.expresionesRelacionales = expresionesRelacionales;
		this.cuerpoSi = cuerpoSi;
	}


	/* (non-Javadoc)
	 * @see co.edu.uniquindio.sintactico.catlike.ISintactica#getArbolVisual()
	 */
	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Sentencia Si");
		
		for (ExpresionRelacional expresionRelacional: expresionesRelacionales) {
			raiz.add(expresionRelacional.getArbolVisual());
		}
		raiz.add(cuerpoSi.getArbolVisual());
		return raiz;	
	}

}
