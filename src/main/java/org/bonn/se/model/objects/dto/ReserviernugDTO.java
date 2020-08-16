package org.bonn.se.model.objects.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ReserviernugDTO {

    private int kundennummer;
    private int auto_id;
    private Timestamp abholdatum;
    private Timestamp rueckgabedatum;


    public ReserviernugDTO(){

    }

    public ReserviernugDTO(int kundennummer, int auto_id, Timestamp abholdatum, Timestamp rueckgabedatum) {
        this.kundennummer = kundennummer;
        this.auto_id = auto_id;
        this.abholdatum = abholdatum;
        this.rueckgabedatum = rueckgabedatum;
    }

    public int getKundennummer() {
        return kundennummer;
    }

    public void setKundennummer(int kundennummer) {
        this.kundennummer = kundennummer;
    }

    public int getAuto_id() {
        return auto_id;
    }

    public void setAuto_id(int auto_id) {
        this.auto_id = auto_id;
    }

    public Timestamp getAbholdatum() {
        return abholdatum;
    }

    public void setAbholdatum(Timestamp abholdatum) {
        this.abholdatum = abholdatum;
    }

    public Timestamp getRueckgabedatum() {
        return rueckgabedatum;
    }

    public void setRueckgabedatum(Timestamp rueckgabedatum) {
        this.rueckgabedatum = rueckgabedatum;
    }
}
