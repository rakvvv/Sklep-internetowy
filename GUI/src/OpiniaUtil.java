import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.OracleTypes;

import java.sql.*;

public class OpiniaUtil {

    public static void dodajOpinia(int klientId, int produktId, Date data, int ocena, String komentarz) throws SQLException {
        String sql = "{call dodaj_nowa_opinia(?, ?, ?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, klientId);
            stmt.setInt(2, produktId);
            stmt.setDate(3, new java.sql.Date(data.getTime()));
            stmt.setInt(4, ocena);
            stmt.setString(5, komentarz);
            stmt.execute();
        }
    }

    public static void aktualizujOpinia(int opiniaId, int klientId, int produktId, Date data, int ocena, String komentarz) throws SQLException {
        String sql = "{call aktualizuj_opinia(?, ?, ?, ?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, opiniaId);
            stmt.setInt(2, klientId);
            stmt.setInt(3, produktId);
            stmt.setDate(4, new java.sql.Date(data.getTime()));
            stmt.setInt(5, ocena);
            stmt.setString(6, komentarz);
            stmt.execute();
        }
    }

    public static void usunOpinia(int opiniaId) throws SQLException {
        String sql = "{call usun_opinia_z_zaleznosci(?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, opiniaId);
            stmt.execute();
        }
    }

    public static ObservableList<Opinia> getAllOpinie() {
        ObservableList<Opinia> opinie = FXCollections.observableArrayList();
        String sql = "{call wyswietl_opinie(?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    Opinia opinia = new Opinia(
                            rs.getInt("OpiniaID"),
                            rs.getInt("KlientID"),
                            rs.getInt("ProduktID"),
                            rs.getDate("Data"),
                            rs.getInt("Ocena"),
                            rs.getString("Komentarz")
                    );
                    opinie.add(opinia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching opinie data", e);
        }
        return opinie;
    }
}
