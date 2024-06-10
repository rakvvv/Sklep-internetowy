import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Pracownik {
    private SimpleIntegerProperty id;
    private SimpleStringProperty imie;
    private SimpleStringProperty nazwisko;
    private SimpleStringProperty stanowisko;
    private SimpleStringProperty email;
    private SimpleStringProperty telefon;

    public Pracownik(int id, String imie, String nazwisko, String stanowisko, String email, String telefon) {
        this.id = new SimpleIntegerProperty(id);
        this.imie = new SimpleStringProperty(imie);
        this.nazwisko = new SimpleStringProperty(nazwisko);
        this.stanowisko = new SimpleStringProperty(stanowisko);
        this.email = new SimpleStringProperty(email);
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

    public String getStanowisko() {
        return stanowisko.get();
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko.set(stanowisko);
    }

    public SimpleStringProperty stanowiskoProperty() {
        return stanowisko;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public SimpleStringProperty emailProperty() {
        return email;
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
        return imie.get() + " " + nazwisko.get() + " (Stanowisko: " + stanowisko.get() + ", Email: " + email.get() + ", Telefon: " + telefon.get() + ")";
    }
}
