package demo.enumPackage;

/**
 * Created by admin on 2017/9/11.
 */
public enum Sex {
    MALE(0, "男"), FEMALE(1, "女");
    private int id;
    private String name;

    private Sex(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Sex getSex(int id) {
        return id == 1 ? MALE : (id == 2 ? FEMALE : null);
    }
}
