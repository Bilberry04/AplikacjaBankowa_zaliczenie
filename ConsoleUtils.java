//-----------------------------------------------------------------------GOTOWE FUNKCJE KONSOLOWE-----------------------------------------------------------------------------------
public class ConsoleUtils {
//CZYSZCZENIE EKRANU
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}