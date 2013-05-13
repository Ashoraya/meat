/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

/**
 *
 * @author Ashoraya
 */
public class UserManager {
    public static final UserManager instance = new UserManager();
    public Profile currentUser;
    
    private UserManager()
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
            if(profiles.getStudent(username).password.equals(password) == true)
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
    
    public Response updateAndSaveStudentUser(StudentProfile student)
    {
        ProfileRepository profiles = new ProfileRepository();
        Response loadProfilesResponse = profiles.loadProfiles();
        if(loadProfilesResponse.success == false)
        {
            return new Response(false, "Failed to load profiles. " + loadProfilesResponse.failureMessage);
        }
        else if(profiles.studentExists(student.username) == false)
        {
            return new Response(false, "That student does not exist.");
        }
        else
        {
            Response updateStudentResponse = profiles.updateStudent(student);
            if(updateStudentResponse.success == false)
            {
                return new Response(false, updateStudentResponse.failureMessage);
            }
            
            Response storeProfilesResponse = profiles.storeProfiles();
            if(storeProfilesResponse.success == false)
            {
                return new Response(false, storeProfilesResponse.failureMessage);
            }
            
            currentUser = student;
            return new Response(true, "");
        }
    }
    
    public Response updateAndSaveTeacherUser(TeacherProfile teacher)
    {
        ProfileRepository profiles = new ProfileRepository();
        Response loadProfilesResponse = profiles.loadProfiles();
        if(loadProfilesResponse.success == false)
        {
            return new Response(false, "Failed to load profiles. " + loadProfilesResponse.failureMessage);
        }
        else if(profiles.teacherExists(teacher.username) == false)
        {
            return new Response(false, "That teacher does not exist.");
        }
        else
        {
            Response updateTeacherResponse = profiles.updateTeacher(teacher);
            if(updateTeacherResponse.success == false)
            {
                return new Response(false, updateTeacherResponse.failureMessage);
            }
            
            Response storeProfilesResponse = profiles.storeProfiles();
            if(storeProfilesResponse.success == false)
            {
                return new Response(false, storeProfilesResponse.failureMessage);
            }
            
            currentUser = teacher;
            return new Response(true, "");
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
