package gobbl3r.cards;

import android.content.Context;
import android.graphics.BitmapFactory;

import gobbl3r.ants.Card;
import gobbl3r.ants.GraphicsView;
import gobbl3r.ants.Player;
import gobbl3r.ants.R;

public class CardDestroyWeapons extends Card{

	// define card
	private static int resId = R.drawable.card03_blank;

	public CardDestroyWeapons(Context context) {
		super(BitmapFactory.decodeResource(context.getResources(), resId), context);
		super.icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.wizard_256);
		super.iconScale = 1;
		super.cost = 4;
		super.actionText = context.getString(R.string.enemy) + " " + context.getString(R.string.weapons).toLowerCase() + " -8";
		super.nameColor = GraphicsView.statsBlue;
		super.name = context.getString(R.string.destroy);
	}

	@Override
	public void play(Player PlayerTurn, Player Opponent) {

		PlayerTurn.removeCrystal(cost);
		Opponent.removeWeapon(8);
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
