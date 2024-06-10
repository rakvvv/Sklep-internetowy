import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KlientWindow extends Stage {

    private TableView<Klient> tableView;

    public KlientWindow() {
        setTitle("Zarządzanie Klientami");

        Label labelKlient = new Label("Klient");
        TextField txtImie = new TextField();
        txtImie.setPromptText("Imię");
        TextField txtNazwisko = new TextField();
        txtNazwisko.setPromptText("Nazwisko");
        TextField txtAdres = new TextField();
        txtAdres.setPromptText("Adres");
        TextField txtTelefon = new TextField();
        txtTelefon.setPromptText("Telefon");

        Button btnDodajKlienta = new Button("Dodaj Klienta");
        btnDodajKlienta.setOnAction(e -> {
            try {
                if (txtImie.getText().isEmpty() || txtNazwisko.getText().isEmpty() || txtAdres.getText().isEmpty() || txtTelefon.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Wszystkie pola muszą być wypełnione.");
                    return;
                }
                String imie = txtImie.getText();
                String nazwisko = txtNazwisko.getText();
                String adres = txtAdres.getText();
                String telefon = txtTelefon.getText();
                KlientUtil.dodajKlienta(imie, nazwisko, adres, telefon);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Klient został dodany.");
                updateTable();
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        TextField txtId = new TextField();
        txtId.setPromptText("ID");
        Button btnAktualizujKlienta = new Button("Aktualizuj Klienta");
        btnAktualizujKlienta.setOnAction(e -> {
            try {
                if (txtId.getText().isEmpty() || txtImie.getText().isEmpty() || txtNazwisko.getText().isEmpty() || txtAdres.getText().isEmpty() || txtTelefon.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Wszystkie pola muszą być wypełnione.");
                    return;
                }
                int klientId = Integer.parseInt(txtId.getText());
                String imie = txtImie.getText();
                String nazwisko = txtNazwisko.getText();
                String adres = txtAdres.getText();
                String telefon = txtTelefon.getText();
                KlientUtil.aktualizujKlienta(klientId, imie, nazwisko, adres, telefon);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Dane klienta zostały zaktualizowane.");
                updateTable();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        Button btnUsunKlienta = new Button("Usuń Klienta");
        btnUsunKlienta.setOnAction(e -> {
            try {
                if (txtId.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Pole ID musi być wypełnione.");
                    return;
                }
                int klientId = Integer.parseInt(txtId.getText());
                KlientUtil.usunKlienta(klientId);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Klient został usunięty.");
                updateTable();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        tableView = new TableView<>();
        TableColumn<Klient, Integer> columnId = new TableColumn<>("ID");
        columnId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        TableColumn<Klient, String> columnImie = new TableColumn<>("Imię");
        columnImie.setCellValueFactory(data -> data.getValue().imieProperty());
        TableColumn<Klient, String> columnNazwisko = new TableColumn<>("Nazwisko");
        columnNazwisko.setCellValueFactory(data -> data.getValue().nazwiskoProperty());
        TableColumn<Klient, String> columnAdres = new TableColumn<>("Adres");
        columnAdres.setCellValueFactory(data -> data.getValue().adresProperty());
        TableColumn<Klient, String> columnTelefon = new TableColumn<>("Telefon");
        columnTelefon.setCellValueFactory(data -> data.getValue().telefonProperty());

        tableView.getColumns().addAll(columnId, columnImie, columnNazwisko, columnAdres, columnTelefon);

        VBox vbox = new VBox(labelKlient, txtId, txtImie, txtNazwisko, txtAdres, txtTelefon, btnDodajKlienta, btnAktualizujKlienta, btnUsunKlienta, tableView);
        Scene scene = new Scene(vbox, 600, 600);
        setScene(scene);

        updateTable();
    }

    private void updateTable() {
        ObservableList<Klient> klienci = KlientUtil.getAllKlienci();
        tableView.setItems(klienci);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
