package tech.secure.protectron.basic_user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import tech.secure.protectron.R;
import tech.secure.protectron.objects.Police;

public class EmergencyActivity extends AppCompatActivity {

    public JSONObject jsonMaster = null;
    TextView text;


    // http://opendata.newwestcity.ca/downloads/significant-buildings-police/SIGNIFICANT_BLDG_POLICE.json


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        text = (TextView) findViewById(R.id.textView);

        new JsonTask().execute("http://opendata.newwestcity.ca/downloads/significant-buildings-police/SIGNIFICANT_BLDG_POLICE.json");

        //System.out.println(json.toString());
        //System.out.println(json.get("id"));

        final View button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Uri number = Uri.parse("tel:6045255411");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);

            }
        });

        final View button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Uri number = Uri.parse("tel:911");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);

            }
        });


    }

    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            text.setText("Nearst Police Station: " + result.substring(133, 136) + ", " + result.substring(153, 164));
        }
    }

}

