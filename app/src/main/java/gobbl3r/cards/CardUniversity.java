package gobbl3r.cards;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import gobbl3r.ants.Card;
import gobbl3r.ants.GraphicsView;
import gobbl3r.ants.Player;
import gobbl3r.ants.R;

public class CardUniversity extends Card{
	private static final String TAG = "CardUniversity";

	// define card image
	private static int resId = R.drawable.card02_blank;

	public CardUniversity(Context context) {
		super(BitmapFactory.decodeResource(context.getResources(), resId), context);
		super.icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.old_book);
		super.cost = 8;
		super.actionText = context.getString(R.string.builders) + " +1";
		super.nameColor = GraphicsView.statsRed;
		super.name = context.getString(R.string.university);
	}

	@Override
	public void play(Player PlayerTurn, Player Opponent) {
		
		PlayerTurn.addBuilder(1);
		PlayerTurn.removeBrick(cost);
	}

	@Override
	public void checkAvailability(Player player) {
		if(player.getBrick() >= cost){
			this.available = true;
		}else{
			this.available = false;
		}
		
	}

}
