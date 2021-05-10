package ntnu.idatt2001.henriabu.finalproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PostalCodeRegister {
    private ObservableList<PostalCode> register;

    public PostalCodeRegister(){
        this.register = FXCollections.observableArrayList();
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

     public ObservableList<PostalCode> getRegister(){
        return register;
     }
}
