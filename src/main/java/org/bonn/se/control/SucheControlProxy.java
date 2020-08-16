package org.bonn.se.control;

import com.vaadin.data.provider.DataProvider;
import org.bonn.se.model.objects.dto.AutoEintragDTO;

import java.time.LocalDate;

public class SucheControlProxy implements Suche {

    private  SucheControl implSucheControl;

    @Override
    public int getRowsCount() {
        if(implSucheControl == null) {
            implSucheControl = new SucheControl();
        }
        return SucheControl.rowsCount;
    }


    @Override
    public DataProvider<AutoEintragDTO, Void> einfacheSuche(String comboMarke, String comboModell, String comboBaujahr, String suchArt, String comboKlimaanlage, String comboAnzahlSitze, String comboAnzahlTuere) {
        if(implSucheControl == null) {
            implSucheControl = new SucheControl();
        }
        return implSucheControl.einfacheSuche(comboMarke, comboModell, comboBaujahr, suchArt, comboKlimaanlage, comboAnzahlSitze, comboAnzahlTuere);
    }
}

