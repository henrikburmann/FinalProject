package ntnu.idatt2001.henriabu.finalproject;

import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidPostalCodeException;
import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidTownException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static List<PostPlace> readFromFile() throws IOException, InvalidPostalCodeException, InvalidTownException {
        List<PostPlace> postalCodes = new ArrayList<>();
        BufferedReader bufferedReader = Files.newBufferedReader(Path.of("src/main/resources/postalCodes.csv"));
        String line;

        while((line = bufferedReader.readLine()) != null){
            String [] postalCodeArray = line.split("\t");

            postalCodes.add(new PostPlace(postalCodeArray[0],postalCodeArray[1],postalCodeArray[2],
                    postalCodeArray[3], postalCodeArray[4]));
        }
        bufferedReader.close();
        return postalCodes;
    }
}
