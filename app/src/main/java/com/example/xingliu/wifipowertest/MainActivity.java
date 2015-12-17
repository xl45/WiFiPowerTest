package com.example.xingliu.wifipowertest;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataOutputStream;

public class MainActivity extends Activity {

    private TextView mTextView;
    private EditText myEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });

        myEditText = (EditText)findViewById(R.id.editText);
    }


    public void downloadClick(View view) {
        String text = myEditText.getText().toString();
        String cmd = "/data/local/download_test ";

        try {
            Thread.sleep(20*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runAsRootThenWaitFor(cmd);
    }


    public void uploadClick(View view) {
        String text = myEditText.getText().toString();
        String cmd = "/data/local/download_test ";

        try {
            Thread.sleep(20*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runAsRootThenWaitFor(cmd);
    }


    public static void runAsRootThenWaitFor(String cmd){
        Process suprocess = null;
        DataOutputStream os = null;

        try {
            suprocess = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(suprocess.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.flush();
        } catch (Exception e) {
            Log.e("wifipowertest", "runAsRootThenWaitFor() cmd: " + cmd + ", error: " + e.getCause().toString());
        } finally {
            try {
                if( os != null )
                    os.close();
                if( suprocess != null )
                    suprocess.waitFor();
            } catch (Exception e) {
                //
            }
        }
    }
}


