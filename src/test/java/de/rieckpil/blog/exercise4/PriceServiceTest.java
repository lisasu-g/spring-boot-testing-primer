package de.rieckpil.blog.exercise4;

import de.rieckpil.blog.PriceService;
import de.rieckpil.blog.ProductVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private ProductVerifier mockedProductVerifier;

    @InjectMocks
    private PriceService cut;


    @Test
    void shouldReturnCheapPriceWhenProductIsInStockOfCompetitor() {
        // 仅仅测试这个方法的逻辑,依赖的方法并不需要测试,仅仅模拟即可
        Mockito.when(mockedProductVerifier.isCurrentlyInStockOfCompetitor("AirPods"))
                .thenReturn(true);
        assertEquals(new BigDecimal("99.99"), cut.calculatePrice("AirPods"));
    }

    @Test
    void shouldReturnHigherPriceWhenProductIsInStockOfCompetitor() {
        // 仅仅测试这个方法,其他方法并不需要测试,模拟即可
        Mockito.when(mockedProductVerifier.isCurrentlyInStockOfCompetitor("AirPods"))
                .thenReturn(false);
        assertEquals(new BigDecimal("149.99"), cut.calculatePrice("AirPods"));
    }
}