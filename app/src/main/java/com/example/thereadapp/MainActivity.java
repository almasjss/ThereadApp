package com.example.thereadapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String baseurl = "https://uselessfacts.jsph.pl/";

    public TextView view;
    public Button btn;

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
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService retrofitService = retrofit.create(ApiService.class);
        Call<ResponseObject> apiData = retrofitService.getRandomFact();
        apiData.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (response.code() == 200) {
//                    List<ResponseObject> apiData = response.body();
//                    for (int i = 0; i < apiData.size(); i++) {
//                        if (apiData.get(i).getId() != null) {
//                            Log.d("TAG", "Id: " + apiData.get(i).getId());
//                        }
//                    }
                    ResponseObject data = response.body();
                    Log.d("TAG", "Id: " + data.getText());
                    view.setText(data.getText());
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
// getting error message
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

    }
}