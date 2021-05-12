package ntnu.idatt2001.henriabu.finalproject;

import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidPostalCodeException;
import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidTownException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class responsible for storing the PostPlaces and handling the methods that use the register.
 */
public class PostPlaceRegister {
    private ArrayList<PostPlace> register;
        public PostPlaceRegister(){
        this.register = new ArrayList<>();
    }

    /**
     * Loops through the list and compares each object to the PostPlace being added.
     * Uses the equals method from the PostPlace class to check if the object
     * being added is equal (has the the postal code) to any other of the post
     * places in the list. This is to avoid duplicates being added to the list
     * @param postPlace The postPlace being added
     * @return True if there are no equal postPlaces already in the register.
     * False if there are.
     */
    public boolean addPostPlace(PostPlace postPlace){
        for (PostPlace p: register){
            if (postPlace.equals(p)){
                return false;
            }
        }
        register.add(postPlace);
        return true;
    }

    /**
     *
     * @return The postPlace register.
     */
     public ArrayList<PostPlace> getRegister(){
        return register;
     }

    /**
     * Method to collect all the postPlaces with requested postal code from the register. Finds all the postPlaces
     * where the postal code's substring from index 0 to the index of the requested code's length is equal to the
     * requested code. This makes it possible to for example search for all the postal codes on Romerike (starts with
     * 20)
     * @param code The postal code that is being searched for
     * @return A list of the PostPlaces with starting with the requested code
     * @throws InvalidPostalCodeException Thrown if the passed code is invalid
     */
     public List<PostPlace> searchByPostalCode(String code) throws InvalidPostalCodeException {
         //Sets int length equal to the length of the requested code
         int length = code.length();
         //Postal codes are 4 digits long and consists of only integers. Therefore an InvalidPostalCodeException
         //is thrown if the length is larger than 4 or contains non-integers. Since you can search for the begin
         //of a code exceptions aren't thrown for lengths shorter than 4.
         if (length>4 || (length>0 && !( code.matches("[0-9]+")))){//To avoid exceptions being thrown when the
             // passed code is empty, after the user deletes what it has written - length must be larger than zero to
             // throw InvalidPostalCodeExceptions
             throw new InvalidPostalCodeException("Postal code must me 4 digits long and integers only");
         }
         // Streams through the register and filtrating and collecting the postPlaces which substring
         //from index 0 to index length equals the requested code, and sets list placesWithRequested code equal
         //to this
        List<PostPlace> placesWithRequestedCode =
                 register.stream().filter(e-> e.getPostalCode().substring(0, length).equals(code)).
                         collect(Collectors.toList());
        return placesWithRequestedCode;
    }

    /**
     * Method that loops through the register and collects all the postPlaces who's town's string is equal
     * to the requested town's, or if the passed town's string is shorter than the town's it is being compared to:
     * the towns where the substring from index 0 to the index of the requested town string's length is equal to
     * the requested town's string are collected. In this way you can for example search for all the
     * towns beginning with Os.
     * @param town The requested town
     * @return A list of the postPlaces who's town is equal to, or it's beginning is equal to, the requested town
     * @throws InvalidTownException Thrown if the requested town is invalid
     */
    public List<PostPlace> searchByTown(String town) throws InvalidTownException {
        //Sets int length equal to the requested town's length
        int length = town.length();
        //If the length is larger than zero and the string contains a char which isn't in the norwegian alphabet, a
        // whitespace or a dash InvalidTownException is thrown. To avoid exceptions being thrown when the
        //passed town is empty, after the user deletes what it has written - length must be larger than zero to
        //throw InvalidTownException
        if (length>0 && !town.matches("[a-zA-zæøåÆØÅ\\s-]+")){
            throw new InvalidTownException("Postal code can only consist of letters");
        }

        //Since the different towns have different length a for loop is used with a double if-statement for only
        // checking the town's which strings are at least the length of int length to avoid trying to make a
        // substring that is larger than the string it self and therefore avoiding an StringIndexOutOfBoundsException
        List<PostPlace> placesWithRequestedTown = new ArrayList<>();
        for (PostPlace p: register){
            if (p.getTown().length()>=length){
                //If the beginning of the town string is equal to the requested town string, or if the strings are
                // identical, the postPlace is added to the list
                if (p.getTown().substring(0, length).equalsIgnoreCase(town)){
                    placesWithRequestedTown.add(p);
                }
            }
        }
        //If the string isn't empty the list is sorted by the length of the town string in ascending order. This is
        // so you for example actually get OS on the top instead of OSLO when searching for "os".
        if (length >0){
            placesWithRequestedTown.sort(Comparator.comparingInt(s -> s.getTown().length()));}
        //If the string is empty all the objects from the register will be in the list and it will therefore be
        // sorted by postalCodes as standard.
        else {
            placesWithRequestedTown.sort(Comparator.comparingInt(s -> s.getPostalCode().length()));
        }
        return placesWithRequestedTown;
    }

    /**
     * Method to get all the postPlaces in a category
     * @param category The category searched for
     * @return The list of all postPlaces in a category
     */
    public List<PostPlace> searchByCategory(String category){
        //Filters all the postPlaces with the requested category and collects them to a list. Sets list
        // allInChosenCategory equal to this
        List<PostPlace> allInChosenCategory =
                register.stream().filter(e-> e.getCategory().equals(category)).collect(Collectors.toList());
        return allInChosenCategory;
    }
}
