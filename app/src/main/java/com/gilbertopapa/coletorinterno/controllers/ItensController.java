package com.gilbertopapa.coletorinterno.controllers;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.gilbertopapa.coletorinterno.R;
import com.gilbertopapa.coletorinterno.api.ColetorAPIItens;
import com.gilbertopapa.coletorinterno.model.Item;
import com.gilbertopapa.coletorinterno.util.Configuration;
import com.gilbertopapa.coletorinterno.util.ItemDeserializer;
import com.gilbertopapa.coletorinterno.util.ItensDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gilbertopapa on 15/11/2017.
 */

public class ItensController {
    private static final String TAG = ".";

    private Type listType = new TypeToken<ArrayList<Item>>() {
    }.getType();

    private Retrofit retrofit = null;
    private ColetorAPIItens coletorAPIItens = null;

    private List<Item> itens = null;
    private Item item = null;

    public void getItens(String order_number, final View v) {
        Gson gson = new GsonBuilder().registerTypeAdapter(listType, new ItensDeserializer()).create();
        retrofit = new Retrofit
                .Builder()
                .baseUrl(new Configuration().getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        coletorAPIItens = retrofit.create(ColetorAPIItens.class);

        Call<List<Item>> call = coletorAPIItens.getItens("application/json", order_number);
        call.enqueue(new Callback<List<Item>>() {

            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                itens = response.body();

                if(itens!=null){
                    List<HashMap<String,String>> itens = new ArrayList<HashMap<String,String>>();
                    for (int i = 0; i < ItensController.this.itens.size(); i++) {
                        HashMap<String, String> item = new HashMap<String,String>();
                        item.put("description", ItensController.this.itens.get(i).getName());
                        item.put("order_number", "Quantidade: ".concat(ItensController.this.itens.get(i).getWeight()));
                        item.put("client", "");
                        itens.add(item);
                    }

                    String[] from = { "description","order_number","client" };
                    int[] to = { R.id.description,R.id.order_number,R.id.client};

                    final ListView listViewItens = (ListView) v.findViewById(R.id.ListItens);
                    SimpleAdapter adapter = new SimpleAdapter(v.getContext(), itens, R.layout.list_orders, from, to);
                    listViewItens.setAdapter(adapter);
                }else{
                    Toast.makeText(v.getContext(), "Não há itens.", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.i(TAG, "");
            }
        });
    }

    public void setItem(String name, String weight, String order_number, final View v){
        System.out.println("Ordem: "+order_number);

        Gson gson = new GsonBuilder().registerTypeAdapter(Item.class, new ItemDeserializer()).create();
        retrofit = new Retrofit
                .Builder()
                .baseUrl(new Configuration().getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        coletorAPIItens = retrofit.create(ColetorAPIItens.class);

        Call<Item> call = coletorAPIItens.setItem(name, weight, order_number);
        call.enqueue(new Callback<Item>() {

            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                item = response.body();
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                System.out.println("Error: "+t.getMessage());

            }
        });
    }

    public List<Item> getItens() {
        return this.itens;
    }
    public Item getItem() { return this.item; }

}
