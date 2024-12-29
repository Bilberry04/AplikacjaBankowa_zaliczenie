import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BankClient {

    // Statyczne pola do pilnowania, które PIN-y i numery kont są już użyte
    private static final Set<Integer> usedPins = new HashSet<>();
    private static final Set<Long> usedAccountNumbers = new HashSet<>();
    private static final Random RANDOM = new Random();

    // Nowe pole – 4-cyfrowy kod do karty kredytowej
    private int creditCardPin;

    // 10-cyfrowy numer konta
    private long accountNumber;

    // Dodatkowe pole login (zamiast loginName)
    private String login;

    // Pozostałe pola (jak wcześniej)
    private String firstName;
    private String lastName;
    private String address;
    private String dateOfBirth; 
    private String pesel;
    private String password;
    private String email;

    /**
     * Konstruktor bezargumentowy.
     * Generuje automatycznie PIN i numer konta.
     */
    public BankClient() {
        this.creditCardPin = generateUniquePin();
        this.accountNumber = generateUniqueAccountNumber();
    }

    /**
     * Konstruktor z wszystkimi danymi.
     * Generuje automatycznie PIN i numer konta.
     */
    public BankClient(String firstName,
                      String lastName,
                      String address,
                      String dateOfBirth,
                      String pesel,
                      String login,
                      String password,
                      String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.pesel = pesel;
        this.login = login;
        this.password = password;
        this.email = email;

        // Generujemy unikalny PIN i numer konta
        this.creditCardPin = generateUniquePin();
        this.accountNumber = generateUniqueAccountNumber();
    }

    // --- GETTERY / SETTERY ---

    public int getCreditCardPin() {
        return creditCardPin;
    }
    
    public long getAccountNumber() {
        return accountNumber;
    }

    // Zwykle PIN i nr konta są stałe, więc brak settera. 
    // Ale jeśli chcesz, możesz dodać:
    // public void setCreditCardPin(int pin) {...}
    // public void setAccountNumber(long account) {...}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // --- Metody generujące unikalne wartości ---

    /**
     * Generuje unikalny 4-cyfrowy PIN do karty (1000 - 9999).
     */
    private static int generateUniquePin() {
        while (true) {
            // Losujemy z przedziału 0..8999 i dodajemy 1000 => 1000..9999
            int candidate = 1000 + RANDOM.nextInt(9000);
            if (!usedPins.contains(candidate)) {
                usedPins.add(candidate);
                return candidate;
            }
        }
    }

    /**
     * Generuje unikalny 10-cyfrowy numer konta (1000000000..9999999999).
     */
    private static long generateUniqueAccountNumber() {
        while (true) {
            // Zakres [1_000_000_000..9_999_999_999]
            long candidate = 1_000_000_000L + (long) (RANDOM.nextDouble() * 9_000_000_000L);
            if (!usedAccountNumbers.contains(candidate)) {
                usedAccountNumbers.add(candidate);
                return candidate;
            }
        }
    }

    // --- toString() ---

    @Override
    public String toString() {
        return
                "----------------------------------------\n"
              + "        DANE NOWEGO KLIENTA BANKU       \n"
              + "----------------------------------------\n"
              + "PIN (karta):    " + creditCardPin + "\n"
              + "Nr konta:       " + accountNumber + "\n"
              + "Login:          " + login + "\n"
              + "Imię:           " + firstName + "\n"
              + "Nazwisko:       " + lastName + "\n"
              + "Adres:          " + address + "\n"
              + "Data urodzenia: " + dateOfBirth + "\n"
              + "PESEL:          " + pesel + "\n"
              + "Hasło:          " + password + "\n"
              + "E-mail:         " + email + "\n"
              + "----------------------------------------\n";
    }
}