package net.java.dev.vcc.impl.vmwarevix;

import net.java.dev.vcc.Connection;
import net.java.dev.vcc.ConnectionProvider;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 16-Sep-2008
 * Time: 20:01:41
 * To change this template use File | Settings | File Templates.
 */
public class VixManager implements ConnectionProvider {
    public boolean acceptsUrl(String url) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Connection connect(String url, String username, char[] password) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
