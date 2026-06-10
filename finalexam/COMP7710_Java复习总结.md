# COMP7710 Java 期末复习总结

> 涵盖 List04–List08 (Exercises) + Week6–Week11 (Labs)
> 除 wordbook 个人项目外的所有 Java 课程内容

---

## 目录

1. [面向对象基础 — List04](#1-面向对象基础--list04)
2. [继承与多态 — List05](#2-继承与多态--list05)
3. [抽象类与模式匹配 — List06](#3-抽象类与模式匹配--list06)
4. [接口与函数式编程 — List07](#4-接口与函数式编程--list07)
5. [集合与异常 — List08](#5-集合与异常--list08)
6. [Lab Week6 — TimetableEntry](#6-lab-week6--timetableentry)
7. [Lab Week7 — 成绩系统](#7-lab-week7--成绩系统)
8. [Lab Week8 — 作业追踪器](#8-lab-week8--作业追踪器)
9. [Lab Week10 — Degree Planner](#9-lab-week10--degree-planner)
10. [Lab Week11 — 泛型与通配符](#10-lab-week11--泛型与通配符)

---

## 1. 面向对象基础 — List04

### 1.1 实例字段 vs 静态字段

**知识点：**
- `private` 实例字段：每个对象独有
- `static` 字段：所有对象共享，属于类本身
- 构造器中可通过 `count++` 来统计对象创建次数

```java
public class CounterStudent {
    private String name;           // 实例字段：每个对象独有
    private static int count = 0;  // 静态字段：所有对象共享

    public CounterStudent(String name) {
        this.name = name;
        count++;  // 每次创建对象，count 自增
    }

    public String getName() { return name; }
    public static int getCount() { return count; }
}
```

### 1.2 访问控制修饰符

**知识点：**

| 修饰符 | 本类 | 同包 | 子类 | 任意 |
|--------|------|------|------|------|
| `private` | ✅ | ❌ | ❌ | ❌ |
| (无修饰符/package-private) | ✅ | ✅ | ❌ | ❌ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `public` | ✅ | ✅ | ✅ | ✅ |

```java
public class Person {
    private final String name;          // 只有本类可访问
    int age;                            // package-private（同包可访问）
    protected final String nationality; // 子类和同包可访问

    public Person(String name, int age, String nationality) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
    }

    public String getName() { return name; }
    protected String getNationality() { return nationality; }
    void birthday() { age++; }  // package-private 方法
}
```

### 1.3 静态工厂方法 + 私有构造器

**知识点：**
- 私有构造器：防止外部直接 `new`，强制使用工厂方法
- 静态工厂方法：`static` 方法返回新对象，封装对象创建逻辑
- `nextId++`：返回当前值后再自增

```java
public class Ticket {
    private int id;
    private String description;
    private static int nextId = 1;  // 全局 ID 计数器

    private Ticket(int id, String description) {  // 私有构造器
        this.id = id;
        this.description = description;
    }

    public static int assignId() {
        return nextId++;  // 先返回当前值，再自增
    }

    public static Ticket create(String description) {  // 工厂方法
        return new Ticket(assignId(), description);
    }
}
```

---

## 2. 继承与多态 — List05

### 2.1 多态 (Polymorphism)

**知识点：**
- `extends`：子类继承父类
- `super(name)`：调用父类构造器
- `@Override`：覆写父类方法
- 多态：父类引用指向子类对象，运行时调用子类实现

```java
// 父类（提供）
public class Vehicle {
    protected String name;
    public Vehicle(String name) { this.name = name; }
    public double tripCost(double km) { return 0.0; }
}

// Car：按公里计费
public class Car extends Vehicle {
    private double costPerKm;
    public Car(String name, double costPerKm) {
        super(name);             // 调用父类构造器
        this.costPerKm = costPerKm;
    }
    @Override
    public double tripCost(double km) { return km * costPerKm; }
}

// Bike：固定费用
public class Bike extends Vehicle {
    private double flatFee;
    public Bike(String name, double flatFee) {
        super(name);
        this.flatFee = flatFee;
    }
    @Override
    public double tripCost(double km) { return flatFee; }
}
```

### 2.2 封装层次（Encapsulation Hierarchy）

**知识点：**
- `protected` 字段可被子类继承访问
- `super.getDescription()`：调用父类方法
- `final class`：禁止被继承

```java
public class Device {
    protected final String name;                        // 子类可直接使用 name
    public Device(String name) { this.name = name; }
    protected String getDescription() { return "Device: " + name; }
}

public class Phone extends Device {
    public Phone(String name) { super(name); }
    @Override
    protected String getDescription() { return "Phone: " + name; }
}

public final class SecurePhone extends Phone {         // final：不可再被继承
    private String securityLevel;
    public SecurePhone(String name, String securityLevel) {
        super(name);
        this.securityLevel = securityLevel;
    }
    @Override
    protected String getDescription() {
        return "Secure " + securityLevel + " " + super.getDescription(); // 调用父类方法
    }
}
// SecurePhone("X","HIGH").getDescription() → "Secure HIGH Phone: X"
```

### 2.3 equals / hashCode / toString

**知识点：**
- `equals` 必须先检查 `this == o`，再检查类型，再比较字段
- `getClass() != o.getClass()`：严格类型检查（不允许子类相等）
- `Objects.hash(...)`：生成 hashCode
- **契约：equals 相等 → hashCode 必须相等**

```java
public class Book {
    private final String isbn;
    private final String title;
    private final int publishYear;

    public Book(String isbn, String title, int publishYear) {
        this.isbn = Objects.requireNonNull(isbn);
        this.title = Objects.requireNonNull(title);
        this.publishYear = publishYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                    // 引用相同
        if (o == null || getClass() != o.getClass()) return false;  // 类型检查
        Book other = (Book) o;
        return publishYear == other.publishYear
                && isbn.equals(other.isbn)
                && title.equals(other.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, publishYear); // 与 equals 字段一致
    }

    @Override
    public String toString() {
        return title + " (" + publishYear + "). ISBN=" + isbn + ".";
    }
}
```

### 2.4 类型转换 (Casting)

**知识点：**
- **向上转型 (Upcast)**：子类 → 父类，自动，安全
- **向下转型 (Downcast)**：父类 → 子类，需要 `instanceof` 检查，否则抛 `ClassCastException`
- `instanceof` 模式变量（Java 16+）：`if (animal instanceof Dog d)` 同时检查并转型

```java
public class CastingApp {
    // 向上转型：返回 Animal 父类引用，实际可能是 Dog/Cat
    public static Animal createAnimal(String type, String name) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(name);
        if (type.equalsIgnoreCase("Dog")) return new Dog(name);  // 自动向上转型
        if (type.equalsIgnoreCase("Cat")) return new Cat(name);
        return new Animal(name);
    }

    // 向下转型：必须先 instanceof 检查
    public static String interact(Animal animal) {
        Objects.requireNonNull(animal);
        if (animal instanceof Dog) {
            Dog dog = (Dog) animal;  // 安全向下转型
            dog.fetch();
            return dog.speak();
        }
        return animal.speak();
    }
}

// Java 16+ 模式匹配语法（更简洁）：
// if (animal instanceof Dog d) { d.fetch(); }
```

---

## 3. 抽象类与模式匹配 — List06

### 3.1 抽象类 (Abstract Class)

**知识点：**
- `abstract class`：不能实例化，可包含抽象方法和具体方法
- `abstract` 方法：只有声明，子类必须实现
- 多态性：通过父类引用调用不同子类的 `area()`

```java
public abstract class Shape {
    public abstract double area();  // 抽象方法，子类必须实现

    // 具体方法，利用多态求所有图形面积之和
    public static double totalArea(Shape[] shapes) {
        double total = 0.0;
        for (Shape s : shapes) {
            total += s.area();  // 多态调用
        }
        return total;
    }
}

public class Circle extends Shape {
    private final double radius;
    public Circle(double radius) { this.radius = radius; }
    @Override
    public double area() { return Math.PI * radius * radius; }
}

public class Rectangle extends Shape {
    private final double width, height;
    public Rectangle(double width, double height) {
        this.width = width; this.height = height;
    }
    @Override
    public double area() { return width * height; }
}

public class Square extends Shape {
    private final double side;
    public Square(double side) { this.side = side; }
    @Override
    public double area() { return side * side; }
}
```

### 3.2 instanceof 模式匹配 + varargs

**知识点：**
- Java 16+ `instanceof` 模式匹配：`o instanceof String s` 检查并绑定变量
- 顺序重要：先检查子类，再检查父类（`Manager` 在 `Employee` 前）
- `double... values`：可变参数，本质是数组，空时 length == 0

```java
public class Processor {
    // instanceof 模式匹配（Java 16+）
    public static void process(Object[] items) {
        for (Object o : items) {
            if (o instanceof String s) {
                System.out.println(s.length());
            } else if (o instanceof Integer i) {
                System.out.println(i * i);
            } else if (o instanceof Manager m) {  // Manager 是 Employee 子类，必须在 Employee 前
                m.setBonus(1000);
            } else if (o instanceof Employee e) {
                System.out.println(e.salary);
            }
        }
    }

    // varargs 可变参数
    public static double average(double... values) {
        if (values.length == 0) return -1.0;  // 空时返回特殊值
        double sum = 0.0;
        for (double v : values) sum += v;
        return sum / values.length;
    }
}
```

---

## 4. 接口与函数式编程 — List07

### 4.1 接口实现 (Implements Interface)

**知识点：**
- `interface`：定义契约（方法签名）
- `default` 方法：接口中带默认实现的方法，实现类可选择覆写
- `implements`：类声明实现接口

```java
// 接口定义（提供）
public interface InterestBearing {
    double annualInterest();  // 抽象方法，实现类必须实现

    default String summary() {  // 默认方法，可以不覆写
        return "Interest[interest=" + annualInterest() + "]";
    }
}

// 定期存款：利息 = 本金 × 利率
public class TimeDeposit implements InterestBearing {
    private final double principal;
    private final double rate;
    public TimeDeposit(double principal, double rate) {
        this.principal = principal; this.rate = rate;
    }
    @Override
    public double annualInterest() { return principal * rate; }
}

// 活期存款：利息 = 余额 × 利率
public class SavingsAccount implements InterestBearing {
    private final double balance;
    private final double rate;
    public SavingsAccount(double balance, double rate) {
        this.balance = balance; this.rate = rate;
    }
    @Override
    public double annualInterest() { return balance * rate; }
}
```

### 4.2 创建接口 + default 方法

```java
// 自定义接口
public interface PricingModel {
    double totalPrice();  // 必须实现

    default String summary() {  // 默认方法
        return "Price[total=" + totalPrice() + "]";
    }
}

// 固定费用：base + fee
public class FlatFeePrice implements PricingModel {
    private final double basePrice, fee;
    public FlatFeePrice(double basePrice, double fee) {
        this.basePrice = basePrice; this.fee = fee;
    }
    @Override
    public double totalPrice() { return basePrice + fee; }
}

// 百分比费用：base + base * rate
public class PercentagePrice implements PricingModel {
    private final double basePrice, rate;
    public PercentagePrice(double basePrice, double rate) {
        this.basePrice = basePrice; this.rate = rate;
    }
    @Override
    public double totalPrice() { return basePrice + (basePrice * rate); }
}
```

### 4.3 函数式接口与 Lambda

**知识点：**
- `Function<T, R>`：接受 T，返回 R，调用 `f.apply(x)`
- `Predicate<T>`：接受 T，返回 boolean，调用 `p.test(x)`
- `BiFunction<T, U, R>`：接受两个参数，返回 R
- `Objects.requireNonNull(f)`：防止函数参数为 null

```java
public final class LambdaApplier {
    // Function<String, String>：字符串变换
    public static String applyFunction(String s, Function<String, String> f) {
        Objects.requireNonNull(f);
        return f.apply(s);
    }
    // 使用：applyFunction("hello", String::toUpperCase) → "HELLO"
    // 使用：applyFunction("hello", s -> s + "!") → "hello!"

    // Predicate<Integer>：整数判断
    public static boolean testPredicate(int x, Predicate<Integer> p) {
        Objects.requireNonNull(p);
        return p.test(x);
    }
    // 使用：testPredicate(5, n -> n > 3) → true

    // BiFunction<Integer, Integer, Integer>：两个整数 → 整数
    public static int applyBiFunction(int a, int b, BiFunction<Integer, Integer, Integer> f) {
        Objects.requireNonNull(f);
        return f.apply(a, b);
    }

    // 条件应用：满足条件则执行 action，否则返回 fallback
    public static int applyIf(int x, Predicate<Integer> condition,
                              Function<Integer, Integer> action, int fallback) {
        Objects.requireNonNull(condition);
        Objects.requireNonNull(action);
        if (condition.test(x)) return action.apply(x);
        return fallback;
    }
}
```

### 4.4 方法引用 (Method References)

**知识点：**
- `ClassName::staticMethod` — 静态方法引用
- `instance::method` — 实例方法引用
- `ClassName::instanceMethod` — 非绑定实例方法引用（第一个参数是 this）
- 自定义函数式接口：`@FunctionalInterface`，只有一个抽象方法

```java
// 自定义函数式接口
@FunctionalInterface
public interface IntOperation {
    int apply(int a, int b);
}

public class Ops {
    // Integer::sum → 等价于 (a, b) -> Integer.sum(a, b)
    public static IntOperation add() { return Integer::sum; }

    // lambda 写法
    public static IntOperation subtract() { return (a, b) -> a - b; }

    // Math::max → 等价于 (a, b) -> Math.max(a, b)
    public static IntOperation max() { return Math::max; }

    // 组合操作：先执行 first(a,b)，结果再与 b 执行 second
    public static IntOperation then(IntOperation first, IntOperation second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        return (a, b) -> second.apply(first.apply(a, b), b);
    }
}
```

### 4.5 Comparator 链式构建

**知识点：**
- `Comparator.comparing(keyExtractor)`：按某个 key 比较
- `comparingInt / comparingDouble`：基本类型避免装箱
- `.reversed()`：反转顺序
- `.thenComparing(...)`：多级排序（第一级相同时用第二级）
- `Comparator.nullsLast(inner)`：null 值排在最后

```java
public final class StudentComparators {
    // 按名字忽略大小写（方法引用）
    public static Comparator<Student> byNameIgnoreCase() {
        return Comparator.comparing(Student::getName, String::compareToIgnoreCase);
    }

    // 按学分升序
    public static Comparator<Student> byCreditsAsc() {
        return Comparator.comparingInt(Student::getCredits);
    }

    // 按 GPA 降序（reversed）
    public static Comparator<Student> byGpaDesc() {
        return Comparator.comparingDouble(Student::getGpa).reversed();
    }

    // 多级：GPA 降序，相同则按名字
    public static Comparator<Student> byGpaDescThenName() {
        return byGpaDesc().thenComparing(byNameIgnoreCase());
    }

    // null 值排最后
    public static Comparator<Student> nullsLast(Comparator<Student> inner) {
        Objects.requireNonNull(inner);
        return Comparator.nullsLast(inner);
    }

    // 返回排序后的副本（不修改原列表）
    public static List<Student> sortedCopy(List<Student> students, Comparator<Student> cmp) {
        Objects.requireNonNull(students);
        Objects.requireNonNull(cmp);
        List<Student> copy = new ArrayList<>(students);
        copy.sort(cmp);
        return copy;
    }
}
```

### 4.6 内部类 (Inner Classes)

**知识点：**

| 内部类类型 | 定义位置 | 能访问外部字段 | 特点 |
|-----------|---------|--------------|------|
| 成员内部类 (Member) | 类体中 | ✅ | 需要外部类实例 |
| 局部内部类 (Local) | 方法中 | ✅ (effectively final) | 只在方法内可见 |
| 匿名内部类 (Anonymous) | 表达式中 | ✅ (effectively final) | 无类名，一次性使用 |

```java
public class MessageCenter {
    private final String prefix;
    public MessageCenter(String prefix) { this.prefix = prefix; }

    // A: 成员内部类 — 可访问外部类的 prefix 字段
    public class Prefixer implements MessageFormatter {
        @Override
        public String format(String msg) {
            return prefix + msg;  // 直接使用外部类 prefix
        }
    }
    public Prefixer prefixer() { return new Prefixer(); }

    // B: 局部内部类 — 在方法内定义，可捕获 effectively final 的局部变量
    public MessageFormatter surround(String left, String right) {
        Objects.requireNonNull(left); Objects.requireNonNull(right);
        class Surrounder implements MessageFormatter {
            @Override
            public String format(String message) {
                return left + message + right;  // 捕获 left, right
            }
        }
        return new Surrounder();
    }

    // C: 匿名内部类 — 不能用 lambda（题目要求），适合一次性使用
    public MessageFormatter upper() {
        return new MessageFormatter() {  // 匿名实现接口
            @Override
            public String format(String message) {
                return message.toUpperCase();
            }
        };
    }
}
```

---

## 5. 集合与异常 — List08

### 5.1 List — ArrayList vs LinkedList

**知识点：**
- `ArrayList`：基于数组，随机访问 O(1)，中间插入/删除 O(n)
- `LinkedList`：基于链表，头尾操作 O(1)，随机访问 O(n)
- 防御性副本：`getAllTasks()` 返回 `new ArrayList<>(tasks)`，防止外部修改内部状态

```java
public class TodoList {
    private final List<String> tasks;

    public TodoList() { this.tasks = new ArrayList<>(); }

    // 根据参数选择实现类
    public TodoList(boolean useLinkedList) {
        this.tasks = useLinkedList ? new LinkedList<>() : new ArrayList<>();
    }

    public void addTask(String task) {
        Objects.requireNonNull(task);
        tasks.add(task);                    // 末尾追加
    }

    public void insertTask(int index, String task) {
        Objects.requireNonNull(task);
        tasks.add(index, task);             // 指定位置插入
    }

    public boolean removeTask(String task) { return tasks.remove(task); }  // 按值删除
    public String removeTaskAt(int index) { return tasks.remove(index); }  // 按索引删除
    public String getTask(int index) { return tasks.get(index); }
    public String replaceTask(int index, String newTask) { return tasks.set(index, newTask); }
    public int size() { return tasks.size(); }

    public boolean containsTask(String task) {
        if (task == null) return false;      // 防止 NPE
        return tasks.contains(task);
    }

    public List<String> getAllTasks() {
        return new ArrayList<>(tasks);       // 防御性副本！
    }
}
```

### 5.2 Set — HashSet vs TreeSet

**知识点：**
- `HashSet`：基于哈希表，O(1) 查找，**无序**（需要 `equals` + `hashCode`）
- `TreeSet`：基于红黑树，O(log n) 查找，**有序**（需要实现 `Comparable` 或传 `Comparator`）
- 双存储：同时维护两个 Set，利用各自优势

```java
public class TagRegistry {
    private final Set<Tag> hashTags;   // 用于快速查找（O(1)）
    private final Set<Tag> treeTags;   // 用于有序迭代

    public TagRegistry() {
        this.hashTags = new HashSet<>();
        this.treeTags = new TreeSet<>();
    }

    public boolean addTag(Tag tag) {
        Objects.requireNonNull(tag);
        boolean added = hashTags.add(tag);  // 以 hashSet 为主，避免重复
        treeTags.add(tag);
        return added;  // 返回是否是新添加的
    }

    public boolean contains(Tag tag) {
        if (tag == null) return false;
        return hashTags.contains(tag);     // 用 HashSet 查找更快
    }

    public Set<Tag> getTagsUnordered() { return new HashSet<>(hashTags); }  // 防御性副本
    public Set<Tag> getTagsSorted() { return new TreeSet<>(treeTags); }      // 防御性副本

    public void mergeFrom(TagRegistry other) {
        Objects.requireNonNull(other);
        for (Tag t : other.hashTags) { addTag(t); }  // 遍历合并
    }
}
```

### 5.3 Map — HashMap

**知识点：**
- `HashMap`：key → value 映射，O(1) 查找
- `map.merge(key, 1, Integer::sum)`：key 存在则用函数合并，不存在则用初始值 1
- `map.getOrDefault(key, 0)`：key 不存在返回默认值
- `entrySet()`：遍历所有键值对

```java
public class WordCounter {
    private final Map<String, Integer> counts;

    public WordCounter() { this.counts = new HashMap<>(); }

    public void addWord(String word) {
        Objects.requireNonNull(word);
        String normalized = word.trim().toLowerCase();  // 规范化
        if (normalized.isEmpty()) return;               // 跳过空字符串
        counts.merge(normalized, 1, Integer::sum);      // 存在则+1，不存在则设为1
    }

    public int getCount(String word) {
        if (word == null) return 0;
        return counts.getOrDefault(word.trim().toLowerCase(), 0);
    }

    public Map<String, Integer> getAllCounts() {
        return new HashMap<>(counts);  // 防御性副本
    }

    public void mergeFrom(WordCounter other) {
        Objects.requireNonNull(other);
        for (Map.Entry<String, Integer> e : other.counts.entrySet()) {
            counts.merge(e.getKey(), e.getValue(), Integer::sum);
        }
    }
}
```

### 5.4 防御性副本 vs 视图 (Copies vs Views)

**知识点：**
- **防御性副本** `new ArrayList<>(source)`：独立的新列表，修改互不影响
- **视图** `list.subList(from, to)`：底层共享，修改视图 = 修改原列表
- 视图的典型用途：通过视图的 `remove(index)` 来删除原列表中的元素

```java
public class Roster {
    private List<String> names;

    public Roster(List<String> names) {
        Objects.requireNonNull(names);
        this.names = new ArrayList<>(names);  // 防御性副本：防止外部修改
    }

    // 返回防御性副本：调用者修改返回值不影响内部
    public List<String> getAllNames() { return new ArrayList<>(names); }

    // 返回视图：修改这个视图会直接修改 names！
    public List<String> firstNView(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        return names.subList(0, Math.min(n, names.size()));  // 视图
    }

    // 返回副本：修改不影响内部
    public List<String> firstNCopy(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        return new ArrayList<>(names.subList(0, Math.min(n, names.size())));  // 副本
    }

    // 通过视图删除元素（视图修改 = 修改原 names）
    public void removeViaView(int index) {
        if (index < 0) throw new IllegalArgumentException();
        List<String> view = names.subList(0, names.size());
        view.remove(index);  // 等效于 names.remove(index)
    }
}
```

### 5.5 Collections 工具类

```java
// 排序
Collections.sort(list);                          // 自然顺序升序
Collections.sort(list, Collections.reverseOrder()); // 降序
Collections.sort(list, comparator);              // 自定义

// 查找
Collections.max(list);   // 最大值（列表不能为空）
Collections.min(list);   // 最小值
Collections.frequency(list, value);  // 某值出现次数
Collections.binarySearch(sortedList, value);  // 二分查找（需已排序，>= 0 表示找到）

// 修改
Collections.replaceAll(list, oldVal, newVal);  // 替换所有旧值
Collections.shuffle(list);                     // 随机打乱
Collections.swap(list, i, j);                  // 交换两个元素

// 示例
public static List<Integer> sortAscending(List<Integer> scores) {
    Objects.requireNonNull(scores);
    List<Integer> copy = new ArrayList<>(scores);
    Collections.sort(copy);
    return copy;
}

public static int indexOfSorted(List<Integer> sorted, int value) {
    Objects.requireNonNull(sorted);
    return Collections.binarySearch(sorted, value);  // 返回索引或负数
}
```

### 5.6 受检异常 (Checked Exceptions)

**知识点：**
- 受检异常：继承 `Exception`（非 `RuntimeException`），调用方必须处理（try-catch 或 throws 声明）
- 非受检异常：继承 `RuntimeException`，不强制处理
- `throws` 关键字：方法签名中声明可能抛出的受检异常

```java
// 自定义受检异常
public class InvalidMarkException extends Exception {
    public InvalidMarkException() { super(); }
    public InvalidMarkException(String message) { super(message); }
}

// 使用受检异常
public class MarkValidator {
    public static void validateMark(int mark) throws InvalidMarkException {
        if (mark < 0 || mark > 100) {
            throw new InvalidMarkException("Mark must be between 0 and 100, was: " + mark);
        }
    }
}

// 调用方必须处理：
// try { MarkValidator.validateMark(mark); } catch (InvalidMarkException e) { ... }
// 或在方法签名加 throws InvalidMarkException
```

### 5.7 异常处理 (Exception Handling)

**知识点：**
- `try-catch`：捕获并处理异常
- 捕获后可返回 `null` 或默认值，而不是让异常传播
- `ArithmeticException`：算术异常（如除以零），属于 `RuntimeException`

```java
public final class SafeDivider {
    // 抛出非受检异常
    public static int divide(int a, int b) {
        if (b == 0) throw new ArithmeticException("Division by zero");
        return a / b;
    }

    // 捕获并安全处理：返回 null 而不是抛出异常
    public static Integer safeDivide(int a, int b) {
        try {
            return divide(a, b);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
            return null;  // 返回 null 表示无结果
        }
    }
}
```

---

## 6. Lab Week6 — TimetableEntry

### 知识点

- 构造器中通过 setter 方法进行验证（不直接赋值），复用验证逻辑
- 非法值时 setter 静默忽略（不抛异常），字段保持默认值
- `WeekDay.fromInt(day)`：工厂方法将 int 转为枚举

```java
public class TimetableEntry {
    private final Course course;
    private final Student student;
    private WeekDay day = WeekDay.SUN;  // 默认值
    private int start = 0;

    public TimetableEntry(Course course, Student student, int day, int start) {
        this.course = Objects.requireNonNull(course);
        this.student = Objects.requireNonNull(student);
        setDay(day);    // 通过 setter 验证，非法值则保持默认 SUN
        setStart(start); // 通过 setter 验证，非法值则保持默认 0
    }

    public void setStart(int start) {
        if (start < 7 || start > 20) return;  // 非法则静默忽略
        this.start = start;
    }

    public void setDay(int day) {
        if (day < 1 || day > 7) return;       // 非法则静默忽略
        this.day = WeekDay.fromInt(day);
    }

    public String describe() {
        return student.getFirstName() + " " + student.getLastName()
                + " attends " + course.getCode()
                + " on " + day.toString()
                + " at " + start + ":00";
    }
}
```

---

## 7. Lab Week7 — 成绩系统

### 7.1 抽象父类 Result

**知识点：**
- `protected` 字段让子类访问
- 构造器中条件赋值：`if (isValid(score)) this.score = score`
- `weightedContribution` = (score/maxScore × 100) × weight
- equals 使用 `getClass()` 严格匹配（LabResult 不等于 ExamResult）

```java
public abstract class Result {
    protected final AssessmentSpec spec;
    protected int score = -1;  // 默认 -1 表示无效

    public Result(AssessmentSpec spec, int score) {
        this.spec = Objects.requireNonNull(spec);
        if (isValid(score)) this.score = score;  // 非法分数则 score 保持 -1
    }

    protected boolean isValid(int score) {
        return score >= 0 && score <= spec.maxScore();
    }

    // 加权贡献：(分数/满分 * 100) * 权重
    public double weightedContribution() {
        if (!isValid(score)) return -1.0;
        return ((double) score / spec.maxScore() * 100) * spec.weight();
    }
}
```

### 7.2 子类 ExamResult 和 LabResult

```java
// ExamResult：简单继承，无额外逻辑
public class ExamResult extends Result {
    public ExamResult(AssessmentSpec spec, int score) { super(spec, score); }
}

// LabResult：输入是 10 个 lab 分数的数组，需要验证
public class LabResult extends Result {
    private final int[] labMarks;

    public LabResult(AssessmentSpec spec, int[] labMarks) {
        super(spec, validateAndTotal(labMarks));  // 先验证并求和，作为 score 传给父类
        this.labMarks = labMarks;
    }

    // 静态辅助方法：验证 + 求和（在调用 super() 之前执行）
    static int validateAndTotal(int[] labMarks) {
        Objects.requireNonNull(labMarks);
        if (labMarks.length != 10)
            throw new IllegalArgumentException("there must be 10 lab marks");
        for (int m : labMarks) {
            if (m < 0 || m > 10)
                throw new IllegalArgumentException("each lab mark must be between 0 and 10");
        }
        return Grader.sum(labMarks);
    }

    @Override
    public String toString() {
        return super.toString() + " labs=" + Arrays.toString(labMarks);
    }
}
```

### 7.3 Grader（评分报告）

```java
// 成绩等级
public static String computeGrade(double score) {
    if (score >= 80) return "HD";
    if (score >= 70) return "D";
    if (score >= 60) return "CR";
    if (score >= 50) return "P";
    return "F";
}

// 报告：使用 instanceof 模式匹配区分 LabResult 和 ExamResult
public static String report(Student student) {
    StringBuilder sb = new StringBuilder();
    sb.append("Student: ").append(student.getFirstName() + " " + student.getLastName()).append("\n");
    sb.append("Grade: ").append(finalGrade(student)).append("\n");
    for (Result r : student.getResults()) {
        if (r instanceof LabResult lr) {
            sb.append("  - Lab: ").append(lr.getScore())
              .append(" (total of ").append(lr.numberOfLabs()).append(" labs)\n");
        } else if (r instanceof ExamResult er) {
            sb.append("  - Exam: ").append(er.getScore()).append("\n");
        }
    }
    return sb.toString();
}
```

---

## 8. Lab Week8 — 作业追踪器

### 知识点

- 抽象类 `Assignment`：equals 用 `instanceof Assignment`（允许不同子类按 code 相等）
- `Lab`/`Exercise`：有任何一个任务完成 → 得满分，否则 0
- `Midterm`：直接存储分数

```java
// 抽象父类
public abstract class Assignment {
    private final String code;
    private final double maxMarks;

    public Assignment(String code, double maxMarks) {
        this.code = code; this.maxMarks = maxMarks;
    }

    public abstract double score();

    // 注意：用 instanceof Assignment（不是 getClass()），允许 Lab == Exercise（同 code）
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assignment other)) return false;
        return Objects.equals(code, other.code);
    }

    @Override
    public int hashCode() { return Objects.hash(code); }
}

// Lab/Exercise：任何一个完成则得满分
public class Lab extends Assignment {
    private final boolean[] completedTasks;
    public Lab(String code, double maxMarks, boolean[] completedTasks) {
        super(code, maxMarks);
        this.completedTasks = completedTasks;
    }
    @Override
    public double score() {
        for (boolean done : completedTasks) {
            if (done) return getMaxMarks();  // 有任何一个完成 → 满分
        }
        return 0.0;
    }
}

// Midterm：直接记录分数
public class Midterm extends Assignment {
    private final double mark;
    public Midterm(String code, double maxMarks, double mark) {
        super(code, maxMarks); this.mark = mark;
    }
    @Override
    public double score() { return mark; }
}

// 追踪器
public class AssignmentTracker {
    public static double totalScore(Assignment[] assignments) {
        if (assignments.length == 0) return -1.0;  // 空数组返回 -1
        double total = 0.0;
        for (Assignment a : assignments) total += a.score();
        return total;
    }
}
```

---

## 9. Lab Week10 — Degree Planner

### 知识点

- 接口 `Rule` 带 `default description()`
- `CreditRule`：实现 `Rule`，检查总学分
- Lambda 作为 Rule（`addAdvancedCourseRule`）
- 局部内部类作为 Rule（`addPrerequisiteRule`）
- `Comparator.comparing` + `Arrays.sort` 排序数组

```java
// 接口
public interface Rule {
    boolean isSatisfiedBy(DegreePlanner planner);
    default String description() { return "Unnamed rule."; }
}

// 实现类
public class CreditRule implements Rule {
    private final int requiredCredits;
    public CreditRule(int requiredCredits) { this.requiredCredits = requiredCredits; }

    @Override
    public boolean isSatisfiedBy(DegreePlanner planner) {
        return planner.totalCredits() >= requiredCredits;
    }

    @Override
    public String description() { return "Minimum credits required: " + requiredCredits; }
}

// DegreePlanner 中：
// Lambda 作为 Rule
public void addAdvancedCourseRule() {
    addRule(planner -> {
        int total = 0;
        for (Course c : planner.flattenCourses()) {
            if (c != null && c.getCode().startsWith("COMP8"))
                total += c.getCredit();
        }
        return total >= 24;
    });
}

// 局部内部类作为 Rule
public void addPrerequisiteRule(Course target, Course prerequisite) {
    class PrerequisiteRule implements Rule {
        @Override
        public boolean isSatisfiedBy(DegreePlanner planner) {
            if (!planner.containsCourseCode(target.getCode())) return true;
            return planner.containsCourseCode(prerequisite.getCode());
        }
        @Override
        public String description() {
            return "Prerequisite: " + prerequisite.getCode() + " required for " + target.getCode();
        }
    }
    addRule(new PrerequisiteRule());
}

// 验证：返回所有未满足规则的描述
public String[] validate() {
    String[] tmp = new String[ruleCount];
    int n = 0;
    for (int i = 0; i < ruleCount; i++) {
        if (!rules[i].isSatisfiedBy(this)) tmp[n++] = rules[i].description();
    }
    return Arrays.copyOf(tmp, n);
}

// 排序（Arrays.sort + Comparator）
public Course[] coursesSortedByCode() {
    Course[] all = flattenCourses();
    Arrays.sort(all, Comparator.comparing(Course::getCode));
    return all;
}
```

---

## 10. Lab Week11 — 泛型与通配符

### 10.1 泛型类 Record\<T\>

**知识点：**
- `<T>`：类型参数，使用时替换为具体类型
- `T value`：可以是任意类型
- `hasValue()`：判断 value 是否为 null

```java
public class Record<T> {
    private final int studentId;
    private final T value;  // 泛型字段

    public Record(int studentId, T value) {
        this.studentId = studentId;
        this.value = value;
    }

    public int getStudentId() { return studentId; }
    public T getValue() { return value; }
    public boolean hasValue() { return value != null; }
}

// 使用
Record<String> r1 = new Record<>(1, "hello");
Record<Integer> r2 = new Record<>(2, 42);
```

### 10.2 泛型方法 + 通配符

**知识点：**
- `<T> List<T> method()`：泛型方法
- `List<? super Integer>`（下界通配符）：可以 add Integer，适合"消费者"
- `List<? extends T>`（上界通配符）：可以 get T，适合"生产者"，不能 add
- **PECS 原则**：Producer Extends, Consumer Super
- `Class<T> type`：运行时类型，`type.isInstance(obj)` 检查，`type.cast(obj)` 转型

```java
public class StudentDirectory {
    // 下界通配符：List<? super Integer> 可以是 List<Integer>, List<Number>, List<Object>
    // 适合"写入"操作（Consumer Super）
    public void exportIdsTo(List<? super Integer> out) {
        Objects.requireNonNull(out);
        for (Student s : all) out.add(s.getId());  // 可以 add Integer
    }

    // 泛型方法 + 运行时类型检查
    public <T> List<T> findAllOfType(Class<T> type) {
        Objects.requireNonNull(type);
        List<T> result = new ArrayList<>();
        for (Student s : all) {
            if (type.isInstance(s)) {        // 等价于 s instanceof T（运行时）
                result.add(type.cast(s));    // 安全转型
            }
        }
        return result;
    }
}

public class DirectoryUtils {
    // 泛型方法：返回列表第一个元素，空/null 返回 null
    public static <T> T firstOrNull(List<T> list) {
        if (list == null || list.isEmpty()) return null;
        return list.get(0);
    }

    // 上界 + 下界通配符：src 是生产者（? extends T），dst 是消费者（? super T）
    public static <T> void copyAll(List<? extends T> src, List<? super T> dst) {
        if (!dst.isEmpty()) dst.clear();
        for (T item : src) dst.add(item);
    }

    // 泛型 swap
    public static <T> void swap(List<T> list, int i, int j) {
        if (list == null) return;
        int n = list.size();
        if (i < 0 || j < 0 || i >= n || j >= n) return;
        Collections.swap(list, i, j);
    }
}
```

---

## 快速参考：常见模式

### 防御性编程
```java
Objects.requireNonNull(param, "message");  // 防止 null 参数
if (list.isEmpty()) throw new IllegalArgumentException();
```

### 防御性副本
```java
// 构造器中
this.list = new ArrayList<>(input);

// getter 中
public List<T> getList() { return new ArrayList<>(list); }
```

### Map.merge 计数
```java
map.merge(key, 1, Integer::sum);  // 存在则 +1，不存在则初始化为 1
```

### Comparator 链
```java
Comparator.comparingInt(Person::getAge)
          .thenComparing(Person::getName)
          .reversed()
```

### 异常层次
```
Throwable
├── Error（不处理）
└── Exception
    ├── RuntimeException（非受检，不需要 throws）
    │   ├── NullPointerException
    │   ├── IllegalArgumentException
    │   ├── ArithmeticException
    │   └── ClassCastException
    └── 受检异常（必须 throws 声明或 try-catch）
        └── InvalidMarkException（自定义）
```

### instanceof vs getClass()
```java
// getClass()：严格类型匹配（子类 ≠ 父类）
// 用于：equals 通常用这个，防止子类互相相等

// instanceof：包含子类（子类 is-a 父类）
// 用于：Assignment.equals 中，Lab 和 Exercise 同 code 视为相等
if (!(o instanceof Assignment other)) return false;
```

---

*总结完毕。涵盖 COMP7710 List04–List08 所有 Exercises 及 Week6–Week11 所有 Labs 的核心知识点与代码实现。*
