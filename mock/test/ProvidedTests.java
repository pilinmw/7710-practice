import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.testng.AssertJUnit.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 提供的 basic test cases（和真实考试一样：通过这些通常能拿不少分）。
 * 评分时还会用额外的隐藏测试。仔细读测试有助于理解题目要求。
 *
 * 注意：这些测试在你完成实现前会失败——这是正常的。
 */
public class ProvidedTests {

    // ===================== Q1: Student =====================

    @Test
    void q1_basic() {
        Student s = new Student("Alice");
        assertEquals("Alice", s.getName());
        s.addGrade(80);
        s.addGrade(90);
        assertEquals(85.0, s.average(), 0.001);
    }

    @Test
    void q1_nullNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Student(null));
    }

    @Test
    void q1_invalidGradeThrows() {
        Student s = new Student("Bob");
        assertThrows(IllegalArgumentException.class, () -> s.addGrade(101));
        assertThrows(IllegalArgumentException.class, () -> s.addGrade(-1));
    }

    @Test
    void q1_defensiveCopy() {
        Student s = new Student("Carol");
        s.addGrade(50);
        List<Integer> grades = s.getGrades();
        grades.add(999); // 修改返回的 list
        assertEquals(1, s.getGrades().size()); // 内部不受影响
    }

    @Test
    void q1_emptyAverage() {
        Student s = new Student("Dave");
        assertEquals(0.0, s.average(), 0.001);
    }

    // ===================== Q2: Shape =====================

    @Test
    void q2_circleArea() {
        Circle c = new Circle(2.0);
        assertEquals(Math.PI * 4, c.area(), 0.001);
    }

    @Test
    void q2_rectangleArea() {
        Rectangle r = new Rectangle(3.0, 4.0);
        assertEquals(12.0, r.area(), 0.001);
    }

    @Test
    void q2_polymorphicDescribe() {
        Shape s = new Rectangle(2.0, 5.0);
        assertEquals("This shape has area 10.00", s.describe());
    }

    @Test
    void q2_negativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Circle(-1));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(-1, 2));
    }

    // ===================== Q3: Employee / EmployeeService =====================

    @Test
    void q3_sortBySalaryDesc() {
        List<Employee> list = new ArrayList<>(Arrays.asList(
                new Employee("A", 5000),
                new Employee("B", 8000),
                new Employee("C", 6000)));
        EmployeeService svc = new EmployeeService();
        List<Employee> sorted = svc.sortBySalaryDesc(list);
        assertEquals("B", sorted.get(0).getName());
        assertEquals("C", sorted.get(1).getName());
        assertEquals("A", sorted.get(2).getName());
        // 原 list 不能被修改
        assertEquals("A", list.get(0).getName());
    }

    @Test
    void q3_sortByNameThenSalary() {
        List<Employee> list = new ArrayList<>(Arrays.asList(
                new Employee("Tom", 9000),
                new Employee("Amy", 7000),
                new Employee("Tom", 3000)));
        EmployeeService svc = new EmployeeService();
        List<Employee> sorted = svc.sortByNameThenSalary(list);
        assertEquals("Amy", sorted.get(0).getName());
        assertEquals("Tom", sorted.get(1).getName());
        assertEquals(3000, sorted.get(1).getSalary(), 0.001); // Tom 中 salary 小的在前
        assertEquals(9000, sorted.get(2).getSalary(), 0.001);
    }

    // ===================== Q4: Point / PointCounter =====================

    @Test
    void q4_equals() {
        assertEquals(new Point(1, 2), new Point(1, 2));
        assertNotEquals(new Point(1, 2), new Point(2, 1));
        assertNotEquals(new Point(1, 2), null);
        assertNotEquals(new Point(1, 2), "not a point");
    }

    @Test
    void q4_hashCodeConsistent() {
        assertEquals(new Point(3, 4).hashCode(), new Point(3, 4).hashCode());
    }

    @Test
    void q4_countUnique() {
        List<Point> pts = Arrays.asList(
                new Point(1, 1), new Point(1, 1),
                new Point(2, 2), new Point(3, 3), new Point(2, 2));
        assertEquals(3, new PointCounter().countUnique(pts));
    }

    // ===================== Q5: Box / BoxUtils =====================

    @Test
    void q5_box() {
        Box<String> b = new Box<>("hello");
        assertEquals("hello", b.get());
        assertFalse(b.isEmpty());
        b.set(null);
        assertTrue(b.isEmpty());
    }

    @Test
    void q5_sumOfNumbers() {
        assertEquals(6.0, BoxUtils.sumOfNumbers(Arrays.asList(1, 2, 3)), 0.001);
        assertEquals(7.5, BoxUtils.sumOfNumbers(Arrays.asList(2.5, 5.0)), 0.001);
    }

    @Test
    void q5_firstOrNull() {
        assertEquals("x", BoxUtils.firstOrNull(new Box<>("x")));
        assertNull(BoxUtils.firstOrNull(new Box<String>(null)));
    }

    // ===================== Q6: ScoreParser =====================

    @Test
    void q6_parseScoreValid() throws InvalidScoreException {
        assertEquals(85, new ScoreParser().parseScore("85"));
    }

    @Test
    void q6_parseScoreNotNumber() {
        InvalidScoreException ex = assertThrows(InvalidScoreException.class,
                () -> new ScoreParser().parseScore("abc"));
        assertEquals("Not a number: abc", ex.getMessage());
    }

    @Test
    void q6_parseScoreOutOfRange() {
        InvalidScoreException ex = assertThrows(InvalidScoreException.class,
                () -> new ScoreParser().parseScore("150"));
        assertEquals("Out of range: 150", ex.getMessage());
    }

    @Test
    void q6_parseAllSkipsInvalid() {
        List<String> inputs = Arrays.asList("10", "abc", "200", "50");
        List<Integer> result = new ScoreParser().parseAll(inputs);
        assertEquals(Arrays.asList(10, 50), result);
    }

    @Test
    void q6_countValid() {
        List<String> inputs = Arrays.asList("10", "abc", "200", "50", "0", "100");
        assertEquals(4, new ScoreParser().countValid(inputs));
    }
}
