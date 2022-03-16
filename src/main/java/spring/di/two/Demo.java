package spring.di.two;

import spring.di.Door;
import spring.di.Wheel;

/**
 * 什么是注入？如同注射药物注入体内
 *
 * 汽车还是依赖 门、轮胎等等
 *
 * @Author qinwen
 * @Date 2022/3/16 2:25 下午
 */
public class Demo {
    public static void main(String[] args) {

        // 实例化Door
        Door door = new Door();
        // 实例化Wheel
        Wheel wheel = new Wheel();
        // 通过构造函数注入到Car中
        Car car = new Car(door, wheel);
        car.run();
    }

}
class Car {
    private Door door;
    private Wheel wheel;

    /**
     * Door类 和 Wheel类 在外部实例化 通过Car类的构造函数 注入进去的
     * 他们的实例化是Car类外部控制的
     *
     * 一个在内一个在外 控制权反转了 ==> IoC
     * @param door
     * @param wheel
     */
    public Car(Door door, Wheel wheel) {
        this.door = door;
        this.wheel = wheel;
    }
    public void run() {
        door.close();
        wheel.makeFull();
        System.out.println("Already car begin run...");
    }

}