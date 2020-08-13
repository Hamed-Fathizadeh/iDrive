package org.bonn.se.gui.ui;


import com.vaadin.annotations.*;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import org.bonn.se.gui.views.*;

import javax.servlet.annotation.WebServlet;

import static org.bonn.se.services.util.Views.*;


@Push
//@Theme("demo")
@Title("iDrive")
@PreserveOnRefresh
public class MyUI extends UI {


    @Override
    public void init(VaadinRequest vaadinRequest) {
        this.setSizeFull();




        Navigator navi = new Navigator(this , this );
        navi.addView(REGISTER, RegisterView.class);
        //navi.addView(LOGINVIEW, LoginView.class);
        navi.addView(VERTIEBLERHOMEVIEW, VertrieblerHomeView.class);
       // navi.addView(KUNDEHOMEVIEW, KundeHomeView.class);



        UI.getCurrent().getNavigator().navigateTo(REGISTER);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = true)
    public static class MyUIServlet extends VaadinServlet {
    }
}
