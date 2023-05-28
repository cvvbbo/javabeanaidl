package com.t.aidltest2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class Myservice extends Service {

    Book mBook =null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Myservice","onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Myservice","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e("Myservice","onStart");
    }

    @Override
    public void onDestroy() {
        Log.e("Myservice","onDestroy");
        super.onDestroy();
    }

         IBinder iBinder = new IMyAidlInterface.Stub(){

             @Override
             public Book getdata() throws RemoteException {
                synchronized (this){
                    if (mBook!=null){
                        return mBook;
                    }
                    return new Book("defult12213123",0);
                }
             }

             @Override
             public void addBook(Book book) throws RemoteException {

//                 mBook = new Book("new book",1);
                 mBook = book;

             }
         };
}
