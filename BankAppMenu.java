import java.util.Scanner;

public class BankAppMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //----------------------------------------------------------------------- TWORZENIE KONTA -----------------------------------------------------------------------------------
        ConsoleUtils.clearConsole();
        ConsoleFormatter.printStartText();
        String userInput = scanner.nextLine();
        ConsoleUtils.clearConsole();

        if ("start".equalsIgnoreCase(userInput)) {
            BankClient newClient = createBankClient(scanner);

            ConsoleUtils.clearConsole();
            ConsoleFormatter.printSeparator();
            System.out.println("Konto zostało utworzone pomyślnie!");
            ConsoleFormatter.printSeparator();

            // Wyświetlamy dane nowego klienta
            System.out.print(newClient);

            ConsoleFormatter.printSeparator();
            System.out.println("Zapamiętaj swoje dane");
            ConsoleFormatter.printSeparator();

            //----------------------------------------------------------------------- LOGOWANIE (do zaimplementowania) -----------------------------------------------------------------------------------
            

        } else {
            System.out.println("Nieprawidłowa komenda. Spróbuj ponownie!");
        }

        scanner.close();
    }

    //--------------------------------------------------------------- TWORZENIE KONTA - WPROWADZANIE DANYCH -----------------------------------------------------------------------------------
    private static BankClient createBankClient(Scanner scanner) {
        System.out.print("Podaj swoje imię: ");
        String firstName = scanner.nextLine();

        System.out.print("Podaj swoje nazwisko: ");
        String lastName = scanner.nextLine();

        System.out.print("Podaj swój adres: ");
        String address = scanner.nextLine();

        System.out.print("Podaj datę urodzenia (np. 1990-05-12): ");
        String dateOfBirth = scanner.nextLine();

        System.out.print("Podaj swój PESEL: ");
        String pesel = scanner.nextLine();

        // Nowe pole: login
        System.out.print("Podaj swój login: ");
        String login = scanner.nextLine();

        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();

        System.out.print("Podaj adres e-mail: ");
        String email = scanner.nextLine();

        // Tworzymy nowego BankClienta z uwzględnieniem loginu
        return new BankClient(
                firstName,
                lastName,
                address,
                dateOfBirth,
                pesel,
                login,
                password,
                email
        );
    }
}