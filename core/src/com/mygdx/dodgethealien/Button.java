package com.mygdx.dodgethealien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by nirbl on 23/03/2017.
 */

public class Button
{
    private Texture texture;
    private Batch batch;
    private int size;
    private boolean active;

    public Button(String type)
    {
        this.active = false;
        this.size = Gdx.graphics.getWidth() / 15;

        if (type.equals("play")) {
            texture = new Texture(Gdx.files.internal("play.png"));
            this.size = Gdx.graphics.getWidth() / 5;
        }
        else
            texture = new Texture(Gdx.files.internal("pause.png"));
        batch = new SpriteBatch();
    }

    public void drawPause()
    {
        int x = Gdx.graphics.getWidth() - this.size;
        int y = Gdx.graphics.getHeight() - this.size;

        batch.begin();
        batch.draw(texture, x, y, this.size, this.size);
        batch.end();
    }

    public void drawPlay()
    {
        int x = Gdx.graphics.getWidth()/2 - this.size/2;
        int y = Gdx.graphics.getHeight()/2 - this.size/2;

        batch.begin();
        batch.draw(texture, x, y, this.size, this.size);
        batch.end();
    }



    public Texture getTexture()
    {
        return texture;
    }

    public Batch getBatch() {
        return batch;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public int getPausePosX ()
    {
        return Gdx.graphics.getWidth() - this.size;
    }

    public int getPausePosY()
    {
        return Gdx.graphics.getHeight() - this.size;
    }
    public int getPlayPosX ()
    {
        return Gdx.graphics.getWidth()/2 - this.size/2;
    }

    public int getPlayPosY()
    {
        return Gdx.graphics.getHeight()/2 - this.size/2;
    }



}

