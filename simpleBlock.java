
import java.util.Date;

public class simpleBlock 
{	
	public String hash;
	public String previousHash; 
	private String data;
	private long timeStamp;
	private int nonce;	
	public simpleBlock(String data,String previousHash) 
	{
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	public String calculateHash() 
	{
		String calculatedhash = stringUtils.applySha256( previousHash 
														+ Long.toString(timeStamp) 
														+ Integer.toString(nonce) 
														+ data);
		return calculatedhash;
	}
	public void mineBlock(int difficulty) 
	{
		String target = stringUtils.getDificultyString(difficulty);
		while(!hash.substring( 0, difficulty).equals(target)) 
		{
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("This block has been mined : " + hash);
	}
}