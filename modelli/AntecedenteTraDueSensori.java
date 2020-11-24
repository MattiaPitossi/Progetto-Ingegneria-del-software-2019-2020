package modelli;

public class AntecedenteTraDueSensori {
	
	private String nomeSensore;
	private String nomeSecondoSensore;
	private String operatoreBooleano;
	private float time;
	private String operatoreBooleanTime;
	
	public AntecedenteTraDueSensori(String nomeSensore, String nomeSecondoSensore, String operatoreBooleano, float time, String operatoreBooleanTime) {
		this.nomeSensore = nomeSensore;
		this.nomeSecondoSensore = nomeSecondoSensore;
		this.operatoreBooleano = operatoreBooleano;
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
	 * @return il nome del sencondo sensore associato all'antecedente
	 */
	public String getNomeSecondoSensore() {
		return nomeSecondoSensore;
	}

	/**
	 * @return l'operatore booleano associato all'antecedente
	 */
	public String getOperatoreBooleano() {
		return operatoreBooleano;
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
