package com.t.aidltest2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

public class Myservice extends Service {

    Book mBook =null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

         IBinder iBinder = new IMyAidlInterface.Stub(){

             @Override
             public Book getdata() throws RemoteException {
                synchronized (this){
                    if (mBook!=null){
                        return mBook;
                    }
                    return new Book("defult",0);
                }
             }

             @Override
             public void addBook(Book book) throws RemoteException {

                 mBook = new Book("new book",1);

             }
         };
}
