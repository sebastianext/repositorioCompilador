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
public class DeclaracionVariable implements ISintactica {
	
	private TokenCatlike modificadorAcceso;
	private TokenCatlike tipo;
	private TokenCatlike identificador;
	private TokenCatlike separadorSentencia;


	/**
	 * 
	 * Metodo Costructor que inicializa las variables
	 * @param tipo
	 * @param identificador
	 */
	public DeclaracionVariable(TokenCatlike modificadorAcceso,TokenCatlike tipo, TokenCatlike identificador, TokenCatlike separadorSentencia) {
		this.modificadorAcceso=modificadorAcceso;
		this.tipo = tipo;
		this.identificador = identificador;
		this.separadorSentencia=separadorSentencia;
	}

	

	/* (non-Javadoc)
	 * @see co.edu.uniquindio.sintactico.catlike.ISintactica#getArbolVisual()
	 */
	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Declaracion de Variable");
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
