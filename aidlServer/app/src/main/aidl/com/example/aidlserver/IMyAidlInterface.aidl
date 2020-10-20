// IMyAidlInterface.aidl
package com.example.aidlserver;

import com.example.aidlserver.User;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    String getName();
    User getUserName(String data);
}
