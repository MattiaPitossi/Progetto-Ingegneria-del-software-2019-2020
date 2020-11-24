package modelli;

import java.util.ArrayList;

public class Conseguente {
    
	private ArrayList<Azioni> arrayAzioni;
	private float start;
	
	public Conseguente (ArrayList<Azioni> arrayAzioni) {
		this.arrayAzioni = arrayAzioni;
		this.start = (float) 000;
	}

	/**
	 * @return la lista delle azioni del conseguente
	 */
	public ArrayList<Azioni> getArrayAzioni() {
		return arrayAzioni;
	}

	/**
	 * @param start imposta l'inizio dell'orario di avvio
	 */
	public void setStart(float start) {
		this.start = start;
	}
	
	
}
