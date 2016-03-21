package edu.ub.pis2016.pis13.pruebadecanvas;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by Marcos on 13/03/2016.
 */
public class Player extends Collidable {

    private Drawable picture;

    public Player(int x, int y,int size) {
        super(x, y, size, size);
    }

    public void setPicture(Drawable d){
        picture = d;
    }

    public void draw(Canvas canvas){
        picture.setBounds(this.getx(),this.gety(),this.getx()+this.getWidth(),this.gety()+this.getHeight());
        picture.draw(canvas);
    }

    public void setPos(int x,int y){
        this.movex(x);
        this.movey(y);
    }



}
