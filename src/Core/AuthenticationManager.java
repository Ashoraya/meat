/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

/**
 *
 * @author Ashoraya
 */
public class AuthenticationManager {
    public static final AuthenticationManager instance = new AuthenticationManager();
    public Profile currentUser;
    
    private AuthenticationManager()
    {
        resetUser();
    }
    
    public Response login(String username, String password)
    {
        ProfileRepository profiles = new ProfileRepository();
        Response loadProfilesResponse = profiles.loadProfiles();
        if(loadProfilesResponse.success == false)
        {
            if(loadProfilesResponse.failureMessage.equals("File not found.") == true)
            {
                return new Response(false, "Invalid username.");
            }
            else
            {
                return new Response(false, "Failed to load profiles. " + loadProfilesResponse.failureMessage);
            }
        }
        else if(profiles.studentExists(username) == true)
        {
            System.out.println(profiles.getStudent(username).password);
            if(profiles.getStudent(username).password.equals(password) == true) //error
            {
                currentUser = profiles.getStudent(username);
                return new Response(true, "");
            }
            else 
            {
                resetUser();
                return new Response(false, "Invalid password.");
            }
        }
        else if(profiles.teacherExists(username) == true)
        {
            if(profiles.getTeacher(username).password.equals(password) == true)
            {
                currentUser = profiles.getTeacher(username);
                return new Response(true, "");
            }
            else
            {
                resetUser();
                return new Response(false, "Invalid password.");
            }
        }
        else
        {
            resetUser();
            return new Response(false, "Invalid username.");
        }
    }
    
    public void logout()
    {
        resetUser();
    }

    private void resetUser()
    {
        currentUser = new Profile();
    }

    public boolean isCurrentUserOfTypeProfile()
    {
        return currentUser.getClass() == Profile.class;
    }

    public boolean isCurrentUserOfTypeStudentProfile()
    {
        return currentUser.getClass() == StudentProfile.class;
    }

    public boolean isCurrentUserOfTypeTeacherProfile()
    {
        return currentUser.getClass() == TeacherProfile.class;
    }

    public StudentProfile getCurrentUserAsStudentProfile()
    {
        return (StudentProfile) currentUser;
    }

    public TeacherProfile getCurrentUserAsTeacherProfile()
    {
        return (TeacherProfile) currentUser;
    }
}
