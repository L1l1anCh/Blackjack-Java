//Made by Lilian Charalampakos
import java.util.ArrayList;
import java.util.Scanner;

class Round {
    private Dealer BlackJackDealer;
    private ArrayList<Player> GamePlayers;
    private ArrayList<Player> SettledPlayers;
    Scanner input = new Scanner(System.in);
    
    public Round(River RiverFlow){		
        this.BlackJackDealer = new Dealer(RiverFlow);		
        this.GamePlayers = new ArrayList<Player>();
        this.SettledPlayers = new ArrayList<Player>(); 
    }
    
    public Player addPlayer(CasinoCustomer playeruno){
        Player OnePlayer = new Player(playeruno);
        GamePlayers.add(OnePlayer);
        return OnePlayer;
    }
    
    public void playRound(){
        // 1. All players place bets
        for(int i=0; i<GamePlayers.size(); i++){			
            GamePlayers.get(i).placeBet();			
        }
        
        // 2. Deal first card to all players, then one to dealer
        for(int i=0; i<GamePlayers.size(); i++){			
            BlackJackDealer.deal(GamePlayers.get(i));					
        }
        System.out.println(BlackJackDealer.toString()); // Dealer's visible card
        
        // 3. Deal second card to all players, then dealer draws a hidden card
        for(int i=0; i<GamePlayers.size(); i++){
            BlackJackDealer.deal(GamePlayers.get(i));
            System.out.println(GamePlayers.get(i)); // Print players' hands
        }
        BlackJackDealer.draw(); // Dealer's hidden card
        
        // 4. Check if Dealer has Blackjack
        if(BlackJackDealer.getHand().isBlackjack()){
            System.out.println("Dealer reveals hidden card: " + BlackJackDealer.getHand());
            System.out.println("Dealer has Blackjack!");
            for(int j=0; j<GamePlayers.size(); j++){				
                if(!GamePlayers.get(j).getCustomersHand().isBlackjack()){
                    GamePlayers.get(j).loses();
                } else {
                    System.out.println("Tie with " + GamePlayers.get(j).getCustomer() + ". Nobody wins.");
                }
            }
            System.out.println("Round is over.");
            return; // Exit round early
        }
        
        // 5. Normal Play (Dealer does NOT have Blackjack)
        for(int j=0; j<GamePlayers.size(); j++){
            Player currentPlayer = GamePlayers.get(j);
            
            if(currentPlayer.getCustomersHand().isBlackjack()){					
                currentPlayer.winsBlackjack();
            } else {
                this.playPlayer(currentPlayer);
            }
        }
        
        // 6. Dealer plays if there are players left to settle
        if (!SettledPlayers.isEmpty()) {
            System.out.println("Dealer reveals hidden card: " + BlackJackDealer.getHand());
            BlackJackDealer.play();
            System.out.println("Dealer's final hand: " + BlackJackDealer.getHand());
            
            // 7. Settle the remaining players
            for(int i=0; i<SettledPlayers.size(); i++){
                BlackJackDealer.settle(SettledPlayers.get(i));
            }
        } else {
             System.out.println("All players busted or got Blackjack. Dealer doesn't need to play.");
        }
    }
    
	private void playNormalHand(Player normalPlayer){
			while(true){
				System.out.print(normalPlayer.toString() + " - Do you want to hit? (Yes/No): ");
				String in = input.next();
				
				if(in.equalsIgnoreCase("Yes")){
					BlackJackDealer.deal(normalPlayer);
					System.out.println(normalPlayer.toString());
					
					if(normalPlayer.getCustomersHand().isBust()){
						System.out.println("Bust!");
						normalPlayer.loses();
						break;
					}
				} else if (in.equalsIgnoreCase("No")) {
					// Player stands
					SettledPlayers.add(normalPlayer);
					break;
				} else {
					System.out.println("Invalid command. Please type 'Yes' to hit or 'No' to stand.");
				}
			}	
		}
    
    private void playDoubledHand(Player doubledPlayer){
        doubledPlayer.doubleBet();
        BlackJackDealer.deal(doubledPlayer); // One card only
        System.out.println(doubledPlayer.toString());
        
        if(doubledPlayer.getCustomersHand().isBust()){
            System.out.println("Bust!");
            doubledPlayer.loses();
        } else {
            SettledPlayers.add(doubledPlayer);
        }
    }
    
    private void playSplitHand(Player playerSplit){
        Hand[] splitHands = playerSplit.getCustomersHand().split();
        
        // Create two separate player instances to track the two hands
        Player split1 = new Player(playerSplit.getCustomer(), splitHands[0], playerSplit.getCustomersBet());
        Player split2 = new Player(playerSplit.getCustomer(), splitHands[1], playerSplit.getCustomersBet());
        
        System.out.println("\nPlaying Hand 1:");
        BlackJackDealer.deal(split1);
        System.out.println(split1.toString());
        playNormalHand(split1);
        
        System.out.println("\nPlaying Hand 2:");
        BlackJackDealer.deal(split2);
        System.out.println(split2.toString());
        playNormalHand(split2);
    }
    
    private void playPlayer(Player play){
        if(play.getCustomersHand().canSplit() && play.wantsToSplit()){
            this.playSplitHand(play);
        } else if(play.wantsToDouble()){
            this.playDoubledHand(play);
        } else {
            this.playNormalHand(play);
        }
    }
}