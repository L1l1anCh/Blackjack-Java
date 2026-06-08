//Made by Lilian Charalampakos
class Dealer {
    private River GameFlow;
    private Hand DealersHand;
    
    public Dealer(River GameFlow){
        this.GameFlow = GameFlow;
        this.DealersHand = new Hand(GameFlow.nextCard());
    }
    
    public Hand getHand(){
        return DealersHand;
    }
    
    public void draw(){
        DealersHand.addCard(GameFlow.nextCard()); 
    }
    
	public void deal(Player player){		
			Card drawnCard = GameFlow.nextCard();
			
			// If the player doesn't have a hand yet, create one with the drawn card
			if (player.getCustomersHand() == null) {
				player.setHand(new Hand(drawnCard));
			} 
			// If they already have a hand, just add the new card to it
			else {
				player.getCustomersHand().addCard(drawnCard);
			}
		}
    
    public void play(){		
        while(DealersHand.score() < 17){ 
            DealersHand.addCard(GameFlow.nextCard());
        }
    }
    
    public void settle(Player otherplayer){
        Hand playerHand = otherplayer.getCustomersHand();
        
        if(playerHand.isBust()){
            otherplayer.loses(); 
        } else if (DealersHand.isBust() || playerHand.score() > DealersHand.score()){
            otherplayer.wins(); 
        } else if (playerHand.score() < DealersHand.score()){
            otherplayer.loses(); 
        } else {
            System.out.println("Tie with " + otherplayer.getCustomer() + ". Nobody wins."); 
        }
    }
    
    public String toString(){
        return "Dealer: " + DealersHand.toString(); 
    }
}