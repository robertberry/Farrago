package com.sunderance.farrago;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

/**
 * Base class for in game objects
 * 
 * @author Robert Berry
 */
abstract public class Entity {
	private double x, y, xSpeed=0, ySpeed=0, xAcceleration=0, yAcceleration=0, radius;
	private Image sprite;
	
	/**
	 * Constructs an Entity at a given position with its sprite loaded from a
	 * given ImageFactory and with its radius explicitly set.
	 * 
	 * @param initial_x
	 * @param initial_y
	 * @param imageFactory
	 * @param _radius
	 */
	public Entity(double initial_x, double initial_y,
			ImageFactory imageFactory, double _radius) {
		x = initial_x;
		y = initial_y;
		sprite = imageFactory.createImage(getSpritePath());
		radius = _radius;
	}
	
	/**
	 * Constructs an Entity at a given position with its sprite loaded from
	 * a given ImageFactory and its radius automatically calculated from the
	 * width and height of the image loaded.
	 * 
	 * @param initial_x
	 * @param initial_y
	 * @param imageFactory
	 */
	public Entity(double initial_x, double initial_y,
			ImageFactory imageFactory) {
		x = initial_x;
		y = initial_y;
		sprite = imageFactory.createImage(getSpritePath());
		radius = Math.min(sprite.getHeight(), sprite.getWidth()) / 2.0;
	}
	
	/**
	 * Returns the path to the sprite image in the resources folder
	 * 
	 * @return The path
	 */
	abstract protected String getSpritePath();
	
	/**
	 * Performs subclass specific behaviour each step
	 * 
	 * @param gc The GameContainer for the game
	 * @param delta The time delta
	 */
	abstract protected void stepBehaviour(GameContainer gc, int delta);
	
	/**
	 * Returns whether the entity is dead (and therefore can be removed from
	 * play).
	 * 
	 * @return Whether dead
	 */
	abstract public boolean isDead();
	
	/**
	 * Updates position of the entity given the amount of time passed
	 * 
	 * @param time The amount of time elapsed
	 */
	public void step(GameContainer gc, int delta) {
		stepBehaviour(gc, delta);
		x += xSpeed + Math.pow(delta, 2) * xAcceleration / 2;
		y += ySpeed + Math.pow(delta, 2) * yAcceleration / 2;
	}
	
	/**
	 * Draws the image on the screen
	 */
	public void draw() {
		sprite.drawCentered((int) x, (int) y);
	}

	/**
	 * The x speed of the Entity
	 * 
	 * @return The speed
	 */
	public double getXSpeed() {
		return xSpeed;
	}

	/**
	 * Sets the x speed of the Entity
	 * 
	 * @param xSpeed The speed
	 */
	public void setXSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	/**
	 * Gets the y speed of the Entity
	 * 
	 * @return The speed
	 */
	public double getYSpeed() {
		return ySpeed;
	}

	/**
	 * Sets the y speed of the Entity
	 * 
	 * @param ySpeed The speed
	 */
	public void setYSpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

	/**
	 * Gets the x acceleration of the Entity
	 * 
	 * @return The acceleration
	 */
	public double getXAcceleration() {
		return xAcceleration;
	}

	/**
	 * Sets the x acceleration of the Entity
	 * 
	 * @param xAcceleration The acceleration
	 */
	public void setXAcceleration(double xAcceleration) {
		this.xAcceleration = xAcceleration;
	}

	/**
	 * Gets the y acceleration of the Entity
	 * 
	 * @return yAcceleration The acceleration
	 */
	public double getYAcceleration() {
		return yAcceleration;
	}

	/**
	 * Sets the y acceleration of the Entity
	 * 
	 * @param yAcceleration The acceleration
	 */
	public void setYAcceleration(double yAcceleration) {
		this.yAcceleration = yAcceleration;
	}

	/**
	 * Returns the radius of the Entity in pixels
	 * 
	 * @return The radius
	 */
	public double getRadius() {
		return radius;
	}
	
	/**
	 * Returns the x coordinate of the Entity
	 * 
	 * @return X coordinate in pixels
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Returns the y coordinate of the Entity
	 * 
	 * @return Y coordinate in pixels
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Returns the distance in pixels from another Entity
	 * 
	 * @param other The other entity
	 * @return The distance
	 */
	public double getDistanceFrom(Entity other) {
		return (float) Math.sqrt(Math.pow(other.getX() - x, 2) + 
			Math.pow(other.getY() - y, 2));
	}
	
	/**
	 * Returns whether the Entity is overlapping another given Entity
	 * 
	 * @param other The other entity
	 * @return Whether overlapping
	 */
	public boolean overlaps(Entity other) {
		return radius + other.getRadius() <= getDistanceFrom(other);
	}
}
