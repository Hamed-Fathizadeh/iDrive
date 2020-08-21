package org.bonn.se.control;

import com.vaadin.data.provider.DataProvider;
import org.bonn.se.model.objects.dto.AutoEintragDTO;

import java.time.LocalDate;

public interface Suche {

    int getRowsCount();

    DataProvider<AutoEintragDTO,Void> einfacheSuche(String comboMarke, String comboModell, String comboBaujahr,
                                                    String suchArt, String comboKlimaanlage,String comboAnzahlSitze ,String comboAnzahlTuere, String comboZustand, String comboKraftstoffart, String combofarbe, int preis, int kilometer);

}