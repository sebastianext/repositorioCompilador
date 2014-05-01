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
 * Clase que representa un cuerpo para.
 */
public class CuerpoPara implements ISintactica {
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 *  lista de declaraciones de subvariables , declaraciones de variable de metodos
	 */
	private ArrayList<DeclaracionVariable> bloqueSubvariable;
	/**
	 *  lista de sentencias
	 */
	private ArrayList<Object> listaSentencias;
	/**
	 * Objeto Break
	 */
	private Break breaK;
	/**
	 *  Objeto return 
	 */
	private Return returN;



	/**
	 * Metodo Costructor que inicializa las variables
	 * @param bloqueSubvariable
	 * @param listaSentencias
	 * @param breaK
	 * @param returN
	 */
	public CuerpoPara(ArrayList<DeclaracionVariable> bloqueSubvariable,
			ArrayList<Object> listaSentencias, Break breaK, Return returN) {
		super();
		this.bloqueSubvariable = bloqueSubvariable;
		this.listaSentencias = listaSentencias;
		this.breaK = breaK;
		this.returN = returN;
	}



	/* (non-Javadoc)
	 * @see co.edu.uniquindio.sintactico.catlike.ISintactica#getArbolVisual()
	 */
	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Cuerpo Metodo ");

		for (DeclaracionVariable declaracionVariable : bloqueSubvariable) {
			raiz.add(declaracionVariable.getArbolVisual());
		}
		for (int i = 0; i < listaSentencias.size(); i++) {
			if (listaSentencias.get(i) instanceof SentenciaSi) {
				raiz.add(((SentenciaSi)listaSentencias.get(i)).getArbolVisual());
			}
			if (listaSentencias.get(i) instanceof SenteciaPara) {
				raiz.add(((SenteciaPara)listaSentencias.get(i)).getArbolVisual());
			}
			if (listaSentencias.get(i) instanceof SentenciaAsignacion) {
				raiz.add(((SentenciaAsignacion)listaSentencias.get(i)).getArbolVisual());
			}

		}
		if (breaK!=null) {
			raiz.add(breaK.getArbolVisual());
		}
		if (returN!=null) {
			raiz.add(returN.getArbolVisual());
		}
		return raiz;	
	}

}
