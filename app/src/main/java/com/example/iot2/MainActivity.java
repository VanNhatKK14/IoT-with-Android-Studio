package com.example.iot2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.os.Handler;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    MqttAndroidClient client;
    // Bien edit text
    EditText editforce;
    EditText editcycle;
    EditText editairA;
    EditText editairB;
    // Bien button
    Button btnapply1;
    Button btnapply2;
    Button btnstart;
    Button btnstop;
    RadioButton rdA;
    RadioButton rdB;
    // Bien publish
    String ON = "0";
    String SetFA = "0";
    String SetFB = "0";
    String SetCA = "0";
    String SetCB = "0";
    String airA="0";
    String airB="0";
    String messpublish, messagesub;
    // Bien Sub
    String FA;
    String FB;
    String POSA;
    String POSB;
    //Bien Line chart
    //private ArrayList<Float> ForceA = new ArrayList<>();

    // topic
    String topicsub = "nhatnguyen/sub";
    String topicpub = "nhatnguyen/tr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Xu li Line chart

        //updateChart();


        // Xu ly checked radio button
        rdA = (RadioButton) findViewById(R.id.RB1);
        rdB = (RadioButton) findViewById(R.id.RB2);
        // Xu ly Edittext force + cycle
        editforce = (EditText) findViewById(R.id.EDT1);
        editcycle = (EditText) findViewById(R.id.EDT2);
        //Xu li Edittext Ari
        editairA = (EditText) findViewById(R.id.EDT3);
        editairB = (EditText) findViewById(R.id.EDT4);
        // Xu li button start
        btnstart = (Button) findViewById(R.id.BTSTART);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ON = "1";
                getStringJSON();
            }
        });
        // Xu li button stop
        btnstop = (Button) findViewById(R.id.BTSTOP);
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ON = "0";
                getStringJSON();
            }
        });
        // Xu li button apply1
        btnapply1 = (Button) findViewById(R.id.BTAPPLY1);
        btnapply1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String force = editforce.getText().toString();
                String cycle = editcycle.getText().toString();
                if (rdA.isChecked()) {
                    SetFA = force;
                    SetCA = cycle;
                }
                if (rdB.isChecked()) {
                    SetFB = force;
                    SetCB = cycle;
                }
                getStringJSON();
            }
        });
        btnapply2 = (Button) findViewById(R.id.BTAPPLY2);
        btnapply2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                airA = editairA.getText().toString();
                airB = editairB.getText().toString();
                getStringJSON();
            }
        });
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("nhatnguyenIOT");
        options.setPassword("123456nnn".toCharArray());
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), "tcp://broker.hivemq.com:1883",
                clientId);
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception
            {
                JSONObject getJSON = new JSONObject(String.valueOf(message));
                FA = getJSON.getString("FA");
                //ForceA.add(Float.valueOf(FA));
                //Log.d("nn",ForceA.toString());
                //FB = getJSON.getString("FB");
                //POSA = getJSON.getString("POSA");
                //POSB = getJSON.getString("POSB");


            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Log.d("mqtt", "onSuccess");
                    int qos = 1;
                    try {
                        IMqttToken subToken = client.subscribe(topicsub, qos);
                        subToken.setActionCallback(new IMqttActionListener() {
                            @Override
                            public void onSuccess(IMqttToken asyncActionToken) {
                                // The message was published
                                Log.d("mqtt", "Subscribed");
                            }

                            @Override
                            public void onFailure(IMqttToken asyncActionToken,
                                                  Throwable exception) {
                                // The subscription could not be performed, maybe the user was not
                                // authorized to subscribe on the specified topic e.g. using wildcards

                            }
                        });
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d("mqtt", "onFailure");

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private String getStringJSON() {
        JSONObject publishJSON = new JSONObject();
        try {
            publishJSON.put("ON", ON);
            publishJSON.put("SetFA", SetFA);
            publishJSON.put("SetCA", SetCA);
            publishJSON.put("SetairA", airA);
            publishJSON.put("SetFB", SetFB);
            publishJSON.put("SetCB", SetCB);
            publishJSON.put("SetairB", airB);
            messpublish = publishJSON.toString();
            try {
                client.publish(topicpub, messpublish.getBytes(), 0, false);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return messpublish;
    }

}