package com.example.getandroiddeviceinfo;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TextView mSoftVersionTextView = ( TextView)findViewById(R.id.txt_softwave_version);
         mSoftVersionTextView.setText(DeviceInfoUntil.getAppVersionName(this));
         
         TextView mOsVersionTextView = ( TextView)findViewById(R.id.txt_os_version);
         mOsVersionTextView.setText(DeviceInfoUntil.getosVersion());
         
         TextView mCpuXinghaoTextView = ( TextView)findViewById(R.id.txt_cpu_xinghao);
         mCpuXinghaoTextView.setText(DeviceInfoUntil.getCpuXingHao());
         
         //TextView mNeicunTextView = ( TextView)findViewById(R.id.txt_neicun);
        // mNeicunTextView.setText(DeviceInfoUntil.getTotalMemory(this));
    }


}
