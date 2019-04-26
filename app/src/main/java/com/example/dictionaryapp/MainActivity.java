package com.example.dictionaryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView myDictionary;
    private Map<String,String> dictionary;

//
//    public static final String countries[] =  {
//
//            "Nepal","Kathmandu",
//            "India","New Delhi",
//            "China","Beijing",
//            "UK","London",
//            "USA","Washington DC",
//            "Japan","Tokyo"
//
//    };
//    private Map<String,String> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ListView mycountries = findViewById(R.id.myDictionary);

        myDictionary = findViewById(R.id.myDictionary);
        dictionary = new HashMap<>();
//        for(int i=0;i<countries.length;i+=2){
//            dictionary.put(countries[i],countries[i+1]);
//        }
        readFromFile();
        ArrayAdapter countryAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(dictionary.keySet())
        );
        myDictionary.setAdapter(countryAdapter);

        myDictionary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String country = parent.getItemAtPosition(position).toString();
                String capital = dictionary.get(country);
                //Toast.makeText(getApplicationContext(),capital.toString(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,CapitalActivity.class);
                        intent.putExtra("capital",capital);
                startActivity(intent);
            }
        });
    }

    private void readFromFile() {
        try{
            FileInputStream fos = openFileInput("words.txt");
            InputStreamReader isr =  new InputStreamReader(fos);
            BufferedReader br = new BufferedReader(isr);
            String line= "";
            while ((line=br.readLine()) !=null){
                String[] parts = line.split("->");
                dictionary.put(parts[0],parts[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
