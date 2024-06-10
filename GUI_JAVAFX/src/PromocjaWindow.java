import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class PromocjaWindow extends Stage {

    private TableView<Promocja> tableView;

    public PromocjaWindow() {
        setTitle("Zarządzanie Promocjami");

        Label labelPromocja = new Label("Promocja");
        TextField txtNazwa = new TextField();
        txtNazwa.setPromptText("Nazwa");
        TextField txtOpis = new TextField();
        txtOpis.setPromptText("Opis");
        DatePicker dpDataRozpoczecia = new DatePicker();
        DatePicker dpDataZakonczenia = new DatePicker();
        TextField txtProcentObnizki = new TextField();
        txtProcentObnizki.setPromptText("Procent Obniżki");

        Button btnDodajPromocje = new Button("Dodaj Promocję");
        btnDodajPromocje.setOnAction(e -> {
            try {
                if (txtNazwa.getText().isEmpty() || txtOpis.getText().isEmpty() || dpDataRozpoczecia.getValue() == null || dpDataZakonczenia.getValue() == null || txtProcentObnizki.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Wszystkie pola muszą być wypełnione.");
                    return;
                }
                String nazwa = txtNazwa.getText();
                String opis = txtOpis.getText();
                LocalDate dataRozpoczecia = dpDataRozpoczecia.getValue();
                LocalDate dataZakonczenia = dpDataZakonczenia.getValue();
                double procentObnizki = Double.parseDouble(txtProcentObnizki.getText());
                PromocjaUtil.dodajPromocje(nazwa, opis, dataRozpoczecia, dataZakonczenia, procentObnizki);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Promocja została dodana.");
                updateTable();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        TextField txtId = new TextField();
        txtId.setPromptText("ID");
        Button btnAktualizujPromocje = new Button("Aktualizuj Promocję");
        btnAktualizujPromocje.setOnAction(e -> {
            try {
                if (txtId.getText().isEmpty() || txtNazwa.getText().isEmpty() || txtOpis.getText().isEmpty() || dpDataRozpoczecia.getValue() == null || dpDataZakonczenia.getValue() == null || txtProcentObnizki.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Wszystkie pola muszą być wypełnione.");
                    return;
                }
                int promocjaId = Integer.parseInt(txtId.getText());
                String nazwa = txtNazwa.getText();
                String opis = txtOpis.getText();
                LocalDate dataRozpoczecia = dpDataRozpoczecia.getValue();
                LocalDate dataZakonczenia = dpDataZakonczenia.getValue();
                double procentObnizki = Double.parseDouble(txtProcentObnizki.getText());
                PromocjaUtil.aktualizujPromocje(promocjaId, nazwa, opis, dataRozpoczecia, dataZakonczenia, procentObnizki);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Promocja została zaktualizowana.");
                updateTable();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        Button btnUsunPromocje = new Button("Usuń Promocję");
        btnUsunPromocje.setOnAction(e -> {
            try {
                if (txtId.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Pole ID musi być wypełnione.");
                    return;
                }
                int promocjaId = Integer.parseInt(txtId.getText());
                PromocjaUtil.usunPromocje(promocjaId);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Promocja została usunięta.");
                updateTable();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        tableView = new TableView<>();
        TableColumn<Promocja, Integer> columnId = new TableColumn<>("ID");
        columnId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        TableColumn<Promocja, String> columnNazwa = new TableColumn<>("Nazwa");
        columnNazwa.setCellValueFactory(data -> data.getValue().nazwaProperty());
        TableColumn<Promocja, String> columnOpis = new TableColumn<>("Opis");
        columnOpis.setCellValueFactory(data -> data.getValue().opisProperty());
        TableColumn<Promocja, LocalDate> columnDataRozpoczecia = new TableColumn<>("Data Rozpoczęcia");
        columnDataRozpoczecia.setCellValueFactory(data -> data.getValue().dataRozpoczeciaProperty());
        TableColumn<Promocja, LocalDate> columnDataZakonczenia = new TableColumn<>("Data Zakończenia");
        columnDataZakonczenia.setCellValueFactory(data -> data.getValue().dataZakonczeniaProperty());
        TableColumn<Promocja, Double> columnProcentObnizki = new TableColumn<>("Procent Obniżki");
        columnProcentObnizki.setCellValueFactory(data -> data.getValue().procentObnizkiProperty().asObject());

        tableView.getColumns().addAll(columnId, columnNazwa, columnOpis, columnDataRozpoczecia, columnDataZakonczenia, columnProcentObnizki);

        VBox vbox = new VBox(labelPromocja, txtId, txtNazwa, txtOpis, dpDataRozpoczecia, dpDataZakonczenia, txtProcentObnizki, btnDodajPromocje, btnAktualizujPromocje, btnUsunPromocje, tableView);
        Scene scene = new Scene(vbox, 600, 600);
        setScene(scene);

        updateTable();
    }

    private void updateTable() {
        ObservableList<Promocja> promocje = PromocjaUtil.getAllPromocje();
        tableView.setItems(promocje);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
