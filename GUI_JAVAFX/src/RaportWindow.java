import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RaportWindow extends Stage {

    private TableView<Raport> tableView;

    public RaportWindow() {
        setTitle("Raport Sprzedaży Produktów");

        tableView = new TableView<>();
        TableColumn<Raport, String> columnNazwa = new TableColumn<>("Nazwa");
        columnNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        TableColumn<Raport, String> columnKategoria = new TableColumn<>("Kategoria");
        columnKategoria.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        TableColumn<Raport, Double> columnZarobek = new TableColumn<>("Zarobek");
        columnZarobek.setCellValueFactory(new PropertyValueFactory<>("zarobek"));
        TableColumn<Raport, Integer> columnIloscSprzedanych = new TableColumn<>("Ilość Sprzedanych");
        columnIloscSprzedanych.setCellValueFactory(new PropertyValueFactory<>("iloscSprzedanych"));
        TableColumn<Raport, Double> columnSredniaOcena = new TableColumn<>("Średnia Ocena");
        columnSredniaOcena.setCellValueFactory(new PropertyValueFactory<>("sredniaOcena"));

        tableView.getColumns().addAll(columnNazwa, columnKategoria, columnZarobek, columnIloscSprzedanych, columnSredniaOcena);

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox, 800, 600);
        setScene(scene);

        updateTable();
    }

    private void updateTable() {
        ObservableList<Raport> raporty = FXCollections.observableArrayList();
        String sql = "{? = call RaportSprzedazyProduktow()}";
        try (Connection conn = DBUtil.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    String nazwa = rs.getString("Nazwa");
                    String kategoria = rs.getString("NazwaKategorii");
                    double zarobek = rs.getDouble("Zarobek");
                    int iloscSprzedanych = rs.getInt("IloscSprzedanych");
                    double sredniaOcena = rs.getDouble("SredniaOcena");
                    raporty.add(new Raport(nazwa, kategoria, zarobek, iloscSprzedanych, sredniaOcena));
                }
            }
            tableView.setItems(raporty);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Błąd", "Wystąpił błąd: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
