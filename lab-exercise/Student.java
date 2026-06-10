import java.util.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Q1 — OOP Fundamentals & Encapsulation (P–CR)
 *
 * 实现一个 Student 类，注意封装、防御性副本、静态计数器和输入验证。
 *
 * 要求（DO NOT change class name, field names, or method signatures）：
 *  1. name 不能为 null：若传入 null，抛出 IllegalArgumentException。
 *  2. getGrades() 必须返回防御性副本（defensive copy），外部修改返回的 list 不能影响内部状态。
 *  3. addGrade(int) 只接受 0–100 的分数；超出范围抛出 IllegalArgumentException。
 *  4. average() 返回所有分数的平均值（double）；若没有分数，返回 0.0。
 *  5. 每成功创建一个 Student，静态计数器 +1；getStudentCount() 返回当前总数。
 *
 * 提示：方法即使没实现完，也要 return 一个值，保证能编译。
 */
public class Student {

    // TODO: 声明 fields（name、grades、static counter）

    public Student(String name) {
        // TODO: 验证 name，初始化 fields，更新计数器
    }

    public String getName() {
        // TODO
        return null;
    }

    public List<Integer> getGrades() {
        // TODO: 返回防御性副本
        return null;
    }

    public void addGrade(int grade) {
        // TODO: 验证 0–100，然后加入
    }

    public double average() {
        // TODO
        return 0.0;
    }

    public static int getStudentCount() {
        // TODO
        return 0;
    }
}
