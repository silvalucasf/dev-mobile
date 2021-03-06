package com.devmobile.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.devmobile.game.objects.BG;

public class BGManager {
    private BG[] bgs;
    private float nextPositionX;
    private int countBG;
    private int lastBG;
    private int countChange ;
    private float offSetX;
    private boolean isChanging;

    //Caso só seja passado uma textura
    public  BGManager(TextureRegion texture, int offSetX){
        bgs = new BG[3];
        reset(texture);
        //Checando se o valor está fora do padrão e reajustando para o limite
        if(offSetX < 0){
            offSetX = 0;
        }
        if(offSetX > 100){
            offSetX = 100;
        }
        this.offSetX = offSetX;
    }

    public void reset(TextureRegion texture){
        countBG = 0;
        lastBG = 2;
        countChange = 1;
        nextPositionX = 0;
        for (int i = 0; i < bgs.length; i++){
            bgs[i] = new BG(texture);
            bgs[i].setX(nextPositionX);
            addNextPostionX(bgs[i]);
        }
        isChanging = false;
    }

    public void update(OrthographicCamera camera){
        float delta = offSetX * Gdx.graphics.getDeltaTime();
        nextPositionX += delta;
        for (BG bg:bgs) {
            bg.setX(bg.getX() + delta);
        }
        if(bgs[countBG].isOutBounds(camera)){
            bgs[countBG].setX(nextPositionX);
            addNextPostionX(bgs[countBG]);
            change();
            addCountSprite();
        }
    }

    public void draw (SpriteBatch batch){
        for (BG bg : bgs) {
            bg.draw(batch);
        }
    }

    private void addNextPostionX (BG bg){
        nextPositionX = bg.getX() + bg.getWidth();
    }

    private void change (){
        if(isChanging){
            countChange +=1;
            if(countChange <= bgs.length){
                bgs[countBG].setTexture(bgs[lastBG].getTexture());
            }
            else {
                countChange = 1;
                isChanging = false;
            }
        }
    }

    private void addCountSprite(){
        countBG += 1;
        lastBG += 1;
        if (countBG == bgs.length) {
            countBG = 0;
        }
        if(lastBG == bgs.length){
            lastBG = 0;
        }
    }
}
