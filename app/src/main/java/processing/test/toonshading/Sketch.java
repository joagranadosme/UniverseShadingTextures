package processing.test.toonshading;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import frames.primitives.Frame;
import frames.primitives.Quaternion;
import frames.primitives.Vector;
import frames.processing.Scene;
import processing.core.*;
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Sketch extends PApplet {

    /*
    TODO QUESTIONS:
        1) Why scene isn't working on Android.
    */

    //Context variables.
    private Context context;

    //Sensor variables.
    private Sensor sensor;
    private SensorManager manager;
    private AccelerometerListener listener;

    //Movement variables.
    private float ax, ay, az;
    private int rotation;

    //Tap rotation variable.
    private boolean flag = false;

    //Frames for planets.
    private Frame universe, sun, earth, moon;

    //Shaders variables
    PImage sunImage, earthImage, moonImage;
    PShape sunShape, earthShape, moonShape;
    PShader light;
    int r;

    public void settings() {
        fullScreen(P3D);
    }

    public void setup() {

        orientation(LANDSCAPE);

        background(0);
        noStroke();

        context = getActivity(); //Get context

        //Initialize sensor
        manager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        listener = new AccelerometerListener();
        manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME);
        textFont(createFont("SansSerif", 10 * displayDensity));

        //Initialize shader
        light = loadShader("texlightfrag.glsl", "texlightvert.glsl");

        //Initialize universe
        universe = new Frame();
        universe.translate(width/2, height/2);

        //Initialize sun
        sun = new Frame(universe, new Vector(0,0), new Quaternion());
        sunImage = loadImage( "sun.jpg");
        sunShape = createShape(SPHERE, 100);
        sunShape.setTexture(sunImage);

        //Initialize earth
        earth = new Frame(sun, new Vector(400, 0), new Quaternion());
        earthImage = loadImage( "world.jpg");
        earthShape = createShape(SPHERE, 50);
        earthShape.setTexture(earthImage);

        //Initialize moon
        moon = new Frame(earth, new Vector(100, 0), new Quaternion());
        moonImage = loadImage( "moon.jpg");
        moonShape = createShape(SPHERE, 10);
        moonShape.setTexture(moonImage);



    }

    public void draw() {

        //Set Background
        background(0);

        //Call shader
        shader(light);
        //Point light
        pointLight(255, 255, 255, width/2, height/2, 0);
        //lightFalloff(width/2, height/2, 0.00001F);
        //ambientLight(255, 255, 255, width/2, height/2, 0);

        //Display sensor values
        text("X: " + ax + "\nY: " + ay + "\nZ: " + az, 0, 0, width, height);

        //Update all frames
        updateFrames();

        noStroke();

        //Draw all frames
        push();
        Scene.applyTransformation(this.g, universe);
        push();
        Scene.applyTransformation(this.g, sun);
        pointLight(255, 255, 255, 0, 0, 0);
        push();
        Scene.applyTransformation(this.g, earth);
        shape(earthShape);
        push();
        Scene.applyTransformation(this.g, moon);
        shape(moonShape);
        pop();
        pop();
        resetShader();
        ambientLight(255, 255, 255);
        shape(sunShape);
        pop();
        pop();

    }

    public void push() {
        pushStyle();
        pushMatrix();
    }

    public void pop() {
        popStyle();
        popMatrix();
    }

    public void planet(float r) {
        sphere(r);
    }

    public void updateFrames() {

        //if(flag)
            rotation++;

        universe.setRotation(new Quaternion(new Vector(1.0F, 0.0F, 0.0F), radians(-20F * ax)));
        sun.setRotation(new Quaternion(new Vector(0.0F, 1.0F, 0.0F), radians(0.5F * rotation)));
        earth.setRotation(new Quaternion(new Vector(0.0F, 1.0F, 0.0F), radians(2*rotation)));
        moon.setRotation(new Quaternion(new Vector(0.0F, 1.0F, 0.0F), radians(4*rotation)));

    }


    //Class for receive the events from Accelerometer
    class AccelerometerListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            //ax =  ((float)((int)(sensorEvent.values[0]*10)))/10;
            ax = sensorEvent.values[0];
            ay = sensorEvent.values[1];
            az = sensorEvent.values[2];
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }

    }

}
