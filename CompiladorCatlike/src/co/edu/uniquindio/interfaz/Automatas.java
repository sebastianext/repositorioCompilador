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
 * @vesion 1.0
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * 
 */
package co.edu.uniquindio.interfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

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
@SuppressWarnings("serial")
public class Automatas extends javax.swing.JDialog {
	private JPanel jPanelAutomas;
	private JLabel jLabelTituloAutomata;
	private JLabel jLabelDigito;
	private JLabel jLabelDescripcion;
	private JLabel jLabelImg;
	private JPanel jPanelImg;
	private JLabel jLabelAutomatasCombo;
	@SuppressWarnings("rawtypes")
	private JComboBox jComboBoxAutomatas;
	private JLabel jLabelSimboloAdmiracion;
	private JLabel jLabelSimboloNumeral;
	private JLabel jLabelMinuscula;
	private JLabel jLabelMayuscula;
	private JLabel jLabelLetra;
	
	public Automatas() {
		super();
		initGUI();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("img/catlike.png")).getImage());
				{
					jPanelAutomas = new JPanel();
					getContentPane().add(jPanelAutomas);
					jPanelAutomas.setBounds(12, 12, 933, 429);
					jPanelAutomas.setLayout(null);
					jPanelAutomas.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					{
						jLabelTituloAutomata = new JLabel();
						jPanelAutomas.add(jLabelTituloAutomata);
						jLabelTituloAutomata.setText("Automatas del lenguaje");
						jLabelTituloAutomata.setBounds(17, 12, 216, 30);
						jLabelTituloAutomata.setFont(new java.awt.Font("Segoe UI",0,20));
					}
					{
						jLabelLetra = new JLabel();
						jPanelAutomas.add(jLabelLetra);
						jLabelLetra.setText("L: Letra mayúscula o minúscula.");
						jLabelLetra.setBounds(18, 78, 209, 16);
					}
					{
						jLabelMayuscula = new JLabel();
						jPanelAutomas.add(jLabelMayuscula);
						jLabelMayuscula.setText("M: Letra mayúscula.");
						jLabelMayuscula.setBounds(18, 100, 160, 16);
					}
					{
						jLabelMinuscula = new JLabel();
						jPanelAutomas.add(jLabelMinuscula);
						jLabelMinuscula.setText("m: Letra minúscula.");
						jLabelMinuscula.setBounds(18, 123, 160, 16);
					}
					{
						jLabelDigito = new JLabel();
						jPanelAutomas.add(jLabelDigito);
						jLabelDigito.setText("D: Dígito; 0, 1, 2, 3, 4, 5, 6, 7, 8, 9");
						jLabelDigito.setBounds(18, 149, 192, 16);
					}
					{
						jLabelSimboloNumeral = new JLabel();
						jPanelAutomas.add(jLabelSimboloNumeral);
						jLabelSimboloNumeral.setText("C: Cualquier símbolo excepto el numeral (#).");
						jLabelSimboloNumeral.setBounds(17, 177, 247, 16);
					}
					{
						jLabelSimboloAdmiracion = new JLabel();
						jPanelAutomas.add(jLabelSimboloAdmiracion);
						jLabelSimboloAdmiracion.setText("Z: Cualquier símbolo excepto el símbolo de admiración (¡).");
						jLabelSimboloAdmiracion.setBounds(17, 204, 314, 16);
					}
					{
						ComboBoxModel jComboBoxAutomatasModel = 
								new DefaultComboBoxModel(
										new String[] { 
												"Entero", "Operador aditivo", "Operador resta", "Operador multiplicativo",
												"Operador division", "Operador relacional", "Operador logico", "Llaves", "Parentesis", 
												"Separador de sentencia", "Comentarios", "Cadenade caracteres", "Decimal"});
						jComboBoxAutomatas = new JComboBox();
						jPanelAutomas.add(jComboBoxAutomatas);
						jComboBoxAutomatas.setModel(jComboBoxAutomatasModel);
						jComboBoxAutomatas.setBounds(445, 20, 161, 23);
						jComboBoxAutomatas.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jComboBoxAutomatasActionPerformed(evt);
							}
						});
					}
					{
						jLabelAutomatasCombo = new JLabel();
						jPanelAutomas.add(jLabelAutomatasCombo);
						jLabelAutomatasCombo.setText("Automatas: ");
						jLabelAutomatasCombo.setBounds(372, 23, 81, 16);
					}
					{
						jPanelImg = new JPanel();
						jPanelAutomas.add(jPanelImg);
						jPanelImg.setBounds(372, 49, 547, 366);
						jPanelImg.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
						jPanelImg.setBackground(new java.awt.Color(255,255,255));
						{
							jLabelImg = new JLabel();
							jPanelImg.add(jLabelImg);
							jLabelImg.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/img0.png")));
						}
					}
					{
						jLabelDescripcion = new JLabel();
						jPanelAutomas.add(jLabelDescripcion);
						jLabelDescripcion.setText("Para este lenguaje se utilizaron las siguientes convenciones:");
						jLabelDescripcion.setBounds(17, 46, 330, 16);
					}
				}
			}
			this.setSize(973, 491);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void jComboBoxAutomatasActionPerformed(ActionEvent evt) {
		
		int elemento=jComboBoxAutomatas.getSelectedIndex();
		for (int i = 0; i < 13; i++) {
			
			if (i==elemento) {
				jLabelImg.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/img"+elemento+".png")));
			}
		}
	}

}
