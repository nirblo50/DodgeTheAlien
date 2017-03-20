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


    public MyFont(int size)
    {
        this.size = size;

        batch = new SpriteBatch();
        FileHandle fontFile = Gdx.files.internal("Amble-Light.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = this.size;
        param.borderColor = Color.RED;
        param.borderWidth = 4;
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

    public void drawTime(float time)
    {
        int x = (int)font.getLineHeight()/2;
        int y = Gdx.graphics.getHeight() - x ;

        batch.begin();
        font.draw(batch, floatToScore(time)+"(s)",x,y);
        batch.end();
    }

    public void drawScore(float time, float highScore)
    {
        int x = Gdx.graphics.getWidth() /2 - font.getRegion().getRegionWidth()/2;
        int y = Gdx.graphics.getHeight() /2 + (int)(font.getLineHeight()*1.3);

        batch.begin();
        font.draw(batch, "Your score is: " + floatToScore(time)+"(s)",x,y);
        font.draw(batch, "High score: " + floatToScore(highScore)+"(s)",x,y - font.getLineHeight());
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

    // taking the time and making it a score with only 2 characters after the dot
    private String floatToScore(float time)
    {
        String score = String.valueOf(time);;
        int index = 0;
        for (int i =0; i<score.length(); i++)
        {
            if( (score.charAt(i) == '.'))
                index = i;
        }

        if (score.length() > index + 3)
            return score.substring(0, index + 3);

        return score;
    }

}
