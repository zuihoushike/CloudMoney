package name.hd.cloudmoney.bean;

/**
 * Created by AkiNobunaga on 2017/10/23.
 */

public class User {
    private String name;
    private String sex;
    private int age;
    public User(){
        super();
    }
    public User(String name, String sex, int age) {
        super();
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
