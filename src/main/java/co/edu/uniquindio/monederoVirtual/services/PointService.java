package co.edu.uniquindio.monederoVirtual.services;

import co.edu.uniquindio.monederoVirtual.model.Customer;
import co.edu.uniquindio.monederoVirtual.model.Rank;
import co.edu.uniquindio.monederoVirtual.model.Transaction;

public interface PointService {
    int createPointsTransaction(Customer client, Transaction transaction);

    boolean redeemPoints(String customerId, int pointsToRedeem);

    Rank getCustomerRank(String customerId);

    void recalculatePoints(String customerId, int pointsToSubtract);

    void updatePointsRanking(Customer client, int oldPoints, int newPoints);
}
