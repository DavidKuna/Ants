package gobbl3r.ants;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

public abstract class Card extends BitmapDrawable{
    
	protected boolean available = false;
	
	protected Card(Bitmap bmp, Context context){
		super(bmp);
	}
	
	public abstract void play(Player PlayerTurn, Player Opponent);
	
	public abstract void checkAvailability(Player player);

	public boolean isAvailable(){
		return this.available;
	}

	public void setAvailability(boolean available) {
		this.available = available;
	}

	@Override
	public void draw(Canvas canvas){
		if(this.available){
			this.setAlpha(255);
		}else{
			this.setAlpha(180);
		}
		
		super.draw(canvas);
	}
	
}
