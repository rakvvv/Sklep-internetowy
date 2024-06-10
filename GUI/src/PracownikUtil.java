import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PracownikUtil {

    public static void dodajPracownika(String imie, String nazwisko, String stanowisko, String email, String telefon) {
        String sql = "{call dodaj_nowego_pracownika(?, ?, ?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, imie);
            stmt.setString(2, nazwisko);
            stmt.setString(3, stanowisko);
            stmt.setString(4, email);
            stmt.setString(5, telefon);
            stmt.execute();
            System.out.println("Pracownik został dodany.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void aktualizujPracownika(int pracownikId, String imie, String nazwisko, String stanowisko, String email, String telefon) {
        String sql = "{call aktualizuj_pracownika(?, ?, ?, ?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, pracownikId);
            stmt.setString(2, imie);
            stmt.setString(3, nazwisko);
            stmt.setString(4, stanowisko);
            stmt.setString(5, email);
            stmt.setString(6, telefon);
            stmt.execute();
            System.out.println("Dane pracownika zostały zaktualizowane.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void usunPracownika(int pracownikId) {
        String sql = "{call usun_pracownika_z_zaleznosci(?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, pracownikId);
            stmt.execute();
            System.out.println("Pracownik został usunięty.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Pracownik> getAllPracownicy() {
        ObservableList<Pracownik> pracownikList = FXCollections.observableArrayList();
        String sql = "{call wyswietl_pracownikow(?)}";
        try (Connection conn = DBUtil.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    int id = rs.getInt("PracownikID");
                    String imie = rs.getString("Imie");
                    String nazwisko = rs.getString("Nazwisko");
                    String stanowisko = rs.getString("Stanowisko");
                    String email = rs.getString("Email");
                    String telefon = rs.getString("Telefon");
                    pracownikList.add(new Pracownik(id, imie, nazwisko, stanowisko, email, telefon));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching pracownicy data", e);
        }
        return pracownikList;
    }
}
