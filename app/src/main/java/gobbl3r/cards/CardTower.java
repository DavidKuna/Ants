package gobbl3r.cards;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import gobbl3r.ants.Card;
import gobbl3r.ants.Player;
import gobbl3r.ants.R;

public class CardTower extends Card {
	private static final String TAG = "CardTower";

	// define card image
	private static int resId = R.drawable.card04;
		
	public CardTower(Context context) {
		super(BitmapFactory.decodeResource(context.getResources(), resId), context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play(Player PlayerTurn, Player Opponent) {
		
		PlayerTurn.buildCastle(5);
		PlayerTurn.removeBrick(5);
		
		Log.d(TAG, "Action ");
		
	}

	@Override
	public void checkAvailability(Player player) {
		if(player.getBrick() >= 5){
			this.available = true;
		}else{
			this.available = false;
		}
		
	}

}
