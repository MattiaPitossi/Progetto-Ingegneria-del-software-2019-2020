package modelli.liste;

import java.util.ArrayList;

import modelli.dispositivi.Sensore;

public class ListaSensoriView implements ListeView {
	
		@Override
	    public void printList(String nomeSensore, int i) {
			System.out.println(i +". " + nomeSensore);
	    }
		
		public void printNomeSensoreNonAllocato(String nomeSensore, int i) {
			System.out.println(i +". "+ nomeSensore + " non allocato ");
		}
		
		public void printValoriSensoreNumerico(String nomeSensore, String stanzaAssociata, int valoreRilevato, int i) {
		   System.out.println(i +". "+ nomeSensore + " allocato in "+  stanzaAssociata +" ,Valore rilevato(cfr. descrizione): " + valoreRilevato);
		}
		
		public void printValoriSensoreNonNumerico(String nomeSensore, String stanzaAssocita, String valoreRilevato,int i) {
			System.out.println(i +". "+ nomeSensore +" allocato in "+ stanzaAssocita +" ,Valote rilevato(cfr. descrizione): " + valoreRilevato);
		}
}
