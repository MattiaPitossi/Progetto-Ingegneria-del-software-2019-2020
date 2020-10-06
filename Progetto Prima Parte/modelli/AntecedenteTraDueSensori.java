package modelli;

public class AntecedenteTraDueSensori {
	
	private String nomeSensore;
	private String nomeSecondoSensore;
	private String operatoreBooleano;
	
	public AntecedenteTraDueSensori(String nomeSensore, String nomeSecondoSensore, String operatoreBooleano) {
		this.nomeSensore = nomeSensore;
		this.nomeSecondoSensore = nomeSecondoSensore;
		this.operatoreBooleano = operatoreBooleano;
	}

	public String getNomeSensore() {
		return nomeSensore;
	}

	public void setNomeSensore(String nomeSensore) {
		this.nomeSensore = nomeSensore;
	}

	public String getNomeSecondoSensore() {
		return nomeSecondoSensore;
	}

	public void setNomeSecondoSensore(String nomeSecondoSensore) {
		this.nomeSecondoSensore = nomeSecondoSensore;
	}

	public String getOperatoreBooleano() {
		return operatoreBooleano;
	}

	public void setOperatoreBooleano(String operatoreBooleano) {
		this.operatoreBooleano = operatoreBooleano;
	}
	
	

}
