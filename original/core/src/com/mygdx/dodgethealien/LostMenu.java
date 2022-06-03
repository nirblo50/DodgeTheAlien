package com.mygdx.dodgethealien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by nirbl on 21/03/2017.
 */

public class LostMenu
{
    private Texture texture;
    private Batch batch;
    private MyFont font;

    public LostMenu()
    {
        font = new MyFont(100);
        texture = new Texture(Gdx.files.internal("lost.png"));
        batch = new SpriteBatch();
    }

    public void drawMenu(float time, float highScore)
    {
        batch.begin();
        batch.draw(texture,0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        font.drawScore(time, highScore);

    }

    public Texture getTexture()
    {
        return texture;
    }

    public Batch getBatch() {
        return batch;
    }


    public MyFont getFont() {
        return font;
    }
}