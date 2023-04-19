package ar.edu.unq.grupof.desarrollo_app_criptop2p.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntentionTest {

    @Test
    void aUserExpressesHisIntentionToBuy() {
        UserModel user = UserModel.builder().name("Miguel").lastName("Bada").build();
        Intention intentionBuy = Intention
                .builder()
                .user(user)
                .type(OperationType.PURCHASE)
                .build();

        assertEquals("Miguel Bada", intentionBuy.getUser().fullName());
        assertEquals(OperationType.PURCHASE, intentionBuy.getType());
    }

    @Test
    void aUserTriesToSelfPurchase() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.processIntentionTo(intentionPurchase);

        assertEquals(StateProcess.ACTIVE, intentionPurchase.getStateProcess());
    }

    @Test
    void aUserTriesToSelfSale() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        Intention intentionSale = Intention
                .builder()
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.processIntentionTo(intentionSale);

        assertEquals(StateProcess.ACTIVE, intentionSale.getStateProcess());
    }

    @Test
    void aUserTriesToProcessIntentionToPurchase() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionPurchase);

        assertEquals(StateProcess.IN_PROCESS, intentionPurchase.getStateProcess());
    }

    @Test
    void aUserTriesToProcessIntentionToSale() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Intention intentionSale = Intention
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
        Intention intentionSale = Intention
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
        Intention intentionSale = Intention
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
    void aUserTriesToProcessIntentionPurchaseToMakeATransfer() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Intention intentionSale = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        miguel.makeTransfer(intentionSale);

        assertEquals(StateProcess.MAKE_TRANSFER, intentionSale.getStateProcess());
    }

    @Test
    void otherUserTriesToProcessIntentionPurchaseToMakeATransfer() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Intention intentionSale = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        juan.makeTransfer(intentionSale);

        assertEquals(StateProcess.IN_PROCESS, intentionSale.getStateProcess());
    }

    @Test
    void aUserTriesToProcessIntentionPurchaseToConfirmReception() {
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

        assertEquals(StateProcess.PROCESSED, intentionPurchase.getStateProcess());
    }

    @Test
    void aOtherUserTriesToProcessIntentionPurchaseToConfirmReception() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionPurchase);
        miguel.makeTransfer(intentionPurchase);
        miguel.confirmReception(intentionPurchase);

        assertEquals(StateProcess.MAKE_TRANSFER, intentionPurchase.getStateProcess());
    }

    @Test
    void aUserTriesToProcessIntentionSaleToConfirmReception() {
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

        assertEquals(StateProcess.PROCESSED, intentionSale.getStateProcess());
    }

    @Test
    void otherUserTriesToProcessIntentionSaleToConfirmReception() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Intention intentionSale = Intention
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
        Intention intentionSale = Intention
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
        Intention intentionSale = Intention
                .builder()
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.cancelIntention(intentionSale);

        assertEquals(StateProcess.CANCEL, intentionSale.getStateProcess());
    }

    @Test
    void aUserTriesToCancelIntentionPurchase() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.cancelIntention(intentionPurchase);

        assertEquals(StateProcess.CANCEL, intentionPurchase.getStateProcess());
    }

    @Test
    void otherUserTriesToCancelIntentionSaleOfOtherUser() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Intention intentionSale = Intention
                .builder()
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.cancelIntention(intentionSale);

        assertEquals(StateProcess.ACTIVE, intentionSale.getStateProcess());
    }

    @Test
    void otherUserTriesToCancelIntentionPurchaseOfOtherUser() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.cancelIntention(intentionPurchase);

        assertEquals(StateProcess.ACTIVE, intentionPurchase.getStateProcess());
    }

    @Test
    void aUserTriesToCancelIntentionSaleProcess() {
        UserModel miguel = UserModel.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        UserModel juan = UserModel.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
        Intention intentionSale = Intention
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
        Intention intentionSale = Intention
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
    void aUserTriesToCancelIntentionPurchaseProcess() {
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
        juan.confirmReception(intentionPurchase);
        miguel.cancelIntention(intentionPurchase);

        assertEquals(StateProcess.PROCESSED, intentionPurchase.getStateProcess());
    }

    @Test
    void otherUserTriesToCancelIntentionPurchaseProcess() {
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
        juan.confirmReception(intentionPurchase);
        juan.cancelIntention(intentionPurchase);

        assertEquals(StateProcess.PROCESSED, intentionPurchase.getStateProcess());
    }

}
