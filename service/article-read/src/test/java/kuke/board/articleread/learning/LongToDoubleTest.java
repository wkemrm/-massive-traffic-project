package kuke.board.articleread.learning;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

public class LongToDoubleTest {
    @Test
    void longToDoubleTest() {
        // long은 64qlxmfh wjdtn
        // double은 64비트로 부동소수점
        long longValue = 111_111_111_111_111_111L;
        System.out.println("longVale = " + longValue);
        double doubleValue = longValue;
        System.out.println("doubleValue = " + new BigDecimal(doubleValue).toString());
        long longValue2 = (long) doubleValue;
        System.out.println("longValue2 = " + longValue2);
    }
}
