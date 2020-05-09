package com.example.ehtewa;

public class counselors {
    private String Specialisations;
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public counselors(){

    }

    public counselors (String Specialisations , String Name){
        this.Specialisations=Specialisations;
        this.Name=Name;
    }
    public String getSpecialisations(){
        return Specialisations;
    }
    public void setSpecialisations(String Specialisations){
        this.Specialisations=Specialisations;

    }



}
