package modelli.categorie;

import java.util.ArrayList;

public class CategoriaSensori {
    
    private String nome;
    private String descrizione;
    private String datiRilevati;
    private ArrayList<String> dominioValoriRilevati;

    //Costuttore del sensore in base alle richieste del progetto
    public CategoriaSensori(String nome, String descrizione, String datiRilevati){
        this.nome = nome;
        this.descrizione = descrizione;
        this.datiRilevati = datiRilevati;
    }

    //Costuttore del sensore non numerico
    public CategoriaSensori(String nome, String descrizione, ArrayList<String> dominioValoriRilevati){
        this.nome = nome;
        this.descrizione = descrizione;
        this.dominioValoriRilevati = dominioValoriRilevati;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void getDatiRilevati() {
        for(String item: dominioValoriRilevati){
            System.out.println(item.toString());
        }
    }

    public void setDatiRilevati(String datiRilevati) {
        this.datiRilevati = datiRilevati;
    }

}