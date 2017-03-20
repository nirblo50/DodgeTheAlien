package com.mygdx.dodgethealien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
    private int posY;

    public Laser()
    {
        this.height = Gdx.graphics.getHeight() / 9;
        this.width = this.height /2;
        this.posX = 0;
        this.posY = 0;
        this.texture = new Texture(Gdx.files.internal("laser.png"));
        this.batch = new SpriteBatch();
    }

    public void drawLaser(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;

        batch.begin();
        batch.draw(texture, this.posX, this.posY, this.width, this.height);
        batch.end();

    }


    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setPosX(int posX) {
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


}