/**
 * 
 */
package co.edu.uniquindio.sintactico.catlike;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author Sebastian
 *
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
		// TODO Auto-generated method stub
		return null;
	}

}
