import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.internal.OracleTypes;

import java.sql.*;

public class ZamowienieUtil {

    public static void dodajZamowienie(int klientId, int pracownikId, int produktId, int ilosc) {
        String sql = "{call dodaj_zamowienie(?, ?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, klientId);
            stmt.setInt(2, pracownikId);
            stmt.setInt(3, produktId);
            stmt.setInt(4, ilosc);
            stmt.execute();
            System.out.println("Zamówienie zostało dodane.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void anulujZamowienie(int zamowienieId) {
        String sql = "{call anuluj_zamowienie(?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, zamowienieId);
            stmt.execute();
            System.out.println("Zamówienie zostało anulowane.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void aktualizujStatus(int zamowienieId, String nowyStatus) {
        String sql = "{call aktualizuj_status_zamowienia(?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, zamowienieId);
            stmt.setString(2, nowyStatus);
            stmt.execute();
            System.out.println("Status zamówienia został zaktualizowany.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Zamowienie> getAllZamowienia() {
        ObservableList<Zamowienie> zamowienieList = FXCollections.observableArrayList();
        String sql = "{call wyswietl_zamowienia(?)}";
        try (Connection conn = DBUtil.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    int id = rs.getInt("ZamowienieID");
                    int klientId = rs.getInt("KlientID");
                    int pracownikId = rs.getInt("PracownikID");
                    int ilosc = rs.getInt("Ilosc");
                    Date dataZamowienia = rs.getDate("DataZamowienia");
                    String status = rs.getString("Status");
                    double kwotaRazem = rs.getDouble("KwotaRazem");
                    zamowienieList.add(new Zamowienie(id, klientId, pracownikId, ilosc, dataZamowienia.toLocalDate(), status, kwotaRazem));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching zamowienia data", e);
        }
        return zamowienieList;
    }
}
