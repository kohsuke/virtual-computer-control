package net.java.dev.vcc.impl.vmwarevix;

import net.java.dev.vcc.Connection;
import net.java.dev.vcc.ConnectionProvider;

import java.util.regex.Pattern;

/**
 * The ConnectionProvider for VMware virtual computers controlled through the VMware VIX API.
 */
public class VixConnectionProvider implements ConnectionProvider {
    public boolean acceptsUrl(String url) {
        return URL_PATTERN.matcher(url).matches();
    }

    public Connection connect(String url, String username, char[] password) {
        return null;
    }

    private final static Pattern URL_PATTERN = Pattern.compile("vcc\\:vmware-vix\\:([^\\:\\;\\&]+)(:\\d+)?([\\;\\&][a-zA-Z][a-zA-Z0-9\\-\\_]*=[^\\;\\&]+)*");
}
