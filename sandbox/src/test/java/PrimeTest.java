import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTest {

    @Test
    public void isPrimeCheck() {
        Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
    }

    @Test
    public void isNoPrimeCheck() {
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
    }
}
