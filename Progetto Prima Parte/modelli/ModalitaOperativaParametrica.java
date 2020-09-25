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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Parametro getParametro() {
        return parametro;
    }

    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }
    
    
}
