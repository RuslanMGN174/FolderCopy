import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Loader extends SimpleFileVisitor<Path> {
    private final Path targetPath;
    private Path sourcePath = null;

    private Loader(Path targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    public FileVisitResult visitFile(final Path file,
                                     final BasicFileAttributes attrs) throws IOException {
        Files.copy(file,
                targetPath.resolve(sourcePath.relativize(file)));
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(final Path dir,
                                             final BasicFileAttributes attrs) throws IOException {
        if (sourcePath == null) {
            sourcePath = dir;
        } else {
            Files.createDirectories(targetPath.resolve(sourcePath
                    .relativize(dir)));
        }
        return FileVisitResult.CONTINUE;
    }

    public static void main(String[] args) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Пожалуйста, введите путь к папке, которую хотите скопировать.");
        String copyFolder = reader.readLine();

        System.out.println("Пожалуйста, введите путь, по которому хотите разместить копию папки.");
        String folder = reader.readLine();

        try {
            Files.walkFileTree(Paths.get(copyFolder), new Loader(Paths.get(folder)));
            System.out.println("Files copied successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

