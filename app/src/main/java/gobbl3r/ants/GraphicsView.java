package gobbl3r.ants;

import gobbl3r.cards.CardConjureCrystals;
import gobbl3r.cards.CardKnight;
import gobbl3r.cards.CardTower;
import gobbl3r.cards.CardWall;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;


public class GraphicsView extends View implements OnTouchListener {
	private static String TAG = "GraphicsView";
		
    private Paint paint = new Paint();
    private Context context;
    
    /*
	 * number of card slots on board
	 */
	private final int num_of_slots = 8;
    
    List<Card> cards = new ArrayList<Card>();

    private Bitmap bmp_background 	= BitmapFactory.decodeResource(getResources(), R.drawable.bg_1066x600);
	private Bitmap bmp_gate 		= BitmapFactory.decodeResource(getResources(), R.drawable.gate);

	private Bitmap bmp_tower1 		= BitmapFactory.decodeResource(getResources(), R.drawable.tower_red);
	private Bitmap bmp_tower2 		= BitmapFactory.decodeResource(getResources(), R.drawable.tower_yellow);

	private Bitmap bmp_ferda1 		= BitmapFactory.decodeResource(getResources(), R.drawable.ferda1);
	private Bitmap bmp_ferda2 		= BitmapFactory.decodeResource(getResources(), R.drawable.ferda2);

	private Bitmap bmp_winner1		= BitmapFactory.decodeResource(getResources(), R.drawable.ferda1_win);
	private Bitmap bmp_winner2 		= BitmapFactory.decodeResource(getResources(), R.drawable.ferda2_win);

	private Bitmap tower1;
	private Bitmap tower2;

    // Display metrics

    //private double scale = getResources().getDisplayMetrics().density;
    public static int defaultWidth = 800;//px
    public static int defaultHeight = 480;//px
    private int dWidth;
    private int dHeight;
    private double scaleW;//dWidth/defaultWidth;
    private double scaleH;// (dHeight/defaultHeight);
    
    // define position and size of elements
    private int statsW;
    private int statsH;
    private int statsPadV;
    private int statsPadH;
    private int statsPadB;
    private int statsFontSize;
    private int statsFontPad;
    private int scoreFontSize;
    private int scoreFontPad;
    
    //define color of elements
    private int statsRed;
    private int statsGreen;
    private int statsBlue;
    
    private int gateWidth;
    private int gateHeight;
    private int gatePosY;
    private int gate1PosX;
    private int gate2PosX;
    
    private int towerWidth;
    private int towerHeight;
    private int towerMinH;
    private double towerCoef;
    private int towerDPosY;
    private int tower1PosX;
    private int tower2PosX;
    
    // ferda 
    private int ferdaWidth;
    private int ferdaHeight;
    private int ferdaPosY;
    private int ferda1PosX;
    private int ferda2PosX;
    
 // ferda winner
    private int winnerWidth;
    private int winnerHeight;
    private int winnerPosY;
    private int winner1PosX;
    private int winner2PosX;
    
    // GAME
    private Game game;
	
	public GraphicsView(Context context) {
		super(context);
        this.context = context;
		setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);

		initializeDimensions();
        initializePaint();        
        initializeCards();

