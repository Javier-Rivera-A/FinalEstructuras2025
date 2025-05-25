package co.edu.uniquindio.monederoVirtual.repository;

import co.edu.uniquindio.monederoVirtual.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
