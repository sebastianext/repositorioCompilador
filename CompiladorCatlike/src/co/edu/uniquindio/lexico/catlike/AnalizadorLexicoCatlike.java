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
package co.edu.uniquindio.lexico.catlike;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Clase que respresenta un analizador léxico
 */
public class AnalizadorLexicoCatlike {

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------
	ArrayList<TokenCatlike> vectorTokens;
	ArrayList<TokenCatlike> vectorTokensNoReconocidos = new ArrayList<TokenCatlike>();

	private String ruta;

	public AnalizadorLexicoCatlike(){
		ruta="";
	}
	/**
	 * Extrae los tokens de un código fuente dado
	 * @param codigo - código al cual se le van a extraer los tokens - !=null
	 * @return vector con los tokens
	 * @throws IOException 
	 */
	public ArrayList<TokenCatlike> obtenerTokens( String codigo ) throws IOException{

		TokenCatlike token;
		vectorTokens = new ArrayList<TokenCatlike>();

		// El primer token se extrae a partir de posición cero
		int indice = 0;
		// Elimina todos los saltos de lineas,tabulaciones o rotornos de carro.
		codigo = codigo.replaceAll("[\n\r\t]","");
		// Ciclo para extraer todos los tokens
		while( indice < codigo.length() ){
			// Extrae el token de la posición indice
			token = extraerSiguienteToken( codigo, indice);
			vectorTokens.add( token );
			indice = token.getIndiceSiguiente();
		}
		return vectorTokens;
	}
	/**
	 * Extrae el token de la cadena codigo a partir de la posición indice, basándose en el Autómata
	 * @param codigo - código al cual se le va a extraer un token - codigo!=null
	 * @param indice - posición a partir de la cual se va a extraer el token  - indice>=0
	 * @return token que se extrajo de la cadena
	 * @throws IOException 
	 */
	public TokenCatlike extraerSiguienteToken( String codigo, int indice ) throws IOException{

		TokenCatlike token;

		// Intenta extraer un entero
		token = extraerNumero( codigo, indice);
		if ( token != null )
			return token;

		// Intenta extraer un operador aditivo
		token = extraerOperadorAditivo( codigo, indice);
		if ( token != null )
			return token;
		// Intenta extraer un operador de la resta
		token = extraerOperadorResta( codigo, indice);
		if ( token != null )
			return token;

		// Intenta extraer un operador multiplicativo
		token = extraerOperadorMultiplicativo( codigo, indice);
		if ( token != null )
			return token;	

		// Intenta extraer un operador de la division
		token = extraerOperadorDivision( codigo, indice);
		if ( token != null )
			return token;

		// Intenta extraer un operador relacional
		token = extraerOperadorRelacional( codigo, indice);
		if ( token != null )
			return token;

		// Intenta extraer un operador logico
		token = extraerOperadorLogico( codigo, indice);
		if ( token != null )
			return token;

		// Intenta extraer un parentesis
		token = extraerParentesis( codigo, indice);
		if ( token != null )
			return token;
		// Intenta extraer una llave
		token = extraerLlaves( codigo, indice);
		if ( token != null )
			return token;

		// Intenta extraer un sepador de sentencia
		token = extraerSeparadorSentencia( codigo, indice);
		if ( token != null )
			return token;

		// Intenta extraer un espacio
		token = extraerEspacios( codigo, indice);
		if ( token != null )
			return token;
		// Intenta extraer una palabra recervada
		token = extraerPalabrasRecervadas( codigo, indice);
		if ( token != null )
			return token;


		// Intenta extraer una palabra recervada
		token = extraerIdentificardorMetodo( codigo, indice);
		if ( token != null )
			return token;
		// Intenta extraer un comentario
		token = extraerComentarios( codigo, indice);
		if ( token != null )
			return token;

		// Intenta extraer una cadena
		token = extraerCadena( codigo, indice);
		if ( token != null )
			return token;

		// Extrae un token no reconocido
		token = extraerNoReconocido( codigo, indice);
		return token;
	}
	/**
	 * Intenta extraer un numero entero o real  del codigo a partir de la posición indice,
	 * @param código al cual se le va a intentar extraer el numero entero o real - codigo!=null
	 * @param indice - posición a partir de la cual se va a intentar extraer el numero entero o real  - 0<=indice<codigo.length()
	 * @return el token numero entero o real o NULL, si el token en la posición dada no es un numero entero o real
	 */
	public TokenCatlike extraerNumero( String codigo, int indice){

		//		&& !esPunto(codigo.charAt(indice))
		if( !esDigito(codigo.charAt(indice))  )
			return null;

		// Halla el índice del siguiente lexema
		int indiceSiguiente=indice;
		String lexema ="";
		do
			indiceSiguiente++;
		while (  indiceSiguiente<codigo.length( ) && esDigito(codigo.charAt(indiceSiguiente)) );

		if ( indiceSiguiente<codigo.length( ) && esPunto(codigo.charAt(indiceSiguiente))) {

			do
				indiceSiguiente++;
			while (  indiceSiguiente<codigo.length( ) && esDigito(codigo.charAt(indiceSiguiente)) );

			lexema =  codigo.substring( indice, indiceSiguiente);
			TokenCatlike token = new TokenCatlike( lexema, ConstantesTipos.REAL, indiceSiguiente );
			return token;
		}

		lexema =  codigo.substring( indice, indiceSiguiente);
		// construye el objeto token para retornarlo.
		// El objeto token se compone del lexema, el tipo y la posición del siguiente token.
		TokenCatlike token = new TokenCatlike( lexema, ConstantesTipos.ENTERO, indiceSiguiente );
		return token;
	}
	/**
	 * Intenta extraer un operador aditivo de la cadena codigo a partir de la posición indice,
	 * basándose en el Autómata
	 * @param codigo - código al cual se le va a intentar extraer el operador aditivo  - codigo!=null
	 * @param indice - posición a partir de la cual se va a intentar extraer el operador aditivo  - 0<=indice<codigo.length()
	 * @return el token operador aditivo o NULL, si el token en la posición dada no es un operador aditivo
	 */
	public TokenCatlike extraerOperadorAditivo ( String codigo, int indice ){

		if( codigo.charAt(indice) !='+')
			return null;

		// Halla la posición del siguiente lexema
		int indiceSiguiente=indice+1;

		// Retorna el token, que se compone del lexema, el tipo del token y 
		// la posición del siguiente lexema.
		TokenCatlike token = new TokenCatlike( "+", ConstantesTipos.OPERADORADITIVO, indiceSiguiente );
		return token;
	}
	/**
	 * Intenta extraer un operador de la resta  de la cadena codigo a partir de la posición indice,
	 * basándose en el Autómata
	 * @param codigo - código al cual se le va a intentar extraer el operador de la resta   - codigo!=null
	 * @param indice - posición a partir de la cual se va a intentar extraer el operador ade la resta  - 0<=indice<codigo.length()
	 * @return el token operador de la resta o NULL, si el token en la posición dada no es un operador de la resta 
	 */
	public TokenCatlike extraerOperadorResta ( String codigo, int indice ){

		if( codigo.charAt(indice) !='-')
			return null;

		// Halla la posición del siguiente lexema
		int indiceSiguiente=indice+1;

		// Retorna el token, que se compone del lexema, el tipo del token y 
		// la posición del siguiente lexema.
		TokenCatlike token = new TokenCatlike( "-", ConstantesTipos.OPERADORADITIVO, indiceSiguiente );
		return token;
	}
	/**
	 * Intenta extraer un operador multiplicativo de la cadena codigo a partir de la posición indice,
	 * basándose en el Autómata
	 * @param codigo - código al cual se le va a intentar extraer el operador multiplicativo - codigo!=null
	 * @param indice - posición a partir de la cual se va a intentar extraer el operador multiplicativo  - 0<=indice<codigo.length()
	 * @return el token operador multiplicativo o NULL, si el token en la posición dada no es un operador multiplicativo
	 */
	public TokenCatlike extraerOperadorMultiplicativo ( String codigo, int indice ){

		if( codigo.charAt(indice) !='*')
			return null;

		// Halla la posición del siguiente lexema
		int indiceSiguiente=indice+1;

		// Retorna el token, que se compone del lexema, el tipo del token y 
		// la posición del siguiente lexema.
		TokenCatlike token = new TokenCatlike( "*", ConstantesTipos.OPERADORMULTIPLICATIVO, indiceSiguiente );
		return token;
	}
	/**
	 * Intenta extraer un operador Division de la cadena codigo a partir de la posición indice,
	 * basándose en el Autómata
	 * @param codigo - código al cual se le va a intentar extraer el operador de la division - codigo!=null
	 * @param indice - posición a partir de la cual se va a intentar extraer el operador multiplicativo  - 0<=indice<codigo.length()
	 * @return el token operador de la division o NULL, si el token en la posición dada no es un operador de la division
	 */
	public TokenCatlike extraerOperadorDivision ( String codigo, int indice ){
		if( codigo.charAt(indice) !='/')
			return null;

		// Halla la posición del siguiente lexema
		int indiceSiguiente=indice+1;

		// Retorna el token, que se compone del lexema, el tipo del token y 
		// la posición del siguiente lexema.
		TokenCatlike token = new TokenCatlike( "/", ConstantesTipos.OPERADORMULTIPLICATIVO, indiceSiguiente );
		return token;
	}
	/**
	 * Intenta extraer un operador logico de la cadena codigo a partir de la posición indice,
	 * basándose en el Autómata
	 * @param codigo - código al cual se le va a intentar extraer el operador logico - codigo!=null
	 * @param indice - posición a partir de la cual se va a intentar extraer el operador logico  - 0<=indice<codigo.length()
	 * @return el token operador logico o NULL, si el token en la posición dada no es un operador logico
	 */
	public TokenCatlike extraerOperadorLogico ( String codigo, int indice){

		if( !esLogico(codigo.charAt(indice)) )
			return null;

		// Halla el índice del siguiente lexema
		int indiceSiguiente=indice;

		indiceSiguiente++;
		while (  indiceSiguiente<codigo.length( ) && esLogico(codigo.charAt(indiceSiguiente)) ){
			if (esLogico(codigo.charAt(indiceSiguiente))) {

				String lexema =  codigo.substring( indice, indiceSiguiente);
				if (lexema.equals("&")||lexema.equals("|")) {
					break;
				}
			}
			indiceSiguiente++;
		}

		// Copia el entero en la cadena lexema
		String lexema =  codigo.substring( indice, indiceSiguiente);

		// construye el objeto token para retornarlo.
		// El objeto token se compone del lexema, el tipo y la posición del siguiente token.
		TokenCatlike token = new TokenCatlike( lexema, ConstantesTipos.OPERADORLOGICO, indiceSiguiente );
		return token;
	}
	/**
	 * Intenta extraer un parentesis de la cadena codigo a partir de la posición indice,
	 * basándose en el Autómata
	 * @param codigo - código al cual se le va a intentar extraer un parentesis - codigo!=null
	 * @param indice - posición a partir de la cual se va a intentar extraer el operador logico  - 0<=indice<codigo.length()
	 * @return el token parentesis o NULL, si el token en la posición dada no es un parentesis
	 */
	private TokenCatlike extraerParentesis(String codigo, int indice) {

		if( codigo.charAt(indice) !='(' && codigo.charAt(indice) !=')' )
			return null;

		if (codigo.charAt(indice) =='(') {

			int indiceSiguiente=indice+1;

			// Retorna el token, que se compone del lexema, el tipo del token y 
			// la posición del siguiente lexema.
			TokenCatlike token = new TokenCatlike( "(", ConstantesTipos.PARENTESISAPERTURA, indiceSiguiente );
			return token;

		}else {
			// Halla la posición del siguiente lexema
			int indiceSiguiente=indice+1;

			// Retorna el token, que se compone del lexema, el tipo del token y 
			// la posición del siguiente lexema.
			TokenCatlike token = new TokenCatlike( ")", ConstantesTipos.PARENTESISCIERRE, indiceSiguiente );
			return token;
		}
	}
	/**
	 * Intenta extraer un parentesis de la cadena codigo a partir de la posición indice,
	 * basándose en el Autómata
	 * @param codigo - código al cual se le va a intentar extraer un parentesis - codigo!=null
	 * @param indice - posición a partir de la cual se va a intentar extraer el operador logico  - 0<=indice<codigo.length()
	 * @return el token parentesis o NULL, si el token en la posición dada no es un parentesis
	 */
	private TokenCatlike extraerLlaves(String codigo, int indice) {

		if( codigo.charAt(indice) !='{' && codigo.charAt(indice) !='}' )
			return null;

		if (codigo.charAt(indice) =='{'){

			int indiceSiguiente=indice+1;

			// Retorna el token, que se compone del lexema, el tipo del token y 
			// la posición del siguiente lexema.
			TokenCatlike token = new TokenCatlike( "{", ConstantesTipos.LLAVEAPERTURA, indiceSiguiente );
			return token;

		}else {
			// Halla la posición del siguiente lexema
			int indiceSiguiente=indice+1;

			// Retorna el token, que se compone del lexema, el tipo del token y 
			// la posición del siguiente lexema.
			TokenCatlike token = new TokenCatlike( "}", ConstantesTipos.LLAVECIERRE, indiceSiguiente );
			return token;
		}
	}
	/**
	 * Intenta extraer un parentesis de la cadena codigo a partir de la posición indice,
	 * basándose en el Autómata
	 * @param codigo - código al cual se le va a intentar extraer un parentesis - codigo!=null
	 * @param indice - posición a partir de la cual se va a intentar extraer el operador logico  - 0<=indice<codigo.length()
	 * @return el token parentesis o NULL, si el token en la posición dada no es un parentesis
	 */
	public TokenCatlike extraerOperadorRelacional ( String codigo, int indice){

		if( !esSigno(codigo.charAt(indice)) )
			return null;

		// Halla el índice del siguiente lexema
		int indiceSiguiente=indice;

		indiceSiguiente++;
		while (  indiceSiguiente<codigo.length( ) && esSigno(codigo.charAt(indiceSiguiente)) ){

			if (esSigno(codigo.charAt(indiceSiguiente)) || codigo.charAt(indiceSiguiente)=='=' || codigo.charAt(indiceSiguiente)=='>') {

				String lexema =  codigo.substring( indice, indiceSiguiente+1);
				if (lexema.equals("<=")||lexema.equals(">=")||lexema.equals("<>")) {
					TokenCatlike token = new TokenCatlike( lexema, ConstantesTipos.OPERADORRELACIONAL, indiceSiguiente+1 );
					return token;
				}
			}
			break;
		}

		// Copia el entero en la cadena lexema
		String lexema =  codigo.substring( indice, indiceSiguiente);
		if (lexema.equals("<")) {
			TokenCatlike token = new TokenCatlike( lexema, ConstantesTipos.OPERADORRELACIONAL, indiceSiguiente );
			return token;

		}if (lexema.equals(">")) {
			TokenCatlike token = new TokenCatlike( lexema, ConstantesTipos.OPERADORRELACIONAL, indiceSiguiente );
			return token;

		}if (lexema.equals("=")) {
			TokenCatlike token = new TokenCatlike( lexema, ConstantesTipos.OPERADORASIGNACION, indiceSiguiente );
			return token;

		}if (lexema.equals("$")) {
			TokenCatlike token = new TokenCatlike( lexema, ConstantesTipos.OPERADORRELACIONAL, indiceSiguiente );
			return token;
		}
		// construye el objeto token para retornarlo.
		// El objeto token se compone del lexema, el tipo y la posición del siguiente token.
		TokenCatlike token = new TokenCatlike( lexema, ConstantesTipos.OPERADORRELACIONAL, indiceSiguiente );
		return token;
	}
	/**
	 * Intenta extraer un parentesis de la cadena codigo a partir de la posición indice,
	 * basándose en el Autómata
	 * @param codigo - código al cual se le va a intentar extraer un parentesis - codigo!=null
	 * @param indice - posición a partir de la cual se va a intentar extraer el operador logico  - 0<=indice<codigo.length()
	 * @return el token parentesis o NULL, si el token en la posición dada no es un parentesis
	 */
	public TokenCatlike extraerSeparadorSentencia(String codigo, int indice) {

		if( codigo.charAt(indice) !=';')
			return null;

		int indiceSiguiente=indice+1;

		// Retorna el token, que se compone del lexema, el tipo del token y 
		// la posición del siguiente lexema.
		TokenCatlike token = new TokenCatlike( ";", ConstantesTipos.SEPARADORSENTENCIA, indiceSiguiente );
		return token;
	}
	/**
	 * Intenta extraer un espacio de la cadena de codigo a partir de la posición indice,
	 * basándose en el Autómata
	 * @param codigo - código al cual se le va a intentar extraer un espacio - codigo!=null
	 * @param indice - posición a partir de la cual se va a intentar extraer el operador logico  - 0<=indice<codigo.length()
	 * @return el token espacio o NULL, si el token en la posición dada no es un espacio
	 */
	public TokenCatlike extraerEspacios(String codigo, int indice) {

		if( !esEspacio(codigo.charAt(indice)))
			return null;

		// Halla el índice del siguiente lexema
		int indiceSiguiente=indice;
		do
			indiceSiguiente++;
		while (  indiceSiguiente<codigo.length( ) && esEspacio(codigo.charAt(indiceSiguiente)) );

		// Copia el entero en la cadena lexema
		String lexema =  codigo.substring( indice, indiceSiguiente);

		// construye el objeto token para retornarlo.
		// El objeto token se compone del lexema, el tipo y la posición del siguiente token.
		TokenCatlike token = new TokenCatlike( lexema, ConstantesTipos.ESPACIOS, indiceSiguiente );
		return token;
	}
	/**
	 * Intenta extraer una palabra reservada de la cadena de codigo a partir de la posición indice,
	 * basándose en el Autómata
	 * @param codigo - código al cual se le va a intentar extraer una palabra reservada - codigo!=null
	 * @param indice - posición a partir de la cual se va a intentar extraer el operador logico  - 0<=indice<codigo.length()
	 * @return el token  palabra reservada o NULL, si el token en la posición dada no es una palabra reservada
	 * @throws IOException 
	 */
	public TokenCatlike extraerPalabrasRecervadas(String codigo, int indice) throws IOException {

		if( !esLetra(codigo.charAt(indice)))
			return null;

		// Halla el índice del siguiente lexema

		int indiceSiguiente=indice;
		if (esLetraMayuscula(codigo.charAt(indice))) {

		}
		do
			indiceSiguiente++;
		while (  indiceSiguiente<codigo.length( ) && (esLetra(codigo.charAt(indiceSiguiente)) || esDigito(codigo.charAt(indiceSiguiente)))  );

		// Copia el entero en la cadena lexema
		String lexema =  codigo.substring( indice, indiceSiguiente);

		// construye el objeto token para retornarlo.
		// El objeto token se compone del lexema, el tipo y la posición del siguiente token.
		TokenCatlike token = new TokenCatlike( lexema, ConstantesTipos.PALABRARESERVADA, indiceSiguiente );

		Properties properties= new Properties();
		FileInputStream archivo= new FileInputStream(ruta);

		properties.load(archivo);
		archivo.close();

		int cantidadPalabras = Integer.parseInt(properties.getProperty("palabras.cantidad"));

		for (int i = 1; i <= cantidadPalabras; i++) {

			if (lexema.equals(properties.getProperty("palabra"+i)))
				return token;
		}
		if (esLetraMayuscula(lexema.charAt(0))) {
			token = new TokenCatlike( lexema, ConstantesTipos.IDENTIFICADORCLASE, indiceSiguiente );
			return token;
		}
		token = new TokenCatlike( lexema, ConstantesTipos.IDENTIFICADOR, indiceSiguiente );
		return token;
	}

