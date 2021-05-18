package com.devmobile.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.devmobile.game.helpers.GameInfo;
import com.devmobile.game.tiles.GenericCharacter;
import com.devmobile.game.tiles.GenericCharacter02;
import com.devmobile.game.tiles.GenericTile;

import java.util.ArrayList;
import java.util.List;

public class MapManager {
    RandomTileManager randomTile;
    GenericTile[][] tiles;
    ArrayList<String> biomes;

    final TileManager tileManager;
    final ParallaxManager parallaxManager;

    int sizeX, sizeY;
    int currentX, currentY;
    int playerY;

    GenericCharacter02 character;

    private World world;

    public MapManager(World world){
        //Tamanho do mapa
        sizeX = (GameInfo.WIDHT / GameInfo.sizeTexture)*2;
        sizeY = (GameInfo.HEIGHT / GameInfo.sizeTexture)*2;

        //Qual vai ser a próxima posição do tile
        currentX = 0;
        currentY = 0;
        playerY = 0;

        //Lista com os nomes dos biomas
        biomes = new ArrayList<>();
        biomes.add("Grassland");
        biomes.add("AutumnForest");
        biomes.add("Tropics");
        biomes.add("WinterWorld");
        randomBiome();

        tileManager = new TileManager(); //Controla os tiles no texture atlas
        randomTile = new RandomTileManager(tileManager); //Controla qual tile do chão vai ser gerado
        parallaxManager = new ParallaxManager(tileManager); //Efeito de parallax dos backgrounds

        character = new GenericCharacter02(tileManager.getCharacters(), "Holly", world);

        //Criando o mapa
        tiles = new GenericTile[sizeX][sizeY];
        startMap();
    }

    //Inicia o mapa
    public void startMap(){
        // Popula todos os tiles para iniciar o jogo
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                tiles[x][y] = randomTile.newTile(currentX, currentY);
                tiles[x][y].setPosition(currentX, currentY);
                currentY += GameInfo.sizeTexture;
            }
            currentX += GameInfo.sizeTexture;
            currentY = 0;
        }
    }

    public void update(OrthographicCamera camera){
        parallaxManager.update(camera); //Atualiza o efeito parallax
        updateMap(camera); //Atualiza a posição dos tiles na tela
        character.update(camera);
        character.checkTiles(tiles, sizeX, sizeY);
    }

    public void draw(SpriteBatch batch, OrthographicCamera camera){
        parallaxManager.draw(batch);
        drawTiles(batch);
        character.draw(batch);
    }

    //Escolhe qual vai ser o bioma ao iniciar
    private void randomBiome(){
        GameInfo.currentBiome = biomes.get(MathUtils.random(0, 3));
    }

    //Atualiza a posição dos tiles na tela
    private void updateMap(OrthographicCamera camera){
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if(isOutBound(camera, tiles[x][y])){
                    for (int i = 0; i < sizeY; i++) {
                        tiles[x][i].setTexture(randomTile.groundGeneration(currentX, currentY));
                        tiles[x][i].setX(currentX);
                        currentY += GameInfo.sizeTexture;
                    }
                    currentY = 0;
                    currentX += GameInfo.sizeTexture;
                }
                //Verifica se o tile é o mesmo da posição do player e se ele não é null
            }
        }
    }

    //Desenha todos os tiles na tela
    private void drawTiles(SpriteBatch batch){
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                tiles[x][y].draw(batch);
            }
        }
    }

    //Limite máximo dos tiles
    public boolean isOutBound(OrthographicCamera camera, GenericTile tile){
        float cameraX = camera.position.x - (GameInfo.WIDHT * 0.6f);
        if(tile.getX() < cameraX){
            return true;
        }
        return false;
    }

    public void dispose(){
        tileManager.dispose();
    }
}
