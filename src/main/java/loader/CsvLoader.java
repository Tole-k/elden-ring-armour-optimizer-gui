package loader;
import item.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class CsvLoader {
    public List<Item> load(String filePath) {
        List<Item> items = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            scanner.useDelimiter("[,\\n]");
            while (scanner.hasNext()) {
                String name=null;
                try {
                    name = scanner.next();
                }catch (InputMismatchException e)
                {
                    System.out.println(scanner.next());
                }
                float[] stats = {scanner.nextFloat(),
                        scanner.nextFloat(),
                        scanner.nextFloat(),
                        scanner.nextFloat(),
                        scanner.nextFloat(),
                        scanner.nextFloat(),
                        scanner.nextFloat(),
                        scanner.nextFloat(),
                        scanner.nextFloat(),
                        scanner.nextFloat(),
                        scanner.nextFloat(),
                        scanner.nextFloat(),
                        scanner.nextFloat()};
                float wgt = scanner.nextFloat();
                Item item = new Item(name,stats,wgt);
                items.add(item);
            }
            return items;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
