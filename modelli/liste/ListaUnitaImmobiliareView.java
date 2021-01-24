package modelli.liste;

public class ListaUnitaImmobiliareView implements ListeView{
	
	@Override
	
	public void printList(String nomeUnita, int i) {
	         System.out.println(i +". " + nomeUnita);
	}

}
