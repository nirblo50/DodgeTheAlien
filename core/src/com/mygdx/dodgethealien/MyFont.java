package com.mygdx.dodgethealien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by nirbl on 20/03/2017.
 */

public class MyFont
{
    //**************************
    private SpriteBatch batch;
    private Texture img;
    private BitmapFont font;
    //**************************

    public MyFont()
    {
        //******************************
        batch = new SpriteBatch();

        FileHandle fontFile = Gdx.files.internal("Amble-Light.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 100;
        param.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        font = generator.generateFont(param);
        generator.dispose();
        //********************************
    }



    public void drawFont(String str, int posX, int posY)
    {
        //*************************
        batch.begin();
        //batch.draw(img, 0, 0);
        font.draw(batch, str, posX, posY);
        batch.end();
        //*************************
    }
}
