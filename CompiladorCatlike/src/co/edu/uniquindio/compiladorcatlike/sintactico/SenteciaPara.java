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
 * Clase que representa una sentencia para.
 */
public class SenteciaPara implements ISintactica {

	private DeclaracionVariable declaracionVariable;
	private ArrayList listaExpresiones;
	private Expresion expresion;
	private CuerpoPara cuerpoPara;
	
	
	
	/**
	 * Metodo Costructor que inicializa las variables
	 * @param declaracionVariable
	 * @param listaSentencias
	 * @param expresion
	 * @param cuerpoPara
	 */
	public SenteciaPara(DeclaracionVariable declaracionVariable,
			ArrayList listaExpresiones, Expresion expresion,
			CuerpoPara cuerpoPara) {
		super();
		this.declaracionVariable = declaracionVariable;
		this.listaExpresiones = listaExpresiones;
		this.expresion = expresion;
		this.cuerpoPara = cuerpoPara;
	}



	/* (non-Javadoc)
	 * @see co.edu.uniquindio.sintactico.catlike.ISintactica#getArbolVisual()
	 */
	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Sentencia Para");
		raiz.add(declaracionVariable.getArbolVisual());
		
		
//		for (Object obj : listaExpresiones) {
//			raiz.add(new DefaultMutableTreeNode("Nombre:"+identificador.getLexema()));
//		}
		
		raiz.add(expresion.getArbolVisual());
		raiz.add(cuerpoPara.getArbolVisual());
		return raiz;	
	}

}
