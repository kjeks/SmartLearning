package no.ntnu.tdt4240.models;

import no.ntnu.tdt4240.activities.GameActivity;
import android.graphics.Canvas;

public class Mine extends Cell {

	private String type = "mine";
	private boolean clicked;
	private Canvas image;
	
	Mine() {
		
		this.image=image;
		clicked=false;
	}

	@Override
	public void onClick() {
	
		
	}
	private void uppdateScore(Player activePlayer){
		activePlayer.setScore(activePlayer.getScore()-200);
		
	}
	
	
	

}