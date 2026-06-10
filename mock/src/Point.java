import java.util.List;

/**
 * Q4 — equals / hashCode & HashMap (D)
 *
 * 实现 Point 类和 PointCounter 类。
 *
 * 要求：
 *  1. Point 有 int x 和 int y（构造器设置，提供 getX()、getY()）。
 *  2. 正确 Override equals() 和 hashCode()：
 *       - 两个 Point 当且仅当 x 和 y 都相等时 equals 返回 true。
 *       - 满足 equals 一致的对象 hashCode 必须相同。
 *       - 处理 null 和不同类型的情况。
 *  3. PointCounter.countUnique(List<Point>) 返回 list 中【不重复】Point 的数量。
 *     （依赖你正确实现的 equals/hashCode，用 HashSet 即可。）
 *
 * 思考题（不计分，但口试可能问）：
 *  如果 Point 是 mutable 的（有 setX），把它当 HashMap 的 key 后再修改 x，
 *  会发生什么？为什么？
 *
 * DO NOT change class names or method signatures.
 */
public class Point {

    // TODO: fields x, y

    public Point(int x, int y) {
        // TODO
    }

    public int getX() {
        return 0;
    }

    public int getY() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        // TODO
        return false;
    }

    @Override
    public int hashCode() {
        // TODO
        return 0;
    }
}
