package ar.edu.unq.grupof.desarrollo_app_criptop2p.model;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.exception.TransactionNotProcessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {
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

    //----------- addDoneOperations ----------------
    @Test
    void aUserCancelsTheBuyIntentionInProcessAndNoAddDoneOperations() {
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionBuy);
        miguel.cancelIntention(intentionBuy);

        assertEquals(0, miguel.getDoneOperations());
        assertEquals(0, juan.getDoneOperations());
    }

   @Test
    void otherUserCancelsTheBuyIntentionInProcessAndNoAddDoneOperations() {
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionBuy);
        juan.cancelIntention(intentionBuy);

        assertEquals(0, miguel.getDoneOperations());
        assertEquals(0, juan.getDoneOperations());
    }

    @Test
    void aUserCancelsTheSaleIntentionInProcessAndNoAddDoneOperations() {
        Transaction intentionPurchase = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionPurchase);
        miguel.cancelIntention(intentionPurchase);

        assertEquals(0, miguel.getDoneOperations());
        assertEquals(0, juan.getDoneOperations());
    }

    @Test
    void otherUserCancelsOtherSaleIntentionInProcessAndNoAddDoneOperations() {
        Transaction intentionPurchase = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();
        juan.processIntentionTo(intentionPurchase);
        juan.cancelIntention(intentionPurchase);

        assertEquals(0, miguel.getDoneOperations());
        assertEquals(0, juan.getDoneOperations());
    }

    @Test
    void aUserCancelsTheSaleIntentionMakeTransferAndNoAddDoneOperations() {
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        juan.makeTransfer(intentionSale);

        assertEquals(0, miguel.getDoneOperations());
        assertEquals(0, juan.getDoneOperations());
    }

    @Test
    void otherUserCancelsTheSaleIntentionMakeTransferAndNoAddDoneOperations() {
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        miguel.makeTransfer(intentionSale);

        assertEquals(0, miguel.getDoneOperations());
        assertEquals(0, juan.getDoneOperations());
    }

    @Test
    void aUserCancelsTheBuyIntentionMakeTransferAndNoAddDoneOperations() {
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionBuy);
        miguel.makeTransfer(intentionBuy);

        assertEquals(0, miguel.getDoneOperations());
        assertEquals(0, juan.getDoneOperations());
    }

    @Test
    void otherUserCancelsTheBuyIntentionMakeTransferAndNoAddDoneOperations() {
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionBuy);
        juan.makeTransfer(intentionBuy);

        assertEquals(0, miguel.getDoneOperations());
        assertEquals(0, juan.getDoneOperations());
    }

    @Test
    void aUserAcceptTheBuyIntentionMakeTransferAndAddDoneOperations() {
        Cripto cripto = Cripto.builder().price(130.0).build();
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .cripto(cripto)
                .criptoQuote(130.0)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionBuy);
        miguel.makeTransfer(intentionBuy);
        juan.confirmReception(intentionBuy);

        assertEquals(1, miguel.getDoneOperations());
        assertEquals(1, juan.getDoneOperations());
    }

    @Test
    void aUserAcceptTheSaleIntentionMakeTransferAndAddDoneOperations() {
        Cripto cripto = Cripto.builder().price(130.0).build();
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .cripto(cripto)
                .criptoQuote(130.0)
                .build();

        juan.processIntentionTo(intentionSale);
        juan.makeTransfer(intentionSale);
        miguel.confirmReception(intentionSale);

        assertEquals(1, miguel.getDoneOperations());
        assertEquals(1, juan.getDoneOperations());
    }

    // -------------- Reputation --------------------------

    @Test
    void aUserCancelsTheBuyIntentionActiveAndNoAddReputation() {
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.cancelIntention(intentionBuy);

        assertEquals(100, miguel.getReputation());
        assertEquals(100, juan.getReputation());
    }

    @Test
    void otherUserCancelsTheBuyIntentionActiveAndNoAddReputation() {
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.cancelIntention(intentionBuy);

        assertEquals(100, miguel.getReputation());
        assertEquals(100, juan.getReputation());
    }

    @Test
    void aUserCancelsTheSaleIntentionActiveAndNoAddReputation() {
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.cancelIntention(intentionSale);

        assertEquals(100, miguel.getReputation());
        assertEquals(100, juan.getReputation());
    }

    @Test
    void otherUserCancelsTheSaleIntentionActiveAndNoAddReputation() {
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.cancelIntention(intentionSale);

        assertEquals(100, miguel.getReputation());
        assertEquals(100, juan.getReputation());
    }
