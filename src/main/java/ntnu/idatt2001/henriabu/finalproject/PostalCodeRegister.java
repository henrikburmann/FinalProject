package ntnu.idatt2001.henriabu.finalproject;

import javafx.geometry.Pos;
import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidPostalCodeException;
import ntnu.idatt2001.henriabu.finalproject.exceptions.InvalidPostalOfficeException;
import ntnu.idatt2001.henriabu.finalproject.exceptions.NoSuchPostalCodeException;
import ntnu.idatt2001.henriabu.finalproject.exceptions.NoSuchPostalOfficeException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostalCodeRegister {
    private ArrayList<PostalCode> register;

    public PostalCodeRegister(){
        this.register = new ArrayList<>();
    }

    public boolean addPostalCode(PostalCode postalCode){
        for (PostalCode p: register){
            if (postalCode.equals(p)){
                return false;
            }
        }
        register.add(postalCode);
        return true;
    }
     public void removePostalCode(PostalCode p){
        register.remove(p);
     }

     public ArrayList<PostalCode> getRegister(){
        return register;
     }

     public List<PostalCode> searchByPostalCode(String code) throws InvalidPostalCodeException {
        int length = code.length();
         if (length>4 || (length>0 && !( code.matches("[0-9]+")))){
             throw new InvalidPostalCodeException("Postal code must me 4 digits long and integers only");
         }
        List<PostalCode> placesWithRequestedCode =
                 register.stream().filter(e-> e.getPostalCode().substring(0, length).equals(code)).
                         collect(Collectors.toList());
        return placesWithRequestedCode;
    }

    /*public List<PostalCode> searchByPostOffice(String postOffice){
        int length = postOffice.length();
        List<PostalCode> placesWithRequestedPostOffice =
                register.stream().filter(e ->{
                        if (e.getPostalOffice().length() < length){}
                        e.getPostalOffice().substring(0, length).
                        equalsIgnoreCase(postOffice)}
                        ).collect(Collectors.toList());

        return placesWithRequestedPostOffice;
    }*/

    public List<PostalCode> searchByPostOffice(String postOffice) throws InvalidPostalOfficeException {
        int length = postOffice.length();
        if (length>0 && !postOffice.matches("[a-zA-zæøåÆØÅ\\s-]+")){
            throw new InvalidPostalOfficeException("Postal code can only consist of letters");
        }
        List<PostalCode> placesWithRequestedPostOffice = new ArrayList<>();
        for (PostalCode p: register){
            if (p.getPostalOffice().length()<length){
                if (p.getPostalOffice().equalsIgnoreCase(postOffice)){
                    placesWithRequestedPostOffice.add(p);}
            }
            else if (p.getPostalOffice().substring(0, length).equalsIgnoreCase(postOffice)){
                placesWithRequestedPostOffice.add(p);
            }
        }
        return placesWithRequestedPostOffice;
    }
}
