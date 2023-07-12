package com.qtds.entity.VO;

public class R {

    private int code;
    private String msg;
    private Object data;
    private long count;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(Object data, long count) {
        this.data = data;
        this.count = count;
    }

    public R(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public R(int code, String msg, Object data, long count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    public static R ok() {
        return new R(0, "success");
    }
    public static R ok(String msg) {
        return new R(0, msg);
    }

    public static R ok(Object data) {
        return new R(0, "success", data);
    }

    public static R ok(long total, Object data) {
        return new R(0, "success", data, total);
    }

    public static R error(String msg) {
        return new R(-1, msg);
    }

    public static R error(int code, String msg) {
        return new R(code, msg);
    }

    @Override
    public String toString() {
        return "R{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
