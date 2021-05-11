package ntnu.idatt2001.henriabu.finalproject;

import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidPostalCodeException;
import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidTownException;

/**
 * Class representing, in the lack of a better word, a Post Place
 */
public class PostPlace {
    private String postalCode;
    private String town;
    private String communeCode;
    private String communeName;
    private String category;

    /**
     *
     * @param postalCode The postal code. Must only consist of integers and be 4 digits lon
     * @param town The town where the post place is located. Must only consist of the
     *                     letters in the norwegian alphabet, whitespaces and dashes.
     * @param communeCode The commune code where the town is located
     * @param communeName The name of the commune
     * @param category  The category of post place. Could be street addresses, post boxes ect.
     * @throws InvalidPostalCodeException Thrown if the postalCode is invalid
     * @throws InvalidTownException Thrown if the town name is invalid
     */
    public PostPlace(String postalCode, String town, String communeCode, String communeName,
                     String category) throws InvalidPostalCodeException, InvalidTownException {
        if (!(postalCode.matches("[0-9]+")) || !(postalCode.length() == 4)){
            throw new InvalidPostalCodeException("Postal code must me 4 digits long and integers only");
        }
        this.postalCode = postalCode;
        if (!town.matches("[a-zA-ZæøåÆØÅ\\s-]+")){
            throw new InvalidTownException("Postal code can only consist of letters, whitespaces" +
                    " and dashes");
        }
        this.town = town;
        if (communeCode == null || communeCode.equals("")){
            throw new IllegalArgumentException("Commune code empty");
        }
        this.communeCode = communeCode;
        if (communeName == null || communeName.equals("")){
            throw new IllegalArgumentException("Commune name empty");
        }
        this.communeName = communeName;
        this.category = category;
    }

    public String getCommuneCode() {
        return communeCode;
    }

    public String getCommuneName() {
        return communeName;
    }

    /**
     *
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @return town
     */
    public String getTown() {
        return town;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Equals method
     * @param o
     * @return
     */
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        else if(!(o instanceof PostPlace)){
            return false;
        }
        PostPlace postPlace =(PostPlace) o;
        return postPlace.getPostalCode().equals(this.postalCode);
    }

    public String toString(){
        return postalCode + " " + town;
    }
}
