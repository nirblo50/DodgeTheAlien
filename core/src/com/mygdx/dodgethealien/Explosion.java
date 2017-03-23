package com.mygdx.dodgethealien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import static com.badlogic.gdx.Gdx.graphics;

/**
 * Created by nirbl on 21/03/2017.
 */

public class Explosion
{

    private int height;    //explosion's height
    private int width;     //explosion's width
    private int pos;


    private SpriteBatch batch;
    private TextureAtlas exp;
    private Animation animation;


    public Explosion()
    {
        this.height = graphics.getHeight() / 3;
        this.width = (int)(this.height * 0.8) ;
        this.pos = 0;

        batch = new SpriteBatch();
        exp = new TextureAtlas(Gdx.files.internal("explosion.atlas"));    //creats the animation of the left boat
        animation = new Animation(1 / 20f, exp.getRegions());
    }

    public void drawExplosion(SpaceShip ship, float timePast)
    {
        int posX = ship.getPos() + ship.getWidth()/2 - this.width/2;
        int posY = ship.getHeight()/3;
        batch.begin();
        batch.draw(animation.getKeyFrame(timePast, true), posX, posY, width, height);
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



    public Batch getBatch()
    {
        return this.batch;
    }

    public int getPos() {
        return pos;
    }

    public TextureAtlas getTexture() {
        return exp;
    }

    public Animation getAnimation() {
        return animation;
    }


}
