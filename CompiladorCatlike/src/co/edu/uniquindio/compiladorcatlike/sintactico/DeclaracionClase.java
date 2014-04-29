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
 * Clase que representa una declaracion de clase.
 */
public class DeclaracionClase implements ISintactica {

	private TokenCatlike modificadorAcceso;
	private TokenCatlike identificador;

	private CuerpoClase cuerpoClase;


	/**
	 * 
	 * Metodo Costructor que inicializa las variables
	 * @param identificador
	 * @param cuerpoClase
	 */
	public DeclaracionClase(TokenCatlike modificadorAcceso,TokenCatlike identificador, CuerpoClase cuerpoClase) {
		this.modificadorAcceso=modificadorAcceso;
		this.identificador = identificador;
		this.cuerpoClase = cuerpoClase;
	}







	/**
	 * Metodo que permite obtener identificador
	 * @return el identificador
	 */
	public TokenCatlike getIdentificador() {
		return identificador;
	}







	/**
	 * Metodo que permite asignar identificador.
	 * @param identificador: el identificador a asignar.
	 */
	public void setIdentificador(TokenCatlike identificador) {
		this.identificador = identificador;
	}







	/**
	 * Metodo que permite obtener cuerpoClase
	 * @return el cuerpoClase
	 */
	public CuerpoClase getCuerpoClase() {
		return cuerpoClase;
	}







	/**
	 * Metodo que permite asignar cuerpoClase.
	 * @param cuerpoClase: el cuerpoClase a asignar.
	 */
	public void setCuerpoClase(CuerpoClase cuerpoClase) {
		this.cuerpoClase = cuerpoClase;
	}







	/* (non-Javadoc)
	 * @see co.edu.uniquindio.sintactico.catlike.ISintactica#getArbolVisual()
	 */
	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Declaracion Clase");
		raiz.add(new DefaultMutableTreeNode("Modificador Acceso: "+modificadorAcceso.getLexema()));
		raiz.add(new DefaultMutableTreeNode("Nombre: "+identificador.getLexema()));
		raiz.add(cuerpoClase.getArbolVisual());
		return raiz;	
	}

}




















