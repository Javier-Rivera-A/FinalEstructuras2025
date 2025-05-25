package co.edu.uniquindio.monederoVirtual.repository;

import co.edu.uniquindio.monederoVirtual.mapper.NotificationMapper;
import co.edu.uniquindio.monederoVirtual.model.Customer;
import co.edu.uniquindio.monederoVirtual.model.Notification;
import co.edu.uniquindio.monederoVirtual.model.Rank;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NotificationRepository {

    private final String dataFilePath;
    private List<Notification> notifications;

    public NotificationRepository(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    private void loadData() {
        File file = new File(dataFilePath);
        if (!file.exists()) {
            return; // Si el archivo no existe, se inicializa con una lista vacía
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                this.notifications = (List<Notification>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
            this.notifications = new ArrayList<>();
        }
    }
    public Notification save(Notification notification) {
        if (notification == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }

      notifications.add(notification);

        saveData(); // Guardar cambios en el archivo
        return notification;
    }
    /**
     * Guarda los datos en el archivo
     */
    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {
            oos.writeObject(notifications);
        } catch (IOException e) {

        }
    }

    public List<Notification> findAll() {
        return new ArrayList<>(notifications); // Retorna una copia para evitar modificaciones externas
    }

}