package modelli.dispositivi;

import java.util.ArrayDeque;
import java.util.Deque;

public class AttuatoreCaretaker {

	final Deque<AttuatoreMemento> memento = new ArrayDeque<>();
	
	  public AttuatoreMemento getMemento() {
		  AttuatoreMemento attuatoreMemento = memento.pop();
	        return attuatoreMemento;
	    }

	  public void addMemento(AttuatoreMemento mementos) {
	    	memento.push(mementos);
	    }
	  
	  public boolean isEmpty() {
		  return memento.isEmpty();
	  }
}
