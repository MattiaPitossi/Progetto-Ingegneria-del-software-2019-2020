package modelli;
public class CategoriaSensori {
    
    private String nome;
    private String descrizione;
    private String datiRilevati;

    //Costuttore del sensore in base alle richieste del progetto
    public CategoriaSensori(String nome, String descrizione, String datiRilevati){
        this.nome = nome;
        this.descrizione = descrizione;
        this.datiRilevati = datiRilevati;
    }

    public String getNome() {
        return nome;
    }

}