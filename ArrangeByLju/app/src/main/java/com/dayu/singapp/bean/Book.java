package com.dayu.singapp.bean;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ljy on 2017/10/25.
 * Parcelable序列化过程
 */

public class Book implements Parcelable {
    public int id;
    public String bookName;
    public int price;

    @Override
    public int describeContents() {
        return 0;// 几乎都返回0 只有在对象中含有文件描述符的时候 返回1
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(bookName);
        out.writeInt(price);
//        out.writeParcelable(User);序列化对象
    }

    //反序列化功能由Creator实现
    public static final Parcelable.Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    private Book(Parcel in){
        this.id = in.readInt();
        this.bookName = in.readString();
        this.price = in.readInt();
//        this.user = in.readParcelable(Thread.currentThread().getContextClassLoader());  反序列化对象
        // 因为User 是一个可序列化对象，反序列化过程中需要传递当前上下文类加载器 否则会报无法找到类的错误
    }
}
