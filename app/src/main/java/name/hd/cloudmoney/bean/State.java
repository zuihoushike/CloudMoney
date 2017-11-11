package name.hd.cloudmoney.bean;

/**
 * Created by AkiNobunaga on 2017/10/28.
 */

public class State {
    private String name;
    private int state;
    public State(){

    }

    public State(int state,String name) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
