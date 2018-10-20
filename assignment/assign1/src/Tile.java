import java.util.*;

public class Tile
        implements java.io.Serializable {
    private List<Block> listBlocks; // store the list of blocks
    private Map<String,Tile> exitMap;// store exit map
    private SoilBlock soilBlock;
    private GrassBlock grassBlock;

    /**
     * Construct a new tile.
     * Each tile should be constructed with no exits (getExits().size() == 0).
     * Each tile must be constructed to start with two soil blocks and then
     * a grass block on top.
     */
    public Tile(){
        this.soilBlock= new SoilBlock();
        this.grassBlock=new GrassBlock();
        this.listBlocks= new ArrayList<>();
        this.exitMap= new HashMap<>(); // There is not exit now.
        this.listBlocks.add(soilBlock);
        this.listBlocks.add(soilBlock);
        this.listBlocks.add(grassBlock);

    }

    /**
     *Construct a new tile.
     * Each tile should be constructed with no exits (getExits().size() == 0).
     * Set the blocks on the tile to be the contents of startingBlocks.
     * Index 0 in startingBlocks is the lowest block on the tile, while index
     * N -1 is the top block on the tile for N blocks.
     * startingBlocks cannot be null.
     * @param startingBlocks a list of blocks on the tile, cannot be null
     * @exception TooHighException if startingBlocks.size() > 8,
     * or if startingBlocks elements with index≥3 are instances of GroundBlock
     */
    public Tile(List<Block> startingBlocks)throws TooHighException{
        this.listBlocks=new ArrayList<>(startingBlocks);
        this.exitMap= new HashMap<>(); // There is not exit now.

        if(this.listBlocks.size()>8){
            throw new TooHighException();
        }
        if(this.listBlocks.size()>3){
            for(int i=3;i<=this.listBlocks.size()-1;i++){
                if (this.listBlocks.get(i) instanceof GroundBlock){
                    throw new TooHighException();
                }
            }
        }
    }
    /**
     *Add a new exit to this tile.
     * @param name  Name of the exit.
     * @param target Tile the exit goes to.
     * @exception NoExitException if name or target is null.
     */
    public void addExit(String name, Tile target)
            throws NoExitException{
        if(name==null ||target==null){
            throw new NoExitException();
        }else {
            this.exitMap.put(name, target);
        }
    }
    /**
     *get the top block of this tile.
     * @return the removed block.
     * @exception TooLowException if there are no blocks on this tile.
     * @exception InvalidBlockException if the block is not diggable.
     */
    public Block dig()
            throws TooLowException,
            InvalidBlockException{
        Block removedBlock; // store removed block.
        if (this.getTopBlock().isDiggable()) {

            //store the length of block
            int blockLength =this.listBlocks.size();
            removedBlock = this.listBlocks.get(blockLength - 1);

            //remove the block on the top
            this.listBlocks.remove(blockLength - 1);
            return removedBlock;
        } else {
            throw new InvalidBlockException();
        }
    }

    /**
     *Get the list of blocks on this tile.
     * @return Blocks on the tile.
     */
    public List<Block> getBlocks(){
        return this.listBlocks;
    }

    /**
     *Show the map of exit name and tile.
     * @return map of names to Tiles.
     */
    public Map<String,Tile> getExits(){
        return this.exitMap;
    }

    /**
     *Get the top block of this tile.
     * @exception TooLowException if there are no blocks on this tile.
     */
    public Block getTopBlock()
            throws TooLowException{
        if (this.listBlocks.size()<1) {
            throw new TooLowException();
        } else{
            return listBlocks.get(this.listBlocks.size() - 1);
        }

    }
    /**
     *Move the top block to target tile.
     * @param exitName the name of the exit to move the block to.
     * @exception NoExitException if the exit is null or does not exist.
     * @exception TooHighException if the target exit is >= to this one.
     * @exception InvalidBlockException if the block is not moveable.
     */
    public void moveBlock(String exitName)
            throws TooHighException, InvalidBlockException,
            NoExitException{
        try{
            // store the top block of current tile
            Block topBlock = this.getTopBlock();
            if(exitMap.get(exitName)==null){
                throw new NoExitException();
            }

            //store the list of block of exit tile.
            List<Block> exitBlock=exitMap.get(exitName).getBlocks();
            if(topBlock.isMoveable()){
                if(exitBlock.size()>=this.getBlocks().size()){
                    throw new  TooHighException();
                }
                else{
                    this.removeTopBlock();
                    exitMap.get(exitName).placeBlock(topBlock);
                }
            }else{
                throw new InvalidBlockException();
            }
        }catch(TooLowException e){

        }
    }
    /**
     *Add the block to the top of the blocks on this tile.
     * @param block the block to place.
     * @exception TooHighException if there are already 8 blocks on the tile,
     * or if this is a ground block and there are already 3
     * or more blocks on the tile.
     * @exception InvalidBlockException if the block is null.
     */
    public void placeBlock(Block block)
            throws TooHighException,
            InvalidBlockException{
        if(block==null){
            throw new InvalidBlockException();
        }
        if(this.listBlocks.size()>=8){
            throw new TooHighException();
        }
        if(this.listBlocks.size()>=3 && block instanceof GroundBlock){
            throw new TooHighException();
        }
        this.listBlocks.add(block);
    }
    /**
     *Remove an exist exit from this tile.
     * @param name Name of exit to remove.
     * @exception NoExitException if name is not in exits,or name is null.
     */
    public void removeExit(String name)
            throws NoExitException{
        if(name==null || !this.exitMap.containsKey(name)){
            throw new NoExitException();
        }else{
            this.exitMap.remove(name);
        }
    }
    /**
     *Remove the top block of this tile.
     * @exception TooLowException if there are no block on the tile.
     */
    public void removeTopBlock()
            throws TooLowException{
        this.listBlocks.remove(this.getTopBlock());
    }
}
