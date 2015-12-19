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

		int dWidth		= context.getResources().getDisplayMetrics().widthPixels;
		int dHeight		= context.getResources().getDisplayMetrics().heightPixels;

		double scaleX = (double)dWidth/GraphicsView.defaultWidth;
		double scaleY = (double)dHeight/GraphicsView.defaultHeight;
		
		cardPadL 	= (int) (10*scaleX);
		cardPadT 	= (int) (340*scaleY);
		dstWidth 	= (int) (85*scaleX);
		dstHeight 	= (int) (127*scaleY);
		
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
