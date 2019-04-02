package hanlonglin.com.appserver.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import hanlonglin.com.appclient.aidl.SsoAuth;

public class SsoAuthService extends Service {

    SsoAuthImpl mBinder =new SsoAuthImpl();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    class SsoAuthImpl extends SsoAuth.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void ssoAuth(String uname, String passwd) throws RemoteException {
            Log.e("SsoAuthService", "ssoAuth....这里是新浪服务端，登陆执行成功：uname:" + uname + ",passwd:" + passwd);
        }
    }
}
