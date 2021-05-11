package ntnu.idatt2001.henriabu.finalproject;

import javafx.geometry.Pos;
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

     public List<PostalCode> searchByPostalCode(String code) throws NoSuchPostalCodeException {
        int length = code.length();
        if (length >4){
            throw new IllegalArgumentException("Length cannot be more than 4 digits");
        }
        List<PostalCode> placesWithRequestedCode =
                 register.stream().filter(e-> e.getPostalCode().substring(0, length).equals(code)).
                         collect(Collectors.toList());
        /*if (placesWithRequestedCode.size() == 0){
            throw new NoSuchPostalCodeException("No such postal code");
        }*/
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

    public List<PostalCode> searchByPostOffice(String postOffice) throws NoSuchPostalOfficeException {
        int length = postOffice.length();
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
        /*if (placesWithRequestedPostOffice.size() == 0){
            throw new NoSuchPostalOfficeException("No such postal office");
        }*/
        return placesWithRequestedPostOffice;
    }
}
