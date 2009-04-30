package net.java.dev.vcc.impl.vmwarevix;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ConnectionURIParserTest
{
    private final VixDatacenterConnection instance = new VixDatacenterConnection();

    @Test
    public void smokes()
    {
        assertTrue( instance.acceptsUrl( "vcc:vmware-vix:myhost.mydomain.com" ) );
        assertTrue( instance.acceptsUrl( "vcc:vmware-vix:myhost.mydomain.com:902" ) );
        assertTrue( instance.acceptsUrl( "vcc:vmware-vix:myhost.mydomain.com;foo=bar" ) );
        assertTrue( instance.acceptsUrl( "vcc:vmware-vix:10.0.0.1:1905&foo=bar&bahh-wibble=moo;mark=0" ) );


    }

    @Test
    public void parser()
    {
        System.out.println( instance.parseUrl( "vcc:vmware-vix:myhost.mydomain.com" ) );
        System.out.println( instance.parseUrl( "vcc:vmware-vix:myhost.mydomain.com;foo=bar" ) );
        System.out.println( instance.parseUrl( "vcc:vmware-vix:myhost.mydomain.com;foo=bar;bar=foo 1992&mush=cow" ) );
        System.out.println(
            instance.parseUrl( "vcc:vmware-vix:myhost.mydomain.com;foo=bar;bar=foo%20%211992&mush=cow" ) );
    }
}
