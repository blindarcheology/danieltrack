package org.track.enumeration;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.track.DanielTrackApplicationTests;

import java.net.InetAddress;
import java.net.NetPermission;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.NoSuchElementException;

public class EnumerationTests extends DanielTrackApplicationTests {

    public static final Logger logger = LoggerFactory.getLogger(EnumerationTests.class);
    private InetAddress addrs[];

    @Test
    public void testEnumeration() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        NetworkInterface networkInterface = networkInterfaces.nextElement();
        Assert.assertTrue(true);
    }

    @Test
    public void testEnumerationCore() {
        String[] values = {"a","b","c","d"};
        Enumeration enumeration = new Enumeration() {
            int poi = 0;
            @Override
            public boolean hasMoreElements() {
                return poi < values.length;
            }
            @Override
            public Object nextElement() {
                return values[poi++];
            }
        };
        StringBuilder sb = new StringBuilder(values.length);
        while (enumeration.hasMoreElements()) {
            String o = (String)enumeration.nextElement();
            sb.append(o);
        }
        logger.info("values is:{}",sb.toString());
    }

    private Enumeration<InetAddress> getInetAddresses() {

        class checkedAddresses implements Enumeration<InetAddress> {

            private int i=0, count=0;
            private InetAddress local_addrs[];

            checkedAddresses() {
                local_addrs = new InetAddress[addrs.length];
                boolean trusted = true;

                SecurityManager sec = System.getSecurityManager();
                if (sec != null) {
                    try {
                        sec.checkPermission(new NetPermission("getNetworkInformation"));
                    } catch (SecurityException e) {
                        trusted = false;
                    }
                }
                for (int j=0; j<addrs.length; j++) {
                    try {
                        if (sec != null && !trusted) {
                            sec.checkConnect(addrs[j].getHostAddress(), -1);
                        }
                        local_addrs[count++] = addrs[j];
                    } catch (SecurityException e) { }
                }

            }

            public InetAddress nextElement() {
                if (i < count) {
                    return local_addrs[i++];
                } else {
                    throw new NoSuchElementException();
                }
            }

            public boolean hasMoreElements() {
                return (i < count);
            }
        }
        return new checkedAddresses();
    }
}
