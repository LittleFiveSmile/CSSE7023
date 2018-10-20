import org.junit.Before;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class TileTest {
    //tile1,tile2 for two types of constructor
    //tile2 for customizing certain blocks on the tile
    private Tile tile1,tile2,targetTile;
    private SoilBlock soilBlock;
    private GrassBlock grassBlock;
    private StoneBlock stoneBlock;
    private WoodBlock woodBlock;
    private List<Block> currentListBlocks;//for customizing block list

    @Before
    public void setUp(){
        tile1=new Tile();
        targetTile=new Tile();
        soilBlock= new SoilBlock();
        grassBlock=new GrassBlock();
        stoneBlock=new StoneBlock();
        woodBlock=new WoodBlock();
        currentListBlocks= new ArrayList<>();
    }

    @Test
    public void tileConstructorTest1(){
        assertEquals(0,tile1.getExits().size() );
    }

    @Test(expected = TooHighException.class)
    public void tileConstructorTest2() throws TooHighException {
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        tile2=new Tile(currentListBlocks);
    }

    @Test(expected = TooHighException.class)
    public void tileConstructorTest3() throws TooHighException {
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(soilBlock); // it is  instances of GroundBlock
        tile2=new Tile(currentListBlocks);
    }
    @Test
    public void tileConstructorTest4(){
        tile1=new Tile();
        currentListBlocks=tile1.getBlocks();
        assertEquals("soil",currentListBlocks.get(0).getBlockType());
        assertEquals("soil",currentListBlocks.get(1).getBlockType());
        assertEquals("grass",currentListBlocks.get(2).getBlockType());
    }
    @Test(expected = NoExitException.class)
    public void addExitTest1() throws NoExitException {
        tile1.addExit(null,targetTile);
    }

    @Test(expected = NoExitException.class)
    public void addExitTest2() throws NoExitException {
        tile1.addExit("name1",null);
    }

    @Test
    public void addExitTest3() throws NoExitException {
        tile1.addExit("name1",targetTile);
        assertSame(targetTile,tile1.getExits().get("name1"));
    }

    @Test(expected = InvalidBlockException.class)
    public void digTest1() throws InvalidBlockException,
            TooLowException, TooHighException {
        currentListBlocks.add(stoneBlock);// stoneBlock cannot be dig
        tile2=new Tile(currentListBlocks);
        tile2.dig();
    }

    @Test(expected = TooLowException.class)
    public void digTest2() throws TooHighException,
            InvalidBlockException, TooLowException {
        tile2=new Tile(currentListBlocks);
        tile2.dig();
    }

    @Test
    public void digTest3() throws InvalidBlockException,
            TooLowException, TooHighException {
        List<Block> expectedList=new ArrayList<>();
        expectedList.add(soilBlock);
        expectedList.add(soilBlock);

        currentListBlocks.add(soilBlock);
        currentListBlocks.add(soilBlock);
        currentListBlocks.add(grassBlock);
        tile2=new Tile(currentListBlocks);
        tile2.dig();
        assertEquals(expectedList,tile2.getBlocks());
    }

    @Test
    public void getBlocksTest() throws TooHighException {
        currentListBlocks.add(soilBlock);
        currentListBlocks.add(soilBlock);
        currentListBlocks.add(grassBlock);
        tile2=new Tile(currentListBlocks);
        assertEquals(currentListBlocks,tile2.getBlocks());
    }

    @Test
    public void getExits() throws NoExitException {
        Map<String,Tile> exitMap=new HashMap<>();
        exitMap.put("name1",targetTile);
        tile1.addExit("name1",targetTile);
        assertEquals(exitMap,tile1.getExits());
    }

    @Test(expected = TooLowException.class)
    public void getTopBlockTest1() throws TooHighException,
            TooLowException {
        tile2=new Tile(currentListBlocks);// create a tile without block
        tile2.getTopBlock();
    }

    @Test
    public void getTopBlockTest2() throws TooHighException,
            TooLowException {
        currentListBlocks.add(soilBlock);
        currentListBlocks.add(soilBlock);
        currentListBlocks.add(grassBlock);
        tile2=new Tile(currentListBlocks);
        assertEquals(grassBlock,tile2.getTopBlock());
    }

    @Test(expected = NoExitException.class)
    public void moveBlockTest1() throws NoExitException,
            InvalidBlockException, TooHighException {
        tile1.moveBlock("exit1");
    }

    @Test(expected = NoExitException.class)
    public void moveBlockTest2() throws NoExitException,
            InvalidBlockException, TooHighException {
        tile1.moveBlock(null);
    }

    @Test(expected = TooHighException.class)
    public void moveBlockTest3() throws TooHighException,
            NoExitException, InvalidBlockException {
        //Create a higher tile
        List<Block> exitListBlocks= new ArrayList<>();
        exitListBlocks.add(soilBlock);
        exitListBlocks.add(woodBlock);
        exitListBlocks.add(woodBlock);
        exitListBlocks.add(woodBlock);
        exitListBlocks.add(woodBlock);
        Tile higherTile=new Tile(exitListBlocks);

        //Create current tile
        currentListBlocks.add(soilBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        tile2=new Tile(currentListBlocks);

        //add exit for current tile
        tile2.addExit("higherTile",higherTile);
        tile2.moveBlock("higherTile");
    }

    @Test(expected = InvalidBlockException.class)
    public void moveBlockTest4() throws TooHighException,
            NoExitException, InvalidBlockException {
        //create a exit tile to move to
        List<Block> exitListBlocks= new ArrayList<>();
        exitListBlocks.add(soilBlock);
        exitListBlocks.add(woodBlock);
        Tile targetTile=new Tile(exitListBlocks);

        //create current tile with a stone block on the top
        currentListBlocks.add(soilBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(stoneBlock); //stone block is not moveable
        tile2=new Tile(currentListBlocks);
        tile2.addExit("targetTile",targetTile);
        tile2.moveBlock("targetTile");
    }

    @Test
    public void moveBlockTest5() throws TooHighException,
            NoExitException, InvalidBlockException {
        List<Block> exitListBlocks= new ArrayList<>();
        exitListBlocks.add(soilBlock);
        exitListBlocks.add(woodBlock);
        Tile targetTile=new Tile(exitListBlocks);

        currentListBlocks.add(soilBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        tile2=new Tile(currentListBlocks);

        //add exit for current tile
        tile2.addExit("targetTile",targetTile);
        tile2.moveBlock("targetTile");

        //test if targetTile has index 2 which is woodBlock
        assertEquals(woodBlock,targetTile.getBlocks().get(2));

        //test if the size of current tile is 2 rather than 3 now
        assertEquals(2,tile2.getBlocks().size());
    }

    @Test(expected = TooHighException.class)
    public void placeBlockTest1() throws TooHighException,
            InvalidBlockException {
        currentListBlocks.add(soilBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        tile2 = new Tile(currentListBlocks);
        tile2.placeBlock(woodBlock);
    }

    @Test(expected = TooHighException.class)
    public void placeBlockTest2() throws TooHighException,
            InvalidBlockException {
        currentListBlocks.add(soilBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        tile2=new Tile(currentListBlocks);
        tile2.placeBlock(soilBlock);
    }

    @Test(expected = InvalidBlockException.class)
    public void placeBlockTest3() throws TooHighException,
            InvalidBlockException {
        tile1.placeBlock(null);
    }

    @Test
    public void placeBlockTest4() throws TooHighException,
            InvalidBlockException {
        currentListBlocks.add(soilBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        currentListBlocks.add(woodBlock);
        tile2 = new Tile(currentListBlocks);
        tile2.placeBlock(woodBlock);
        assertEquals(5,tile2.getBlocks().size());
    }

    @Test(expected = NoExitException.class)
    public void removeExitTest1() throws NoExitException {
        tile1.removeExit(null);
    }

    @Test(expected = NoExitException.class)
    public void removeExitTest2() throws NoExitException {
        Map<String,Tile> exitMap=new HashMap<>();
        exitMap.put("name1",targetTile);

        tile1.addExit("name1",targetTile);
        tile1.removeExit("name2");
    }

    @Test
    public void removeExitTest3() throws NoExitException {
        //create an expected map
        Map<String,Tile> exitMap=new HashMap<>();
        exitMap.put("name1",targetTile);

        tile1.addExit("name1",targetTile);
        int MapSize=tile1.getExits().size();// store original map size
        tile1.removeExit("name1");
        int newMapSize=tile1.getExits().size();// store new map size

        assertEquals(MapSize-1,newMapSize);
    }

    @Test(expected = TooLowException.class)
    public void removeTopBlockTest1() throws TooHighException,TooLowException {
        tile2 = new Tile(currentListBlocks);
        tile2.removeTopBlock();
    }

    @Test
    public void removeTopBlockTest2() throws TooHighException,TooLowException{
        //create a tile with a wood block on the top
        currentListBlocks.add(soilBlock);
        currentListBlocks.add(woodBlock);
        tile2 = new Tile(currentListBlocks);
        tile2.removeTopBlock();

        assertEquals(soilBlock,tile2.getTopBlock());
    }
}