package com.mygdx.dodgethealien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by nirbl on 20/03/2017.
 */

public class MyFont
{
    private SpriteBatch batch;
    private BitmapFont font;
    private int size;


    public MyFont()
    {
        this.size = 100;

        batch = new SpriteBatch();
        FileHandle fontFile = Gdx.files.internal("Amble-Light.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = this.size;
        param.borderColor = Color.RED;
        param.borderWidth = 1;
        //param.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        font = generator.generateFont(param);
        generator.dispose();
    }



    public void drawFont(String str, int posX, int posY)
    {
        batch.begin();
        font.draw(batch, str, posX, posY);
        batch.end();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }
}
