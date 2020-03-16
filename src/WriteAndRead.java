import java.io.*;

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

    /**
     * Write data to the file
     * @param filename
     * @param text
     */
    public static void writeToFile(String filename, String text) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));
            bufferedWriter.write(text);
            bufferedWriter.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}