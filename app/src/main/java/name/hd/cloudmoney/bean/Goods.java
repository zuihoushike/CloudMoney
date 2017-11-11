package name.hd.cloudmoney.bean;

/**
 * Created by AkiNobunaga on 2017/10/26.
 */

public class Goods {
    private int icon;
    private String name;
    private int scanNum;
    private int selledNum;
    private String state;
    private double price;

    public Goods(){

    }
    public Goods(int icon, String name, int scanNum, int selledNum, String state, double price) {
        this.icon = icon;
        this.name = name;
        this.scanNum = scanNum;
        this.selledNum = selledNum;
        this.state = state;
        this.price = price;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScanNum() {
        return scanNum;
    }

    public void setScanNum(int scanNum) {
        this.scanNum = scanNum;
    }

    public int getSelledNum() {
        return selledNum;
    }

    public void setSelledNum(int selledNum) {
        this.selledNum = selledNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
