package net.java.dev.vcc;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 17-Sep-2008
 * Time: 17:45:26
 * To change this template use File | Settings | File Templates.
 */
public interface Connection {

    Set<Host> getHosts();

    Set<Computer> getComputers();
    
}
