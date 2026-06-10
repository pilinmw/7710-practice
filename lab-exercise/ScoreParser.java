import java.util.List;

/**
 * Q6 — ScoreParser，解析分数字符串，演示异常处理。
 *
 * 要求：
 *  1. parseScore(String s) throws InvalidScoreException：
 *       - 把 s 解析为 int 分数。
 *       - 如果 s 不是合法整数（NumberFormatException），抛出 InvalidScoreException，
 *         message 为 "Not a number: " + s。
 *       - 如果分数不在 0–100，抛出 InvalidScoreException，
 *         message 为 "Out of range: " + score。
 *       - 合法则返回该 int。
 *
 *  2. parseAll(List<String> inputs)：
 *       - 逐个解析 inputs 中的字符串。
 *       - 用 try/catch 跳过非法项（解析失败的不计入结果），合法的加入返回的 list。
 *       - 返回所有成功解析的分数 list（顺序与输入一致）。
 *       - 这个方法【不抛】异常（内部 catch 处理）。
 *
 *  3. countValid(List<String> inputs)：
 *       - 返回能成功解析的数量。
 *
 * DO NOT change class names or method signatures.
 */
public class ScoreParser {

    public int parseScore(String s) throws InvalidScoreException {
        // TODO
        return 0;
    }

    public List<String> parseAllAsStrings() {
        // 占位，忽略此方法
        return null;
    }

    public java.util.List<Integer> parseAll(List<String> inputs) {
        // TODO: try/catch 跳过非法项
        return null;
    }

    public int countValid(List<String> inputs) {
        // TODO
        return 0;
    }
}
