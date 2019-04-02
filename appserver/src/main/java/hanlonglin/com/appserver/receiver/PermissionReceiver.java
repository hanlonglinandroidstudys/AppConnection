package hanlonglin.com.appserver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PermissionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("PermissionReceiver","接收到权限广播：action:"+intent.getAction());
    }
}
