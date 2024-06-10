import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Kategoria {
    private SimpleIntegerProperty id;
    private SimpleStringProperty nazwaKategorii;
    private SimpleStringProperty opis;
    private SimpleIntegerProperty promocjaId;

    public Kategoria(int id, String nazwaKategorii, String opis, Integer promocjaId) {
        this.id = new SimpleIntegerProperty(id);
        this.nazwaKategorii = new SimpleStringProperty(nazwaKategorii);
        this.opis = new SimpleStringProperty(opis);
        if (promocjaId != null) {
            this.promocjaId = new SimpleIntegerProperty(promocjaId);
        } else {
            this.promocjaId = null;
        }
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

    public String getNazwaKategorii() {
        return nazwaKategorii.get();
    }

    public void setNazwaKategorii(String nazwaKategorii) {
        this.nazwaKategorii.set(nazwaKategorii);
    }

    public SimpleStringProperty nazwaKategoriiProperty() {
        return nazwaKategorii;
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

    public Integer getPromocjaId() {
        return promocjaId != null ? promocjaId.get() : null;
    }

    public void setPromocjaId(Integer promocjaId) {
        if (promocjaId != null) {
            this.promocjaId.set(promocjaId);
        } else {
            this.promocjaId = null;
        }
    }

    public SimpleIntegerProperty promocjaIdProperty() {
        return promocjaId;
    }

    @Override
    public String toString() {
        return nazwaKategorii.get() + (promocjaId != null ? " (Promocja: " + promocjaId.get() + ")" : "");
    }
}
