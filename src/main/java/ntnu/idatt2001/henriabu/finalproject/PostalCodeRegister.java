package ntnu.idatt2001.henriabu.finalproject;

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
        int length = code.length();
        List<PostalCode> placesWithRequestedCode =
                 register.stream().filter(e-> e.getPostalCode().substring(0, length).equals(code)).
                         collect(Collectors.toList());
        return placesWithRequestedCode;
    }

    public List<PostalCode> searchByPostOffice(String postOffice){
        int length = postOffice.length();
        List<PostalCode> placesWithRequestedPostOffice =
                register.stream().filter(e -> e.getPostalOffice().substring(0, length).
                        equalsIgnoreCase(postOffice)).collect(Collectors.toList());
        return placesWithRequestedPostOffice;
    }
}
