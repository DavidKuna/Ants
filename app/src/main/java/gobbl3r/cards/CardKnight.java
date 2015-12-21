package gobbl3r.cards;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import gobbl3r.ants.Card;
import gobbl3r.ants.GraphicsView;
import gobbl3r.ants.Player;
import gobbl3r.ants.R;

public class CardKnight extends Card {
	private static final String TAG = "CardKnight";
	
	// define card image
	private static int resId = R.drawable.card01_blank;
	
	public CardKnight(Context context){
		super(BitmapFactory.decodeResource(context.getResources(), resId), context);
		super.icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.sword);
        super.iconScale = 0.8;
        super.cost = 3;
        super.actionText = context.getString(R.string.knight_text);
        super.nameColor = GraphicsView.statsGreen;
		name = context.getString(R.string.knight);
	}

	@Override
	public void play(Player PlayerTurn, Player Opponent) {
		
		PlayerTurn.removeWeapon(cost);
		Opponent.attack(3);
		
		Log.d(TAG, "Action ");
		
	}

	@Override
	public void checkAvailability(Player player) {
		if(player.getWeapon() >= 2){
			this.available = true;
		}else{
			this.available = false;
		}
	}
}
