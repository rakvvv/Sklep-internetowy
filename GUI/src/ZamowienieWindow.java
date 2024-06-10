import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ZamowienieWindow extends Stage {

    private TableView<Zamowienie> tableView;
    private ComboBox<Klient> cmbKlient;
    private ComboBox<Pracownik> cmbPracownik;
    private ComboBox<Produkt> cmbProdukt;
    private TextField txtIlosc;
    private TableView<Produkt> produktyTableView;
    private ObservableList<Produkt> produktyList;

    public ZamowienieWindow() {
        setTitle("Zarządzanie Zamówieniami");

        Label labelZamowienie = new Label("Zamówienie");

        cmbKlient = new ComboBox<>();
        cmbKlient.setPromptText("Wybierz Klienta");
        cmbKlient.setItems(KlientUtil.getAllKlienci());

        cmbPracownik = new ComboBox<>();
        cmbPracownik.setPromptText("Wybierz Pracownika");
        cmbPracownik.setItems(PracownikUtil.getAllPracownicy());

        cmbProdukt = new ComboBox<>();
        cmbProdukt.setPromptText("Wybierz Produkt");
        cmbProdukt.setItems(ProduktUtil.getAllProdukty());

        txtIlosc = new TextField();
        txtIlosc.setPromptText("Ilość");

        produktyList = FXCollections.observableArrayList();

        produktyTableView = new TableView<>();
        TableColumn<Produkt, String> columnNazwa = new TableColumn<>("Nazwa");
        columnNazwa.setCellValueFactory(data -> data.getValue().nazwaProperty());
        TableColumn<Produkt, Double> columnCena = new TableColumn<>("Cena");
        columnCena.setCellValueFactory(data -> data.getValue().cenaJednostkowaProperty().asObject());
        TableColumn<Produkt, Integer> columnIloscProdukt = new TableColumn<>("Ilość");
        columnIloscProdukt.setCellValueFactory(data -> data.getValue().iloscProperty().asObject());

        produktyTableView.getColumns().addAll(columnNazwa, columnCena, columnIloscProdukt);
        produktyTableView.setItems(produktyList);

        Button btnDodajProdukt = new Button("Dodaj Produkt do Zamówienia");
        btnDodajProdukt.setOnAction(e -> {
            try {
                if (cmbProdukt.getValue() == null || txtIlosc.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Wszystkie pola muszą być wypełnione.");
                    return;
                }
                Produkt produkt = cmbProdukt.getValue();
                int ilosc = Integer.parseInt(txtIlosc.getText());
                Produkt produktDoZamowienia = new Produkt(produkt.getId(), produkt.getKategoriaId(), produkt.getPromocjaId(), produkt.getNazwa(), produkt.getOpis(), produkt.getCenaJednostkowa());
                produktDoZamowienia.setIlosc(ilosc);
                produktyList.add(produktDoZamowienia);
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        Button btnDodajZamowienie = new Button("Dodaj Zamówienie");
        btnDodajZamowienie.setOnAction(e -> {
            try {
                if (cmbKlient.getValue() == null || cmbPracownik.getValue() == null || produktyList.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Wszystkie pola muszą być wypełnione.");
                    return;
                }
                int klientId = cmbKlient.getValue().getId();
                int pracownikId = cmbPracownik.getValue().getId();
                for (Produkt produkt : produktyList) {
                    ZamowienieUtil.dodajZamowienie(klientId, pracownikId, produkt.getId(), produkt.getIlosc());
                }
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Zamówienie zostało dodane.");
                updateTable();
                produktyList.clear();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        TextField txtId = new TextField();
        txtId.setPromptText("ID");
        TextField txtStatus = new TextField();
        txtStatus.setPromptText("Status");
        Button btnAktualizujStatus = new Button("Aktualizuj Status");
        btnAktualizujStatus.setOnAction(e -> {
            try {
                if (txtId.getText().isEmpty() || txtStatus.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Pole ID i Status muszą być wypełnione.");
                    return;
                }
                int zamowienieId = Integer.parseInt(txtId.getText());
                String nowyStatus = txtStatus.getText();
                ZamowienieUtil.aktualizujStatus(zamowienieId, nowyStatus);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Status zamówienia został zaktualizowany.");
                updateTable();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        Button btnAnulujZamowienie = new Button("Anuluj Zamówienie");
        btnAnulujZamowienie.setOnAction(e -> {
            try {
                if (txtId.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Błąd", "Pole ID musi być wypełnione.");
                    return;
                }
                int zamowienieId = Integer.parseInt(txtId.getText());
                ZamowienieUtil.anulujZamowienie(zamowienieId);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Zamówienie zostało anulowane.");
                updateTable();
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Nieprawidłowy format liczby.");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + ex.getMessage());
            }
        });

        tableView = new TableView<>();
        TableColumn<Zamowienie, Integer> columnId = new TableColumn<>("ID");
        columnId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        TableColumn<Zamowienie, Integer> columnKlientId = new TableColumn<>("ID Klienta");
        columnKlientId.setCellValueFactory(data -> data.getValue().klientIdProperty().asObject());
        TableColumn<Zamowienie, Integer> columnPracownikId = new TableColumn<>("ID Pracownika");
        columnPracownikId.setCellValueFactory(data -> data.getValue().pracownikIdProperty().asObject());
        TableColumn<Zamowienie, Integer> columnIlosc = new TableColumn<>("Ilość");
        columnIlosc.setCellValueFactory(data -> data.getValue().iloscProperty().asObject());
        TableColumn<Zamowienie, LocalDate> columnDataZamowienia = new TableColumn<>("Data Zamówienia");
        columnDataZamowienia.setCellValueFactory(data -> data.getValue().dataZamowieniaProperty());
        TableColumn<Zamowienie, String> columnStatus = new TableColumn<>("Status");
        columnStatus.setCellValueFactory(data -> data.getValue().statusProperty());
        TableColumn<Zamowienie, Double> columnKwotaRazem = new TableColumn<>("Kwota Razem");
        columnKwotaRazem.setCellValueFactory(data -> data.getValue().kwotaRazemProperty().asObject());

        tableView.getColumns().addAll(columnId, columnKlientId, columnPracownikId, columnIlosc, columnDataZamowienia, columnStatus, columnKwotaRazem);

        VBox vbox = new VBox(labelZamowienie, cmbKlient, cmbPracownik, cmbProdukt, txtIlosc, btnDodajProdukt, produktyTableView, btnDodajZamowienie, txtId, txtStatus, btnAktualizujStatus, btnAnulujZamowienie, tableView);
        Scene scene = new Scene(vbox, 800, 800);
        setScene(scene);

        updateTable();
    }

    private void updateTable() {
        ObservableList<Zamowienie> zamowienia = ZamowienieUtil.getAllZamowienia();
        tableView.setItems(zamowienia);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
