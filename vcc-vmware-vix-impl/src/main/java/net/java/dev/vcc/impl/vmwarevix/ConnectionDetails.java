package net.java.dev.vcc.impl.vmwarevix;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Holds the connection details
 */
public class ConnectionDetails {
    private final String host;
    private final int port;
    private final String libraryPath;
    private final Map<String, String> params;

    public ConnectionDetails(String host, int port, String libraryPath, Map<String, String> params) {
        this.host = host;
        this.port = port;
        this.libraryPath = libraryPath;
        this.params = Collections.unmodifiableMap(new HashMap<String, String>(params));
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getLibraryPath() {
        return libraryPath;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder("vcc:vmware-vix:");
        buf.append(host);
        buf.append(':');
        buf.append(port);
        buf.append("&libraryPath=");
        buf.append(libraryPath);
        for (Map.Entry<String, String> e : params.entrySet()) {
            buf.append('&');
            buf.append(e.getKey());
            buf.append('=');
            buf.append(e.getValue());
        }
        return buf.toString();
    }
}


