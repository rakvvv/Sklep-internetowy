# ProjektBazyDanych

## Konfiguracja JavaFX w IntelliJ IDEA

Aby poprawnie skonfigurować JavaFX w IntelliJ IDEA, wykonać następujące kroki:

### Krok 1: Dodanie Ścieżki JavaFX do Modułu

1. Otwórzyć projekt w IntelliJ IDEA.
2. Przejść do `File > Project Structure > Modules`.
4. Przejść do zakładki `Dependencies`.
5. Kliknąć ikonę `+` i wybierć `JARs or directories`.
6. Dodać ścieżkę do katalogu `lib` JavaFX SDK (np. `C:\Scieżka\javafx-sdk-22.0.1\lib`).
7. Upewnić się, że są one oznaczone jako `Compile`.

### Krok 2: Skonfigurowanie Opcji VM w Konfiguracji Uruchamiania

1. Przejść do `Run > Edit Configurations`.
2. W Main do pola `VM options` dodać odpwiednią scieżkę (np. `C:\Scieżka\javafx-sdk-22.0.1\lib`).

## Dane do logowania do bazy danych

SQL Developer:

- **Nazwa:** sklepinterentowy
- **Typ bazy danych:** Oracle
- **Nazwa użytkownika:** sklepinterentowy
- **Hasło:** sklepinternetowy
- **Hostname:** localhost
- **Port:** 1521
- **Nazwa usługi:** XEPDB1
