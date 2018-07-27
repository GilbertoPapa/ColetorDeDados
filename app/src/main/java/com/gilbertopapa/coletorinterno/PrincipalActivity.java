package com.gilbertopapa.coletorinterno;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gilbertopapa.coletorinterno.controllers.WorkOrdemController;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PrincipalActivity extends AppCompatActivity {

    TextView firstName, dataAtual;
    ListView listViewOrdens;

    Button _btnAtt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        dataAtual = (TextView) findViewById(R.id.dataAtual);
        firstName = (TextView) findViewById(R.id.FirstName);
        listViewOrdens = (ListView) findViewById(R.id.listViewPessoas);

        _btnAtt = (Button) findViewById(R.id.btnAtt);

        Intent conferenteIntent = getIntent();
        final String firstNameIntent = conferenteIntent.getStringExtra("firstNameIntent");
        final String idConferente = conferenteIntent.getStringExtra("idIntent");
        firstName.setText("Conferente: ".concat(firstNameIntent));

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dataAtual.setText(sdf.format(c.getTime()));



        new Thread() {
            @Override
            public void run() {
                super.run();

                WorkOrdemController workOrdemController = new WorkOrdemController();
                workOrdemController.Getworkordem(idConferente, getWindow()
                        .getDecorView().getRootView(),firstNameIntent,idConferente,null,null);
                while (workOrdemController.Getworkordens() == null) {
                }

            }

        }.start();

        _btnAtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(PrincipalActivity.this, "Iniciando Atualização", Toast.LENGTH_SHORT).show();

                new Thread() {
                    @Override
                    public void run() {
                        super.run();

                        WorkOrdemController workOrdemController = new WorkOrdemController();
                        workOrdemController.Getworkordem(idConferente, getWindow()
                                .getDecorView().getRootView(),firstNameIntent,idConferente,null,null);
                        while (workOrdemController.Getworkordens() == null) {
                        }
                    }

                }.start();
            }
        });

    }

}
