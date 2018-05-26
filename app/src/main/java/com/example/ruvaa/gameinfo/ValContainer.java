package com.example.ruvaa.gameinfo;

/**
 * Created by ruvaa on 2018-05-25.
 */

public class ValContainer<T> {
    private T val;

    public ValContainer() {
    }

    public ValContainer(T v) {
        this.val = v;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }
}
