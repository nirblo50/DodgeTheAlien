
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
	private MyFont timeText;			// text
	private StartMenu startMenu;		// the start menu
	private LostMenu lostMenu;			// the lost menu
	private ShieldBall shieldBall;		// the shield ball
	private Explosion explosion;		// the explosion
	private Button pauseButton;			// the pause button
	private Button playButton;			// the play button
	//********************************
	private Alien [] aliens;			// the aliens
	private Laser [] lasers;			// the lasers
	//********************************

	//o variables
	private float timePast;				// the time that passed since the game started
	private float timeScore;			// the time score of each game
	private boolean isGame;				// if player started the game
	private boolean gameLost;			// if player lost the game
	private float highScore;			// the players saved high score
	private int shieldNum;

	//o the Preferences of the high score
	Preferences prefs;


	@Override
	public void create ()
	{
		//o creating the visual objects
		this.background = new Background();					// setting the background
		this.spaceShip = new SpaceShip();					// setting the ship's variable
		this.timeText = new MyFont(75);						// setting the text's variable
		this.startMenu = new StartMenu();					// setting the start menu
		this.lostMenu = new LostMenu();						// setting the lost menu
		this.shieldBall = new ShieldBall(8);				// setting the shield ball
		this.explosion = new Explosion();					// setting the explosion
		this.pauseButton = new Button("pause");				// setting the pause button
		this.playButton = new Button("play");				// setting the play button

		//***********************************
		this.aliens = new Alien[13];						// setting the aliens
		this.lasers = new Laser[aliens.length*2];			// setting the lasers
		//***********************************

		//o variables statements
		timePast = 0;										// the time that passed since app started
		timeScore = 0;										// the time that passed since game actually started
		isGame = false;										// game not started, show menu
		gameLost = false;									// game was lost
		shieldNum = 0;										// the number of shields player has

		//o pull the high score from memory
		prefs = Gdx.app.getPreferences("highScore");
		highScore = prefs.getFloat("highScore");

		for (int i=0; i<lasers.length; i++)
			lasers[i] = new Laser();
		for (int i=0; i<aliens.length; i++)
			aliens[i] = new Alien();


	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		background.drawBackground(timePast);				// drawing the background
		Gdx.input.setInputProcessor(this);					//o setting the input from the phone (pressing the screen)
		timePast += graphics.getDeltaTime();
		timeText.drawTime(timeScore, timePast, shieldNum);

		float accelX = Gdx.input.getAccelerometerX();		//o the X tilt of the phone
		float accelY = Gdx.input.getAccelerometerY();		//o the Y tilt of the phone
		float accelZ = Gdx.input.getAccelerometerZ();		//o the Z tilt of the phone


		if(!isGame && !gameLost)
			startMenu.drawMenu();

		// if the game has started and player did not lose yet
		if (isGame && !playButton.isActive())
		{
			timeScore += graphics.getDeltaTime();;
			didLose(spaceShip, lasers);		// checks if the game was lost and change the isGame and didLose flags

			//o ball's location and drawing (alien activation)
			setShieldBallLocation(shieldBall, timeScore);

			//o checks if player took ball, and act accordingly
			didTakeBall(spaceShip, shieldBall);


			//o laser's location and drawing (laser activation) for all the aliens
			for (int i = 0; i<aliens.length; i++)
			{

				if (i ==0)
					setLaserLocation(aliens[0], lasers[0]);
				else
				{
					setLaserLocation(aliens[i], lasers[i * 2]);
					setLaserLocation(aliens[i], lasers[i * 2 + 1]);
				}
			}


			// control the aliens and activate them one by one
			for (int i = 0; i<aliens.length; i++)
			{
				if ((int) timeScore / 3 > i || i == 1)		// every 3 second another ship starts
					aliens[i].setActive(true);
				if (aliens[i].isActive())
					setAlienLocation(aliens[i]);
			}

			//o ship's location and drawing (ship activation)
			setShipLocation(spaceShip, accelX, accelY);            // sets the ship's location according to the phone tilt
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
			spaceShip.drawShip(timePast);
			explosion.drawExplosion(spaceShip, timePast);
		}


		// pause button activation
		if (pauseButton.isActive())
			pauseButton.drawPause();

		// play button activation
		if (playButton.isActive())
		{
			for (int i = 0; i <aliens.length; i++)
				aliens[i].drawAlien(timePast);
			for (int i = 0; i <lasers.length; i++)
				lasers[i].drawLaser();
			
			spaceShip.drawShip(timePast);
			playButton.drawPlay();
		}

	}
	
	@Override
	public void dispose ()
	{
		spaceShip.getBatch().dispose();
		spaceShip.getAtlas().dispose();
		spaceShip.getBatch2().dispose();
		spaceShip.getTexture().dispose();

		for (int i=0; i<aliens.length; i++)
		{
			aliens[i].getBatch().dispose();
			aliens[i].getAtlas().dispose();
		}

		background.getBatch().dispose();
		background.getAtlas().dispose();


		for (int i=0; i<lasers.length; i++)
		{
			lasers[i].getBatch().dispose();
			lasers[i].getTexture().dispose();
		}

		startMenu.getBatch().dispose();
		startMenu.getTexture().dispose();
		lostMenu.getBatch().dispose();
		lostMenu.getTexture().dispose();
		lostMenu.getFont().getFont().dispose();
		lostMenu.getFont().getBatch().dispose();

		timeText.getBatch().dispose();
		timeText.getFont().dispose();
		timeText.getShieldBall().getBatch().dispose();
		timeText.getShieldBall().getAtlas().dispose();

		explosion.getBatch().dispose();
		explosion.getTexture().dispose();

		shieldBall.getBatch().dispose();
		shieldBall.getAtlas().dispose();

	}


	// sets the ship's location according to the phone tilt
	public void setShipLocation(SpaceShip ship, float accelX, float accelY)
	{
		int sensitivity = 20;
		//o set the possition according to the tilt of the phone  between the boundaries
		if (ship.getPos() + accelY / 3.8 * sensitivity >= 0 && ship.getPos() + ship.getWidth() + accelY / 3.8 * sensitivity <= graphics.getWidth())
		{
			if (accelY < -0.46 || accelY > 0.46)
				ship.setPos((int)(ship.getPos() + accelY / 3.8 * sensitivity));
		}
		ship.drawShip(timePast);
		ship.drawShield(timeScore);
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
		System.out.println("********   "+alien.getDirection()+ "  ***************");
	}


	//o control the laser and activate it
	public void setLaserLocation(Alien alien, Laser laser)
	{
		//o variables
		int x1 = -alien.getWidth();				// the left start location
		int x2 = Gdx.graphics.getWidth();		// the right start location
		int speed = 20;

		// if alien is on the screen
		if ( (alien.getPos() >= x1) && (alien.getPos() <= x2) && alien.isActive())
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
			laser.setPosY(laser.getPosY() - (int)(graphics.getHeight() *speed /2560 ));

		laser.drawLaser();
	}


	//o control the shieldBall and activate it
	public void setShieldBallLocation(ShieldBall ball, float timeScore)
	{
		int y = Gdx.graphics.getHeight();
		int speed = (Gdx.graphics.getHeight() * 20) / 2560;
		int time = 10;

		//activate shield ball every 10 seconds
		if ( (int)timeScore % time == 0  && (int) timeScore != 0)
			ball.setActive(true);

		// if shield is active
		if (ball.isActive())
		{
			// if shield has not reached all the way down
			if (ball.getPosY() > - ball.getSize())
				ball.setPosY(ball.getPosY() - speed );

			// if ball has reached all the way down
			else
			{
				ball.setActive(false);
				ball.setPosY(y);
				ball.getBatch().enableBlending();
			}
		}

		ball.drawShieldBall(timePast);
	}


	public  boolean didTakeBall(SpaceShip ship, ShieldBall ball)
	{
		int x1 = ship.getPos();											// the left side if the ship
		int x2 = x1 + ship.getWidth();									// the right side if the ship
		int x3 = ball.getPosX();						    			// the left side if the laser
		int x4 = x3 + ball.getSize();									// the right side if the laser

		int y1 = ship.getHeight();
		int y2 = ball.getPosY();

		if ( y2 <= y1/2 && y2 > 0 )
		{
			if ( (x3 > x1 && x3 < x2) || (x4 > x1 && x4 < x2) )
			{
				ball.getBatch().disableBlending();
				shieldNum ++;
				ball.setActive(false);
				ball.setPosY(graphics.getHeight());
				return true;
			}
		}
		return false;
	}


	//o checks if laser has hit the ship
	public boolean isCrash (SpaceShip ship, Laser laser)
	{
		int x1 = ship.getPos()+ ship.getWidth()/8;						// the left side if the ship
		int x2 = x1 + ship.getWidth() - (int)(ship.getWidth() / 4);									// the right side if the ship
		int x3 = laser.getPosX();										// the left side if the laser
		int x4 = x3 + laser.getWidth();									// the right side if the laser

		int y1 = ship.getHeight();
		int y2 = laser.getPosY();

		if (ship.isShiledOn())
			return false;

		if ( y2 <= y1/2 && y2 >= laser.getHeight()/5 )
		{
			if ( (x3 >= x1 && x3 <= x2) || (x4 >= x1 && x4 <= x2) )
				return true;
		}
		return false;
	}


	public boolean didLose(SpaceShip ship, Laser[] laserss)
	{
		for (int i=0; i<aliens.length; i++)
		{
			if (isCrash(ship, laserss[i]))
			{
				isGame = false;
				gameLost = true;
				return true;
			}
		}
		return false;
	}



	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		int touchX = screenX;								// the X of the touch
		int touchY = Gdx.graphics.getHeight() - screenY;	// the Y of the touch

		// if player has started the game
		if (!isGame && !gameLost)
		{
			isGame = true;
			timeScore = 0;
			this.pauseButton.setActive(true);
		}

		// if player uses his shield
		if (isGame && shieldNum > 0 &&!spaceShip.isShiledOn())
		{
			this.spaceShip.setStartTime(timeScore);
			shieldNum --;
		}

		// if player pressed pause
		if (pauseButton.isActive() && touchX >= pauseButton.getPausePosX() && touchX <= pauseButton.getPausePosX() + pauseButton.getSize())
		{
			if (touchY >= pauseButton.getPausePosY() && touchY <= pauseButton.getPausePosY() + pauseButton.getSize())
			{
				playButton.setActive(true);
				pauseButton.setActive(false);
			}
		}

		// if player pressed pause
		if (playButton.isActive() && touchX >= playButton.getPlayPosX() && touchX <= playButton.getPlayPosX() + playButton.getSize())
		{
			if (touchY >= playButton.getPlayPosY() && touchY <= playButton.getPlayPosY() + playButton.getSize())
			{
				playButton.setActive(false);
				pauseButton.setActive(true);
			}
		}




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
