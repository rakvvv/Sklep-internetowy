import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Produkt {
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty kategoriaId;
    private SimpleIntegerProperty promocjaId;
    private SimpleStringProperty nazwa;
    private SimpleStringProperty opis;
    private SimpleDoubleProperty cenaJednostkowa;
    private SimpleIntegerProperty ilosc;

    public Produkt(int id, int kategoriaId, Integer promocjaId, String nazwa, String opis, double cenaJednostkowa) {
        this.id = new SimpleIntegerProperty(id);
        this.kategoriaId = new SimpleIntegerProperty(kategoriaId);
        this.promocjaId = new SimpleIntegerProperty(promocjaId != null ? promocjaId : 0);
        this.nazwa = new SimpleStringProperty(nazwa);
        this.opis = new SimpleStringProperty(opis);
        this.cenaJednostkowa = new SimpleDoubleProperty(cenaJednostkowa);
        this.ilosc = new SimpleIntegerProperty(0);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public int getKategoriaId() {
        return kategoriaId.get();
    }

    public void setKategoriaId(int kategoriaId) {
        this.kategoriaId.set(kategoriaId);
    }

    public SimpleIntegerProperty kategoriaIdProperty() {
        return kategoriaId;
    }

    public Integer getPromocjaId() {
        return promocjaId.get() != 0 ? promocjaId.get() : null;
    }

    public void setPromocjaId(Integer promocjaId) {
        this.promocjaId.set(promocjaId != null ? promocjaId : 0);
    }

    public SimpleIntegerProperty promocjaIdProperty() {
        return promocjaId;
    }

    public String getNazwa() {
        return nazwa.get();
    }

    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }

    public SimpleStringProperty nazwaProperty() {
        return nazwa;
    }

    public String getOpis() {
        return opis.get();
    }

    public void setOpis(String opis) {
        this.opis.set(opis);
    }

    public SimpleStringProperty opisProperty() {
        return opis;
    }

    public double getCenaJednostkowa() {
        return cenaJednostkowa.get();
    }

    public void setCenaJednostkowa(double cenaJednostkowa) {
        this.cenaJednostkowa.set(cenaJednostkowa);
    }

    public SimpleDoubleProperty cenaJednostkowaProperty() {
        return cenaJednostkowa;
    }

    public int getIlosc() {
        return ilosc.get();
    }

    public void setIlosc(int ilosc) {
        this.ilosc.set(ilosc);
    }

    public SimpleIntegerProperty iloscProperty() {
        return ilosc;
    }

    @Override
    public String toString() {
        return nazwa.get() + " (Cena: " + cenaJednostkowa.get() + ")";
    }
}
