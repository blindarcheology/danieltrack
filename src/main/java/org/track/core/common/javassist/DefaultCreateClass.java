package org.track.core.common.javassist;

import javassist.*;

import java.io.IOException;
import java.util.List;

/**
 * @author blindarcheology@hotmail.com
 * @version 1.0
 * @className DefaultCreateClass
 * @description javassist test
 * @date 2020/10/16 9:24 PM
 **/
public abstract class DefaultCreateClass {

    /**
     * name: package name+class name.eg:java.lang.String
     *
     * @param name
     * @param cts
     */
    public static void create(String name, List<Field> cts, String path) throws CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass(name);

        if (null == cts || cts.size() == 0) {
            throw new JavassistException("makeClass is exception and the CtField is not allow null");
        }
        cts.forEach(obj -> {
            try {
                CtClass fieldType = pool.get(obj.getType());
                CtField ctField = new CtField(fieldType, obj.getName(), ctClass);
                ctField.setModifiers(obj.getModifierType());
                ctClass.addField(ctField);

                ctClass.addMethod(CtNewMethod.setter("set" +
                        obj.getName().substring(0, 1).toUpperCase() + obj.getName().substring(1), ctField));
                ctClass.addMethod(CtNewMethod.setter("get" +
                        obj.getName().substring(0, 1).toUpperCase() + obj.getName().substring(1), ctField));
            } catch (NotFoundException | CannotCompileException e) {
                throw new JavassistException("makeClass is exception");
            }

        });
        ctClass.writeFile(path);
    }

}
