package dec01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Dec01 {

    private static final Map<String, String> map = Map.ofEntries(
            Map.entry("one", "1"),
            Map.entry("two", "2"),
            Map.entry("three", "3"),
            Map.entry("four", "4"),
            Map.entry("five", "5"),
            Map.entry("six", "6"),
            Map.entry("seven", "7"),
            Map.entry("eight", "8"),
            Map.entry("nine", "9"),
            Map.entry("1", "1"),
            Map.entry("2", "2"),
            Map.entry("3", "3"),
            Map.entry("4", "4"),
            Map.entry("5", "5"),
            Map.entry("6", "6"),
            Map.entry("7", "7"),
            Map.entry("8", "8"),
            Map.entry("9", "9"),
            Map.entry("0", "0")
    );

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/dec01/input.txt");
        System.out.println("Answer part one: " + partOne(new Scanner(file)));
        System.out.println("Answer part two: " + partTwo(new Scanner(file)));
    }

    private static int partOne(Scanner scanner) {
        int sum = 0;
        while(scanner.hasNextLine()){
            String nbrs = scanner.nextLine().replaceAll("[^\\d.]","");
            sum += Integer.parseInt(nbrs.charAt(0) + nbrs.substring(nbrs.length()-1));
        }
        return sum;
    }

    private static int partTwo(Scanner scanner) {
        int sum = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] digits = {"", ""};

            for(int i = 0 ; i <= line.length() ; i++){
                if(digits[0].isEmpty()) digits[0] = checkString(line.substring(0,i));
                if(digits[1].isEmpty()) digits[1] = checkString(line.substring(line.length()-i));
            }
            sum += Integer.parseInt(String.join("",digits));
        }
        return sum;
    }

    private static String checkString(String s){
        for (String key : map.keySet()) {
            if (s.contains(key)) {
                return map.get(key);
            }
        }
        return "";
    }
}
