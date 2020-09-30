package org.track.machinecode;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.track.DanielTrackApplicationTests;
import oshi.SystemInfo;
import oshi.hardware.ComputerSystem;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class MachineCodeTests extends DanielTrackApplicationTests {

    public static final Logger LOGGER = LoggerFactory.getLogger(MachineCodeTests.class);

    @Test
    public void testMachineCode(){
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        ComputerSystem computerSystem = hardwareAbstractionLayer.getComputerSystem();

        String vendor = operatingSystem.getManufacturer();
        String processorSerialNumber = computerSystem.getSerialNumber();

        Assert.assertEquals("Apple",vendor);
        LOGGER.info("processor serial number is:{}",processorSerialNumber);
    }

    @Test
    public void testMachineInfo(){
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        GlobalMemory memory = hardwareAbstractionLayer.getMemory();
        LOGGER.info("os total memeory is:{}",memory.getTotal());
    }

}
