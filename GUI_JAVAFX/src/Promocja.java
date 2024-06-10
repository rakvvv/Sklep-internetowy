import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class Promocja {
    private SimpleIntegerProperty id;
    private SimpleStringProperty nazwa;
    private SimpleStringProperty opis;
    private SimpleObjectProperty<LocalDate> dataRozpoczecia;
    private SimpleObjectProperty<LocalDate> dataZakonczenia;
    private SimpleDoubleProperty procentObnizki;

    public Promocja(int id, String nazwa, String opis, LocalDate dataRozpoczecia, LocalDate dataZakonczenia, double procentObnizki) {
        this.id = new SimpleIntegerProperty(id);
        this.nazwa = new SimpleStringProperty(nazwa);
        this.opis = new SimpleStringProperty(opis);
        this.dataRozpoczecia = new SimpleObjectProperty<>(dataRozpoczecia);
        this.dataZakonczenia = new SimpleObjectProperty<>(dataZakonczenia);
        this.procentObnizki = new SimpleDoubleProperty(procentObnizki);
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

    public LocalDate getDataRozpoczecia() {
        return dataRozpoczecia.get();
    }

    public void setDataRozpoczecia(LocalDate dataRozpoczecia) {
        this.dataRozpoczecia.set(dataRozpoczecia);
    }

    public SimpleObjectProperty<LocalDate> dataRozpoczeciaProperty() {
        return dataRozpoczecia;
    }

    public LocalDate getDataZakonczenia() {
        return dataZakonczenia.get();
    }

    public void setDataZakonczenia(LocalDate dataZakonczenia) {
        this.dataZakonczenia.set(dataZakonczenia);
    }

    public SimpleObjectProperty<LocalDate> dataZakonczeniaProperty() {
        return dataZakonczenia;
    }

    public double getProcentObnizki() {
        return procentObnizki.get();
    }

    public void setProcentObnizki(double procentObnizki) {
        this.procentObnizki.set(procentObnizki);
    }

    public SimpleDoubleProperty procentObnizkiProperty() {
        return procentObnizki;
    }

    @Override
    public String toString() {
        return nazwa.get() + " (" + procentObnizki.get() + "%)";
    }
}
