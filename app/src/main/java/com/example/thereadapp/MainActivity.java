package com.example.thereadapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String baseurl = "https://uselessfacts.jsph.pl/";

    public TextView view;
    public Button btn;
    private final ExecutorService executorService;

    public MainActivity() {
       executorService = Executors.newSingleThreadExecutor();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        view = (TextView) findViewById(R.id.textview);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             getAPIData();
            }
        });
    }
    private void getAPIData() {
        executorService.execute(() -> {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiService retrofitService = retrofit.create(ApiService.class);
            Call<ResponseObject> apiData = retrofitService.getRandomFact();
            try {
                Response<ResponseObject> response = apiData.execute();
                if (response.isSuccessful()) {
                    ResponseObject data = response.body();
                    if (data!=null) {
                       runOnUiThread(()-> view.setText(data.getText()));
                    }
                }else {
                    System.out.println("Fail::"+response.errorBody().string());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public void shutdown(){
        executorService.shutdown();
    }
}