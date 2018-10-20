public class StoneBlock
        extends java.lang.Object
        implements Block {

    /**
     *Get the type of a StoneBlock
     * Always returns "soil"
     * @return "stone".
     */
    @Override
    public String getBlockType() {
        return "stone";
    }

    /**
     *Get the colour of a StoneBlock
     * Always returns "black"
     * @return "gray".
     */
    @Override
    public String getColour() {
        return "gray";
    }

    /**
     *StoneBlock are carryable.
     * Always returns true
     * @return false.
     */
    @Override
    public boolean isCarryable() {
        return false;
    }

    /**
     *StoneBlocks are not diggable
     * Always returns false
     * @return false.
     */
    @Override
    public boolean isDiggable() {
        return false;
    }

    /**
     *StoneBlocks are not moveable
     * Always returns false
     * @return false.
     */
    @Override
    public boolean isMoveable() {
        return false;
    }
}
