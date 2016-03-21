package edu.ub.pis2016.pis13.pruebadecanvas;

import android.graphics.drawable.Drawable;

/**
 * Created by Marcos on 13/03/2016.
 */
public class Collidable {

    private int x;
    private int y;
    private int width;
    private int height;


    public Collidable(int x,int y,int w,int h){

        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;

    }

    public void movex(int x){
        this.x = x;
    }

    public void movey(int y){
        this.y = y;
    }

    public int getx(){
        return this.x;
    }

    public int gety(){
        return this.y;
    }

    public int getWidth() { return this.width; }

    public int getHeight() { return this.height; }

    public boolean checkCollition(Collidable other){
        //if (this.x<other.getx() && this.y<other.gety() && this.x+this.width>other.getx() )
        return true;
    }
    public void setHeight(int h){
        this.height = h;
    }
    public void setWidth(int w){
        this.width = w;
    }
}
