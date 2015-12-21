package gobbl3r.cards;

import android.content.Context;
import android.graphics.BitmapFactory;

import gobbl3r.ants.Card;
import gobbl3r.ants.GraphicsView;
import gobbl3r.ants.Player;
import gobbl3r.ants.R;

public class CardWizard extends Card{

	// define card
	private static int resId = R.drawable.card03_blank;

	public CardWizard(Context context) {
		super(BitmapFactory.decodeResource(context.getResources(), resId), context);
		super.icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.wizard_hat);
		super.iconScale = 1;
		super.cost = 8;
		super.actionText = context.getString(R.string.mages) + " +1";
		super.nameColor = GraphicsView.statsBlue;
		super.name = context.getString(R.string.wizard);
	}

	@Override
	public void play(Player PlayerTurn, Player Opponent) {

		PlayerTurn.removeCrystal(cost);
		PlayerTurn.addMage(1);
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
