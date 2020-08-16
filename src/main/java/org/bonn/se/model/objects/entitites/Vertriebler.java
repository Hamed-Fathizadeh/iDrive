package org.bonn.se.model.objects.entitites;

public class Vertriebler extends User{
    private int personalnummer;
    private String vorname;
    private String nachname;


    public Vertriebler(){
        setPersonalnummer(personalnummer);
        setVorname(vorname);
        setNachname(nachname);
        super.setType("V");
    }

    public Vertriebler(String email, int personalnummer, String vorname, String nachname, String passwort){
        super.setEmail(email);
        this.personalnummer = personalnummer;
        this.vorname = vorname;
        this.nachname = nachname;
        super.setPasswort(passwort);
        super.setType("V");

    }

    public int getPersonalnummer() {
        return personalnummer;
    }

    public void setPersonalnummer(int personalnummer) {
        this.personalnummer = personalnummer;
    }

    @Override
    public String getVorname() {
        return vorname;
    }

    @Override
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @Override
    public String getNachname() {
        return nachname;
    }

    @Override
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
}
