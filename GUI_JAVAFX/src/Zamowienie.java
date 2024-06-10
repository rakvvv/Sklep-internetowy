import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class Zamowienie {
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty klientId;
    private SimpleIntegerProperty pracownikId;
    private SimpleIntegerProperty ilosc;
    private SimpleObjectProperty<LocalDate> dataZamowienia;
    private SimpleStringProperty status;
    private SimpleDoubleProperty kwotaRazem;

    public Zamowienie(int id, int klientId, int pracownikId, int ilosc, LocalDate dataZamowienia, String status, double kwotaRazem) {
        this.id = new SimpleIntegerProperty(id);
        this.klientId = new SimpleIntegerProperty(klientId);
        this.pracownikId = new SimpleIntegerProperty(pracownikId);
        this.ilosc = new SimpleIntegerProperty(ilosc);
        this.dataZamowienia = new SimpleObjectProperty<>(dataZamowienia);
        this.status = new SimpleStringProperty(status);
        this.kwotaRazem = new SimpleDoubleProperty(kwotaRazem);
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

    public int getKlientId() {
        return klientId.get();
    }

    public void setKlientId(int klientId) {
        this.klientId.set(klientId);
    }

    public SimpleIntegerProperty klientIdProperty() {
        return klientId;
    }

    public int getPracownikId() {
        return pracownikId.get();
    }

    public void setPracownikId(int pracownikId) {
        this.pracownikId.set(pracownikId);
    }

    public SimpleIntegerProperty pracownikIdProperty() {
        return pracownikId;
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

    public LocalDate getDataZamowienia() {
        return dataZamowienia.get();
    }

    public void setDataZamowienia(LocalDate dataZamowienia) {
        this.dataZamowienia.set(dataZamowienia);
    }

    public SimpleObjectProperty<LocalDate> dataZamowieniaProperty() {
        return dataZamowienia;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public double getKwotaRazem() {
        return kwotaRazem.get();
    }

    public void setKwotaRazem(double kwotaRazem) {
        this.kwotaRazem.set(kwotaRazem);
    }

    public SimpleDoubleProperty kwotaRazemProperty() {
        return kwotaRazem;
    }
}
