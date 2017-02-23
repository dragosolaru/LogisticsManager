package com.example.android.logisticsmanager;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CarsDetail extends AppCompatActivity implements android.view.View.OnClickListener {

    public Button btnSave, btnDelete, btnSend;
    private EditText nrText;
    private AutoCompleteTextView marcaText;
    private EditText dataText;
    private EditText soferText;
    private int _Auto_Id;
    private EditAuto e_a;
    private Spinner tipText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_detail);

        takeAndManInfo();

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        final String numar = nrText.getText().toString();
        final String marca = marcaText.getText().toString();
        final String tip = tipText.getSelectedItem().toString();
        final String data = dataText.getText().toString();
        final String sofer = soferText.getText().toString();

        Auto auto = new Auto(numar, marca, tip, data, sofer);

        String msgEmail = "Autoturismul cu numarul:" + auto.getNr_inm()
                + "\nmarca: " + auto.getMarca()
                + "\nde tipul: " + auto.getTip()
                + "\ninmatriculat in data: " + auto.getData()
                + "\nutilizat de: " + auto.getSofer();

        switch (view.getId()) {
            case R.id.btnSave:
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
                if (_Auto_Id == 0) {
                    Toast.makeText(this, "No entry to delete", Toast.LENGTH_SHORT).show();
                } else {
                    e_a.delete(_Auto_Id);
                    Toast.makeText(this, "Cars Record Deleted", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
                break;

            case R.id.btnSend:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"Introduceti adresa de email"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Date despre autoturismul: " + numar.toUpperCase());
                intent.putExtra(Intent.EXTRA_TEXT, msgEmail);
                try {
                    startActivity(Intent.createChooser(intent, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
        }

    }


    public void takeAndManInfo() {
        String[] marca = getResources().getStringArray(R.array.makes_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, marca);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSend = (Button) findViewById(R.id.btnSend);

        nrText = (EditText) findViewById(R.id.numar);
        marcaText = (AutoCompleteTextView) findViewById(R.id.marca);
        marcaText.setAdapter(adapter);
        tipText = (Spinner) findViewById(R.id.spinner);
        dataText = (EditText) findViewById(R.id.data);
        soferText = (EditText) findViewById(R.id.sofer);

        Intent intent = getIntent();
        _Auto_Id = intent.getIntExtra("auto_Id", 0);
        e_a = new EditAuto(this);                   //cautam si populam dupa ID in baza de date
        Auto auto = e_a.getAutoById(_Auto_Id);
        nrText.setText(auto.getNr_inm());
        marcaText.setText(auto.getMarca());
        tipText.setSelection(getIndex(tipText, auto.getTip()));
        dataText.setText(auto.getData());
        soferText.setText(auto.getSofer());

        //    Toast.makeText(getApplicationContext(), "ID= " + _Auto_Id, Toast.LENGTH_SHORT).show();

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSend.setOnClickListener(this);

        dataText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean hasfocus) {
                if (hasfocus) {
                    DateDialog dialog = new DateDialog(view);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }

        });
    }

    //set spiner base :)
    private int getIndex(Spinner spinner, String myString) {
        int index = 0;

        for (int i = 1; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
//  public void onItemSelected(AdapterView<?> parent, View view, int pos,
//                               long id) {
//
//        Toast.makeText(parent.getContext(),
//                "OnItemSelectedListener : " +
//                        parent.getItemAtPosition(pos).toString(),
//                Toast.LENGTH_SHORT).show();
//
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


//    public void autoComplet() {
//        //Creating the instance of ArrayAdapter containing list of language names
//        ArrayAdapter<String> adapter = new ArrayAdapter<>
//                (this, android.R.layout.select_dialog_item, getResources().getStringArray(R.array.tipuri_auto));
//
//        //Getting the instance of AutoCompleteTextView
//        tipText = (AutoCompleteTextView) findViewById(R.id.autoComplTipul);
//        tipText.setThreshold(1);//will start working from first character
//        tipText.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//    }

//tipText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//@Override
//public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        int i=position;
//        switch (i){
//        case 0:
//        break;
//        //  Toast.makeText(getApplicationContext(),"este autoutilitara",Toast.LENGTH_LONG).show();
//        case 1:
//        Toast.makeText(getApplicationContext(),"este autoutilitara",Toast.LENGTH_LONG).show();
//        break;
//        case 2:
//        Toast.makeText(getApplicationContext(),"este autoturism",Toast.LENGTH_LONG).show();
//        break;
//        }
//        }
//
//@Override
//public void onNothingSelected(AdapterView<?> parent) {
//
//        }
//        });