package org.bonn.se.model.objects.entitites;

import org.bonn.se.services.util.IllegalException;

public class Vertriebler extends  User {
    private int personalnummer;

    public void setEmail(String email){
       super.setEmail(email);
    }

    public int getPersonalnummer() {
        return personalnummer;
    }

    public void setPersonalnummer(int personalnummer) {
        int length = String.valueOf(personalnummer).length();
        if(length<=0) {
            throw new IllegalException();
        }
        if(length>5){
            throw  new IllegalException();
        }
        else
            this.personalnummer = personalnummer;
    }

    public Vertriebler(String email, long personanummer){
        setEmail(email);
        setPersonalnummer(personalnummer);
    }
    public Vertriebler(){
        super();
        setPersonalnummer(personalnummer);
        setType("V");
    }
}
