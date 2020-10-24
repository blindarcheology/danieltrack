package org.track.core.common.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @author blindarcheology@hotmail.com
 * @version 1.0
 * @className AgentMain
 * @description AgentMain
 * @date 2020/10/24 5:16 PM
 **/
public class AgentMain {

    public static final Logger logger = LoggerFactory.getLogger(AgentMain.class);

    public static void agentmain(String args, Instrumentation inst) throws UnmodifiableClassException {
        Class[] classes = inst.getAllLoadedClasses();
        for (Class cls : classes) {
            logger.info("class name is:", cls.getName());
        }
        inst.addTransformer(new DefaultTransformer());
        inst.retransformClasses(MethodTimeConsumingMonitor.class);
    }

}
