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
 * Clase que representa una declaracion de metodo.
 */
public class DeclaracionMetodo implements ISintactica {
	
	private TokenCatlike modificadorAcceso;
	private TokenCatlike tipo;
	private TokenCatlike identificador;
	private CuerpoMetodo cuerpoMetodo;
	private ArrayList<Parametro> listaParametros;


	




	/**
	 * Metodo Costructor que inicializa las variables
	 * @param modificadorAcceso
	 * @param tipo
	 * @param identificador
	 * @param parentesisApertura
	 * @param parentesisCierre
	 * @param cuerpoMetodo
	 */
	public DeclaracionMetodo(TokenCatlike modificadorAcceso, TokenCatlike tipo,
			TokenCatlike identificador, ArrayList<Parametro> listaParametros, CuerpoMetodo cuerpoMetodo) {
		super();
		this.modificadorAcceso = modificadorAcceso;
		this.tipo = tipo;
		this.identificador = identificador;
		
		this.listaParametros=listaParametros;
		
		this.cuerpoMetodo = cuerpoMetodo;
	}







	/* (non-Javadoc)
	 * @see co.edu.uniquindio.sintactico.catlike.ISintactica#getArbolVisual()
	 */
	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Declaracion de Metodo");
		if (modificadorAcceso.getLexema()==null) {
			raiz.add(new DefaultMutableTreeNode("Modificador de Acceso: default"));
		}else {
			raiz.add(new DefaultMutableTreeNode("Modificador de Acceso:"+modificadorAcceso.getLexema()));
		}
		raiz.add(new DefaultMutableTreeNode("Tipo: "+tipo.getLexema()));
		raiz.add(new DefaultMutableTreeNode("Nombre: "+identificador.getLexema()));
		if (listaParametros!=null) {
			for (Parametro parametro : listaParametros) {
				raiz.add(parametro.getArbolVisual());
			}
		}
		
		raiz.add(cuerpoMetodo.getArbolVisual());
	
		return raiz;
	}

}
