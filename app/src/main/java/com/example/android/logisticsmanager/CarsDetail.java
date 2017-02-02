package com.example.android.logisticsmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CarsDetail extends AppCompatActivity implements android.view.View.OnClickListener{

    Button btnSave ,  btnDelete, btnClose;
    private EditText nrText;
    private EditText marcaText;
    private EditText tipText;
    private EditText dataText;
    private EditText soferText;
    private int _Auto_Id=0;
    private EditAuto e_a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        nrText = (EditText) findViewById(R.id.numar);
        marcaText = (EditText) findViewById(R.id.marca);
        tipText = (EditText) findViewById(R.id.tipul);
        dataText = (EditText) findViewById(R.id.data);
        soferText = (EditText) findViewById(R.id.sofer);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnSave.setText("Update");


       // _Auto_Id =0;
        Intent intent = getIntent();
        _Auto_Id =intent.getIntExtra("auto_Id", 0);
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
        Intent main = new Intent(this, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (view == findViewById(R.id.btnSave)){

            final String numar = nrText.getText().toString();
            final String marca = marcaText.getText().toString();
            final String tip = tipText.getText().toString();
            final String data = dataText.getText().toString();
            final String sofer = soferText.getText().toString();

            Auto auto = new Auto(numar,marca,tip,data,sofer);
            if (_Auto_Id==0){
                btnSave.setText("ADD");
                e_a.insertAuto(auto);
                startActivity(main);
                Toast.makeText(this,"New Cars Insert",Toast.LENGTH_SHORT).show();
            }else{
                e_a.updateAutoById(_Auto_Id,auto);
                Toast.makeText(this,"Cars Record updated",Toast.LENGTH_SHORT).show();
                startActivity(main);
            }
        }else if (view== findViewById(R.id.btnDelete)){
            EditAuto repo = new EditAuto(this);
            Toast.makeText(this, "Cars Record Deleted", Toast.LENGTH_SHORT).show();
            repo.delete(_Auto_Id);
            startActivity(main);
            finish();
        }else if (view== findViewById(R.id.btnClose)){
            Intent intent=new Intent(this,MainActivity.class);
            finish();
        }

    }

}
//            auto.setNr_inm(nrText.getText().toString());
//            auto.setMarca(marcaText.getText().toString());
//            auto.setTip(tipText.getText().toString());
//            auto.setData( dataText.getText().toString());
//            auto.setSofer( soferText.getText().toString());
//            auto.setAuto_Id(_Auto_Id);