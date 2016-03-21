package edu.ub.pis2016.pis13.pruebadecanvas;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    double x;
    double y;
    Player p;
    EjemploView mainview;
    int vel = 3;
    double t=0;
    int cubex = 0;
    int cubey = 0;
    int viewHeight;
    int viewWidth;
    int state = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final EjemploView mainview = new EjemploView(this);
        setContentView(mainview);

        p=new Player(300,300,40);
        p.setPicture(getResources().getDrawable(R.drawable.red_player));

        Canvas c = new Canvas();

        mainview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = event.getX();
                y = event.getY();
                v.invalidate();
                cubex = (int) x;
                cubey = (int) y;
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    if(viewHeight/2<y){
                        if(viewWidth/2<x){
                            state = 1;
                        }else{
                            state = 2;
                        }
                    }else{
                        if((viewWidth)/3<x && x<=(2*viewWidth)/3){
                            state = 3;
                        }else if((2*viewWidth)/3<x){
                            state = 4;
                        }else if(0<x && x<=(viewWidth/3)){
                            state = 5;
                        }
                    }
                    return true;
                }else if(event.getAction() == MotionEvent.ACTION_UP) {
                    state = 0;
                    return true;
                }
                return false;
            }
        });
        new CountDownTimer((long) Double.POSITIVE_INFINITY,32){
            public void onTick(long millisUntilFinished) {
                mainview.invalidate();
                t+=32;
                //p.setPos(p.getx(),p.gety()+10);
                switch(state){
                    case 1:
                        //Move right
                        p.setPos(p.getx()+vel,p.gety());
                        break;
                    case 2:
                        p.setPos(p.getx()-vel,p.gety());
                        break;
                    case 3:
                        p.setPos(p.getx(),p.gety()-vel);
                        break;
                    case 4:
                        p.setPos(p.getx()+vel,p.gety()-vel);
                        break;
                    case 5:
                        p.setPos(p.getx()-vel,p.gety()-vel);
                        break;
                    default:
                        break;
                }
            }

            public void onFinish() {
                mainview.invalidate();
            }

        }.start();
        }

        class EjemploView extends View {

            public EjemploView (Context context) {
                super(context);
            }
            @Override
            protected void onDraw(Canvas canvas) {
                //Dibujar aquí
                int n=20;
                int [][] matrix = {//26x15
                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
                };

                Drawable gblock1 = getResources().getDrawable(R.drawable.ground_block1);
                Drawable gblock2 = getResources().getDrawable(R.drawable.ground_block2);
                Drawable d1 = getResources().getDrawable(R.drawable.foxy);
                int w = canvas.getWidth();
                int h = canvas.getHeight();
                viewHeight = h;
                viewWidth = w;
                int wblock = h/15;
                d1.setBounds(0, 0, w, h);
                d1.draw(canvas);
                p.setHeight(wblock);
                p.setWidth(wblock);
                if(matrix[((p.gety()+5)/wblock)+1][p.getx()/wblock] == 0 && matrix[((p.gety()+5)/wblock)+1][(p.getx()/wblock)+1] == 0 ) {
                    p.setPos(p.getx(), p.gety() + 5);
                }else{
                    p.setPos(p.getx(),((p.gety()+5)/wblock)*wblock);
                }
                for(int i=0;i<matrix.length;i++){
                    for(int j=0;j<matrix[i].length;j++) {
                        /*
                        1 -- Bloque marrón
                        2 -- Bloque gris
                        3 --
                         */
                        switch(matrix[i][j]){
                            case 1:
                                gblock1.setBounds(j * wblock,i * wblock,(1 + j) * wblock,(1 + i) * wblock);
                                gblock1.draw(canvas);
                                break;
                            case 2:
                                gblock2.setBounds(j * wblock,i * wblock,(1 + j) * wblock,(1 + i) * wblock);
                                gblock2.draw(canvas);
                                break;
                            default:
                                break;
                        }
                    }
                }
                canvas.drawText(Double.toString(viewHeight),50,70,new Paint(Color.BLACK));
                canvas.drawText(Double.toString(viewWidth),100,70,new Paint(Color.BLACK));
                canvas.drawText(Double.toString(x),300,70,new Paint(Color.BLACK));
                canvas.drawText(Double.toString(y),350,70,new Paint(Color.BLACK));
                canvas.drawText(Double.toString(state),150,70,new Paint(Color.BLACK));
                //gblock2.setBounds(cubex,cubey,cubex+wblock,cubey+wblock);
                gblock2.setBounds((int) (100*Math.cos(((double)t)/1000.0))+250,(int) (100*Math.sin(((double)t)/1000.0))+300,
                        (int) (100*Math.cos(((double)t)/1000.0)+wblock)+250,(int) (100*Math.sin(((double)t)/1000.0)+wblock)+300);
                /*

                */
                gblock2.draw(canvas);
                p.draw(canvas);
            }
        }
    }
