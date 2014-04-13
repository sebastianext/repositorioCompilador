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
public class ExpresionRelacional implements ISintactica {
	
	private TokenCatlike expresionIz;
	private TokenCatlike operadorRelacional;
	private TokenCatlike expresionDer;

	
	
	/**
	 * Metodo Costructor que inicializa las variables
	 * @param expresionIz
	 * @param operadorRelacional
	 * @param expresionDer
	 */
	public ExpresionRelacional(TokenCatlike expresionIz,
			TokenCatlike operadorRelacional, TokenCatlike expresionDer) {
		super();
		this.expresionIz = expresionIz;
		this.operadorRelacional = operadorRelacional;
		this.expresionDer = expresionDer;
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
