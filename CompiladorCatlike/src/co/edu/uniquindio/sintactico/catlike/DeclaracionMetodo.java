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
public class DeclaracionMetodo implements ISintactica {
	
	private TokenCatlike modificadorAcceso;
	private TokenCatlike tipo;
	private TokenCatlike identificador;


	/**
	 * 
	 * Metodo Costructor que inicializa las variables
	 * @param tipo
	 * @param identificador
	 */
	public DeclaracionMetodo(TokenCatlike modificadorAcceso,TokenCatlike tipo, TokenCatlike identificador) {
		this.modificadorAcceso=modificadorAcceso;
		this.tipo = tipo;
		this.identificador = identificador;
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
	
		return raiz;
	}

}
