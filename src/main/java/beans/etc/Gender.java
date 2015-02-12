package beans.etc;

/**
 *
 * @author Cesardl
 */
public enum Gender {

    MALE('M'),
    FEMALE('F');

    private final char value;

    private Gender(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

}
