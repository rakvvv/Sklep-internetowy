import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Zarządzanie Sklepem Internetowym");

        Button btnKlienci = new Button("Zarządzanie Klientami");
        btnKlienci.setOnAction(e -> new KlientWindow().show());

        Button btnPracownicy = new Button("Zarządzanie Pracownikami");
        btnPracownicy.setOnAction(e -> new PracownikWindow().show());

        Button btnProdukty = new Button("Zarządzanie Produktami");
        btnProdukty.setOnAction(e -> new ProduktWindow().show());

        Button btnZamowienia = new Button("Zarządzanie Zamówieniami");
        btnZamowienia.setOnAction(e -> new ZamowienieWindow().show());

        Button btnPromocje = new Button("Zarządzanie Promocjami");
        btnPromocje.setOnAction(e -> new PromocjaWindow().show());

        Button btnKategorie = new Button("Zarządzanie Kategoriami");
        btnKategorie.setOnAction(e -> new KategoriaWindow().show());

        Button btnOpinie = new Button("Zarządzanie Opiniami");
        btnOpinie.setOnAction(e -> new OpiniaWindow().show());

        Button btnRaporty = new Button("Raporty Sprzedaży");
        btnRaporty.setOnAction(e -> new RaportWindow().show());

        VBox vbox = new VBox(btnKlienci, btnPracownicy, btnProdukty, btnZamowienia, btnPromocje, btnKategorie,btnOpinie, btnRaporty);
        Scene scene = new Scene(vbox, 300, 250);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
