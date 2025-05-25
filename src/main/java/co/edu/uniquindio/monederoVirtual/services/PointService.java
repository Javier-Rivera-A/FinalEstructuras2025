package co.edu.uniquindio.monederoVirtual.services;

import co.edu.uniquindio.monederoVirtual.model.Customer;
import co.edu.uniquindio.monederoVirtual.model.Transaction;

public interface PointService {
    int createPointsTransaction(Customer client, Transaction transaction);

    void updatePointsRanking(Customer client, int oldPoints, int newPoints);
}
