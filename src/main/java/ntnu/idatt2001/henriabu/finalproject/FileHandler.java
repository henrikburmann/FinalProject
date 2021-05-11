package ntnu.idatt2001.henriabu.finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static List<PostalCode> readFromFile() throws IOException {
        List<PostalCode> postalCodes = new ArrayList<>();
        BufferedReader bufferedReader = Files.newBufferedReader(Path.of("src/main/resources/postalCodes.csv"));
        String line;

        while((line = bufferedReader.readLine()) != null){
            String [] postalCodeArray = line.split("\t");

            postalCodes.add(new PostalCode(postalCodeArray[0],postalCodeArray[1],postalCodeArray[2],
                    postalCodeArray[3], postalCodeArray[4]));
        }
        bufferedReader.close();
        return postalCodes;
    }
}
