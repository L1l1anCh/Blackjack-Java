//Made by Lilian Charalampakos
import java.util.Scanner;

class Player {
    private CasinoCustomer customer;
    private Hand customersHand;
    private int customersBet;
    Scanner input = new Scanner(System.in);
    
    public Player(CasinoCustomer customer){
        this.customer = customer;
    }
    
    public Player(CasinoCustomer customer2, Hand customersHand, int customersBet){
        this.customer = customer2;
        this.customersHand = customersHand;		
        this.customersBet = customersBet;
    }
    
    // Setter for Hand (useful when splitting)
    public void setHand(Hand hand) {
        this.customersHand = hand;
    }

    public CasinoCustomer getCustomer(){
        return customer;
    }
    
    public Hand getCustomersHand(){
        return customersHand;
    }
    
    public int getCustomersBet(){
        return customersBet;
    }
    
    public void wins(){
        customer.collectBet(customersBet);
        System.out.println("Success! " + customer.toString() + " won " + customersBet);
    }
    
    public void winsBlackjack(){
        System.out.println("Blackjack! " + customer.toString() + " won!");		
        customer.collectBet((customersBet * 3) / 2);
    }
    
    public void loses(){
        System.out.println("Fail! " + customer.toString() + " lost " + customersBet);
        customer.payBet(customersBet);
    }
    
	public void placeBet(){
			System.out.println(customer.printState());		
			while (true) {
				System.out.print(customer.toString() + " - Place bet: ");
				if (input.hasNextInt()) {
					int askingBet = input.nextInt();
					if (askingBet >= 1 && customer.canCover(askingBet)) {
						System.out.println("Bet placed: " + askingBet);
						customersBet = askingBet;
						break;
					} else {
						System.out.println("Invalid bet. Please enter an amount you can cover (minimum 1).");
					}
				} else {
					System.out.println("Invalid input. Please enter a numeric value.");
					input.next(); // Clear bad input
				}
			}		
		}
    
    public void doubleBet(){
        customersBet = customersBet * 2;
        System.out.println("Bet doubled to: " + customersBet);
    }
    
	public boolean wantsToDouble(){
			if(customer.canCover(customersBet)){ 
				while (true) {
					System.out.print("Do you want to double your bet? (Yes/No): ");
					String askdouble = input.next();
					if(askdouble.equalsIgnoreCase("Yes")) return true;
					if(askdouble.equalsIgnoreCase("No")) return false;
					System.out.println("Invalid input. Please type 'Yes' or 'No'.");
				}
			}
			return false;
		}
    
	public boolean wantsToSplit(){
			if(customer.canCover(customersBet)){ // Check if they can cover the new second hand
				while (true) {
					System.out.print("Do you want to split? (Yes/No): ");
					String asksplit = input.next();
					if(asksplit.equalsIgnoreCase("Yes")) return true;
					if(asksplit.equalsIgnoreCase("No")) return false;
					System.out.println("Invalid input. Please type 'Yes' or 'No'.");
				}
			}
			return false;
		}
    
    public String toString(){
        if (customersHand != null) {
            return "Player " + customer.toString() + ": " + customersHand.toString();
        }
        return "Player " + customer.toString();
    }
}