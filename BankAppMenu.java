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

            System.out.print(newClient);

            ConsoleFormatter.printSeparator();
            System.out.println("Zapamiętaj swoje dane");
            ConsoleFormatter.printSeparator();

            // ----------------------------------------------------------------------- MENU PO UTWORZENIU KONTA -----------------------------------------------------------------------------------
            mainMenuLoop(scanner, newClient);

        } else {
            System.out.println("Nieprawidłowa komenda. Spróbuj ponownie!");
        }

        scanner.close();
    }


    private static BankClient createBankClient(Scanner scanner) {

       
        String firstName;
        do {
            System.out.print("Podaj swoje imię: ");
            firstName = scanner.nextLine().trim();
            if (firstName.isEmpty()) {
                System.out.println("Imię jest wymagane. Spróbuj ponownie.");
                continue;
            }
            if (!validateName(firstName)) {
                System.out.println("Niepoprawne imię (min. 2 litery, tylko a-z/A-Z). Spróbuj ponownie.");
                firstName = "";
            }
        } while (firstName.isEmpty());

       
        String lastName;
        do {
            System.out.print("Podaj swoje nazwisko: ");
            lastName = scanner.nextLine().trim();
            if (lastName.isEmpty()) {
                System.out.println("Nazwisko jest wymagane. Spróbuj ponownie.");
                continue;
            }
            if (!validateName(lastName)) {
                System.out.println("Niepoprawne nazwisko (min. 2 litery, tylko a-z/A-Z). Spróbuj ponownie.");
                lastName = "";
            }
        } while (lastName.isEmpty());

      
        String address;
        do {
            System.out.print("Podaj swój adres: ");
            address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println("Adres jest wymagany. Spróbuj ponownie.");
            }
        } while (address.isEmpty());

        
        String dateOfBirth;
        while (true) {
            System.out.print("Podaj datę urodzenia (np. 1990-05-12): ");
            dateOfBirth = scanner.nextLine().trim();
            if (dateOfBirth.isEmpty()) {
                System.out.println("Data urodzenia jest wymagana. Spróbuj ponownie.");
                continue;
            }
            if (validateDateOfBirth(dateOfBirth)) {
                break;
            } else {
                System.out.println("Niepoprawna data. Format musi być RRRR-MM-DD i być realną datą.");
            }
        }

      
        String pesel;
        while (true) {
            System.out.print("Podaj swój PESEL: ");
            pesel = scanner.nextLine().trim();
            if (pesel.isEmpty()) {
                System.out.println("PESEL jest wymagany. Spróbuj ponownie.");
                continue;
            }
            if (!validatePesel(pesel)) {
                System.out.println("Niepoprawny PESEL (musi mieć 11 cyfr). Spróbuj ponownie.");
            } else {
                break;
            }
        }

        
        String login;
        do {
            System.out.print("Podaj swój login: ");
            login = scanner.nextLine().trim();
            if (login.isEmpty()) {
                System.out.println("Login jest wymagany. Spróbuj ponownie.");
            }
        } while (login.isEmpty());

        String password;
        while (true) {
            System.out.print("Podaj hasło: ");
            password = scanner.nextLine();
            if (password.isEmpty()) {
                System.out.println("Hasło jest wymagane. Spróbuj ponownie.");
                continue;
            }
            if (!validatePassword(password)) {
                System.out.println("Hasło musi mieć co najmniej 8 znaków," +
                        " zawierać przynajmniej 1 dużą literę, 1 małą literę i 1 cyfrę.");
            } else {
                break;
            }
        }

        
        String email;
        while (true) {
            System.out.print("Podaj adres e-mail: ");
            email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("E-mail jest wymagany. Spróbuj ponownie.");
                continue;
            }
            if (!validateEmail(email)) {
                System.out.println("Niepoprawny format e-maila. Spróbuj ponownie.");
            } else {
                break;
            }
        }

     
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

    
    private static void mainMenuLoop(Scanner scanner, BankClient client) {
        boolean running = true;

        while (running) {
            ConsoleUtils.clearConsole();
            ConsoleFormatter.printSeparator();
            System.out.println("Co chcesz teraz zrobić?");
            System.out.println("[1] Zaloguj się do bankowości internetowej");
            System.out.println("[2] Skorzystaj z bankomatu");
            System.out.println("[3] Opuść aplikację");
            ConsoleFormatter.printSeparator();

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleInternetBanking(scanner, client);
                    break;

                case "2":
                    handleAtm(scanner, client);
                    break;

                case "3":
                    System.out.println("Dziękujemy za skorzystanie z aplikacji. Do zobaczenia!");
                    running = false; 
                    break;

                default:
                    System.out.println("Nieprawidłowa komenda. Spróbuj ponownie!");
                    break;
            }
        }
    }

    private static void handleInternetBanking(Scanner scanner, BankClient client) {
        ConsoleUtils.clearConsole();
        ConsoleFormatter.printSeparator();
        System.out.println("=== BANKOWOŚĆ INTERNETOWA ===");
        System.out.println("Wpisz 'zaloguj', aby się zalogować, 'powrót' aby wrócić do menu głównego.");
        ConsoleFormatter.printSeparator();

        String command = scanner.nextLine().trim();
        if ("zaloguj".equalsIgnoreCase(command)) {
            
            System.out.print("Podaj login: ");
            String inputLogin = scanner.nextLine().trim();

            System.out.print("Podaj hasło: ");
            String inputPassword = scanner.nextLine().trim();

           
            if (client.getLogin().equals(inputLogin) && client.getPassword().equals(inputPassword)) {
                System.out.println("Zalogowano pomyślnie użytkownika: " + client.getLogin());
                System.out.println("Wpisz 'wyloguj', aby się wylogować.");

                while (true) {
                    String innerCommand = scanner.nextLine().trim();
                    if ("wyloguj".equalsIgnoreCase(innerCommand)) {
                        System.out.println("Wylogowano z bankowości internetowej.");
                        break;
                    } else {
                        System.out.println("Nieznana komenda. Użyj: 'wyloguj'.");
                    }
                }
            } else {
                System.out.println("Błędny login lub hasło. Spróbuj ponownie.");
            }
        } else if ("powrót".equalsIgnoreCase(command)) {
            
            return;
        } else {
            System.out.println("Nieprawidłowa komenda. Powrót do menu głównego...");
        }
    }

   
    private static void handleAtm(Scanner scanner, BankClient client) {
        ConsoleUtils.clearConsole();
        ConsoleFormatter.printSeparator();
        System.out.println("=== BANKOMAT ===");
        System.out.println("Wpisz 'wloz' aby włożyć kartę, 'powrót' aby wrócić do menu głównego.");
        ConsoleFormatter.printSeparator();

        String command = scanner.nextLine().trim();
        if ("wloz".equalsIgnoreCase(command)) {
            System.out.println("Włożyłeś kartę. Podaj 4-cyfrowy PIN do karty:");
            String pinInput = scanner.nextLine().trim();

            try {
                int pin = Integer.parseInt(pinInput);
                if (pin == client.getCreditCardPin()) {
                    System.out.println("PIN poprawny! Jesteś zalogowany w bankomacie.");
                    System.out.println("Wpisz 'zakoncz', aby zakończyć korzystanie.");

                    while (true) {
                        String atmCommand = scanner.nextLine().trim();
                        if ("zakoncz".equalsIgnoreCase(atmCommand)) {
                            System.out.println("Zakończono korzystanie z bankomatu.");
                            break;
                        } else {
                            System.out.println("Nieznana komenda. Użyj: 'zakoncz'.");
                        }
                    }
                } else {
                    System.out.println("Błędny PIN. Odrzucono dostęp.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Błędny format PINu. Musi być 4-cyfrową liczbą.");
            }
        } else if ("powrót".equalsIgnoreCase(command)) {
           
            return;
        } else {
            System.out.println("Nieprawidłowa komenda. Powrót do menu głównego...");
        }
    }

    // ---------------------------------------------------------------------------------
    // -------------------- METODY WALIDUJĄCE --------------------
    // ---------------------------------------------------------------------------------


    private static boolean validateName(String name) {
        if (name.length() < 2) {
            return false;
        }
       
        return name.matches("[a-zA-Z]+");
    }

   
   
    private static boolean validateDateOfBirth(String dateStr) {
        try {
            java.time.LocalDate.parse(dateStr);  
            return true;
        } catch (Exception e) {
            return false;
        }
    }

  
    private static boolean validatePesel(String pesel) {
        return pesel.matches("\\d{11}");
    }

   
    private static boolean validatePassword(String password) {
        
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        return password.matches(regex);
    }

  
    private static boolean validateEmail(String email) {
        String regex = "^[^@]+@[^@]+\\.[^@]+$";
        return email.matches(regex);
    }
}