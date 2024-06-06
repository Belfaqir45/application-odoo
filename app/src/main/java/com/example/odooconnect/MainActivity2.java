package com.example.odooconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import android.widget.EditText;

public class MainActivity2 extends Activity {
    JSONArray jArray;
    JSONObject json_data;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        jArray = null;
        EditText rech2 = findViewById(R.id.rech2);

        findViewById(R.id.button4).setOnClickListener(view -> {
            try {
                String adresse = "http://192.168.43.62:81/php/connection.php?rech2=" + rech2.getText().toString();
                String wololo = new HTTPRequest.HTTPSELECTRequest().execute(adresse).get();
                jArray = new JSONArray(wololo);
                i = 0;
                json_data = jArray.getJSONObject(i);
                ((TextView) findViewById(R.id.active)).setText(json_data.getString("active"));
                ((TextView) findViewById(R.id.login)).setText(json_data.getString("login"));
                ((TextView) findViewById(R.id.notif)).setText(json_data.getString("notification_type"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        findViewById(R.id.button5).setOnClickListener(view -> {
            try {
                if (i > 0) {
                    i--;
                    json_data = jArray.getJSONObject(i);
                    ((TextView) findViewById(R.id.active)).setText(json_data.getString("active"));
                    ((TextView) findViewById(R.id.login)).setText(json_data.getString("login"));
                    ((TextView) findViewById(R.id.notif)).setText(json_data.getString("notification_type"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        findViewById(R.id.button6).setOnClickListener(view -> {
            try {
                if (i < jArray.length() - 1) {
                    i++;
                    json_data = jArray.getJSONObject(i);
                    ((TextView) findViewById(R.id.active)).setText(json_data.getString("active"));
                    ((TextView) findViewById(R.id.login)).setText(json_data.getString("login")); // Correction ici
                    ((TextView) findViewById(R.id.notif)).setText(json_data.getString("notification_type"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Button buttonRedirect = findViewById(R.id.button_redirect2);
        buttonRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }
}
