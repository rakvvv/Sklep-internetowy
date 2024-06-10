import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PracownikWindow extends Stage {

    private TableView<Pracownik> tableView;

    public PracownikWindow() {
        setTitle("Zarządzanie Pracownikami");

        Label labelPracownik = new Label("Pracownik");
        TextField txtImie = new TextField();
        txtImie.setPromptText("Imię");
        TextField txtNazwisko = new TextField();
        txtNazwisko.setPromptText("Nazwisko");
        TextField txtStanowisko = new TextField();
        txtStanowisko.setPromptText("Stanowisko");
        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Email");
        TextField txtTelefon = new TextField();
        txtTelefon.setPromptText("Telefon");

        Button btnDodajPracownika = new Button("Dodaj Pracownika");
        btnDodajPracownika.setOnAction(e -> {
            try {
                if (txtImie.getText().isEmpty() || txtNazwisko.getText().isEmpty() || txtStanowisko.getText().isEmpty() || txtEmail.getText().isEmpty() || txtTelefon.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Wszystkie pola muszą być wypełnione.");
                    return;
                }
                String imie = txtImie.getText();
                String nazwisko = txtNazwisko.getText();
                String stanowisko = txtStanowisko.getText();
                String email = txtEmail.getText();
                String telefon = txtTelefon.getText();
                PracownikUtil.dodajPracownika(imie, nazwisko, stanowisko, email, telefon);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Pracownik został dodany.");
                updateTable();
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        TextField txtId = new TextField();
        txtId.setPromptText("ID");
        Button btnAktualizujPracownika = new Button("Aktualizuj Pracownika");
        btnAktualizujPracownika.setOnAction(e -> {
            try {
                if (txtId.getText().isEmpty() || txtImie.getText().isEmpty() || txtNazwisko.getText().isEmpty() || txtStanowisko.getText().isEmpty() || txtEmail.getText().isEmpty() || txtTelefon.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Wszystkie pola muszą być wypełnione.");
                    return;
                }
                int pracownikId = Integer.parseInt(txtId.getText());
                String imie = txtImie.getText();
                String nazwisko = txtNazwisko.getText();
                String stanowisko = txtStanowisko.getText();
                String email = txtEmail.getText();
                String telefon = txtTelefon.getText();
                PracownikUtil.aktualizujPracownika(pracownikId, imie, nazwisko, stanowisko, email, telefon);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Dane pracownika zostały zaktualizowane.");
                updateTable();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        Button btnUsunPracownika = new Button("Usuń Pracownika");
        btnUsunPracownika.setOnAction(e -> {
            try {
                if (txtId.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Pole ID musi być wypełnione.");
                    return;
                }
                int pracownikId = Integer.parseInt(txtId.getText());
                PracownikUtil.usunPracownika(pracownikId);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Pracownik został usunięty.");
                updateTable();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        tableView = new TableView<>();
        TableColumn<Pracownik, Integer> columnId = new TableColumn<>("ID");
        columnId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        TableColumn<Pracownik, String> columnImie = new TableColumn<>("Imię");
        columnImie.setCellValueFactory(data -> data.getValue().imieProperty());
        TableColumn<Pracownik, String> columnNazwisko = new TableColumn<>("Nazwisko");
        columnNazwisko.setCellValueFactory(data -> data.getValue().nazwiskoProperty());
        TableColumn<Pracownik, String> columnStanowisko = new TableColumn<>("Stanowisko");
        columnStanowisko.setCellValueFactory(data -> data.getValue().stanowiskoProperty());
        TableColumn<Pracownik, String> columnEmail = new TableColumn<>("Email");
        columnEmail.setCellValueFactory(data -> data.getValue().emailProperty());
        TableColumn<Pracownik, String> columnTelefon = new TableColumn<>("Telefon");
        columnTelefon.setCellValueFactory(data -> data.getValue().telefonProperty());

        tableView.getColumns().addAll(columnId, columnImie, columnNazwisko, columnStanowisko, columnEmail, columnTelefon);

        VBox vbox = new VBox(labelPracownik, txtId, txtImie, txtNazwisko, txtStanowisko, txtEmail, txtTelefon, btnDodajPracownika, btnAktualizujPracownika, btnUsunPracownika, tableView);
        Scene scene = new Scene(vbox, 600, 600);
        setScene(scene);

        updateTable();
    }

    private void updateTable() {
        ObservableList<Pracownik> pracownicy = PracownikUtil.getAllPracownicy();
        tableView.setItems(pracownicy);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
