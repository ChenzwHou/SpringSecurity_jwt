package com.example.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class R implements Serializable {


    private int code; // 200是正常，非200表示异常
    private String msg;
    private Object data;

    public static R succ(Object data) {
        return succ(200, "操作成功", data);
    }
    public static R succ(int code){
        return succ(code);
    }
    public static R succ(int code, String msg, Object data) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
    public static R fail(int code){
        return fail(code);
    }
    public static R fail(String msg) {
        return fail(400, msg, null);
    }
    public static R fail(String msg, Object data) {
        return fail(400, msg, data);
    }
    public static R fail(int code, String msg, Object data) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
