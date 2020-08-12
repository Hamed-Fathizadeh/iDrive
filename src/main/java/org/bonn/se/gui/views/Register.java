package org.bonn.se.gui.views;


import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.gui.component.RegistrationPasswordField;
import org.bonn.se.gui.component.RegistrationTextField;




public class Register extends VerticalLayout implements View {

    public void setUp(){

        this.setMargin(true);
        this.setSizeFull();

        Button loginButton = new Button("Login");
        loginButton.setWidth("250px");
        loginButton.setHeight("45px");

        //regStudent.addClickListener((Button.ClickListener) event -> UI.getCurrent().getNavigator().navigateTo(Views.REGISTERSTUDENT));

       // regUnternehmen.addClickListener((Button.ClickListener) event -> UI.getCurrent().getNavigator().navigateTo(REGISTERUNTERNEHMEN));

        Label lPatzhalter = new Label("&nbsp", ContentMode.HTML);

//Textfeld Login
        RegistrationTextField userLogin = new RegistrationTextField("E-Mail");
        userLogin.setWidth("310px");
        userLogin.selectAll();


//Textfelt Passwort
        RegistrationPasswordField passwordField = new RegistrationPasswordField ("Passwort");
        passwordField.setWidth("310px");


        GridLayout mainGrid = new GridLayout(1, 5);
        mainGrid.setHeightFull();
        mainGrid.setWidthFull();
        mainGrid.setMargin(true);



//Vertikales Layout + Hinzufügen der Textfelder
        VerticalLayout layout = new VerticalLayout();




        layout.addComponent(userLogin);
        layout.addComponent(passwordField);

//Platzhalter
        Label label = new Label ( "&nbsp;", ContentMode.HTML);
        layout.addComponent(label);



//Button zum Login + Symbol auf Button

        Button buttonLogin = new Button("Login", VaadinIcons.SEARCH);
        buttonLogin.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        layout.addComponent(buttonLogin);
        layout.setComponentAlignment(buttonLogin, Alignment.MIDDLE_CENTER);

        //layout.addComponent(link4);
        //layout.setComponentAlignment(link4, Alignment.MIDDLE_CENTER);

        //Button für Passwort vergessen.

        //Erstellen und Hinzufügen eines Panels + Platzierung in die Mitte
        Panel panel = new Panel( "");
        panel.setWidth("40px");

        panel.setContent(layout);
        panel.setSizeUndefined();
        //panel.setStyleName("login_bg");


        buttonLogin.addClickListener((Button.ClickListener) clickEvent -> {
            String login = userLogin.getValue();
            String password = passwordField.getValue();

        });


        mainGrid.addComponent(panel,0,2,0,2);

        mainGrid.setComponentAlignment(panel,Alignment.BOTTOM_CENTER);

        this.addComponent(mainGrid);
        this.setComponentAlignment(mainGrid,Alignment.MIDDLE_CENTER);




    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
       /* if (UI.getCurrent().getSession().getAttribute(Roles.STUDENT) != null) {
            UI.getCurrent().getNavigator().navigateTo(Views.STUDENTHOMEVIEW);
        } else if(UI.getCurrent().getSession().getAttribute(Roles.UNTERNEHMEN) != null) {
            UI.getCurrent().getNavigator().navigateTo(Views.UNTERNEHMENHOMEVIEW);
        } else {
            this.setUp();
        }*/
        this.setUp();
    }
}