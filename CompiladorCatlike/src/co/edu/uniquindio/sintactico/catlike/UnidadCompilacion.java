/**
 * 
 */
package co.edu.uniquindio.sintactico.catlike;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 
 * Clase que implementa UnidadCompilacion.java
 */
public class UnidadCompilacion implements ISintactica {


	private DeclaracionClase declaracionClase;


	public UnidadCompilacion(DeclaracionClase declaracionClase){
		this.declaracionClase=declaracionClase;	
	}

	/* (non-Javadoc)
	 * @see co.edu.uniquindio.sintactico.catlike.ISintactica#getArbolVisual()
	 */
	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Unidad de Compilación");
		raiz.add(declaracionClase.getArbolVisual());
		return raiz;
	}

	/**
	 * Metodo que permite obtener declaracionClase
	 * @return el declaracionClase
	 */
	public DeclaracionClase getDeclaracionClase() {
		return declaracionClase;
	}

	/**
	 * Metodo que permite asignar declaracionClase.
	 * @param declaracionClase: el declaracionClase a asignar.
	 */
	public void setDeclaracionClase(DeclaracionClase declaracionClase) {
		this.declaracionClase = declaracionClase;
	}


}
