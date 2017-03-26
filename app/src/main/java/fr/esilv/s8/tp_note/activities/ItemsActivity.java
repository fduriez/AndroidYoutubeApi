package fr.esilv.s8.tp_note.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import fr.esilv.s8.tp_note.R;
import fr.esilv.s8.tp_note.adapters.ItemsAdapter;
import fr.esilv.s8.tp_note.interfaces.OnItemSelectedListener;

import fr.esilv.s8.tp_note.Constants;
import fr.esilv.s8.tp_note.models.Item;
import fr.esilv.s8.tp_note.models.Items;
import fr.esilv.s8.tp_note.models.ParseData;

public class ItemsActivity extends AppCompatActivity implements OnItemSelectedListener {


    private static  final String NBRESULT = "10";
    private static final String YOUTUBE_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=" + NBRESULT + "&type=video&q=";
    private String research;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        research = getIntent().getStringExtra("RESEARCH");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getContracts();
    }

    private void getContracts() {
        StringRequest contractsRequest = new StringRequest(YOUTUBE_URL + research + "&key=" + Constants.API_KEY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            ParseData parseData = new Gson().fromJson(response, ParseData.class);
            setAdapter(parseData.getItems());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Contracts", "Error");
            }
        });

        Volley.newRequestQueue(this).add(contractsRequest);
    }

    private void setAdapter(Items items) {
        ItemsAdapter adapter = new ItemsAdapter(items);
        adapter.setOnItemSelectedListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(Item item) {
        DetailActivity.start(this, item);
    }
}
