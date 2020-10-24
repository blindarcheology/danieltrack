package org.track.core.common.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author blindarcheology@hotmail.com
 * @version 1.0
 * @className PreMainTraceAgent
 * @description static preMainTraceAgent that run before the main function runs
 * @date 2020/10/24 4:54 PM
 **/
public class PreMainTraceAgent {

    public static final Logger logger = LoggerFactory.getLogger(PreMainTraceAgent.class);

    public static void premain(String agentArgs, Instrumentation inst) {
        logger.info("agent args is:{}", agentArgs);
        inst.addTransformer(new DefaultDefineTransformer());
    }

    static class DefaultDefineTransformer implements ClassFileTransformer {

        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            logger.info("premain load class name is:{}", className);
            return classfileBuffer;
        }
    }

}
