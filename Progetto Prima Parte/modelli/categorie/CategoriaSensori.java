package modelli.categorie;

import java.util.ArrayList;

public class CategoriaSensori {
    
    private String nome;
    private String descrizione;
    private String datiRilevati;
    private ArrayList<String> dominioValoriRilevati;
    private String tipoCategoria;

    //Costuttore del sensore in base alle richieste del progetto
    public CategoriaSensori(String nome, String descrizione, String datiRilevati){
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipoCategoria = "Numerico";
        this.datiRilevati = datiRilevati;
    }

    //Costuttore del sensore non numerico
    public CategoriaSensori(String nome, String descrizione, ArrayList<String> dominioValoriRilevati){
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipoCategoria = "Non numerico";
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

    
    public ArrayList<String> getDominioValoriRilevati() {
		return dominioValoriRilevati;
	}

	public void setDominioValoriRilevati(ArrayList<String> dominioValoriRilevati) {
		this.dominioValoriRilevati = dominioValoriRilevati;
	}

	public void getDatiRilevati() {
    	int i = 1;
        for(String item: dominioValoriRilevati){
            System.out.println(i + item.toString());
            i++;
        }
    }

    public void setDatiRilevati(String datiRilevati) {
        this.datiRilevati = datiRilevati;
    }
    
    public String getTipoCategoria() {
    	return tipoCategoria;
    }

}