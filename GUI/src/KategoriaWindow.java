import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KategoriaWindow extends Stage {

    private TableView<Kategoria> tableView;
    private ComboBox<Promocja> cmbPromocja;

    public KategoriaWindow() {
        setTitle("Zarządzanie Kategoriami");

        Label labelKategoria = new Label("Kategoria");
        TextField txtNazwaKategorii = new TextField();
        txtNazwaKategorii.setPromptText("Nazwa Kategorii");
        TextField txtOpis = new TextField();
        txtOpis.setPromptText("Opis");

        cmbPromocja = new ComboBox<>();
        cmbPromocja.setPromptText("Wybierz Promocję");
        cmbPromocja.setItems(PromocjaUtil.getAllPromocje());

        Button btnDodajKategorii = new Button("Dodaj Kategorię");
        btnDodajKategorii.setOnAction(e -> {
            try {
                if (txtNazwaKategorii.getText().isEmpty() || txtOpis.getText().isEmpty() || cmbPromocja.getValue() == null) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Wszystkie pola muszą być wypełnione.");
                    return;
                }
                String nazwaKategorii = txtNazwaKategorii.getText();
                String opis = txtOpis.getText();
                int promocjaId = cmbPromocja.getValue().getId();
                KategoriaUtil.dodajKategorie(promocjaId, nazwaKategorii, opis);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Kategoria została dodana.");
                updateTable();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        TextField txtId = new TextField();
        txtId.setPromptText("ID");
        Button btnAktualizujKategorii = new Button("Aktualizuj Kategorię");
        btnAktualizujKategorii.setOnAction(e -> {
            try {
                if (txtId.getText().isEmpty() || txtNazwaKategorii.getText().isEmpty() || txtOpis.getText().isEmpty() || cmbPromocja.getValue() == null) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Wszystkie pola muszą być wypełnione.");
                    return;
                }
                int kategoriaId = Integer.parseInt(txtId.getText());
                String nazwaKategorii = txtNazwaKategorii.getText();
                String opis = txtOpis.getText();
                int promocjaId = cmbPromocja.getValue().getId();
                KategoriaUtil.aktualizujKategorie(kategoriaId, nazwaKategorii, opis);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Kategoria została zaktualizowana.");
                updateTable();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        Button btnUsunKategorii = new Button("Usuń Kategorię");
        btnUsunKategorii.setOnAction(e -> {
            try {
                if (txtId.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Pole ID musi być wypełnione.");
                    return;
                }
                int kategoriaId = Integer.parseInt(txtId.getText());
                KategoriaUtil.usunKategorie(kategoriaId);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Kategoria została usunięta.");
                updateTable();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        tableView = new TableView<>();
        TableColumn<Kategoria, Integer> columnId = new TableColumn<>("ID");
        columnId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        TableColumn<Kategoria, String> columnNazwaKategorii = new TableColumn<>("Nazwa Kategorii");
        columnNazwaKategorii.setCellValueFactory(data -> data.getValue().nazwaKategoriiProperty());
        TableColumn<Kategoria, String> columnOpis = new TableColumn<>("Opis");
        columnOpis.setCellValueFactory(data -> data.getValue().opisProperty());
        TableColumn<Kategoria, Integer> promocjaColumn = new TableColumn<>("Promocja ID");
        promocjaColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().promocjaIdProperty() != null) {
                return cellData.getValue().promocjaIdProperty().asObject();
            } else {
                return new SimpleIntegerProperty(0).asObject(); // lub inna wartość domyślna
            }
        });
        tableView.getColumns().addAll(columnId, columnNazwaKategorii, columnOpis, promocjaColumn);

        VBox vbox = new VBox(labelKategoria, txtId, txtNazwaKategorii, txtOpis, cmbPromocja, btnDodajKategorii, btnAktualizujKategorii, btnUsunKategorii, tableView);
        Scene scene = new Scene(vbox, 600, 600);
        setScene(scene);

        updateTable();
    }

    private void updateTable() {
        ObservableList<Kategoria> kategorie = KategoriaUtil.getAllKategorie();
        tableView.setItems(kategorie);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
