package com.mygdx.dodgethealien;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import static com.badlogic.gdx.Gdx.graphics;

public class MyGdxGame extends ApplicationAdapter
{

	//o game objects (visual)
	private Background background;		// space background
	private SpaceShip spaceShip;		// player's ship
	private Alien alien;				// alien
	private Laser laser;				// laser
	private MyFont font;				// text

	//o variables
	private float timePast;				//o the time that passed since the game started
	private float timeScore;			//o the time score of each game



	@Override
	public void create ()
	{
		//o creating the visual objects
		this.background = new Background();		// setting the background
		this.spaceShip = new SpaceShip();		// setting the ship's variable
		this.alien = new Alien();				// setting the alien's variable
		this.laser = new Laser();				// setting the laser's variable
		this.font = new MyFont();				// setting the text's variable

		timePast = 0;							//o the time that passed since game started



	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		timePast += graphics.getDeltaTime();			//o calculate the time that past simce game has began

		background.drawBackground(timePast);		// drawing the background
		spaceShip.drawShip(0, timePast);			// drawing the space ship
		alien.drawAlien(0, graphics.getHeight() - alien.getHeight(), timePast);		// drawing the alien
		laser.drawLaser(500,500);
		font.drawFont("Your score is: 100", 1000, 700);


	}
	
	@Override
	public void dispose ()
	{

	}
}
