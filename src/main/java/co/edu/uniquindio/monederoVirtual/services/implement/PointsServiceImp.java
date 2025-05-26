package co.edu.uniquindio.monederoVirtual.services.implement;
import co.edu.uniquindio.monederoVirtual.model.Customer;
import co.edu.uniquindio.monederoVirtual.model.Rank;
import co.edu.uniquindio.monederoVirtual.model.Transaction;
import co.edu.uniquindio.monederoVirtual.model.TransactionType;
import co.edu.uniquindio.monederoVirtual.services.PointService;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.TreeSet;


@Service
public class PointsServiceImp implements PointService {


    private Hashtable<String, Integer> customerPoints;


    private TreeSet<CustomerPointsNode> pointsRanking = new TreeSet<CustomerPointsNode>();


    private static final int DEPOSIT_POINTS_PER_100 = 1;
    private static final int WITHDRAWAL_POINTS_PER_100 = 2;
    private static final int TRANSFER_POINTS_PER_100 = 3;
    private static final double POINTS_THRESHOLD = 100.0;


    public PointsServiceImp() {
        this.customerPoints = new Hashtable<>();
        this.pointsRanking = new TreeSet<CustomerPointsNode>();
    }

   @Override
    public int createPointsTransaction(Customer customer, Transaction transaction) {
        if (customer == null || transaction == null) {
            throw new IllegalArgumentException("Cliente y transacción no pueden ser nulos");
        }


        int pointsEarned = calculatePointsByTransactionType(
                transaction.getType(),
                transaction.getAmount()
        );

        if (pointsEarned > 0) {

            String clientId = customer.getId();
            Integer currentPoints = customerPoints.get(clientId);
            int currentPointsInt = (currentPoints != null) ? currentPoints : 0;


            int newTotalPoints = currentPointsInt + pointsEarned;
            customerPoints.put(clientId, newTotalPoints);


            updatePointsRanking(customer, currentPointsInt, newTotalPoints);

            System.out.println("Cliente " + customer.getName() + " ganó " + pointsEarned +
                    " puntos. Total: " + newTotalPoints + " puntos");
        }

        return pointsEarned;
    }
    @Override
    public boolean redeemPoints(String customerId, int pointsToRedeem) {
        Integer currentPoints = customerPoints.get(customerId);
        if (currentPoints == null || currentPoints < pointsToRedeem) {
            return false;
        }

        int newPoints = currentPoints - pointsToRedeem;
        customerPoints.put(customerId, newPoints);


        System.out.println("Puntos canjeados exitosamente. Puntos restantes: " + newPoints);
        return true;
    }
    private int calculatePointsByTransactionType(TransactionType transactionType, double amount) {
        if (amount <= 0) {
            return 0;
        }

        int units = (int) (amount / POINTS_THRESHOLD);

        switch (transactionType) {
            case DEPOSIT:
                return units * DEPOSIT_POINTS_PER_100;

            case WITHDRAWAL:
                return units * WITHDRAWAL_POINTS_PER_100;

            case TRANSFER:
                return units * TRANSFER_POINTS_PER_100;

            default:
                return 0;
        }
    }

    public int getCustomerPoints(String customerId) {
        Integer points = customerPoints.get(customerId);
        return (points != null) ? points : 0;
    }
    @Override
    public Rank getCustomerRank(String customerId) {
        int points = getCustomerPoints(customerId);
        return determineRank(points);
    }

    private Rank determineRank(int points) {
        if (points > 5000) {
            return Rank.PLATINUM;
        } else if (points > 1000) {
            return Rank.GOLD;
        } else if (points > 500) {
            return Rank.SILVER;
        } else {
            return Rank.BRONZE;
        }
    }
    @Override
    public void recalculatePoints(String customerId, int pointsToSubtract) {
        Integer currentPoints = customerPoints.get(customerId);
        if (currentPoints != null) {
            int newPoints = Math.max(0, currentPoints - pointsToSubtract);
            customerPoints.put(customerId, newPoints);
            System.out.println("Puntos recalculados para cliente " + customerId + ". Nuevos puntos: " + newPoints);
        }
    }

   public void updatePointsRanking(Customer client, int oldPoints, int newPoints) { CustomerPointsNode oldNode = new CustomerPointsNode(client.getId(), client.getName(), oldPoints);
       CustomerPointsNode newNode = new CustomerPointsNode(client.getId(), client.getName(), newPoints);
       pointsRanking.remove(oldNode);  // remove old
       pointsRanking.add(newNode);
   }


    private static class CustomerPointsNode implements Comparable<CustomerPointsNode> {
        private String customerId;
        private String customerName;
        private int points;

        public CustomerPointsNode(String customerId, String clientName, int points) {
            this.customerId = customerId;
            this.customerName = customerName;
            this.points = points;
        }

        @Override
        public int compareTo(CustomerPointsNode other) {

            return Integer.compare(other.points, this.points);
        }


        public String getCustomerId() { return customerId; }
        public String getCustomerName() { return customerName; }
        public int getPoints() { return points; }
    }
}