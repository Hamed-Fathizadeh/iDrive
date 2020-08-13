package org.bonn.se.control;

public class LoginControl {
    private LoginControl(){

    }

    private static LoginControl instance;

    public static LoginControl getInstance() {
        if (instance == null){
            instance = new LoginControl();
        }
        return instance;
    }


}
