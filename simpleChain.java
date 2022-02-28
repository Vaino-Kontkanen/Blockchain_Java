
import java.util.ArrayList;
public class simpleChain 
{	
	public static ArrayList<simpleBlock> blockchain = new ArrayList<simpleBlock>();
	public static int difficulty = 5;
	public static void main(String[] args) 
	{	
		System.out.println("Trying to Mine block 1... ");
		addBlock(new simpleBlock("This is the Genesis block", "0"));
		
		System.out.println("Trying to Mine block 2... ");
		addBlock(new simpleBlock("This is the second block",blockchain.get(blockchain.size()-1).hash));
		
		System.out.println("Trying to Mine block 3... ");
		addBlock(new simpleBlock("This is the third block",blockchain.get(blockchain.size()-1).hash));	
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		String blockchainJson = stringUtils.getJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
	}
	public static Boolean isChainValid() 
	{
		simpleBlock currentBlock; 
		simpleBlock previousBlocks;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		for(int i=1; i < blockchain.size(); i++) 
		{
			currentBlock = blockchain.get(i);
			previousBlocks = blockchain.get(i-1);

			if(!currentBlock.hash.equals(currentBlock.calculateHash()))
			{
				System.out.println("Current Hashes not equal");			
				return false;
			}
			if(!previousBlocks.hash.equals(currentBlock.previousHash)) 
			{
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) 
			{
				System.out.println("This block hasn't been mined");
				return false;
			}
			
		}
		return true;
	}
	
	public static void addBlock(simpleBlock newBlock) 
	{
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}
}