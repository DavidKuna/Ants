package gobbl3r.cards;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import gobbl3r.ants.Card;
import gobbl3r.ants.GraphicsView;
import gobbl3r.ants.Player;
import gobbl3r.ants.R;

public class CardArcher extends Card {
	private static final String TAG = "CardArcher";

	// define card image
	private static int resId = R.drawable.card01_blank;

	public CardArcher(Context context){
		super(BitmapFactory.decodeResource(context.getResources(), resId), context);
		super.icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.bow);
        super.iconScale = 0.8;
        super.cost = 1;
        super.actionText = context.getString(R.string.attack) + " + 2";
        super.nameColor = GraphicsView.statsGreen;
		name = context.getString(R.string.archer);
	}

	@Override
	public void play(Player PlayerTurn, Player Opponent) {
		
		PlayerTurn.removeWeapon(cost);
		Opponent.attack(2);
	}

	@Override
	public void checkAvailability(Player player) {
		if(player.getWeapon() >= cost){
			this.available = true;
		}else{
			this.available = false;
		}
	}
}
