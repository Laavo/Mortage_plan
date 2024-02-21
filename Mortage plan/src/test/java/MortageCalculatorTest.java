import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MortgageCalculatorTest {

    @Test
    public void testCalculateMonthlyPayment() {
        Customer customer = new Customer("Test", 100000.0, 5.0, 30);
        double expected = 536.82; // Expected monthly payment
        double actual = MortgageCalculator.calculateMonthlyPayment(customer);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testCalculateMonthlyPaymentThrowsException() {
        Customer customer = new Customer("Test", 100000.0, 5.0, 0); 
        assertThrows(IllegalArgumentException.class, () -> MortgageCalculator.calculateMonthlyPayment(customer));
    }
}