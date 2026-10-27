package com.example.motiontracker;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private float[] accelerationValues = new float[3]; // X, Y, Z
    private TextView textViewX;
    private TextView textViewY;
    private TextView textViewZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewX = findViewById(R.id.textViewX);
        textViewY = findViewById(R.id.textViewY);
        textViewZ = findViewById(R.id.textViewZ);


        // Initialize SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Get the accelerometer sensor
        if (sensorManager != null) {
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (accelerometerSensor == null) {
                Log.e("MainActivity", "Accelerometer not available");
                return;
            }
        }

        // Register listener to get sensor data
        if (accelerometerSensor != null && sensorManager != null) {
            sensorManager.registerListener(
                    this,
                    accelerometerSensor,
                    SensorManager.SENSOR_DELAY_NORMAL
            );
        } else {
            Log.e("MainActivity", "Failed to register sensor listener");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accelerometerSensor != null && sensorManager != null) {
            sensorManager.registerListener(
                    this,
                    accelerometerSensor,
                    SensorManager.SENSOR_DELAY_NORMAL
            );
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerationValues[0] = event.values[0];  // X-axis
            accelerationValues[1] = event.values[1];  // Y-axis
            accelerationValues[2] = event.values[2];  // Z-axis

            // Print to console
            Log.d("MainActivity", "Acceleration: X=" + accelerationValues[0] +
                    ", Y=" + accelerationValues[1] + ", Z=" + accelerationValues[2]);

            // Show on Screen
            textViewX.setText("X: " + String.format("%.2f", accelerationValues[0]));
            textViewY.setText("Y: " + String.format("%.2f", accelerationValues[1]));
            textViewZ.setText("Z: " + String.format("%.2f", accelerationValues[2]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
