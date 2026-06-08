//Made by Lilian Charalampakos
import java.util.Random;

class River {
    private int numberOfpacks;	
    private int cardsLeft;
    private int numberOfcards;
    private Card[] array;	
    private String[] cardarray = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    
    public River(int numberOfpacks){
        this.numberOfpacks = numberOfpacks;		
        this.numberOfcards = numberOfpacks * 52;		
        this.cardsLeft = this.numberOfcards;		
        this.array = new Card[numberOfcards];
        
        String[] cardarray2 = new String[numberOfcards];
        for(int j=0; j<numberOfcards; j++){
            cardarray2[j] = cardarray[j%13];
        }
        for(int i = 0; i<numberOfcards; i++){				
            array[i] = new Card(cardarray2[i]);				
        }
    }
    
    public Card nextCard(){
        Random rndm = new Random();
        if(cardsLeft == 0){
            return null;
        }
        // Create a random value between 0 and cardsLeft-1 
        int random = rndm.nextInt(cardsLeft);			
        Card drawnCard = array[random];
        
        // Swap with the last card 
        array[random] = array[cardsLeft - 1];
        array[cardsLeft - 1] = drawnCard;
        
        cardsLeft--;		
        return drawnCard;		
    }
    
    public boolean shouldRestart(){
        // Return true if remaining cards fall below 1/4 of original 
        return cardsLeft < (numberOfcards / 4);
    }
    
    public int restart(){
        // Shuffle by resetting cardsLeft 
        cardsLeft = numberOfcards; 
        return cardsLeft;
    }
    
    public String toString(){
        String string = "";
        for(int i=0; i<cardsLeft; i++){
            string += array[i] + " ";
        }
        return string;
    }
}