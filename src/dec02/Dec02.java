package dec02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Dec02 {

    private static final Map<String, Integer> MAX_COLOR = Map.of("red", 12, "green", 13, "blue", 14);

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/dec02/input.txt");
        System.out.println("Answer part one: " + partOne(new Scanner(file)));
        System.out.println("Answer part two: " + partTwo(new Scanner(file)));
    }

    private static int partOne(Scanner scanner) {
        int sum = 0;
        while (scanner.hasNextLine()){
            String[] line = scanner.nextLine().split(": |; |;");
            if(gameIsPossible(Arrays.copyOfRange(line, 1, line.length))){
                sum += Integer.parseInt(line[0].replaceAll("[^0-9]",""));
            }
        }
        return sum;
    }

    private static boolean gameIsPossible(String[] game) {
        return Arrays.stream(game).allMatch(set -> setIsPossible(set));
    }

    private static boolean setIsPossible(String set){
        return Arrays.stream(set.split(", "))
                .map(cube -> cube.split(" "))
                .allMatch(c -> (MAX_COLOR.get(c[1]) >= Integer.parseInt(c[0])));
    }

    private static int partTwo(Scanner scanner) {
        int sum = 0;
        while (scanner.hasNextLine()){
            Map<String, Integer> minCubeAmount = new HashMap<>(Map.of("red", 0, "blue", 0, "green", 0));
            String[] line = scanner.nextLine().split(": |; |;");
            updateIfSmallest(Arrays.copyOfRange(line, 1, line.length), minCubeAmount);
            sum += minCubeAmount.values().stream().reduce(1, (x,y) -> x * y);
        }
        return sum;
    }

    private static void updateIfSmallest(String[] game, Map<String, Integer> map){
        for(String set: game){
            Arrays.stream(set.split(", "))
                    .map(cube -> cube.split(" "))
                    .forEach(cube -> map.put(cube[1], Math.max(map.get(cube[1]),Integer.parseInt(cube[0]))));
        }
    }

}
