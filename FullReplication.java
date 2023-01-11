import java.sql.*;
import java.util.*;

class FullReplication
{
	public static void main(String args[])
	{
	Scanner sc= new Scanner(System.in);	
	try{
	//oracle connection
	Class.forName("oracle.jdbc.OracleDriver");
	Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-8KR21VC:1521:sqlplus","scott","finalbtech8");
	
	//mysql connection
	Class.forName("com.mysql.cj.jdbc.Driver");
    	Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/naheendb?characterEncoding=utf8","root","*Nah2001");
	
	
	Statement stmt1=con1.createStatement();
	Statement stmt2=con2.createStatement();
	ResultSet ress = stmt1.executeQuery("select * from userregis");
	System.out.println("\nData in sqlplus :\n ");
	//System.out.println("\nnaheendb - userregis table:\n");
	System.out.println("--------------------------------------");
	System.out.println("id\tname\tage\tgender\temail\tpassword\theight\tweight");
	while(ress.next()) 
		{
			System.out.println(ress.getInt(1)+"\t"+ress.getString(2)+"\t"+ress.getInt(3)+"\t"+ress.getString(4)+"\t"+ress.getString(5)+"\t"+ress.getString(6)+"\t"+ress.getInt(7)+"\t"+ress.getInt(8));	
		}

	ResultSet res = stmt1.executeQuery("select * from userregis");

	int count = 0;
	while(res.next())
	{
		count++;
		int id = res.getInt(1);
		String name = res.getString(2);
		int age = res.getInt(3);
		String gender = res.getString(4);
		String email = res.getString(5);
		String password = res.getString(6);
		int height=res.getInt(7);
		int weight=res.getInt(8);
		
		String query = String.format("insert into userregis values(%d,'%s',%d,'%s','%s','%s',%d,%d)",id,name,age,gender,email,password,height,weight);
		
		stmt2.executeUpdate(query);
	}

	ResultSet res1 = stmt2.executeQuery("select * from userregis");
	System.out.println("\nData Copied from Oracle Sql to MySql : "+count);
	System.out.println("\nnaheendb - userregis table:\n");
	System.out.println("--------------------------------------");
	System.out.println("id\tname\tage\tgender\temail\tpassword\theight\tweight");
	while(res1.next()) 
		{
			System.out.println(res1.getInt(1)+"\t"+res1.getString(2)+"\t"+res1.getInt(3)+"\t"+res1.getString(4)+"\t"+res1.getString(5)+"\t"+res1.getString(6)+"\t"+res1.getInt(7)+"\t"+res1.getInt(8));	
		}
	/*
	ResultSet ress = stmt1.executeQuery("select * from userregis");
	System.out.println("\nData in sqlplus :\n ");
	//System.out.println("\nnaheendb - userregis table:\n");
	System.out.println("--------------------------------------");
	System.out.println("id\tname\tage\tgender\temail\tpassword\theight\tweight");
	while(ress.next()) 
		{
			System.out.println(ress.getInt(1)+"\t"+ress.getString(2)+"\t"+ress.getInt(3)+"\t"+ress.getString(4)+"\t"+ress.getString(5)+"\t"+ress.getString(6)+"\t"+ress.getInt(7)+"\t"+ress.getInt(8));	
		}*/
	}
	
	catch(Exception e)
	{
		System.out.println(e);
	}
	}
}