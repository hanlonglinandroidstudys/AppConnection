# 进程间通信的方式

>模块：

>> appclient 客户端

>> appserver 服务端

## 通信方式

### 广播
在appserver 中注册广播接收者，指定action, appclient 通过action发送广播；
这里用到了权限广播，限定了具有指定权限的应用才能收到广播；具体实现步骤：
* * 1.在appclient Manifest中自定义广播：
```
 <!-- 定义广播接收权限  只有具有此权限的应用才能接收此广播 -->
    <permission android:name="com.appclient.sendXXX" />
```
* * 2.在appclient 发送广播时指定权限：
```
//发送带有权限的广播
    Intent intent = new Intent("com.appserver.receiver");
    sendBroadcast(intent, Manifest.permission.sendXXX);
```
* * 3.在appreceiver  Mainfest中声明权限：
```
<!--使用权限广播  如果不使用 则无法接收到对应权限的广播-->
    <uses-permission android:name="com.appclient.sendXXX" />
```
更多用法参考 [广播的权限使用](https://blog.csdn.net/mafei852213034/article/details/79934375)

### AIDL方式：

* 使用步骤：
* * 1.在appclient中创建aidl文件，项目中为SsoAuth.aidl;默认里面有一个方法void basicTypes, 在里面定义需要使用的方法，这里添加了void ssoAuth() 方法；rebuild工程，appclient中就生成了SsoAuth.java类；

* * 2.把appclient中的aidl包复制到 appserver 中的src/main中，保证两边的aidl文件内容和包名一致，同样rebuild工程appserver,也在同样地方生成了SsoAuth.java;

* * 3.在appserver 中定义SsoService 继承Service ,负责提供服务器的连接和调用，创建一个内部类SsoAuthImpl 继承SsoAuth.Stub，并重写其中方法，这里就是需要暴漏给客户端的方法；并在onBind()中将SsoAuthImpl的实例返回；
```
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
```
* * 4.在appserver Mainfest中注册service:
```
 <service
            android:name=".service.SsoAuthService"
            android:exported="true"
            android:label="@string/app_name"
            android:process=":remote">
            <intent-filter>
                <action android:name="com.action.aidlserver" />
            </intent-filter>
        </service>
```

* * 5.appclient中调用服务：
```
SsoAuth ssoAuth = null;
private void bindService() {
        Intent intent = new Intent("com.action.aidlserver");
        intent.setPackage("hanlonglin.com.appserver"); //5.0以后需要指定包名，不然会报错
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
```
