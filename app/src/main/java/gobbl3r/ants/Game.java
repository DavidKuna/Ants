package gobbl3r.ants;

import java.util.List;
import java.util.Random;

public class Game {

	public Player player1;
	public Player player2;
	
	private Player PlayerTurn;
	private Player Opponent;
	
	private List<Card> cards;
	
	/**
	 * Constructor of game. Game uses package of cards taken from parameter.
	 * @param cards
	 */
	public Game(List<Card> cards, Slot[] slots1, Slot[] slots2){
		
		this.cards = cards;
		
		player1 = new Player(1, "Yellow", slots1);
		player2 = new Player(2, "Green", slots2);
		
		newGame();
	}
	
	/**
	 * Set default values of players and generate new cards.
	 */
	public void newGame(){
		player1.setDefault();
		player2.setDefault();
		
		setStartCards(player1);
		setStartCards(player2);
		
		PlayerTurn 	= player1;
		Opponent 	= player2;
		
		// debug setting
		player1.destroyCastle(28);
		player2.buildCastle(68);
	}
	
	public Player getPlayerTurn(){
		return this.PlayerTurn;
	}
	
	public Player getOpponent(){
		return this.Opponent;
	}
	
	public boolean playCard(int index){
		if(this.PlayerTurn.getCard(index).isAvailable()){
			this.PlayerTurn.getCard(index).play(PlayerTurn, Opponent);
			this.PlayerTurn.setSlot(index, getRandomCard());
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Compare reference of current player and set the other one to current.
	 */
	public void nextRound(){
		Opponent = PlayerTurn;
		if(PlayerTurn == player1){
			PlayerTurn = player2;
		}else{
			 PlayerTurn = player1;
		}	
		// add materials to current player
		PlayerTurn.payout();
	}
	
	private void setStartCards(Player player){
		Slot[] slots = player.getSlots();
		
		for (int i = 0; i < slots.length; i++) {
			player.setSlot(i, getRandomCard());
		}
	}
	
	private Card getRandomCard(){
		Random rand = new Random();
		return cards.get(rand.nextInt(cards.size()));
	}
	
	/**
	 * Check if current player is winner.
	 * @return True if player wins and false if no one is winner.
	 */
	public boolean checkWin(){
		if(PlayerTurn.getCastle() >= 100 || Opponent.getCastle() <= 0){
			return true;
		}else{
			return false;
		}	
	}

	public void playerWins(){
		PlayerTurn.addPoint();
	}
	
}
