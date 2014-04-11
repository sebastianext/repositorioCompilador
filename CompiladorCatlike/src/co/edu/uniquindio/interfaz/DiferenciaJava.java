/**
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * 
 * Universidad del Quindío (Armenia - Colombia)
 * Programa de Ingeniería de Sistemas y Computación
 *
 * Asignatura: Compiladores
 * Aplicacion: AnalizadorLexico
 * @author Johan Sebastian Quintero Orozco
 * @vesion 2.0
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * 
 */
package co.edu.uniquindio.interfaz;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class DiferenciaJava extends javax.swing.JDialog {
	
	
	private static final long serialVersionUID = 2611731854102849174L;
	private JPanel jPanelImg;
	private JScrollPane jScrollPaneImg;
	private JLabel jLabelImg;

	
	public DiferenciaJava() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(324, 464));
			this.setLayout(null);
			this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("img/catlike.png")).getImage());
			{
				jPanelImg = new JPanel();
				getContentPane().add(jPanelImg);
				jPanelImg.setBounds(12, 12, 284, 402);
				jPanelImg.setLayout(null);
				{
					jScrollPaneImg = new JScrollPane();
					jPanelImg.add(jScrollPaneImg);
					jScrollPaneImg.setBounds(0, 0, 284, 402);
					{
						jLabelImg = new JLabel();
						jScrollPaneImg.setViewportView(jLabelImg);
						jLabelImg.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/diferencia.png")));
						jLabelImg.setPreferredSize(new java.awt.Dimension(273, 621));
					}
				}
			}
			this.setSize(324, 464);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
