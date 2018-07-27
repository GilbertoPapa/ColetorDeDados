package com.gilbertopapa.coletorinterno.controllers;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.gilbertopapa.coletorinterno.ConferenciaCargaActivity;
import com.gilbertopapa.coletorinterno.ConferenciaDescargaActivity;
import com.gilbertopapa.coletorinterno.R;
import com.gilbertopapa.coletorinterno.api.ColetorAPIWorkOrder;
import com.gilbertopapa.coletorinterno.model.WorkOrder;
import com.gilbertopapa.coletorinterno.util.Configuration;
import com.gilbertopapa.coletorinterno.util.WorkOrderDeserializer;
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
 * Created by Gilbertopapa on 16/09/2017.
 */

public class WorkOrdemController {

    private static final String TAG = ".";
    private int Testefirstname;


    private Type listType = new TypeToken<ArrayList<WorkOrder>>() {
    }.getType();

    private Gson gson = new GsonBuilder().registerTypeAdapter(listType, new WorkOrderDeserializer()).create();
    private Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl(new Configuration().getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    private ColetorAPIWorkOrder coletorAPIWorkOrder = retrofit.create(ColetorAPIWorkOrder.class);


    private List<WorkOrder> workOrders = null;

    public void Getworkordem(String order, final View v,
                             final String firstName, final String idconf,
                             final String order_number,final String plate) {

        Call<List<WorkOrder>> call = coletorAPIWorkOrder.getWorkOrder("application/json", order);

        call.enqueue(new Callback<List<WorkOrder>>() {

            @Override
            public void onResponse(Call<List<WorkOrder>> call, Response<List<WorkOrder>> response) {
                workOrders = response.body();

                if(v.findViewById(R.id.listViewPessoas)!=null){
                    List<HashMap<String,String>> orders = new ArrayList<HashMap<String,String>>();
                    for (int i = 0; i < workOrders.size(); i++) {
                        HashMap<String, String> order = new HashMap<String,String>();
                        order.put("description", workOrders.get(i).getDescription());
                        if(workOrders.get(i).getType_of_order().contains("1")){
                            order.put("order_number", "Número da Ordem: ".concat(workOrders.get(i)
                                    .getId().toString()).concat(" - Carga"));
                            order.put("backdrops_url","Placa: ".concat(workOrders.get(i)
                                    .getVehicle_plate()));
                        }else{
                            order.put("order_number", "Número da Ordem: "
                                    .concat(workOrders.get(i).getId().toString())
                                    .concat(" - Descarga"));
                            order.put("plate","Placa: ".concat(workOrders.get(i).getVehicle_plate()));
                        }
                        order.put("client", "Cliente: ".concat(workOrders.get(i).getClient()));
                        orders.add(order);
                    }
                    String[] from = { "description","order_number","plate","client" };
                    int[] to = { R.id.description,R.id.order_number,R.id.plate,R.id.client};

                    final ListView listViewOrdens = (ListView) v.findViewById(R.id.listViewPessoas);
                    SimpleAdapter adapter = new SimpleAdapter(v.getContext(), orders, R.layout.list_orders, from, to);
                    listViewOrdens.setAdapter(adapter);

                    listViewOrdens.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {

                            listViewOrdens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {

                                    TextView order_number_list = (TextView) view.findViewById(R.id.order_number);
                                    TextView plate_list = (TextView) view.findViewById(R.id.plate);

                                    if(order_number_list.getText().toString().contains("Carga")){
                                        Intent _conferenciaCarga = new Intent(v.getContext(), ConferenciaCargaActivity.class);
                                        _conferenciaCarga.putExtra("firstNameIntent", firstName);
                                        _conferenciaCarga.putExtra("id",idconf+"");
                                        _conferenciaCarga.putExtra("order_number",order_number_list.getText()+"");
                                        _conferenciaCarga.putExtra("plate",plate_list.getText()+"");
                                        v.getContext().startActivity(_conferenciaCarga);
                                    }else{
                                        Intent _conferenciaDescarga = new Intent(v.getContext(), ConferenciaDescargaActivity.class);
                                        _conferenciaDescarga.putExtra("firstNameIntent", firstName);
                                        _conferenciaDescarga.putExtra("id",idconf+"");
                                        _conferenciaDescarga.putExtra("order_number",order_number_list.getText()+"");
                                        _conferenciaDescarga.putExtra("plate",plate_list.getText()+"");
                                        v.getContext().startActivity(_conferenciaDescarga);
                                    }

                                }
                            });


                        }

                    });
                }else{

                }




            }
            @Override
            public void onFailure(Call<List<WorkOrder>> call, Throwable t) {
                Log.i(TAG, "");
            }
        });
    }

    public List<WorkOrder> Getworkordens() {

        return this.workOrders;
    }

}

