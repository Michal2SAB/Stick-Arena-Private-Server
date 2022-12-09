/*
 *     THIS FILE AND PROJECT IS SUPPLIED FOR EDUCATIONAL PURPOSES ONLY.
 *
 *     This program is free software; you can redistribute it
 *     and/or modify it under the terms of the GNU General
 *     Public License as published by the Free Software
 *     Foundation; either version 2 of the License, or (at your
 *     option) any later version.
 *
 *     This program is distributed in the hope that it will be
 *     useful, but WITHOUT ANY WARRANTY; without even the
 *     implied warranty of MERCHANTABILITY or FITNESS FOR A
 *     PARTICULAR PURPOSE. See the GNU General Public License
 *     for more details.
 *
 *     You should have received a copy of the GNU General
 *     Public License along with this program; if not, write to
 *     the Free Software Foundation, Inc., 59 Temple Place,
 */
package ballistickemu.Tools;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.concurrent.locks.ReentrantLock;
/**
 *
 * @author Simon
 */
public class DatabaseTools {
    private static Connection db_Connection = null;
    public static String user = "";
    public static String pass = "";
    public static String server = "";
    public static String database = "";
    public static final ReentrantLock lock = new ReentrantLock();

    public static void dbConnect()
    {

        try
        {
            // Load the JDBC driver
                String driverName = "org.gjt.mm.mysql.Driver"; // MySQL MM JDBC driver
                Class.forName(driverName);
                String url = "jdbc:mysql://" + server +  "/" + database; // a JDBC url
                
                db_Connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
               System.out.println(e.toString());
        } catch (SQLException e) {
                System.out.println(e.toString());
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }

    }

    public static int executeQuery(String Query)
    {
        
        String q = Query;
        try
        {
        if (db_Connection.isClosed() || db_Connection == null)
                dbConnect();

        } catch(SQLException e){}
        try
        {

            return db_Connection.prepareStatement(Query).executeUpdate();
        } catch(SQLException e)
        {
            System.out.println("There was an error executing query: " + Query + ". The exception returned was:" + e.toString());
        }
        lock.unlock();
        return -1;
    }

    
    public static int executeQuery(PreparedStatement ps) {
        lock.lock();
        try
        {
        if (db_Connection.isClosed() || db_Connection == null)
                dbConnect();

        } catch(SQLException e){}
        try
        {
            return ps.executeUpdate();
        } catch(SQLException e)
        {
            System.out.println("There was an error executing query: " + ps.toString() + ". The exception returned was:" + e.toString());
        } finally
        {
            lock.unlock();
        }
        return -1;
    }

    public static ResultSet executeSelectQuery(String Query)
    {
        lock.lock();
         try
        {
        if (db_Connection.isClosed() || db_Connection == null)
                dbConnect();

        } catch(SQLException e){}

         try
        {
            return db_Connection.createStatement().executeQuery(Query);
        } catch(SQLException e)
        {
            System.out.println("There was an error executing query: " + Query + ". The exception returned was:" + e.toString());
            return null;
        } finally
        {
            lock.unlock();
        }
    }

    public static Connection getDbConnection()
    {
        lock.lock();
        try
        {
           
           if(db_Connection.isClosed())
           {
               dbConnect();
           }
        } catch(SQLException e){}
        finally
        {
            lock.unlock();
        }
        return db_Connection;
    }

    public static int getRowCount(PreparedStatement ps)
    {
        lock.lock();
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
            rs.last();
            return rs.getRow();
        } catch (SQLException e)
        {
            System.out.println("Exception executing query: + " + e.toString());
        }
        finally {
            lock.unlock();
        }
        return -1;
    }

}
