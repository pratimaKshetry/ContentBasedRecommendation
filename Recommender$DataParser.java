/*******************************************************************************************************************************
Original AUthor: Pratima Kshetry

This code will parse the amazon dataset available at: http://snap.stanford.edu/data/amazon-meta.html

The dataformat as available in the dataset is: 
Id:   1
	   ASIN: 0827229534
	   title: Patterns of Preaching: A Sermon Sampler
	   group: Book
	   salesrank: 396585
	   similar: 5  0804215715  156101074X  0687023955  0687074231  082721619X
	   categories: 2
	    |Books[283155]|Subjects[1000]|Religion & Spirituality[22]|Christianity[12290]|Clergy[12360]|Preaching[12368]
	    |Books[283155]|Subjects[1000]|Religion & Spirituality[22]|Christianity[12290]|Clergy[12360]|Sermons[12370]
	   reviews: total: 2  downloaded: 2  avg rating: 5
	    2000-7-28  cutomer: A2JW67OY8U6HHK  rating: 5  votes:  10  helpful:   9
	    2003-12-14  cutomer: A2VE83MZF98ITY  rating: 5  votes:   6  helpful:   5
***********************************************************************************************************************************/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class AmazonDataParser {
   
   private BufferedReader reader=null;
   private  String inputLine=null;
   private String  filePath=null;
 //  private Map<String, AmazonCustomerProfile> CustomerProfiles=null;
   private Map<String,CustomerProfile> CustomerProfiles=null;
   private String currentProductID=null;
   private String currentProductTitle=null;
   private String similarProductList=null;
   private double avgeRating=0;
   //private Map<String, AmazonProductProfile> ProductProfiles=null;
   private Map<String, ProductProfile> ProductProfiles=null;
   public AmazonDataParser(String filePath)
   {
	   this.filePath=filePath;	  
	  // this.CustomerProfiles= new  HashMap<String, AmazonCustomerProfile>();
	   this.CustomerProfiles= new HashMap<String, CustomerProfile>();
	  // this.ProductProfiles=new HashMap<String, AmazonProductProfile>();
	   this.ProductProfiles=new HashMap<String, ProductProfile>();
   }
   
   public void parse()
   {
	   try
	   {
		   if(reader!=null) reader.close();
		   reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)), 1024*100);
    	   inputLine=reader.readLine();
    	   while(inputLine!=null)
    	   {
    		   //if(inputLine.startsWith("Id:"))
    		   if(inputLine.startsWith("ASIN:"))
    		   {
    			   this.currentProductID=extractProductID(inputLine);
    			   inputLine=processInputLines(reader);    		   
    		   }
    		   else
    		   {
    			   inputLine=reader.readLine();
    		   }
    				   
    		   //System.out.println(inputLine);
    	   }
    	   
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
       finally{
	    	try
	    	{
	    		if(reader!=null) reader.close();
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
    	
       }
	   
   }
   
	private  String processInputLines(BufferedReader reader)throws IOException
	{
		String line=reader.readLine();
		//System.out.println("\n****[Start]*****");
		while(line!=null && !line.startsWith("Id:"))
		{
			//System.out.println(line);
			parseLine(line); //Important parses each line
			line=reader.readLine();
		}
		//System.out.println("\n****[END]****");
		return line;
	}
	
	private void parseLine(String input)
	{
		//Implement regular expression to parse and build customer profile
		input=input.trim();
		if(input.startsWith("title:")) 
		{	
			this.currentProductTitle=extractProductTitle(input);
			
		}
		if(input.startsWith("similar:"))
		{
			this.similarProductList=extractSimilarProduct(input);
			
		}
		if(input.contains("cutomer:") ) 
		{
			extractCustomerProfile(input);
		}
		if(input.contains("rating:"))
		{
			extractCustomerRating(input);
		}
		if(input.contains("avg rating:"))
		{
			String s=extractProductAvgeRating(input);
			avgeRating=Double.parseDouble(s);
			
		}
	}
	
	private String extractProductAvgeRating(String input)
	{
		String extractedAvgeRating=null;
		if(input==null)
			{
			return null;
			}
		input=input.trim();
		if(input.contains("avg rating:"))
		{
			int pos=input.indexOf("avg rating:");
			extractedAvgeRating=input.substring(pos+11);
			if(extractedAvgeRating!=null)
			{
				extractedAvgeRating=extractedAvgeRating.trim();
			}
		}
		return extractedAvgeRating;
	}
	
	private String extractCustomerRating(String input)
	{
		String extractedRating=null;
		if(input==null)
			{
			return null;
			}
		input=input.trim();
		if(input.startsWith("rating:"))
		{
			int pos=input.indexOf(':');
			extractedRating=input.substring(pos+1);
			if(extractedRating!=null)
			{
				extractedRating=extractedRating.trim();
			}
		}
		return extractedRating;
	}
	
	private String extractSimilarProduct(String input)
	{
		String extractedProductList=null;
		if(input==null) return null;
		input=input.trim();
		if(input.startsWith("similar:"))
		{
			int pos=input.indexOf(':');
			extractedProductList=input.substring(pos+1);
			if(extractedProductList!=null)
			{
				extractedProductList=extractedProductList.trim();
			}
		}
		return extractedProductList;
	}
	
	private String extractProductID(String input)
	{
		String extractedText=null;
		if(input!=null && input.startsWith("ASIN:"))
		{
			int pos=input.indexOf(':');
			extractedText=input.substring(pos+1);
			if(extractedText!=null)
			{
				extractedText=extractedText.trim();
			}
		}
		return extractedText;
	}
	private String extractProductTitle(String input)
	{
		String extractedText=null;
		if(input==null) return null;
		input=input.trim();
		if(input.startsWith("title:"))
		{
			int pos=input.indexOf(':');
			extractedText=input.substring(pos+1);
			if(extractedText!=null)
			{
				extractedText=extractedText.trim();
			}
		}
		return extractedText;
	}
	
	
	private CustomerProfile extractCustomerProfile(String input)
	{
		//String extractedText=null;
		CustomerProfile custProfile=null;
		if(input!=null)
		{
			input=input.trim();
			if(input.contains("cutomer:"))
			{
				/*int pos=input.indexOf("cutomer:");
				extractedText=input.substring(pos+1);
				if(extractedText!=null)
				{
					extractedText.trim();
				}*/
				
				//test.s
				String[]splitString=input.split(".*cutomer:|\\s+rating:|\\s+votes:|\\shelpful:");
			    //Must contain 5 characters
				if(splitString.length==5)
				{					
					String customerID=splitString[1].trim();
					if(CustomerProfiles.containsKey(customerID))
					{
						custProfile=CustomerProfiles.get(customerID);
					}
					else
					   {
						custProfile=new CustomerProfile(customerID);
						CustomerProfiles.put(customerID, custProfile);
						
					   }
					ProductProfile product=new ProductProfile(currentProductID);
					product.ID=currentProductID;
					
					product.avgeRating=avgeRating;
					
					//product.Title=currentProductTitle;
					String[] productIDlist=this.similarProductList.split("\\s+");
				
					/*for(int i=0;i<productIDlist.length;i++)
					{
						product.AddSimilarProductID(productIDlist[i]);						
					}*/
					/*
					boolean bSkip=true;
					
					for(String s:productIDlist)
					{ 
						if(!bSkip)
						{
							product.AddSimilarProduct(s);
						}
						bSkip=false;
					}
					*/
					for(int i=1;i<productIDlist.length;i++)
					{
						product.AddSimilarProduct(productIDlist[i]);
					}
					//product.similarProductList=similarProductList;
									
					try
					{
						product.Rating=Integer.parseInt(splitString[2].trim());
					}
					catch(Exception e)
					{
						product.Rating=-1;
					}
					custProfile.AddProductProfile(product);	
					ProductProfiles.put(product.ID,product);
					
				}
			}
		}
		return custProfile;
}
			
	
	public void printCustomersProfile()
	{
		for(String key:CustomerProfiles.keySet())
		{
			CustomerProfile  profile=CustomerProfiles.get(key);
			System.out.println(profile.toString());
		}
	}
	
	public void printCustomerSimilarProductList(String input)
	{
		if(CustomerProfiles.containsKey(input))
		{
			CustomerProfile p=CustomerProfiles.get(input);
			//System.out.println(p.toString());	
			
			/*for(AmazonProductProfile product:p.GetProductsPurchased())
			{
			    
			}
				*/	
			
			/*for(AmazonProductProfile product:p.GetSimilarProductList())
			{
				System.out.println(product.similarProductIDList);
			}
			*/
			for(ProductProfile product:p.GetSimilarProductList())
			{
				for(String s:product.GetSimilarProductList())
				{
					System.out.println("\nSimilar Items\n"+s+"\n");
					
				}
			}
		}
	}
	

	//Prints ProductProfile
	public void printProductsProfile()
	{
		for(String key:ProductProfiles.keySet())
		{
			ProductProfile  profile=ProductProfiles.get(key);
			System.out.println(profile.toString());
		}
	}
/****************************************************************************************************************************
Prints Recommendation
**********************************************************************************************************************************/
	public void printRecommendation(String customerID)
	{
		
		CustomerProfile customer=CustomerProfiles.get(customerID);
		Vector<ProductProfile> purchasedProductList=customer.GetProductsPurchased();
		for(ProductProfile item:purchasedProductList)
		{
			String itemID = item.ID;
		    ///Print printSimilarItemList here
			String recommendedItem=getSimilarItemString(itemID);
			System.out.println(recommendedItem);
		}
			
	}
	
	public String getSimilarItemString(String itemID)
	{
		String p="";
		ProductProfile item=ProductProfiles.get(itemID);
		//Vector<String>similarProductIDList=item.GetSimilarProductList();
		for(String id:item.similarProductIDList)
		{
			//p=itemList.toString();
			p+="\nItemID:"+id;
			ProductProfile item1=ProductProfiles.get(id);
			if(item1!=null)
			{
				p+=item1.ID+"\nAvgeRating:"+item1.avgeRating;
			}
			
		}		
		return p;		
	}
}
	
	


