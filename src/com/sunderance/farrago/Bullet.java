package com.sunderance.farrago;

import org.newdawn.slick.GameContainer;

abstract public class Bullet extends Entity {
	private boolean offScreen = false;
	private boolean hitSomething = false;
	
	public Bullet(double initial_x, double initial_y, ImageFactory imageFactory) {
		super(initial_x, initial_y, imageFactory);
		
	}

	@Override
	protected void stepBehaviour(GameContainer gc, int delta) {
		// check if off screen
		double x, y;
		x = getX(); y = getY();
		if (x > gc.getWidth() || x < 0 || y > gc.getHeight() || y < 0) {
			offScreen = true;
		}
	}

	@Override
	public boolean isDead() {
		return offScreen || hitSomething;
	}

}