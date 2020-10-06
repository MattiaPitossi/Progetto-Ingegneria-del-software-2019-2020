package modelli;

public class Azioni {

	private String nomeAttuatore;
	private String valore;
	
	public Azioni(String nomeAttuatore, String valore) {
		this.nomeAttuatore = nomeAttuatore;
		this.valore = valore;
	}

	public String getNomeAttuatore() {
		return nomeAttuatore;
	}

	public void setNomeAttuatore(String nomeAttuatore) {
		this.nomeAttuatore = nomeAttuatore;
	}

	public String getValore() {
		return valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}
	
	
}
