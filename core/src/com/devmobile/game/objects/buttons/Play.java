package com.devmobile.game.objects.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.devmobile.game.DevMobile;
import com.devmobile.game.helpers.GameInfo;
import com.devmobile.game.scenes.GameScreen;

public class Play extends GenericButton{
    public Play(final DevMobile game, BitmapFont font, int tamanho) {
        super(GameInfo.menuSkin.getDrawable("play"),GameInfo.menuSkin.getDrawable("play_pressed"), font, tamanho);
        button.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                switch (GameInfo.currentScreen){
                    case MENU:
                        game.setScreen(new GameScreen(game));
                        break;
                    case GAMEPAUSE:
                        GameInfo.currentScreen = GameInfo.states.GAMERUNNING;
                        break;
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
}
