package com.gilbertopapa.coletorinterno;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gilbertopapa.coletorinterno.controllers.ConferenteController;

public class LoginActivity extends AppCompatActivity {

    private TextView tvConferente;
    private EditText edtTitle;
    private Button btnLogin;
    String title,firstNameIntent,idIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvConferente = (TextView) findViewById(R.id.textView);
        edtTitle = (EditText) findViewById(R.id.Title);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = edtTitle.getText().toString();

                new Thread() {
                    @Override
                    public void run() {
                        super.run();

                        ConferenteController conferenteController = new ConferenteController();
                        conferenteController.getConferente(title);
                        while (conferenteController.getConferentes() == null) {
                        }

                        firstNameIntent = conferenteController.getConferentes().get(0).getFirst_name();
                        idIntent = String.valueOf(conferenteController.getConferentes().get(0).getId());

                        Intent UsuarioLogado = new Intent(LoginActivity.this, PrincipalActivity.class);
                        UsuarioLogado.putExtra("firstNameIntent", firstNameIntent);
                        UsuarioLogado.putExtra("idIntent",idIntent);
                        startActivity(UsuarioLogado);

                    }
                }.start();
            }
        });
    }



}
