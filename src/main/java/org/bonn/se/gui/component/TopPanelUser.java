package org.bonn.se.gui.component;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import org.bonn.se.control.LoginControl;
import org.bonn.se.gui.ui.MyUI;
import org.bonn.se.model.objects.entitites.Kunde;
import org.bonn.se.model.objects.entitites.Vertriebler;

import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

public class TopPanelUser extends GridLayout {
    final MenuBar bar;
    MenuBar.MenuItem item1;

    public TopPanelUser() {

        this.setRows(1);
        this.setColumns(10);
        this.setStyleName("toppanel");


        this.setMargin(false);
        this.setWidthFull();
        this.setHeightUndefined();

        ThemeResource resource = new ThemeResource("img/RegisterStudent/logo.png");

        Button imagePropertyInfo = new Button(resource);
        imagePropertyInfo.setStyleName(ValoTheme.BUTTON_BORDERLESS);

        imagePropertyInfo.addClickListener((Button.ClickListener) clickEvent -> {
            if(UI.getCurrent().getSession().getAttribute(Roles.KUNDE) != null) {
                UI.getCurrent().getNavigator().navigateTo(Views.KUNDEHOMEVIEW);
            }else{
                UI.getCurrent().getNavigator().navigateTo(Views.VERTIEBLERHOMEVIEW);
            }
        });

        this.addComponent(imagePropertyInfo,0,0,0,0);
        this.setComponentAlignment(imagePropertyInfo, Alignment.MIDDLE_LEFT);

        bar = new MenuBar();

        bar.addStyleName("user-menu");






        if(UI.getCurrent().getSession().getAttribute(Roles.KUNDE) != null) {
            item1.addItem("Home", VaadinIcons.HOME, (MenuBar.Command) menuItem -> UI.getCurrent().getNavigator().navigateTo(Views.REGISTER));
            item1.addItem("Mein Profil", VaadinIcons.USER, (MenuBar.Command) menuItem -> MyUI.getCurrent().getNavigator().navigateTo(Views.REGISTER));
            item1.addItem("Einstellungen", VaadinIcons.COG, (MenuBar.Command) menuItem -> UI.getCurrent().getNavigator().navigateTo(Views.REGISTER));
            item1.addSeparator();
            item1.addItem("Logout", VaadinIcons.SIGN_OUT, (MenuBar.Command) menuItem -> LoginControl.logoutUser());
        }else {
            item1.addItem("Home", VaadinIcons.HOME, (MenuBar.Command) menuItem -> UI.getCurrent().getNavigator().navigateTo(Views.REGISTER));
            item1.addItem("Mein Profil", VaadinIcons.USER, (MenuBar.Command) menuItem -> MyUI.getCurrent().getNavigator().navigateTo(Views.REGISTER));
            item1.addItem("Einstellungen", VaadinIcons.COG, (MenuBar.Command) menuItem -> UI.getCurrent().getNavigator().navigateTo(Views.REGISTER));
            item1.addSeparator();
            item1.addItem("Logout", VaadinIcons.SIGN_OUT, (MenuBar.Command) menuItem -> LoginControl.logoutUser());
        }
        item1.getMenuBar().getItems().get(0).setStyleName("NameUserWhite");
        this.addComponent(bar,9,0,9,0);
        this.setComponentAlignment(bar, Alignment.MIDDLE_CENTER);

    }
}
