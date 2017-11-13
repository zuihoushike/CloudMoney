package name.hd.cloudmoney.bean;

/**
 * Created by AkiNobunaga on 2017/11/13.
 */

public class Angel {
    private int icon;
    private String nickname;
    private String date;
    private double turnover;
    private int subCommission;
    private double commission;
    private int orderNum;
    public Angel(){

    }

    public Angel(int icon, String nickname, String date, double turnover, int subCommission,
                 double commission, int orderNum) {
        this.icon = icon;
        this.nickname = nickname;
        this.date = date;
        this.turnover = turnover;
        this.subCommission = subCommission;
        this.commission = commission;
        this.orderNum = orderNum;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public int getSubCommission() {
        return subCommission;
    }

    public void setSubCommission(int subCommission) {
        this.subCommission = subCommission;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
}
