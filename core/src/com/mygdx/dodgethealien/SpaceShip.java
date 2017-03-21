package com.mygdx.dodgethealien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import static com.badlogic.gdx.Gdx.graphics;

/**
 * Created by nirbl on 19/03/2017.
 */

public class SpaceShip
{
    private int height;    // ship's height
    private int width;     // ship's width
    private int pos;       // ship's position

    //space ship
    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Animation animation;

    //shield
    private Texture texture;
    private Batch batch2;
    private float startTime;


    public SpaceShip()
    {
        this.height = graphics.getHeight() / 6;		//o setting the proportions for the ship
        this.width = this.height * 2;		//o setting the proportions for the ship
        this.pos = graphics.getWidth()/2 - this.width/2;

        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("spaceShip.atlas"));    //creates the animation of the ship
        animation = new Animation(1 / 30f, atlas.getRegions());
        texture = new Texture(Gdx.files.internal("shield.png"));
        batch2 = new SpriteBatch();
        startTime = 0;
    }

    public void drawShip(float timePast)
    {
        batch.begin();
        batch.draw(animation.getKeyFrame(timePast, true), this.pos, 0, width, height);
        batch.end();
        drawShield(0);
    }

    public void drawShip(int posX, float timePast)
    {
        this.pos = posX;
        batch.begin();
        batch.draw(animation.getKeyFrame(timePast, true), posX, 0, width, height);
        batch.end();
    }

    public void drawShield(float timePast)
    {
        int x = this.pos - width/2;
        int y = - this.height+ height/2;

        if ( ((double)timePast - (double)startTime) >= 2.5)
            this.startTime = 0;

        if (startTime != 0)
        {
            batch.begin();
            batch.draw(texture,x, y, this.width*2, this.height*2);
            batch.end();
        }
    }


    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Batch getBatch()
    {
        return this.batch;
    }

    public int getPos() {
        return pos;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public Animation getAnimation() {
        return animation;
    }

    public Texture getTexture() {
        return texture;
    }

    public Batch getBatch2() {
        return batch2;
    }

    public float getStartTime() {
        return startTime;
    }

    public void setStartTime(float startTime)
    {
        if (this.startTime == 0)
            this.startTime = startTime;
    }

    public boolean isShiledOn()
    {
        if (startTime > 0)
            return true;
        return false;
    }
}
