import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * WriteAndRead class writes and read file content
 */
public class WriteAndRead{

    public WriteAndRead() {

    }

    /**
     * Read from file and return text in as a String
     * @param fileName
     * @return String context
     */
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