package com.example.wambugugrocery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_SOCKET_TIMEOUT_MS = 9000;
    List<Product> products = new ArrayList<>();
    private Button get_product_btn;
    private RecyclerView recyclerView;
    private MyProductRecyclerViewAdapter myProductRecyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get_product_btn = findViewById(R.id.get_product);
        recyclerView = findViewById(R.id.product_rv);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getProducts();








        get_product_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "getting product", Toast.LENGTH_SHORT).show();
                getProducts();
            }
        });




    }
    void buildRecyclerView(){

    }
    void getProducts(){
        String url = "http://ec2-54-166-100-167.compute-1.amazonaws.com:9090/productapi/products";
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest
                jsonArrayRequest
                = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                        Log.i("response", "onResponse: "+response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            // creating a new json object and
                            // getting each object from our json array.
                            try {
                                // we are getting each json object.
                                JSONObject responseObj = response.getJSONObject(i);
                                Long id = responseObj.getLong("id");
                                String name = responseObj.getString("name");
                                String description = responseObj.getString("description");
                                String imageUrl = responseObj.getString("imageUrl");
                                double price = responseObj.getDouble("price");
                                boolean isAvailable = responseObj.getBoolean("available");
                                products.add(new Product(id, name, isAvailable,description,imageUrl,new BigDecimal(price)));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        myProductRecyclerViewAdapter = new MyProductRecyclerViewAdapter(products,MainActivity.this);
                        recyclerView.setAdapter(myProductRecyclerViewAdapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("error", "onErrorResponse: "+error.toString());
                    }
                });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);
    }



}