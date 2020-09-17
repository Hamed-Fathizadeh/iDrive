package org.bonn.se.gui.views;


import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.control.UserControl;
import org.bonn.se.gui.component.RegistrationPasswordField;
import org.bonn.se.gui.component.RegistrationTextField;
import org.bonn.se.model.dao.UserDAO;
import org.bonn.se.model.objects.entitites.Kunde;
import org.bonn.se.model.objects.entitites.User;
import org.bonn.se.model.objects.entitites.Vertriebler;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import java.io.File;


public class RegisterView extends Panel implements View {

    public void setUp(){

        this.setSizeFull();

        Button loginButton = new Button("Login",VaadinIcons.BULLSEYE);



        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/VAADIN/themes/demo/img/RegisterBild.png"));
        Image registerBild = new Image("", resource);

//Textfeld vorname
        RegistrationTextField vorname = new RegistrationTextField("Vorname");
        vorname.selectAll();
        vorname.setValue(VaadinService.getCurrent().getBaseDirectory().toString());
 //Textfield nachname
        RegistrationTextField nachname = new RegistrationTextField( "Nachname");
        nachname.setValue(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath());

//Textfeld Login
        RegistrationTextField email = new RegistrationTextField("E-Mail");
        email.selectAll();


//Textfelt Passwort
        RegistrationPasswordField password = new RegistrationPasswordField ("Passwort");


        GridLayout mainGrid = new GridLayout(2, 5);
        mainGrid.setHeightFull();
        mainGrid.setWidthFull();
        mainGrid.setMargin(true);

//combobox als was
        ComboBox<String> comboAlsWas = new ComboBox<>();
        comboAlsWas.setWidth(300.0f, Unit.PIXELS);
        comboAlsWas.setPlaceholder("Kunde oder Vertriebler");
        comboAlsWas.setItems( "Kunde", "Vertriebler");

//Vertikales Layout + Hinzufügen der Textfelder
        VerticalLayout layout = new VerticalLayout();

        String basepath2 = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource2 = new FileResource(new File(basepath2 +
                "/VAADIN/themes/demo/img/Logo_iDrive.png"));
        Image logo = new Image("", resource2);



        layout.addComponent(vorname);
        layout.addComponent(nachname);
        layout.addComponent(email);
        layout.addComponent(password);
//binder
        Binder<User> binder = new Binder<>(User.class);

        binder.forField(vorname)
                .asRequired("Vorname muss angegeben werden!")
                .bind(User::getVorname,User::setVorname);

        binder.forField(nachname)
                .asRequired("Nachname muss angegeben werden!")
                .bind(User::getNachname,User::setNachname);
        binder.forField(email)
                .asRequired("E-Mail muss angegeben werden")
                .withValidator(new EmailValidator("Keine gültige E-Mail Adresse!"))
                .bind(User::getEmail,User::setEmail);
        binder.forField(password)
                .asRequired("Bitte Passwort eingeben!")
                .withValidator(new StringLengthValidator(
                        "Passwort muss mindestens 8 Zeichen lang sein", 8, null))
                .bind(User::getPasswort,User::setPasswort);
        binder.forField(comboAlsWas)
                .asRequired("Bitte wählen Sie ein User!")
                .bind(User::getType,User::setType);

        User user = new User();

        binder.setBean(user);

//Platzhalter
        Label label = new Label ( "&nbsp;", ContentMode.HTML);
        layout.addComponent(label);



//Buttons

        Button registerButton = new Button("Registrieren");

        registerButton.addClickListener(
                event -> {

                    try {

                        if (UserControl.getInstance().existUser(email.getValue())) {
                            email.setValue("");
                            email.setPlaceholder("E-Mail existiert schon!");
                            email.setComponentError(new UserError("Bitte eine andere E-Mail verwenden."));

                        } else {

                            if(user.getType().equals("Vertriebler")) {
                                String emailDomain =user.getEmail().substring( user.getEmail().indexOf('@'),user.getEmail().length());
                                if(!emailDomain.equals("@idrive.de")){
                                    org.bonn.se.gui.window.ConfirmationWindow confWindow =  new org.bonn.se.gui.window.ConfirmationWindow("Als Vertriebler mussen Sie sich mit der Email domain: @idrive.de registrieren!");
                                    confWindow.setCaption("Email passt nicht!");
                                    confWindow.setWidth("600px");
                                    UI.getCurrent().addWindow(confWindow);
                                    confWindow.focus();
                                return;
                                }else {
                                    UserDAO.getInstance().registerUser(user);
                                    Vertriebler vertriebler = new Vertriebler();
                                    vertriebler.setEmail(user.getEmail());
                                    vertriebler.setVorname(user.getVorname());
                                    vertriebler.setNachname(user.getNachname());
                                    vertriebler.setPasswort(user.getPasswort());
                                    vertriebler.setType(user.getType());
                                    vertriebler.setPersonalnummer(UserControl.getInstance().getPersonalnummer(email.getValue()));
                                    UI.getCurrent().getSession().setAttribute(Roles.VERTRIEBLER, vertriebler);
                                    UI.getCurrent().getNavigator().navigateTo(Views.VERTIEBLERHOMEVIEW);
                                }

                            }else{
                                UserDAO.getInstance().registerUser(user);
                                Kunde kunde = new Kunde();
                                kunde.setEmail(user.getEmail());
                                kunde.setVorname(user.getVorname());
                                kunde.setNachname(user.getNachname());
                                kunde.setPasswort(user.getPasswort());
                                kunde.setType(user.getType());
                                kunde.setKundennummer(UserControl.getInstance().getKundennummer(user.getEmail()));
                                UI.getCurrent().getSession().setAttribute(Roles.KUNDE, kunde);
                                UI.getCurrent().getNavigator().navigateTo(Views.KUNDEHOMEVIEW);
                            }

                        }

                    } catch(DatabaseException e){
                        e.printStackTrace();
                    }

                });

        layout.addComponent(comboAlsWas);
        layout.setComponentAlignment(comboAlsWas, Alignment.MIDDLE_CENTER);
        layout.addComponent(registerButton);
        layout.setComponentAlignment(registerButton, Alignment.MIDDLE_CENTER);

        Panel panel = new Panel( "");
        panel.setWidth("40px");


        panel.setContent(layout);
        panel.setSizeUndefined();


        loginButton.addClickListener((Button.ClickListener) clickEvent -> {
            UI.getCurrent().getNavigator().navigateTo(Views.LOGINVIEW);

        });

        mainGrid.addComponent(loginButton,1,1,1,1);
        mainGrid.addComponent(logo,0,1,0,1);
        mainGrid.addComponent(panel,0,3,0,3);
        mainGrid.addComponent(registerBild,1,3,1,3);

        mainGrid.setComponentAlignment(loginButton,Alignment.TOP_RIGHT);
        mainGrid.setComponentAlignment(logo,Alignment.TOP_LEFT);
        mainGrid.setComponentAlignment(panel,Alignment.TOP_LEFT);
        mainGrid.setComponentAlignment(registerBild,Alignment.TOP_RIGHT);

        this.setContent(mainGrid);
        this.setSizeFull();




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