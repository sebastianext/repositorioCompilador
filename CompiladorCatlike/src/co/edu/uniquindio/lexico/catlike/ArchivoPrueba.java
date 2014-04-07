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
package co.edu.uniquindio.lexico.catlike;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * Clase que representa un Archivo.
 */
public class ArchivoPrueba {

	public String leerArchivo(String rutaArchivo){
		
		BufferedReader entrada=null;
		String texto="";
		String codigo="";
		try {
			entrada=new BufferedReader(new FileReader(rutaArchivo));
			while((texto=entrada.readLine())!=null){
				if (!texto.equals(null)) {
					codigo+=texto;
				}
			}
//			System.out.println(codigo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				entrada.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return codigo;

	}

}
