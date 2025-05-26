package co.edu.uniquindio.monederoVirtual.bussinesLogic;

import co.edu.uniquindio.monederoVirtual.model.Customer;
import co.edu.uniquindio.monederoVirtual.model.Rank;
import co.edu.uniquindio.monederoVirtual.model.Transaction;
import co.edu.uniquindio.monederoVirtual.model.TransactionType;
import co.edu.uniquindio.monederoVirtual.services.implement.PointsServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PointsServiceTest {

    private PointsServiceImp pointsService;
    private Customer testCustomer;

    @BeforeEach
    public void setup() {
        pointsService = new PointsServiceImp();

        testCustomer = new Customer();
        testCustomer.setId("cust-123");
        testCustomer.setName("Cliente Ejemplo");
    }

    @Test
    public void testCreatePointsTransaction_Deposit() {
        Transaction transaction = new Transaction();
        transaction.setAmount(300);
        transaction.setType(TransactionType.DEPOSIT);

        int points = pointsService.createPointsTransaction(testCustomer, transaction);

        assertEquals(3, points); // 300 / 100 = 3 * 1
        assertEquals(3, pointsService.getCustomerPoints("cust-123"));
    }

    @Test
    public void testCreatePointsTransaction_Transfer() {
        Transaction transaction = new Transaction();
        transaction.setAmount(200);
        transaction.setType(TransactionType.TRANSFER);

        int points = pointsService.createPointsTransaction(testCustomer, transaction);

        assertEquals(6, points); // 200 / 100 = 2 * 3
        assertEquals(6, pointsService.getCustomerPoints("cust-123"));
    }

    @Test
    public void testRedeemPoints_Success() {
        // Primero asignar puntos
        Transaction transaction = new Transaction();
        transaction.setAmount(1000); // Will earn 10 DEPOSIT points
        transaction.setType(TransactionType.DEPOSIT);
        pointsService.createPointsTransaction(testCustomer, transaction);

        boolean result = pointsService.redeemPoints(testCustomer.getId(), 5);
        assertTrue(result);
        assertEquals(5, pointsService.getCustomerPoints(testCustomer.getId()));
    }

    @Test
    public void testRedeemPoints_Fail_NotEnoughPoints() {
        boolean result = pointsService.redeemPoints(testCustomer.getId(), 10);
        assertFalse(result);
        assertEquals(0, pointsService.getCustomerPoints(testCustomer.getId()));
    }

    @Test
    public void testGetCustomerRank() {
        // BRONZE: 0–500
        assertEquals(Rank.BRONZE, pointsService.getCustomerRank(testCustomer.getId()));

        // Add points
        Transaction tx = new Transaction();
        tx.setAmount(2000); // 20 points (DEPOSIT)
        tx.setType(TransactionType.TRANSFER);
        pointsService.createPointsTransaction(testCustomer, tx);

        // TRANSFER → 3 pts per 100 → 2000 / 100 = 20 → 20*3 = 60
        assertEquals(60, pointsService.getCustomerPoints(testCustomer.getId()));
        assertEquals(Rank.BRONZE, pointsService.getCustomerRank(testCustomer.getId()));

        // Add more points to go over 1000 (GOLD)
        tx.setAmount(40000); // 400 pts * 3 = 1200
        pointsService.createPointsTransaction(testCustomer, tx);

        assertEquals(Rank.GOLD, pointsService.getCustomerRank(testCustomer.getId()));
    }

    @Test
    public void testRecalculatePoints() {
        Transaction tx = new Transaction();
        tx.setAmount(1000); // 10 points (DEPOSIT)
        tx.setType(TransactionType.DEPOSIT);
        pointsService.createPointsTransaction(testCustomer, tx);

        pointsService.recalculatePoints(testCustomer.getId(), 7);
        assertEquals(3, pointsService.getCustomerPoints(testCustomer.getId()));

        // Recalculate more than current points
        pointsService.recalculatePoints(testCustomer.getId(), 10);
        assertEquals(0, pointsService.getCustomerPoints(testCustomer.getId()));
    }
}