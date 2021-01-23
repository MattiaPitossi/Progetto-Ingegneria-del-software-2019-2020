package modelli.liste;


public abstract class RegoleTemplate {
	
	abstract boolean regolaDaVerificare();
	abstract boolean disattivaRegola();

	public final void DisattivaRegolaAutomaticamente(boolean regolaDaDisattivare) {
		
		if(regolaDaVerificare()) {
			disattivaRegola();
		}
	
	}
}
