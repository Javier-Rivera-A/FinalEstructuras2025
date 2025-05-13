package co.edu.uniquindio.monederoVirtual.model;

import java.time.LocalDateTime;

public class Points {
    private String pointsID;
    private int amount;
    private LocalDateTime dateTime;
    private Transaction sourceTransaction;
    private Customer customer;
}
