package org.bonn.se.model.objects.dto;

public class AutoEintragDTO {

    private int auto_id;
    private String marke;
    private String modell;
    private String kurz_beschreibung;
    private String lang_beschreibung;
    private int baujahr;
    private boolean automatik;
    private int anzahl_sitzplaetze;
    private int anzahl_tueren;
    private double preis_pro_tag;
    private boolean klimaanlage;
    private String auto_type;
    private String autokennzeichen;
    private int personalnummer;

    public AutoEintragDTO(){

    }

    public AutoEintragDTO(int auto_id, String marke, String modell, String kurz_beschreibung, String lang_beschreibung, int baujahr,
                          boolean automatik, int anzahl_sitzplaetze, int anzahl_tueren, double preis_pro_tag, boolean klimaanlage,
                          String auto_type, String autokennzeichen, int personalnummer) {
        this.auto_id = auto_id;
        this.marke = marke;
        this.modell = modell;
        this.kurz_beschreibung = kurz_beschreibung;
        this.lang_beschreibung = lang_beschreibung;
        this.baujahr = baujahr;
        this.automatik = automatik;
        this.anzahl_sitzplaetze = anzahl_sitzplaetze;
        this.anzahl_tueren = anzahl_tueren;
        this.preis_pro_tag = preis_pro_tag;
        this.klimaanlage = klimaanlage;
        this.auto_type = auto_type;
        this.autokennzeichen = autokennzeichen;
        this.personalnummer = personalnummer;
    }

    public int getAuto_id() {
        return auto_id;
    }

    public void setAuto_id(int auto_id) {
        this.auto_id = auto_id;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public String getKurz_beschreibung() {
        return kurz_beschreibung;
    }

    public void setKurz_beschreibung(String kurz_beschreibung) {
        this.kurz_beschreibung = kurz_beschreibung;
    }

    public String getLang_beschreibung() {
        return lang_beschreibung;
    }

    public void setLang_beschreibung(String lang_beschreibung) {
        this.lang_beschreibung = lang_beschreibung;
    }

    public int getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(int baujahr) {
        this.baujahr = baujahr;
    }

    public boolean isAutomatik() {
        return automatik;
    }

    public void setAutomatik(boolean automatik) {
        this.automatik = automatik;
    }

    public int getAnzahl_sitzplaetze() {
        return anzahl_sitzplaetze;
    }

    public void setAnzahl_sitzplaetze(int anzahl_sitzplaetze) {
        this.anzahl_sitzplaetze = anzahl_sitzplaetze;
    }

    public int getAnzahl_tueren() {
        return anzahl_tueren;
    }

    public void setAnzahl_tueren(int anzahl_tueren) {
        this.anzahl_tueren = anzahl_tueren;
    }

    public double getPreis_pro_tag() {
        return preis_pro_tag;
    }

    public void setPreis_pro_tag(double preis_pro_tag) {
        this.preis_pro_tag = preis_pro_tag;
    }

    public boolean isKlimaanlage() {
        return klimaanlage;
    }

    public void setKlimaanlage(boolean klimaanlage) {
        this.klimaanlage = klimaanlage;
    }

    public String getAuto_type() {
        return auto_type;
    }

    public void setAuto_type(String auto_type) {
        this.auto_type = auto_type;
    }

    public String getAutokennzeichen() {
        return autokennzeichen;
    }

    public void setAutokennzeichen(String autokennzeichen) {
        this.autokennzeichen = autokennzeichen;
    }

    public int getPersonalnummer() {
        return personalnummer;
    }

    public void setPersonalnummer(int personalnummer) {
        this.personalnummer = personalnummer;
    }
}
