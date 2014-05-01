/**
 * 
 */
package co.edu.uniquindio.compiladorcatlike.sintactico;
import java.util.ArrayList;

import co.edu.uniquindio.compiladorcatlike.lexico.ConstantesLexema;
import co.edu.uniquindio.compiladorcatlike.lexico.ConstantesTipos;
import co.edu.uniquindio.compiladorcatlike.lexico.TokenCatlike;

/**
 * @author Sebastian
 *
 */
public class AnalizadorSintactico {

	private ArrayList<TokenCatlike> listaSimbolosLexicos;
	private TokenCatlike tokenActual;
	private int indice;
	private UnidadCompilacion unidadCompilacion;
	private ArrayList<ErrorSintactico> listaErroresSintacticos;

	//Constructor de la clase AnalizadorSintactico
	public AnalizadorSintactico(ArrayList<TokenCatlike> listaSimbolos)
	{
		this.listaSimbolosLexicos = listaSimbolos;
		listaErroresSintacticos = new ArrayList<ErrorSintactico>();
		indice = 0;
		tokenActual = listaSimbolosLexicos.get(indice);
		unidadCompilacion = esUnidadCompilacion();

	}

	public UnidadCompilacion esUnidadCompilacion()
	{
		DeclaracionClase declaracionClase = esDeclaracionClase();
		if (declaracionClase!=null) {
			return new UnidadCompilacion(declaracionClase);
		}

		return null;
	}

	public DeclaracionClase esDeclaracionClase() {

		TokenCatlike modificadorAcceso=null;
		TokenCatlike identificador=null;

		if (tokenActual.getLexema().equals(ConstantesLexema.PUBLIC)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA))
			modificadorAcceso=tokenActual;
		darSiguienteToken();
		if (tokenActual.getLexema().equals(ConstantesLexema.CLASS)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA))
			darSiguienteToken();
		else
			return null;
		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADORCLASE)) {
			identificador=tokenActual;
			darSiguienteToken();

		}else {
			//manejo de error
			String mensaje="No es un identificador de clase.";
			listaErroresSintacticos.add(new ErrorSintactico(mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
			if(!tokenActual.getTipo().equals(ConstantesTipos.LLAVEAPERTURA)){
				modoPanicoClase();
			}
		}

		if (tokenActual.getTipo().equals(ConstantesTipos.LLAVEAPERTURA)) {
			darSiguienteToken();
		}
		else {
			//recupercion de error
			String mensaje="No es un llave de apertura.";
			listaErroresSintacticos.add(new ErrorSintactico(mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
			if(!tokenActual.getTipo().equals(ConstantesTipos.LLAVEAPERTURA)){
				modoPanicoClaseLLaveApertura();
			}
		}
		CuerpoClase cuerpoClase= esCuerpoClase();
		if (tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE)) {
			darSiguienteToken();
			//		return new DeclaracionClase(modificadorAcceso,identificador, cuerpoClase);
		}
		else {
			String mensaje="Falta la llave de cierre.";
			listaErroresSintacticos.add(new ErrorSintactico(mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
		}
		return new DeclaracionClase(modificadorAcceso,identificador, cuerpoClase);
	}

	private void modoPanicoClaseLLaveApertura() {
		while(!(tokenActual.getLexema().equals(ConstantesLexema.PUBLIC)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA))&&
				!(tokenActual.getLexema().equals(ConstantesLexema.PRIVATE)&&
						tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA))&&
						!esTipoMetodo()&& indice +1 != listaSimbolosLexicos.size()){
			darSiguienteToken();
		}

	}

	private void modoPanicoClase() {
		while(!tokenActual.getTipo().equals(ConstantesTipos.LLAVEAPERTURA)){
			darSiguienteToken();
		}
	}
	private void modoPanicoAtributo() {

		while(!tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)){
			darSiguienteToken();
		}

	}

	public CuerpoClase esCuerpoClase() {
		ArrayList<DeclaracionVariable> bloqueVariables= new ArrayList<DeclaracionVariable>();
		ArrayList<DeclaracionMetodo> bloqueMetodos=  new ArrayList<DeclaracionMetodo>();
		if (!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE)&&
				!tokenActual.getTipo().equals(ConstantesTipos.LLAVEAPERTURA)) {
			bloqueVariables= esBloqueVariables();
			bloqueMetodos= esBloqueMetodos();
		}
		return new CuerpoClase(bloqueVariables, bloqueMetodos);
	}

	public DeclaracionVariable esDeclaracionVariable() {

		TokenCatlike modificadorAcceso=null;
		TokenCatlike tipoDato=null;
		TokenCatlike identificador=null;
		int indiceAux=indice;
		if (!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE)) {
			if (esModificadorAcceso()) {
				modificadorAcceso=tokenActual;
				darSiguienteToken();
			}
			if(esTipoVariable()) {
				tipoDato=tokenActual;
				darSiguienteToken();
			}else {
				if (modificadorAcceso!=null ||tipoDato!=null) {
					if (tokenActual.getLexema().equals(ConstantesLexema.VOID)||tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADORMETODO)) {
						realizarBactracking(indiceAux);
						return null;

					}else {
						String mensaje="no hay declaracion de tipo.";
						listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
						//						modoPanicoClaseLLaveApertura();

					}
				}else {
					String mensaje="No es una decalracion de variable";
					listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
					modoPanicoClaseLLaveApertura();
					return null;
				}

			}
			if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)) {
				identificador=tokenActual;
				darSiguienteToken();
			}else {

				if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADORMETODO)) {
					realizarBactracking(indiceAux);
					return null;
				}else {
					String mensaje = "Debe ser un identificador de variable o metodo";
					listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
					modoPanicoAtributo();

				}
			}
			if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
				//			separadorSentenica= tokenActual;
				darSiguienteToken();
				return new DeclaracionVariable(modificadorAcceso,tipoDato, identificador);
			}else {
				//manejo de error;
				String mensaje = "Falta el separador de sentencia";
				listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
				modoPanicoClaseLLaveApertura();
				return new DeclaracionVariable(modificadorAcceso,tipoDato, identificador);
			}
		}
		else {
			return null;
		}
	}



