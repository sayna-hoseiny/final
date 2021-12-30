package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private Spinner spinner2;
    private EditText searchEt;
    private ImageView searchIv;
    String word,lang1,lang2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RequestQueue queue = Volley.newRequestQueue(this);

        String token="67798.2zG1dJzW7tnFHV9WVG8TVd1lx6OUZrsAwlOr5kum";

        getSupportActionBar().hide();


        spinner=findViewById(R.id.spinner1);
        spinner2=findViewById(R.id.spinner2);
        searchEt=findViewById(R.id.searchEt);
        searchIv=findViewById(R.id.searchIv);
        ArrayAdapter<CharSequence> spinneradapter=ArrayAdapter.createFromResource(this,R.array.language, android.R.layout.simple_spinner_item);

        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinneradapter);
        spinner2.setAdapter(spinneradapter);

        spinner.setOnItemSelectedListener(this);
     //   spinner2.setOnItemSelectedListener(this);
       word = searchEt.getText().toString();
        lang1 = spinner.getSelectedItem().toString();
        lang2 = spinner2.getSelectedItem().toString();
        String SelectedLang;
        if(lang1=="English"){ SelectedLang="en2fa";}
        else
            {SelectedLang="dehkhoda";}

        searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, GetUrl(token,word,SelectedLang),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        textView.setText("That didn't work!");
                        Toast.makeText(getApplicationContext(),""+error,Toast.LENGTH_LONG).show();
                    }
                });

// Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {

        lang1 = spinner.getSelectedItem().toString();
        lang2 =spinner2.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public String GetUrl(String Token,String word,String lang){
   return "http://api.vajehyab.com/v3/search?token=" + Token + "&q=" + word + "&type=Text&filter="+lang ;


}}