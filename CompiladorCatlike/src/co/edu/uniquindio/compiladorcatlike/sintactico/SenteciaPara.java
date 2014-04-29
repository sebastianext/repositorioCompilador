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
	private ExpresionLogica expresionLogica;
	private ExpresionRelacional expresionRelacional;
	private SentenciaAsignacion sentenciaAsignacion;
	private CuerpoPara cuerpoPara;
	
	
	
	/**
	 * Metodo Costructor que inicializa las variables
	 * @param declaracionVariable
	 * @param listaSentencias
	 * @param expresion
	 * @param cuerpoPara
	 */
	public SenteciaPara(DeclaracionVariable declaracionVariable,
			ExpresionLogica expresionLogica, SentenciaAsignacion sentenciaAsignacion,
			CuerpoPara cuerpoPara) {
		super();
		this.declaracionVariable = declaracionVariable;
		this.expresionLogica = expresionLogica;
		this.sentenciaAsignacion = sentenciaAsignacion;
		this.cuerpoPara = cuerpoPara;
	}
	
	public SenteciaPara(DeclaracionVariable declaracionVariable,
			ExpresionRelacional expresionRelacional, SentenciaAsignacion sentenciaAsignacion,
			CuerpoPara cuerpoPara) {
		super();
		this.declaracionVariable = declaracionVariable;
		this.expresionRelacional = expresionRelacional;
		this.sentenciaAsignacion = sentenciaAsignacion;
		this.cuerpoPara = cuerpoPara;
	}



	/* (non-Javadoc)
	 * @see co.edu.uniquindio.sintactico.catlike.ISintactica#getArbolVisual()
	 */
	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Sentencia Para");
		
		raiz.add(declaracionVariable.getArbolVisual());
		
		if (expresionLogica!=null) {
			raiz.add(expresionLogica.getArbolVisual());
		}
		if (expresionRelacional!=null) {
			raiz.add(expresionRelacional.getArbolVisual());
		}
		if (sentenciaAsignacion!=null) {
			raiz.add(sentenciaAsignacion.getArbolVisual());
		}
		if (cuerpoPara!=null) {
			raiz.add(cuerpoPara.getArbolVisual());
		}
		
		
		return raiz;	
	}

}
