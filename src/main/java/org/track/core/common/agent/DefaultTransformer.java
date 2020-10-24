package org.track.core.common.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author blindarcheology@hotmail.com
 * @version 1.0
 * @className Defaulttransformer
 * @description Defaulttransformer
 * @date 2020/10/24 5:06 下午
 **/
public class DefaultTransformer implements ClassFileTransformer {

    @lombok.SneakyThrows
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        ClassPool pool = new ClassPool();
        pool.insertClassPath(new LoaderClassPath(loader));
        CtClass ctClass = pool.get(className.replaceAll("/", "."));
        CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
        for (CtMethod ctMethod : declaredMethods) {
            ctMethod.addLocalVariable("begin", CtClass.longType);
            ctMethod.addLocalVariable("end", CtClass.longType);
            ctMethod.insertBefore("begin=System.currentTimeMillis();");
            ctMethod.insertAfter("end=System.currentTimeMillis();System.out.println((end-begin));");
        }

        return new byte[0];
    }
}
