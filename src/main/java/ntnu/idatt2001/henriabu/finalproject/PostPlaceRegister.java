package ntnu.idatt2001.henriabu.finalproject;

import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidPostalCodeException;
import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidTownException;

import java.util.ArrayList;
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
     * Loops through the list and compares each object to the
     * @param postPlace
     * @return
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

     public ArrayList<PostPlace> getRegister(){
        return register;
     }

     public List<PostPlace> searchByPostalCode(String code) throws InvalidPostalCodeException {
        int length = code.length();
         if (length>4 || (length>0 && !( code.matches("[0-9]+")))){
             throw new InvalidPostalCodeException("Postal code must me 4 digits long and integers only");
         }
        List<PostPlace> placesWithRequestedCode =
                 register.stream().filter(e-> e.getPostalCode().substring(0, length).equals(code)).
                         collect(Collectors.toList());
        return placesWithRequestedCode;
    }

    public List<PostPlace> searchByTown(String town) throws InvalidTownException {
        int length = town.length();
        if (length>0 && !town.matches("[a-zA-zæøåÆØÅ\\s-]+")){
            throw new InvalidTownException("Postal code can only consist of letters");
        }
        List<PostPlace> placesWithRequestedTown = new ArrayList<>();
        for (PostPlace p: register){
            if (p.getTown().length()<length){
                if (p.getTown().equalsIgnoreCase(town)){
                    placesWithRequestedTown.add(p);}
            }
            else if (p.getTown().substring(0, length).equalsIgnoreCase(town)){
                placesWithRequestedTown.add(p);
            }
        }
        return placesWithRequestedTown;
    }
}
