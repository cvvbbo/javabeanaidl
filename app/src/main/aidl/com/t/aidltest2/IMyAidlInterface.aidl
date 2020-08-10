// IMyAidlInterface.aidl
package com.t.aidltest2;

// Declare any non-default types here with import statements

import com.t.aidltest2.Book;
interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */


            Book getdata();

            void addBook(in Book book);

}
