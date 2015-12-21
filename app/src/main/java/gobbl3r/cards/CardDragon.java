package gobbl3r.cards;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import gobbl3r.ants.Card;
import gobbl3r.ants.GraphicsView;
import gobbl3r.ants.Player;
import gobbl3r.ants.R;

public class CardDragon extends Card{
	private static final String TAG = "CardDragon";

	// define card
	private static int resId = R.drawable.card03_blank;

	public CardDragon(Context context) {
		super(BitmapFactory.decodeResource(context.getResources(), resId), context);
		super.icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.dragon_eye);
		super.iconScale = 1;
		super.cost = 21;
		super.actionText = context.getString(R.string.attack) + " +25";
		super.nameColor = GraphicsView.statsBlue;
		super.name = context.getString(R.string.dragon);
	}

	@Override
	public void play(Player PlayerTurn, Player Opponent) {

		PlayerTurn.removeCrystal(cost);
		Opponent.attack(25);
	}

	@Override
	public void checkAvailability(Player player) {
		if(player.getCrystal() >= cost){
			this.available = true;
		}else{
			this.available = false;
		}
		
	}

}
