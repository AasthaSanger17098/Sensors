package com.gla.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textViewSensorInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewSensorInfo = findViewById(R.id.sensor_info);
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //String sensorName = "Name:";
        String sensorName = getString(R.string.sensor_name);
        //NAme: Generic Accelerometer Sensor
        sensorName = sensorName.concat(" "+ sensor.getName());

        textViewSensorInfo.setText(sensorName);

        String sensorVendor = getString(R.string.sensor_vendor);
        sensorVendor = sensorVendor.concat(" "+ sensor.getVendor());
        textViewSensorInfo.append("\n" + sensorVendor);

        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try{
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            textViewSensorInfo.setText(event.timestamp+ ": (" + "," + y + "," + z + ")");
        }
        catch(Exception e){
            textViewSensorInfo.setText(e.getMessage());
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
