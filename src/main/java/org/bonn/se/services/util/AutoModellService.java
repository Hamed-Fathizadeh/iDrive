
package org.bonn.se.services.util;


        import org.bonn.se.control.ComponentControl;
        import org.bonn.se.model.dao.AbstractDAO;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.stream.Stream;

public class AutoModellService extends AbstractDAO {

    private static org.bonn.se.services.util.AutoModellService dao = null;
    private static List< String> listeBeg = new ArrayList<>();

    public AutoModellService(String marke) {
        listeBeg = ComponentControl.getInstance().getAutoModell(marke);
    }

    public  List< String> getAutoModell(){
        return listeBeg;
    }


    public int count(String filter) {
        return (int) getAutoModell().stream()
                .filter(begrif -> filter == null || begrif
                        .toLowerCase().startsWith(filter.toLowerCase())
                        ||begrif.toLowerCase().contains(filter.toLowerCase())
                )
                .count();
    }

    public Stream<String> fetch(String filter, int offset, int limit) {
        return getAutoModell().stream()
                .filter(begrif -> filter == null || begrif
                        .toLowerCase().startsWith(filter.toLowerCase()) || begrif
                        .toLowerCase().contains(filter.toLowerCase())
                )
                .skip(offset).limit(limit);
    }



}