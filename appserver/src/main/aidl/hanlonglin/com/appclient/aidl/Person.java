package hanlonglin.com.appclient.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    String name;
    String sex;
    int age;

    public Person(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static Creator<Person> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(sex);
        dest.writeInt(age);
    }

    public static final Creator<Person> CREATOR=new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source.readString(),source.readString(),source.readInt());
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }

    };
}
