/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package File.Upload;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Carlitos
 */
public class WriteFile {
    private String path;
    private String content;
    private String exceptions;


    public WriteFile(String path, String content) {
        this.path = path;
        this.content = content;
        this.exceptions="";
    }
    public boolean write()
    {
        try
        {
          FileWriter file = new FileWriter(path);
          file.write(content);
          file.flush();
          file.close();
          return true;
        }catch(IOException e)
        {
           this.exceptions=e.getMessage();
           return false;
        }
    }
    
      public String getExceptions() {
        return exceptions;
    }
}
