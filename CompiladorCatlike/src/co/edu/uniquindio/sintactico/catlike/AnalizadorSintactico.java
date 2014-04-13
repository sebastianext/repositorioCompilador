/**
 * 
 */
package co.edu.uniquindio.sintactico.catlike;

import java.util.ArrayList;

import org.omg.PortableInterceptor.ForwardRequest;

import co.edu.uniquindio.lexico.catlike.ConstantesLexema;
import co.edu.uniquindio.lexico.catlike.ConstantesTipos;
import co.edu.uniquindio.lexico.catlike.TokenCatlike;

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
		}
		CuerpoClase cuerpoClase= esCuerpoClase();

		if (cuerpoClase!=null) {
			darSiguienteToken();
			return new DeclaracionClase(modificadorAcceso,identificador, cuerpoClase);
		}else {
			//manejo de erro
			return null;
		}
	}

	public CuerpoClase esCuerpoClase() {
		TokenCatlike llaveApertura=null;
		TokenCatlike llaveCierre=null;
		if (tokenActual.getTipo().equals(ConstantesTipos.LLAVEAPERTURA)) {
			llaveApertura=tokenActual;
			darSiguienteToken();
		}
		else {
			//recupercion de error
		}
		ArrayList<DeclaracionVariable> bloqueVariables= esBloqueVariables();
		ArrayList<DeclaracionMetodo> bloqueMetodos= esBloqueMetodos();

		if (tokenActual.getTipo().equals(ConstantesTipos.LLAVEAPERTURA)) {
			llaveApertura=tokenActual;
			darSiguienteToken();
			return new CuerpoClase(llaveApertura, llaveCierre, bloqueVariables, bloqueMetodos);
		}
		else {
			//recupercion de error
			return null;
		}


	}

	public DeclaracionVariable esDeclaracionVariable() {

		TokenCatlike modificadorAcceso=null;
		TokenCatlike tipoDato=null;
		TokenCatlike identificador=null;
		TokenCatlike separadorSentenica=null;

		if (tokenActual.getLexema().equals(ConstantesLexema.PUBLIC)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)||
				tokenActual.getLexema().equals(ConstantesLexema.PRIVATE)&&
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)) {
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
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
			separadorSentenica= tokenActual;
			darSiguienteToken();
			return new DeclaracionVariable(modificadorAcceso,tipoDato, identificador,separadorSentenica);
		}else {
			//manejo de error;
		}


		return null;
	}

	public DeclaracionMetodo esDeclaracionMetodo() {
		TokenCatlike modificadorAcceso=null;
		TokenCatlike tipo=null;
		TokenCatlike identificador=null;
		TokenCatlike parentesisApertura=null;
		TokenCatlike parentesisCierre=null;

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
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISAPERTURA)) {
			parentesisApertura=tokenActual;
			darSiguienteToken();
		}
		ArrayList<Parametro> listaParametros=esListaParametros();

		if (tokenActual.getTipo().equals(ConstantesTipos.PARENTESISCIERRE)) {
			parentesisCierre=tokenActual;
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
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Parametro> esListaParametros() {
		ArrayList<Parametro> listaParametros = new ArrayList<Parametro>();
		Parametro parametro = esParametro();
		while(parametro!=null)
		{
			listaParametros.add(parametro);
			parametro = esParametro();
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
	public ListaSentencias esListaSentencias() {
		// TODO Auto-generated method stub
		return null;
	}
	public SenteciaPara esSenteciaPara() {
		// TODO Auto-generated method stub
		return null;
	}
	public CuerpoPara esCuerpoPara() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	public SentenciaAsignacion esSentenciaAsignacion() {
		// TODO Auto-generated method stub
		return null;
	}

	public Expresion esExpresion() {
		

		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)||
				tokenActual.getTipo().equals(ConstantesTipos.ENTERO)) {
			
			darSiguienteToken();	
		}else {
			return null;
		}
		
		return null;
	}

	public ExpresionRelacional esExpresionRelacional() {
		TokenCatlike expresionIz=null;
		TokenCatlike operadorRelacional=null;
		TokenCatlike expresionDer=null;

		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)||
				tokenActual.getTipo().equals(ConstantesTipos.ENTERO)) {
			expresionIz=tokenActual;
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
		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)||
				tokenActual.getTipo().equals(ConstantesTipos.ENTERO)) {
			expresionDer=tokenActual;
			darSiguienteToken();
			return new ExpresionRelacional(expresionIz, operadorRelacional, expresionDer);
		}else {
			//manejo de error
			return null;
		}

	}

	public Return esReturn() {
		// TODO Auto-generated method stub
		return null;
	}

	public Break esBreak() {
		TokenCatlike breaK=null;
		if (tokenActual.getLexema().equals("break")) {
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
		// TODO Auto-generated method stub
		return null;
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
				tokenActual.getTipo().equals(ConstantesTipos.PALABRARESERVADA)) {
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

}
