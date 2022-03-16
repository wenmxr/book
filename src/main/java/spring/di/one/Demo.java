package spring.di.one;

import spring.di.Door;
import spring.di.Wheel;

/**
 * 什么是依赖？依靠、需要，离不开
 * 就像一辆汽车，需要门、轮胎等等
 *
 *
 * @Author qinwen
 * @Date 2022/3/16 11:38 上午
 */
public class Demo {
    public static void main(String[] args) {
        // 实例化Car 一定先实例化了 Door 和 Wheel 这就是依赖
        Car car = new Car();
        car.run();
    }
}

class Car {
    private Door door;
    private Wheel wheel;

    /**
     * Door类 和 Wheel类 在 Car类 的构造器里实例化的
     * 他们的实例化交由Car类内部控制
     */
    public Car() {
        this.door = new Door();
        this.wheel = new Wheel();
    }

    public void run() {
        door.close();
        wheel.makeFull();
        System.out.println("Already car begin run...");
    }
}