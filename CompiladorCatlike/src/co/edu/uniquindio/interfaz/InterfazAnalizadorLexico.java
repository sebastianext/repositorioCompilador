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
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.BusinessBlackSteelSkin;

import co.edu.uniquindio.lexico.catlike.AnalizadorLexicoCatlike;
import co.edu.uniquindio.lexico.catlike.ArchivoPrueba;
import co.edu.uniquindio.lexico.catlike.ConstantesTipos;
import co.edu.uniquindio.lexico.catlike.TokenCatlike;

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

public class InterfazAnalizadorLexico extends javax.swing.JFrame {


	/**
	 * Componentes
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPanelTextEditor;
	private JScrollPane jScrollPaneTabla;
	private JMenu jMenuAutomata;
	private JTable jTableNoReconocidos;
	private JScrollPane jScrollPaneNoReconocidos;
	private JPanel jPanelNoReconocidos;
	private JMenuItem jMenuItemImportarPalabras;
	private JMenuItem jMenuItemDiferencias;
	private JButton jButtonLimpiar;
	private JButton jButtonArchivoPrueba;
	private JEditorPane jEditorPaneCompilador;
	private JScrollPane jScrollPaneCompilador;
	private JPanel jPanelCompilador;
	private JMenuItem jMenuItemSalir;
	private JMenu menu;
	private JMenuBar jMenuBar;
	private JEditorPane jEditorPaneCodigo;
	private JScrollPane jScrollPaneCodigo;
	private JButton jButtonVerTokens;
	private JMenuItem jMenuItemVerAutomatas;
	private JSeparator jSeparatorSubMenu;
	private JTable jTableResultados;
	private JPanel jPanelResultados;
	DefaultTableModel modeloTokens;
	DefaultTableModel modeloTokensNoReconocidos;
	private AnalizadorLexicoCatlike analizadorLexicoCatlike;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {

		SubstanceLookAndFeel.setSkin(new BusinessBlackSteelSkin());
		// mejorar el fondo  SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceCopperplateEngravingWatermark");
		InterfazAnalizadorLexico inst = new InterfazAnalizadorLexico();
		// mejorar los botones   inst.jButtonVerTokens.putClientProperty( SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
		inst.setLocationRelativeTo(null);
		inst.setVisible(true);
	}

	public InterfazAnalizadorLexico() {
		super();
		initGUI();
		analizadorLexicoCatlike=new AnalizadorLexicoCatlike();
		setResizable(false);
	}

	@SuppressWarnings("deprecation")
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("img/catlike.png")).getImage());
			{
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
			}
			{
				jPanelTextEditor = new JPanel();
				getContentPane().add(jPanelTextEditor);
				jPanelTextEditor.setBounds(12, 35, 448, 254);
				jPanelTextEditor.setLayout(null);
				jPanelTextEditor.setBorder(BorderFactory.createTitledBorder("Codigo"));
				{
					jScrollPaneCodigo = new JScrollPane();
					jPanelTextEditor.add(jScrollPaneCodigo);
					jScrollPaneCodigo.setBounds(13, 19, 422, 221);

					{
						jEditorPaneCodigo = new JEditorPane();
						jScrollPaneCodigo.setViewportView(jEditorPaneCodigo);
						jEditorPaneCodigo.setPreferredSize(new java.awt.Dimension(419, 218));
					}
				}
			}
			{
				jPanelResultados = new JPanel();
				getContentPane().add(jPanelResultados);
				jPanelResultados.setBounds(12, 295, 640, 214);
				jPanelResultados.setBorder(BorderFactory.createTitledBorder("Tabla de Tokens"));
				jPanelResultados.setLayout(null);
				{
					jScrollPaneTabla = new JScrollPane();
					jPanelResultados.add(jScrollPaneTabla);
					jScrollPaneTabla.setBounds(13, 22, 611, 176);
					{
						String[]  cabecera={ "Token", "Tipo"};
						String[][] datos={ };
						modeloTokens=new DefaultTableModel(datos,cabecera);
						jTableResultados = new JTable();
						jPanelResultados.add(jTableResultados);
						jTableResultados.setModel(modeloTokens);
						jScrollPaneTabla.setViewportView(jTableResultados);
					}
				}
			}
			{
				jButtonVerTokens = new JButton();
				getContentPane().add(jButtonVerTokens);
				jButtonVerTokens.setText("Analizar");
				jButtonVerTokens.setBounds(472, 42, 180, 55);
				jButtonVerTokens.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
				jButtonVerTokens.setToolTipText("Analiza el codigo escrito para poder asi obtener los tokens");
				jButtonVerTokens.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/ver.png")));
				jButtonVerTokens.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButtonVerTokensActionPerformed(evt);
					}
				});
			}
			{
				jPanelCompilador = new JPanel();
				getContentPane().add(jPanelCompilador);
				jPanelCompilador.setBounds(663, 34, 448, 254);
				jPanelCompilador.setLayout(null);
				jPanelCompilador.setBorder(BorderFactory.createTitledBorder("Codigo Organizado"));
				{
					jScrollPaneCompilador = new JScrollPane();
					jPanelCompilador.add(jScrollPaneCompilador);
					jScrollPaneCompilador.setBounds(14, 22, 419, 221);
					{
						jEditorPaneCompilador = new JEditorPane();
						jScrollPaneCompilador.setViewportView(jEditorPaneCompilador);
						jEditorPaneCompilador.setPreferredSize(new java.awt.Dimension(416, 218));
						jEditorPaneCompilador.setEditable(false);
					}
				}
			}
			{
				jButtonArchivoPrueba = new JButton();
				getContentPane().add(jButtonArchivoPrueba);
				jButtonArchivoPrueba.setText("Importar archivo ");
				jButtonArchivoPrueba.setBounds(472, 190, 180, 45);
				jButtonArchivoPrueba.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
				jButtonArchivoPrueba.setToolTipText("Importa un archivo de prueba donde esta definido la estructura del lenguaje");
				jButtonArchivoPrueba.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/txt.png")));
				jButtonArchivoPrueba.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButtonArchivoPruebaActionPerformed(evt);
					}
				});
			}
			{
				jButtonLimpiar = new JButton();
				getContentPane().add(jButtonLimpiar);
				jButtonLimpiar.setText("Limpiar todo");
				jButtonLimpiar.setBounds(472, 246, 180, 41);
				jButtonLimpiar.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
				jButtonLimpiar.setToolTipText("Limpia en totalidad la ventana (Campo del codigo y tablas)");
				jButtonLimpiar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/limpiar.png")));
				jButtonLimpiar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButtonLimpiarActionPerformed(evt);
					}
				});
			}
			{
				jPanelNoReconocidos = new JPanel();
				getContentPane().add(jPanelNoReconocidos);
				jPanelNoReconocidos.setBounds(664, 295, 447, 213);
				jPanelNoReconocidos.setBorder(BorderFactory.createTitledBorder(null, "Tabla de Token NO reconocidos", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION));
				jPanelNoReconocidos.setLayout(null);
				{
					jScrollPaneNoReconocidos = new JScrollPane();
					jPanelNoReconocidos.add(jScrollPaneNoReconocidos);
					jScrollPaneNoReconocidos.setBounds(12, 23, 418, 179);
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
			pack();
			this.setSize(1139, 575);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//BOTON VER TOKENS

	private void jButtonVerTokensActionPerformed(ActionEvent evt) {

		try {
			String codigo=jEditorPaneCodigo.getText();
			if (!codigo.equals("")) {

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
				for( int i = 0; i < tokensObtenidos.size( ); i++)
				{
					token = (TokenCatlike)tokensObtenidos.get(i);

					if(!token.getTipo().equals(ConstantesTipos.ESPACIOS)){
						if (token.getTipo().equals(ConstantesTipos.NORECONOCIDO)) {
							Object datos[]={token.getLexema()};
							modeloTokensNoReconocidos.addRow(datos);
						}else{
							Object datos[]={token.getLexema(),token.getTipo()};
							modeloTokens.addRow(datos);
						}
					}
				}
				organizarCodigo(tokensObtenidos);
			}
			else{
				JOptionPane.showMessageDialog(null,"Debe ingresar algun tipo de codigo" , "Mensaje de Advertencia ", JOptionPane.WARNING_MESSAGE);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error inesperado "+e.getMessage(), "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);

		}
	}

	/**
	 *  asignar color a los tokens	
	 * @param tokensObtenidos 
	 */
	public void organizarCodigo(ArrayList<TokenCatlike> tokensObtenidos){

		String codigoCompilado="";
		jEditorPaneCompilador.setText("");
		TokenCatlike token;
		jEditorPaneCompilador.setContentType("text/html");
		for( int i = 0; i < tokensObtenidos.size( ); i++)
		{
			token = (TokenCatlike)tokensObtenidos.get(i);

			String lexema=token.getLexema();
			if (token.getTipo().equals(ConstantesTipos.PALABRARESERVADA)) {

				codigoCompilado+="<b><font color=\"#000066\">"+lexema+"</font></b>"+" ";

			}if (token.getTipo().equals(ConstantesTipos.IDENTIFICADOR)) {

				codigoCompilado+="<font color=\"#333333\">"+lexema+"</font>"+" ";

			}if (token.getTipo().equals(ConstantesTipos.PARENTESISAPERTURA)||
					token.getTipo().equals(ConstantesTipos.PARENTESISCIERRE)) {

				codigoCompilado+="<font color=\"#0000FF\">"+lexema+"</font>"+" ";

			}if (token.getTipo().equals(ConstantesTipos.LLAVEAPERTURA)||
					token.getTipo().equals(ConstantesTipos.LLAVECIERRE)) {

				codigoCompilado+="<b><font color=\"#CC33CC\">"+lexema+"</font></b><br>";

			}if(token.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)){

				codigoCompilado+="<b><font color=\"#CC33CC\">"+lexema+"</font></b><br>";

			}if (token.getTipo().equals(ConstantesTipos.OPERADORRELACIONAL)||
					token.getTipo().equals(ConstantesTipos.OPERADORLOGICO)||
					token.getTipo().equals(ConstantesTipos.OPERADORADITIVO)||
					token.getTipo().equals(ConstantesTipos.OPERADORMULTIPLICATIVO)||
					token.getTipo().equals(ConstantesTipos.OPERADORASIGNACION)||
					token.getTipo().equals(ConstantesTipos.OPERADORRELACIONAL)) {
				if (lexema.equals("<")) {
					codigoCompilado+="<font color=\"#996600\">"+"&#60"+"</font>"+" ";
				}
				if(lexema.equals("<=")){
					codigoCompilado+="<font color=\"#996600\">"+"&#60"+"</font>"+" ";
				}
				if (lexema.equals("<>")) {
					codigoCompilado+="<font color=\"#996600\">"+"&#60 "+"&#62"+"</font>"+" ";
				}else{
					codigoCompilado+="<font color=\"#996600\">"+lexema+"</font>"+" ";
				}

			}if (token.getTipo().equals(ConstantesTipos.NORECONOCIDO)) {

				codigoCompilado+="<b><font color=\"#FF0000\">"+lexema+"</font></b>"+" ";

			}
			if (token.getTipo().equals(ConstantesTipos.COMENTARIOS)) {

				codigoCompilado+="<font color=\"#999999\">"+lexema+"</font>"+"<br>";

			}if (token.getTipo().equals(ConstantesTipos.COMENTARIOSSINCERRAR)) {

				codigoCompilado+="<font color=\"#999999\">"+lexema+"</font>"+" ";

			}if (token.getTipo().equals(ConstantesTipos.CADENACARACTERES)) {

				codigoCompilado+="<font color=\"#00CCFF\">"+lexema+"</font>"+" ";

			}if (token.getTipo().equals(ConstantesTipos.CADENACARACTERESSINCERRAR)) {

				codigoCompilado+="<font color=\"#339900\">"+lexema+"</font>"+" ";

			}if (token.getTipo().equals(ConstantesTipos.ENTERO)) {

				codigoCompilado+="<font color=\"#333333\">"+lexema+"</font>"+" ";
			}
			if (token.getTipo().equals(ConstantesTipos.REAL)) {

				codigoCompilado+="<font color=\"#333333\">"+lexema+"</font>"+" ";
			}
		}
		jEditorPaneCompilador.setText(codigoCompilado);
	}

	private void jMenuItemSalirActionPerformed(ActionEvent evt) {
		System.exit(0);
	}

	//BOTON LIMPIAR 
	private void jButtonLimpiarActionPerformed(ActionEvent evt) {
		jEditorPaneCodigo.setText("");
		jEditorPaneCompilador.setText("");
		modeloTokens.setRowCount(0);
		modeloTokensNoReconocidos.setRowCount(0);
	}
	//BOTON AGREGAR ARCHIVO
	private void jButtonArchivoPruebaActionPerformed(ActionEvent evt) {

		JFileChooser fc = new JFileChooser( "./archivosdeprueba" );
		fc.setDialogTitle( "Importar archivo de prueba" );
		int resultado = fc.showOpenDialog( null );

		if( resultado == JFileChooser.APPROVE_OPTION )
		{
			ArchivoPrueba archivo= new ArchivoPrueba();
			String rutaArchivo = fc.getSelectedFile().getAbsolutePath();
			String texto=archivo.leerArchivo(rutaArchivo);
			jEditorPaneCodigo.setText(texto);
		}

	}
	/**
	 * hace visisble la ventana de las deferencias de java con en el lenguaje catlike
	 * 
	 */

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

}
