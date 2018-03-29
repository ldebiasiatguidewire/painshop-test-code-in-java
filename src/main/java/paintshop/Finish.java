package paintshop;

/**
 * Represent a finish 'G'losse or 'M'atte.
 */
public enum Finish {

    /**
     * Glosse.
     */
    G,

    /**
     * Matte.
     */
    M;

    /**
     * Return "G" for glosse or "M" for matte.
     *
     * @return "G" for glosse or "M" for matte.
     * Never {@code null}.
     */
    @Override
    public String toString() {
        return name();
    }
}