//	private void modoPanicoTipoV() {
//		while(!esTipoMetodo()&&!esModificadorAcceso()){
//			darSiguienteToken();
//		}
//
//	}

	public DeclaracionVariable esSubDeclaracionVariable() {

		TokenCatlike tipoDato=null;
		TokenCatlike identificador=null;

		if (!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE)) {



			if(esTipoVariable()) {
				tipoDato=tokenActual;
				darSiguienteToken();
			}else {
				if (!tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)&&!(tokenActual.getLexema().equals(ConstantesLexema.FOR)&&
						!tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA))&&
						!(tokenActual.getLexema().equals(ConstantesLexema.IF)&&
								!tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA))&&
								!tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)&&
								!(tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)&&
										!tokenActual.getLexema().equals(ConstantesLexema.BREAK))&&
										!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE) &&
										indice +1 != listaSimbolosLexicos.size()) {

					String mensaje="No es una declaracion de variable";
					listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
					while (!esTipoVariable()&&!(tokenActual.getLexema().equals(ConstantesLexema.FOR)&&
							!tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA))&&
							!(tokenActual.getLexema().equals(ConstantesLexema.IF)&&
									!tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA))&&
									!tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)&&
									!(tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)&&
											!tokenActual.getLexema().equals(ConstantesLexema.BREAK))&&
											!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE) &&
											indice +1 != listaSimbolosLexicos.size()) {
						darSiguienteToken();

					}
					
				}
				return null;
			}
			if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)) {
				identificador=tokenActual;
				darSiguienteToken();
			}
			else {
				//			int indiceAux=indice;
				//			darSiguienteToken();
				if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
					String mensaje="falta un identificador de variable";
					listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
					return new DeclaracionVariable(tipoDato, identificador);
				}
				else {
					return null;
				}

			}
			if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
				darSiguienteToken( );
				return new DeclaracionVariable(tipoDato, identificador);
			}else {
				//manejo de error;
				//manejo de error;
				String mensaje = "Falta el separador de sentencia";
				listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
				while (!esTipoVariable()&&!(tokenActual.getLexema().equals(ConstantesLexema.FOR)&&
						!tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA))&&
						!(tokenActual.getLexema().equals(ConstantesLexema.IF)&&
								!tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA))&&
								!tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)&&
								!(tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)&&
										!tokenActual.getLexema().equals(ConstantesLexema.BREAK))&&
										!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE) &&
										indice +1 != listaSimbolosLexicos.size()) {
					darSiguienteToken();

				}
				return  new DeclaracionVariable(tipoDato, identificador);
			}
		}else {
			return null;
		}
	}

	public DeclaracionMetodo esDeclaracionMetodo() {
		TokenCatlike modificadorAcceso=null;
		TokenCatlike tipo=null;
		TokenCatlike identificador=null;
		//		int indiceAux=indice;
		if (!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE)) {

			if (esModificadorAcceso()) {
				modificadorAcceso=tokenActual;
				darSiguienteToken();
			}
			if (esTipoMetodo()) {
				tipo=tokenActual;
				darSiguienteToken();
			}else {

				if (modificadorAcceso!=null ||tipo!=null) {
					if (!tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADORMETODO)) {
						return null;

					}else {
						String mensaje="no hay declaracion de tipo.";
						listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
					}
				}else {
					String mensaje="No es una decalracion de metodo";
					listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
					modoPanicoClaseLLaveApertura();
					return null;
				}
				//
				//
				//				String mensaje = "Debe ser una declaracion de metodo o de variable.";
				//				listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
				//				while(!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE)){
				//					darSiguienteToken();
				//				}
				//				return null;
			}
			if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADORMETODO)) {
				identificador=tokenActual;
				darSiguienteToken();
			}else {
				//manejo de error
				if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)) {
					String mensaje = "Debe ser un identificador de metodo";
					listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
					modoPanicoMetodoLLaveApertura();
					return null;
				}
				String mensaje = "Debe ser un identificador de metodo";
				listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
				if (!tokenActual.getTipo().equals(ConstantesTipos.PARENTESISAPERTURA)) {
					darSiguienteToken();
				}
			}
			if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISAPERTURA)) {

				darSiguienteToken();
			}else {
				//manejo de error
				String mensaje="falta el parentesis de apertura.";
				listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
				modoPanicoMetodo();

			}
			ArrayList<Parametro> listaParametros=esListaParametros();

			if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISCIERRE)) {

				darSiguienteToken();
			}else {
				//manejo de error
				String mensaje="falta el parentesis de cierre";
				listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
				modoPanicoMetodoPApertura();
			}

			if (tokenActual.getTipo().equals(ConstantesTipos.LLAVEAPERTURA)) {
				darSiguienteToken();
			}else {
				//recupercion de error
				String mensaje="No es un llave de apertura.";
				listaErroresSintacticos.add(new ErrorSintactico(mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
				while (!esTipoVariable()&&!(tokenActual.getLexema().equals(ConstantesLexema.FOR)&&
						!tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA))&&
						!(tokenActual.getLexema().equals(ConstantesLexema.IF)&&
								!tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA))&&
								!tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)&&
								!(tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)&&
										!tokenActual.getLexema().equals(ConstantesLexema.BREAK))&&
										!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE) &&
										indice +1 != listaSimbolosLexicos.size()) {
					darSiguienteToken();

				}
			}

			CuerpoMetodo cuerpoMetodo= esCuerpoMetodo();
			if (tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE)) {

				darSiguienteToken();
				//			return new DeclaracionClase(modificadorAcceso,identificador, cuerpoClase);
			}
			else {
				String mensaje="Falta la llave de cierre.";
				listaErroresSintacticos.add(new ErrorSintactico(mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
			}
			darSiguienteToken();
			return new DeclaracionMetodo(modificadorAcceso, tipo, identificador,listaParametros, cuerpoMetodo);
		}
		else {
			return null;
		}



	}
	private void modoPanicoMetodoLLaveApertura() {

		while(!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE)){
			darSiguienteToken();
		}
	}

	private void modoPanicoMetodoPApertura() {
		while(!tokenActual.getTipo().equals(ConstantesTipos.LLAVEAPERTURA)&&
				!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE)){
			darSiguienteToken();
		}

	}

	private void modoPanicoMetodo() {
		while(!esTipoVariable()){
			darSiguienteToken();
		}
	}


	public CuerpoMetodo esCuerpoMetodo() {

		ArrayList<DeclaracionVariable> bloqueSubvariable=esBloqueSubVariables();
		ArrayList<Object> listaSentencias= esListaSentencias();
		Break breaK= esBreak();
		Return returN=esReturn();
		if (breaK!=null) {
			darSiguienteToken();
			return new CuerpoMetodo(bloqueSubvariable, listaSentencias, breaK, returN);
		}else if (returN!=null) {
			darSiguienteToken();
			return new CuerpoMetodo(bloqueSubvariable, listaSentencias, breaK, returN);
		}else {
			darSiguienteToken();
			return new CuerpoMetodo(bloqueSubvariable, listaSentencias, breaK, returN);
		}

	}

	public ArrayList<Parametro> esListaParametros() {

		if (!tokenActual.getTipo().equals(ConstantesTipos.PARENTESISCIERRE)) {

			ArrayList<Parametro> listaParametros = new ArrayList<Parametro>();
			Parametro parametro = esParametro();
			while(parametro!=null)
			{
				listaParametros.add(parametro);
				if (tokenActual.getLexema().equals(ConstantesLexema.COMA)) {
					darSiguienteToken();
					parametro = esParametro();
				}else {
					//manejo de error
					if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISCIERRE)) {
						break;
					}else {
						if (tokenActual.getTipo().equals(ConstantesTipos.LLAVEAPERTURA)) {
							break;
						}
						String mensaje="falta separador de parametro.";
						listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
						moodoPanicoParametro();
						break;
					}
				}

			}
			return listaParametros;
		}
		else {
			return null;
		}

	}

	private void moodoPanicoParametro() {
		while (!tokenActual.getTipo().equals(ConstantesTipos.PARENTESISCIERRE)&&
				!tokenActual.getTipo().equals(ConstantesTipos.LLAVEAPERTURA)) {
			darSiguienteToken();

		}

	}

	public ArrayList<Parametro> esListaParametrosInvocacion() {
		ArrayList<Parametro> listaParametros = new ArrayList<Parametro>();
		Parametro parametro = esParametroInvocacion();
		while(parametro!=null)
		{
			if (tokenActual.getLexema().equals(ConstantesLexema.COMA)) {
				darSiguienteToken();
				listaParametros.add(parametro);
				parametro = esParametroInvocacion();
			}else {
				//manejo de error
				if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISAPERTURA)) {
					break;
				}else {
					if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
						break;
					}
					String mensaje="falta separador de parametro.";
					listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
					moodoPanicoParametro();
				}
			}

		}
		return listaParametros;
	}

	public Parametro esParametro() {
		TokenCatlike tipo=null;
		TokenCatlike identificador=null;
		if (esTipoVariable()) {
			tipo=tokenActual;
			darSiguienteToken();
		}else{
			String mensaje="falta tipo de variable";
			listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
			if (!tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)) {
				return null;
			}
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)) {
			identificador=tokenActual;
			darSiguienteToken();
			return new Parametro(tipo, identificador);
		}else {

			String mensaje="falta identificador de varibale";
			listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
			moodoPanicoParametro();
			return new Parametro(tipo, identificador);
		}
	}
