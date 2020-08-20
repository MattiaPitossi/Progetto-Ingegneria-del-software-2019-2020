package model;
public class CategoriaAttuatori {
    
    private String nome;
    private String descrizione;
    private String[] modalitaOperative;

    //Costuttore del sensore in base alle richieste del progetto
    public CategoriaAttuatori(String nome, String descrizione, String[] modalitaOperative){
        this.nome = nome;
        this.descrizione = descrizione;
        this.modalitaOperative = modalitaOperative;
    }
    
}