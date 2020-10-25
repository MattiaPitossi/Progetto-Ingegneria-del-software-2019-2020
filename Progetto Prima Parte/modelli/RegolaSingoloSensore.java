package modelli;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimerTask;

import modelli.liste.ListaAttuatori;
import modelli.liste.ListaSensori;

public class RegolaSingoloSensore extends TimerTask{

	private String nomeRegola;
	private AntecedenteSingoloSensore antecedente;
	private Conseguente conseguente;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
	private boolean attivaDisattiva;
	private String tipoRegola;
	
	public RegolaSingoloSensore (String nomeRegola, AntecedenteSingoloSensore antecedente, Conseguente conseguente, String tipoRegola) {
		this.nomeRegola = nomeRegola;
		this.antecedente = antecedente;
		this.conseguente = conseguente;
		this.attivaDisattiva = true;
		this.tipoRegola = tipoRegola;
	}

	public String getNomeRegola() {
		return nomeRegola;
	}

	public void setNomeRegola(String nomeRegola) {
		this.nomeRegola = nomeRegola;
	}

	public AntecedenteSingoloSensore getAntecedente() {
		return antecedente;
	}

	public void setAntecedente(AntecedenteSingoloSensore antecedente) {
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

	public String getTipoRegola() {
		return tipoRegola;
	}

	public void setTipoRegola(String tipoRegola) {
		this.tipoRegola = tipoRegola;
	}

	@Override
	public void run() {
		boolean operazioneSoddisfata = false;
		boolean sensoreTrovato = false;
		int valoreRilevato = 0;
		System.out.println("Verifica della regola " + nomeRegola + "... ");
		//Ciclo per trovare la posizione del sensore NUMERICO se non viene trovato allora il sensore e' NON NUMERICO 
		for(int i = 0; i < ListaSensori.getInstance().getListSize(); i++) {
			if(ListaSensori.getInstance().getSensorFromList(i).getNomeSensore().equalsIgnoreCase(antecedente.getNomeSensore())) {
				
				if(tipoRegola.equalsIgnoreCase("Numerico")) {
					//Viene trasformato il valore confrontato in un int 
					valoreRilevato = Integer.parseInt(antecedente.getValoreConfrontato());
					 
					//Ciclo if else per verificare quale e' l'operazione booleana da soddisfare
					if(antecedente.getOperatoreBooleano().equalsIgnoreCase(">")) {
						//Ciclo che verifica se l'operazione booleana > e' verificata
						if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() > valoreRilevato) {
							operazioneSoddisfata = true;
						} else {
							System.out.println("L'operazione non è soddisfata");
						}
					} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase(">=")) {
						
						//Ciclo che verifica se l'operazione booleana >= e' verificata
						if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() >= valoreRilevato) {
							operazioneSoddisfata = true;
						} else {
							System.out.println("L'operazione non è soddisfata");
						}
					} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase("<")) {
						
						//Ciclo che verifica se l'operazione booleana < e' verificata
						if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() < valoreRilevato) {
							operazioneSoddisfata = true;
						} else {
							System.out.println("L'operazione non è soddisfata");
						}
					} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase("<=")) {
						
						//Ciclo che verifica se l'operazione booleana <= e' verificata
						if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() <= valoreRilevato) {
							operazioneSoddisfata = true;
						} else {
							System.out.println("L'operazione non è soddisfata");
						}
					} else if(antecedente.getOperatoreBooleano().equalsIgnoreCase("=")) {
						
						//Ciclo che verifica se l'operazione booleana = e' verificata
						if(ListaSensori.getInstance().getSensorFromList(i).getValoreRilevato() == valoreRilevato) {
							operazioneSoddisfata = true;
						}  else {
							System.out.println("L'operazione non è soddisfata");
						}
					}
				} else {
					//Ciclo per trovare la posizione del sensore NON NUMERICO
					for(int j = 0; j < ListaSensori.getInstance().getListSize(); j++) {
						if(ListaSensori.getInstance().getSensorFromList(j).getNomeSensore().equalsIgnoreCase(antecedente.getNomeSensore())) {
							
							//Ciclo che verifica se l'operazione booleana = e' verificata
							if(ListaSensori.getInstance().getSensorFromList(j).getValoreRilevatoNonNumerico() == antecedente.getValoreConfrontato()) {
								operazioneSoddisfata = true;
							}  else {
								System.out.println("L'operazione non è soddisfata");
							}
						}
					}
				}
			}
		}
		
		
		
		//If per verificare se il conseguente deve partire 
		if(operazioneSoddisfata) {
			
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
}
