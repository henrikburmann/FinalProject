package ntnu.idatt2001.henriabu.finalproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

     public List<PostalCode> searchByPostalCode(String code){
        List<PostalCode> placesWithRequestedCode;
        placesWithRequestedCode = register.stream().filter(e-> e.getPostalCode() == code).collect(Collectors.toList());
        return placesWithRequestedCode;
    }
}
