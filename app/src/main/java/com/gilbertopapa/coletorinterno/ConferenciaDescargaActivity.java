package com.gilbertopapa.coletorinterno;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.gilbertopapa.coletorinterno.controllers.ItensController;
import com.gilbertopapa.coletorinterno.controllers.WorkOrdemController;

public class ConferenciaDescargaActivity extends AppCompatActivity {
    TextView firstName, placa, nunOS;
    Button btnEnd, btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conferencia_descarga);

        firstName = (TextView) findViewById(R.id.FirstName);

        final Intent conferenteIntent = getIntent();
        final String firstNameIntent = conferenteIntent.getStringExtra("firstNameIntent");
        firstName.setText("Conferente: ".concat(firstNameIntent));
        final String idConferente = conferenteIntent.getStringExtra("id");
        final String order_number = conferenteIntent.getStringExtra("order_number").replace("Número da Ordem: ","").replace(" - Descarga","");
        final String plate = conferenteIntent.getStringExtra("plate");


        placa = (TextView) findViewById(R.id.PlacaDescarga);
        placa.setText(plate);

        nunOS = (TextView) findViewById(R.id.NumOSDescarga);
        nunOS.setText(order_number);

        btnEnd = (Button) findViewById(R.id.btnEnd);
        btnAddItem = (Button) findViewById(R.id.BtnAdd);

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                finish();
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Dialog dialog = new Dialog(getWindow().getDecorView().getRootView().getContext());

                dialog.setContentView(R.layout.dialog_add_item);

                dialog.setTitle("Busca de cliente:");

                //instancia os objetos que estão no layout customdialog.xml
                final Button confirmar = (Button) dialog.findViewById(R.id.BtnConfirmar);
                final Button cancelar = (Button) dialog.findViewById(R.id.BtnCancelar);

                final EditText edtName = (EditText) dialog.findViewById(R.id.edtItemName);
                final EditText edtQtde = (EditText) dialog.findViewById(R.id.edtQtde);
                final EditText edtWeight = (EditText) dialog.findViewById(R.id.edtWeight);

                final CheckBox cBom = (CheckBox) dialog.findViewById(R.id.checkBox2);
                final CheckBox cDanificado = (CheckBox) dialog.findViewById(R.id.checkBox3);
                final CheckBox cLimpeza = (CheckBox) dialog.findViewById(R.id.checkBox5);
                final CheckBox cInutilizado = (CheckBox) dialog.findViewById(R.id.checkBox6);

                confirmar.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        new Thread(){
                            @Override
                            public void run() {
                                super.run();
                                ItensController itensController = new ItensController();
                                itensController.setItem(edtName.getText().toString(),
                                        edtWeight.getText().toString(),order_number, getWindow().getDecorView().getRootView());
                                while (itensController.getItem() == null) {
                                }

                                System.out.println("Sou +Eu: "+itensController.getItem().getName());

                                itensController = new ItensController();
                                itensController.getItens(order_number, getWindow().getDecorView().getRootView());
                                while (itensController.getItens() == null) {
                                }

                            }
                        }.start();
                        dialog.dismiss();
                    }
                });

                cancelar.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        new Thread() {
            @Override
            public void run() {
                super.run();

                WorkOrdemController workOrdemController = new WorkOrdemController();
                workOrdemController.Getworkordem(idConferente, getWindow()
                                .getDecorView().getRootView(),null,null,order_number,null);

                while (workOrdemController.Getworkordens() == null) {
                }

                ItensController itensController = new ItensController();
                itensController.getItens(order_number, getWindow().getDecorView().getRootView());
                while (itensController.getItens() == null) {
                }
            }

        }.start();
    }
}
