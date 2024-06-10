import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Klient {
    private SimpleIntegerProperty id;
    private SimpleStringProperty imie;
    private SimpleStringProperty nazwisko;
    private SimpleStringProperty adres;
    private SimpleStringProperty telefon;

    public Klient(int id, String imie, String nazwisko, String adres, String telefon) {
        this.id = new SimpleIntegerProperty(id);
        this.imie = new SimpleStringProperty(imie);
        this.nazwisko = new SimpleStringProperty(nazwisko);
        this.adres = new SimpleStringProperty(adres);
        this.telefon = new SimpleStringProperty(telefon);
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

    public String getImie() {
        return imie.get();
    }

    public void setImie(String imie) {
        this.imie.set(imie);
    }

    public SimpleStringProperty imieProperty() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko.get();
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }

    public SimpleStringProperty nazwiskoProperty() {
        return nazwisko;
    }

    public String getAdres() {
        return adres.get();
    }

    public void setAdres(String adres) {
        this.adres.set(adres);
    }

    public SimpleStringProperty adresProperty() {
        return adres;
    }

    public String getTelefon() {
        return telefon.get();
    }

    public void setTelefon(String telefon) {
        this.telefon.set(telefon);
    }

    public SimpleStringProperty telefonProperty() {
        return telefon;
    }

    @Override
    public String toString() {
        return imie.get() + " " + nazwisko.get() + " (" + adres.get() + ", " + telefon.get() + ")";
    }
}
