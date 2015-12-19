package gobbl3r.ants;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

public class Slot extends BitmapDrawable{

	// Display metrics
    private float scale;

    protected final int cardPadL;
    protected final int cardPadT;
	protected final int dstWidth;
	protected final int dstHeight;
	
	private Card card;
	
	public Slot(int index, Context context){
		
		scale = context.getResources().getDisplayMetrics().density;
		
		cardPadL 	= (int) (6*scale);
		cardPadT 	= (int) (225*scale);
		dstWidth 	= (int) (60*scale); 
		dstHeight 	= (int) (90*scale);
		
		this.setBounds(cardPadL + ((cardPadL + dstWidth)*(index)), cardPadT, ((cardPadL + dstWidth)*(index+1)), cardPadT+dstHeight);
	}
	
	
	public void setCard(Card card){
		this.card = card;
	}
	
	public Card getCard(){
		return this.card;
	}
		
	public void draw(Canvas canvas, Player player){
		card.setBounds(this.getBounds());
		card.checkAvailability(player);
		card.draw(canvas);
	}
	
}
