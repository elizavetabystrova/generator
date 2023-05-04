import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileWriter fileWriter = new FileWriter("test data.cdr");
            Generator.generateTestData(fileWriter);
            fileWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
