package org.bonn.se.model.objects.entitites;
import org.bonn.se.services.util.IllegalException;
public class Endkunde  extends User{

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

    public  Endkunde(String email, int personanummer){
        setEmail(email);
        setPersonalnummer(personanummer);
    }
    public  Endkunde(){
        super();
        setPersonalnummer(personalnummer);
        setType("V");
    }
}
