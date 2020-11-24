package modelli;

public class Azioni {

	private String nomeAttuatore;
	private String valore;
	
	public Azioni(String nomeAttuatore, String valore) {
		this.nomeAttuatore = nomeAttuatore;
		this.valore = valore;
	}

	/**
	 * @return il nome dell'attuatore
	 */
	public String getNomeAttuatore() {
		return nomeAttuatore;
	}

	/**
	 * @return il valore associato all'azione
	 */
	public String getValore() {
		return valore;
	}
	
}
