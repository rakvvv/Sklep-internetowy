import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;
import java.sql.SQLException;

public class OpiniaWindow extends Stage {

    private TableView<Opinia> tableView;
    private ComboBox<Klient> cbKlientId;
    private ComboBox<Produkt> cbProduktId;

    public OpiniaWindow() {
        setTitle("Zarządzanie Opiniami");

        Label labelOpinia = new Label("Opinia");
        TextField txtId = new TextField();
        txtId.setPromptText("ID");

        cbKlientId = new ComboBox<>();
        cbKlientId.setPromptText("Klient ID");
        loadKlientIds();

        cbProduktId = new ComboBox<>();
        cbProduktId.setPromptText("Produkt ID");
        loadProduktIds();

        DatePicker dpData = new DatePicker();
        TextField txtOcena = new TextField();
        txtOcena.setPromptText("Ocena");
        TextField txtKomentarz = new TextField();
        txtKomentarz.setPromptText("Komentarz");

        Button btnDodajOpinia = new Button("Dodaj Opinię");
        btnDodajOpinia.setOnAction(e -> {
            try {
                if (cbKlientId.getValue() == null || cbProduktId.getValue() == null || dpData.getValue() == null || txtOcena.getText().isEmpty() || txtKomentarz.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Wszystkie pola muszą być wypełnione.");
                    return;
                }
                int klientId = cbKlientId.getValue().getId();
                int produktId = cbProduktId.getValue().getId();
                Date data = java.sql.Date.valueOf(dpData.getValue());
                int ocena = Integer.parseInt(txtOcena.getText());
                String komentarz = txtKomentarz.getText();
                OpiniaUtil.dodajOpinia(klientId, produktId, data, ocena, komentarz);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Opinia została dodana.");
                updateTable();
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        Button btnAktualizujOpinia = new Button("Aktualizuj Opinię");
        btnAktualizujOpinia.setOnAction(e -> {
            try {
                if (txtId.getText().isEmpty() || cbKlientId.getValue() == null || cbProduktId.getValue() == null || dpData.getValue() == null || txtOcena.getText().isEmpty() || txtKomentarz.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Wszystkie pola muszą być wypełnione.");
                    return;
                }
                int opiniaId = Integer.parseInt(txtId.getText());
                int klientId = cbKlientId.getValue().getId();
                int produktId = cbProduktId.getValue().getId();
                Date data = java.sql.Date.valueOf(dpData.getValue());
                int ocena = Integer.parseInt(txtOcena.getText());
                String komentarz = txtKomentarz.getText();
                OpiniaUtil.aktualizujOpinia(opiniaId, klientId, produktId, data, ocena, komentarz);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Opinia została zaktualizowana.");
                updateTable();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        Button btnUsunOpinia = new Button("Usuń Opinię");
        btnUsunOpinia.setOnAction(e -> {
            try {
                if (txtId.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Pole ID musi być wypełnione.");
                    return;
                }
                int opiniaId = Integer.parseInt(txtId.getText());
                OpiniaUtil.usunOpinia(opiniaId);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Opinia została usunięta.");
                updateTable();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        tableView = new TableView<>();
        TableColumn<Opinia, Integer> columnId = new TableColumn<>("ID");
        columnId.setCellValueFactory(data -> data.getValue().opiniaIdProperty().asObject());
        TableColumn<Opinia, Integer> columnKlientId = new TableColumn<>("Klient ID");
        columnKlientId.setCellValueFactory(data -> data.getValue().klientIdProperty().asObject());
        TableColumn<Opinia, Integer> columnProduktId = new TableColumn<>("Produkt ID");
        columnProduktId.setCellValueFactory(data -> data.getValue().produktIdProperty().asObject());
        TableColumn<Opinia, Date> columnData = new TableColumn<>("Data");
        columnData.setCellValueFactory(data -> data.getValue().dataProperty());
        TableColumn<Opinia, Integer> columnOcena = new TableColumn<>("Ocena");
        columnOcena.setCellValueFactory(data -> data.getValue().ocenaProperty().asObject());
        TableColumn<Opinia, String> columnKomentarz = new TableColumn<>("Komentarz");
        columnKomentarz.setCellValueFactory(data -> data.getValue().komentarzProperty());

        tableView.getColumns().addAll(columnId, columnKlientId, columnProduktId, columnData, columnOcena, columnKomentarz);

        VBox vbox = new VBox(labelOpinia, txtId, cbKlientId, cbProduktId, dpData, txtOcena, txtKomentarz, btnDodajOpinia, btnAktualizujOpinia, btnUsunOpinia, tableView);
        Scene scene = new Scene(vbox, 600, 600);
        setScene(scene);

        updateTable();
    }

    private void loadKlientIds() {
        ObservableList<Klient> klienci = KlientUtil.getAllKlienci();
        cbKlientId.setItems(klienci);
    }

    private void loadProduktIds() {
        ObservableList<Produkt> produkty = ProduktUtil.getAllProdukty();
        cbProduktId.setItems(produkty);
    }

    private void updateTable() {
        ObservableList<Opinia> opinie = OpiniaUtil.getAllOpinie();
        tableView.setItems(opinie);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
