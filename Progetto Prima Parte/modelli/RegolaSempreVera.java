package modelli;

import java.util.TimerTask;

import modelli.liste.ListaAttuatori;

public class RegolaSempreVera extends TimerTask{
	
	private String nomeRegola;
	private AntecedenteSempreVero antecedente;
	private Conseguente conseguente;
	
	public RegolaSempreVera (String nomeRegola, AntecedenteSempreVero antecedente, Conseguente conseguente) {
		this.nomeRegola = nomeRegola;
		this.antecedente = antecedente;
		this.conseguente = conseguente;
	}

	public String getNomeRegola() {
		return nomeRegola;
	}

	public void setNomeRegola(String nomeRegola) {
		this.nomeRegola = nomeRegola;
	}

	public AntecedenteSempreVero getAntecedente() {
		return antecedente;
	}

	public void setAntecedente(AntecedenteSempreVero antecedente) {
		this.antecedente = antecedente;
	}

	public Conseguente getConseguente() {
		return conseguente;
	}

	public void setConseguente(Conseguente conseguente) {
		this.conseguente = conseguente;
	}

	@Override
	public void run() {
		//Il conseguente puo' contenere piu' azioni 
		for(Azioni azione : conseguente.getArrayAzioni()) {
			
			//Ciclo for per trovare la posizione dell'attuatore 
			for(int k = 0; k < ListaAttuatori.getInstance().getListSize(); k++) {
				
				//If per verificare se il nome dell'attuatore e' lo stesso dell'azione 
				if(ListaAttuatori.getInstance().getActuatorFromList(k).getNomeAttuatore().equalsIgnoreCase(azione.getNomeAttuatore())) {
					
					//Se if si avvera' allora viene impostato il valore contenuto nell'azione
					//Non serve prenderlo dalla categoria visto che e' gia' stato preso nel metodo della creazione dell'azione
					ListaAttuatori.getInstance().getActuatorFromList(k).setModalita(azione.getValore());
				}
			}
		}
	}
	
	

}
