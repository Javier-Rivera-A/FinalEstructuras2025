package co.edu.uniquindio.monederoVirtual.dto;

import co.edu.uniquindio.monederoVirtual.model.Customer;
import co.edu.uniquindio.monederoVirtual.model.Transaction;
import co.edu.uniquindio.monederoVirtual.services.implement.PointsServiceImp;

import java.util.Hashtable;
import java.util.TreeSet;

public record PointsTdo(Hashtable<String,Integer> customerPoints, Customer customer, Transaction transaction, int pointsToRedeem) {
}
