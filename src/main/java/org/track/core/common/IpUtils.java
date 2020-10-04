package org.track.core.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author blindarcheology@hotmail.com
 * @Descriptor ip toolKit
 * @time 2020/10/2 13:54:50
 */
public class IpUtils {

    public static final Logger logger = LoggerFactory.getLogger(IpUtils.class);

    /**
     * Obtain the ip address according to the system network interface.
     * If the network interface <code>networkInterfaceName</code> is not empty, get the specified network interface ip,
     * Otherwise,obtain the ip address according to system all network interface.
     *
     * @param networkInterfaceName system network interface name
     * @return destination ip
     */
    public static String getIp(String networkInterfaceName) {
        String ip;
        try {
            List<String> hosts = getHost(networkInterfaceName);
            ip = hosts.isEmpty() ? "" : hosts.get(0);
        } catch (Exception e) {
            ip = "";
            logger.warn("get ip is warn", e);
        }
        return ip;
    }

    /**
     * Obtain the ip address according to the system network interface
     *
     * @param networkInterfaceName
     * @return destination host address
     * @throws SocketException the exception that is thrown when a socket error occurs
     */
    private static List<String> getHost(String networkInterfaceName) throws SocketException {
        List<String> ipList = new ArrayList<>(5);
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                if (inetAddress.isLoopbackAddress() || inetAddress instanceof Inet6Address) {
                    continue;
                }
                String hostAddress = inetAddress.getHostAddress();
                if (null == networkInterfaceName) {
                    ipList.add(hostAddress);
                } else if (networkInterfaceName.equals(networkInterface.getDisplayName())) {
                    ipList.add(hostAddress);
                }
            }
        }
        return ipList;
    }

}
