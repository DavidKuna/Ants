package gobbl3r.cards;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import gobbl3r.ants.Card;
import gobbl3r.ants.GraphicsView;
import gobbl3r.ants.Player;
import gobbl3r.ants.R;

public class CardWall extends Card{
	private static final String TAG = "CardWall";

	// define card image
	private static int resId = R.drawable.card02_blank;
		
	public CardWall(Context context) {
		super(BitmapFactory.decodeResource(context.getResources(), resId), context);
		super.icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.wall);
		super.cost = 2;
		super.actionText = context.getString(R.string.defence_text);
		super.nameColor = GraphicsView.statsRed;
		super.name = context.getString(R.string.wall);
	}

	@Override
	public void play(Player PlayerTurn, Player Opponent) {
		
		PlayerTurn.buildWall(2);
		PlayerTurn.removeBrick(cost);
		
		Log.d(TAG, "Action ");
		
	}

	@Override
	public void checkAvailability(Player player) {
		if(player.getBrick() >= 1){
			this.available = true;
		}else{
			this.available = false;
		}
		
	}

}
