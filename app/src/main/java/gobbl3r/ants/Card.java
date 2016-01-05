package gobbl3r.ants;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

public abstract class Card extends BitmapDrawable{
    
	protected boolean available = false;
    protected Context context;
    protected String name;
    protected int nameColor = Color.BLACK;
    protected Bitmap icon;
    protected double iconScale = 1;
    protected int cost;
    protected String actionText;
	
	protected Card(Bitmap bmp, Context context){
		super(bmp);
        this.context = context;
	}
	
	public abstract void play(Player PlayerTurn, Player Opponent);
	
	public abstract void checkAvailability(Player player);

	public boolean isAvailable(){
		return this.available;
	}

	@Override
	public void draw(Canvas canvas){
		if(this.available){
			this.setAlpha(255);
		}else{
			this.setAlpha(180);
		}
		
		super.draw(canvas);

        drawName(canvas);
        drawCost(canvas);
        drawIcon(canvas);
        drawActionText(canvas);
	}

    public int getCenterX() {
        Rect bounds = this.getBounds();
        return ((bounds.right - bounds.left) / 2) + bounds.left;

    }

    private void drawName(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(nameColor);
        paint.setAntiAlias(true);
        paint.setTextSize((float)(15 * GraphicsView.getScaleY(context)));
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        int left = (int) (getCenterX() - paint.measureText(name) / 2);
        int top = (int)(getBounds().top + (30 * GraphicsView.getScaleY(context)));
        canvas.drawText(name, left, top, paint);
    }

    private void drawCost(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setTextSize((float)(12 * GraphicsView.getScaleY(context)));
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        int left = (int) (getBounds().right - 15 * GraphicsView.getScaleX(context));
        int top = (int)(getBounds().top + (17 * GraphicsView.getScaleY(context)));
        canvas.drawText(String.valueOf(cost), left, top, paint);
    }


    private void drawActionText(Canvas canvas) {
        if (actionText != null) {
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setAntiAlias(true);
            paint.setTextSize((float)(10 * GraphicsView.getScaleY(context)));
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            int left = (int) (getCenterX() - paint.measureText(actionText) / 2);
            int top = (int) (getBounds().bottom - (10 * GraphicsView.getScaleY(context)));
            canvas.drawText(actionText, left, top, paint);
        }
    }

    private void drawIcon(Canvas canvas) {
        if (icon instanceof Bitmap) {
            Rect bounds = this.getBounds();
            Paint paint = new Paint();
            if(this.available){
                paint.setAlpha(255);
            }else{
                paint.setAlpha(180);
            }
            int iconWidth = (int)(70 * iconScale * GraphicsView.getScaleX(context));
            int iconHeight = (int)(70 * iconScale * GraphicsView.getScaleY(context));
            int top = (int) (bounds.top + (40 * GraphicsView.getScaleY(context)));
            canvas.drawBitmap(Bitmap.createScaledBitmap(icon, iconWidth, iconHeight, true), getCenterX() - (iconWidth / 2), top, paint);
        }
    }

    public void setAvailability(boolean availability) {
        this.available = availability;
    }
}
