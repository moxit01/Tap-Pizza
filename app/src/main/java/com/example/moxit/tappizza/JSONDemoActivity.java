package com.example.moxit.tappizza;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class JSONDemoActivity extends AppCompatActivity{


    ListView FirstDemo;
    Button BtnJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsondemo);
        FirstDemo=(ListView)findViewById(R.id.FirstDemo);
        new JSONParsing().execute();


    }

    public class JSONParsing extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... voids) {

            //this is the classes to get api
            HttpClient HC=new DefaultHttpClient();
            HttpGet HG=new HttpGet("http://antarikshtech.com/api/feeds");
            ResponseHandler<String> RH=new BasicResponseHandler();
            try {

                //parse json string to jsonarray or jsonobject
                String MyJsonStr = HC.execute(HG, RH);
                return MyJsonStr;
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try{
                JSONArray JA=new JSONArray(s);
                ArrayList<String> titles = new ArrayList<>();

                //

                for(int i=0; i<JA.length();i++){
                    JSONObject jb=JA.getJSONObject(i);
                    titles.add(jb.getString("title"));
                }
                FirstDemo.setAdapter(new ArrayAdapter<String>(
                        JSONDemoActivity.this
                        , android.R.layout.simple_list_item_1
                        , titles));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

