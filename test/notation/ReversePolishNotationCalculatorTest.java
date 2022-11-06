package notation;

import delivery.TransportType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import java.lang.NoSuchMethodException;

public class ReversePolishNotationCalculatorTest {

    private final ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
    private static Method method;

    @BeforeAll
    private static void getIsOperationMethod() throws NoSuchMethodException {
        method = ReversePolishNotationCalculator.class.getDeclaredMethod("isOperation", String.class);
        method.setAccessible(true);
    }

    @Test
    public void shouldCalculateMultiplication() {
        try {
            boolean result = (boolean) method.invoke(calculator, "*");
            Assertions.assertTrue(result);
        } catch (IllegalStateException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void shouldCalculateSubtraction() {
        try {
            boolean result = (boolean) method.invoke(calculator, "-");
            Assertions.assertTrue(result);
        } catch (IllegalStateException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void shouldCalculateAddition() {
        try {
            boolean result = (boolean) method.invoke(calculator, "+");
            Assertions.assertTrue(result);
        } catch (IllegalStateException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void shouldCalculateNegativeNumber() {
        int resultAdd = calculator.calculatePolishNotation("-3 2 +");
        int resultMult = calculator.calculatePolishNotation("5 -2 *");
        int resultSub = calculator.calculatePolishNotation("-7 2 -");
        Assertions.assertEquals(-1, resultAdd);
        Assertions.assertEquals(-10, resultMult);
        Assertions.assertEquals(-9, resultSub);
    }

    @Test
    public void shouldCalculatePositiveNumber() {
        int resultAdd = calculator.calculatePolishNotation("3 2 +");
        int resultMult = calculator.calculatePolishNotation("3 2 *");
        int resultSub = calculator.calculatePolishNotation("3 2 -");
        Assertions.assertEquals(5, resultAdd);
        Assertions.assertEquals(6, resultMult);
        Assertions.assertEquals(1, resultSub);
    }

    @Test
    public void CheckAnyNumberOfSpaces() {
        int result = calculator.calculatePolishNotation(" 3     2   +");
        Assertions.assertEquals(5, result);
    }

    private Executable generateExecutable() {
        return () -> getIsOperationMethod();
    }
}