package edu.school21.numbers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NumberWorkerTest {
    public NumberWorker numberWorker;

    @BeforeAll
    public void testingConstructor() throws Exception{
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {13,17,3,5,7,29, 2 })
    void isPrimeForPrimes(int n){
        assertTrue(numberWorker.isPrime(n));
    }

    @ParameterizedTest
    @ValueSource(ints = {4,6,8,12,9, 144, 169})
    void isPrimeForNotPrimes(int n){
        assertFalse(numberWorker.isPrime(n));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, -19})
    void isPrimeForIncorrectNumbers(int n){
        assertThrows(IllegalNumberException.class, ()->{numberWorker.isPrime(n);});
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../../../data.csv")
    void digitSum(int digits, int sum){
        assertEquals(sum, numberWorker.digitSum(digits));
    }
}
