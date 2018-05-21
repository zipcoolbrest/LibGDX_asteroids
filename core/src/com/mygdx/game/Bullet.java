package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    Vector2 position; //position.x  position.y
    float speed;
    boolean active;
    //Texture texture;

    public Bullet(){
        position = new Vector2(0,0);
        speed = 14;
        active = false;
    }

    public void deactivate(){
        active = false;
    }

    public void activate(float x, float y){
        position.set(x, y);
        active = true;
    }

    public void update(){
        position.x += speed;
        if (position.x > 1280){
            deactivate();
        }
    }
}
