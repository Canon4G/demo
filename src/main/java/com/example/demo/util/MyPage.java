package com.example.demo.util;

import java.io.Serializable;
import java.util.List;

/**
 * create by Canon4G 2018-12-17
 **/
public class MyPage<T> implements Serializable {

    private static final long serialVersionUID = 8880050137694062262L;

    private long count;

    private List<T> list;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
