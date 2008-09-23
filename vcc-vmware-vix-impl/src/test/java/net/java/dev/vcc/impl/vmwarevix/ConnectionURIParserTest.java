package net.java.dev.vcc.impl.vmwarevix;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConnectionURIParserTest {
    private final VixConnectionProvider instance = new VixConnectionProvider();

    @Test
    public void smokes() {
        assertTrue(instance.acceptsUrl("vcc:vmware-vix:myhost.mydomain.com"));
        assertTrue(instance.acceptsUrl("vcc:vmware-vix:myhost.mydomain.com:902"));
        assertTrue(instance.acceptsUrl("vcc:vmware-vix:myhost.mydomain.com;foo=bar"));
        assertTrue(instance.acceptsUrl("vcc:vmware-vix:10.0.0.1:1905&foo=bar&bahh-wibble=moo;mark=0"));


    }
}
