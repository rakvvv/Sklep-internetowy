# ProjektBazyDanych

## Konfiguracja JavaFX w IntelliJ IDEA

Aby poprawnie skonfigurować JavaFX w IntelliJ IDEA, wykonaj następujące kroki:

### Krok 1: Dodanie Ścieżki JavaFX do Modułu

1. Otwórz projekt w IntelliJ IDEA.
2. Przejdź do `File > Project Structure > Modules`.
4. Przejdź do zakładki `Dependencies`.
5. Kliknij ikonę `+` i wybierz `JARs or directories`.
6. Dodaj ścieżkę do katalogu `lib` JavaFX SDK (np. `C:\Scieżka\javafx-sdk-22.0.1\lib`).
7. Upewnij się, że są one oznaczone jako `Compile`.

### Krok 2: Skonfigurowanie Opcji VM w Konfiguracji Uruchamiania

1. Przejdź do `Run > Edit Configurations`.
2. W Main do pola `VM options` dodaj odpwiednią scieżkę (np. `C:\Scieżka\javafx-sdk-22.0.1\lib`).

## Dane do logowania do bazy danych

SQL Developer:

- **Nazwa:** sklepinterentowy
- **Typ bazy danych:** Oracle
- **Nazwa użytkownika:** sklepinterentowy
- **Hasło:** sklepinternetowy
- **Hostname:** localhost
- **Port:** 1521
- **Nazwa usługi:** XEPDB1
