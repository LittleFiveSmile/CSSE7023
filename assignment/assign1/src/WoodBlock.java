public class WoodBlock
        extends java.lang.Object
        implements Block {

    /**
     *Get the type of a WoodBlock
     * Always returns "wood"
     * @return "wood".
     */
    @Override
    public String getBlockType() {
        return "wood";
    }
    /**
     *Get the colour of a WoodBlock
     * Always returns "brown"
     * @return "brown".
     */
    @Override
    public String getColour() {
        return "brown";
    }
    /**
     *A woodblock is carryable
     * Always returns true
     * @return true.
     */
    @Override
    public boolean isCarryable() {
        return true;
    }
    /**
     *A woodblock is diggable
     * Always returns true
     * @return true.
     */
    @Override
    public boolean isDiggable() {
        return true;
    }
    /**
     *A woodblock is moveable
     * Always returns true
     * @return true.
     */
    @Override
    public boolean isMoveable() {
        return true;
    }
}
