package com.example.xingliu.wifipowertest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private String size_in_bytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.round_activity_main);

//        final Spinner spinner = (Spinner) findViewById(R.id.mySpinner);
//
//        List<String> list = new ArrayList<String>();
//        list.add("2KB");
//        list.add("5KB");
//        list.add("10KB");
//        list.add("50KB");
//        list.add("100KB");
//        list.add("500KB");
//        list.add("1MB");
//        list.add("2MB");
//        list.add("3MB");
//        list.add("4MB");
//        list.add("5MB");
//
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, list);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(dataAdapter);
//        spinner.setOnItemSelectedListener(this);
    }


    public void downloadClick(View view) {
        String cmd = "/data/local/download_test_new 156.56.83.26 6001 1 2097152 -1 -1 &";
        runAsRoot(cmd);
    }

    public void uploadClick(View view) {
        String cmd = "/data/local/download_test_new 156.56.83.26 6001 2097152 1 -1 -1 &";
        runAsRoot(cmd);
    }


//    public void uploadClick(View view) {
//        String cmd = "/data/local/download_test 156.56.83.26 6001 " + size_in_bytes
//                + " 1 -1 -1";
//
//        Log.v("wifipowertest", cmd);
//
//        try {
//            Thread.sleep(8*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        runAsRootThenWaitFor(cmd);
//    }
//
//
//    public void onItemSelected(AdapterView<?> parent, View view,
//                               int pos, long id) {
//        // An item was selected. You can retrieve the selected item using
//        String spinner_item = (String) parent.getItemAtPosition(pos);
//
//        if( spinner_item.equals("1KB") )
//            size_in_bytes = "1000";
//        else if( spinner_item.equals("2KB") )
//            size_in_bytes = "2000";
//        else if( spinner_item.equals("5KB") )
//            size_in_bytes = "5000";
//        else if( spinner_item.equals("10KB") )
//            size_in_bytes = "10000";
//        else if( spinner_item.equals("50KB") )
//            size_in_bytes = "50000";
//        else if( spinner_item.equals("100KB") )
//            size_in_bytes = "100000";
//        else if( spinner_item.equals("500KB") )
//            size_in_bytes = "500000";
//        else if( spinner_item.equals("1MB") )
//            size_in_bytes = "1000000";
//        else if( spinner_item.equals("2MB") )
//            size_in_bytes = "2000000";
//        else if( spinner_item.equals("3MB") )
//            size_in_bytes = "3000000";
//        else if( spinner_item.equals("4MB") )
//            size_in_bytes = "4000000";
//        else if( spinner_item.equals("5MB") )
//            size_in_bytes = "5000000";
//
//        Log.v("wifipowertest", size_in_bytes);
//    }


    public static void runAsRoot(String cmd){
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
//                if( suprocess != null )
//                    suprocess.waitFor();
            } catch (Exception e) {
                //
            }
        }
    }
}


