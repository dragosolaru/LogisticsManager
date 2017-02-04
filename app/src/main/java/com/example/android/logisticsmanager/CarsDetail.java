package com.example.android.logisticsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CarsDetail extends AppCompatActivity implements android.view.View.OnClickListener {

    Button btnSave, btnDelete, btnSend;
    private EditText nrText;
    private EditText marcaText;
    private EditText tipText;
    private EditText dataText;
    private EditText soferText;
    private int _Auto_Id;
    private EditAuto e_a;
    private String msgEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSend = (Button) findViewById(R.id.btnSend);

        nrText = (EditText) findViewById(R.id.numar);
        marcaText = (EditText) findViewById(R.id.marca);
        tipText = (EditText) findViewById(R.id.tipul);
        dataText = (EditText) findViewById(R.id.data);
        soferText = (EditText) findViewById(R.id.sofer);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSend.setOnClickListener(this);

        Intent intent = getIntent();
        _Auto_Id = intent.getIntExtra("auto_Id", 0);
        e_a = new EditAuto(this);

        Auto auto;
        auto = e_a.getAutoById(_Auto_Id);

        nrText.setText(auto.getNr_inm());
        marcaText.setText(auto.getMarca());
        tipText.setText(auto.getTip());
        dataText.setText(auto.getData());
        soferText.setText(auto.getSofer());
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        final String numar = nrText.getText().toString();
        final String marca = marcaText.getText().toString();
        final String tip = tipText.getText().toString();
        final String data = dataText.getText().toString();
        final String sofer = soferText.getText().toString();

        Auto auto = new Auto(numar, marca, tip, data, sofer);

        msgEmail = "Autoturismul cu numarul:" + auto.getNr_inm()
                + "\nmarca: " + auto.getMarca()
                + "\nde tipul: " + auto.getTip()
                + "\ninmatriculata in data: " + auto.getData()
                + "\nutilizata de: " + auto.getSofer();

        switch (view.getId()) {
            case R.id.btnSave:

                intent = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                if (_Auto_Id == 0) {
                    e_a.insertAuto(auto);
                    startActivity(intent);
                    Toast.makeText(this, "New Cars Insert", Toast.LENGTH_SHORT).show();
                } else {
                    e_a.updateAutoById(_Auto_Id, auto);
                    Toast.makeText(this, "Cars Record updated", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                break;

            case R.id.btnDelete:
                //   EditAuto repo = new EditAuto(this);
                e_a.delete(_Auto_Id);
                Toast.makeText(this, "Cars Record Deleted", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
                break;

            case R.id.btnSend:

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"Introduceti adresa de email"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Date despre autoturismul: " + numar.toUpperCase());
                i.putExtra(Intent.EXTRA_TEXT, msgEmail);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

        }

    }

}
//            auto.setNr_inm(nrText.getText().toString());
//            auto.setMarca(marcaText.getText().toString());
//            auto.setTip(tipText.getText().toString());
//            auto.setData( dataText.getText().toString());
//            auto.setSofer( soferText.getText().toString());
//            auto.setAuto_Id(_Auto_Id);

//                //Intent intent=new Intent(this,MainActivity.class);
//                Toast.makeText(this, "Cars Record unchanged", Toast.LENGTH_SHORT).show();
//                finish();
//                break;