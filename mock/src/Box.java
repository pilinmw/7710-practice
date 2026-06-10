import java.util.List;

/**
 * Q5 — Generics & Wildcards (D–HD)
 *
 * 实现一个泛型类 Box<T> 和 BoxUtils 工具类。
 *
 * 要求：
 *  1. Box<T> 持有一个 T 类型的值：
 *       - 构造器 Box(T value)
 *       - T get()
 *       - void set(T value)
 *       - boolean isEmpty()  —— value 为 null 时返回 true
 *  2. BoxUtils.sumOfNumbers(List<? extends Number>) 返回 list 中所有数字之和（double）。
 *       - 用 bounded wildcard <? extends Number>，使其能接受 List<Integer>、List<Double> 等。
 *       - 提示：Number 有 doubleValue() 方法。
 *  3. BoxUtils.firstOrNull(Box<T>) 是泛型方法：
 *       - 如果 box 非空返回其值，否则返回 null。
 *       - 方法签名：public static <T> T firstOrNull(Box<T> box)
 *
 * DO NOT change class names or method signatures.
 */
public class Box<T> {

    // TODO: field

    public Box(T value) {
        // TODO
    }

    public T get() {
        return null;
    }

    public void set(T value) {
        // TODO
    }

    public boolean isEmpty() {
        return false;
    }
}
