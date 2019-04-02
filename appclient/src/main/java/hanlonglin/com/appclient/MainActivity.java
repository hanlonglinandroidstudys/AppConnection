package hanlonglin.com.appclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import hanlonglin.com.appclient.aidl.SsoAuth;

public class MainActivity extends AppCompatActivity {

    SsoAuth ssoAuth = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_send_bro = (Button) findViewById(R.id.btn_send_bro);
        btn_send_bro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送带有权限的广播
                Intent intent = new Intent("com.appserver.receiver");
                sendBroadcast(intent, Manifest.permission.sendXXX);
            }
        });

        Button btn_aidl = (Button) findViewById(R.id.btn_send_aidl);
        btn_aidl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ssoAuth==null){
                bindService();}else{
                    try {
                        ssoAuth.ssoAuth("韩龙林","123456");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void bindService() {
        Intent intent = new Intent("com.action.aidlserver");
        intent.setPackage("hanlonglin.com.appserver");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ssoAuth = SsoAuth.Stub.asInterface(service);
            Log.e("ServiceConnection","service 客户端连接成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ssoAuth=null;
        }
    };
}
