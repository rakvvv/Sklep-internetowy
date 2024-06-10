import javafx.beans.property.*;

public class Opinia {
    private IntegerProperty opiniaId;
    private IntegerProperty klientId;
    private IntegerProperty produktId;
    private ObjectProperty<java.sql.Date> data;
    private IntegerProperty ocena;
    private StringProperty komentarz;

    public Opinia(int opiniaId, int klientId, int produktId, java.sql.Date data, int ocena, String komentarz) {
        this.opiniaId = new SimpleIntegerProperty(opiniaId);
        this.klientId = new SimpleIntegerProperty(klientId);
        this.produktId = new SimpleIntegerProperty(produktId);
        this.data = new SimpleObjectProperty<>(data);
        this.ocena = new SimpleIntegerProperty(ocena);
        this.komentarz = new SimpleStringProperty(komentarz);
    }

    public int getOpiniaId() {
        return opiniaId.get();
    }

    public IntegerProperty opiniaIdProperty() {
        return opiniaId;
    }

    public void setOpiniaId(int opiniaId) {
        this.opiniaId.set(opiniaId);
    }

    public int getKlientId() {
        return klientId.get();
    }

    public IntegerProperty klientIdProperty() {
        return klientId;
    }

    public void setKlientId(int klientId) {
        this.klientId.set(klientId);
    }

    public int getProduktId() {
        return produktId.get();
    }

    public IntegerProperty produktIdProperty() {
        return produktId;
    }

    public void setProduktId(int produktId) {
        this.produktId.set(produktId);
    }

    public java.sql.Date getData() {
        return data.get();
    }

    public ObjectProperty<java.sql.Date> dataProperty() {
        return data;
    }

    public void setData(java.sql.Date data) {
        this.data.set(data);
    }

    public int getOcena() {
        return ocena.get();
    }

    public IntegerProperty ocenaProperty() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena.set(ocena);
    }

    public String getKomentarz() {
        return komentarz.get();
    }

    public StringProperty komentarzProperty() {
        return komentarz;
    }

    public void setKomentarz(String komentarz) {
        this.komentarz.set(komentarz);
    }
}
