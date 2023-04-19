package ar.edu.unq.grupof.desarrollo_app_criptop2p.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntentionTest {

    @Test
    void aUserExpressesHisIntentionToBuy() {
        User user = User.builder().name("Miguel").lastName("Bada").build();
        Intention intentionBuy = Intention
                .builder()
                .user(user)
                .type(OperationType.PURCHASE)
                .build();

        assertEquals(intentionBuy.getUser().fullName(), "Miguel Bada");
        assertEquals(intentionBuy.getType(), OperationType.PURCHASE);
    }

    @Test
    void aUserTriesToSelfPurchase() {
        User miguel = User.builder().name("Miguel").lastName("Bada").build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.processIntentionTo(intentionPurchase);

        assertEquals(intentionPurchase.getStateProcess(), StateProcess.ACTIVE);
    }

    @Test
    void aUserTriesToSelfSale() {
        User miguel = User.builder().name("Miguel").lastName("Bada").build();
        Intention intentionSale = Intention
                .builder()
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.processIntentionTo(intentionSale);

        assertEquals(intentionSale.getStateProcess(), StateProcess.ACTIVE);
    }

    @Test
    void aUserTriesToProcessIntentionToPurchase() {
        User miguel = User.builder().name("Miguel").lastName("Bada").build();
        User juan = User.builder().name("Juan").lastName("Gomez").build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionPurchase);

        assertEquals(intentionPurchase.getStateProcess(), StateProcess.IN_PROCESS);
    }

    @Test
    void aUserTriesToProcessIntentionToSale() {
        User miguel = User.builder().name("Miguel").lastName("Bada").build();
        User juan = User.builder().name("Juan").lastName("Gomez").build();
        Intention intentionSale = Intention
                .builder()
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);

        assertEquals(intentionSale.getStateProcess(), StateProcess.IN_PROCESS);
    }

    @Test
    void aUserTriesToProcessIntentionSaleToMakeATransfer() {
        User miguel = User.builder().name("Miguel").lastName("Bada").build();
        User juan = User.builder().name("Juan").lastName("Gomez").build();
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
        User miguel = User.builder().name("Miguel").lastName("Bada").build();
        User juan = User.builder().name("Juan").lastName("Gomez").build();
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
        User miguel = User.builder().name("Miguel").lastName("Bada").build();
        User juan = User.builder().name("Juan").lastName("Gomez").build();
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
        User miguel = User.builder().name("Miguel").lastName("Bada").build();
        User juan = User.builder().name("Juan").lastName("Gomez").build();
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
        User miguel = User.builder().name("Miguel").lastName("Bada").doneOperations(0).build();
        User juan = User.builder().name("Juan").lastName("Gomez").doneOperations(0).build();
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
        User miguel = User.builder().name("Miguel").lastName("Bada").build();
        User juan = User.builder().name("Juan").lastName("Gomez").build();
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
        User miguel = User.builder().name("Miguel").lastName("Bada").doneOperations(0).build();
        User juan = User.builder().name("Juan").lastName("Gomez").doneOperations(0).build();
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
        User miguel = User.builder().name("Miguel").lastName("Bada").build();
        User juan = User.builder().name("Juan").lastName("Gomez").build();
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
        User miguel = User.builder().name("Miguel").lastName("Bada").build();
        User juan = User.builder().name("Juan").lastName("Gomez").build();
        User martin = User.builder().name("Martin").lastName("Lacosta").build();
        Intention intentionSale = Intention
                .builder()
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.processIntentionTo(intentionSale);
        martin.makeTransfer(intentionSale);

        assertEquals(intentionSale.getStateProcess(), StateProcess.IN_PROCESS);
    }

    //-----------------cancel-------------------
    @Test
    void aUserTriesToCancelIntentionSale() {
        User miguel = User.builder().name("Miguel").lastName("Bada").build();
        Intention intentionSale = Intention
                .builder()
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.cancelIntention(intentionSale);

        assertEquals(intentionSale.getStateProcess(), StateProcess.CANCEL);
    }

    @Test
    void aUserTriesToCancelIntentionPurchase() {
        User miguel = User.builder().name("Miguel").lastName("Bada").build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        miguel.cancelIntention(intentionPurchase);

        assertEquals(intentionPurchase.getStateProcess(), StateProcess.CANCEL);
    }

    @Test
    void otherUserTriesToCancelIntentionSaleOfOtherUser() {
        User miguel = User.builder().name("Miguel").lastName("Bada").build();
        User juan = User.builder().name("Juan").lastName("Gomez").build();
        Intention intentionSale = Intention
                .builder()
                .user(miguel)
                .type(OperationType.SALE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.cancelIntention(intentionSale);

        assertEquals(intentionSale.getStateProcess(), StateProcess.ACTIVE);
    }

    @Test
    void otherUserTriesToCancelIntentionPurchaseOfOtherUser() {
        User miguel = User.builder().name("Miguel").lastName("Bada").build();
        User juan = User.builder().name("Juan").lastName("Gomez").build();
        Intention intentionPurchase = Intention
                .builder()
                .user(miguel)
                .type(OperationType.PURCHASE)
                .stateProcess(StateProcess.ACTIVE)
                .build();

        juan.cancelIntention(intentionPurchase);

        assertEquals(intentionPurchase.getStateProcess(), StateProcess.ACTIVE);
    }

    @Test
    void aUserTriesToCancelIntentionSaleProcess() {
        User miguel = User.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        User juan = User.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
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
        User miguel = User.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        User juan = User.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
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
        User miguel = User.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        User juan = User.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
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
        User miguel = User.builder().name("Miguel").lastName("Bada").doneOperations(0).reputation(100).build();
        User juan = User.builder().name("Juan").lastName("Gomez").doneOperations(0).reputation(100).build();
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
