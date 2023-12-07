/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dwh.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.olap4j.OlapConnection;
import org.olap4j.OlapException;

/**
 *
 * @author Carlitos
 */
public class ClassConnection {

    private String pathSchemaXml;
    private String host;
    private String dataBase;
    private String port;
    private String user;
    private String password;
    private String exceptions;
    private  OlapConnection olapConnection;
    
    public ClassConnection (String pathSchemaXml,  String host, String dataBase,String port,String user, String password) {
        this.pathSchemaXml = pathSchemaXml;
        this.host = host;
        this.dataBase = dataBase;
        this.port = port;
        this.user = user;
        this.password = password;
        this.exceptions = "";
        this.olapConnection = null;
    }

    public boolean processConnection()
    {
        try
        {
            String connStr = 
            "jdbc:mondrian:Catalog="+pathSchemaXml.trim()+";" +
            "JdbcDrivers=org.postgresql.Driver;JdbcPassword="+password.trim()+""+";JdbcUser="+user.trim()+";"+                         
            "Jdbc=jdbc:postgresql://"+host.trim()+":"+port.trim()+"/"+dataBase.trim()+""; 
            Class.forName("mondrian.olap4j.MondrianOlap4jDriver");
            Connection connection = DriverManager.getConnection(connStr);
            this.olapConnection = connection.unwrap(OlapConnection.class);
            return true;
        }
        catch(Exception err)
        {
            this.exceptions = err.getMessage();
            return false;
        }        
    }
    
    public OlapConnection getOlapConnection() {
        return olapConnection;
    }
    
    public String getExceptions() {
        return exceptions;
    }
    /*
    public static void main(String arg[])
    {
       String path = "D:/Proyecto de investigacion/INFORME FINAL/Anexos/anexo 4_cubos/schema_udenar_cantidad.xml";
        String host = "127.0.0.1";
        String port = "5432";
        String dataBase = "repositorio_dwh";
        String user = "postgres";
        String password ="postgres1";
        try
        {
            ClassConnection cOlap = new ClassConnection(path, host, dataBase, port, user, password);
            if(cOlap.processConnection() == true)
            {
                System.out.println(cOlap.olapConnection);
                System.out.println("Connection with success");        
            }
            else
            {
                System.out.println(cOlap.getExceptions());
            }
        }catch(Exception err)
        {
            System.out.println(err.toString());
        }
    }*/
}

