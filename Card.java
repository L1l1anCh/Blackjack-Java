//Made by Lilian Charalampakos
class Card{
	private String card;
	private int value;
	
	public Card(String Card){
		this.card = Card;
		this.value = value;
		
	}
	
	public int getValue(){
		if((this.card).equals("A")){
			value = 1;
			return value;
		}else if((this.card).equals("2")){
			value = 2;
			return value;
		}else if((this.card).equals("3")){
			value = 3;
			return value;
		}else if((this.card).equals("4")){
			value = 4;
			return value;
		}else if((this.card).equals("5")){
			value = 5;
			return value;
		}else if((this.card).equals("6")){
			value = 6;
			return value;
		}else if((this.card).equals("7")){
			value = 7;
			return value;
		}else if((this.card).equals("8")){
			value = 8;
			return value;
		}else if((this.card).equals("9")){
			value = 9;
			return value;
		}else if((this.card).equals("10")){
			value = 10;
			return value;
		}else if((this.card).equals("J")){
			value = 10;
			return value;
		}else if((this.card).equals("Q")){
			value = 10;
			return value;
		}else if((this.card).equals("K")){
			value = 10;
			return value;
		}
		return value;
			
	}
	
	
	public boolean isAce(){
		if((this.card).equals("A")){
			return true;
		}
		return false;
	}
	
	public boolean equals(Card card1){
		if(((this.card).equals(card1))){
			return true;
		}
		return false;
	}
	
	public String toString(){		
		return this.card;
	}
		
}