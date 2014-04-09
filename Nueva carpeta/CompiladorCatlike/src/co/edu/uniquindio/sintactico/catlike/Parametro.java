/**
 * 
 */
package co.edu.uniquindio.sintactico.catlike;

import javax.swing.tree.DefaultMutableTreeNode;

import co.edu.uniquindio.lexico.catlike.TokenCatlike;

/**
 * @author Sebastian
 *
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



	/* (non-Javadoc)
	 * @see co.edu.uniquindio.sintactico.catlike.ISintactica#getArbolVisual()
	 */
	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		// TODO Auto-generated method stub
		return null;
	}

}
