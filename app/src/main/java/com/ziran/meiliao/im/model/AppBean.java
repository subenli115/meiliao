package com.ziran.meiliao.im.model;

public class AppBean {

    private int id;
    private int icon;
    private String funcName;
    private boolean isSelect;
    private String status="0";
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    private String gold;

    public int getIcon() {
        return icon;
    }

    public String getFuncName() {
        return funcName;
    }

    public int getId() {
        return id;
    }

    public AppBean(int icon, String funcName,String gold){
        this.icon = icon;
        this.funcName = funcName;
        this.gold = gold;
    }
}
