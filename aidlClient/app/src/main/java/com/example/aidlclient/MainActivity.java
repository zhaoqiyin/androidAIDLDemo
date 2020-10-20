package com.example.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aidlserver.IMyAidlInterface;


public class MainActivity extends AppCompatActivity {
    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService();
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceConnection = null;
        }
    };

    private void bindService() {
        Intent intent = new Intent();
        intent.setClassName("com.example.aidlserver", "com.example.aidlserver.MyService");
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    private static final String TAG = "MainActivity";

    public void onClick(View view) {
        try {
            Log.d(TAG, "onClick" +
                    ", iMyAidlInterface = " + iMyAidlInterface.getName() +
                    ", iMyAidlInterface.getUserName(1) = " + iMyAidlInterface.getUserName("1") +
                    "" +
                    "");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
