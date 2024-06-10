import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.time.LocalDate;
public class PromocjaUtil {

    public static void dodajPromocje(String nazwa, String opis, LocalDate dataRozpoczecia, LocalDate dataZakonczenia, double procentObnizki) {
        String sql = "{call dodaj_nowa_promocje(?, ?, ?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, nazwa);
            stmt.setString(2, opis);
            stmt.setDate(3, java.sql.Date.valueOf(dataRozpoczecia));
            stmt.setDate(4, java.sql.Date.valueOf(dataZakonczenia));
            stmt.setDouble(5, procentObnizki);
            stmt.execute();
            System.out.println("Promocja została dodana.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void aktualizujPromocje(int promocjaId, String nazwa, String opis, LocalDate dataRozpoczecia, LocalDate dataZakonczenia, double procentObnizki) {
        String sql = "{call aktualizuj_promocje(?, ?, ?, ?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, promocjaId);
            stmt.setString(2, nazwa);
            stmt.setString(3, opis);
            stmt.setDate(4, java.sql.Date.valueOf(dataRozpoczecia));
            stmt.setDate(5, java.sql.Date.valueOf(dataZakonczenia));
            stmt.setDouble(6, procentObnizki);
            stmt.execute();
            System.out.println("Promocja została zaktualizowana.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void usunPromocje(int promocjaId) {
        String sql = "{call usun_promocje_z_zaleznosci(?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, promocjaId);
            stmt.execute();
            System.out.println("Promocja została usunięta.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Promocja> getAllPromocje() {
        ObservableList<Promocja> promocjeList = FXCollections.observableArrayList();
        String sql = "{call wyswietl_promocje(?)}";
        try (Connection conn = DBUtil.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    int id = rs.getInt("PromocjaID");
                    String nazwa = rs.getString("Nazwa");
                    String opis = rs.getString("Opis");
                    Date dataRozpoczecia = rs.getDate("DataRozpoczecia");
                    Date dataZakonczenia = rs.getDate("DataZakonczenia");
                    double procentObnizki = rs.getDouble("ProcentObnizki");
                    promocjeList.add(new Promocja(id, nazwa, opis, dataRozpoczecia.toLocalDate(), dataZakonczenia.toLocalDate(), procentObnizki));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching promocje data", e);
        }
        return promocjeList;
    }
}
