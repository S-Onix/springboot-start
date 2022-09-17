import org.example.Calculator;
import org.example.DollarCalculator;
import org.example.MarketApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DollarCalculatorTest {

    @Mock //mock 객체란? 행위를 검증하기 위해 사용되는 객체(실제로 서비스할 객체를 대신해서 사용하는 용도)
    private MarketApi marketApi;

    @BeforeEach
    public void init(){
        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    public void testHello(){
        System.out.println("hello");
    }

    @Test
    public void mockTest(){
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();


        Calculator calculator = new Calculator(dollarCalculator);

        Assertions.assertEquals(60000, calculator.sum(10,10));
        Assertions.assertEquals(0, calculator.sub(10,10));
    }
}


