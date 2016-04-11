package com.example.dkovalev.photofetcher;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "FLICKR_TAG";

    private CustomArrayList<FlickrPhoto.Photo> flickrPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final CustomArrayList<? super String> genericList = new CustomArrayList<String>(10);

        genericList.add("Hello");
        genericList.add("World");

        String toShow = genericList.get(1).toString();

        Toast.makeText(getApplicationContext(), toShow, Toast.LENGTH_LONG).show();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new FetchData().execute();
            }
        });
    }

   private class FetchData extends AsyncTask<Void, Void, String> {

        private HttpsURLConnection httpsURLConnection = null;
        private StringBuilder resultJSON;

        @Override
        protected String doInBackground(Void... params) {

            try {

                Uri.Builder builder = new Uri.Builder();
                builder.scheme("https")
                        .authority(getApplication().getString(R.string.flickr_api_url))
                        .appendPath("services")
                        .appendPath("rest")
                        .appendQueryParameter("method", "flickr.people.getPhotos")
                        .appendQueryParameter("api_key", getApplication().getString(R.string.flickr_api_key))
                        .appendQueryParameter("user_id", "14291558@N00") //TODO Fix this stupendous hardcode.
                        .appendQueryParameter("format", "json");

                String flickrAPIUrl = builder.build().toString();

                Log.i(TAG, flickrAPIUrl);

                URL url = new URL(flickrAPIUrl);

                httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("GET");
                //httpsURLConnection.setReadTimeout(10000);
                httpsURLConnection.connect();
                //InputStream inputStream = httpsURLConnection.getInputStream();
                InputStream in = new BufferedInputStream(httpsURLConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    resultJSON.append(line);
                }

            }catch( Exception e) {
                e.printStackTrace();
            }
            finally {
                httpsURLConnection.disconnect();
            }

            return resultJSON.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }
}
