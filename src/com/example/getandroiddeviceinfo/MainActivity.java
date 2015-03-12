package com.example.getandroiddeviceinfo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

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
         
         TextView mNeicunTextView = ( TextView)findViewById(R.id.txt_neicun);
         mNeicunTextView.setText(DeviceInfoUntil.getTotalMemory(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
