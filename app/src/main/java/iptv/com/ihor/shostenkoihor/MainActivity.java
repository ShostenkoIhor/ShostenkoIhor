package iptv.com.ihor.shostenkoihor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExchangeRateAdapter adapter;
    private List<ExchangeRate> exchangeRates;
    private Retrofit retrofit;
    private Api api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        exchangeRates = new ArrayList<>();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.cashex.com.ua/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        adapter = new ExchangeRateAdapter(exchangeRates, MainActivity.this);
        recyclerView.setAdapter(adapter);
        api.getExchangeRate().enqueue(new Callback<List<ExchangeRate>>() {
            @Override
            public void onResponse(Call<List<ExchangeRate>> call, Response<List<ExchangeRate>> response) {
                if (!(response == null)) {
                    exchangeRates.addAll(response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<List<ExchangeRate>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Помилка", Toast.LENGTH_SHORT).show();

            }
        });

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(MainActivity.this, "Позиція = " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
