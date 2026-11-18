import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** - Unit test (place in your test package). */
class ProductCalculationsTest {

    @Test
    void GetVat_WithValidData_ReturnsCorrectValue() {
        ProductCalculations calc = new ProductCalculations(3500);
        assertEquals(525.0, calc.GetVat(), 0.001);
    }
}
