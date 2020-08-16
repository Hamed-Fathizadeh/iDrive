package org.bonn.se.model.objects.entitites;


public class Auto {
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

    public Auto(){
        setAuto_id(auto_id);
        setMarke(marke);
        setModell(modell);
        setKurz_beschreibung(kurz_beschreibung);
        setLang_beschreibung(lang_beschreibung);
        setBaujahr(baujahr);
        setAutomatik(automatik);
        setAnzahl_sitzplaetze(anzahl_sitzplaetze);
        setAnzahl_tueren(anzahl_tueren);
        setPreis_pro_tag(preis_pro_tag);
        setKlimaanlage(klimaanlage);
        setAuto_type(auto_type);
        setAutokennzeichen(autokennzeichen);
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
}