        // Start game
        game = new Game(context, cards, createSlots(), createSlots());
	}

    /**
     * Initializing metrics, scales and dimensions
     */
	private void initializeDimensions() {

		dWidth		= getResources().getDisplayMetrics().widthPixels;
		dHeight		= getResources().getDisplayMetrics().heightPixels;
		scaleW  	= (double)dWidth/defaultWidth;
		scaleH  	= (double)dHeight/defaultHeight;

		// define position and size of elements
		statsW 		= (int) (130*scaleW);
		statsH 		= (int) (70*scaleH);
		statsPadV 	= (int) (30*scaleH);
		statsPadH 	= (int) (10*scaleW);
		statsPadB 	= (int) (10*scaleH);
		statsFontSize	= (int) (15*scaleH);
		statsFontPad	= (int) (9*scaleH);
		scoreFontSize	= (int) (25*scaleH);
        scoreFontPad    = (int) (110*scaleH);

		//define color of elements
		statsRed		= Color.rgb(241, 52, 52);
		statsGreen	= Color.rgb(4, 174, 4);
		statsBlue		= Color.rgb(62, 136, 186);

		gateWidth		= (int) (180*scaleW);
		gateHeight	    = (int) (102*scaleH);
		gatePosY		= (int) (230*scaleH);
		gate1PosX		= (int) (150*scaleW);
		gate2PosX		= (int) (470*scaleW);

		towerWidth	= (int) (120*scaleW);//picture size 216
		towerHeight	= (int) (277*scaleW);//picture size 500
		towerMinH	= (int) (100*scaleH);
		towerCoef	= 2.15;
		towerDPosY	= (int) (20*scaleH);
		tower1PosX	= (int) (180*scaleW);
		tower2PosX	= (int) (500*scaleW);

		// ferda
		ferdaWidth	= (int) (50*scaleW);
		ferdaHeight	= (int) (113*scaleH);
		ferdaPosY		= (int) (20*scaleH);
		ferda1PosX	= (int) (150*scaleW);
		ferda2PosX	= (int) (600*scaleW);

		// ferda winner
		winnerWidth	= (int) (100*scaleW);
		winnerHeight	= (int) (98*scaleH);
		winnerPosY	= (int) (220*scaleH);
		winner1PosX	= (int) (250*scaleW);
		winner2PosX	= (int) (470*scaleW);

		tower1 = Bitmap.createScaledBitmap(bmp_tower1, towerWidth, towerHeight, true);
		tower2 = Bitmap.createScaledBitmap(bmp_tower2, towerWidth, towerHeight, true);
	}

	@Override
    public void onDraw(Canvas canvas) {
        
        // draw background
		canvas.drawBitmap(bmp_background, 0, 0, paint);
		// draw towers
		drawTowers(canvas);
		// draw statistics
		drawStats(canvas);

		
		if(game.checkWin()){
  			drawWinner(canvas);
  		}else{
	  		drawCurrentPlayer(canvas);
  		}
		
		// draw slots with cards of current player
		game.getPlayerTurn().drawSlots(canvas);
        drawLastCard(canvas);

    }
	
	/**
	 * Default initialization of paint
	 */
	private void initializePaint(){
		paint.setColor(Color.BLACK);
		paint.setTypeface(Typeface.MONOSPACE);
        paint.setAntiAlias(true);
	}
	
	/**
	 * Initialize of cards which will be used for game
	 */
	private void initializeCards(){
		cards.add(new CardKnight(this.getContext()));
		cards.add(new CardWall(this.getContext()));
		cards.add(new CardWall(this.getContext()));
		cards.add(new CardConjureCrystals(this.getContext()));
		cards.add(new CardTower(this.getContext()));
	}
	
	/**
	 * Initialize of cards which will be used for game
	 */
	private Slot[] createSlots(){
		Slot slots[] = new Slot[num_of_slots];
		for (int i = 0; i < num_of_slots; i++) {
			slots[i] = new Slot(i, this.getContext());
		}
		return slots; 
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {

        Point point = new Point();
        point.x = (int) event.getX();
        point.y = (int) event.getY();
        
       switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: 
            	if(!game.checkWin()){
			       	for (int i = 0; i < num_of_slots; i++) {
			          	if (game.getPlayerTurn().getSlot(i).getBounds().contains(point.x, point.y)) {
			          		
			          		if(game.playCard(i)){ // if card was available and played 
			          			if(!game.checkWin()){ // if player is not win game continue 
				          			game.nextRound();
				          		}else{				// else player wins
				          			game.playerWon();
				          		}
			          		}
			              	break;
			              }
			  		}
            	}else{
            		game.newGame();
            	}
                break;

            case MotionEvent.ACTION_MOVE:
                // finger moves on the screen
                break;

            case MotionEvent.ACTION_UP:   
            	Log.d(TAG, "UP"); 
                break;
        }
        
        if(event.getAction() == MotionEvent.ACTION_DOWN){
        	Log.d(TAG, "DOWN"); 
        }

        invalidate();
        return true;
    }
	
	/**
	 * Draw gates and towers of both players
	 * @param canvas
	 */
	public void drawTowers(Canvas canvas){
		// draw tower1
		int tower1Crop = (int) ((towerMinH - (game.player1.getCastle()))*towerCoef);
		int tower1posY = towerDPosY + tower1Crop;
		
		Bitmap curtower1 = Bitmap.createBitmap(tower1, 0, 0, tower1.getWidth(), tower1.getHeight()-tower1Crop);
		canvas.drawBitmap(curtower1, tower1PosX, tower1posY, paint);
		
		// draw towe2
		int tower2Crop = (int) ((towerMinH - (game.player2.getCastle()))*towerCoef);
		int tower2posY = towerDPosY + tower2Crop;
		
		Bitmap curtower2 = Bitmap.createBitmap(tower2, 0, 0, tower2.getWidth(), tower2.getHeight()-tower2Crop);
		canvas.drawBitmap(curtower2, tower2PosX, tower2posY, paint);
		
		// draw gate1
		canvas.drawBitmap(Bitmap.createScaledBitmap(bmp_gate, gateWidth, gateHeight, true), gate1PosX, gatePosY, paint);
		// draw gate2
		canvas.drawBitmap(Bitmap.createScaledBitmap(bmp_gate, gateWidth, gateHeight, true), gate2PosX, gatePosY, paint);
				
	}

    /**
     * Draw last played card
     * @param canvas
     */
    public void drawLastCard(Canvas canvas){
        int dWidth		= context.getResources().getDisplayMetrics().widthPixels;
        int dHeight		= context.getResources().getDisplayMetrics().heightPixels;

        double scaleX = (double)dWidth/GraphicsView.defaultWidth;
        double scaleY = (double)dHeight/GraphicsView.defaultHeight;

        int dstWidth 	= (int) (85*scaleX);
        int dstHeight 	= (int) (127*scaleY);
        int cardPadL 	= (int) ((dWidth - dstWidth) / 2);
        int cardPadT 	= (int) (30*scaleY);

        Rect bounds = new Rect(cardPadL, cardPadT, (cardPadL + dstWidth), cardPadT + dstHeight);
        Card lastCard = game.getLastPlayedCard();
        if (lastCard instanceof Card) {
            lastCard.setBounds(bounds);
            lastCard.setAvailability(true);
            lastCard.draw(canvas);
        }
    }

    /**
	 * Draw statistics of both players
	 * @param canvas
	 */
	public void drawStats(Canvas canvas){		
		Rect red1 	= new Rect(statsPadH, statsPadV, statsPadH+statsW, statsPadV+statsH);
		Rect green1 = new Rect(statsPadH, statsPadV+statsH, statsPadH+statsW, statsPadV+statsH*2);
		Rect blue1 	= new Rect(statsPadH, statsPadV+statsH*2, statsPadH+statsW, statsPadV+statsH*3);
		Rect black1 = new Rect(statsPadH, statsPadV+statsH*3+statsPadB, statsPadH+statsW, statsPadV+statsH*4+statsPadB);
		
		Rect red2 	= new Rect(dWidth-statsPadH-statsW, statsPadV, dWidth-statsPadH, statsPadV+statsH);
		Rect green2 = new Rect(dWidth-statsPadH-statsW, statsPadV+statsH, dWidth-statsPadH, statsPadV+statsH*2);
		Rect blue2 	= new Rect(dWidth-statsPadH-statsW, statsPadV+statsH*2, dWidth-statsPadH, statsPadV+statsH*3);
		Rect black2 = new Rect(dWidth - statsPadH-statsW, statsPadV+statsH*3+statsPadB, dWidth-statsPadH, statsPadV+statsH*4+statsPadB);

		// draw boxes of statistics
		paint.setColor(statsRed);
		canvas.drawRect(red1, paint);
		canvas.drawRect(red2, paint);
		
		paint.setColor(statsGreen);
		canvas.drawRect(green1, paint);
		canvas.drawRect(green2, paint);
		
		paint.setColor(statsBlue);
		canvas.drawRect(blue1, paint);
		canvas.drawRect(blue2, paint);
		
		paint.setColor(Color.BLACK);
		canvas.drawRect(black1, paint);
		canvas.drawRect(black2, paint);
		
		paint.setColor(Color.WHITE);
		paint.setTextSize(statsFontSize);

        // draw text into boxes player1
        drawPlayerStats(canvas, game.player1, red1, green1, blue1, black1);
        // draw text into boxes player2
        drawPlayerStats(canvas, game.player2, red2, green2, blue2, black2);

		paint.setColor(Color.BLACK);
		paint.setTextSize(scoreFontSize);
		// draw score of player1
		canvas.drawText(String.valueOf(game.player1.getScore()), (int)((red1.right-red1.left)/2)+red1.left, red1.top-5, paint);
		// draw score of player2
		canvas.drawText(String.valueOf(game.player2.getScore()), (int)((red2.right-red2.left)/2)+red2.left, red2.top-5, paint);
	}

    /**
     * Draw statistics of one player
     * @param canvas
     * @param player
     * @param red
     * @param green
     * @param blue
     * @param black
     */
    private void drawPlayerStats(Canvas canvas, Player player, Rect red, Rect green, Rect blue, Rect black) {
        canvas.drawText(context.getString(R.string.builders), red.left+statsFontPad, red.top+statsFontSize+statsFontPad, paint);
        canvas.drawText(String.valueOf(player.getBuilder()), red.left+scoreFontPad, red.top+statsFontSize+statsFontPad, paint);
        canvas.drawText(context.getString(R.string.bricks), red.left+statsFontPad, red.top+(statsFontSize+statsFontPad)*2+statsFontPad, paint);
        canvas.drawText(String.valueOf(player.getBrick()), red.left+scoreFontPad, red.top+(statsFontSize+statsFontPad)*2+statsFontPad, paint);

        canvas.drawText(context.getString(R.string.soldiers), green.left+statsFontPad, green.top+statsFontSize+statsFontPad, paint);
        canvas.drawText(String.valueOf(player.getSoldier()), green.left+scoreFontPad, green.top+statsFontSize+statsFontPad, paint);
        canvas.drawText(context.getString(R.string.weapons), green.left+statsFontPad, green.top+(statsFontSize+statsFontPad)*2+statsFontPad, paint);
        canvas.drawText(String.valueOf(player.getWeapon()), green.left+scoreFontPad, green.top+(statsFontSize+statsFontPad)*2+statsFontPad, paint);

        canvas.drawText(context.getString(R.string.mages), blue.left+statsFontPad, blue.top + statsFontSize + statsFontPad, paint);
        canvas.drawText(String.valueOf(player.getMage()), blue.left+scoreFontPad, blue.top+statsFontSize+statsFontPad, paint);
        canvas.drawText(context.getString(R.string.crystals), blue.left+statsFontPad, blue.top+(statsFontSize+statsFontPad)*2+statsFontPad, paint);
        canvas.drawText(String.valueOf(player.getCrystal()), blue.left+scoreFontPad, blue.top+(statsFontSize+statsFontPad)*2+statsFontPad, paint);

        canvas.drawText(context.getString(R.string.castle), black.left + statsFontPad, black.top + statsFontSize + statsFontPad, paint);
        canvas.drawText(String.valueOf(player.getCastle()), black.left + scoreFontPad, black.top + statsFontSize + statsFontPad, paint);
        canvas.drawText(context.getString(R.string.wall), black.left+statsFontPad, black.top+(statsFontSize+statsFontPad)*2+statsFontPad, paint);
        canvas.drawText(String.valueOf(player.getWall()), black.left+scoreFontPad, black.top+(statsFontSize+statsFontPad)*2+statsFontPad, paint);
    }

	/**
	 * Draw illustration of player's turn
	 * @param canvas
	 */
	public void drawCurrentPlayer(Canvas canvas){
		if(game.getPlayerTurn().getId() == 1){
			canvas.drawBitmap(Bitmap.createScaledBitmap(bmp_ferda1, ferdaWidth, ferdaHeight, true), ferda1PosX, ferdaPosY, paint);
		}else{
			canvas.drawBitmap(Bitmap.createScaledBitmap(bmp_ferda2, ferdaWidth, ferdaHeight, true), ferda2PosX, ferdaPosY, paint);
		}
	}
	
	/**
	 * Draw illustration of winner.
	 * @param canvas
	 */
	public void drawWinner(Canvas canvas){
		if(game.getPlayerTurn().getId() == 1){
			canvas.drawBitmap(Bitmap.createScaledBitmap(bmp_winner1, winnerWidth, winnerHeight, true), winner1PosX, winnerPosY, paint);
		}else{
			canvas.drawBitmap(Bitmap.createScaledBitmap(bmp_winner2, winnerWidth, winnerHeight, true), winner2PosX, winnerPosY, paint);
		}
	}
}
