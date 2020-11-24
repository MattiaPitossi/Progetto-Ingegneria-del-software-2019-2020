package modelli;

public class ModalitaOperativaParametrica {

    private String nome;
    private Parametro parametro;

    public ModalitaOperativaParametrica(String nome, Parametro parametro){
        this.nome = nome;
        this.parametro = parametro;

    }

    /**
	 * @return  il nome della modalita' operativa parametrica
	 */
    public String getNome() {
        return nome;
    }

    /**
	 * @return il parametro
	 */
    public Parametro getParametro() {
        return parametro;
    }
    
    
}
