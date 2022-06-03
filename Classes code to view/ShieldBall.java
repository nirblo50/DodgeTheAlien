package com.mygdx.dodgethealien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.Random;

/**
 * Created by nirbl on 21/03/2017.
 */

public class ShieldBall
{
    private int size;    //ball's size
    private int posX;
    private int posY;
    private boolean active;


    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Animation animation;


    public ShieldBall(int size)
    {
        this.size = Gdx.graphics.getHeight() / size;
        randomPostX();
        this.posY = Gdx.graphics.getHeight();
        this.active = false;

        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("shieldBall.atlas"));    //creates the animation of the left boat
        animation = new Animation(1 / 30f, atlas.getRegions());
    }

    public void drawShieldBall(int posX, int posY, float timePast)
    {
        batch.begin();
        batch.draw(animation.getKeyFrame(timePast, true), posX, posY, this.size, this.size);
        batch.end();
    }

    public void drawShieldBall(float timePast)
    {
        batch.begin();
        batch.draw(animation.getKeyFrame(timePast, true), this.posX, this.posY, this.size, this.size);
        batch.end();
    }



    public void setSize(int width)
    {
        this.size = width;
    }

    public void setActive(boolean active)
    {
        if (!active)
            this.randomPostX();
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public Batch getBatch()
    {
        return this.batch;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY()
    {
        return posY;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setPosX(int posX)
    {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getSize()
    {
        return size;
    }




    public void randomPostX()
    {
        int x = 0;
        int y = Gdx.graphics.getWidth() - this.size;

        Random rnd = new Random();
        this.posX = rnd.nextInt(y - x) + x;
    }
}
