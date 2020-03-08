import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class WriteAndRead{

    public WriteAndRead() {

    }
    public static String readFromFile(String fileName)  {
        String text = "";
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String l;
            while ((l = bufferedReader.readLine()) != null) {
                text  = text + l + "\n";
            }

            bufferedReader.close();
        } catch(IOException ioe) {
            System.out.println(ioe);
        }

        return text;
    }
}