// stateProcess In_Process
    @Test
    void aUserCancelsTheBuyIntentionInProgressAndDiscountReputation() {
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionBuy);
        miguel.cancelIntention(intentionBuy);

        assertEquals(80, miguel.getReputation());
        assertEquals(100, juan.getReputation());
    }

    @Test
    void otherUserCancelsTheBuyIntentionInProgressAndDiscountReputation() {
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionBuy);
        juan.cancelIntention(intentionBuy);

        assertEquals(100, miguel.getReputation());
        assertEquals(80, juan.getReputation());
    }

    @Test
    void aUserCancelsTheSaleIntentionInProgressAndDiscountReputation() {
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        miguel.cancelIntention(intentionSale);

        assertEquals(80, miguel.getReputation());
        assertEquals(100, juan.getReputation());
    }

    @Test
    void otherUserCancelsTheSaleIntentionInProgressAndDiscountReputation() {
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        juan.cancelIntention(intentionSale);

        assertEquals(100, miguel.getReputation());
        assertEquals(80, juan.getReputation());
    }

    @Test
    void aUserCancelsTheBuyIntentionMakeTransactionAndDiscountReputation() {
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionBuy);
        miguel.makeTransfer(intentionBuy);
        miguel.cancelIntention(intentionBuy);

        assertEquals(80, miguel.getReputation());
        assertEquals(100, juan.getReputation());
    }

    @Test
    void otherUserCancelsTheBuyIntentionMakeTransactionAndDiscountReputation() {
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionBuy);
        miguel.makeTransfer(intentionBuy);
        juan.cancelIntention(intentionBuy);

        assertEquals(100, miguel.getReputation());
        assertEquals(80, juan.getReputation());
    }

    @Test
    void aUserCancelsTheBuyIntentionMakeTransactionAndDiscountReputationToZero() {
        miguel.setReputation(10);
        Transaction intentionBuy = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .user(miguel)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionBuy);
        miguel.makeTransfer(intentionBuy);
        miguel.cancelIntention(intentionBuy);

        assertEquals(0, miguel.getReputation());
        assertEquals(100, juan.getReputation());
    }

    // --------------- AutoCancel------------------------
    //---------SALE--------------
    @Test
    void aUserAcceptTheSaleIntentionMakeTransferAndNotAutoCancelTheTransactionForDiffToPricesTo5Percent() {
        Cripto cripto = Cripto.builder().price(100.0).build();
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .cripto(cripto)
                .user(miguel)
                .criptoQuantity(1.0)
                .criptoQuote(105.0)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        juan.makeTransfer(intentionSale);
        miguel.confirmReception(intentionSale);

        assertEquals(StateProcess.PROCESSED, intentionSale.getStateProcess());
    }

    @Test
    void aUserAcceptTheSaleIntentionMakeTransferAndNotAutoCancelTheTransactionForDiffToPricesToLess5Percent() {
        Cripto cripto = Cripto.builder().price(130.0).build();
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .cripto(cripto)
                .user(miguel)
                .criptoQuantity(1.0)
                .criptoQuote(125.0)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        juan.makeTransfer(intentionSale);
        miguel.confirmReception(intentionSale);

        assertEquals(StateProcess.PROCESSED, intentionSale.getStateProcess());
    }

    @Test
    void aUserAcceptTheSaleIntentionMakeTransferAndNotAutoCancelTheTransactionForDiffToPricesToMore5Percent() {
        Cripto cripto = Cripto.builder().price(130.0).build();
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .cripto(cripto)
                .user(miguel)
                .criptoQuantity(0.5)
                .criptoQuote(83.0)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        juan.makeTransfer(intentionSale);
        miguel.confirmReception(intentionSale);

        Throwable exception = assertThrows(TransactionNotProcessException.class, () -> {
            throw new TransactionNotProcessException("the transaction is canceled due to price difference");
        });

        assertEquals(StateProcess.CANCEL, intentionSale.getStateProcess());
        assertEquals("the transaction is canceled due to price difference", exception.getMessage());
    }

    //--------------BUY---------------

    @Test
    void aUserAcceptTheBuyIntentionMakeTransferAndNotAutoCancelTheTransactionForDiffToPricesTo5Percent() {
        Cripto cripto = Cripto.builder().price(100.0).build();
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .cripto(cripto)
                .user(miguel)
                .criptoQuantity(1.0)
                .criptoQuote(105.0)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        miguel.makeTransfer(intentionSale);
        juan.confirmReception(intentionSale);

        assertEquals(StateProcess.PROCESSED, intentionSale.getStateProcess());
    }

    @Test
    void aUserAcceptTheBuyIntentionMakeTransferAndNotAutoCancelTheTransactionForDiffToPricesToLess5Percent() {
        Cripto cripto = Cripto.builder().price(130.0).build();
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .cripto(cripto)
                .user(miguel)
                .criptoQuantity(1.0)
                .criptoQuote(125.0)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        miguel.makeTransfer(intentionSale);
        juan.confirmReception(intentionSale);

        assertEquals(StateProcess.PROCESSED, intentionSale.getStateProcess());
    }

    @Test
    void aUserAcceptTheBuyIntentionMakeTransferAndAutoCancelTheTransactionForDiffToPricesToMore5Percent() {
        Cripto cripto = Cripto.builder().price(130.0).build();
        Transaction intentionSale = Transaction
                .builder()
                .dateTime(LocalDateTime.now())
                .cripto(cripto)
                .user(miguel)
                .criptoQuantity(1.0)
                .criptoQuote(83.0)
                .type(OperationType.BUY)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        miguel.makeTransfer(intentionSale);
        juan.confirmReception(intentionSale);

        Throwable exception = assertThrows(TransactionNotProcessException.class, () -> {
            throw new TransactionNotProcessException("the transaction is canceled due to price difference");
        });

        assertEquals(StateProcess.CANCEL, intentionSale.getStateProcess());
        assertEquals("the transaction is canceled due to price difference", exception.getMessage());
    }

}
