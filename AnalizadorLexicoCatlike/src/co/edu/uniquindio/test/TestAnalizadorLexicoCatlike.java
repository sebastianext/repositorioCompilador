/**
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * 
 * Universidad del Quindío (Armenia - Colombia)
 * Programa de Ingeniería de Sistemas y Computación
 *
 * Asignatura: 
 * Aplicacion: AnalizadorLexico
 * @author Johan Sebastian Quintero Orozco -
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * 
 */
package co.edu.uniquindio.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import co.edu.uniquindio.catlike.AnalizadorLexicoCatlike;
import co.edu.uniquindio.catlike.Constantes;
import co.edu.uniquindio.catlike.TokenCatlike;

/**
 * Clase que representa un las prubas.
 */
public class TestAnalizadorLexicoCatlike {

	/**
	 * Es el analizador léxico con el que se harán las pruebas
	 */
	private AnalizadorLexicoCatlike analizadorLexico;

	//-----------------------------------------------------------------
	// Métodos
	//-----------------------------------------------------------------

	/**
	 * Prueba automática del método extraerNumeroEntero()
	 * 
	 * Se utiliza el método assertEquals, cuyos parámetros son:
	 *    - Mensaje en caso de que la prueba falle
	 *    - Valor que debe retornar el método que se está probando
	 *    - Valor que retornó el método que se está probando
	 */
	@Test
	public final void testExtraerNumeroEntero() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		TokenCatlike token = analizadorLexico.extraerNumero("987+256+a",4);
		assertEquals( "Error en el lexema", "256", token.getLexema());
		assertEquals( "Error en el tipo", Constantes.ENTERO, token.getTipo());
		assertEquals( "Error en el índice del siguiente lexema", 7, token.getIndiceSiguiente());
	}
	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#extraerNumero(String, int).
	 */
	@Test
	public final void testExtraerNumeroReal() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		TokenCatlike token = analizadorLexico.extraerNumero("98+2.56+a",3);
		assertEquals( "Error en el lexema", "2.56", token.getLexema());
		assertEquals( "Error en el tipo", Constantes.REAL, token.getTipo());
		assertEquals( "Error en el índice del siguiente lexema", 7, token.getIndiceSiguiente());
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#extraerOperadorAditivo(String, int).
	 */
	@Test
	public final void testExtraerOperadorAditivo() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		TokenCatlike token = analizadorLexico.extraerOperadorAditivo("98+256+a",2);
		assertEquals( "Error en el lexema", "+", token.getLexema());
		assertEquals( "Error en el tipo", Constantes.OPERADORADITIVO, token.getTipo());
		assertEquals( "Error en el índice del siguiente lexema", 3, token.getIndiceSiguiente());
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#extraerOperadorResta(String, int).
	 */
	@Test
	public final void testExtraerOperadorResta() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		TokenCatlike token = analizadorLexico.extraerOperadorResta("98-256+a",2);
		assertEquals( "Error en el lexema", "-", token.getLexema());
		assertEquals( "Error en el tipo", Constantes.OPERADORADITIVO, token.getTipo());
		assertEquals( "Error en el índice del siguiente lexema", 3, token.getIndiceSiguiente());
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#extraerOperadorMultiplicativo(String, int).
	 */
	@Test
	public final void testExtraerOperadorMultiplicativo() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		TokenCatlike token = analizadorLexico.extraerOperadorMultiplicativo("98*256+a",2);
		assertEquals( "Error en el lexema", "*", token.getLexema());
		assertEquals( "Error en el tipo", Constantes.OPERADORMULTIPLICATIVO, token.getTipo());
		assertEquals( "Error en el índice del siguiente lexema", 3, token.getIndiceSiguiente());
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#extraerOperadorDivision(String, int).
	 */
	@Test
	public final void testExtraerOperadorDivision() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		TokenCatlike token = analizadorLexico.extraerOperadorDivision("98/256+a",2);
		assertEquals( "Error en el lexema", "/", token.getLexema());
		assertEquals( "Error en el tipo", Constantes.OPERADORMULTIPLICATIVO, token.getTipo());
		assertEquals( "Error en el índice del siguiente lexema", 3, token.getIndiceSiguiente());
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#extraerOperadorLogico(String, int).
	 */
	@Test
	public final void testExtraerOperadorLogico() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		TokenCatlike token = analizadorLexico.extraerOperadorLogico("98&256+a",2);
		assertEquals( "Error en el lexema", "&", token.getLexema());
		assertEquals( "Error en el tipo", Constantes.OPERADORLOGICO, token.getTipo());
		assertEquals( "Error en el índice del siguiente lexema", 3, token.getIndiceSiguiente());
	}
	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#extraerOperadorRelacional(String, int).
	 */
	@Test
	public final void testExtraerOperadorRelacional() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		TokenCatlike token = analizadorLexico.extraerOperadorRelacional("98<=56+a",1);
		assertEquals( "Error en el lexema", "<=", token.getLexema());
		assertEquals( "Error en el tipo", Constantes.OPERADORRELACIONAL, token.getTipo());
		assertEquals( "Error en el índice del siguiente lexema", 4, token.getIndiceSiguiente());
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#extraerSeparadorSentencia(String, int).
	 */
	@Test
	public final void testExtraerSeparadorSentencia() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		TokenCatlike token = analizadorLexico.extraerSeparadorSentencia("98;",2);
		assertEquals( "Error en el lexema", ";", token.getLexema());
		assertEquals( "Error en el tipo", Constantes.SEPARADORSENTENCIA, token.getTipo());
		assertEquals( "Error en el índice del siguiente lexema", 3, token.getIndiceSiguiente());
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#extraerEspacios(String, int).
	 */
	@Test
	public final void testExtraerEspacios() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		TokenCatlike token = analizadorLexico.extraerEspacios("98 256",2);
		assertEquals( "Error en el lexema", " ", token.getLexema());
		assertEquals( "Error en el tipo", Constantes.ESPACIOS, token.getTipo());
		assertEquals( "Error en el índice del siguiente lexema", 3, token.getIndiceSiguiente());
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#extraerPalabrasRecervadas(String, int).
	 */
	@Test
	public final void testExtraerPalabrasRecervadas() {
		try {
			analizadorLexico = new AnalizadorLexicoCatlike( );
			TokenCatlike token;

			token = analizadorLexico.extraerPalabrasRecervadas("2+4*if<hola",4);

			assertEquals( "Error en el lexema", "if", token.getLexema());
			assertEquals( "Error en el tipo", Constantes.PALABRARESERVADA, token.getTipo());
			assertEquals( "Error en el índice del siguiente lexema", 6, token.getIndiceSiguiente());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#extraerCadena(String, int).
	 */
	@Test
	public final void testExtraerCadena() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		TokenCatlike token = analizadorLexico.extraerCadena("public¡hola¡4+5",6);
		assertEquals( "Error en el lexema", "¡hola¡", token.getLexema());
		assertEquals( "Error en el tipo", Constantes.CADENACARACTERES, token.getTipo());
		assertEquals( "Error en el índice del siguiente lexema", 12, token.getIndiceSiguiente());
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#extraerCadenaSinCerrar(String, int).
	 */
	@Test
	public final void testExtraerCadenaSinCerrar() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		TokenCatlike token = analizadorLexico.extraerCadena("public¡hola4+5",6);
		assertEquals( "Error en el lexema", "¡hola4+5", token.getLexema());
		assertEquals( "Error en el tipo", Constantes.CADENACARACTERESSINCERRAR, token.getTipo());
		assertEquals( "Error en el índice del siguiente lexema", 14, token.getIndiceSiguiente());
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#extraerComentarios(String, int).
	 */
	@Test
	public final void testExtraerComentarios() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		TokenCatlike token = analizadorLexico.extraerComentarios("public#hola#4+5",6);
		assertEquals( "Error en el lexema", "#hola#", token.getLexema());
		assertEquals( "Error en el tipo", Constantes.COMENTARIOS, token.getTipo());
		assertEquals( "Error en el índice del siguiente lexema", 12, token.getIndiceSiguiente());
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#extraerComentariosSinCerrar(String, int).
	 */
	@Test
	public final void testExtraerComentariosSinCerrar() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		TokenCatlike token = analizadorLexico.extraerComentarios("public#hola4+5",6);
		assertEquals( "Error en el lexema", "#hola4+5", token.getLexema());
		assertEquals( "Error en el tipo", Constantes.COMENTARIOSSINCERRAR, token.getTipo());
		assertEquals( "Error en el índice del siguiente lexema", 14, token.getIndiceSiguiente());
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#extraerNoReconocido(String, int).
	 */
	@Test
	public final void testExtraerNoReconocido() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		TokenCatlike token = analizadorLexico.extraerNoReconocido("94+3@hola",4);
		assertEquals( "Error en el lexema", "@", token.getLexema());
		assertEquals( "Error en el tipo", Constantes.NORECONOCIDO, token.getTipo());
		assertEquals( "Error en el índice del siguiente lexema", 5, token.getIndiceSiguiente());
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#esSigno(char).
	 */
	@Test
	public final void testEsSigno() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		boolean resultado = analizadorLexico.esSigno('<');
		assertTrue("Error en la comprovacion",resultado);
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#esLetra(char).
	 */
	@Test
	public final void testEsLetra() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		boolean resultado = analizadorLexico.esLetra('a');
		assertTrue("Error en la comprovacion",resultado);
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#esDigito(char).
	 */
	@Test
	public final void testEsDigito() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		boolean resultado = analizadorLexico.esDigito('2');
		assertTrue("Error en la comprovacion",resultado);
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#esPunto(char).
	 */
	@Test
	public final void testEsPunto() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		boolean resultado = analizadorLexico.esPunto('.');
		assertTrue("Error en la comprovacion",resultado);
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#esLogico(char).
	 */
	@Test
	public final void testEsLogico() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		boolean resultado = analizadorLexico.esLogico('|');
		assertTrue("Error en la comprovacion",resultado);
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#esEspacio(char).
	 */
	@Test
	public final void testEsEspacio() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		boolean resultado = analizadorLexico.esEspacio(' ');
		assertTrue("Error en la comprovacion",resultado);
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#esOperador(char).
	 */
	@Test
	public final void testEsOperador() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		boolean resultado = analizadorLexico.esOperador('+');
		assertTrue("Error en la comprovacion",resultado);
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#esLlave(char).
	 */
	@Test
	public final void testEsLlave() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		boolean resultado = analizadorLexico.esLlave('{');
		assertTrue("Error en la comprovacion",resultado);
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#esParentesis(char).
	 */
	@Test
	public final void testEsParentesis() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		boolean resultado = analizadorLexico.esParentesis('(');
		assertTrue("Error en la comprovacion",resultado);
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#esPuntoComa(char).
	 */
	@Test
	public final void testEsPuntoComa() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		boolean resultado = analizadorLexico.esPuntoComa(';');
		assertTrue("Error en la comprovacion",resultado);
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#esAdmiracion(char).
	 */
	@Test
	public final void testEsAdmiracion() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		boolean resultado = analizadorLexico.esAdmiracion('¡');
		assertTrue("Error en la comprovacion",resultado);
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#esNumeral(char).
	 */
	@Test
	public final void testEsNumeral() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		boolean resultado = analizadorLexico.esNumeral('#');
		assertTrue("Error en la comprovacion",resultado);
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#esDemasSignos(char).
	 */
	@Test
	public final void testEsDemasSignos() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		boolean resultado = analizadorLexico.esDemasSignos('"');
		assertTrue("Error en la comprovacion",resultado);
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#esCualquierCosaCadena(char).
	 */
	@Test
	public final void testEsCualquierCosaCadena() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		boolean resultado = analizadorLexico.esCualquierCosaCadena('x');
		assertTrue("Error en la comprovacion",resultado);
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo
	 * AnalizadorLexicoCatlike#esCualquieCosaCometario(char).
	 */
	@Test
	public final void testEsCualquierCosaComentario() {
		analizadorLexico = new AnalizadorLexicoCatlike( );
		boolean resultado = analizadorLexico.esCualquierCosaComentario('x');
		assertTrue("Error en la comprovacion",resultado);
	}
}
