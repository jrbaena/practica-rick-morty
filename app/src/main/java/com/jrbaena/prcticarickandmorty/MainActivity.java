package com.jrbaena.prcticarickandmorty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

  public final String BASE_URL = "https://rickandmortyapi.com/api/";

  TextView txtString;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RickAndMortyApiService test = new RickAndMortyApiService();
    test.execute(BASE_URL);

    txtString = findViewById(R.id.txtString);


  }

  public class RickAndMortyApiService extends AsyncTask {

    // avoid creating several instances, should be singleton
    OkHttpClient client = new OkHttpClient();

    @Override
    protected Object doInBackground(Object[] objects) {
      Request.Builder builder = new Request.Builder();
      builder.url((String) objects[0]);
      Request request = builder.build();

      try {
        Response response = client.newCall(request).execute();
        return response.body().string();
      } catch (Exception e) {
        e.printStackTrace();
      }

      return null;
    }

    @Override
    protected void onPostExecute(Object o) {
      super.onPostExecute(o.toString());
      txtString.setText(o.toString());
    }
  }
}