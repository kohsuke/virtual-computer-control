package net.java.dev.vcc.util;

import net.java.dev.vcc.api.DatacenterManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class JDK5ServiceLoaderImpl<S>
        implements ServiceLoader<S> {

    JDK5ServiceLoaderImpl(Class<S> service, ClassLoader loader) {
        this.service = service;
        this.loader = loader;
    }

    /**
     * Method loadServices loads the services of a class that are defined using the SPI mechanism.
     *
     * @param clazz       The interface / abstract class defining the service.
     * @param classLoader of type ClassLoader the classloader to use.
     * @return An array of instances.
     */
    @SuppressWarnings("unchecked")
    private static <T> T[] loadServices(Class<T> clazz, ClassLoader classLoader) {
        final Logger LOGGER = Logger.getLogger(DatacenterManager.class.getName());

        LOGGER.log(Level.FINEST, "loadServices({0},{1})", new Object[]{clazz, classLoader});

        final String resourceName = "META-INF/services/" + clazz.getName();

        final Set<String> names = new HashSet<String>();

        try {
            final Enumeration<URL> urlEnumeration = classLoader.getResources(resourceName);

            if (urlEnumeration == null) {
                return (T[]) Array.newInstance(clazz, 0);
            }

            if (!urlEnumeration.hasMoreElements()) {
                return (T[]) Array.newInstance(clazz, 0);
            }

            nextUrl:
            while (urlEnumeration.hasMoreElements()) {
                final URL url = urlEnumeration.nextElement();
                try {
                    final InputStream inputStream = url.openStream();
                    try {
                        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        try {
                            final BufferedReader reader = new BufferedReader(inputStreamReader);
                            try {
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    int ci = line.indexOf('#');
                                    if (ci >= 0) {
                                        line = line.substring(0, ci);
                                    }
                                    line = line.trim();
                                    int n = line.length();
                                    if (n == 0) {
                                        continue; // next line
                                    }
                                    if ((line.indexOf(' ') >= 0) || (line.indexOf('\t') >= 0)) {
                                        LOGGER.log(Level.WARNING, "Illegal configuration-file syntax");
                                        continue nextUrl; // next url
                                    }
                                    int cp = line.codePointAt(0);
                                    if (!Character.isJavaIdentifierStart(cp)) {
                                        LOGGER.log(Level.SEVERE, "Illegal provider-class name: {0}", line);
                                        continue nextUrl; // next url
                                    }
                                    for (int i = Character.charCount(cp); i < n; i += Character.charCount(cp)) {
                                        cp = line.codePointAt(i);
                                        if (!Character.isJavaIdentifierPart(cp) && (cp != '.')) {
                                            LOGGER.log(Level.SEVERE, "Illegal provider-class name: {0}", line);
                                            continue nextUrl; // next url
                                        }
                                    }
                                    if (!names.contains(line)) {
                                        names.add(line);
                                    }
                                }
                            }
                            finally {
                                reader.close();
                            }
                        }
                        finally {
                            inputStreamReader.close();
                        }
                    }
                    finally {
                        inputStream.close();
                    }
                }
                catch (IOException e) {
                    LOGGER.log(Level.FINE, "I/O problem", e);
                }
            }
        }
        catch (IOException e) {
            LOGGER.log(Level.WARNING, "Could not determine services", e);
        }

        List<T> result = new ArrayList<T>();
        for (String name : names) {
            try {
                Class implClass = classLoader.loadClass(name);
                if (!clazz.isAssignableFrom(implClass)) {
                    LOGGER.log(Level.SEVERE, "{0} does not implement {1}", new Object[]{implClass, clazz});
                    continue;
                }
                result.add((T) implClass.newInstance());
            }
            catch (ClassNotFoundException e) {
                LOGGER.log(Level.SEVERE, "Could not find provider", e);
            }
            catch (IllegalAccessException e) {
                LOGGER.log(Level.WARNING, "Could not access provider", e);
            }
            catch (InstantiationException e) {
                LOGGER.log(Level.WARNING, "Could not instantiate provider", e);
            }
        }
        return result.toArray((T[]) Array.newInstance(clazz, result.size()));
    }

    private final Class<S> service;

    private final ClassLoader loader;

    private S[] providers = null;


    public synchronized void reload() {
        providers = null;
    }

    public synchronized Iterator<S> iterator() {
        if (providers == null) {
            providers = loadServices(service, loader);
        }
        return Arrays.asList(providers).iterator();
    }
}
