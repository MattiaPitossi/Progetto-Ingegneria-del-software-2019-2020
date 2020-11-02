package modelli;

public class AntecedenteSingoloSensore {
	
	private String nomeSensore;
	private String operatoreBooleano;
	private String valoreConfrontato;
	private float time;
	private String operatoreBooleanTime;
	
	public AntecedenteSingoloSensore(String nomeSensore, String operatoreBooleano, String valoreConfrontato, float time, String operatoreBooleanTime) {
		this.nomeSensore = nomeSensore;
		this.operatoreBooleano = operatoreBooleano;
		this.valoreConfrontato = valoreConfrontato;
		this.time = time;
		this.operatoreBooleano = operatoreBooleano;
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
