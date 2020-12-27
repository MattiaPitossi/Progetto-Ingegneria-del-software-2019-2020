package modelli.liste;

public class ListaAttuatoriView implements ListeView{

	@Override
	public void printList(String nomeAttuatore, int i) {
		System.out.println(i +". " + nomeAttuatore);
		
	}
	
	public void printNomeAttuatore(int i, String nomeAttuatore) {
		System.out.println(i + ". " + nomeAttuatore);
	}	

}
