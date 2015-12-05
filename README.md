# AmazonRecommendation

Original Author: Pratima Kshetry Student at: University of Maryland, College Park Technologies Used: Hadoop, MapReduce, Java

This is a simple java code that parses the amazon dataset, creates customer and product profiles from the dataset, which will later be used to generate recommendations for a given customer.

The dataset is available at: http://snap.stanford.edu/data/amazon-meta.html

The format of the dataset is : 
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
	    
	    
The product profile.java create product profile of each of the product available in the dataset.
The customer profile.java creates customer profile of each customer who have purchased a product/item as given by the Amazon dataset.
The recommenderApp.java contains the "Main" class.
The Recommender$DataParser contains the code that parses the given amazon dataset.
***************************The source code to this program is licensed under Apache License Version 2.0,

