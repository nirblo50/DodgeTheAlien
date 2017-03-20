
// lg g4 resolution 2560 x 1440

package com.mygdx.dodgethealien;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;

import static com.badlogic.gdx.Gdx.graphics;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor
{

	//o game objects (visual)
	private Background background;		// space background
	private SpaceShip spaceShip;		// player's ship
	private Alien alien1;				// alien
	private Alien alien2;
	private Laser laser1;				// laser
	private Laser laser2;				// laser
	private Laser laser3;				// laser
	private Laser laser4;				// laser


	private MyFont font;				// text

	//o variables
	private float timePast;				//o the time that passed since the game started
	private float timeScore;			//o the time score of each game



	@Override
	public void create ()
	{
		//o creating the visual objects
		this.background = new Background();					// setting the background
		this.spaceShip = new SpaceShip();					// setting the ship's variable
		this.alien1 = new Alien();							// setting the alien's variable
		this.alien2 = new Alien();							// setting the alien's variable
		alien2.newDirection();
		this.laser1 = new Laser();							// setting the laser's variable
		this.laser2 = new Laser();							// setting the laser's variable
		this.laser3 = new Laser();							// setting the laser's variable
		this.laser4 = new Laser();							// setting the laser's variable
		this.font = new MyFont();							// setting the text's variable

		//o variables statements
		timePast = 0;										//o the time that passed since game started



	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		background.drawBackground(timePast);				// drawing the background
		Gdx.input.setInputProcessor(this);					//o setting the input from the phone (pressing the screen)
		timePast += graphics.getDeltaTime();

		float accelX = Gdx.input.getAccelerometerX();		//o the X tilt of the phone
		float accelY = Gdx.input.getAccelerometerY();		//o the Y tilt of the phone
		float accelZ = Gdx.input.getAccelerometerZ();		//o the Z tilt of the phone

		//o ship's location and drawing (ship activation)
		setShipLocation(spaceShip, accelX, accelY);			// sets the ship's location according to the phone tilt


		//o laser's location and drawing (laser activation)
		setLaserLocation(alien1, laser1);
		setLaserLocation(alien1, laser2);
		setLaserLocation(alien2, laser3);
		setLaserLocation(alien2, laser4);

		//o alien's location and drawing (alien activation)
		setAlienLocation(alien1);
		setAlienLocation(alien2);





	}
	
	@Override
	public void dispose ()
	{
		spaceShip.getBatch().dispose();
		spaceShip.getAtlas().dispose();

		alien1.getBatch().dispose();
		alien1.getAtlas().dispose();
		alien2.getBatch().dispose();
		alien2.getAtlas().dispose();

		background.getBatch().dispose();
		background.getAtlas().dispose();

		laser1.getBatch().dispose();
		laser2.getTexture().dispose();

		font.getBatch().dispose();
		font.getFont().dispose();
	}


	// sets the ship's location according to the phone tilt
	public void setShipLocation(SpaceShip ship, float accelX, float accelY)
	{
		//o set the possition according to the tilt of the phone  between the boundaries
		if (ship.getPos() + accelY / 3.8 * 15 >= 0 && ship.getPos() + ship.getWidth() + accelY / 3.8 * 15 <= graphics.getWidth())
		{
			if (accelY < -0.46 || accelY > 0.46)
				ship.setPos((int)(ship.getPos() + accelY / 3.8 * 15));
		}
		ship.drawShip(timePast);
	}



	public void setAlienLocation (Alien alien)
	{
		//o variables
		int x1 = -alien.getWidth();				// the left start location
		int x2 = Gdx.graphics.getWidth();		// the right start location

		if ( (alien.getPos() >= x1) && (alien.getPos() <= x2) )
			alien.setPos(alien.getPos() + alien.getDirection());


		else
		{
			alien.newDirection();
			if (alien.getDirection() > 0)
				alien.setPos(x1);
			else
				alien.setPos(x2);

		}
		alien.drawAlien(timePast);

	}


	public void setLaserLocation (Alien alien, Laser laser)
	{
		//o variables
		int x1 = -alien.getWidth();				// the left start location
		int x2 = Gdx.graphics.getWidth();		// the right start location

		// if alien is on the screen
		if ( (alien.getPos() >= x1) && (alien.getPos() <= x2) )
		{
			// if alien has reached the point of shot
			if(!laser.isActive() &&  (((alien.getDirection()>0) && (alien.getPos() > laser.getNextPos()) ) || ((alien.getDirection()<0) && (alien.getPos() < laser.getNextPos())  ) ) )
			{
				laser.setActive(true);
				laser.setPosX(laser.getNextPos());
			}

		}
		else
		{
			laser.randomNextPos();
			laser.setPosY(Gdx.graphics.getHeight() - laser.getHeight());
			laser.setActive(false);
		}

		// if laser has not reached all the way down yet
		if (laser.isActive() && laser.getPosY() >= -laser.getHeight())
			laser.setPosY(laser.getPosY()-17);


		laser.drawLaser();

	}



	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}


	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
