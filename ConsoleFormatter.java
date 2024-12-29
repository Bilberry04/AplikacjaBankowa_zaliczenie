public class ConsoleFormatter {

//-----------------------------------------------------------------------GOTOWE FORMATY-----------------------------------------------------------------------------------

    public static void printInBox(String text) {
        int length = text.length();
        String horizontalBorder = "+" + "-".repeat(length + 2) + "+";
        System.out.println(horizontalBorder);
        System.out.println("| " + text + " |");
        System.out.println(horizontalBorder);
    }

    public static void printSeparator() {
        System.out.println("====================================================================================================");
    }
   
    public static void printHeader(String headerText) {
        String separator = "=".repeat(15);
        System.out.println(separator + "  " + headerText.toUpperCase() + "  " + separator);
    }

    
    public static void printBulletList(String... items) {
        for (String item : items) {
            System.out.println("  - " + item);
        }
    }

    
    public static void printSection(String title, String content) {
        String topAndBottom = "*".repeat(40);
        System.out.println(topAndBottom);
        System.out.println("* " + title);
        System.out.println(topAndBottom);
        System.out.println(content);
        System.out.println(topAndBottom);
    }

//-----------------------------------------------------------------------GOTOWE KWESTIE-----------------------------------------------------------------------------------

    public static void printStartText() {
        System.out.println("=============================================INFORMACJA=============================================");
        System.out.println("                 Witaj w aplikacji bankowej stworzonej na potrzeby projektu");
        System.out.println("Program ten jest uproszczony, więc proszę o przymrużenie oka np. na długość numeru konta bankowego");
        System.out.println("                                     Życzę miłej zabawy");
        System.out.println("====================================================================================================");
        System.out.println("                     Wpisz 'start', aby rozpocząć proces zakładania konta:");
    }
}