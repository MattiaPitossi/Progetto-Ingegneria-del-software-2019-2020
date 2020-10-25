package modelli;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimerTask;

import modelli.liste.ListaAttuatori;

public class RegolaSempreVera extends TimerTask{
	
	private String nomeRegola;
	private AntecedenteSempreVero antecedente;
	private Conseguente conseguente;
	private SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
	private boolean attivaDisattiva;
	
	public RegolaSempreVera (String nomeRegola, AntecedenteSempreVero antecedente, Conseguente conseguente) {
		this.nomeRegola = nomeRegola;
		this.antecedente = antecedente;
		this.conseguente = conseguente;
		this.attivaDisattiva = true;
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

	public boolean getAttivaDisattiva() {
		return attivaDisattiva;
	}

	public void setAttivaDisattiva(boolean attivaDisattiva) {
		this.attivaDisattiva = attivaDisattiva;
	}

	@Override
	public void run() {
		
		//Viene trasformata l'ora in un valore float e salvato 
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		conseguente.setStart(Float.valueOf(sdf.format(timestamp)));
		
		//Il conseguente puo' contenere piu' azioni 
		for(Azioni azione : conseguente.getArrayAzioni()) {
			
			//Ciclo for per trovare la posizione dell'attuatore 
			for(int k = 0; k < ListaAttuatori.getInstance().getListSize(); k++) {
				
				//If per verificare se il nome dell'attuatore e' lo stesso dell'azione 
				if(ListaAttuatori.getInstance().getActuatorFromList(k).getNomeAttuatore().equalsIgnoreCase(azione.getNomeAttuatore())) {
					
					System.out.println("L'operazione delle regola e' soddisfatta, l'azione/azioni del/degli attuatore/attuatori e' stata effettuata/sono state effettuate");
					//Se if si avvera' allora viene impostato il valore contenuto nell'azione
					//Non serve prenderlo dalla categoria visto che e' gia' stato preso nel metodo della creazione dell'azione
					ListaAttuatori.getInstance().getActuatorFromList(k).setModalita(azione.getValore());
				}
			}
		}
	}
	
	

}
