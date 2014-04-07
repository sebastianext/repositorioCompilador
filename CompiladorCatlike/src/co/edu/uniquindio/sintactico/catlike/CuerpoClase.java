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
public class CuerpoClase implements ISintactica {
	
	private ArrayList<DeclaracionVariable> bloqueVariables;
	private ArrayList<DeclaracionMetodo> bloqueMetodos;

	
	
	
	public CuerpoClase(ArrayList<DeclaracionVariable> bloqueVariables,ArrayList<DeclaracionMetodo> bloqueMetodos) {
		this.bloqueVariables = bloqueVariables;
		this.bloqueMetodos = bloqueMetodos;
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
