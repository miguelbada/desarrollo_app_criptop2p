package ar.edu.unq.grupof.desarrollo_app_criptop2p.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    private static UserModel miguel;
    private static UserModel juan;
    @BeforeEach
    void setup() {
        miguel = UserModel
                .builder()
                .name("Miguel")
                .lastName("Bada")
                .username("miguel@gmail.com")
                .doneOperations(0)
                .reputation(0)
                .reputation(100)
                .build();

        juan = UserModel
                .builder()
                .name("Juan")
                .lastName("Gomez")
                .username("juan@hotmail.com")
                .doneOperations(0)
                .reputation(100)
                .build();
    }
    @Test
    void aUserExpressesHisIntentionToBuy() {
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .build();

        assertEquals("miguel@gmail.com", intentionBuy.getUser().getUsername());
        assertEquals(OperationType.BUY, intentionBuy.getType());
    }

    @Test
    void aUserTriesToSelfBuy() {
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.processIntentionTo(intentionBuy);

        assertEquals(StateProcess.ACTIVE, intentionBuy.getStateProcess());
    }

    @Test
    void aUserTriesToSelfSale() {
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.processIntentionTo(intentionSale);

        assertEquals(StateProcess.ACTIVE, intentionSale.getStateProcess());
    }

    @Test
    void aUserTriesToProcessIntentionToBuy() {
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionBuy);

        assertEquals(StateProcess.IN_PROCESS, intentionBuy.getStateProcess());
    }

    @Test
    void aUserTriesToProcessIntentionToSale() {
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);

        assertEquals(StateProcess.IN_PROCESS, intentionSale.getStateProcess());
    }

    @Test
    void aUserTriesToProcessIntentionSaleToMakeATransfer() {
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        juan.makeTransfer(intentionSale);

        assertEquals(StateProcess.MAKE_TRANSFER, intentionSale.getStateProcess());
    }

    @Test
    void otherUserTriesToProcessIntentionSaleToMakeATransfer() {
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        miguel.makeTransfer(intentionSale);

        assertEquals(StateProcess.IN_PROCESS, intentionSale.getStateProcess());
    }

    @Test
    void aUserTriesToProcessIntentionBuyToMakeATransfer() {
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionBuy);
        miguel.makeTransfer(intentionBuy);

        assertEquals(StateProcess.MAKE_TRANSFER, intentionBuy.getStateProcess());
    }

    @Test
    void otherUserTriesToProcessIntentionBuyToMakeATransfer() {
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionBuy);
        juan.makeTransfer(intentionBuy);

        assertEquals(StateProcess.IN_PROCESS, intentionBuy.getStateProcess());
    }

    @Test
    void aUserTriesToProcessIntentionBuyToConfirmReception() {
        Cripto cripto = Cripto.builder().price(100.0).build();
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .cripto(cripto)
                .criptoQuote(100.0)
                .build();

        juan.processIntentionTo(intentionBuy);
        miguel.makeTransfer(intentionBuy);
        juan.confirmReception(intentionBuy);

        assertEquals(StateProcess.PROCESSED, intentionBuy.getStateProcess());
    }

    @Test
    void aOtherUserTriesToProcessIntentionBuyToConfirmReception() {
        Cripto cripto = Cripto.builder().price(100.0).build();
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .cripto(cripto)
                .criptoQuote(100.0)
                .build();

        juan.processIntentionTo(intentionBuy);
        miguel.makeTransfer(intentionBuy);
        miguel.confirmReception(intentionBuy);

        assertEquals(StateProcess.MAKE_TRANSFER, intentionBuy.getStateProcess());
    }

    @Test
    void aUserTriesToProcessIntentionSaleToConfirmReception() {
        Cripto cripto = Cripto.builder().price(100.0).build();
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .cripto(cripto)
                .criptoQuote(100.0)
                .build();

        juan.processIntentionTo(intentionSale);
        juan.makeTransfer(intentionSale);
        miguel.confirmReception(intentionSale);

        assertEquals(StateProcess.PROCESSED, intentionSale.getStateProcess());
    }

    @Test
    void otherUserTriesToProcessIntentionSaleToConfirmReception() {
        Cripto cripto = Cripto.builder().price(100.0).build();
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .cripto(cripto)
                .criptoQuote(100.0)
                .build();

        juan.processIntentionTo(intentionSale);
        juan.makeTransfer(intentionSale);
        juan.confirmReception(intentionSale);

        assertEquals(StateProcess.MAKE_TRANSFER, intentionSale.getStateProcess());
    }

    @Test
    void aOtherUserTriesToProcessIntentionToMakeATransfer() {
        UserModel martin = UserModel.
                builder()
                .name("Martin")
                .lastName("Lacosta")
                .username("martin@hotmail.com")
                .doneOperations(0)
                .reputation(0)
                .build();

        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        martin.makeTransfer(intentionSale);

        assertEquals(StateProcess.IN_PROCESS, intentionSale.getStateProcess());
    }

    //-----------------cancel-------------------
    @Test
    void aUserTriesToCancelIntentionSale() {
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.cancelIntention(intentionSale);

        assertEquals(StateProcess.CANCEL, intentionSale.getStateProcess());
    }

    @Test
    void aUserTriesToCancelIntentionBuy() {
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.cancelIntention(intentionBuy);

        assertEquals(StateProcess.CANCEL, intentionBuy.getStateProcess());
    }

    @Test
    void otherUserTriesToCancelIntentionSaleOfOtherUser() {
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.cancelIntention(intentionSale);

        assertEquals(StateProcess.ACTIVE, intentionSale.getStateProcess());
    }

    @Test
    void otherUserTriesToCancelIntentionBuyOfOtherUser() {
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.cancelIntention(intentionBuy);

        assertEquals(StateProcess.ACTIVE, intentionBuy.getStateProcess());
    }

    @Test
    void aUserTriesToCancelIntentionSaleProcess() {
        Cripto cripto = Cripto.builder().price(100.0).build();
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .cripto(cripto)
                .criptoQuote(100.0)
                .build();

        juan.processIntentionTo(intentionSale);
        juan.makeTransfer(intentionSale);
        miguel.confirmReception(intentionSale);
        miguel.cancelIntention(intentionSale);

        assertEquals(StateProcess.PROCESSED, intentionSale.getStateProcess());
    }

    @Test
    void otherUserTriesToCancelIntentionSaleProcess() {
        Cripto cripto = Cripto.builder().price(100.0).build();
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .cripto(cripto)
                .criptoQuote(100.0)
                .build();

        juan.processIntentionTo(intentionSale);
        juan.makeTransfer(intentionSale);
        miguel.confirmReception(intentionSale);
        juan.cancelIntention(intentionSale);

        assertEquals(StateProcess.PROCESSED, intentionSale.getStateProcess());
    }

    @Test
    void aUserTriesToCancelIntentionBuyProcess() {
        Cripto cripto = Cripto.builder().price(100.0).build();
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .cripto(cripto)
                .criptoQuote(100.0)
                .build();

        juan.processIntentionTo(intentionBuy);
        miguel.makeTransfer(intentionBuy);
        juan.confirmReception(intentionBuy);
        miguel.cancelIntention(intentionBuy);

        assertEquals(StateProcess.PROCESSED, intentionBuy.getStateProcess());
    }

    @Test
    void otherUserTriesToCancelIntentionBuyProcess() {
        Cripto cripto = Cripto.builder().price(100.0).build();
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .cripto(cripto)
                .criptoQuote(100.0)
                .build();

        juan.processIntentionTo(intentionBuy);
        miguel.makeTransfer(intentionBuy);
        juan.confirmReception(intentionBuy);
        juan.cancelIntention(intentionBuy);

        assertEquals(StateProcess.PROCESSED, intentionBuy.getStateProcess());
    }

    //------------------ safePrice ------------------

    @Test
    void isSafePriceTransactionWhit5PercentDifference() {
        Cripto cripto = Cripto.builder().price(100.0).build();
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .cripto(cripto)
                .criptoQuote(105.0).build();

        assertTrue(intentionBuy.isSafePrice());
    }

    @Test
    void isSafePriceTransactionWhitLess5PercentDifference() {
        Cripto cripto = Cripto.builder().price(130.0).build();
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .cripto(cripto)
                .criptoQuote(125.0)
                .build();

        assertTrue(intentionBuy.isSafePrice());
    }

    @Test
    void NotIsSafePriceTransactionWhitMore5PercentDifference() {
        Cripto cripto = Cripto.builder().price(130.0).build();
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .cripto(cripto)
                .criptoQuote(150.0)
                .build();

        assertFalse(intentionBuy.isSafePrice());
    }

}
