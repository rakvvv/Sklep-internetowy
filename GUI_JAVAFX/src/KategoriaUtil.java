import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.internal.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KategoriaUtil {

    public static void dodajKategorie(int promocjaId, String nazwaKategorii, String opis) {
        String sql = "{call dodaj_nowa_kategorie(?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, promocjaId);
            stmt.setString(2, nazwaKategorii);
            stmt.setString(3, opis);
            stmt.execute();
            System.out.println("Kategoria została dodana.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void aktualizujKategorie(int kategoriaId, String nazwaKategorii, String opis) {
        String sql = "{call aktualizuj_kategorie(?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, kategoriaId);
            stmt.setString(2, nazwaKategorii);
            stmt.setString(3, opis);
            stmt.execute();
            System.out.println("Kategoria została zaktualizowana.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void usunKategorie(int kategoriaId) {
        String sql = "{call usun_kategorie_z_zaleznosci(?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, kategoriaId);
            stmt.execute();
            System.out.println("Kategoria została usunięta.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Kategoria> getAllKategorie() {
        ObservableList<Kategoria> kategoriaList = FXCollections.observableArrayList();
        String sql = "{call wyswietl_kategorie(?)}";
        try (Connection conn = DBUtil.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    int id = rs.getInt("KategoriaID");
                    Integer promocjaId = rs.getInt("PromocjaID") != 0 ? rs.getInt("PromocjaID") : null;
                    String nazwaKategorii = rs.getString("NazwaKategorii");
                    String opis = rs.getString("Opis");
                    kategoriaList.add(new Kategoria(id, nazwaKategorii, opis, promocjaId));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching kategoria data", e);
        }
        return kategoriaList;
    }

}
