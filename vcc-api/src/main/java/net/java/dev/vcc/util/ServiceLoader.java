package net.java.dev.vcc.util;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 17-Sep-2008
 * Time: 17:12:48
 * To change this template use File | Settings | File Templates.
 */
interface ServiceLoader<S> {
    void reload();

    Iterator<S> iterator();
}
