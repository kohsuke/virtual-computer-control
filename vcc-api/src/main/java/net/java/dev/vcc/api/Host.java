package net.java.dev.vcc.api;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 18-Sep-2008
 * Time: 08:26:36
 * To change this template use File | Settings | File Templates.
 */
public interface Host  extends ManagedObject {

    Set<Computer> getComputers();

    String getName();
}
