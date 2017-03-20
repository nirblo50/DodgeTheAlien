package com.mygdx.dodgethealien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Created by nirbl on 20/03/2017.
 */

public class Laser
{
    private Texture texture;
    private Batch batch;
    private int height;
    private int width;
    private int posX;
    private int nextPos;
    private int posY;
    private int acceleration;
    private boolean active;
    private boolean onTheWay;


    public Laser()
    {
        this.active = false;
        this.onTheWay = false;
        this.height = Gdx.graphics.getHeight() / 10;
        this.width = this.height /2;
        this.posX = Gdx.graphics.getWidth()/2;
        this.posY = Gdx.graphics.getHeight() - (int) (height*1.2);
        randomNextPos();
        this.acceleration = 0;
        this.texture = new Texture(Gdx.files.internal("laser.png"));
        this.batch = new SpriteBatch();
    }

    public void drawLaser(int posX, int posY)
    {
        if (this.active)
        {
            batch.begin();
            batch.draw(texture, posX, posY, this.width, this.height);
            batch.end();
            this.posX = posX;
            this.posY = posY;
        }
    }

    public void drawLaser()
    {
        if (this.active)
        {
            batch.begin();
            batch.draw(texture, this.posX, this.posY, this.width, this.height);
            batch.end();
        }
    }


    public void setPosY(int posY)
    {
        if (this.active)
            this.posY = posY;
    }

    public void setPosX(int posX)
    {
      if (this.active)
        this.posX = posX;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Texture getTexture()
    {
        return texture;
    }

    public Batch getBatch() {
        return batch;
    }


    public int getNextPos() {
        return nextPos;
    }

    public void randomNextPos()
    {
        Random rnd = new Random();
        if (this.active)
            this.nextPos = rnd.nextInt(Gdx.graphics.getWidth() - this.width*2) + this.width;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOnTheWay(boolean onTheWay) {
        this.onTheWay = onTheWay;
    }

    public boolean isOnTheWay() {
        return onTheWay;
    }
}