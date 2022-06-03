package com.mygdx.dodgethealien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by nirbl on 20/03/2017.
 */

public class StartMenu
{
    private Texture texture;
    private Batch batch;

    public StartMenu()
    {
        texture = new Texture(Gdx.files.internal("startMenu.png"));
        batch = new SpriteBatch();
    }

    public void drawMenu()
    {
        batch.begin();
        batch.draw(texture,0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

    }

    public Texture getTexture()
    {
        return texture;
    }

    public Batch getBatch() {
        return batch;
    }


}