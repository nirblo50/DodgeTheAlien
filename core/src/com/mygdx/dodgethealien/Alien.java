package com.mygdx.dodgethealien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.Random;

import static com.badlogic.gdx.Gdx.graphics;

/**
 * Created by nirbl on 20/03/2017.
 */

public class Alien
{
    private int height;    // alien's height
    private int width;     // alien's width
    private int pos;       // alien's position
    private int direction;  // alien's direction


    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Animation animation;


    public Alien()
    {
        this.height = graphics.getHeight() / 8;		//o setting the proportions for the alien
        this.width = this.height * 2;		//o setting the proportions for the alien
        this.pos = - this.width;
        direction = 11;

        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("alien.atlas"));    //creates the animation of the alien
        animation = new Animation(1 / 30f, atlas.getRegions());
    }

    public void drawAlien(float timePast)
    {
        batch.begin();
        batch.draw(animation.getKeyFrame(timePast, true), this.pos, Gdx.graphics.getHeight() - (int)(this.height*0.8), width, height);
        batch.end();
    }

    public void drawAlien(int posX, float timePast)
    {
        batch.begin();
        batch.draw(animation.getKeyFrame(timePast, true), posX, Gdx.graphics.getHeight() - this.height , width, height);
        batch.end();
        this.pos = posX;
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

    public Batch getBatch()
    {
        return this.batch;
    }

    public int getPos() {
        return pos;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public Animation getAnimation() {
        return animation;
    }

    public int getDirection()
    {
        return direction;
    }

    public void newDirection()
    {
        Random rnd = new Random();
        this.direction = rnd.nextInt(30)-15;

        while (this.direction >= -7 && this.direction <= 0)
            this.direction -= 4;
        while (this.direction <= 7 && this.direction >= 0)
            this.direction += 4;



    }
}
