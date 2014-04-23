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
			//			return new DeclaracionClase(modificadorAcceso,identificador, cuerpoClase);
		}
		else {
			String mensaje="Falta la llave de cierre.";
			listaErroresSintacticos.add(new ErrorSintactico(mensaje, tokenActual.getIndiceSiguiente(), tokenActual));

		}
		return new DeclaracionClase(modificadorAcceso,identificador, cuerpoClase);
	}

	private void modoPanicoClaseLLaveApertura() {
		while(!tokenActual.getLexema().equals(ConstantesLexema.PUBLIC)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)||
				tokenActual.getLexema().equals(ConstantesLexema.PRIVATE)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)||esTipoVariable()||esTipoMetodo()){
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

		ArrayList<DeclaracionVariable> bloqueVariables= esBloqueVariables();
		ArrayList<DeclaracionMetodo> bloqueMetodos= esBloqueMetodos();
		return new CuerpoClase(null, null, bloqueVariables, bloqueMetodos);

	}

	public DeclaracionVariable esDeclaracionVariable() {

		TokenCatlike modificadorAcceso=null;
		TokenCatlike tipoDato=null;
		TokenCatlike identificador=null;
		
		if (esModificadorAcceso()) {
			modificadorAcceso=tokenActual;
			darSiguienteToken();
		}
		if(esTipoVariable()) {
			tipoDato=tokenActual;
			darSiguienteToken();
		}else {
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)) {
			identificador=tokenActual;
			darSiguienteToken();
		}else {
			
			if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADORMETODO)) {
				realizarBactracking(indice);
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
			return null;
		}
	}

	

	public DeclaracionVariable esSubDeclaracionVariable() {

		TokenCatlike tipoDato=null;
		TokenCatlike identificador=null;
		

		if(esTipoVariable()) {
			tipoDato=tokenActual;
			darSiguienteToken();
		}else {
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)) {
			identificador=tokenActual;
			darSiguienteToken();
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
			darSiguienteToken();
			return new DeclaracionVariable(tipoDato, identificador);
		}else {
			//manejo de error;
			return null;
		}	
	}

	public DeclaracionMetodo esDeclaracionMetodo() {
		TokenCatlike modificadorAcceso=null;
		TokenCatlike tipo=null;
		TokenCatlike identificador=null;
//		TokenCatlike parentesisApertura=null;
//		TokenCatlike parentesisCierre=null;

		if (tokenActual.getLexema().equals(ConstantesLexema.PUBLIC)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)||
				tokenActual.getLexema().equals(ConstantesLexema.PRIVATE)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)) {
			modificadorAcceso=tokenActual;
			darSiguienteToken();
		}
		if (esTipoMetodo()) {
			tipo=tokenActual;
			darSiguienteToken();
		}else {
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADORMETODO)) {
			identificador=tokenActual;
			darSiguienteToken();
		}else {
			//manejo de error
			
				String mensaje = "Debe ser un identificador de metodo";
				listaErroresSintacticos.add(new ErrorSintactico (mensaje, tokenActual.getIndiceSiguiente(), tokenActual));
				if (!tokenActual.getTipo().equals(ConstantesTipos.PARENTESISAPERTURA)) {
					darSiguienteToken();
				}
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISAPERTURA)) {
//			parentesisApertura=tokenActual;
			darSiguienteToken();
		}else {
			//manejo de error
			return null;
		}
		ArrayList<Parametro> listaParametros=esListaParametros();

		if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISCIERRE)) {
//			parentesisCierre=tokenActual;
			darSiguienteToken();
		}else {
			//manejo de error
			return null;
		};

		CuerpoMetodo cuerpoMetodo= esCuerpoMetodo();
		if (cuerpoMetodo!=null) {
			darSiguienteToken();
			return new DeclaracionMetodo(modificadorAcceso, tipo, identificador, parentesisApertura,listaParametros, parentesisCierre, cuerpoMetodo);
		}else {
			//manejo de error
			return null;
		}


	}
	public CuerpoMetodo esCuerpoMetodo() {

		ArrayList<DeclaracionVariable> bloqueSubvariable=esBloqueSubVariables();
		ArrayList listaSentencias= esListaSentencias();
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
		ArrayList<Parametro> listaParametros = new ArrayList<Parametro>();
		Parametro parametro = esParametro();
		while(parametro!=null)
		{
			if (tokenActual.getLexema().equals(ConstantesLexema.COMA)) {
				darSiguienteToken();
				listaParametros.add(parametro);
				parametro = esParametro();
			}else {
				//manejo de error
			}

		}
		return listaParametros;
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
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)) {
			identificador=tokenActual;
			darSiguienteToken();
			return new Parametro(tipo, identificador);
		}else {
			return null;
		}
	}
	public Parametro esParametroInvocacion() {

		TokenCatlike identificador=null;

		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)) {
			identificador=tokenActual;
			darSiguienteToken();
			return new Parametro(identificador);
		}else {
			return null;
		}
	}
	public ArrayList esListaSentencias() {
		ArrayList listaSentencias = new ArrayList();
		SenteciaPara sentenciaPara = esSenteciaPara();
		SentenciaSi sentenciaSi=esSentenciaSi();
		SentenciaAsignacion sentenciaAsignacion=esSentenciaAsignacion();
		while(sentenciaPara!=null || sentenciaSi!=null || sentenciaAsignacion!=null )
		{
			if (sentenciaPara!=null) {
				listaSentencias.add(sentenciaPara);
				sentenciaPara = esSenteciaPara();
				sentenciaSi=esSentenciaSi();
				sentenciaAsignacion=esSentenciaAsignacion();
			}
			if (sentenciaSi!=null) {
				listaSentencias.add(sentenciaSi);
				sentenciaPara = esSenteciaPara();
				sentenciaSi=esSentenciaSi();
				sentenciaAsignacion=esSentenciaAsignacion();
			}
			if (sentenciaAsignacion!=null) {
				listaSentencias.add(sentenciaAsignacion);
				sentenciaPara = esSenteciaPara();
				sentenciaSi=esSentenciaSi();
				sentenciaAsignacion=esSentenciaAsignacion();
			}

		}
		return listaSentencias;
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
			return null;
		}
		DeclaracionVariable declaracionVariable= esDeclaracionVariable();
		if (declaracionVariable!=null) {
			darSiguienteToken();
		}else {
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
			darSiguienteToken();
		}else {
			//recuperar error
		}
		ArrayList<Expresion> listaExpresiones= esListaExpresiones();
		if (listaExpresiones!=null) {
			darSiguienteToken();
		}
		else {
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
			darSiguienteToken();
		}else {
			//recuperar error
		}
		Expresion expresion= esExpresion();
		if (expresion!=null) {
			darSiguienteToken();
		}else {
			//recuperar error
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISAPERTURA)) {
			darSiguienteToken();
		}else {
			//recuperar error
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.LLAVEAPERTURA)) {
			darSiguienteToken();
		}else {
			//manejo deerror
		}
		CuerpoPara cuerpoPara= esCuerpoPara();
		if (tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE)) {
			darSiguienteToken();
			return new SenteciaPara(declaracionVariable, listaExpresiones, expresion, cuerpoPara);
		}
		else {
			//manejo de error
			return null;
		}

	}
	public CuerpoPara esCuerpoPara() {

		ArrayList<DeclaracionVariable> bloqueSubvariable=esBloqueSubVariables();
		ArrayList listaSentencias= esListaSentencias();
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
			return null;
		}
		ArrayList<ExpresionRelacional> expresionesRelacionales= esListaExpresionesRelacionales(); 
		if (expresionesRelacionales!=null) {
			darSiguienteToken();
		}else {
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISCIERRE)) {
			darSiguienteToken();
		}else {
			//manejo de error
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.LLAVEAPERTURA)) {
			darSiguienteToken();
		}else {
			//manejo deerror
		}

		CuerpoSi cuerpoSi=esCuerpoSi();

		if (tokenActual.getTipo().equals(ConstantesTipos.LLAVECIERRE)) {
			darSiguienteToken();
			return new SentenciaSi(expresionesRelacionales, cuerpoSi);
		}
		else {
			//manejo de error
			return null;
		}


	}
	public ArrayList<ExpresionRelacional> esListaExpresionesRelacionales(){

		ArrayList<ExpresionRelacional> expresionesRelacionales = new ArrayList<ExpresionRelacional>();
		ExpresionRelacional expresionRelacional = esExpresionRelacional();
		while(expresionRelacional!=null)
		{
			expresionesRelacionales.add(expresionRelacional);
			expresionRelacional = esExpresionRelacional();
		}
		return expresionesRelacionales;
	}
	public CuerpoSi esCuerpoSi() {
		ArrayList<DeclaracionVariable> bloqueSubvariable=esBloqueSubVariables();
		ArrayList listaSentencias= esListaSentencias();
		Break breaK= esBreak();
		Return returN=esReturn();

		if (breaK!=null) {
			darSiguienteToken();
			return new CuerpoSi(bloqueSubvariable, listaSentencias, breaK, returN);
		}else if (returN!=null) {
			darSiguienteToken();
			return new CuerpoSi(bloqueSubvariable, listaSentencias, breaK, returN);
		}else {
			darSiguienteToken();
			return new CuerpoSi(bloqueSubvariable, listaSentencias, breaK, returN);
		}
	}
	//Revisar en expresion
	public SentenciaAsignacion esSentenciaAsignacion() {
		TokenCatlike identificador=null;
		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)) {
			identificador=tokenActual;
			darSiguienteToken();

		}else {
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.OPERADORASIGNACION)) {
			darSiguienteToken();
		}
		Expresion expresion= esExpresion();
		if (expresion!=null) {
			darSiguienteToken();
		}else {
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
			darSiguienteToken();
			return new SentenciaAsignacion(identificador, expresion);
		}
		return null;
	}
	//corregir
	public Expresion esExpresion() {
		TokenCatlike expresionIz=null;
		TokenCatlike operadorAritmetico=null;
		TokenCatlike expresionDer=null;


		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)||
				tokenActual.getTipo().equals(ConstantesTipos.ENTERO)) {
			expresionIz=tokenActual;
			darSiguienteToken();	
		}else {
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.OPERADORARITMETICO)) {
			operadorAritmetico=tokenActual;
			darSiguienteToken();
		}else {
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)||
				tokenActual.getTipo().equals(ConstantesTipos.ENTERO)) {
			expresionDer=tokenActual;
			darSiguienteToken();	
			return new Expresion(expresionIz, operadorAritmetico, expresionDer);
		}else {
			return null;
		}

	}
	public ArrayList<Expresion> esListaExpresiones(){

		ArrayList<Expresion> expresiones= new ArrayList<Expresion>();
		Expresion expresion= esExpresion();
		while(expresion!=null)
		{
			expresiones.add(expresion);
			expresion = esExpresion();
		}
		return expresiones;
	}

	public ExpresionRelacional esExpresionRelacional() {

		TokenCatlike operadorRelacional=null;

		Expresion expresionIzq=esExpresion();
		if (expresionIzq!=null) {
			darSiguienteToken();	
		}else {
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.OPERADORRELACIONAL)) {
			operadorRelacional=tokenActual;
			darSiguienteToken();
		}else {
			return null;
		}
		Expresion esExpresionDer=esExpresion();
		if (esExpresionDer!=null) {
			darSiguienteToken();
			return new ExpresionRelacional(expresionIzq, operadorRelacional, esExpresionDer);
		}else {
			//manejo de error
			return null;
		}

	}

	public Return esReturn() {

		if (tokenActual.getLexema().equals(ConstantesLexema.RETURN)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)) {
			darSiguienteToken();
		}else {
			return null;
		}
		Expresion expresion= esExpresion();
		if (expresion!=null) {
			darSiguienteToken();
		}else if (tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)
				&& tokenActual.getLexema().equals(ConstantesLexema.NULL)) {
			darSiguienteToken();

		}else if (tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)
				&& tokenActual.getLexema().equals(ConstantesLexema.TRUE)) {
			darSiguienteToken();
		}else if (tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)
				&& tokenActual.getLexema().equals(ConstantesLexema.FALSE)) {
			darSiguienteToken();
		}else if (tokenActual.getTipo().equals(ConstantesTipos.CADENACARACTERES)) {
			darSiguienteToken();
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
			darSiguienteToken();
			return new Return();f
		}

		return null;
	}

	public Break esBreak() {
		TokenCatlike breaK=null;
		if (tokenActual.getLexema().equals(ConstantesLexema.BREAK)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)) {
			breaK=tokenActual;
			darSiguienteToken();
		}else{
			return null;
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
			return new Break(breaK);
		}else{
			//manejar error
		}
		return null;
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
			return null;
		}
		ArrayList<Parametro> parametros=esListaParametrosInvocacion();
		if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISCIERRE)) {
			darSiguienteToken();
		}else {
			//manejo de error
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
			darSiguienteToken();
			return new InvocacionMetodo(identificador, parametros);
		}
		else {
			//manejo de error
			return null;
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
			declaracionVariable = esSubDeclaracionVariable();
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

		if (esTipoVariable()||tokenActual.getLexema().equals(ConstantesLexema.VOID)&&
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

}
