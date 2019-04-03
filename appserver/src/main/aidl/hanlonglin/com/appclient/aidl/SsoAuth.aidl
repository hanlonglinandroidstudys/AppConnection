// SsoAuth.aidl
package hanlonglin.com.appclient.aidl;

import hanlonglin.com.appclient.aidl.Person;

// Declare any non-default types here with import statements

interface SsoAuth {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    //实现的sso授权
    void ssoAuth(String uname,String passwd);

    //通过名字查找
    //复杂对象的传递
    Person modifyPerson(in Person per1);
}
