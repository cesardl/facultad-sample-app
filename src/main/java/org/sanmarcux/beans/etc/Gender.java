package org.sanmarcux.beans.etc;

/**
 * @author Cesardl
 */
public enum Gender {

    MALE('\u2642'),
    FEMALE('\u2640');

    private final char value;

    private Gender(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

}
