package com.example.luca.testfirebase;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity implements TaskDelegate {
        private ArrayList<User> Utenti;
        private ListView lv;
        private ImageView imgV;
        private ProgressBar pb;
        private int progressStatus = 0;
        private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listV);
        imgV = (ImageView) findViewById(R.id.imageV);
        pb = (ProgressBar) findViewById(R.id.pb);
        pb.setVisibility(View.VISIBLE);
        TaskDelegate delegate = this;
        getUser(delegate);

    }

        public void getUser(final TaskDelegate delegate){
            TestRestClient.get("Users.json", null, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // If the response is JSONObject instead of expected JSONArray
                    if(statusCode== 200){
                        Log.i("statusC","status:");
                        Utenti = JsonParser.getAllUsers(response);
                        delegate.TaskCompletionResult(null,true);
                    }

                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable error, JSONObject jsonObject){
                    Log.i("restFB","Error");
                    delegate.TaskCompletionResult(null,false);
                }

                @Override
                public void onProgress(long bytesWritten, long totalSize) {
                    Log.i("BYTES", String.valueOf(bytesWritten));
                    Log.i("TOTALS", String.valueOf(totalSize));
                }
            });
        }



    @Override
    public void TaskCompletionResult(String result, boolean otherResult) {
            if(!otherResult ){
                pb.setVisibility(View.INVISIBLE);
                Toast.makeText(this,"No Internet connection",Toast.LENGTH_LONG).show();

            }
            else{
                MyAdapter myAdapter=new MyAdapter(getApplicationContext(),R.layout.item_user,Utenti);
                lv.setAdapter(myAdapter);
                pb.setVisibility(View.INVISIBLE);
        }



    }
    public void showProgress(){
            //Set the progress status zero
            progressStatus = 0;
            //Visible the progressBar
            pb.setVisibility(View.VISIBLE);

            //Start the operation in a background thread
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (progressStatus < 100) {
                        //Update progressStaus
                        progressStatus ++;
                    }
                    try {
                        Thread.sleep(20);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pb.setProgress(progressStatus);

                            if (progressStatus == 100) {
                                pb.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }).start();
    }
}
