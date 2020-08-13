package org.bonn.se.gui.views;


import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.gui.component.RegistrationPasswordField;
import org.bonn.se.gui.component.RegistrationTextField;
import org.bonn.se.model.objects.entitites.User;


public class Register extends VerticalLayout implements View {

    public void setUp(){

        this.setMargin(true);
        this.setSizeFull();

        Button loginButton = new Button("Login",VaadinIcons.BULLSEYE);

        //regStudent.addClickListener((Button.ClickListener) event -> UI.getCurrent().getNavigator().navigateTo(Views.REGISTERSTUDENT));

       // regUnternehmen.addClickListener((Button.ClickListener) event -> UI.getCurrent().getNavigator().navigateTo(REGISTERUNTERNEHMEN));

        Label lPatzhalter = new Label("&nbsp", ContentMode.HTML);
//schrifft
        String ls1 = "<div class=WordSection1>\n" +
                "\n" +
                "<p class=MsoNormal><b><span style='font-size:36.0pt;line-height:107%;\n" +
                "font-family:\"\"YACgEa4Wckw 0\", _fb_, auto\";color:#003853'>Willkommen bei <span\n" +
                "class=SpellE>iDrive &#128663</span><o:p></o:p></span></b></p>\n" +
                "\n" +
                "<p class=MsoNormal><b><span style='font-size:36.0pt;line-height:107%;\n" +
                "font-family:\"\"YACgEa4Wckw 0\", _fb_, auto\";color:#003853'>Registrieren Sie sich jetzt!<o:p></o:p></span></b></p>\n" +
                "\n" +
                "</div>";

        Label head = new Label(ls1, ContentMode.HTML);

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


        GridLayout mainGrid = new GridLayout(1, 5);
        mainGrid.setHeightFull();
        mainGrid.setWidthFull();
        mainGrid.setMargin(true);



//Vertikales Layout + Hinzufügen der Textfelder
        VerticalLayout layout = new VerticalLayout();



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

        User user = new User();

        binder.setBean(user);

//Platzhalter
        Label label = new Label ( "&nbsp;", ContentMode.HTML);
        layout.addComponent(label);



//Button zum Login + Symbol auf Button

        Button registerButton = new Button("Registrieren");
        registerButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        layout.addComponent(registerButton);
        layout.setComponentAlignment(registerButton, Alignment.MIDDLE_CENTER);

        //layout.addComponent(link4);
        //layout.setComponentAlignment(link4, Alignment.MIDDLE_CENTER);

        //Button für Passwort vergessen.

        //Erstellen und Hinzufügen eines Panels + Platzierung in die Mitte
        Panel panel = new Panel( "");
        panel.setWidth("40px");


        panel.setContent(layout);
        panel.setSizeUndefined();


        loginButton.addClickListener((Button.ClickListener) clickEvent -> {
            String sLogin = email.getValue();
            String sPassword = password.getValue();

        });

        mainGrid.addComponent(loginButton,0,1,0,1);
        mainGrid.addComponent(head,0,2,0,2);
        mainGrid.addComponent(panel,0,3,0,3);

        mainGrid.setComponentAlignment(loginButton,Alignment.TOP_RIGHT);
        mainGrid.setComponentAlignment(head,Alignment.TOP_LEFT);
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