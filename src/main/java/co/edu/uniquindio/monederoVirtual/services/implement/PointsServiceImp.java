package co.edu.uniquindio.monederoVirtual.services.implement;
import co.edu.uniquindio.monederoVirtual.model.Customer;
import co.edu.uniquindio.monederoVirtual.model.Transaction;
import co.edu.uniquindio.monederoVirtual.model.TransactionType;
import co.edu.uniquindio.monederoVirtual.services.PointService;

import java.util.Hashtable;
import java.util.TreeSet;



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

        // Calcular puntos según el tipo de transacción
        int pointsEarned = calculatePointsByTransactionType(
                transaction.getType(),
                transaction.getAmount()
        );

        if (pointsEarned > 0) {
            // Obtener puntos actuales del cliente
            String clientId = customer.getId();
            Integer currentPoints = customerPoints.get(clientId);
            int currentPointsInt = (currentPoints != null) ? currentPoints : 0;

            // Actualizar puntos totales
            int newTotalPoints = currentPointsInt + pointsEarned;
            customerPoints.put(clientId, newTotalPoints);

            // Actualizar ranking en BST
            updatePointsRanking(customer, currentPointsInt, newTotalPoints);

            System.out.println("Cliente " + customer.getName() + " ganó " + pointsEarned +
                    " puntos. Total: " + newTotalPoints + " puntos");
        }

        return pointsEarned;
    }
    public boolean redeemPoints(String customerId, int pointsToRedeem) {
        Integer currentPoints = customerPoints.get(customerId);
        if (currentPoints == null || currentPoints < pointsToRedeem) {
            return false; // No hay suficientes puntos
        }
        customerPoints.put(customerId, currentPoints - pointsToRedeem);
        // Actualizar el ranking si es necesario
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

   @Override
   public void updatePointsRanking(Customer client, int oldPoints, int newPoints) { CustomerPointsNode oldNode = new CustomerPointsNode(client.getId(), client.getName(), oldPoints);
       CustomerPointsNode newNode = new CustomerPointsNode(client.getId(), client.getName(), newPoints);
       pointsRanking.remove(oldNode);  // remove old
       pointsRanking.add(newNode);
   }

    /**
     * Clase interna para representar un nodo de cliente con puntos en el BST.
     */
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
            // Ordenar por puntos (mayor a menor para ranking)
            return Integer.compare(other.points, this.points);
        }

        // Getters
        public String getCustomerId() { return customerId; }
        public String getCustomerName() { return customerName; }
        public int getPoints() { return points; }
    }
}