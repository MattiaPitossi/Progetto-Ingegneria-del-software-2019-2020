package modelli;

public interface Liste {

    //verifica che l'elemento non sia gia' in lista
    boolean alreadyExist(String nameToVerify);

    //stampa la lista
    void printList();

    //ritorna la dimensione della lista
    int getListSize();

    //verifica se le liste sono vuote
    boolean isEmptyList();

    
}