//	private void modoPanicoParametro() {
//		while (!tokenActual.getTipo().equals(ConstantesTipos.PARENTESISCIERRE)||
//				!tokenActual.getLexema().equals(ConstantesLexema.COMA)) {
//			darSiguienteToken();
//
//		}
//
//	}

	public Parametro esParametroInvocacion() {

		TokenCatlike identificador=null;

		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)||
				tokenActual.getTipo().equals(ConstantesTipos.ENTERO)||
				tokenActual.getTipo().equals(ConstantesTipos.REAL)||
				tokenActual.getTipo().equals(ConstantesTipos.CADENACARACTERES)) {
			identificador=tokenActual;
			darSiguienteToken();
			return new Parametro(identificador);
		}else {
			return null;
		}

	}
	public ArrayList<Object> esListaSentencias() {
		ArrayList<Object> listaSentencias = new ArrayList<Object>();
		SenteciaPara sentenciaPara = esSenteciaPara();
		SentenciaSi sentenciaSi=esSentenciaSi();
		SentenciaAsignacion sentenciaAsignacion=esSentenciaAsignacion();
		while(sentenciaPara!=null || sentenciaSi!=null || sentenciaAsignacion!=null )
		{
			if (sentenciaPara!=null) {
				listaSentencias.add(sentenciaPara);
			}
			if (sentenciaSi!=null) {
				listaSentencias.add(sentenciaSi);
			}
			if (sentenciaAsignacion!=null) {
				listaSentencias.add(sentenciaAsignacion);
			}
			sentenciaPara = esSenteciaPara();
			sentenciaSi=esSentenciaSi();
			sentenciaAsignacion=esSentenciaAsignacion();
		}
		return listaSentencias;
		// TODO
	}
	public SenteciaPara esSenteciaPara() {

		if (tokenActual.getLexema().equals(ConstantesLexema.FOR)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)) {
			darSiguienteToken();
		}else {
			return null;
		} if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISAPERTURA)) {
			darSiguienteToken();
		}else {
			String mensaje="falta parentesis de apertura.";
			listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
			
		}
		DeclaracionVariable declaracionVariable= esSubDeclaracionVariable();
		if (declaracionVariable!=null) {
			//darSiguienteToken();
		}else {
			return null;
		}
		//		if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
		//			darSiguienteToken();
		//		}else {
		//			//recuperar error
		//		}
		ExpresionLogica expresionLogica = esExpresionLogica();
		ExpresionRelacional expresionRelacional=null;
		if (expresionLogica==null) {
			expresionRelacional=esExpresionRelacional();
		}if (expresionRelacional==null){
			//			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
			darSiguienteToken();
		}else {
			//recuperar error
			// TODO
		}
		SentenciaAsignacion sentenciaAsignacion = esSentenciaAsignacion();
		if (sentenciaAsignacion!=null) {
			//			darSiguienteToken();
		}else {
			//recuperar error
			// TODO
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISCIERRE)) {
			darSiguienteToken();
		}else {
			//recuperar error
			// TODO
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.LLAVEAPERTURA)) {
			darSiguienteToken();
		}else {
			//manejo deerror
			// TODO
		}
		CuerpoPara cuerpoPara= esCuerpoPara();
		if (tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE)) {
			darSiguienteToken();
			if (expresionLogica==null) {
				return new SenteciaPara(declaracionVariable, expresionRelacional, sentenciaAsignacion, cuerpoPara);
			}
			return new SenteciaPara(declaracionVariable, expresionLogica, sentenciaAsignacion, cuerpoPara);
		}
		else {
			//manejo de error
			return null;
			// TODO
		}


	}
	public CuerpoPara esCuerpoPara() {
		if (!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE)) {
			ArrayList<DeclaracionVariable> bloqueSubvariable=esBloqueSubVariables();
			ArrayList<Object> listaSentencias= esListaSentencias();
			Break breaK= esBreak();
			Return returN=esReturn();

			if (breaK!=null) {
				darSiguienteToken();
				return new CuerpoPara(bloqueSubvariable, listaSentencias, breaK, returN);
			}else if (returN!=null) {
				darSiguienteToken();
				return new CuerpoPara(bloqueSubvariable, listaSentencias, breaK, returN);
			}else {
				darSiguienteToken();
				return new CuerpoPara(bloqueSubvariable, listaSentencias, breaK, returN);
			}
		}else {
			return null;
		}
		// TODO
	}
	public SentenciaSi esSentenciaSi() {


		if (tokenActual.getLexema().equals(ConstantesLexema.IF)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)) {
			darSiguienteToken();
		}else {
			return null;
		} if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISAPERTURA)) {
			darSiguienteToken();
		}else {
			String mensaje="falta parentesis de apertura.";
			listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
			
			while(!tokenActual.getTipo().equals(ConstantesTipos.ENTERO)&&
					!tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)&&
					!tokenActual.getTipo().equals(ConstantesTipos.REAL)&&
					!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE) &&
					indice +1 != listaSimbolosLexicos.size()){
				darSiguienteToken();
			}
		}
		ExpresionLogica expresionLogica= esExpresionLogica(); 
		ExpresionRelacional expresionRelacional=null;
		if (expresionLogica==null) {
			expresionRelacional=esExpresionRelacional();
		}if (expresionRelacional==null){
			//			return null;
		}

		if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISCIERRE)) {
			darSiguienteToken();
		}else {
			//manejo de error
			String mensaje="falta parentesis de cierre.";
			listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
			while(!tokenActual.getTipo().equals(ConstantesTipos.LLAVEAPERTURA)&&
					!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE) &&
					indice +1 != listaSimbolosLexicos.size()){
				darSiguienteToken();
			}
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.LLAVEAPERTURA)) {
			darSiguienteToken();
		}else {
			//manejo deerror
			String mensaje="falta llave de apertura.";
			listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
			while (!esTipoVariable()&&!(tokenActual.getLexema().equals(ConstantesLexema.FOR)&&
					!tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA))&&
					!(tokenActual.getLexema().equals(ConstantesLexema.IF)&&
							!tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA))&&
							!tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)&&
							!(tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)&&
									!tokenActual.getLexema().equals(ConstantesLexema.BREAK))&&
									!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE) &&
									indice +1 != listaSimbolosLexicos.size()) {
				darSiguienteToken();

			}
		}

		CuerpoSi cuerpoSi=esCuerpoSi();

		if (tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE)) {
			darSiguienteToken();
			if (expresionLogica==null) {
				return new SentenciaSi(expresionRelacional, cuerpoSi);
			}
			return new SentenciaSi(expresionLogica, cuerpoSi);
		}
		else {
			//manejo de error
			return null;
			// TODO
		}


	}
	public ExpresionLogica esExpresionLogica(){

		TokenCatlike operadorLogico=null;
		int indiceAux=indice;

		ExpresionRelacional expresionRelacionalIzq = esExpresionRelacional();
		if (expresionRelacionalIzq!=null) {
			//			darSiguienteToken();
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.OPERADORLOGICO)) {
			operadorLogico=tokenActual;
			darSiguienteToken();

		}else {
			realizarBactracking(indiceAux);
			return null;
		}
		ExpresionRelacional expresionRelacionalDer = esExpresionRelacional();
		if (expresionRelacionalDer!= null) {
			//			darSiguienteToken();
			return new ExpresionLogica(expresionRelacionalIzq, operadorLogico, expresionRelacionalDer);
		}
		else {
			return null;
		}
	}
	public CuerpoSi esCuerpoSi() {
		if (!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE)) {
			ArrayList<DeclaracionVariable> bloqueSubvariable=esBloqueSubVariables();
			ArrayList<Object> listaSentencias= esListaSentencias();
			Break breaK= esBreak();
			Return returN=esReturn();

			if (breaK!=null) {
				darSiguienteToken();
				return new CuerpoSi(bloqueSubvariable, listaSentencias, breaK, returN);
			}else if (returN!=null) {
				darSiguienteToken();
				return new CuerpoSi(bloqueSubvariable, listaSentencias, breaK, returN);
			}else {
				//				darSiguienteToken();
				return new CuerpoSi(bloqueSubvariable, listaSentencias, breaK, returN);
			}
		}
		else {
			return null;
		}
		// TODO
	}
	//Revisar en expresion
	public SentenciaAsignacion esSentenciaAsignacion() {
		TokenCatlike identificador=null;
		TokenCatlike factor=null;
		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)) {
			identificador=tokenActual;
			darSiguienteToken();

		}else {
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.OPERADORASIGNACION)) {
			darSiguienteToken();
		}else {
			String mensaje="No es una sentencia de asignacion";
			listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
			while (!esTipoVariable()&&!(tokenActual.getLexema().equals(ConstantesLexema.FOR)&&
					!tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA))&&
					!(tokenActual.getLexema().equals(ConstantesLexema.IF)&&
							!tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA))&&
							!tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)&&
							!(tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)&&
									!tokenActual.getLexema().equals(ConstantesLexema.BREAK))&&
									!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE) &&
									indice +1 != listaSimbolosLexicos.size()) {
				darSiguienteToken();

			}
			return null;
		}
		ExpresionAritmetica expresionAritmetica= esExpresionAritmetica();
		if (expresionAritmetica==null) {
			//			darSiguienteToken();
			if (tokenActual.getTipo().equals(ConstantesTipos.ENTERO)|| 
					tokenActual.getTipo().equals(ConstantesTipos.REAL)||
					tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)){
				factor=tokenActual;
				darSiguienteToken();
			}
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
			darSiguienteToken();
			if (expresionAritmetica==null) {
				return new SentenciaAsignacion(identificador, factor);
			}
			return new SentenciaAsignacion(identificador, expresionAritmetica);
		}else {
			if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISCIERRE)) {
				if (expresionAritmetica==null) {
					return new SentenciaAsignacion(identificador, factor);
				}
				return new SentenciaAsignacion(identificador, expresionAritmetica);
			}
		}
		return null;
		// TODO
	}
	//corregir
	public ExpresionAritmetica esExpresionAritmetica() {
		TokenCatlike expresionIz=null;
		TokenCatlike operadorAritmetico=null;
		TokenCatlike expresionDer=null;
		int indiceAux=indice;


		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)||
				tokenActual.getTipo().equals(ConstantesTipos.ENTERO)||
				tokenActual.getTipo().equals(ConstantesTipos.REAL)) {
			expresionIz=tokenActual;
			darSiguienteToken();	
		}else {
			return null;
		}
		if (operadorAritmetico()) {
			operadorAritmetico=tokenActual;
			darSiguienteToken();
		}else {
			realizarBactracking(indiceAux);
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)||
				tokenActual.getTipo().equals(ConstantesTipos.ENTERO)||
				tokenActual.getTipo().equals(ConstantesTipos.REAL)) {
			expresionDer=tokenActual;
			darSiguienteToken();	
			return new ExpresionAritmetica(expresionIz, operadorAritmetico, expresionDer);
		}else {
			return null;
		}
		// TODO

	}

	public ArrayList<ExpresionAritmetica> esListaExpresiones(){

		ArrayList<ExpresionAritmetica> expresiones= new ArrayList<ExpresionAritmetica>();
		ExpresionAritmetica expresion= esExpresionAritmetica();
		while(expresion!=null)
		{
			expresiones.add(expresion);
			expresion = esExpresionAritmetica();
		}
		return expresiones;
		// TODO
	}

	public ExpresionRelacional esExpresionRelacional() {

		TokenCatlike operadorRelacional=null;
		Object expresionIzq=esExpresionAritmetica();
		if (expresionIzq!=null) {
			//			darSiguienteToken();	
		}else if(tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)||
				tokenActual.getTipo().equals(ConstantesTipos.ENTERO)||
				tokenActual.getTipo().equals(ConstantesTipos.REAL)){
			expresionIzq=tokenActual;
			darSiguienteToken();
		}else  {
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.OPERADORRELACIONAL)) {
			operadorRelacional=tokenActual;
			darSiguienteToken();
		}else {
			return null;
		}
		ExpresionAritmetica esExpresionDer=esExpresionAritmetica();
		if (esExpresionDer!=null) {
			//			darSiguienteToken();
			return new ExpresionRelacional(expresionIzq, operadorRelacional, esExpresionDer);
		}else if(tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)||
				tokenActual.getTipo().equals(ConstantesTipos.ENTERO)||
				tokenActual.getTipo().equals(ConstantesTipos.REAL)){
			TokenCatlike t=tokenActual;
			darSiguienteToken();
			return new ExpresionRelacional(expresionIzq, operadorRelacional, t);
		}else {
			//manejo de error

			return null;
		}
		// TODO

	}

	public Return esReturn() {

		TokenCatlike retorno=null;
		if (tokenActual.getLexema().equals(ConstantesLexema.RETURN)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)) {
			darSiguienteToken();
		}else {
			return null;
		}
		//		Expresion expresion= esExpresion();
		//		if (expresion!=null) {
		//			darSiguienteToken();
		//		}else
		if (tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)
				&& tokenActual.getLexema().equals(ConstantesLexema.NULL)||
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)
				&& tokenActual.getLexema().equals(ConstantesLexema.TRUE)||
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)
				&& tokenActual.getLexema().equals(ConstantesLexema.FALSE)||
				tokenActual.getTipo().equals(ConstantesTipos.CADENACARACTERES)||
				tokenActual.getTipo().equals(ConstantesTipos.REAL)||
				tokenActual.getTipo().equals(ConstantesTipos.ENTERO)||
				tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)) {
			retorno=tokenActual;
			darSiguienteToken();

		}else {
			if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
				darSiguienteToken();
				return new Return(retorno);
			}else {
				String mensaje = "Debe tener algun retorno.";
				listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
				modoPanicoAtributo();
			}

		}

		if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
			darSiguienteToken();
			return new Return(retorno);
		}
		else {
			String mensaje = "falta el separador de sentencia.";
			listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
			return null;
		}

	}

	public Break esBreak() {
		//		TokenCatlike breaK=null;
		if (tokenActual.getLexema().equals(ConstantesLexema.BREAK)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)) {
			//			breaK=tokenActual;
			darSiguienteToken();
		}else{
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
			return new Break();
		}else{
			//manejar error
			String mensaje="Falta el separador de sentencia";
			listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
			return new Break();
		}

	}

	public InvocacionMetodo esInvocacionMetodo() {

		TokenCatlike identificador=null;

		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADORMETODO)) {
			darSiguienteToken();
		}else {
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISAPERTURA)) {
			darSiguienteToken();
		}else {
			//manejo de error
			String mensaje="falta el parentesis de apertura.";
			listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
			modoPanicoInvocacioMetodo();

		}
		ArrayList<Parametro> parametros=esListaParametrosInvocacion();
		if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISCIERRE)) {
			darSiguienteToken();
		}else {
			String mensaje="falta el parentesis de cierre.";
			listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
			modoPanicoInvocacioMetodoPCierre();
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
			darSiguienteToken();
			return new InvocacionMetodo(identificador, parametros);
		}
		else {
			//manejo de error
			String mensaje = "Falta el separador de sentencia";
			listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
			return null;
		}

	}

	private void modoPanicoInvocacioMetodo() {
		while(!tokenActual.getTipo().equals(ConstantesTipos.PARENTESISCIERRE)||
				!tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)){
			darSiguienteToken();
		}
	}
	private void modoPanicoInvocacioMetodoPCierre() {
		while(!tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)){
			darSiguienteToken();
		}
	}

	public ArrayList<DeclaracionVariable> esBloqueVariables() {

		ArrayList<DeclaracionVariable> bloqueVariables = new ArrayList<DeclaracionVariable>();
		DeclaracionVariable declaracionVariable = esDeclaracionVariable();
		while(declaracionVariable!=null)
		{
			bloqueVariables.add(declaracionVariable);
			declaracionVariable = esDeclaracionVariable();
		}
		return bloqueVariables;

	}

	public ArrayList<DeclaracionVariable> esBloqueSubVariables() {

		ArrayList<DeclaracionVariable> bloqueVariables = new ArrayList<DeclaracionVariable>();
		DeclaracionVariable declaracionVariable = esSubDeclaracionVariable();


		while(declaracionVariable!=null)
		{	
			bloqueVariables.add(declaracionVariable);
			if (!tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE)) {

				declaracionVariable = esSubDeclaracionVariable();
			}
			else {
				break;
			}
		}
		return bloqueVariables;

	}

	public ArrayList<DeclaracionMetodo> esBloqueMetodos() {

		ArrayList<DeclaracionMetodo> bloqueMetodos = new ArrayList<DeclaracionMetodo>();
		DeclaracionMetodo declaracionMetodo = esDeclaracionMetodo();
		while(declaracionMetodo!=null)
		{
			bloqueMetodos.add(declaracionMetodo);
			declaracionMetodo = esDeclaracionMetodo();
		}
		return bloqueMetodos;

	}
	public boolean esTipoVariable(){

		if (tokenActual.getLexema().equals(ConstantesLexema.INT)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)||
				tokenActual.getLexema().equals(ConstantesLexema.DOUBLE)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)||
				tokenActual.getLexema().equals(ConstantesLexema.BOOLEAN)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)) {
			return true;
		}
		return false;
	}

	public boolean esTipoMetodo(){

		if (esTipoVariable()|| tokenActual.getLexema().equals(ConstantesLexema.VOID)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)||esTipoVariable()) {
			return true;
		}
		return false;
	}

	public boolean esModificadorAcceso(){
		if(tokenActual.getLexema().equals(ConstantesLexema.PUBLIC)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)||
				tokenActual.getLexema().equals(ConstantesLexema.PRIVATE)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA))
			return true;
		return false;
	}


	public boolean operadorAritmetico(){
		if(tokenActual.getTipo().equals(ConstantesTipos.OPERADORADITIVO)||
				tokenActual.getTipo().equals(ConstantesTipos.OPERADORMULTIPLICATIVO)){
			return true;
		}
		return false;
	}















	public void darSiguienteToken()
	{
		if(indice == listaSimbolosLexicos.size()-1)
		{
			return;
		}else
		{
			indice++;
			tokenActual = listaSimbolosLexicos.get(indice);
		}
	}

	public void realizarBactracking(int posBT)
	{
		indice = posBT;
		tokenActual = listaSimbolosLexicos.get(indice);
	}

	/**
	 * Metodo que permite obtener unidadCompilacion
	 * @return el unidadCompilacion
	 */
	public UnidadCompilacion getUnidadCompilacion() {
		return unidadCompilacion;
	}

	/**
	 * Metodo que permite asignar unidadCompilacion.
	 * @param unidadCompilacion: el unidadCompilacion a asignar.
	 */
	public void setUnidadCompilacion(UnidadCompilacion unidadCompilacion) {
		this.unidadCompilacion = unidadCompilacion;
	}

	/**
	 * Metodo que permite obtener listaErroresSintacticos
	 * @return el listaErroresSintacticos
	 */
	public ArrayList<ErrorSintactico> getListaErroresSintacticos() {
		return listaErroresSintacticos;
	}

	/**
	 * Metodo que permite asignar listaErroresSintacticos.
	 * @param listaErroresSintacticos: el listaErroresSintacticos a asignar.
	 */
	public void setListaErroresSintacticos(
			ArrayList<ErrorSintactico> listaErroresSintacticos) {
		this.listaErroresSintacticos = listaErroresSintacticos;
	}


}

