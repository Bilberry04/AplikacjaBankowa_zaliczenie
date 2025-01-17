public class MobileAccount {

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        BankClient client = new BankClient("Jan", "Kowalski", "Warszawa, ul. Prosta 1", "1990-01-01", "90010112345", "jan123", "Haslo123!", "jan.kowalski@example.com");
        boolean running = true;

        while (running) {
            logIn(); 

            System.out.print("Wybierz opcję (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    checkAccountBalance(client);
                    break;
                case "2":
                    performTransfer(scanner, client);
                    break;
                case "3":
                    openNewAccount(scanner, client);
                    break;
                case "4":
                    changeEmail(scanner, client);
                    break;
                case "5":
                    changePassword(scanner, client);
                    break;
                case "6":
                    System.out.println("Dziękujemy za skorzystanie z aplikacji. Do zobaczenia!");
                    running = false; 
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                    break;
            }
        }

        scanner.close();
    }

    public static void logIn() {
        ConsoleFormatter.printHeader("Witaj na stronie banku, co chcesz zrobić?");
        System.out.println("1. Sprawdź stan konta");
        System.out.println("2. Wykonaj przelew");
        System.out.println("3. Załóż nowy rachunek");
        System.out.println("4. Zmień adres email");
        System.out.println("5. Zmień hasło");
        System.out.println("6. Wyjdź");
    }

    private static void checkAccountBalance(BankClient client) {
        ConsoleFormatter.printHeader("Stan konta");
        client.getAccounts().forEach((currency, balance) ->
                System.out.println("Waluta: " + currency + ", Saldo: " + balance + " PLN"));
        ConsoleFormatter.printSeparator();
    }

    private static void performTransfer(java.util.Scanner scanner, BankClient client) {
        ConsoleFormatter.printHeader("Wykonaj przelew");
        System.out.print("Podaj numer konta odbiorcy: ");
        String accountNumber = scanner.nextLine().trim();

        System.out.print("Podaj kwotę przelewu: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 

        if (client.getAccounts().get("PLN") >= amount) {
            client.deposit("PLN", -amount); 
            System.out.println("Przelew na konto " + accountNumber + " o wartości " + amount + " PLN został wykonany.");
        } else {
            System.out.println("Niewystarczające środki na koncie.");
        }
        ConsoleFormatter.printSeparator();
    }

    private static void openNewAccount(java.util.Scanner scanner, BankClient client) {
        ConsoleFormatter.printHeader("Zakładanie nowego rachunku");
        System.out.print("Podaj walutę nowego rachunku (PLN, USD, EUR): ");
        String currency = scanner.nextLine().trim().toUpperCase();

        if (!client.getAccounts().containsKey(currency)) {
            client.addAccount(currency);
            System.out.println("Utworzono nowy rachunek w walucie: " + currency);
        } else {
            System.out.println("Masz już rachunek w tej walucie.");
        }
        ConsoleFormatter.printSeparator();
    }

    private static void changeEmail(java.util.Scanner scanner, BankClient client) {
        ConsoleFormatter.printHeader("Zmiana adresu email");
        System.out.print("Podaj nowy adres email: ");
        String newEmail = scanner.nextLine().trim();

        if (validateEmail(newEmail)) {
            client.setEmail(newEmail);
            System.out.println("Adres email został zmieniony na: " + newEmail);
        } else {
            System.out.println("Nieprawidłowy format adresu email.");
        }
        ConsoleFormatter.printSeparator();
    }

    private static void changePassword(java.util.Scanner scanner, BankClient client) {
        ConsoleFormatter.printHeader("Zmiana hasła");
        System.out.print("Podaj nowe hasło: ");
        String newPassword = scanner.nextLine().trim();

        if (validatePassword(newPassword)) {
            client.setPassword(newPassword);
            System.out.println("Hasło zostało zmienione.");
        } else {
            System.out.println("Hasło musi zawierać co najmniej 8 znaków, w tym 1 dużą literę, 1 małą literę i 1 cyfrę.");
        }
        ConsoleFormatter.printSeparator();
    }

    private static boolean validateEmail(String email) {
        String regex = "^[^@]+@[^@]+\\.[^@]+$";
        return email.matches(regex);
    }

    private static boolean validatePassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        return password.matches(regex);
    }
}