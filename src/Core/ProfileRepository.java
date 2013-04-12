/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Ashoraya
 */
public class ProfileRepository implements Serializable {
    public HashMap<String, TeacherProfile> Teachers = new HashMap<String, TeacherProfile>();
    public HashMap<String, StudentProfile> Students = new HashMap<String, StudentProfile>();
    
    public Response addTeacher(TeacherProfile profile)
    {
        if(Teachers.containsKey(profile.getUsername()) == false)
        {
            Teachers.put(profile.getUsername(), profile);
            return new Response(true, "");
        }
        else
        {
            return new Response(false, "That teacher profile already exists.");
        }
    }
    
    public TeacherProfile getTeacher(String username)
    {
        return Teachers.get(username);
    }
    
    public Response updateTeacher(TeacherProfile profile)
    {
        if(Teachers.containsKey(profile.getUsername()) == true)
        {
            Teachers.put(profile.getUsername(), profile);
            return new Response(true, "");
        }
        else
        {
            return new Response(false, "That teacher profile doesn't exist.");
        }
    }
    
    public boolean teacherExists(String username)
    {
        if(Teachers.containsKey(username) == true)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    public Response addStudent(StudentProfile profile)
    {
        if(Students.containsKey(profile.getUsername()) == false)
        {
            Students.put(profile.getUsername(), profile);
            return new Response(true, "");
        }
        else
        {
            return new Response(false, "That student profile already exists.");
        }
    }
    
    public StudentProfile getStudent(String username)
    {
        return Students.get(username);
    }
    
    public Response updateStudent(StudentProfile profile)
    {
        if(Students.containsKey(profile.getUsername()) == true)
        {
            Students.put(profile.getUsername(), profile);
            return new Response(true, "");
        }
        else
        {
            return new Response(false, "That student profile doesn't exist.");
        }
    }
    
    public boolean studentExists(String username)
    {
        if(Students.containsKey(username) == true)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    public Response loadProfiles()
    {
        try 
        {
            // Read from disk using FileInputStream.
            FileInputStream f_in = new FileInputStream ("Profiles.data");

            // Read object using ObjectInputStream.
            ObjectInputStream obj_in = new ObjectInputStream (f_in);

            // Read an object.
            Object obj = obj_in.readObject();
            
            ProfileRepository profiles = (ProfileRepository)obj;
            
            this.Students = profiles.Students;
            this.Teachers = profiles.Teachers;
            return new Response(true, "");
        } 
        
        catch (FileNotFoundException e) {
            return new Response(false, "File not found.");
        }
        
        catch(java.io.IOException e) {
            return new Response(false, "IO exception.");
        }
        
        catch(java.lang.ClassNotFoundException e) {
            return new Response(false, "Class not found exception.");
        }
    }
    
    public Response storeProfiles()
    {
        try
        {
            // Use a FileOutputStream to send data to a file
            // called myobject.data.
            FileOutputStream f_out = new FileOutputStream("Profiles.data");

            // Use an ObjectOutputStream to send object data to the
            // FileOutputStream for writing to disk.
            ObjectOutputStream obj_out = new ObjectOutputStream(f_out);

            // Pass our object to the ObjectOutputStream's
            // writeObject() method to cause it to be written out
            // to disk.
            obj_out.writeObject(this);

            return new Response(true, "");
        }
        catch (FileNotFoundException e) {
            return new Response(false, "File not found.");
        }
        catch(java.io.IOException e) {
            return new Response(false, "IO Exception.");
        }
    }
}