	/**
	 * Intenta extraer una palabra reservada de la cadena de codigo a partir de la posición indice,
	 * basándose en el Autómata
	 * @param codigo - código al cual se le va a intentar extraer una palabra reservada - codigo!=null
	 * @param indice - posición a partir de la cual se va a intentar extraer el operador logico  - 0<=indice<codigo.length()
	 * @return el token  palabra reservada o NULL, si el token en la posición dada no es una palabra reservada
	 * @throws IOException 
	 */
	public TokenCatlike extraerIdentificardorMetodo(String codigo, int indice) {

		if( !esGuion(codigo.charAt(indice)))
			return null;

		// Halla el índice del siguiente lexema

		int indiceSiguiente=indice;

		do
			indiceSiguiente++;
		while (  indiceSiguiente<codigo.length( ) && (esLetra(codigo.charAt(indiceSiguiente)) || esDigito(codigo.charAt(indiceSiguiente)))  );

		// Copia el entero en la cadena lexema
		String lexema =  codigo.substring( indice, indiceSiguiente);

		// construye el objeto token para retornarlo.
		// El objeto token se compone del lexema, el tipo y la posición del siguiente token.
		TokenCatlike token = new TokenCatlike( lexema, ConstantesTipos.IDENTIFICADORMETODO, indiceSiguiente );
		return token;

	}


