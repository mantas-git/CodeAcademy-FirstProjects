import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tank tank = new Tank();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        printOptions();
        while(!exit){
            String command = scanner.nextLine();
            switch (command){
                case "w":
                    tank.goNorth();
                    break;
                case "s":
                    tank.goSouth();
                    break;
                case "d":
                    tank.goEast();
                    break;
                case "a":
                    tank.goWest();
                    break;
                case " ":
                    tank.makeShot();
                    break;
                case "i":
                    tank.getInfo();
                    break;
                case "m":
                    printOptions();
                    break;
                case "x":
                    exit = true;
                    break;
                default:
                    System.out.println("Neatpažinta komanda");
                    break;
            }
        }
        scanner.close();
    }

    private static void printOptions() {
        System.out.println("Pasirinkite norimą veiksmą: ");
        System.out.println("Pirmyn į Šiaurę (w)" );
        System.out.println("Atgal į Pietus (s)");
        System.out.println("Dešinėns į Rytus (d)");
        System.out.println("Kairėn į Vakarus (a)");
        System.out.println("Šauti ('tarpas')");
        System.out.println("Informacija apie tanką (i)");
        System.out.println("Pakartoti komandų sąrašą (m)");
        System.out.println(("Išeiti (x)"));
    }
}