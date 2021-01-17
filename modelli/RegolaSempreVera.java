package modelli;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimerTask;

import modelli.liste.ListaAttuatoriController;
import modelli.liste.ListaAttuatoriModel;

public class RegolaSempreVera extends TimerTask{
	
	private String nomeRegola;
	private AntecedenteSempreVero antecedente;
	private Conseguente conseguente;
	private SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
	private boolean attivaDisattiva;
	private ListaAttuatoriController controllerAttuatori = new ListaAttuatoriController();
	
	public RegolaSempreVera (String nomeRegola, AntecedenteSempreVero antecedente, Conseguente conseguente) {
		this.nomeRegola = nomeRegola;
		this.antecedente = antecedente;
		this.conseguente = conseguente;
		this.attivaDisattiva = true;
	}

	/**
	 * @return  il nome della regola sempre vera
	 */
	public String getNomeRegola() {
		return nomeRegola;
	}

	/**
	 * @return  il conseguente associato alla regola
	 */
	public Conseguente getConseguente() {
		return conseguente;
	}

	/**
	 * @return  ritorna l'operatore booleano relativo alla regola che indica il suo stato 
	 */
	public boolean getAttivaDisattiva() {
		return attivaDisattiva;
	}

	/**
	 * @param attivaDisattiva consente di impostare lo stato attivo o disattivo della regola
	 */
	public void setAttivaDisattiva(boolean attivaDisattiva) {
		this.attivaDisattiva = attivaDisattiva;
	}

	@Override
	public void run() {
		
		if(attivaDisattiva) {
			//Viene trasformata l'ora in un valore float e salvato 
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			conseguente.setStart(Float.valueOf(sdf.format(timestamp)));

			System.out.println("");
			System.out.println("Verifica della regola " + nomeRegola + "... ");
			//Il conseguente puo' contenere piu' azioni 
			for(Azioni azione : conseguente.getArrayAzioni()) {
				
				//Ciclo for per trovare la posizione dell'attuatore 
				for(int k = 0; k < controllerAttuatori.getSize(); k++) {
					
					//If per verificare se il nome dell'attuatore e' lo stesso dell'azione 	
					if(controllerAttuatori.findAttuatoreAzione(k, azione)) {
						
						System.out.println("L'operazione delle regola " + nomeRegola + " e' soddisfatta, l'attuatore " + azione.getNomeAttuatore() + " e' stato impostato a " + azione.getValore());
						//Se if si avvera' allora viene impostato il valore contenuto nell'azione
						//Non serve prenderlo dalla categoria visto che e' gia' stato preso nel metodo della creazione dell'azione
						controllerAttuatori.setModalita(k, azione);
					}
				}
			}
		}
	}
	
	

}
