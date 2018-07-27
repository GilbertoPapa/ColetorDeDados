package com.gilbertopapa.coletorinterno;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gilbertopapa.coletorinterno.controllers.ItensController;
import com.gilbertopapa.coletorinterno.controllers.WorkOrdemController;

public class ConferenciaCargaActivity extends AppCompatActivity {
    TextView firstName, placa, nunOS;
    Button btnEnd;
    String finishOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conferencia_carga);

        firstName = (TextView) findViewById(R.id.FirstName);

        final Intent conferenteIntent = getIntent();
        final String firstNameIntent = conferenteIntent.getStringExtra("firstNameIntent");
        firstName.setText("Conferente: ".concat(firstNameIntent));
        final String idConferente = conferenteIntent.getStringExtra("id");
        final String order_number = conferenteIntent.getStringExtra("order_number").replace("Número da Ordem: ","").replace(" - Carga","").replace(" - Descarga","");
        final String plate = conferenteIntent.getStringExtra("plate");


        placa = (TextView) findViewById(R.id.PlacaCarga);
        placa.setText(plate);

        nunOS = (TextView) findViewById(R.id.NumOSCarga);
        nunOS.setText(order_number);



        btnEnd = (Button) findViewById(R.id.btnEnd);

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(ConferenciaCargaActivity.this, "Carga Finalizada", Toast.LENGTH_SHORT).show();
                finish();
            }
        });



        new Thread() {
            @Override
            public void run() {
                super.run();

                WorkOrdemController workOrdemController = new WorkOrdemController();
                workOrdemController.Getworkordem(idConferente, getWindow().getDecorView().getRootView()
                        ,null,null,order_number,null);

                while (workOrdemController.Getworkordens() == null) {
                }

                for (int i = 0; i < workOrdemController.Getworkordens().size(); i++) {
                    if(order_number.equalsIgnoreCase(workOrdemController.Getworkordens().get(i).getId().toString())){
                        ItensController itensController = new ItensController();
                        itensController.getItens(workOrdemController.Getworkordens().get(i).getId().toString()
                                ,getWindow().getDecorView().getRootView());
                        while (itensController.getItens() == null) {
                        }
                        System.out.println("eu+ "+itensController.getItens().get(0).getName());
                        itensController.getItens(order_number, getWindow().getDecorView().getRootView());

                       break;
                    }
                }

            }

        }.start();

    }
}
