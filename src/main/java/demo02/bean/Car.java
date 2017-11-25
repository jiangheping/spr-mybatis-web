package demo02.bean;

public class Car {
    private String brand;

    private String color;

    private int maxSpeed;

    public Car() {
        System.out.println("init car constructor!!");
    }

    public Car(String brand,String color,int maxSpeed){
        this.brand = brand;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }

    public void introduce() {
        System.out.println("brand:"+brand+";color:"+color+";maxSpeed:"+maxSpeed);
    }

    public String getBrand() {
        return brand;
    }

    public Car setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Car setColor(String color) {
        this.color = color;
        return this;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public Car setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }
}
