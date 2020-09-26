package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class JdbcDao {
     private DataSource dataSource;
     
     
     public DataSource getDataSource() {
           if (this.dataSource == null) {
                try {
                     InitialContext ctxt = new  InitialContext();
                     DataSource dataSource = ( DataSource )  ctxt.lookup("java:comp/env/jdbc/xe");
                     
                     return dataSource;
                } catch (NamingException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                }
           }
           return dataSource;
     }
     
     
     
     //insert Activitys Dao start
     
     public int insertActivitys(Activitys activitys) {
           try (
                     Connection conn =  getDataSource().getConnection();
                     Statement stmt = conn.createStatement();
                     PreparedStatement pstmt =  conn.prepareStatement("INSERT INTO activitys (id, activeAt,  name, topic, content, location, limitedNum, limitedType) VALUES  (?,?,?,?,?,?,?,?)");){
                  int deptno = 1;
                    String getAnimalIdSql = "SELECT  animal_id.nextval FROM DUAL";
                  
                  //自取號機取得新部門的部門代號
                    ResultSet rs =  stmt.executeQuery(getAnimalIdSql);
                 if (rs.next()) deptno = rs.getInt(1);
                 rs.close();
                 
                 
                 pstmt.setInt(1, activitys.getId());
                 java.sql.Date ActiveAt = new java.sql.Date(activitys.getActiveAt().getTime());
                 pstmt.setDate(2, ActiveAt);
                 pstmt.setString(2, activitys.getName());
                 pstmt.setString(3, activitys.getTopic());
                 pstmt.setString(4, activitys.getContent());
                 pstmt.setString(5, activitys.getLocation());
                 pstmt.setInt(6, activitys.getLimitedNum());
                 pstmt.setInt(7, activitys.getLimitedType());
                 pstmt.executeUpdate();
                  pstmt.clearParameters();
                 
                 stmt.close();
               return deptno;
           }catch (Exception e) {
                e.printStackTrace();
           }
           return 0;
     }
    
    //update Activitys 
     public boolean updateActivitys (Activitys activitys) {
           try(
                     Connection conn =  getDataSource().getConnection();
                     PreparedStatement pstmt =  conn.prepareStatement("update activitys set id=?, activtAt=?, name=?, topic=?, content=?, location=?, limitedNum=?, limitedType=? where id=?");) {
                  
                 
        	   pstmt.setInt(1, activitys.getId());
               java.sql.Date ActiveAt = new java.sql.Date(activitys.getActiveAt().getTime());
               pstmt.setDate(2, ActiveAt);
               pstmt.setString(2, activitys.getName());
               pstmt.setString(3, activitys.getTopic());
               pstmt.setString(4, activitys.getContent());
               pstmt.setString(5, activitys.getLocation());
               pstmt.setInt(6, activitys.getLimitedNum());
               pstmt.setInt(7, activitys.getLimitedType());
               pstmt.executeUpdate();
               pstmt.clearParameters();
                
               return true;
           }catch (Exception e) {
                e.printStackTrace();
           }
           return false;
     }
     
     
    //Activitys list  select all ActivitysDao
     public List<Activitys> listActivtys() {
           List<Activitys> list = new ArrayList<>();
           try (
                     Connection conn =  getDataSource().getConnection();
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("select *  from  activitys ");){
                  
                while (rs.next()) {
                	Activitys activitys = new Activitys ();
                	activitys.setId(rs.getInt("id"));
                	activitys.setActiveAt(rs.getDate("activeA"));
                	activitys.setName(rs.getString("name"));
                	activitys.setTopic(rs.getString("topic"));
                	activitys.setContent(rs.getString("content"));
                	activitys.setLocation(rs.getString("location"));
                	activitys.setLimitedNum(rs.getInt("limitedNum"));
                	activitys.setLimitedType(rs.getInt("limitedType"));
                     list.add(activitys);
                }
                return list;
                 
                 
           }catch (Exception e) {
                e.printStackTrace();
           }
           return list;
     }


// select activitysId 
	public boolean queryActivitys(Activitys activitys) {
		try(
				Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select * from activitys where id=?");) {
		  	  
		      pstmt.setInt(1, activitys.getId());
//		      java.sql.Date ActiveAt = new java.sql.Date(activitys.getActiveAt().getTime());
//		      pstmt.setDate(2, ActiveAt);
//              pstmt.setString(2, activitys.getName());
//              pstmt.setString(3, activitys.getTopic());
//              pstmt.setString(4, activitys.getContent());
//              pstmt.setString(5, activitys.getLocation());
//              pstmt.setInt(6, activitys.getLimitedNum());
//              pstmt.setInt(7, activitys.getLimitedType());

		      ResultSet rs = pstmt.executeQuery(); 

		      while(rs.next()){            
		          System.out.print(rs.getInt("id"));
		          System.out.print(rs.getDate("activeAt"));
		          System.out.print(rs.getString("topic"));
		          System.out.print(rs.getString("content"));
		          System.out.println(rs.getString("location"));
		          System.out.println(rs.getInt("limitedNum"));
		          System.out.println(rs.getInt("limitedTyp"));
		          
		      }
			  pstmt.clearParameters();
		      
		    return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}





//delete activitysDao


public boolean deleteActivitys(Activitys activitys) {
	try(
			Connection conn = getDataSource().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from activitys where id=?");) {
	  	  
		 pstmt.setInt(1, activitys.getId());
//	      java.sql.Date ActiveAt = new java.sql.Date(activitys.getActiveAt().getTime());
//	      pstmt.setDate(2, ActiveAt);
//         pstmt.setString(2, activitys.getName());
//         pstmt.setString(3, activitys.getTopic());
//         pstmt.setString(4, activitys.getContent());
//         pstmt.setString(5, activitys.getLocation());
//         pstmt.setInt(6, activitys.getLimitedNum());
//         pstmt.setInt(7, activitys.getLimitedType());


	      pstmt.executeUpdate();
		  pstmt.clearParameters();
	      
	    return true;
	}catch (Exception e) {
		e.printStackTrace();
	}
	return false;
 }	

}

// Activitys the end ....







//ActivtyReccords.java
/**
public DataSource getDataSource() {
     if (this.dataSource == null) {
           try {
                InitialContext ctxt = new InitialContext();
                DataSource dataSource = ( DataSource )  ctxt.lookup("java:comp/env/jdbc/xe");
                
                return dataSource;
           } catch (NamingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
           }
     }
     return dataSource;
}
public int insertAnimal(Animals animal) {
     try (
                Connection conn =  getDataSource().getConnection();
                Statement stmt = conn.createStatement();
                PreparedStatement pstmt =  conn.prepareStatement("INSERT INTO animals  (id,age,animal_type,breed,coat,sex,img_url,is_alive) VALUES  (?,?,?,?,?,?,?,?)");){
            int deptno = 1;
               String getAnimalIdSql = "SELECT animal_id.nextval  FROM DUAL";
            
            //自取號機取得新部門的部門代號
               ResultSet rs = stmt.executeQuery(getAnimalIdSql);
           if (rs.next()) deptno = rs.getInt(1);
           rs.close();
           
           
           pstmt.setInt(1, animal.getId());
           pstmt.setString(2, animal.getAge());
           pstmt.setString(3, animal.getAnimalType());
           pstmt.setString(4, animal.getBreed());
           pstmt.setString(5, animal.getCoat());
           pstmt.setString(6, animal.getSex());
           pstmt.setString(7, animal.getImgUrl());
           pstmt.setBoolean(8, animal.isAlive());
           pstmt.executeUpdate();
             pstmt.clearParameters();
           
           stmt.close();
         return deptno;
     }catch (Exception e) {
           e.printStackTrace();
     }
     return 0;
}
public boolean updateAnimal(Animals animal) {
     try(
                Connection conn =  getDataSource().getConnection();
                PreparedStatement pstmt =  conn.prepareStatement("update animals set  age=?,animal_type=?,breed=?,coat=?,sex,img_url=?,is_alive=?  where id=?");) {
            
           
           pstmt.setString(1, animal.getAge());
           pstmt.setString(2, animal.getAnimalType());
           pstmt.setString(3, animal.getBreed());
           pstmt.setString(4, animal.getCoat());
           pstmt.setString(5, animal.getSex());
           pstmt.setString(6, animal.getImgUrl());
           pstmt.setBoolean(7, animal.isAlive());
           pstmt.setInt(8, animal.getId());
           pstmt.executeUpdate();
             pstmt.clearParameters();
           
         return true;
     }catch (Exception e) {
           e.printStackTrace();
     }
     return false;
}
public List<Animals> listAnimals() {
     List<Animals> list = new ArrayList<>();
     try (
                Connection conn =  getDataSource().getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from  animals");){
            
           while (rs.next()) {
                Animals animal = new Animals();
                animal.setId(rs.getInt("id"));
                animal.setAge(rs.getString("age"));
                animal.setAnimalType(rs.getString("animal_type"));
                animal.setBreed(rs.getString("breed"));
                animal.setCoat(rs.getString("coat"));
                animal.setSex(rs.getString("sex"));
                animal.setImgUrl(rs.getString("img_url"));
                animal.setAlive(rs.getBoolean("is_alive"));
                list.add(animal);
           }
           return list;
       */    