import java.util.Objects;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class assignmentUserPassLOGGER {
	public static void main(String[] args) {
		final String driver = "oracle.jdbc.driver.OracleDriver";
		final String URL = "jdbc:oracle:thin:system/Piyushkhushi99@localhost:1521/sourabh";
		
		try {
			//load and register driver
			Class.forName(driver);
			System.out.println("Oracle Driver loaded and registered");
			
			//connect to Database
			Connection con = DriverManager.getConnection(URL);
			System.out.println("Connection to Database established");
			
			//create platform to run queries
			Statement stmt = con.createStatement();
			
			//Execute queries
			System.out.println("CREATING TABLE");
			String creTabQry = "create table tableuser(username varchar(20), password varchar(15) not null, person varchar(30), primary key(username))";
			boolean flag = stmt.execute(creTabQry);
			
			if(!flag)
				System.out.println("Table USER created");
			else
			{
				System.out.println("Cannot create Table !!!");
				System.exit(0);
			}
			do
			{
				Scanner sc = new Scanner(System.in);
				System.out.println("Choose an action to perform : " + "\n" + "1.LOGIN" + "\n" + "2.Create New User");
				System.out.println("Press any other key to exit...");
				int choice = sc.nextInt();
				
				switch(choice)
				{
					case 1:
					{
						System.out.println("Enter your username :");
						String user = sc.next();
						
						System.out.println("Enter your password : ");
						String pass = sc.next();
						
						String selQry = "select person, password from tableuser where username = user";
						boolean status = stmt.execute(selQry);
						
						System.out.println(status);
						
						ResultSet rs = stmt.getResultSet();
						String p = rs.getString(2);
						if(Objects.equals(p, pass))
						{
							String name = rs.getString(1);
							System.out.println(name + "Successfully LOGGED IN");
							System.out.println("EXITING........");
							System.exit(0);
						}
						else
						{
							System.out.println(p + "Not Found" + "\n" + "1.Do you wish to create new user : " + "\n" + "1.YES" + "\n" + "2.NO");
							int choice2 = sc.nextInt();
							
							if(choice2 == 1)
							{
								String username, password = "h", rpassword = "k", name;
								
								while(!Objects.equals(password, rpassword))
								{
									System.out.println("Enter username");
									username = sc.next();
								
									System.out.println("Enter password");
									password = sc.next();
								
									System.out.println("Re-enter password");
									rpassword = sc.next();
									
									System.out.println("Enter your name");
									name = sc.next();
									
									if(!Objects.equals(password, rpassword))
									{
										System.out.println("Press 1 to re-enter " + "\n" + "Press 2 to exit");
										int key = sc.nextInt();
										
										if(key == 1)
											continue;
										else
											break;
									}
									else
									{
										String insQry = "insert into tableuser values(username, password, name)";
										PreparedStatement pstmt = con.prepareStatement(insQry);
										pstmt.execute();
										int count = pstmt.getUpdateCount();
										System.out.println(count + "  Change applied");
									}
									
								}
							}
							else
							{
								break;
							}
							
						}
						break;
					}
					case 2:
					{
						String username, password = "h", rpassword = "k", name;
						
						while(!Objects.equals(password, rpassword))
						{
							System.out.println("Enter username");
							username = sc.next();
						
							System.out.println("Enter password");
							password = sc.next();
						
							System.out.println("Re-enter password");
							rpassword = sc.next();
							
							System.out.println("Enter your name");
							name = sc.next();
							
							if(!Objects.equals(password, rpassword))
							{
								System.out.println("Press 1 to re-enter " + "\n" + "Press 2 to exit");
								int key = sc.nextInt();
								
								if(key == 1)
									continue;
								else
									break;
							}
							else
							{
								String insQry = "insert into tableuser values(?, ?, ?)";
								PreparedStatement pstmt = con.prepareStatement(insQry);
								pstmt.setString(1, username);
								pstmt.setString(2, password);
								pstmt.setString(3, name);
								pstmt.execute();
								int count = pstmt.getUpdateCount();
								System.out.println(count + " Change applied");
							}
						}
						break;
					}
					default:
					{
						System.out.println("EXITING.........");
						System.exit(0);
					}	
				}				
			}while(true);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
