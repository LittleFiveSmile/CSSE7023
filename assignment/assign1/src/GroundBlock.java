public abstract class GroundBlock
        extends java.lang.Object
        implements Block{

    /**
     *Is the GroundBlock diggable? GroundBlocks enforce allowing digging
     * @return true
     */
    public final boolean isDiggable(){
        return true;
    }

    /**
     *Is the GroundBlock moveable? GroundBlocks enforce not moving
     * @return false
     */
    public final boolean isMoveable(){
        return false;
    }
}
//////////////// 不要忘了 comment