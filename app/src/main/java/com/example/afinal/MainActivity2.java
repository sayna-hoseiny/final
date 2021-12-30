package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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


        searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word = searchEt.getText().toString();
                lang1 = spinner.getSelectedItem().toString();
                lang2 = spinner2.getSelectedItem().toString();
                String SelectedLang;
                if(lang1.equals("English")){ SelectedLang="en2fa";}
                else
                {SelectedLang="dehkhoda";}

                StringRequest stringRequest = new StringRequest(Request.Method.GET, GetUrl(token,word,SelectedLang),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
try{
    JSONObject body=new JSONObject(response);
    JSONObject a=new JSONObject(body.getString("data"));
    JSONArray results=a.getJSONArray("results");
    String result="";
    for(int i=0;i<results.length();i++){
        JSONObject jo=results.getJSONObject(i);
        result=jo.getString("text");
    }


Intent intent=new Intent(getApplicationContext(),MainActivity3.class);
    intent.putExtra("result",result);
    intent.putExtra("word",word);
    startActivity(intent);


}catch (JSONException j){searchEt.setText(j.toString());}


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        textView.setText("That didn't work!");
                        Toast.makeText(getApplicationContext(),""+error,Toast.LENGTH_LONG).show();
                    }
                });




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
   return "http://api.vajehyab.com/v3/search?token=" + Token + "&q=" + word + "&type=exact&filter="+lang ;


}}