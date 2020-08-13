package org.bonn.se.control;

public class NoSuchUserOrPassword  extends  Exception{
    public NoSuchUserOrPassword() {
        super("Fehler Passwort oder Email ist falsch!");
    }
}
