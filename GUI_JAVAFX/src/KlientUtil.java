import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
public class KlientUtil {

    public static void dodajKlienta(String imie, String nazwisko, String adres, String telefon) throws SQLException {
        String sql = "{call dodaj_nowego_klienta(?, ?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, imie);
            stmt.setString(2, nazwisko);
            stmt.setString(3, adres);
            stmt.setString(4, telefon);
            stmt.executeUpdate();
        }
    }

    public static void aktualizujKlienta(int klientId, String imie, String nazwisko, String adres, String telefon) throws SQLException {
        String sql = "{call aktualizuj_klienta(?, ?, ?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, klientId);
            stmt.setString(2, imie);
            stmt.setString(3, nazwisko);
            stmt.setString(4, adres);
            stmt.setString(5, telefon);
            stmt.executeUpdate();
        }
    }

    public static void usunKlienta(int klientId) throws SQLException {
        String sql = "{call usun_klienta_z_zaleznosci(?)}";
        try (Connection conn = DBUtil.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, klientId);
            stmt.executeUpdate();
        }
    }

    public static ObservableList<Klient> getAllKlienci() {
        ObservableList<Klient> klientList = FXCollections.observableArrayList();
        String sql = "{call wyswietl_klientow(?)}";
        try (Connection conn = DBUtil.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    int id = rs.getInt("KlientID");
                    String imie = rs.getString("Imie");
                    String nazwisko = rs.getString("Nazwisko");
                    String adres = rs.getString("Adres");
                    String telefon = rs.getString("Telefon");
                    klientList.add(new Klient(id, imie, nazwisko, adres, telefon));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching klienci data", e);
        }
        return klientList;
    }
}
