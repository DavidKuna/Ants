package gobbl3r.ants;

import android.graphics.Canvas;

public class Player {
	
	private int id;
	private int score = 0;
	private String name;

	private Slot slots[];
	
	// definition of commodities
	private int brick;
	private int weapon;
	private int crystal;
	
	// definition of figures
	private int builder;
	private int soldier;
	private int mage;
	
	// definition of castle
	private int castle	= 30;
	private int wall	= 10;
	
	/**
	 * constructor
	 * @name - player name
	 */
	public Player(int id, String name, Slot[] slots){
		this.id 	= id;
		this.name 	= name;	
		this.slots 	= slots;
		
		setDefault();
	}
	
	/**
	 * Initialization default values of commodities, figures and castle.
	 */
	public void setDefault(){
		brick	= 5;
		weapon  = 5; 
		crystal	= 5;
		
		builder	= 2;
		soldier	= 2;
		mage	= 2;
		
		castle	= 30;
		wall	= 10;
	}
	
	/**
	 * Draw players cards to canvas
	 * @param canvas
	 */
	public void drawSlots(Canvas canvas){
		for (int i = 0; i < slots.length; i++) {
			slots[i].draw(canvas, this);
		}
	}
		
	/**
	 * Add raw materials. 
	 */
	public void payout(){
		this.brick 		+= this.builder;
		this.weapon 	+= this.soldier;
		this.crystal 	+= this.mage;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	/**
	 * Increment players score.
	 */
	public void addPoint(){
		this.score++;
	}
	
	public Slot[] getSlots(){
		return this.slots;
	}
	
	public Slot getSlot(int index){
		return this.slots[index];
	}
	
	public Card getCard(int index){
		return this.slots[index].getCard();
	}
	
	
	public void setSlot(int index, Card card){
		slots[index].setCard(card);
	}
	
	public int getScore(){
		return this.score;
	}
	
	public int getBrick(){
		return this.brick;
	}
	
	public int getWeapon(){
		return this.weapon;
	}
	
	public int getCrystal(){
		return this.crystal;
	}
	
	public int getBuilder(){
		return this.builder;
	}
	
	public int getSoldier(){
		return this.soldier;
	}
	
	public int getMage(){
		return this.mage;
	}
	
	public int getCastle(){
		if(this.castle > 100)
			return 100;
		else
			return this.castle;
	}
	
	public int getWall(){
		return this.wall;
	}
	
	public void addBrick(int num){
		this.brick += num;
	}
	
	public void addWeapon(int num){
		this.weapon += num;
	}
	
	public void addCrystal(int num){
		this.crystal += num;
	}
	
	public void addBuilder(int num){
		this.builder += num;
	}
	
	public void addSoldier(int num){
		this.soldier += num;
	}
	
	public void addMage(int num){
		this.mage += num;
	}
	
	public void buildCastle(int num){
		this.castle += num;
	}
	
	public void buildWall(int num){
		this.wall += num;
	}
	
	public void removeBrick(int num){
		this.brick -= num;
		if (brick < 0) {
			brick = 0;
		}
	}
	
	public void removeWeapon(int num){
		this.weapon -= num;
		if (weapon < 0) {
			weapon = 0;
		}
	}
	
	public void removeCrystal(int num){
		this.crystal -= num;
		if (crystal < 0) {
			crystal = 0;
		}
	}
	
	public void removeBuilder(int num){
		this.builder -= num;
		if (builder < 0) {
			builder = 0;
		}
	}
	
	public void removeSoldier(int num){
		this.soldier -= num;
		if (soldier < 0) {
			soldier = 0;
		}
	}
	
	public void removeMage(int num){
		this.mage -= num;
		if (mage < 0) {
			mage = 0;
		}
	}
	
	public void attack(int num){
		if(num > this.wall){
			this.castle -= num - this.wall;
			if (castle < 0) {
				castle = 0;
			}
			this.wall 	= 0;
		}else{
			this.wall -= num;
		}
	}
	
	public void destroyCastle(int num){
		this.castle -= num;
		if (castle < 0) {
			castle = 0;
		}
	}
	
	public void destroyWall(int num){
		this.wall -= num;
	}
}
