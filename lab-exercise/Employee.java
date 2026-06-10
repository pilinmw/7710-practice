import java.util.List;

/**
 * Q3 — Interface, Comparator & Collections (CR–D)
 *
 * 实现 Employee 类和 EmployeeService 类。
 *
 * 要求：
 *  1. Employee 有 name（String）和 salary（double），都通过 constructor 设置。
 *     提供 getName() 和 getSalary()。
 *  2. EmployeeService.sortBySalaryDesc(List<Employee>) 返回一个【新的】list，
 *     按 salary 从高到低排序。原 list 不能被修改。
 *  3. EmployeeService.sortByNameThenSalary(List<Employee>) 返回一个【新的】list，
 *     先按 name 字母升序，name 相同时按 salary 升序。原 list 不能被修改。
 *
 * 提示：
 *  - 可以用 Comparator.comparing / comparingDouble / thenComparing / reversed。
 *  - 不要直接 sort 传入的 list（会修改原 list）；先复制一份。
 *
 * DO NOT change class names or method signatures.
 */
public class Employee {

    // TODO: fields

    public Employee(String name, double salary) {
        // TODO
    }

    public String getName() {
        return null;
    }

    public double getSalary() {
        return 0.0;
    }
}
