package com.example.android.logisticsmanager;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity implements android.view.View.OnClickListener {

    Button btnAdd, btnGetAll;
    TextView auto_Id;
    EditAuto repo;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listeaza();
        Auto a = new Auto();


        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dbmanager = new Intent(MainActivity.this, AndroidDatabaseManager.class);
                startActivity(dbmanager);
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.btnAdd)) {
            intent = new Intent(this, CarsDetail.class);
            startActivity(intent);

        }
    }

    public void listeaza() {

        repo = new EditAuto(this);

        ArrayList<HashMap<String, String>> carsList = repo.getCarsList();
        if (carsList.size() != 0) {
            ListView lv = getListView();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Button btnSaveU = (Button) findViewById(R.id.btnSave);
                    auto_Id = (TextView) view.findViewById(R.id.id);
                    String carId = auto_Id.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(), CarsDetail.class);
                    objIndent.putExtra("auto_Id", Integer.parseInt(carId));
                    startActivity(objIndent);
                }
            });
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, carsList, R.layout.view_car_entry,
                    new String[]{"id", "numar", "marca", "tip", "data", "sofer"},
                    new int[]{R.id.id, R.id.numar, R.id.marca, R.id.type, R.id.data, R.id.sofer});
            setListAdapter(adapter);
        } else {
            Toast.makeText(this, "No Cars!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, CarsDetail.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }


}