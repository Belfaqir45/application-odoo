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



public class MainActivity extends Activity {
    JSONArray jArray;
    JSONObject json_data;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jArray = null;
        EditText rech = findViewById(R.id.rech);

        findViewById(R.id.button).setOnClickListener(view -> {
            try {
                String adresse = "http://192.168.1.125:81/php/test.php?rech=" + rech.getText().toString();
                String wololo = new HTTPRequest.HTTPSELECTRequest().execute(adresse).get();
                jArray = new JSONArray(wololo);
                i = 0;
                json_data = jArray.getJSONObject(i);
                ((TextView) findViewById(R.id.name)).setText(json_data.getString("name"));
                ((TextView) findViewById(R.id.street)).setText(json_data.getString("street"));
                ((TextView) findViewById(R.id.mail)).setText(json_data.getString("email"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        findViewById(R.id.button2).setOnClickListener(view -> {
            try {
                if (i > 0) {
                    i--;
                    json_data = jArray.getJSONObject(i);
                    ((TextView) findViewById(R.id.name)).setText(json_data.getString("name"));
                    ((TextView) findViewById(R.id.street)).setText(json_data.getString("street"));
                    ((TextView) findViewById(R.id.mail)).setText(json_data.getString("email"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        findViewById(R.id.button3).setOnClickListener(view -> {
            try {
                if (i < jArray.length() - 1) {
                    i++;
                    json_data = jArray.getJSONObject(i);
                    ((TextView) findViewById(R.id.name)).setText(json_data.getString("name"));
                    ((TextView) findViewById(R.id.street)).setText(json_data.getString("street"));
                    ((TextView) findViewById(R.id.mail)).setText(json_data.getString("email"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button buttonRedirect = findViewById(R.id.button_redirect);
        buttonRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }
}
