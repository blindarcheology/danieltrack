package org.track.enumeration;

import org.junit.Assert;
import org.junit.Test;
import org.track.DanielTrackApplicationTests;
import org.track.core.common.IpUtils;

public class IPTests extends DanielTrackApplicationTests {

    @Test
    public void testIp() {
        String netWorkInterfaceName = "en0";
        String ip = IpUtils.getIp(netWorkInterfaceName);
        Assert.assertEquals("127.0.0.1",ip);
    }

}
