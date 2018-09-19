package com.wq.bean;

/**
 * Created by qwu on 2018/9/19.
 */
public enum EmpStatus {
    LOGIN(100, "在线"), LOGOUT(200, "离线"), REMOVE(-100, "注销");
    private Integer code;
    private String msg;

    public static EmpStatus getEmpStatusByCode(Integer code) {
        switch (code) {
            case 100:
                return LOGIN;
            case 200:
                return LOGOUT;
            case -100:
                return REMOVE;
            default:
                return LOGOUT;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    EmpStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
