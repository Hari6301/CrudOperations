package com.tap;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CRUDoperations {
	private static Scanner sc = new Scanner(System.in);
	private static Connection connection;
	private static PreparedStatement statement;
	private static ResultSet res;
//	private static String SQL;
	
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/jdbcclass";
        String username = "root";
        String password = "Hari123";
        
		try {
			connection = DriverManager.getConnection(url, username, password);
			
			System.out.println("-------Welcome to Console Based Application--------");
			
			do {
				
			display(connection,statement,null);	
			
			System.out.println("\nEnter the choice..\n1.Insert \n2.Update\n3.Delete\n");
			
			int x = sc.nextInt();
			
			switch(x) {
				case 1:
					insertData(connection,statement,res);
					break;
				case 2:
					updateData(connection,statement,res);
					break;
				case 3:
					deleteData(connection,statement,res);
					break;
				
				default :
				System.out.println("Invalid Input.Make sure you give correct option...."
						+ "Do you want to continue(Yes/No)");
			
			}
			
			}while(sc.next().equalsIgnoreCase("Yes"));
	
//			if(sc.next().equalsIgnoreCase("NO")) {
				display(connection, statement, res);
				System.out.println("Thank you...Data Manipulated Successfully..");
//		}
		
	
		}catch (SQLException e) {
		e.printStackTrace();
	}
		
		finally {
			close(connection,statement,res);
		}
	}



	public static void display(Connection connection,PreparedStatement statement,ResultSet res) throws SQLException {
		
		String SQL ="select * from employee";
		
		statement  = connection.prepareStatement(SQL);
		
		
		res = statement.executeQuery();
		
		
		while(res.next()) {
		int id = res.getInt("id");
		String name = res.getString("name");
		String email = res.getString("email");
		String dept = res.getString("dept");
		int salary = res.getInt("salary");
		
//	     System.out.println("id,name,email,dept,salary");
		System.out.printf("%-3d %-15s %-30s %-10s %d\n", id,name,email,dept,salary) ;
		}
		}
	public static void insertData(Connection connection, PreparedStatement statement,ResultSet res) throws SQLException {
		
		String SQL = "INSERT into `employee` (`id`,`name`,`email`,`dept`,`salary`) values (?,?,?,?,?)";
	
		statement = connection.prepareStatement(SQL);
		do {	
		System.out.println("id");
		statement.setInt(1,sc.nextInt());
		System.out.println("name");
		statement.setString(2,sc.next());
		System.out.println("email");
		statement.setString(3,sc.next());
		System.out.println("dept");
		statement.setString(4,sc.next());
		System.out.println("salary");
		statement.setInt(5,sc.nextInt());
		int i = statement.executeUpdate();
		System.out.println(i);
		display(connection,statement,null);
		System.out.println("Do you want to insert more details?...(Yes/No)");
		}
		while(sc.next().equalsIgnoreCase("Yes"));
		System.out.println("Do you want to perform other operations(Yes/No)");
		
	}

	public static void updateData(Connection connection, PreparedStatement statement,ResultSet res) throws SQLException {
		
		do {
			
		System.out.println("1.To update name\n2.To update email\n3.To update dept\n4.To update salary\n");
		
		int y = sc.nextInt();
		
		switch(y){
			
		case 1:
			String SQL1 =" UPDATE employee SET name =?  where id =?"; 
			
			statement = connection.prepareStatement(SQL1); 
				
			System.out.println("Enter the name");
			
			statement.setString(1, sc.next()); 
			
			System.out.println("Enter the id where you want to update"); 
			
			statement.setInt(2, sc.nextInt()); 
		
			statement.executeUpdate();
			
			display(connection,statement,null);
			
			System.out.println("Name Updated Successfully.\n Do you want to update more details(Yes/No)");
			break;
			
		case 2:
			
			String SQL2 = "UPDATE employee SET email = ? where id =?" ; 
			
			statement = connection.prepareStatement(SQL2); 
				
			System.out.println("Enter the email");
				
			statement.setString(1, sc.next()); 
			
			System.out.println("Enter the id where you want to update"); 
				
			statement.setInt(2, sc.nextInt()); 
				
			statement.executeUpdate();
			
			display(connection,statement,null);
				
			System.out.println("Email Updated Successfully.\n Do you want to update more details(Yes/No)");

			break;
			
			case 3:
				
			String SQL3 =" UPDATE employee SET dept =?  where id = ?"; 
			
			statement = connection.prepareStatement(SQL3); 
			
				System.out.println("Enter the dept");
				
				statement.setString(1, sc.next()); 
				
				System.out.println("Enter the id where you want to update"); 
				
				statement.setInt(2, sc.nextInt()); 
				
				statement.executeUpdate();
				
				display(connection,statement,null);
				
				System.out.println("updated dept Successfully.\n Do you want to update more details(Yes/No)");
			
			break;
			
			case 4:
				
			String SQL4 = "UPDATE employee SET salary = ? where id = ?"; 
			
			statement = connection.prepareStatement(SQL4); 
			
				System.out.println("Enter the salary");
				
				statement.setString(1, sc.next()); 
				
				System.out.println("Enter the id where you want to update"); 
				
				statement.setInt(2, sc.nextInt()); 
				
				statement.executeUpdate();
				
				display(connection,statement,null);
				
				System.out.println("Salary Updated Successfully.\n Do you want to update more details(Yes/No)");
			
			break;
		
			default:
				
				System.out.println("Invalid choice!Make sure you will give the correct choice.Do you want to update(Yes/No)");
				
				break;
			}
		}
		while(sc.next().equalsIgnoreCase("Yes"));
		
			
		System.out.println("Do you want to perform other operations(Yes/No)");
		
	}
	
	public static void deleteData(Connection connection, PreparedStatement statement, ResultSet res) throws SQLException {
		
	
	  String SQL = "DELETE FROM employee where id = ?";
	  
	statement = connection.prepareStatement(SQL);
	
	System.out.println("Enter the ID you want to delete");
	
	statement.setInt(1, sc.nextInt()); 
	
	statement.executeUpdate();
	
	System.out.println("Data deleted Successfully..."); 
	
	System.out.println("Do you want to delete more details(Yes/No)");
	
	display(connection, statement, res);
			
	System.out.println("Do you want to perform other operations(Yes/No)");
		
	
	}
	private static void close(Connection connection, PreparedStatement statement, ResultSet res) {
		try {
			if(res!=null) {
			res.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(statement!=null) {
			statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(connection!=null) {
			connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

		
	

	
	
	
	
	
	
	
	
	
	
