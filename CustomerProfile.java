/********************************
Original Author: Pratima Kshetry
The data format as available in the dataset is: 
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
	    
	   
The customer profile generated using the dataset will be in the format as: 
*******************************************************************************************8*/

import java.util.*;
public class AmazonCustomerProfile {

	private String ID;
	private Vector<AmazonProductProfile> PurchasedProductList;
	private Vector<AmazonProductProfile> SimilarProductList;
	
	
	public AmazonCustomerProfile(String ID)
	{
		this.ID=ID;
		PurchasedProductList=new Vector<AmazonProductProfile>();
		SimilarProductList=new Vector<AmazonProductProfile>();
	}
	public String GetID()
	{
		return ID;
	}
	public  Vector<AmazonProductProfile> GetProductsPurchased()
	{
		return this.PurchasedProductList;
	}
		
	public Vector<AmazonProductProfile> GetSimilarProductList()
	{
		StringBuilder s=new StringBuilder("***Similar Products***");
		s.append(ID);
		for(AmazonProductProfile pdProfile:PurchasedProductList)
		{
			for(String p:pdProfile.similarProductIDList)
			{
			   s.append("\nSimilar Items:"+p);	
			}
		}
		s.append("\n**************************************************\n\n");		
		return this.SimilarProductList;
	}
	
	public String toString()
	{
		StringBuilder sb=new StringBuilder("******************Customer*************************");
		sb.append("\nCustomerID:"+ID);
		//sb.append("\n******************Products******************");
		sb.append("\n***************************************************");
		for(AmazonProductProfile prodProfile:PurchasedProductList)
		{
			sb.append("\nProductID:"+prodProfile.ID);
			sb.append("\nTitle:"+prodProfile.Title);
			sb.append("\nRatings:"+prodProfile.Rating);
			for(String s:prodProfile.similarProductIDList)
			{
			sb.append("\nSimilarProductID:"+s);
			}
		}
		sb.append("\n**************************************************\n\n");		
		return sb.toString();
	}
	public void AddProductProfile(AmazonProductProfile product)
	{
		PurchasedProductList.add(product);
		SimilarProductList.add(product);
	}
	
	
}
