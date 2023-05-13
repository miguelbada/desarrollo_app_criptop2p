package ar.edu.unq.grupof.desarrollo_app_criptop2p.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void aUserExpressesHisIntentionToBuy() {
        UserModel user = UserModel.builder().name("Miguel").lastName("Bada").build();
        Transaction intentionBuy = Transaction
                .builder()
                .user(user)
                .type(OperationType.BUY)
                .build();

        assertEquals("Miguel Bada", intentionBuy.getUser().fullName());
        assertEquals(OperationType.BUY, intentionBuy.getType());
    }

    @Test
    void aUserTriesToSelfBuy() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        Transaction intentionBuy = Transaction
                .builder()
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.processIntentionTo(intentionBuy);

        assertEquals(StateProcess.ACTIVE, intentionBuy.getStateProcess());
    }

    @Test
    void aUserTriesToSelfSale() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        Transaction intentionSale = Transaction
                .builder()
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.processIntentionTo(intentionSale);

        assertEquals(StateProcess.ACTIVE, intentionSale.getStateProcess());
    }

    @Test
    void aUserTriesToProcessIntentionToBuy() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Transaction intentionBuy = Transaction
                .builder()
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionBuy);

        assertEquals(StateProcess.IN_PROCESS, intentionBuy.getStateProcess());
    }

    @Test
    void aUserTriesToProcessIntentionToSale() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Transaction intentionSale = Transaction
                .builder()
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);

        assertEquals(StateProcess.IN_PROCESS, intentionSale.getStateProcess());
    }

    @Test
    void aUserTriesToProcessIntentionSaleToMakeATransfer() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Transaction intentionSale = Transaction
                .builder()
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
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Transaction intentionSale = Transaction
                .builder()
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
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Transaction intentionBuy = Transaction
                .builder()
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
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Transaction intentionBuy = Transaction
                .builder()
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
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).build();
        Transaction intentionBuy = Transaction
                .builder()
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionBuy);
        miguel.makeTransfer(intentionBuy);
        juan.confirmReception(intentionBuy);

        assertEquals(StateProcess.PROCESSED, intentionBuy.getStateProcess());
    }

    @Test
    void aOtherUserTriesToProcessIntentionBuyToConfirmReception() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Transaction intentionBuy = Transaction
                .builder()
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionBuy);
        miguel.makeTransfer(intentionBuy);
        miguel.confirmReception(intentionBuy);

        assertEquals(StateProcess.MAKE_TRANSFER, intentionBuy.getStateProcess());
    }

    @Test
    void aUserTriesToProcessIntentionSaleToConfirmReception() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).build();
        Transaction intentionSale = Transaction
                .builder()
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        juan.makeTransfer(intentionSale);
        miguel.confirmReception(intentionSale);

        assertEquals(StateProcess.PROCESSED, intentionSale.getStateProcess());
    }

    @Test
    void otherUserTriesToProcessIntentionSaleToConfirmReception() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Transaction intentionSale = Transaction
                .builder()
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        juan.makeTransfer(intentionSale);
        juan.confirmReception(intentionSale);

        assertEquals(StateProcess.MAKE_TRANSFER, intentionSale.getStateProcess());
    }

    @Test
    void aOtherUserTriesToProcessIntentionToMakeATransfer() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        UserModel martin = UserModel.builder().name("Martin").lastName("Lacosta").build();
        Transaction intentionSale = Transaction
                .builder()
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
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        Transaction intentionSale = Transaction
                .builder()
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.cancelIntention(intentionSale);

        assertEquals(StateProcess.CANCEL, intentionSale.getStateProcess());
    }

    @Test
    void aUserTriesToCancelIntentionBuy() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        Transaction intentionBuy = Transaction
                .builder()
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.cancelIntention(intentionBuy);

        assertEquals(StateProcess.CANCEL, intentionBuy.getStateProcess());
    }

    @Test
    void otherUserTriesToCancelIntentionSaleOfOtherUser() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Transaction intentionSale = Transaction
                .builder()
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.cancelIntention(intentionSale);

        assertEquals(StateProcess.ACTIVE, intentionSale.getStateProcess());
    }

    @Test
    void otherUserTriesToCancelIntentionBuyOfOtherUser() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Transaction intentionBuy = Transaction
                .builder()
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.cancelIntention(intentionBuy);

        assertEquals(StateProcess.ACTIVE, intentionBuy.getStateProcess());
    }

    @Test
    void aUserTriesToCancelIntentionSaleProcess() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Transaction intentionSale = Transaction
                .builder()
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        juan.makeTransfer(intentionSale);
        miguel.confirmReception(intentionSale);
        miguel.cancelIntention(intentionSale);

        assertEquals(StateProcess.PROCESSED, intentionSale.getStateProcess());
    }

    @Test
    void otherUserTriesToCancelIntentionSaleProcess() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Transaction intentionSale = Transaction
                .builder()
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        juan.makeTransfer(intentionSale);
        miguel.confirmReception(intentionSale);
        juan.cancelIntention(intentionSale);

        assertEquals(StateProcess.PROCESSED, intentionSale.getStateProcess());
    }

    @Test
    void aUserTriesToCancelIntentionBuyProcess() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Transaction intentionBuy = Transaction
                .builder()
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionBuy);
        miguel.makeTransfer(intentionBuy);
        juan.confirmReception(intentionBuy);
        miguel.cancelIntention(intentionBuy);

        assertEquals(StateProcess.PROCESSED, intentionBuy.getStateProcess());
    }

    @Test
    void otherUserTriesToCancelIntentionBuyProcess() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Transaction intentionBuy = Transaction
                .builder()
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
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
        Transaction intentionBuy = Transaction.builder().cripto(cripto).criptoQuantity(1.0).criptoQuote(105.0).build();

        assertTrue(intentionBuy.isSafePrice());
    }

    @Test
    void isSafePriceTransactionWhitLess5PercentDifference() {
        Cripto cripto = Cripto.builder().price(130.0).build();
        Transaction intentionBuy = Transaction.builder().cripto(cripto).criptoQuantity(0.5).criptoQuote(63.0).build();

        assertTrue(intentionBuy.isSafePrice());
    }

    @Test
    void NotisSafePriceTransactionWhitMore5PercentDifference() {
        Cripto cripto = Cripto.builder().price(130.0).build();
        Transaction intentionBuy = Transaction.builder().cripto(cripto).criptoQuantity(0.5).criptoQuote(83.0).build();

        assertFalse(intentionBuy.isSafePrice());
    }

}
