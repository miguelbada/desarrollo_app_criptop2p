package ar.edu.unq.grupof.desarrollo_app_criptop2p.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    //----------- addDoneOperations ----------------
    @Test
    void aUserCancelsThePurchaseIntentionInProcessAndNoAddDoneOperations() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionPurchase);
        miguel.cancelIntention(intentionPurchase);

        assertEquals(0, miguel.getDoneOperations());
        assertEquals(0, juan.getDoneOperations());
    }

    @Test
    void otherUserCancelsThePurchaseIntentionInProcessAndNoAddDoneOperations() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionPurchase);
        juan.cancelIntention(intentionPurchase);

        assertEquals(0, miguel.getDoneOperations());
        assertEquals(0, juan.getDoneOperations());
    }

    @Test
    void aUserCancelsTheSaleIntentionInProcessAndNoAddDoneOperations() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Intention intentionPurchase = Intention
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
        Intention intentionPurchase = Intention
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
        Intention intentionSale = Intention
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
        Intention intentionSale = Intention
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
    void aUserCancelsThePurchaseIntentionMakeTransferAndNoAddDoneOperations() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionPurchase);
        miguel.makeTransfer(intentionPurchase);

        assertEquals(0, miguel.getDoneOperations());
        assertEquals(0, juan.getDoneOperations());
    }

    @Test
    void otherUserCancelsThePurchaseIntentionMakeTransferAndNoAddDoneOperations() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionPurchase);
        juan.makeTransfer(intentionPurchase);

        assertEquals(0, miguel.getDoneOperations());
        assertEquals(0, juan.getDoneOperations());
    }

    @Test
    void aUserAcceptThePurchaseIntentionMakeTransferAndAddDoneOperations() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionPurchase);
        miguel.makeTransfer(intentionPurchase);
        juan.confirmReception(intentionPurchase);

        assertEquals(1, miguel.getDoneOperations());
        assertEquals(1, juan.getDoneOperations());
    }

    @Test
    void aUserAcceptTheSaleIntentionMakeTransferAndAddDoneOperations() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).build();
        Intention intentionSale = Intention
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
    void aUserCancelsThePurchaseIntentionActiveAndNoAddReputation() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.cancelIntention(intentionPurchase);

        assertEquals(100, miguel.getReputation());
        assertEquals(100, juan.getReputation());
    }

    @Test
    void otherUserCancelsThePurchaseIntentionActiveAndNoAddReputation() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.cancelIntention(intentionPurchase);

        assertEquals(100, miguel.getReputation());
        assertEquals(100, juan.getReputation());
    }

    @Test
    void aUserCancelsTheSaleIntentionActiveAndNoAddReputation() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Intention intentionSale = Intention
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
        Intention intentionSale = Intention
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
    void aUserCancelsThePurchaseIntentionInProgressAndDiscountReputation() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionPurchase);
        miguel.cancelIntention(intentionPurchase);

        assertEquals(80, miguel.getReputation());
        assertEquals(100, juan.getReputation());
    }

    @Test
    void otherUserCancelsThePurchaseIntentionInProgressAndDiscountReputation() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionPurchase);
        juan.cancelIntention(intentionPurchase);

        assertEquals(100, miguel.getReputation());
        assertEquals(80, juan.getReputation());
    }

    @Test
    void aUserCancelsTheSaleIntentionInProgressAndDiscountReputation() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Intention intentionSale = Intention
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
        Intention intentionSale = Intention
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
    void aUserCancelsThePurchaseIntentionMakeTransactionAndDiscountReputation() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionPurchase);
        miguel.makeTransfer(intentionPurchase);
        miguel.cancelIntention(intentionPurchase);

        assertEquals(80, miguel.getReputation());
        assertEquals(100, juan.getReputation());
    }

    @Test
    void otherUserCancelsThePurchaseIntentionMakeTransactionAndDiscountReputation() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionPurchase);
        miguel.makeTransfer(intentionPurchase);
        juan.cancelIntention(intentionPurchase);

        assertEquals(100, miguel.getReputation());
        assertEquals(80, juan.getReputation());
    }

}
