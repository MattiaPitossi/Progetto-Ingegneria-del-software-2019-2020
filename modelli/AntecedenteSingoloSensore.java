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
		this.operatoreBooleanTime = operatoreBooleanTime;
	}

	/**
     * @return	il nome del sensore associato all'antecedente
     */
	public String getNomeSensore() {
		return nomeSensore;
	}

	/**
	 * @return l'operatore booleano associato all'antecedente
	 */
	public String getOperatoreBooleano() {
		return operatoreBooleano;
	}

	/**
	 * @return l'operatore booleano associato all'antecedente
	 */
	public String getValoreConfrontato() {
		return valoreConfrontato;
	}

	/**
	 * @return l'ora associata all'antecedente
	 */
	public float getTime() {
		return time;
	}

	/**
	 * @return ritorna l'operatore di confrontro tra tempo e valore
	 */
	public String getOperatoreBooleanTime() {
		return operatoreBooleanTime;
	}
	

}
