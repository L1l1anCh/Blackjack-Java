//Made by Lilian Charalampakos
import java.util.Scanner;

class Blackjack {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int number = 0;
        
        System.out.println("How many players are in this game? ");
        while (true) {
            if (input.hasNextInt()) {
                number = input.nextInt();
                if (number > 0) {
                    break; // Valid number, exit loop
                } else {
                    System.out.println("Please enter a number greater than 0.");
                }
            } else {
                System.out.println("Invalid input. Please type a valid number.");
                input.next(); // Clear the bad input from the scanner
            }
        }
        
        BlackjackTable Table = new BlackjackTable(number);
        Table.play();
    }
}