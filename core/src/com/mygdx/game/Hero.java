package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private Texture texture;
    private Vector2 position;
    public float speed;
    private int fireCounter;

// Sound fireSound;

    Hero(){
        texture = new Texture("ship64.png");
        position = new Vector2(100, 328);
        speed = 7.0f;
        fireCounter = 0;
       // fireSound = Gdx.audio.newSound(Gdx.files.internal("fire.wav"));
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, position.x, position.y);
    }

    public void update(){

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            fireCounter++;
            if(fireCounter >= 8){
                fireCounter = 0;
                fire();
            }
        }

        //работа с клавиатурой. нажатие клавиш W, A, S, D
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            if(position.y >= 720){
                position.y = -64;
            }else{
                position.y += speed;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            if(position.y <= -64){
                position.y = 720;
            }else{
                position.y -= speed;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            if(position.x <= 0) {
                position.x = 0;
            }else{
                position.x -= speed;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            if(position.x >= 1216) {
                position.x = 1216;
            }else{
                position.x += speed;
            }
        }

        //работа с тачскрином. пальцем (мышкой) тыкаем левее или правее кораблика
        if (Gdx.input.isTouched()){
            if(position.x >= Gdx.input.getX()){
                position.x -= speed;
            }
            if(position.x <= Gdx.input.getX()){
                position.x += speed;
            }
            if(position.y >= Gdx.graphics.getHeight() - Gdx.input.getY()){
                position.y -= speed;
            }
            if(position.y <= Gdx.graphics.getHeight() - Gdx.input.getY()){
                position.y += speed;
            }
        }

    }

    private void fire(){
        for (int i = 0; i < MyGdxGame.bullets.length; i++) {
            if(!MyGdxGame.bullets[i].active){
                MyGdxGame.bullets[i].activate(position.x + 48, position.y + 32);
                //fireSound.play();
                break;
            }
        }
    }

}
