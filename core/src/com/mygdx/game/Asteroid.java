package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Asteroid {
    private Texture texture;
    private Vector2 position; //position.x  position.y
    private float speed;
    private Rectangle hitBox;
    private float rotation;
    private Vector2 direction;
    private Random random = new Random();
    private float size = MathUtils.random(0.4f, 1.5f);


    Asteroid() {
        float [] point = getPoint();
        texture = new Texture("asteroid64.png");
        position = new Vector2(point[0], point[1]);
        speed = MathUtils.random(2.5f, 5.0f);
        hitBox = new Rectangle(position.x, position.y, 64*size, 64*size);
        direction = new Vector2(MathUtils.random(-3.5f, 3.5f), MathUtils.random(-3.5f, 3.5f));

        //hitBox.overlaps() внутри указываем другой хитбокс. если стоклновение произошло значит что-то....
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, position.x, position.y, 32, 32, 64, 64, 1*size, 1*size, rotation, 0, 0, 64, 64, false,false);
    }

    public void recreate(){
        float [] point = getPoint();
        position.set(point[0], point[1]);
        speed = MathUtils.random(2.5f, 5.0f);
        direction.set(MathUtils.random(-3.5f, 3.5f), MathUtils.random(-3.5f, 3.5f));
        rotation = 0;
    }

    public void update() {
        rotation += speed / 4;
        position.x += direction.x;
        position.y += direction.y;
        if (position.y > 784) position.y = -64;
        if (position.y < -64) position.y = 784;
        if (position.x > 1344) position.x = -64;
        if (position.x < -64) position.x = 1344;

//        if (position.x < -100 || position.x >= 1360 || position.y < -100 || position.y >= 820) {
//            recreate();
//        }
        hitBox.setPosition(position);
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    private float[] getPoint(){
        float [] point = new float[2];
        switch (MathUtils.random(1,3)){
            case 1:
                point[0] = MathUtils.random(200, 1480);
                point[1] = MathUtils.random(720, 920);
                break;
            case 2:
                point[0] = MathUtils.random(1280, 1480);
                point[1] = MathUtils.random(-200, 720);
                break;
            case 3:
                point[0] = MathUtils.random(200, 1280);
                point[1] = MathUtils.random(-200, -64);
                break;
        }
        return point;
    }
}
