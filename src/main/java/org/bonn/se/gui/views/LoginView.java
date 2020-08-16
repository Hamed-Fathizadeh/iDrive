package org.bonn.se.gui.views;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.control.LoginControl;
import org.bonn.se.control.exception.NoSuchUserOrPassword;
import org.bonn.se.gui.component.RegistrationPasswordField;
import org.bonn.se.gui.component.RegistrationTextField;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginView  extends VerticalLayout implements View {
    public void setUp(){
        //Textfeld Login
        RegistrationTextField userLogin = new RegistrationTextField("E-Mail");
        userLogin.setWidth("310px");
        userLogin.selectAll();


        //Textfelt Passwort
        RegistrationPasswordField passwordField = new RegistrationPasswordField ("Passwort");
        passwordField.setWidth("310px");


        GridLayout mainGrid = new GridLayout(1, 5);
        mainGrid.setHeightFull();
        //mainGrid.setWidth("1000px");
        mainGrid.setWidthFull();
        mainGrid.setMargin(true);
        mainGrid.setStyleName("login_bg");


        //Vertikales Layout + Hinzufügen der Textfelder
        VerticalLayout layout = new VerticalLayout();
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/VAADIN/themes/demo/img/Logo_iDrive.png"));
        Image logo = new Image("", resource);
        layout.addComponent(logo);


        layout.addComponent(userLogin);
        layout.addComponent(passwordField);

        //Platzhalter
        Label label = new Label ( "&nbsp;", ContentMode.HTML);
        layout.addComponent(label);



//Button zum Login + Symbol auf Button

        Button buttonLogin = new Button("Login");
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


        Button registerButton = new Button("Registrieren");
        registerButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        registerButton.addClickListener((Button.ClickListener) clickEvent -> {
            UI.getCurrent().getNavigator().navigateTo(Views.REGISTER);
        });


        buttonLogin.addClickListener((Button.ClickListener) clickEvent -> {
            String login = userLogin.getValue();
            String password = passwordField.getValue();

            try {
                LoginControl.getInstance().checkAuthentication(login, password);
            } catch (DatabaseException ex) {
                Notification.show("DB-Fehler", ex.getReason(), Notification.Type.ERROR_MESSAGE);
                userLogin.clear();
                passwordField.clear();
            } catch (SQLException throwables) {
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
            } catch (org.bonn.se.control.NoSuchUserOrPassword noSuchUserOrPassword) {
                noSuchUserOrPassword.printStackTrace();
            }
        });


        mainGrid.addComponent(panel,0,2,0,2);
        mainGrid.addComponent(registerButton,0,3,0,3);



        mainGrid.setComponentAlignment(panel,Alignment.BOTTOM_CENTER);
        mainGrid.setComponentAlignment(registerButton,Alignment.BOTTOM_CENTER);

        this.addComponent(mainGrid);
        this.setComponentAlignment(mainGrid,Alignment.MIDDLE_CENTER);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (UI.getCurrent().getSession().getAttribute(Roles.KUNDE) != null) {
            UI.getCurrent().getNavigator().navigateTo(Views.KUNDEHOMEVIEW);
        } else if(UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER) != null) {
            UI.getCurrent().getNavigator().navigateTo(Views.VERTIEBLERHOMEVIEW);
        } else {
            this.setUp();
        }
    }
}