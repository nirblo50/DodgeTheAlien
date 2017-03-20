package com.mygdx.dodgethealien;

import com.badlogic.gdx.Gdx;
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


    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Animation animation;


    public SpaceShip()
    {

        this.height = graphics.getHeight() / 6;		//o setting the proportions for the ship
        this.width = this.height * 2;		//o setting the proportions for the ship
        this.pos = 0;

        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("spaceShip.atlas"));    //creates the animation of the ship
        animation = new Animation(1 / 30f, atlas.getRegions());
    }

    public void drawShip(float timePast)
    {
        batch.begin();
        batch.draw(animation.getKeyFrame(timePast, true), this.pos, 0, width, height);
        batch.end();
    }

    public void drawShip(int posX, float timePast)
    {
        this.pos = posX;
        batch.begin();
        batch.draw(animation.getKeyFrame(timePast, true), posX, 0, width, height);
        batch.end();
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


}
