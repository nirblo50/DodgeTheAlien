
// lg g4 resolution 2560 x 1440

package com.mygdx.dodgethealien;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;

import static com.badlogic.gdx.Gdx.graphics;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor
{

	//o game objects (visual)
	private Background background;		// space background
	private SpaceShip spaceShip;		// player's ship
	private Alien alien1;				// alien
	private Alien alien2;				// alien
	private Laser laser1;				// laser
	private Laser laser2;				// laser
	private Laser laser3;				// laser
	private Laser laser4;				// laser
	private MyFont timeText;			// text
	private StartMenu startMenu;		// the start menu
	private LostMenu lostMenu;			// the lost menu

	//o variables
	private float timePast;				// the time that passed since the game started
	private float timeScore;			// the time score of each game
	private boolean isGame;			// if player started the game
	private boolean gameLost;			// if player lost the game
	private float highScore;

	//o the Preferences of the high score
	Preferences prefs;


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
		this.timeText = new MyFont(75);						// setting the text's variable
		this.startMenu = new StartMenu();					// setting the start menu
		this.lostMenu = new LostMenu();						// setting the lost menu

		//o variables statements
		timePast = 0;										// the time that passed since app started
		timeScore = 0;										// the time that passed since game actually started
		isGame = false;									// game not started, show menu
		gameLost = false;									// game was lost

		//o pull the high score from memory
		prefs = Gdx.app.getPreferences("highScore");
		highScore = prefs.getFloat("highScore");

	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		background.drawBackground(timePast);				// drawing the background
		Gdx.input.setInputProcessor(this);					//o setting the input from the phone (pressing the screen)
		timePast += graphics.getDeltaTime();
		timeText.drawTime(timeScore);

		float accelX = Gdx.input.getAccelerometerX();		//o the X tilt of the phone
		float accelY = Gdx.input.getAccelerometerY();		//o the Y tilt of the phone
		float accelZ = Gdx.input.getAccelerometerZ();		//o the Z tilt of the phone

		if(!isGame && !gameLost)
			startMenu.drawMenu();

		// if the game has started and player did not lose yet
		if (isGame)
		{
			timeScore = timePast;
			didLose(spaceShip, laser1,laser2, laser3, laser4);		// checks if the game was lost and change the isGame and didLose flags

			//o ship's location and drawing (ship activation)
			setShipLocation(spaceShip, accelX, accelY);            // sets the ship's location according to the phone tilt


			//o laser's location and drawing (laser activation)
			setLaserLocation(alien1, laser1);
			setLaserLocation(alien1, laser2);
			setLaserLocation(alien2, laser3);
			setLaserLocation(alien2, laser4);

			//o alien's location and drawing (alien activation)
			setAlienLocation(alien1);
			setAlienLocation(alien2);
		}
		else if(gameLost)
		{
			//o check if new score is better than the old high score
			if (timeScore > highScore)
			{
				//o put the new score as the new high score
				highScore = timeScore;
				prefs.putFloat("highScore", highScore);

				//o update your preferences
				prefs.flush();
			}

			lostMenu.drawMenu(timeScore, highScore);
		}




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
		laser1.getTexture().dispose();
		laser2.getBatch().dispose();
		laser2.getTexture().dispose();
		laser3.getBatch().dispose();
		laser3.getTexture().dispose();
		laser4.getBatch().dispose();
		laser4.getTexture().dispose();

		startMenu.getBatch().dispose();
		startMenu.getTexture().dispose();
		lostMenu.getBatch().dispose();
		lostMenu.getTexture().dispose();

		timeText.getBatch().dispose();
		timeText.getFont().dispose();
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


	//o controll the alien ship and activate it
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


	//o controll the laser and activate it
	public void setLaserLocation(Alien alien, Laser laser)
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
		else if (laser.getPosY() <= -laser.getHeight())
		{
			laser.randomNextPos();
			laser.setPosY(Gdx.graphics.getHeight() - laser.getHeight());
			laser.setActive(false);
		}

		// if laser has not reached all the way down yet
		if (laser.isActive() && laser.getPosY() >= -laser.getHeight())
			laser.setPosY(laser.getPosY()-18);

		laser.drawLaser();
	}

	//o checks if laser has hit the ship
	public boolean isCrash (SpaceShip ship, Laser laser)
	{
		int x1 = ship.getPos()  ;
		int x2 = x1 + ship.getWidth();
		int x3 = laser.getPosX();
		int x4 = x3 + laser.getWidth();

		int y1 = ship.getHeight();
		int y2 = laser.getPosY();

		if ( y2 <= y1 && y2 > 0 )
		{
			if ( (x3 > x1 && x3 < x2) || (x4 > x1 && x4 < x2) )
				return true;
		}

		return false;
	}


	public boolean didLose(SpaceShip ship, Laser laser1, Laser laser2, Laser laser3, Laser laser4)
	{
		if (isCrash(ship, laser1) || isCrash(ship, laser2) || isCrash(ship, laser3) || isCrash(ship, laser4))
		{
			isGame = false;
			gameLost = true;
			return true;
		}

		return false;
	}



	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		if (!isGame && !gameLost)
			isGame = true;

		if (gameLost)
			create();
		return true;
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
