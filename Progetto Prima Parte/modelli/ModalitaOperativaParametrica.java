package modelli;

public class ModalitaOperativaParametrica {

    private String nome;
    private Parametro parametro;

    public ModalitaOperativaParametrica(String nome, Parametro parametro){
        this.nome = nome;
        this.parametro = parametro;

    }

    public String getNome() {
        return nome;
    }

    public Parametro getParametro() {
        return parametro;
    }
}
