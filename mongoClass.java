import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection; 
import com.mongodb.client.MongoDatabase; 
import org.bson.Document; 
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential; 
import com.mongodb.client.model.Filters; 
import com.mongodb.client.model.Updates;   
import java.util.*;
public class mongoClass 
{ 
	public static void main( String args[] ) { 
	Scanner sc = new Scanner(System.in);
	// Creating a Mongo client 
	MongoClient mongo = new MongoClient( "localhost" , 27017 ); 
	// Creating Credentials 	
	MongoCredential credential; 
	credential = MongoCredential.createCredential("sampleUser", "myDB","password".toCharArray()); 
	System.out.println("Connected to the database successfully"); 
	// Accessing the database 
	MongoDatabase database = mongo.getDatabase("myDB"); 
	/* Retrieving a collection*/
	MongoCollection<Document> collection = database.getCollection("product"); 
	//System.out.println("Collection product selected successfully");
	while(true){
		System.out.println("1.Insert One Document");
		System.out.println("2.Insert Many Document");
		System.out.println("3.Delete Document");
		System.out.println("4.Retrive Collection");
		System.out.println("5.Update Document");
		System.out.println("6.Exit");
		System.out.println("Enter Choice : ");
		int choice = sc.nextInt();
		switch(choice)
		{
			case 1 : Document document = new Document("title", "product1") 
						.append("prod_id", 101)
						.append("prod_name", "cloths") 
						.append("cost", "20000") 
						.append("brand", "ccc");
					 collection.insertOne(document);
					 System.out.println();
					 System.out.println("Document inserted successfully\n");
					 break;
					 
			case 2 : Document doc1 = new Document("title", "product2")
						.append("prod_id", 102)
						.append("prod_name", "TV") 
						.append("cost", "60000") 
						.append("brand", "ttt");
					 Document doc2 = new Document("title", "product3")
						.append("prod_id", 103)
						.append("prod_name", "Laptop") 
						.append("cost", "90000") 
						.append("brand", "lll");
					 Document doc3 = new Document("title", "product4")
						.append("prod_id", 104)
						.append("prod_name", "Cycles") 
						.append("cost", "15000") 
						.append("brand", "ccc");
					 List<Document> list = new ArrayList<Document>();
					 list.add(doc1);
					 list.add(doc2);
					 list.add(doc3);
					 collection.insertMany(list);
					 System.out.println();
					 System.out.println("Many Documents inserted successfully\n");
					 break;
			case 3 : collection.deleteOne(Filters.eq("title", "product1")); 
					 System.out.println("Document deleted successfully...\n");
					 break;
			case 4 : FindIterable<Document> iterDoc = collection.find(); 
					 int i = 1; 
					// Getting the iterator 
					 Iterator it = iterDoc.iterator(); 
					 while (it.hasNext()) 
					 {  
						System.out.println(it.next());  
						i++; 
					 }  
					 break;
			case 5 :  collection.updateOne(Filters.eq("title", "product1"), Updates.set("cost", 15000));       
					  System.out.println("Document update successfully...\n");
					  break;
			case 6 :System.exit(0);
		}
	}
	} 
}