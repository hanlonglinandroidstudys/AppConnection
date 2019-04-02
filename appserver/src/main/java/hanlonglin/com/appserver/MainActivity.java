package hanlonglin.com.appserver;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hanlonglin.com.appserver.receiver.PermissionReceiver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        PermissionReceiver receiver=new PermissionReceiver();
//        IntentFilter intentFilter=new IntentFilter("");
//        registerReceiver(receiver,);
    }
}
