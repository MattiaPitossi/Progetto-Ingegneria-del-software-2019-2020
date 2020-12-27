package modelli;

import java.io.IOException;

public class LettoreLibrerieController {
	
	private LettoreLibrerie fr = new LettoreLibrerie();

	public void letturaLibreriaCategorie() throws IOException {
		fr.letturaLibreriaCategorie();
	}
	
	public void letturaLibreriaRegole() throws IOException {
		fr.letturaLibreriaRegole();
	}
	
	public void letturaLibreriaUnita() throws IOException {
		fr.letturaLibreriaUnita();
	}
}
