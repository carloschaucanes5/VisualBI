/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package File.Upload;
/**
 *
 * @author Carlitos
 */
import java.util.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.*;
import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class UploadFile {
    public HttpServletRequest request;
    public String nameFile;
    public long sizeFile;
    public String typeFile;
    public String pathProject;
    public String pathFile;
    public FileItem item;
    public String exeptions;

    public UploadFile(HttpServletRequest re) {
        request=re;
        nameFile="";
        sizeFile=0;
        typeFile="";
        pathProject="";
        item=null;
    }
    
    public boolean  setFile(ServletContext app)
    {
       String name="",value="",pathFile1="",folder="";
        try
        {
            FileItemFactory controlador=new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(controlador);
            List items=upload.parseRequest(request);
            Iterator iter=items.iterator();
            ServletContext application=null; 
            while(iter.hasNext())
            {
                item=(FileItem) iter.next();
                if(item.isFormField())
                {
                }
                else
                {
                  nameFile=item.getName();
                  sizeFile=item.getSize();
                  typeFile=item.getContentType();
                  folder=app.getRealPath("");    
                  folder=folder.replace("\\build","");
                  folder.replace("\\", "/");    
                  pathProject=folder;           
                }
            }
          return true;
        }
        catch(Exception err)
        {
            this.exeptions=err.getMessage();
            return false;
        }
    }
    public boolean writeFile()
    {
        try
        {
            File file_server=new File(pathProject+"/uploads/"+ nameFile);
            item.write(file_server); 
            return true;
        }
        catch(Exception err)
        {
            this.exeptions=err.getMessage();
            return false;
        }
    }
    public boolean deleteFiles(String Directorio)
    {
       try
       {
            File directorio = new File(Directorio);
            File[] files = directorio.listFiles();
            for (int x=0;x<files.length;x++)
            {
                files[x].delete();                 
            }
            return true;
       }
       catch(Exception err)
       {
           this.exeptions=err.getMessage();
           return false;
       }
    }    
    public String getPath()
    {
        return pathProject.replace("\\","/");
    }  
}
