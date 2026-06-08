//Made by Lilian Charalampakos
import java.util.Scanner;

class BlackjackTable {
    Scanner input = new Scanner(System.in);
    private River BlackJackRiver;
    private CasinoCustomer[] BlackjackArray;
    private int numberOfPlayers;
        
    public BlackjackTable(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
        BlackJackRiver = new River(6); 
        BlackjackArray = new CasinoCustomer[numberOfPlayers];
        for(int i=0; i<numberOfPlayers; i++){
            BlackjackArray[i] = this.createCasinoCustomer();
        }
    }
    
    private CasinoCustomer createCasinoCustomer(){
        System.out.println("\nInput Player name: ");
        String in = input.next();
        
        int num = 0;
        System.out.println("Input Available Money for " + in + ": ");
        while (true) {
            if (input.hasNextInt()) {
                num = input.nextInt();
                if (num > 0) {
                    break; // Valid amount
                } else {
                    System.out.println("Money must be greater than 0. Try again:");
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric value:");
                input.next(); // Clear bad input
            }
        }
        return new CasinoCustomer(in, num); 
    }
    
    public void play(){
        boolean gameActive = true;
        
        while (gameActive) {
            System.out.println("\n--- New Round! ---"); 
            
            if(BlackJackRiver.shouldRestart()){
                BlackJackRiver.restart(); 
                System.out.println("Deck shuffled.");
            }
            
            Round currentRound = new Round(BlackJackRiver);
            gameActive = false; 
            
            for(int j=0; j<numberOfPlayers; j++){
                if(!BlackjackArray[j].isBroke()){ 
                    currentRound.addPlayer(BlackjackArray[j]);
                    gameActive = true; 
                }
            }
            
            if(gameActive){
                currentRound.playRound();
                
                String playAgain = "";
                while (true) {
                    System.out.print("\nDo you want to play another round? (Yes/No): ");
                    playAgain = input.next();
                    if (playAgain.equalsIgnoreCase("Yes") || playAgain.equalsIgnoreCase("No")) {
                        break;
                    }
                    System.out.println("Invalid input. Please type 'Yes' or 'No'.");
                }
                
                if (playAgain.equalsIgnoreCase("No")) {
                    System.out.println("Thanks for playing! Cashing out remaining players...");
                    gameActive = false; 
                }
            } else {
                System.out.println("Game Over. All players are broke.");
            }
        }
    }
}