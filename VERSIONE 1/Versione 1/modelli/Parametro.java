package modelli;

public class Parametro {
    private String nomeParametro;
    private int valore;

    public Parametro(String nomeParametro, int valore){
        this.nomeParametro = nomeParametro;
        this.valore = valore;

    }

    public String getNomeParametro() {
        return nomeParametro;
    }

    public void setNomeParametro(String nomeParametro) {
        this.nomeParametro = nomeParametro;
    }

    public int getValore() {
        return valore;
    }

    public void setValore(int valore) {
        this.valore = valore;
    }

}
