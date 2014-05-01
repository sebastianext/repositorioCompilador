package co.edu.uniquindio.compiladorcatlike.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.BusinessBlackSteelSkin;

import co.edu.uniquindio.compiladorcatlike.lexico.AnalizadorLexicoCatlike;
import co.edu.uniquindio.compiladorcatlike.lexico.ArchivoPrueba;
import co.edu.uniquindio.compiladorcatlike.lexico.ConstantesTipos;
import co.edu.uniquindio.compiladorcatlike.lexico.TokenCatlike;
import co.edu.uniquindio.compiladorcatlike.sintactico.AnalizadorSintactico;
import co.edu.uniquindio.compiladorcatlike.sintactico.ErrorSintactico;

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
public class InterfazAnalizadorSintactico extends javax.swing.JFrame {

	/**
	 *   fields
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPaneEntradaCodigo;
	private JButton jButtonGuardar;
	private JButton jButtonNuevoArchivo;
	private JTextArea jTextAreaEntradaCodigo;
	private JMenuItem jMenuItemSalir;
	private JPanel jPanelSimbolosAceptados;
	private JPanel jPanelLexica;
	private JPanel jPanelTablaSintactica;
	private JTabbedPane jTabbedPaneTablas;
	private JPanel jPanelCodigo;
	private JPanel jPanelBotones;
	private JPanel jPanelTablas;
	private JPanel jPanelArbol;
	private JMenu menu;
	private JMenuBar jMenuBar;
	private JMenu jMenuAutomata;
	private JMenuItem jMenuItemVerAutomatas;
	private JSeparator jSeparatorSubMenu;
	private JMenuItem jMenuItemImportarPalabras;
	private JMenuItem jMenuItemDiferencias;
	private JScrollPane jScrollPaneTablaELexico;
	private JScrollPane jScrollPaneNoReconocidos;
	private JTable jTableResultadosELexico;
	private JTable jTableSintactico;
	private JButton jButtonLimpiar;
	private JTree jTree;
	private JScrollPane jScrollPaneArbol;
	private JTextArea jTextAreaNumeracion;
	private JScrollPane jScrollPaneNumeracion;
	private JButton jButtonImportarArchivo;
	private JScrollPane jScrollPaneSintactico;
	private JTable jTableNoReconocidos;
	DefaultTableModel modeloTokens;
	DefaultTableModel modeloTokensNoReconocidos;
	DefaultTableModel modeloErroresSintacticos;

	private AnalizadorLexicoCatlike analizadorLexicoCatlike;
	private AnalizadorSintactico analizadorSintactico;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SubstanceLookAndFeel.setSkin(new BusinessBlackSteelSkin());
		// mejorar el fondo  SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceCopperplateEngravingWatermark");
		InterfazAnalizadorSintactico inst = new InterfazAnalizadorSintactico();
		// mejorar los botones   inst.jButtonVerTokens.putClientProperty( SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
		inst.setLocationRelativeTo(null);
		inst.setVisible(true);
	}

	public InterfazAnalizadorSintactico() {
		super();
		initGUI();
		analizadorLexicoCatlike= new AnalizadorLexicoCatlike();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("co/edu/uniquindio/compiladorcatlike/img/catlike.png")).getImage());
			this.setTitle("Compilador Catlike");
			{
				jPanelArbol = new JPanel();
				getContentPane().add(jPanelArbol);
				jPanelArbol.setBounds(12, 7, 340, 685);
				jPanelArbol.setBorder(BorderFactory.createTitledBorder(null, "Arbol Sintactico", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0,0,64)));
				jPanelArbol.setLayout(null);
				{
					jScrollPaneArbol = new JScrollPane();
					jPanelArbol.add(jScrollPaneArbol);
					jScrollPaneArbol.setBounds(17, 30, 306, 638);
				}
			}
			{
				jPanelTablas = new JPanel();
				getContentPane().add(jPanelTablas);
				jPanelTablas.setBounds(358, 485, 875, 207);
				jPanelTablas.setLayout(null);
				{
					jTabbedPaneTablas = new JTabbedPane();
					jPanelTablas.add(jTabbedPaneTablas);
					jTabbedPaneTablas.setBounds(1, 2, 873, 203);
					{
						jPanelTablaSintactica = new JPanel();
						jTabbedPaneTablas.addTab("Errores Sintacticos", null, jPanelTablaSintactica, null);
						jPanelTablaSintactica.setLayout(null);
						{
							jScrollPaneSintactico = new JScrollPane();
							jPanelTablaSintactica.add(jScrollPaneSintactico);
							jScrollPaneSintactico.setBounds(12, 12, 844, 151);
							{
								String[]  cabecera={ "Token", "Fila","Columna","Mensaje"};
								String[][] datos={ };
								modeloErroresSintacticos=new DefaultTableModel(datos,cabecera);
								jTableSintactico = new JTable();
								jScrollPaneSintactico.setViewportView(jTableSintactico);
								jTableSintactico.setModel(modeloErroresSintacticos);
//								jTableSintactico.setPreferredSize(new java.awt.Dimension(841, 0));
							}
						}
					}
					{
						jPanelLexica = new JPanel();
						jTabbedPaneTablas.addTab("Errores Lexicos", null, jPanelLexica, null);
						jPanelLexica.setLayout(null);
						{
							jScrollPaneNoReconocidos = new JScrollPane();
							jPanelLexica.add(jScrollPaneNoReconocidos);
							jScrollPaneNoReconocidos.setBounds(12, 12, 844, 151);
							{
								String[]  cabecera={ "Token"};
								String[][] datos={ };
								modeloTokensNoReconocidos=new DefaultTableModel(datos,cabecera);
								jTableNoReconocidos = new JTable();
								jScrollPaneNoReconocidos.setViewportView(jTableNoReconocidos);
								jTableNoReconocidos.setModel(modeloTokensNoReconocidos);
							}
						}
					}
					{
						jPanelSimbolosAceptados = new JPanel();
						jTabbedPaneTablas.addTab("Simbolos Lexicos Aceptados", null, jPanelSimbolosAceptados, null);
						jPanelSimbolosAceptados.setLayout(null);
						{
							jScrollPaneTablaELexico = new JScrollPane();
							jPanelSimbolosAceptados.add(jScrollPaneTablaELexico);
							jScrollPaneTablaELexico.setBounds(12, 12, 844, 150);
							{
								String[]  cabecera={ "Token", "Tipo","Fila","Columna"};
								String[][] datos={ };
								modeloTokens=new DefaultTableModel(datos,cabecera);
								jTableResultadosELexico = new JTable();
								jPanelSimbolosAceptados.add(jTableResultadosELexico);
								jTableResultadosELexico.setModel(modeloTokens);
								jScrollPaneTablaELexico.setViewportView(jTableResultadosELexico);
							}
						}
					}
				}
			}
			{
				jPanelCodigo = new JPanel();
				getContentPane().add(jPanelCodigo);
				jPanelCodigo.setBounds(358, 51, 877, 424);
				jPanelCodigo.setLayout(null);
				jPanelCodigo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				{
					jScrollPaneEntradaCodigo = new JScrollPane();
					jPanelCodigo.add(jScrollPaneEntradaCodigo);
					jScrollPaneEntradaCodigo.setBounds(55, 7, 813, 408);
					{
						jTextAreaEntradaCodigo = new JTextArea();
						jTextAreaEntradaCodigo.addCaretListener(new CaretListener() {
							public void caretUpdate(CaretEvent evt) {
								entradaCodigoCaretUpdate(evt);
							}
						});
						jTextAreaEntradaCodigo.addKeyListener(new KeyAdapter() {
							public void keyTyped(KeyEvent evt) {
								entradaCodigoFuenteKeyTyped(evt);
							}
						});
						jScrollPaneEntradaCodigo.setViewportView(jTextAreaEntradaCodigo);
						jTextAreaEntradaCodigo.setFont(new java.awt.Font("Segoe UI",0,16));

					}
				}
				{
					jScrollPaneNumeracion = new JScrollPane();
					jPanelCodigo.add(jScrollPaneNumeracion);
					jScrollPaneNumeracion.setBounds(9, 7, 40, 408);
					jScrollPaneNumeracion.getVerticalScrollBar().setVisible(false);
					jScrollPaneNumeracion.getVerticalScrollBar().setEnabled(false);
					jScrollPaneNumeracion.getVerticalScrollBar().setPreferredSize(new java.awt.Dimension(0, 55));
					jScrollPaneNumeracion.getVerticalScrollBar().setUnitIncrement(0);
					jScrollPaneNumeracion.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
						public void adjustmentValueChanged(AdjustmentEvent evt) {
							verticalScrollBarAdjustmentValueChanged(evt);
						}
					});
					{
						jTextAreaNumeracion = new JTextArea();
						jScrollPaneNumeracion.setViewportView(jTextAreaNumeracion);
						jTextAreaNumeracion.setFont(new java.awt.Font("Segoe UI",0,16));
						jTextAreaNumeracion.setEditable(false);
					}
				}
			}
			{
				jPanelBotones = new JPanel();
				getContentPane().add(jPanelBotones);
				jPanelBotones.setBounds(358, 16, 875, 32);
				jPanelBotones.setLayout(null);
				jPanelBotones.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				{
					jButtonNuevoArchivo = new JButton();
					jPanelBotones.add(jButtonNuevoArchivo);
					jButtonNuevoArchivo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("co/edu/uniquindio/compiladorcatlike/img/newFile24.png")));
					jButtonNuevoArchivo.setBounds(5, 3, 30, 27);
					jButtonNuevoArchivo.setBorderPainted(false);
					jButtonNuevoArchivo.setContentAreaFilled(false);
					jButtonNuevoArchivo.setFocusPainted(false);
					jButtonNuevoArchivo.setPressedIcon(new ImageIcon(getClass().getClassLoader().getResource("co/edu/uniquindio/compiladorcatlike/img/newFile24press.png")));
					jButtonNuevoArchivo.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("co/edu/uniquindio/compiladorcatlike/img/newFile24hover.png")));
					jButtonNuevoArchivo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButtonNuevoArchivoActionPerformed(evt);
						}
					});
				}
				{
					jButtonGuardar = new JButton();
					jPanelBotones.add(jButtonGuardar);
					jButtonGuardar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("co/edu/uniquindio/compiladorcatlike/img/save24.png")));
					jButtonGuardar.setBounds(32, 1, 38, 31);
					jButtonGuardar.setBorderPainted(false);
					jButtonGuardar.setContentAreaFilled(false);
					jButtonGuardar.setFocusPainted(false);
					jButtonGuardar.setPressedIcon(new ImageIcon(getClass().getClassLoader().getResource("co/edu/uniquindio/compiladorcatlike/img/save24press.png")));
					jButtonGuardar.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("co/edu/uniquindio/compiladorcatlike/img/save24hover.png")));
					jButtonGuardar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButtonGuardarActionPerformed(evt);
						}
					});
				}
				{
					jButtonImportarArchivo = new JButton();
					jPanelBotones.add(jButtonImportarArchivo);
					jButtonImportarArchivo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("co/edu/uniquindio/compiladorcatlike/img/import24.png")));
					jButtonImportarArchivo.setBounds(64, 2, 38, 31);
					jButtonImportarArchivo.setContentAreaFilled(false);
					jButtonImportarArchivo.setBorderPainted(false);
					jButtonImportarArchivo.setFocusPainted(false);
					jButtonImportarArchivo.setPressedIcon(new ImageIcon(getClass().getClassLoader().getResource("co/edu/uniquindio/compiladorcatlike/img/impot24press.png")));
					jButtonImportarArchivo.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("co/edu/uniquindio/compiladorcatlike/img/import24hover.png")));
					jButtonImportarArchivo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButtonImportarArchivoActionPerformed(evt);
						}
					});
				}
				{
					jButtonLimpiar = new JButton();
					jPanelBotones.add(jButtonLimpiar);
					jButtonLimpiar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("co/edu/uniquindio/compiladorcatlike/img/clear24.png")));
					jButtonLimpiar.setBounds(835, 1, 38, 31);
					jButtonLimpiar.setBorderPainted(false);
					jButtonLimpiar.setContentAreaFilled(false);
					jButtonLimpiar.setFocusPainted(false);
					jButtonLimpiar.setPressedIcon(new ImageIcon(getClass().getClassLoader().getResource("co/edu/uniquindio/compiladorcatlike/img/clear24press.png")));
					jButtonLimpiar.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("co/edu/uniquindio/compiladorcatlike/img/clear24hover.png")));
					jButtonLimpiar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButtonLimpiarActionPerformed(evt);
						}
					});
				}
			}

			jMenuBar = new JMenuBar();
			setJMenuBar(jMenuBar);
			{
				menu = new JMenu();
				jMenuBar.add(menu);
				menu.setText("Menu");
				menu.setFocusable(true);
				{
					jMenuItemImportarPalabras = new JMenuItem();
					menu.add(jMenuItemImportarPalabras);
					jMenuItemImportarPalabras.setText("Importar palabras recervadas");
					jMenuItemImportarPalabras.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));

					jMenuItemImportarPalabras.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jMenuItemImportarPalabrasActionPerformed(evt);
						}
					});
				}
				{
					jMenuItemDiferencias = new JMenuItem();
					menu.add(jMenuItemDiferencias);
					jMenuItemDiferencias.setText("Ver diferencias con java");
					jMenuItemDiferencias.setBounds(34, 21, 136, 21);
					jMenuItemDiferencias.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jMenuItemDiferenciasActionPerformed(evt);
						}
					});
				}
				{
					jSeparatorSubMenu = new JSeparator();
					menu.add(jSeparatorSubMenu);
					jSeparatorSubMenu.setBounds(164, 63, 38, 8);
				}
				{
					jMenuItemSalir = new JMenuItem();
					menu.add(jMenuItemSalir);
					jMenuItemSalir.setText("Salir");
					jMenuItemSalir.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jMenuItemSalirActionPerformed(evt);
						}
					});
				}
			}
			{
				jMenuAutomata = new JMenu();
				jMenuBar.add(jMenuAutomata);
				jMenuAutomata.setText("Automas");
				{
					jMenuItemVerAutomatas = new JMenuItem();
					jMenuAutomata.add(jMenuItemVerAutomatas);
					jMenuItemVerAutomatas.setText("Ver Automatas");
					jMenuItemVerAutomatas.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jMenuItemVerAutomatasActionPerformed(evt);
						}
					});
				}
			}

			pack();
//			Dimension dim=super.getToolkit().getScreenSize();
			this.setSize(1261, 769);
			//			setSize(400, 300);
		} catch (Exception e) {
			//add your error handling code here
			e.printStackTrace();
		}
	}

	private void jMenuItemDiferenciasActionPerformed(ActionEvent evt) {

		DiferenciaJava diferenciaJava= new DiferenciaJava();

		diferenciaJava.setVisible(true);
		diferenciaJava.setLocationRelativeTo(null);
	}

	private void jMenuItemImportarPalabrasActionPerformed(ActionEvent evt) {
		JFileChooser fc= new JFileChooser("");
		fc.setDialogTitle("Importar archivo de palabras recervadas");
		int resultado=fc.showOpenDialog(null);

		if (resultado==JFileChooser.APPROVE_OPTION) {
			String rutaArchivo= fc.getSelectedFile().getAbsolutePath();
			analizadorLexicoCatlike.setRuta(rutaArchivo);

		}
	}

	private void jMenuItemVerAutomatasActionPerformed(ActionEvent evt) {
		Automatas automatas= new Automatas();

		automatas.setVisible(true);
		automatas.setLocationRelativeTo(null);
	}
	private void jMenuItemSalirActionPerformed(ActionEvent evt) {
		System.exit(0);
	}

	private void jButtonNuevoArchivoActionPerformed(ActionEvent evt) {
		System.out.println("jButtonNuevoArchivo.actionPerformed, event="+evt);
		//TODO add your code for jButtonNuevoArchivo.actionPerformed
	}

	private void jButtonGuardarActionPerformed(ActionEvent evt) {

		try {
			String codigo=jTextAreaEntradaCodigo.getText();
			if (validarEntrada(codigo)) {

				if (analizadorLexicoCatlike.getRuta().equals("")) {
					JOptionPane.showMessageDialog(null,"Debe cargar primero las palabras recervadas","Advertencia", JOptionPane.INFORMATION_MESSAGE);
					JFileChooser fc= new JFileChooser("");
					fc.setDialogTitle("Importar archivo de palabras recervadas");
					int resultado=fc.showOpenDialog(null);

					if (resultado==JFileChooser.APPROVE_OPTION) {
						String rutaArchivo= fc.getSelectedFile().getAbsolutePath();
						analizadorLexicoCatlike.setRuta(rutaArchivo);
					}
				}

				ArrayList<TokenCatlike>  tokensObtenidos = analizadorLexicoCatlike.obtenerTokens(codigo);

				TokenCatlike token;
				modeloTokens.setRowCount(0);
				modeloTokensNoReconocidos.setRowCount(0);
				modeloErroresSintacticos.setRowCount(0);
				for( int i = 0; i < tokensObtenidos.size( ); i++)
				{
					token = (TokenCatlike)tokensObtenidos.get(i);

					if(!token.getTipo().equals(ConstantesTipos.ESPACIOS)&&
							!token.getTipo().equals(ConstantesTipos.SALTOLINEA)&&
							!token.getTipo().equals(ConstantesTipos.TABULADOR)){
						if (token.getTipo().equals(ConstantesTipos.NORECONOCIDO)) {
							Object datos[]={token.getLexema()};
							modeloTokensNoReconocidos.addRow(datos);
						}else{
							Object datos[]={token.getLexema(),token.getTipo(),token.getFila(),token.getColumna()};
							modeloTokens.addRow(datos);
						}
					}
				}
				ArrayList<TokenCatlike> listaTokensFiltrados=analizadorLexicoCatlike.filtrarTokens();
				analizadorSintactico= new AnalizadorSintactico(listaTokensFiltrados);
				getArbol(analizadorSintactico.getUnidadCompilacion().getArbolVisual());
				ArrayList<ErrorSintactico> erroresSintacticos=analizadorSintactico.getListaErroresSintacticos();
				
				for (ErrorSintactico errorSintactico : erroresSintacticos) {
					Object datos[]={errorSintactico.getToken().getLexema(),errorSintactico.getToken().getFila(),errorSintactico.getToken().getColumna(),errorSintactico.getMensaje()};
					modeloErroresSintacticos.addRow(datos);
				}
		
			}
			else{
				JOptionPane.showMessageDialog(null,"Debe ingresar algun tipo de codigo" , "Mensaje de Advertencia ", JOptionPane.WARNING_MESSAGE);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error inesperado "+e.getMessage(), "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);

		}
	}
	public boolean validarEntrada(String codigo){
		int cont=0;
		for (int i = 0; i < codigo.length(); i++) {
			if (codigo.charAt(i)=='\n' || codigo.charAt(i)=='\t'|| codigo.charAt(i)==' ') {
				cont++;
			}
		}
		if (cont==codigo.length()) {
			return false;
		}else {
			return true;
		}
	}

	private void jButtonImportarArchivoActionPerformed(ActionEvent evt) {
		JFileChooser fc = new JFileChooser( "./archivosdeprueba" );
		fc.setDialogTitle( "Importar archivo de prueba" );
		int resultado = fc.showOpenDialog( null );

		if( resultado == JFileChooser.APPROVE_OPTION )
		{
			ArchivoPrueba archivo= new ArchivoPrueba();
			String rutaArchivo = fc.getSelectedFile().getAbsolutePath();
			String texto=archivo.leerArchivo(rutaArchivo);
			jTextAreaEntradaCodigo.setText(texto);
		}
	}

	private void jButtonLimpiarActionPerformed(ActionEvent evt) {
		jTextAreaEntradaCodigo.setText("");
		modeloTokens.setRowCount(0);
		modeloTokensNoReconocidos.setRowCount(0);
		modeloErroresSintacticos.setRowCount(0);
	}
	public void getArbol(DefaultMutableTreeNode d){
		jTree = new JTree(d);
		jScrollPaneArbol.setViewportView(jTree);
	}

	/**
	 * Metodo que permite marcar las filas de codigo fuente
	 */
	private void marcarFilas() {
		int x = 1;
		String filas = "";
		String texto = jTextAreaEntradaCodigo.getText();
		for (int i = 0; i < texto.length(); i++) {
			if (texto.charAt(i) == '\n') {
				x++;
			}
		}
		for (int i = 0; i < x; i++) {
			filas += i + 1 + "\n";
		}
		jTextAreaNumeracion.setText(filas);
	}

	/**
	 * Metodo que controla el evento de entrada de codigo.
	 * @param evt, es el evento que controla el caretUpdate
	 */
	private void entradaCodigoCaretUpdate(CaretEvent evt) {
		marcarFilas();
	}

	/**
	 * 
	 * @param evt
	 */
	private void entradaCodigoFuenteKeyTyped( KeyEvent evt) {
		if ((evt.getKeyChar() == KeyEvent.VK_ENTER)|| 
				(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)||
				(evt.getKeyCode() == KeyEvent.VK_DELETE)) {
			marcarFilas();
		}
	}

	private void verticalScrollBarAdjustmentValueChanged(AdjustmentEvent evt) {
		jScrollPaneNumeracion.getVerticalScrollBar().setValue(jScrollPaneEntradaCodigo.getVerticalScrollBar().getValue());
	}


}
