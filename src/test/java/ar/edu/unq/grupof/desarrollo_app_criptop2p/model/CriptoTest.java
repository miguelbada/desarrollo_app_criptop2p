package ar.edu.unq.grupof.desarrollo_app_criptop2p.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CriptoTest {
    private static Cripto cripto;

    @BeforeEach
    void setup() {
        cripto = Cripto
                .builder()
                .symbol("BTC")
                .dateTime(LocalDateTime.now().minusHours(25))
                .price(10.0)
                .argentineCurrency(1000.0)
                .historicalPrices(new ArrayList<HistoricalPrice>())
                .build();

        cripto.generateHistoricalPrice();

    }

    @Test
    void getHistoricalPricesReturnAllHistoricalPrice() {
        assertEquals(1, cripto.getHistoricalPrices().size());
    }

    @Test
    void getHistoricalPricesNotReturnHistoricalPriceWhitMinusOf24Hs() {
        assertTrue(cripto.getLas24tHs().isEmpty());
    }

    @Test
    void getHistoricalPricesReturnOneHistoricalPriceWhitMinusOf24Hs() {
        cripto.setPrice(11.0);
        cripto.setDateTime(LocalDateTime.now());
        cripto.generateHistoricalPrice();

        assertEquals(1, cripto.getLas24tHs().size());
    }
}
