package gobbl3r.cards;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import gobbl3r.ants.Card;
import gobbl3r.ants.GraphicsView;
import gobbl3r.ants.Player;
import gobbl3r.ants.R;

public class CardConjureCrystals extends Card{
	private static final String TAG = "CardConjureCrystals";
	
	// define card
	private static int resId = R.drawable.card03_blank;

	public CardConjureCrystals(Context context) {
		super(BitmapFactory.decodeResource(context.getResources(), resId), context);
		super.icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.diamond);
		super.iconScale = 0.9;
		super.cost = 2;
		super.actionText = context.getString(R.string.conjure_crystals_text);
		super.nameColor = GraphicsView.statsBlue;
		super.name = context.getString(R.string.conjure_crystals);
	}

	@Override
	public void play(Player PlayerTurn, Player Opponent) {
		
		PlayerTurn.addCrystal(4);
		
		Log.d(TAG, "Action ");
		
	}

	@Override
	public void checkAvailability(Player player) {
		if(player.getCrystal() >= 4){
			this.available = true;
		}else{
			this.available = false;
		}
		
	}

}
