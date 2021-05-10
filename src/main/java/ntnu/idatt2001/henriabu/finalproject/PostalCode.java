package ntnu.idatt2001.henriabu.finalproject;

public class PostalCode {
    private String code;
    private String name;

    public PostalCode(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        else if(!(o instanceof PostalCode)){
            return false;
        }
        PostalCode postalCode =(PostalCode) o;
        return postalCode.getCode().equals(this.code);
    }

    public String toString(){
        return code + " " + name;
    }
}
