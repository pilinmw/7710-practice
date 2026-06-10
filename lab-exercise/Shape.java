/**
 * Q2 — Inheritance, Abstract Class & Polymorphism (P–CR / CR)
 *
 * 本题涉及 3 个文件：Shape（抽象父类）、Circle、Rectangle。
 *
 * 要求：
 *  1. Shape 是 abstract class，有一个 abstract 方法 double area()。
 *  2. Shape 有一个 concrete 方法 String describe()，返回：
 *       "This shape has area X.XX"
 *     其中 X.XX 是 area() 的结果保留两位小数（用 String.format("%.2f", ...)）。
 *     注意：describe() 必须利用 polymorphism 调用子类的 area()。
 *  3. Circle 有 radius（double），area = π * r²（用 Math.PI）。
 *  4. Rectangle 有 width 和 height（double），area = width * height。
 *  5. radius / width / height 不能为负数，否则抛 IllegalArgumentException。
 *
 * DO NOT change class names or method signatures.
 */
public abstract class Shape {

    // TODO: 声明 abstract area() 方法
    public abstract double area();

    public String describe() {
        // TODO: 利用 area() 构造描述字符串
        return null;
    }
}
