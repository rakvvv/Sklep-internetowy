import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.internal.OracleTypes;

import java.sql.*;

public class ProduktUtil {

    public static void dodajProdukt(int kategoriaId, Integer promocjaId, String nazwa, String opis, double cenaJednostkowa) {
        String sql = "{call dodaj_nowy_produkt(?, ?, ?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, kategoriaId);
            if (promocjaId != null) {
                stmt.setInt(2, promocjaId);
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setString(3, nazwa);
            stmt.setString(4, opis);
            stmt.setDouble(5, cenaJednostkowa);
            stmt.execute();
            System.out.println("Produkt został dodany.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void aktualizujProdukt(int produktId, int kategoriaId, Integer promocjaId, String nazwa, String opis, double cenaJednostkowa) {
        String sql = "{call aktualizuj_produkt(?, ?, ?, ?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, produktId);
            stmt.setInt(2, kategoriaId);
            if (promocjaId != null) {
                stmt.setInt(3, promocjaId);
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            stmt.setString(4, nazwa);
            stmt.setString(5, opis);
            stmt.setDouble(6, cenaJednostkowa);
            stmt.execute();
            System.out.println("Produkt został zaktualizowany.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void usunProdukt(int produktId) {
        String sql = "{call usun_produkt_z_zaleznosci(?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, produktId);
            stmt.execute();
            System.out.println("Produkt został usunięty.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Produkt> getAllProdukty() {
        ObservableList<Produkt> produktList = FXCollections.observableArrayList();
        String sql = "{call wyswietl_produkty(?)}";
        try (Connection conn = DBUtil.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    int id = rs.getInt("ProduktID");
                    int kategoriaId = rs.getInt("KategoriaID");
                    Integer promocjaId = rs.getInt("PromocjaID") != 0 ? rs.getInt("PromocjaID") : null;
                    String nazwa = rs.getString("Nazwa");
                    String opis = rs.getString("Opis");
                    double cenaJednostkowa = rs.getDouble("CenaJednostkowa");
                    produktList.add(new Produkt(id, kategoriaId, promocjaId, nazwa, opis, cenaJednostkowa));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching produkt data", e);
        }
        return produktList;
    }
}
