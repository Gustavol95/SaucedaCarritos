package com.sociostec.saucedacarritos;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView texto;
    ArrayList<Carro> carros;
    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carros=new ArrayList<>();
        pager=(ViewPager)findViewById(R.id.viewPager);
        button=(Button)findViewById(R.id.boton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="https://screwyouguys.herokuapp.com/cars.json";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray arreglo=new JSONArray(response);
                                    for(int i=0;i<arreglo.length();i++){
                                        JSONObject temp=arreglo.getJSONObject(i);
                                        Carro carroTemp=new Carro(temp.getString("brand"),
                                                temp.getString("model"),
                                                temp.getString("year"),
                                                temp.getString("color"));
                                        carros.add(carroTemp);
                                    }

                                    AdapterPager adapter=new AdapterPager(getApplicationContext(),carros);
                                    pager.setAdapter(adapter);

                                } catch (JSONException e) {

                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
// Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });
    }
}
