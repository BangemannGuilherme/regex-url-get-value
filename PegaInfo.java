import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.regex.Matcher;

public class PegaInfo {

    public static void main(String[] args) {
        String output = getUrlContents("https://www.univates.br/nih/tempo-real");        
        System.out.println(output);
        System.out.println("---------------------------------------------");
        Pattern result = Pattern.compile("-?\\d+(?:\\.\\d+)?\\s*�\\s*C(?:\\s*-\\s*-?\\d+(?:\\.\\d+)?\\s*�\\s*C)?");
        Matcher m = result.matcher(output);
        if (m.find()) {
            System.out.println("Temperatura:" + m.group());
        }
    }
    
    private static String getUrlContents(String theUrl) {
        StringBuilder content = new StringBuilder() ;

        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");

            }

            bufferedReader.close();

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
