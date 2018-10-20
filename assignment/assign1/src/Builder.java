import java.util.ArrayList;
import java.util.List;

public class Builder {
    private String name;// store builder's name
    private Tile startingTile; // store the starting tile
    private List<Block> inventory; // store list of blocks on inventory

    /**
     *Create a builder.Set the name of the Builder
     * @param name the name of builder.
     * @param startingTile the tile the builder starts in.
     */
    public Builder(String name,Tile startingTile){
        this.name=name;
        this.startingTile=startingTile;
    }

    /**
     *Create a builder Set the name of the Builder
     * Copy the starting inventory into the builder's inventory, such that
     * the contents of getInventory() are identical to startingInventory.
     * Builder is constructed should not change the result of getInventory().
     * @param name the name of builder.
     * @param startingTile  the tile the builder starts in.
     * @param startingInventory the starting inventory (blocks).
     * @exception InvalidBlockException if for any Block (block)
     * in startingInventory, block.isCarryable() == false.
     */
    public Builder(String name,Tile startingTile,List<Block> startingInventory)
            throws InvalidBlockException{
        this.name=name;
        this.startingTile=startingTile;

        // check all blocks in the inventory are carryable
        for(int i=0;i<=startingInventory.size()-1;i++){
            if (!startingInventory.get(i).isCarryable()){
                throw new InvalidBlockException();
            }
        }
        this.inventory= new ArrayList<>(startingInventory);
    }

    /**
     *Get the name of this builder.
     * @return this builders name.
     */
    public String getName(){
        return this.name;
    }

    /**
     *Get the current tile that the builder is on.
     * @return the current tile.
     */
    public Tile getCurrentTile(){
        return this.startingTile;
    }

    /**
     *Get the list of blocks in the builder's inventory.
     * @return the list of blocks in the inventory.
     */
    public List<Block> getInventory(){
        return this.inventory;
    }

    /**
     *Drop the block from the inventory.
     * @param inventoryIndex the index in the inventory to place.
     * @exception InvalidBlockException if the inventoryIndex is
     * out of the inventory range.
     * @exception TooHighException if there are 8 blocks on the current
     * tile already, or if the block is an instance of GroundBlock and
     * there are already 3 or more blocks on the current tile.
     */
    public void dropFromInventory(int inventoryIndex)
        throws InvalidBlockException,
            TooHighException{
        if (inventoryIndex<0 || inventoryIndex>this.inventory.size()-1){
            throw new InvalidBlockException();
        }
        if (this.startingTile.getBlocks().size()>=8){
            throw new TooHighException();
        }
        if ((this.startingTile.getBlocks().size()>=3)&&
                !(inventory.get(inventoryIndex) instanceof GroundBlock)){
            throw new TooHighException();
        }

    }

    /**
     *Attempt to dig in the current tile.
     * @exception InvalidBlockException if the top block is not diggable.
     * @exception TooLowException if there are no blocks on the current tile.
     */
    public void digOnCurrentTile()
            throws TooLowException,
            InvalidBlockException{
        Block removedBlock=this.getCurrentTile().dig(); // store removed Block
        if(this.getCurrentTile().getTopBlock().isCarryable()){
            this.getInventory().add(removedBlock);
        }
    }
    /**
     *Check if the Builder can enter a tile from the current tile.
     * @param newTile the tile to test if we can enter.
     * @return true if the tile can be entered.
     */
    public boolean canEnter(Tile newTile){
        if (newTile==null){
            return false;
        }
        int currentBlockSize=this.getCurrentTile().getBlocks().size();
        int newTileBlockSize=newTile.getBlocks().size();
        return this.getCurrentTile().getExits().containsValue(newTile) &&
                Math.abs(newTileBlockSize - currentBlockSize) <= 1;
    }

    /**
     *Move the builder to a new tile.
     * @param newTile the tile to move to.
     * @exception NoExitException if canEnter(newTile)==false.
     */
    public void moveTo(Tile newTile)
        throws NoExitException{
        if (this.canEnter(newTile)){
            this.startingTile=newTile;
        }
        else{
            throw new NoExitException();
        }
    }


}

