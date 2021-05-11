package ntnu.idatt2001.henriabu.finalproject;

import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidPostalCodeException;
import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidPostalOfficeException;

public class PostalCode {
    private String postalCode;
    private String postalOffice;
    private String communeCode;
    private String communeName;
    private String category;

    public PostalCode(String postalCode, String postalOffice, String communeCode, String communeName,
                      String category) throws InvalidPostalCodeException, InvalidPostalOfficeException {
        if (!(postalCode.matches("[0-9]+")) || !(postalCode.length() == 4)){
            throw new InvalidPostalCodeException("Postal code must me 4 digits long and integers only");
        }
        /*if (!postalOffice.chars().allMatch(Character::isLetter)){
            throw new InvalidPostalOfficeException("Postal code can only consist of letters");
        }*/
        this.postalCode = postalCode;
        this.postalOffice = postalOffice;
        this.communeCode = communeCode;
        this.communeName = communeName;
        this.category = category;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalOffice() {
        return postalOffice;
    }

    public void setPostalOffice(String postalOffice) {
        this.postalOffice = postalOffice;
    }

    public String getCommuneCode() {
        return communeCode;
    }

    public void setCommuneCode(String communeCode) {
        this.communeCode = communeCode;
    }

    public String getCommuneName() {
        return communeName;
    }

    public void setCommuneName(String communeName) {
        this.communeName = communeName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        else if(!(o instanceof PostalCode)){
            return false;
        }
        PostalCode postalCode =(PostalCode) o;
        return postalCode.getPostalCode().equals(this.postalCode);
    }

    public String toString(){
        return postalCode + " " + postalOffice;
    }
}
