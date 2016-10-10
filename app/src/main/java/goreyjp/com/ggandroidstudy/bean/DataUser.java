package goreyjp.com.ggandroidstudy.bean;

/**
 * Created by goreyjp on 16/10/10.
 */

public class DataUser {
    String name = "";
    int age = 0;

    public DataUser(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
