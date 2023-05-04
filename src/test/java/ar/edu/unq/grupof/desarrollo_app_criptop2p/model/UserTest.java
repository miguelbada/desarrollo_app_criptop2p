package ar.edu.unq.grupof.desarrollo_app_criptop2p.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    //----------- addDoneOperations ----------------
    @Test
    void aUserCancelsTheBuyIntentionInProcessAndNoAddDoneOperations() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Transaction intentionBuy = Transaction
                .builder()
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
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Transaction intentionBuy = Transaction
                .builder()
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
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Transaction intentionPurchase = Transaction
                .builder()
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
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Transaction intentionPurchase = Transaction
                .builder()
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

        assertEquals(0, miguel.getDoneOperations());
        assertEquals(0, juan.getDoneOperations());
    }

    @Test
    void otherUserCancelsTheSaleIntentionMakeTransferAndNoAddDoneOperations() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).build();
        Transaction intentionSale = Transaction
                .builder()
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

        assertEquals(0, miguel.getDoneOperations());
        assertEquals(0, juan.getDoneOperations());
    }

    @Test
    void otherUserCancelsTheBuyIntentionMakeTransferAndNoAddDoneOperations() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).build();
        Transaction intentionBuy = Transaction
                .builder()
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

        assertEquals(1, miguel.getDoneOperations());
        assertEquals(1, juan.getDoneOperations());
    }

    @Test
    void aUserAcceptTheSaleIntentionMakeTransferAndAddDoneOperations() {
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

        assertEquals(1, miguel.getDoneOperations());
        assertEquals(1, juan.getDoneOperations());
    }

    // -------------- Reputation --------------------------

    @Test
    void aUserCancelsTheBuyIntentionActiveAndNoAddReputation() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Transaction intentionBuy = Transaction
                .builder()
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
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Transaction intentionBuy = Transaction
                .builder()
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
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Transaction intentionSale = Transaction
                .builder()
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
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Transaction intentionSale = Transaction
                .builder()
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
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Transaction intentionBuy = Transaction
                .builder()
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
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Transaction intentionBuy = Transaction
                .builder()
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
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Transaction intentionSale = Transaction
                .builder()
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
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Transaction intentionSale = Transaction
                .builder()
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
        miguel.cancelIntention(intentionBuy);

        assertEquals(80, miguel.getReputation());
        assertEquals(100, juan.getReputation());
    }

    @Test
    void otherUserCancelsTheBuyIntentionMakeTransactionAndDiscountReputation() {
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
        juan.cancelIntention(intentionBuy);

        assertEquals(100, miguel.getReputation());
        assertEquals(80, juan.getReputation());
    }

    @Test
    void aUserCancelsTheBuyIntentionMakeTransactionAndDiscountReputationToZero() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(10).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Transaction intentionBuy = Transaction
                .builder()
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

}
