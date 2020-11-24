package modelli;

public class Parametro {
    private String nomeParametro;
    private int valore;

    public Parametro(String nomeParametro, int valore){
        this.nomeParametro = nomeParametro;
        this.valore = valore;

    }

    /**
	 * @return  il valore associato al parametro
	 */
    public int getValore() {
        return valore;
    }

}
