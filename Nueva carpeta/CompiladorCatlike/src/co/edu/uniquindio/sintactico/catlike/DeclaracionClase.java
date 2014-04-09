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
public class DeclaracionClase implements ISintactica {

	private TokenCatlike identificador;

	private CuerpoClase cuerpoClase;


	/**
	 * 
	 * Metodo Costructor que inicializa las variables
	 * @param identificador
	 * @param cuerpoClase
	 */
	public DeclaracionClase(TokenCatlike identificador, CuerpoClase cuerpoClase) {
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
		// TODO Auto-generated method stub
		return null;
	}

}
