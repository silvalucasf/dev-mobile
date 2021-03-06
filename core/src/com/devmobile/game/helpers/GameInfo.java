package com.devmobile.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.devmobile.game.managers.EnemyManager;
import com.devmobile.game.managers.ItemManager;
import com.devmobile.game.managers.TileManager;
import com.devmobile.game.objects.GenericCharacter;

public class GameInfo {
    //------------------------------------------------------
    //---------------CONFIGURAÇÔES-PRINCIPAIS---------------
    //------------------------------------------------------
    public final static int WIDHT = 640;
    public final static int HEIGHT = 360;
    public final static int sizeTexture = 16; //Tamanho dos tiles 16x16
    public static float PPM = 100; // Pixel per Meters

    //------------------------------------------------------
    //-----------------VARIAVEIS-GLOBIAS--------------------
    //------------------------------------------------------

    public static World world;
    public static float mainScore;
    public static GenericCharacter mainCharacter;
    public static float velCamera = 100f; // Velocidade da camera
    public static float runningTime;

    public  enum states{
        MENU,
        GAMERUNNING,
        GAMEPAUSE
    }
    public static states currentScreen;

    //------------------------------------------------------
    //---------------------COIN-CHANCE----------------------
    //------------------------------------------------------
    public static int coinChance = 40; // Chance de gerar as moedas em cima de um terreno;
    public static int coinSmallChance = 5;
    public static int coinMediumChance = 2;
    public static int coinLargerChance = 1;

    //------------------------------------------------------
    //--------------------ENEMY-CHANCE----------------------
    //------------------------------------------------------
    public static int enemyChance = 30;
    public static int enemyOctiChance = 3;
    public static int enemyPokeyBubChance = 2;
    public static int enemyRoboPumpkinChance = 2;
    public static int enemySpikeyBubChance = 2;
    public static int enemyBirdChance = 0;
    public static int enemyDevilChance = 0;
    public static int enemyBlockyBubChance = 2;
    public static int enemyBubChance = 2;

    //------------------------------------------------------
    //-----------------------MANAGERS-----------------------
    //------------------------------------------------------
    //Referência dos managers
    public static TileManager tileManager;
    public static ItemManager itemManager;
    public static EnemyManager enemyManager;
    public static Skin menuSkin;

    //------------------------------------------------------
    //-----------------------PARALLAX-----------------------
    //------------------------------------------------------
    //Parallax, os valores vão de 0 a 100, sendo 100 parado e 0 sem efeito
    public static int parallaxBG01 = 50;
    public static int parallaxBG02 = 60;
    public static int parallaxBG03 = 70;
    //O valor do back vai define quando tempo irá durar o bioma atual
    public static int parallaxBG04 = 80;

    //------------------------------------------------------
    //-----------------------BIOMAS-------------------------
    //------------------------------------------------------
    //Configuração referência dos biomas
    public final static String grasslandBG = "Grassland_BG_";
    public final static String autumnForestBG = "AutumnForest_BG_";
    public final static String tropicsBG = "Tropics_BG_";
    public final static String winterWorldBG = "WinterWorld_BG_";
    public static String currentBiome; //Bioma atual do mapa

    //------------------------------------------------------
    //-----------------------TERRENOS-----------------------
    //------------------------------------------------------
    //Configuração da geração do terreno de baixo
    public static int downGroundMaxHeight = 5;
    public static int downGroundMinHeight = 1;
    public static int downGroundMinWight = 3;
    public static int downGroundMaxWight = 30;
    public static int downGroundMinSpace = 4;
    public static int downGroundMaxSpace = 8;

    //Configuração da geração do terreno de cima
    public static int topGroundMinPosition = 8; //minY
    public static int topGroundMaxPosition = 16; //maxY
    public static int topGroundMinSpace = 6; // Tamanho máximo do espaco entre os tiles
    public static int topGroundMaxSpace = 6; // Tamanho máximo do espaco entre os tiles
    public static int topGroundMinWight = 15;
    public static int topGroundMaxWight = 15;

    //------------------------------------------------------
    //------------------------METODOS-----------------------
    //------------------------------------------------------

    public static BitmapFont criarFonte(String nomeFonte, int tamanho){
        FreeTypeFontGenerator.setMaxTextureSize(FreeTypeFontGenerator.NO_MAXIMUM);
        FreeTypeFontGenerator gerador = new FreeTypeFontGenerator(Gdx.files.internal("Font/" + nomeFonte));
        FreeTypeFontGenerator.FreeTypeFontParameter parametros = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parametros.size = tamanho;

        BitmapFont fonte = gerador.generateFont(parametros);
        gerador.dispose();

        return(fonte);
    }
}