package utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TestWhiteBox {
	
    public boolean alreadyExistsRoom(String roomToCheck, ArrayList<String> stanze){
        for(int i=0; i<stanze.size(); i++){
            if(stanze.get(i).equals(roomToCheck)) return true;
        }
        return false;
    }
    
    @Test
	void testTrue() {
    	ArrayList<String> stanze = new ArrayList<>();
    	stanze.add("Cucina");
    	stanze.add("Bagno");
    	stanze.add("Salotto");
    	assertEquals(true, alreadyExistsRoom("Cucina",stanze));
	}
    
    @Test
	void testFalse() {
    	ArrayList<String> stanze = new ArrayList<>();
    	stanze.add("Cucina");
    	stanze.add("Bagno");
    	stanze.add("Salotto");
    	assertEquals(false, alreadyExistsRoom("Garage",stanze));
	}
    

}
