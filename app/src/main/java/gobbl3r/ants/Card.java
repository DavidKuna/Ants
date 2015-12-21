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
        paint.setTextSize(20f);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        int left = (int) (getCenterX() - paint.measureText(name) / 2);
        int top = (int)(getBounds().top + (30 * GraphicsView.getScaleY(context)));
        canvas.drawText(name, left, top, paint);
    }

    private void drawCost(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setTextSize(17f);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        int left = (int) (getBounds().right - 12 * GraphicsView.getScaleX(context));
        int top = (int)(getBounds().top + (17 * GraphicsView.getScaleY(context)));
        canvas.drawText(String.valueOf(cost), left, top, paint);
    }


    private void drawActionText(Canvas canvas) {
        if (actionText != null) {
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setAntiAlias(true);
            paint.setTextSize(14f);
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            int left = (int) (getCenterX() - paint.measureText(actionText) / 2);
            int top = (int) (getBounds().bottom - (13 * GraphicsView.getScaleY(context)));
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
            int iconWidth = (int)(100 * iconScale);
            canvas.drawBitmap(Bitmap.createScaledBitmap(icon, iconWidth, iconWidth, true), getCenterX() - (iconWidth / 2), bounds.top + 55, paint);
        }
    }

    public void setAvailability(boolean availability) {
        this.available = availability;
    }
}
