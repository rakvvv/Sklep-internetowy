public class Raport {
    private String nazwa;
    private String kategoria;
    private double zarobek;
    private int iloscSprzedanych;
    private double sredniaOcena;

    public Raport(String nazwa, String kategoria, double zarobek, int iloscSprzedanych, double sredniaOcena) {
        this.nazwa = nazwa;
        this.kategoria = kategoria;
        this.zarobek = zarobek;
        this.iloscSprzedanych = iloscSprzedanych;
        this.sredniaOcena = sredniaOcena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public double getZarobek() {
        return zarobek;
    }

    public void setZarobek(double zarobek) {
        this.zarobek = zarobek;
    }

    public int getIloscSprzedanych() {
        return iloscSprzedanych;
    }

    public void setIloscSprzedanych(int iloscSprzedanych) {
        this.iloscSprzedanych = iloscSprzedanych;
    }

    public double getSredniaOcena() {
        return sredniaOcena;
    }

    public void setSredniaOcena(double sredniaOcena) {
        this.sredniaOcena = sredniaOcena;
    }
}
