/**
 * 
 */
package co.edu.uniquindio.sintactico.catlike;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import co.edu.uniquindio.lexico.catlike.TokenCatlike;

/**
 * @author Sebastian
 *
 */
public class CuerpoClase implements ISintactica {

	private TokenCatlike llaveApertura;
	private TokenCatlike llaveCierre;
	private ArrayList<DeclaracionVariable> bloqueVariables;
	private ArrayList<DeclaracionMetodo> bloqueMetodos;







	/**
	 * Metodo Costructor que inicializa las variables
	 * @param llaveApertura
	 * @param llaveCierre
	 * @param bloqueVariables
	 * @param bloqueMetodos
	 */
	public CuerpoClase(TokenCatlike llaveApertura, TokenCatlike llaveCierre,
			ArrayList<DeclaracionVariable> bloqueVariables,
			ArrayList<DeclaracionMetodo> bloqueMetodos) {
		super();
		this.llaveApertura = llaveApertura;
		this.llaveCierre = llaveCierre;
		this.bloqueVariables = bloqueVariables;
		this.bloqueMetodos = bloqueMetodos;
	}







	/* (non-Javadoc)
	 * @see co.edu.uniquindio.sintactico.catlike.ISintactica#getArbolVisual()
	 */
	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Cuerpo Clase");
		for (DeclaracionVariable declaracionVariable : bloqueVariables) {
			raiz.add(declaracionVariable.getArbolVisual());
		}
		for (DeclaracionMetodo declaracionMetodo : bloqueMetodos) {
			raiz.add(declaracionMetodo.getArbolVisual());
		}
		return raiz;	
	}
}
