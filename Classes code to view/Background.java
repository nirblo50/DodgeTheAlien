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

public class Background
{
    private int height;    // background's height
    private int width;     // background's width

    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Animation animation;


    public Background()
    {

        this.height = graphics.getHeight();		//o setting the proportions for the background
        this.width = graphics.getWidth();		//o setting the proportions for the background

        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("space.atlas"));    //creates the animation of the background
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


    public TextureAtlas getAtlas() {
        return atlas;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void drawBackground(float timePast)
    {
        batch.begin();
        batch.draw(animation.getKeyFrame(timePast, true), 0, 0, width, height);
        batch.end();
    }
}