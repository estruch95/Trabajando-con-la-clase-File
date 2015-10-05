package Funciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FuncionesImpl {
	
	FileReader fr=null;
	BufferedReader bfr=null;
	FileWriter fw=null;
	BufferedWriter bfw=null;
	
	private File fichero1, fichero2;

	public FuncionesImpl() {
		this.fichero1 = new File("lena.jpg");
		this.fichero2 = new File("lenaCopia.jpg");
		
		//EJECUCIÓN DE MÉTODOS
		
		//leerFichero1(fichero1);
		//copiarFichero(fichero1, fichero2);
		//buscarPalabra(fichero1, "Fichero5", false);
		//copiarInverso(fichero1,fichero2);
		//compararFicheros(fichero1, fichero2);
		//copiaBytes(fichero1, fichero2);
	}
	
	public void leerFichero1(File fichero){
			String cadena;
			
			try {
				if(fichero.exists()){
					fr = new FileReader(fichero);
					bfr = new BufferedReader(fr);
					cadena = bfr.readLine();
					
					while(cadena != null){
						System.out.println(cadena);
						cadena = bfr.readLine();
					}
					fr.close();
					bfr.close();
				}
				else{
					System.err.println("No existe el fichero que desea leer.");
				}	
			}
			catch (FileNotFoundException noEncontrado) {
				// TODO Auto-generated catch block
				noEncontrado.printStackTrace();
				System.err.println("No se encuentra el fichero que desea leer.");
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println("Error de lectura del fichero.");
			}
	}
	
	public void copiarFichero(File origen, File destino){
		String cadena;
		
			try {
				fr = new FileReader(origen);
				bfr = new BufferedReader(fr);
				fw = new FileWriter(destino);
				bfw = new BufferedWriter(fw);
				cadena = bfr.readLine();
				
				while(cadena != null){
					bfw.write(cadena+"\n");
					cadena = bfr.readLine();
				}
				bfr.close();
				bfw.close();
			}
			catch (FileNotFoundException noEncontrado) {
				// TODO Auto-generated catch block
				noEncontrado.printStackTrace();
				System.err.println("Fichero origen no encontrado.");
			}
			catch (IOException error) {
				// TODO Auto-generated catch block
				error.printStackTrace();
				System.err.println("Error de escritura en el fichero.");
			}
	}
	
	public void buscarPalabra(File fichero, String palabra, boolean primera){
		String cadena;
		boolean encontrada=false;
		ArrayList<String> lineas = new ArrayList<String>();
		int linea=1;
		
		try {
			fr = new FileReader(fichero);
			bfr = new BufferedReader(fr);
			cadena = bfr.readLine();
			
			while(cadena != null){
				if(cadena.equals(palabra)){
					encontrada=true;
					lineas.add(String.valueOf(linea));
					cadena = bfr.readLine();
					linea++;
				}
				else{
					cadena = bfr.readLine();
					linea++;
				}
			}
			linea=1;
			fr.close();
			bfr.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(encontrada == true && primera == true){
			System.out.println("Se ha encontrado la palabra"+palabra+" en la linea: "+lineas.get(0));
		}
		else if(encontrada == true && primera == false){
			System.out.println("Se ha encontrado la palabra"+palabra+" en la linea: "+lineas.get(lineas.size()-1));
		}
		else{
			System.out.println("No se ha encontrado la palabra.");
		}
	}
	
	public void copiarInverso(File origen, File destino){
		ArrayList<String> cadenas = new ArrayList<String>();
		String cadena;
		
		try {
			fr = new FileReader(origen);
			bfr = new BufferedReader(fr);
			fw = new FileWriter(destino);
			bfw = new BufferedWriter(fw);
			cadena = bfr.readLine();
			
			while(cadena != null){
				cadenas.add(cadena);
				cadena = bfr.readLine();
			}
			bfr.close();
			
			for(int x=cadenas.size()-1;x>=0;x--){
				bfw.write(cadenas.get(x)+"\n");
			}
			bfw.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void compararFicheros(File origen, File destino){
		String cadena1, cadena2;
		boolean iguales=true;
		
		try {
			//Lectura fichero1
			fr = new FileReader(origen);
			bfr = new BufferedReader(fr);
			//Lectura fichero2
			FileReader fr2 = new FileReader(destino);
			BufferedReader bfr2 = new BufferedReader(fr2);
			cadena1 = bfr.readLine();
			cadena2 = bfr2.readLine();
			
			while(cadena1 != null){
				if(cadena1.equals(cadena2)){
					cadena1 = bfr.readLine();
					cadena2 = bfr2.readLine();
				}
				else{
					iguales=false;
					cadena1 = bfr.readLine();
					cadena2 = bfr2.readLine();
				}
			}
			bfr.close();
			bfr2.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(iguales == true){
			System.out.println("Ficheros iguales");
		}
		else{
			System.out.println("Ficheros no iguales");
		}
	}
	
	public void copiaBytes(File origen, File destino){
		int cadena;
		
		try {
			if(origen.exists()){
				FileInputStream fi = new FileInputStream(origen);
				FileOutputStream fo = new FileOutputStream(destino);
				
				byte[] buffer = new byte[1024*1024];
				
				while((cadena = fi.read(buffer)) > 0){
					fo.write(buffer, 0, cadena);
				}
				fi.close();
				fo.close();
			}
			else{
				System.err.println("El fichero origen especificado no existe.");
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
