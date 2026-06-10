#COMP7710 #FinalExam #MockExam

# COMP7710 模拟期末考试

> **格式：** 6 道编程题（P→HD 难度递进）+ 1 组选择题
> **建议：** 先读完所有题，从简单的开始；先让 basic test cases 通过。
> **代码文件：** `mock-exam/src/`（stub，已可编译）；测试：`mock-exam/test/ProvidedTests.java`
> **规则：** 不要改类名、方法签名；方法即使没实现也要 return 一个值。

---

## 编程题概览

| # | 主题 | 难度 | 文件 |
|---|---|---|---|
| Q1 | OOP 基础、封装、防御性副本、static 计数 | P–CR | `Student.java` |
| Q2 | 继承、抽象类、多态 | P–CR/CR | `Shape/Circle/Rectangle.java` |
| Q3 | 接口、Comparator、集合排序 | CR–D | `Employee/EmployeeService.java` |
| Q4 | equals/hashCode、HashMap/HashSet | D | `Point/PointCounter.java` |
| Q5 | 泛型、通配符 | D–HD | `Box/BoxUtils.java` |
| Q6 | 异常（自定义/try-catch-finally）、集合 | D–HD | `InvalidScoreException/ScoreParser.java` |

详细要求见每个 `.java` 文件顶部的 Javadoc 注释。提供的测试用例见 `ProvidedTests.java`。

---

## Part 2: 选择题（Multiple Choice）

> 直接在这里作答，把答案发给我即可（例如 `MCQ1: B`）。覆盖全课程，重点是确定考点。

**MCQ1.** 下面代码的时间复杂度？
```java
for (int i = 0; i < n; i++) {
    for (int j = 0; j < 5; j++) {
        System.out.println(i + j);
    }
}
```
A. O(1)  B. O(n)  C. O(n²)  D. O(n log n)

**MCQ2.** 下面代码的时间复杂度？
```java
for (int i = n; i > 0; i = i / 2) {
    System.out.println(i);
}
```
A. O(1)  B. O(log n)  C. O(n)  D. O(n²)

**MCQ3.** 下面代码输出什么？
```java
try {
    System.out.println("A");
    throw new RuntimeException("x");
} catch (RuntimeException e) {
    System.out.println("B");
    return;
} finally {
    System.out.println("C");
}
```
A. A B  B. A B C  C. A C  D. A B C 然后抛异常

**MCQ4.** 关于 checked exception 和 unchecked exception，哪项正确？
A. checked exception 继承 RuntimeException
B. unchecked exception 必须用 try/catch 或 throws 声明
C. checked exception 必须用 try/catch 或在签名上 throws 声明
D. 两者没有区别

**MCQ5.** 以下哪个表达式是 statement（语句）而非 expression（表达式）？
A. `2 + 5`  B. `x > 0`  C. `int y = 3;`  D. `Math.max(1, 2)`

**MCQ6.** 关于 ArrayList 和 LinkedList，哪项正确？
A. ArrayList 随机访问 get(i) 是 O(1)，LinkedList 是 O(n)
B. 两者 get(i) 都是 O(1)
C. LinkedList 随机访问比 ArrayList 快
D. ArrayList 不能动态扩容

**MCQ7.** 下面代码输出什么？
```java
class A { String who() { return "A"; } }
class B extends A { String who() { return "B"; } }

A obj = new B();
System.out.println(obj.who());
```
A. A  B. B  C. AB  D. 编译错误

**MCQ8.** 关于 abstract class，哪项【错误】？
A. 可以有 constructor
B. 可以有已实现的方法
C. 可以直接用 new 实例化
D. 子类必须实现所有 abstract 方法（除非子类也是 abstract）

**MCQ9.** 下面代码会怎样？
```java
List<Integer> list = new ArrayList<>();
list.add(5);
int x = list.get(0);
```
A. 编译错误  B. 运行错误  C. 正常，x = 5（涉及 autoboxing/unboxing）  D. x = null

**MCQ10.** `super` 关键字【不能】用于以下哪个？
A. 调用父类构造器 `super(...)`
B. 调用父类被覆盖的方法 `super.method()`
C. 访问父类的字段
D. 创建父类的新对象 `super = new Parent()`

**MCQ11.** 一个 Comparator 的 compare(a, b) 永远返回正数，用它排序会怎样？
A. 正常升序  B. 正常降序  C. 排序结果无意义/不可靠  D. 抛异常

**MCQ12.** 关于 `var` 关键字，哪项【错误】？
A. `var x = 10;` 合法
B. `var x;`（无初始化）合法
C. 不能用于方法参数
D. 不能用于实例字段

**MCQ13.** 下面代码输出什么？
```java
Integer a = 1000;
Integer b = 1000;
System.out.println(a == b);
System.out.println(a.equals(b));
```
A. true true  B. false true  C. true false  D. false false
（提示：== 比较引用，equals 比较值；Integer 缓存只在 -128~127）

**MCQ14.** 关于 interface，哪项正确？
A. 一个类只能 implement 一个 interface
B. interface 可以有 constructor
C. interface 可以有 default 方法（带实现）
D. interface 可以有实例字段（非 static final）

**MCQ15.** 把一个 mutable 对象作为 HashMap 的 key，put 之后修改了该对象（导致 hashCode 改变），再用它 get，会怎样？
A. 正常取到值
B. 很可能取不到（返回 null），因为 hashCode 变了
C. 抛异常
D. 自动更新 key

---

## 作答建议

1. **编程题**：在 IntelliJ 里打开 `mock-exam`，把 `src` 设为 source root、`test` 设为 test root，导入 JUnit 5，跑 `ProvidedTests`。
2. **一题一题来**：做完一题把代码发我，我批改+讲解，再做下一题。
3. **选择题**：可以先做，把答案发我对一遍。

---

## TODO

- [ ] Q1 Student — 完成并通过 q1_* 测试
- [ ] Q2 Shape/Circle/Rectangle — 完成并通过 q2_* 测试
- [ ] Q3 Employee/EmployeeService — 完成并通过 q3_* 测试
- [ ] Q4 Point/PointCounter — 完成并通过 q4_* 测试
- [ ] Q5 Box/BoxUtils — 完成并通过 q5_* 测试
- [ ] Q6 InvalidScoreException/ScoreParser — 完成并通过 q6_* 测试
- [ ] MCQ 1–15 — 作答并对答案
