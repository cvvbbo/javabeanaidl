package com.t.testaidlclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.t.aidltest2.Book;
import com.t.aidltest2.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    /**
     *
     *  https://www.jianshu.com/p/a8e43ad5d7d2
     *
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setPackage("com.t.aidltest2");
        intent.setAction("com.my.aidl");
        bindService(intent,conn,BIND_AUTO_CREATE);
    }

    ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            final IMyAidlInterface mAidl = IMyAidlInterface.Stub.asInterface(service);
            try {
                // 获取到服务端aidl 中的值。
                Book book = mAidl.getdata();
                Log.e("this-->",book.getName()+"--"+book.getAge());
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 在客户端里面给aidl 服务端赋值。
                        mAidl.addBook(new Book("hahaha",20));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }
            },1500);


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
