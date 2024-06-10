import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProduktWindow extends Stage {

    private TableView<Produkt> tableView;

    public ProduktWindow() {
        setTitle("Zarządzanie Produktami");

        Label labelProdukt = new Label("Produkt");
        TextField txtNazwa = new TextField();
        txtNazwa.setPromptText("Nazwa");
        TextField txtOpis = new TextField();
        txtOpis.setPromptText("Opis");
        TextField txtCenaJednostkowa = new TextField();
        txtCenaJednostkowa.setPromptText("Cena Jednostkowa");

        ComboBox<Kategoria> comboKategoria = new ComboBox<>(KategoriaUtil.getAllKategorie());
        comboKategoria.setPromptText("Wybierz Kategorię");

        ComboBox<Promocja> comboPromocja = new ComboBox<>(PromocjaUtil.getAllPromocje());
        comboPromocja.setPromptText("Wybierz Promocję");

        Button btnDodajProdukt = new Button("Dodaj Produkt");
        btnDodajProdukt.setOnAction(e -> {
            try {
                if (txtNazwa.getText().isEmpty() || txtOpis.getText().isEmpty() || txtCenaJednostkowa.getText().isEmpty() || comboKategoria.getValue() == null) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Wszystkie pola muszą być wypełnione.");
                    return;
                }
                String nazwa = txtNazwa.getText();
                String opis = txtOpis.getText();
                double cenaJednostkowa = Double.parseDouble(txtCenaJednostkowa.getText());
                int kategoriaId = comboKategoria.getValue().getId();
                Integer promocjaId = comboPromocja.getValue() != null ? comboPromocja.getValue().getId() : null;
                ProduktUtil.dodajProdukt(kategoriaId, promocjaId, nazwa, opis, cenaJednostkowa);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Produkt został dodany.");
                updateTable();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        TextField txtId = new TextField();
        txtId.setPromptText("ID");
        Button btnAktualizujProdukt = new Button("Aktualizuj Produkt");
        btnAktualizujProdukt.setOnAction(e -> {
            try {
                if (txtId.getText().isEmpty() || txtNazwa.getText().isEmpty() || txtOpis.getText().isEmpty() || txtCenaJednostkowa.getText().isEmpty() || comboKategoria.getValue() == null) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Wszystkie pola muszą być wypełnione.");
                    return;
                }
                int produktId = Integer.parseInt(txtId.getText());
                String nazwa = txtNazwa.getText();
                String opis = txtOpis.getText();
                double cenaJednostkowa = Double.parseDouble(txtCenaJednostkowa.getText());
                int kategoriaId = comboKategoria.getValue().getId();
                Integer promocjaId = comboPromocja.getValue() != null ? comboPromocja.getValue().getId() : null;
                ProduktUtil.aktualizujProdukt(produktId, kategoriaId, promocjaId, nazwa, opis, cenaJednostkowa);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Produkt został zaktualizowany.");
                updateTable();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        Button btnUsunProdukt = new Button("Usuń Produkt");
        btnUsunProdukt.setOnAction(e -> {
            try {
                if (txtId.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Pole ID musi być wypełnione.");
                    return;
                }
                int produktId = Integer.parseInt(txtId.getText());
                ProduktUtil.usunProdukt(produktId);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Produkt został usunięty.");
                updateTable();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        tableView = new TableView<>();
        TableColumn<Produkt, Integer> columnId = new TableColumn<>("ID");
        columnId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        TableColumn<Produkt, String> columnNazwa = new TableColumn<>("Nazwa");
        columnNazwa.setCellValueFactory(data -> data.getValue().nazwaProperty());
        TableColumn<Produkt, String> columnOpis = new TableColumn<>("Opis");
        columnOpis.setCellValueFactory(data -> data.getValue().opisProperty());
        TableColumn<Produkt, Double> columnCenaJednostkowa = new TableColumn<>("Cena Jednostkowa");
        columnCenaJednostkowa.setCellValueFactory(data -> data.getValue().cenaJednostkowaProperty().asObject());
        TableColumn<Produkt, Integer> columnKategoriaId = new TableColumn<>("ID Kategorii");
        columnKategoriaId.setCellValueFactory(data -> data.getValue().kategoriaIdProperty().asObject());
        TableColumn<Produkt, Integer> columnPromocjaId = new TableColumn<>("ID Promocji");
        columnPromocjaId.setCellValueFactory(data -> data.getValue().promocjaIdProperty().asObject());

        tableView.getColumns().addAll(columnId, columnNazwa, columnOpis, columnCenaJednostkowa, columnKategoriaId, columnPromocjaId);

        VBox vbox = new VBox(labelProdukt, txtId, txtNazwa, txtOpis, txtCenaJednostkowa, comboKategoria, comboPromocja, btnDodajProdukt, btnAktualizujProdukt, btnUsunProdukt, tableView);
        Scene scene = new Scene(vbox, 600, 600);
        setScene(scene);

        updateTable();
    }

    private void updateTable() {
        ObservableList<Produkt> produkty = ProduktUtil.getAllProdukty();
        tableView.setItems(produkty);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
