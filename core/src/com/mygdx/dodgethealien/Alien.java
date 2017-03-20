package com.mygdx.dodgethealien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import static com.badlogic.gdx.Gdx.graphics;

/**
 * Created by nirbl on 20/03/2017.
 */

public class Alien
{
    private int height;    // alien's height
    private int width;     // alien's width
    private int pos;       // alien's position


    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Animation animation;


    public Alien()
    {

        this.height = graphics.getHeight() / 8;		//o setting the proportions for the alien
        this.width = this.height * 2;		//o setting the proportions for the alien
        this.pos = 0;

        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("alien.atlas"));    //creates the animation of the alien
        animation = new Animation(1 / 30f, atlas.getRegions());
    }


    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setWidth(int width)
    {
        this.width = width;
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

    public void drawAlien(int posX, int posY, float timePast)
    {
        batch.begin();
        batch.draw(animation.getKeyFrame(timePast, true), posX, posY, width, height);
        batch.end();
    }
}
