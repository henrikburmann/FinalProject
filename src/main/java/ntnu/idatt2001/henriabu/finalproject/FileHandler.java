package ntnu.idatt2001.henriabu.finalproject;

import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidPostalCodeException;
import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidTownException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for reading from the file consisting of all the post places
 */
public class FileHandler {

    /**
     * Method to read the postPlaces.csv file and create PostPlace objects
     * @return A list of all of the postPlaces
     * @throws IOException
     * @throws InvalidPostalCodeException thrown if any of the postal codes are invalid
     * @throws InvalidTownException thrown if any of the towns are invalid
     */
    public static List<PostPlace> readFromFile() throws IOException, InvalidPostalCodeException, InvalidTownException {
        List<PostPlace> postPlaces = new ArrayList<>();
        BufferedReader bufferedReader = Files.newBufferedReader(Path.of("src/main/resources/postPlaces.csv"));
        String line;
        //As long as there is a new line - String line is set equal to that line
        while((line = bufferedReader.readLine()) != null){
            String [] postPlaceArray = line.split("\t"); //Line is split by tabs to separate the variables and put
            // into an array
            //postalPlace objects are made from the the postPlaceArray
            postPlaces.add(new PostPlace(postPlaceArray[0],postPlaceArray[1],postPlaceArray[2],
                    postPlaceArray[3], postPlaceArray[4]));
        }
        bufferedReader.close();
        return postPlaces;
    }
}
