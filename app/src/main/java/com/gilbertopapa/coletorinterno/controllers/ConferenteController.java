package com.gilbertopapa.coletorinterno.controllers;

import android.util.Log;

import com.gilbertopapa.coletorinterno.api.ColetorAPIConferente;
import com.gilbertopapa.coletorinterno.model.Conferente;
import com.gilbertopapa.coletorinterno.util.ConferenteDeserializer;
import com.gilbertopapa.coletorinterno.util.Configuration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by Gilbertopapa on 03/08/2017.
 */

public class ConferenteController {

    private static final String TAG = "";

    private Type listType = new TypeToken<ArrayList<Conferente>>() {
    }.getType();

    private Gson gson = new GsonBuilder().registerTypeAdapter(listType, new ConferenteDeserializer()).create();
    private Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl(new Configuration().getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    private ColetorAPIConferente coletorAPIConferente = retrofit.create(ColetorAPIConferente.class);

    private List<Conferente> conferentes = null;

    public void getConferente(String title) {

        Call<List<Conferente>> call = coletorAPIConferente.getConferente("application/json", title);

        call.enqueue(new Callback<List<Conferente>>() {

            @Override
            public void onResponse(Call<List<Conferente>> call, Response<List<Conferente>> response) {

                conferentes = response.body();

            }

            @Override
            public void onFailure(Call<List<Conferente>> call, Throwable t) {
                Log.i(TAG, "");
            }
        });

    }

    public List<Conferente> getConferentes() {

        return this.conferentes;
    }

}
