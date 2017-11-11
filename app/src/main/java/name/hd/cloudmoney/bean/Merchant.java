package name.hd.cloudmoney.bean;

/**
 * Created by AkiRuka on 2017/9/21.
 */

public class Merchant {
    private int icon;
    private int selled;
    private String name,info;
    private double price,distance;

    public Merchant() {
    }

    public Merchant(int icon, int selled, String name, String info, double price, double distance) {
        this.icon = icon;
        this.selled = selled;
        this.name = name;
        this.info = info;
        this.price = price;
        this.distance = distance;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getSelled() {
        return selled;
    }

    public void setSelled(int selled) {
        this.selled = selled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
