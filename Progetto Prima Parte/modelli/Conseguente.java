package modelli;

import java.util.ArrayList;

public class Conseguente {
    
	private ArrayList<Azioni> arrayAzioni;
	
	public Conseguente (ArrayList<Azioni> arrayAzioni) {
		this.arrayAzioni = arrayAzioni;
	}

	public ArrayList<Azioni> getArrayAzioni() {
		return arrayAzioni;
	}

	public void setArrayAzioni(ArrayList<Azioni> arrayAzioni) {
		this.arrayAzioni = arrayAzioni;
	}
	
}