	/**
	 * Intenta extraer una cadena del codigo a partir de la posición indice,
	 * basándose en el Autómata
	 * @param codigo - código al cual se le va a intentar extraer una cadena - codigo!=null
	 * @param indice - posición a partir de la cual se va a intentar extraer el operador logico  - 0<=indice<codigo.length()
	 * @return el token cadena o NULL, si el token en la posición dada no es una cadena
	 */

	public TokenCatlike extraerCadena ( String codigo, int indice){

		if( codigo.charAt(indice) !='¡' )
			return null;

		int indiceSiguiente=indice;
		do{
			indiceSiguiente++;
			if(!(indiceSiguiente>=codigo.length())){
				if (codigo.charAt(indiceSiguiente) =='¡') {
					indiceSiguiente++;
					String lexema =  codigo.substring( indice, indiceSiguiente);
					TokenCatlike token = new TokenCatlike( lexema, ConstantesTipos.CADENACARACTERES, indiceSiguiente );
					return token;
				}
			}
		}
		while (  indiceSiguiente<codigo.length( ) && esCualquierCosaCadena(codigo.charAt(indiceSiguiente)) );

		// Copia el entero en la cadena lexema
		String lexema =  codigo.substring( indice, indiceSiguiente);
		TokenCatlike token = new TokenCatlike( lexema, ConstantesTipos.CADENACARACTERESSINCERRAR, indiceSiguiente );
		return token;
	}
	/**
	 * Intenta extraer un comentario de la cadena de codigo a partir de la posición indice,
	 * basándose en el Autómata
	 * @param codigo - código al cual se le va a intentar extraer un comentario  - codigo!=null
	 * @param indice - posición a partir de la cual se va a intentar extraer el operador logico  - 0<=indice<codigo.length()
	 * @return el token comentario  o NULL, si el token en la posición dada no es un comentario 
	 */
	public TokenCatlike extraerComentarios ( String codigo, int indice){

		if( codigo.charAt(indice) !='#' )
			return null;

		int indiceSiguiente=indice;
		do{
			indiceSiguiente++;
			if(!(indiceSiguiente>=codigo.length())){
				if (codigo.charAt(indiceSiguiente) =='#') {
					indiceSiguiente++;
					String lexema =  codigo.substring( indice, indiceSiguiente);
					TokenCatlike token = new TokenCatlike( lexema, ConstantesTipos.COMENTARIOS, indiceSiguiente );
					return token;
				}
			}
		}
		while (  indiceSiguiente<codigo.length( ) && esCualquierCosaComentario(codigo.charAt(indiceSiguiente)) );

		// Copia el entero en la cadena lexema
		String lexema =  codigo.substring( indice, indiceSiguiente);
		TokenCatlike token = new TokenCatlike( lexema, ConstantesTipos.COMENTARIOSSINCERRAR, indiceSiguiente );
		return token;
	}
	/**
	 * Extraer un token no reconocido de la cadena codigo a partir de la posición indice.
	 * Antes de utilizar este método, debe haberse intentado todos los otros métodos para los otros tipos de token
	 * @param codigo - código al cual se le va a extraer el token no reconocido - codigo!=null
	 * @param indice - posición a partir de la cual se va a extraer el token no reconocido  - 0<=indice<codigo.length()
	 * @return el token no reconocido
	 */
	public TokenCatlike extraerNoReconocido ( String codigo, int indice){
		String lexema =  codigo.substring( indice, indice + 1);
		int indiceSiguiente=indice+1;
		TokenCatlike token = new TokenCatlike( lexema, ConstantesTipos.NORECONOCIDO, indiceSiguiente );
		vectorTokensNoReconocidos.add(token);
		return token;
	}

