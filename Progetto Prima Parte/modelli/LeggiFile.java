package modelli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import modelli.categorie.CategoriaSensori;
import modelli.liste.ListaCategoriaSensori;
import utility.InputDati;


public class LeggiFile {
	

    private InputDati inputDati = new InputDati();

	public void letturaFile() throws IOException {
		
		boolean finito = false;
		String yesOrNo = "";
		ArrayList<String> dominioValoriRilevati = new ArrayList<>();
		CategoriaSensori categoriaCreata;
		
		Path pathToFile = Paths.get("Cartel1.csv");
		
		try(BufferedReader buff = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			
			String line = buff.readLine();
			
			while(line != null) {
				String[] attributes = line.split(";");
				
				//Se il primo attributo e' categoria sensore allora verra' creato una categoria sensore 
				if(attributes[0].equalsIgnoreCase("Categoria sensore")) {
					
					//Ciclo do while per permettere al manutentore di scegliere quali categorie vuole creare 
					do {
						yesOrNo = inputDati.leggiStringaNonVuota("Vuoi inserire la categoria " + attributes[1] + " (Y/N)?");
						
						if(yesOrNo.equalsIgnoreCase("Y")) {
							finito = true;
							
							//Se la categoria e' numerica allora verra' creata una categoria numerica 
							if(attributes[3].equalsIgnoreCase("Numerico")) {
								categoriaCreata = new CategoriaSensori(attributes[1], attributes[2], attributes[4]);
								
							//Se la categoria e' non numerica allora verra' creata una categoria non numerica 
							} else {
								for(int i = 4; attributes[i] != null; i++ ) {
									dominioValoriRilevati.add(attributes[i]);
								}
								
								categoriaCreata = new CategoriaSensori(attributes[1], attributes[2], dominioValoriRilevati);
							}
							
							ListaCategoriaSensori.getInstance().addToList(attributes[1], categoriaCreata);
							
						} else if(yesOrNo.equalsIgnoreCase("N")) {
							finito = true;
						} else {
							System.out.println("Puoi inserire solo Y o N.");
						}
						
					}while(!finito);
				}
				
			}

		}
	}

}
