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

	public String getOperatoreBooleano() {
		return operatoreBooleano;
	}


	public String getValoreConfrontato() {
		return valoreConfrontato;
	}
	

}
