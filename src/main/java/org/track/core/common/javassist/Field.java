package org.track.core.common.javassist;

/**
 * @author blindarcheology@hotmail.com
 * @version 1.0
 * @className Field
 * @description field
 * @date 2020/10/16 10:01 PM
 **/
public class Field {
    private String type;
    private String name;
    private int modifierType;

    public Field(String type, String name, int modifierType) {
        this.type = type;
        this.name = name;
        this.modifierType = modifierType;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getModifierType() {
        return modifierType;
    }
}
