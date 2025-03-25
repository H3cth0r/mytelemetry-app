package com.example.mytelemetryapp;

import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor gyroscope;
    private TextView sensorDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorDataTextView = findViewById(R.id.sensorData);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        StringBuilder data = new StringBuilder();
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            data.append("Accelerometer:\nX: ").append(event.values[0])
                .append("\nY: ").append(event.values[1])
                .append("\nZ: ").append(event.values[2]);
        } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            data.append("Gyroscope:\nX: ").append(event.values[0])
                .append("\nY: ").append(event.values[1])
                .append("\nZ: ").append(event.values[2]);
        }
        sensorDataTextView.setText(data.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
