//Made by Lilian Charalampakos
class CasinoCustomer
{
	private String NamePlayer;
	private int PlayersMoney;
	
	public CasinoCustomer(String NamePlayer, int PlayersMoney){
		this.NamePlayer = NamePlayer;
		this.PlayersMoney = PlayersMoney;
		
	}
	public int getPlayersMoney(){
		return PlayersMoney;
	}
	
	public int payBet(int PlayersLostBet){
		PlayersMoney -= PlayersLostBet;
		return PlayersMoney;
		
	}
	
	public int collectBet(int PlayersWonBet){
		PlayersMoney += PlayersWonBet;
		return PlayersMoney;
	}
	
	public boolean canCover(int betMoney){
		if(PlayersMoney>betMoney){
			return true;
		}
		return false;
	}
	
	public boolean isBroke(){
		if(PlayersMoney<1){
			return true;
		}
		return false;
	}
	
	public String toString(){
		return NamePlayer;
	}
	public String printState(){		
		return NamePlayer + " " + PlayersMoney;
		
	}
	
	
}