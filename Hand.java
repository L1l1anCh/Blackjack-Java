//Made by Lilian Charalampakos
import java.util.ArrayList;

class Hand {
    private ArrayList<Card> HandList = new ArrayList<Card>();
    
    public Hand(Card card){
        HandList.add(card);
    }
    
    public Card addCard(Card card1){
        HandList.add(card1);	
        return card1;
    }

    public int score(){
        int p = 0;
        boolean hasAce = false;
        
        for (int i = 0; i < HandList.size(); i++) {
            p += HandList.get(i).getValue();
            if (HandList.get(i).isAce()) {
                hasAce = true;
            }
        }			
        
        // If hand has an Ace and adding 10 doesn't bust, add 10 
        if (hasAce && (p + 10) <= 21) {
            p += 10;
        }
        return p;
    }

    public boolean canSplit(){	
        // Must be exactly 2 cards that are equal 
        if (HandList.size() == 2 && (HandList.get(0)).equals(HandList.get(1))){
            return true;
        }
        return false;
    }

    public Hand[] split(){
        // Returns an array of two new hands 
        Hand[] splitHands = new Hand[2];
        splitHands[0] = new Hand(HandList.get(0));
        splitHands[1] = new Hand(HandList.get(1));
        return splitHands;
    }

    public boolean isBlackjack(){		
        // Blackjack is 21 points with exactly two cards 
        if(HandList.size() == 2 && score() == 21){
            return true;
        }
        return false;
    }
    
    public boolean isBust(){
        return score() > 21; 
    }
    
    public String toString(){
        String string = "";
        for(int i=0; i<HandList.size(); i++){
            string += HandList.get(i) + " ";
        }
        return string;
    }
}