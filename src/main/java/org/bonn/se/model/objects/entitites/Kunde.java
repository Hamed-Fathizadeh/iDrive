package org.bonn.se.model.objects.entitites;

public class Kunde extends User{
    private int kundennummer;
    private String vorname;
    private String nachname;

    public Kunde(){
        setKundennummer(kundennummer);
        setVorname(vorname);
        setNachname(nachname);
        super.setType("K");
    }

    public Kunde(String email, int kundennummer, String vorname, String nachname, String passwort){
        super.setEmail(email);
        this.kundennummer = kundennummer;
        this.vorname = vorname;
        this.nachname = nachname;
        super.setPasswort(passwort);
        super.setType("K");

    }

    public int getKundennummer() {
        return kundennummer;
    }

    public void setKundennummer(int kundennummer) {
        this.kundennummer = kundennummer;
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
