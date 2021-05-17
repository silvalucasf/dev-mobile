package com.devmobile.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.devmobile.game.DevMobile;
import com.devmobile.game.helpers.GameInfo;

public class MenuScreen implements Screen {

    final DevMobile game;

    //Configuração da câmera
    private OrthographicCamera mainCamera;
    private Viewport gameViewport;

    private Stage stage;
    private TextureAtlas atlasMenu;
    private Skin skin;
    private Table mainTable;
    private TextButton buttonPlay, buttonOptions;
    private BitmapFont fonte;

    public MenuScreen (DevMobile game){
        this.game = game;

        //Configuração da camera e do viewport da tela
        mainCamera = new OrthographicCamera(GameInfo.WIDHT, GameInfo.HEIGHT);
        mainCamera.position.set(GameInfo.WIDHT/2f, GameInfo.HEIGHT/2f, 0f);
        gameViewport = new StretchViewport(GameInfo.WIDHT, GameInfo.HEIGHT, mainCamera);


    }

    @Override
    public void show() {
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        atlasMenu = new TextureAtlas("UI_buttons.txt");
        skin = new Skin(atlasMenu);

        mainTable = new Table(skin);
        mainTable.setBounds(0, 0,Gdx.graphics.getWidth()*1.50f, Gdx.graphics.getHeight());

        fonte = criarFonte("FreePixel.ttf", 100);
        fonte.getData().setScale(1f);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle textButtonStyle2 = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("play");
        textButtonStyle.down = skin.getDrawable("play_pressed");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = fonte;
        textButtonStyle2.up = skin.getDrawable("options");
        textButtonStyle2.down = skin.getDrawable("options_pressed");
        textButtonStyle2.pressedOffsetX = 1;
        textButtonStyle2.pressedOffsetY = -1;
        textButtonStyle2.font = fonte;

        buttonPlay = new TextButton("       ", textButtonStyle);
        buttonOptions = new TextButton("       ", textButtonStyle2);

        buttonOptions.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        mainTable.add(buttonPlay);
        mainTable.row();
        mainTable.add(buttonOptions);
        mainTable.debug();
        stage.addActor(mainTable);
    }

    private BitmapFont criarFonte(String nomeFonte, int tamanho){
        FreeTypeFontGenerator.setMaxTextureSize(FreeTypeFontGenerator.NO_MAXIMUM);
        FreeTypeFontGenerator gerador = new FreeTypeFontGenerator(Gdx.files.internal("Font/" + nomeFonte));
        FreeTypeFontGenerator.FreeTypeFontParameter parametros = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parametros.size = tamanho;

        BitmapFont fonte = gerador.generateFont(parametros);
        gerador.dispose();

        return(fonte);
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Parte da configuração da camera
        game.batch.setProjectionMatrix(mainCamera.combined);
        mainCamera.update();

        game.batch.begin();



        stage.act(delta);
        stage.draw();

/*
        float x = (GameInfo.WIDHT / 2 + 98);
        if(Gdx.input.getX() < x + playButtonInactive.getWidth() && Gdx.input.getX() > x &&  Gdx.input.getY() < (GameInfo.HEIGHT / 2) + playButtonInactive.getHeight() && Gdx.input.getY() > GameInfo.HEIGHT / 2){
            game.batch.draw(playButtonInactive, x, GameInfo.HEIGHT / 2, 147, 60);
        }else{
            game.batch.draw(playButtonPressed, x, GameInfo.HEIGHT / 2, 147, 60);
        }

        game.batch.draw(optionsButtonInactive, GameInfo.WIDHT / 2 + 98 ,100, 147, 60);*/

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
