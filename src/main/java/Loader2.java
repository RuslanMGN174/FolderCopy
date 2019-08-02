import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Loader2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Пожалуйста, введите путь к папке, которую хотите скопировать.");
        String sourceFolder = scanner.nextLine();

        System.out.println("Пожалуйста, введите путь, по которому хотите разместить копию папки.");
        String destFolder = scanner.nextLine();

        File source = new File(sourceFolder);
        File dest = new File(destFolder);

        try {
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
