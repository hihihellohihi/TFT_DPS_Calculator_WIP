import champion.*;
import reader.ProcessChamps;
import reader.ProcessItems;
import reader.ProcessTraits;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ProcessTraits traitProcessor = new ProcessTraits();
        ProcessItems itemProcessor = new ProcessItems();
        ProcessChamps champProcessor = new ProcessChamps();
        try {
            traitProcessor.readTraitCSV("src/traits.csv");
            itemProcessor.readItemCSV("src/items.csv");
            champProcessor.readChampCSV("src/champions.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scanner champNameScan = new Scanner(System.in);
        System.out.println("Enter [champion_name star_level]");

        String champName = champNameScan.nextLine();
        Scanner conditional = new Scanner(System.in);
        System.out.println("Do you want conditional items? (Giant Slayer, Guardbreaker, Blue Buff) Y/N");

        String conditionalYN = conditional.nextLine();  // Read user input
        if (conditionalYN.equalsIgnoreCase("y")) {
            ArrayList<Object[]> BIS = FindBIS.FindConditionalBIS(champName, new int[] {2, 2},
                    new int[] {10}, 2 , 15);
            for (Object[] itemSet: BIS) {
                System.out.print(itemSet[0]);
                System.out.print(" ");
                if (itemSet[1] instanceof String[]) {
                    System.out.println(Arrays.toString((String[]) itemSet[1]));
                }
            }
        }
        else {
            ArrayList<Object[]> BIS2 = FindBIS.FindUnconditionalBIS(champName, new int[]{2, 2},
                    new int[]{10}, 2, 15);
            for (Object[] itemSet : BIS2) {
                System.out.print(itemSet[0]);
                System.out.print(" ");
                if (itemSet[1] instanceof String[]) {
                    System.out.println(Arrays.toString((String[]) itemSet[1]));
                }
            }
        }
    }
}