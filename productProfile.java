/************************************
Original Author: Pratima Kshetry
This code generates product profile for the amazon dataset available at: http://snap.stanford.edu/data/amazon-meta.html
The data format available is: 
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
	    
#####################################################################################################################
The product profile will be in the following format:
	   < 1047655	Prodigal Daughter	4	0061007129 0061007358 0061007137 0061099341 0061007161 > where
	   1047655 is the product ID,
	   Prodigal Daughter is the product Title,
	   4 is the product rating, and
	   "0061007129 0061007358 0061007137 0061099341 0061007161" are the list of similar products to the given product
	   

***************************************************************************************************************/

import java.util.Vector;

public class AmazonProductProfile {
	public String ID;
	public String Title;
	public int Rating;
	public Vector<String>similarProductIDList;
		
	public AmazonProductProfile(String ID)
	{
		this.ID=ID;
		similarProductIDList=new Vector <String>();
	}
	public String GetID()
	{
		return this.ID;
	}
	public Vector<String> GetSimilarProductList()
	{
		return this.similarProductIDList;
	}
	public String toString()
	{
		StringBuilder sb= new StringBuilder("*****ASIN ProductID:******");
		sb.append("ProductID(ASIN):"+ID);
		sb.append("\n***********************");
		for(String prodID:similarProductIDList)
		{
			sb.append("\nProductID:"+prodID);
			sb.append("\nSimilarProducts"+prodID);
		}
		sb.append("\n*******************");
		return sb.toString();
	}
	public void AddSimilarProductID(String product)
	{
		similarProductIDList.add(product);
	}
	
}
