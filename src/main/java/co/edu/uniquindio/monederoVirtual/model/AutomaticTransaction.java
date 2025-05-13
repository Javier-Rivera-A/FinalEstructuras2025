package co.edu.uniquindio.monederoVirtual.model;

import java.time.LocalDateTime;

public class AutomaticTransaction {
    private String recipientWallet,originWallet;
    private LocalDateTime scheduledDate;
    private int recurrence;
}
