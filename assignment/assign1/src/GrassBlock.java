public class GrassBlock
        extends GroundBlock{
    /**
     *Get the type of a GrassBlock
     * Always returns "grass"
     * @return "grass".
     */
    public String getBlockType(){
        return "grass";

    }
    /**
     *Get the colour of a GrassBlock
     * Always returns "green"
     * @return "green".
     */
    public String getColour(){
        return "green";

    }
    /**
     *GrassBlocks are not carryable
     * Always returns false
     * @return "false".
     */
    public boolean isCarryable(){
        return false;
    }

}
