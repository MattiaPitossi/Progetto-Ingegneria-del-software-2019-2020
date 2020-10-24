package modelli;

import java.util.ArrayList;

public class Conseguente {
    
	private ArrayList<Azioni> arrayAzioni;
	private float start;
	
	public Conseguente (ArrayList<Azioni> arrayAzioni) {
		this.arrayAzioni = arrayAzioni;
		this.start = (float) 000;
	}

	public ArrayList<Azioni> getArrayAzioni() {
		return arrayAzioni;
	}

	public void setArrayAzioni(ArrayList<Azioni> arrayAzioni) {
		this.arrayAzioni = arrayAzioni;
	}

	public float getStart() {
		return start;
	}

	public void setStart(float start) {
		this.start = start;
	}
	
	
}