	/**
	 * Determina si un signo <,>,=,$.
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea un signo o no
	 */
	public boolean esSigno (char caracter ){
		return  caracter == '<' || caracter == '>' || caracter=='='|| caracter=='$';
	}
	/**
	 * Determina si un carácter es una letra
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea una letra  o no
	 */
	public boolean esLetra (char caracter ){
		return ( caracter >= 'a' && caracter <= 'z') || esLetraMayuscula(caracter);
	}
	/**
	 * Determina si un carácter es una letra Mayuscula
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea una letra  o no
	 */
	public boolean esLetraMayuscula (char caracter ){
		return (caracter >= 'A' && caracter <= 'Z');
	}

	/**
	 * Determina si un carácter es un dígito
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea un dígito o no
	 */
	public boolean esDigito (char caracter ){
		return  caracter >= '0' && caracter <= '9';
	}
	/**
	 * Determina si un caracter es un punto.
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea un punto o no
	 */
	public boolean esPunto (char caracter ){
		return  caracter == '.';
	}
	/**
	 * Determina si un caracter es logico &, |.
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter es logico o no
	 */
	public boolean esLogico (char caracter ){
		return  caracter == '&' || caracter == '|';
	}
	/**
	 * Determina si un caracter es espacio.
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea un espacio o no
	 */
	public boolean esEspacio (char caracter ){
		return  caracter == ' ' ;
	}
	/**
	 * Determina si un caracter es operador aritmetico -,+,*,/,%.
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea un operaodr aritmetico o no
	 */
	public boolean esOperador (char caracter ){
		return  caracter == '-' || caracter == '+' || caracter == '*' || caracter == '/' || caracter=='%';
	}
	/**
	 * Determina si un caracter es llave
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea una llave o no
	 */
	public boolean esLlave (char caracter ){
		return  caracter == '{' || caracter == '}';
	}
	/**
	 * Determina si un caracter un parentesis
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea un parentesis o no
	 */
	public boolean esParentesis (char caracter ){
		return  caracter == '(' || caracter == ')';
	}
	/**
	 * Determina si un caracter es punto y coma ;.
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea un punto y coma o no
	 */
	public boolean esPuntoComa (char caracter ){
		return  caracter == ';' ;
	}
	/**
	 * Determina si un caracter es un signo de admiracion ¡.
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea un signo de admiracion o no
	 */
	public boolean esAdmiracion (char caracter ){
		return  caracter == '¡' ;
	}
	/**
	 * Determina si un caracter es un numeral #.
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea un numeral o no
	 */
	public boolean esNumeral (char caracter ){
		return  caracter == '#' ;
	}
	/**
	 * Determina si un caracter es un numeral #.
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea un numeral o no
	 */
	public boolean esGuion (char caracter ){
		return  caracter == '_' ;
	}
	/**
	 * Determina si un caracter es un signo determinado.
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea un signo determinado o no
	 */
	public boolean esDemasSignos (char caracter ){

		return  caracter == 'ª' || caracter == 'º' || caracter == '!' || caracter == '"' || caracter == '`'||
				caracter == '?' || caracter == '¿' || caracter == '@' || caracter == '~' || caracter =='€' || 
				caracter == '¬' || caracter == '\''|| caracter == 'ç' || caracter == '[' || caracter == ']'||
				caracter=='´'||caracter=='·';
	} 
	/**
	 * Determina si un caracter es cualquier cosa para las cadenas.
	 * @param caracter  - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea un cualquier cosa o no
	 */

