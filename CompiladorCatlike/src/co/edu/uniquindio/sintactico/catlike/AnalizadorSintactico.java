/**
 * 
 */
package co.edu.uniquindio.sintactico.catlike;

import java.util.ArrayList;

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
		TokenCatlike identificador=null;
		if (tokenActual.getLexema().equals("public"))
			darSiguienteToken();
		if (tokenActual.getLexema().equals("class"))
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
			return new DeclaracionClase(identificador, cuerpoClase);
		}else {
			//manejo de erro
			return null;
		}


	}

	public CuerpoClase esCuerpoClase() {
		ArrayList<DeclaracionVariable> bloqueVariables= esBloqueVariables();
		ArrayList<DeclaracionMetodo> bloqueMetodos= esBloqueMetodos();

		return new CuerpoClase(bloqueVariables, bloqueMetodos);
	}

	public DeclaracionVariable esDeclaracionVariable() {
		TokenCatlike tipoDato=null;
		TokenCatlike identificador=null;
		
		if (tokenActual.getLexema().equals("public")||
			tokenActual.getLexema().equals("private")) {
			tipoDato=tokenActual;
			darSiguienteToken();
		}
		if (tokenActual.getTipo().equals(ConstantesTipos.IDENTIFICADOR)) {
			identificador=tokenActual;
			darSiguienteToken();
		}
		if (tokenActual.getLexema().equals(ConstantesTipos.SEPARADORSENTENCIA)) {
			return new DeclaracionVariable();
		}else {
			//manejo de error;
		}
		
		return null;
	}

	public DeclaracionMetodo esDeclaracionMetodo() {
		// TODO Auto-generated method stub
		return null;
	}
	public CuerpoMetodo esCuerpoMetodo() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListaParametros esListaParametros() {
		// TODO Auto-generated method stub
		return null;
	}

	public Parametro esParametro() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	public ExpresionRelacional esExpresionRelacional() {
		// TODO Auto-generated method stub
		return null;
	}

	public Return esReturn() {
		// TODO Auto-generated method stub
		return null;
	}

	public Break esBreak() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
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
