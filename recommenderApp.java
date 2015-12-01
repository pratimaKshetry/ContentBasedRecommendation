/***********************************************************************************************************************************
Original Author: Pratima Kshetry

This file contains the Main class for the content-based recommendation.
***********************************************************************************************************************************/

/* Sample data example****
 * 
 * Id:   1
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
*/

import java.util.Scanner;

public class AmazonDataParserApp {

	public static void main(String[] args) {
		
		String filePath="E:\\Recodata\\amazon1.txt";
		AmazonDataParser amDP=new AmazonDataParser(filePath);
		Scanner user_input=new Scanner(System.in);
		String customerID;
		System.out.println("Enter the CustomerID");
		customerID=user_input.next();
		
		amDP.parse();
		
		/*String test="2002-9-24  cutomer: A1I7GHG2XNYO3J  rating: 5  votes:   2  helpful:   1";
		//test.s
		String[]splitString=test.split(".*cutomer:|\\s+rating:|\\s+votes:|\\shelpful:");
	    //Must contain 5 characters
		if(splitString.length==5)
		{
			
		}*/
		//amDP.printCustomersProfile();
		//amDP.printCustomerSimilarProductList(customerID);
		//amDP.printProductsProfile();
		amDP.printRecommendation(customerID);
		
		
	
	}
}