	public boolean esCualquierCosaCadena (char caracter ){

		if (esSigno(caracter) || esLetra(caracter) || esDigito(caracter) ||
				esPunto(caracter) || esLogico(caracter)|| esEspacio(caracter)|| 
				esOperador(caracter) ||esLlave(caracter) || esParentesis(caracter) ||
				esPuntoComa(caracter)|| esNumeral(caracter)|| esDemasSignos(caracter)) {
			return true;
		}else{
			return  false;
		}
	}
	/**
	 * Determina si un caracter es cualquier cosa para los comentarios.
	 * @param caracter - Carácter que se va a analizar - caracter!=null,
	 * @return true o false según el carácter sea un cualquier cosa o no
	 */
	public boolean esCualquierCosaComentario (char caracter ){

		if (esSigno(caracter) || esLetra(caracter) || esDigito(caracter) ||
				esPunto(caracter) || esLogico(caracter)|| esEspacio(caracter)|| 
				esOperador(caracter) ||esLlave(caracter) || esParentesis(caracter)||
				esPuntoComa(caracter)||esAdmiracion(caracter)|| esDemasSignos(caracter)) {
			return true;
		}else{
			return  false;
		}
	}
	/**
	 * Metodo GET de ruta.Retorna el valor de ruta de la clase
	 * @return: el ruta
	 */
	public String getRuta() {
		return ruta;
	}
	/**
	 * Metodo SET de  ruta.Cambia el valor de ruta de la clase
	 * @param: ruta: el ruta 
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}


}
