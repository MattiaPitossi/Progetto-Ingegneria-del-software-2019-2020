package modelli;

public class AntecedenteSingoloSensore {
	
	private String nomeSensore;
	private String operatoreBooleano;
	private String valoreConfrontato;
	
	public AntecedenteSingoloSensore(String nomeSensore, String operatoreBooleano, String valoreConfrontato) {
		this.nomeSensore = nomeSensore;
		this.operatoreBooleano = operatoreBooleano;
		this.valoreConfrontato = valoreConfrontato;
	}

	public String getNomeSensore() {
		return nomeSensore;
	}

	public void setNomeSensore(String nomeSensore) {
		this.nomeSensore = nomeSensore;
	}

	public String getOperatoreBooleano() {
		return operatoreBooleano;
	}

	public void setOperatoreBooleano(String operatoreBooleano) {
		this.operatoreBooleano = operatoreBooleano;
	}

	public String getValoreConfrontato() {
		return valoreConfrontato;
	}

	public void setValoreConfrontato(String valoreConfrontato) {
		this.valoreConfrontato = valoreConfrontato;
	}
	
	

}
