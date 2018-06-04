package processing.test.toonshading;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import frames.primitives.Frame;
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
    PShader toon;
    boolean shaderEnabled = false;

    public void settings() {
        fullScreen(P3D);
    }

    public void setup() {

        orientation(LANDSCAPE);

        noStroke();
        fill(204);
        toon = loadShader("ToonFrag.glsl", "ToonVert.glsl");

    }

    public void draw() {

        if (shaderEnabled) {
            shader(toon);
        }

        noStroke();
        background(0);
        float dirY = (mouseY / PApplet.parseFloat(height) - 0.5f) * 2;
        float dirX = (mouseX / PApplet.parseFloat(width) - 0.5f) * 2;
        directionalLight(204, 204, 204, -dirX, -dirY, -1);
        translate(width/2, height/2);
        sphere(120);

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
