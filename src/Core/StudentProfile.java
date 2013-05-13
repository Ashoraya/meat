/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Ashoraya
 */
public class StudentProfile extends Profile implements Serializable {
    public String teacherUsername;
    public StudentStatistics statistics;
    public HashMap<Integer, HomeworkAssignment> HomeworkAssignments;
    
    public StudentProfile(String firstName, String lastName, String teacherUsername, String username, String password)
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.HomeworkAssignments = new HashMap<Integer, HomeworkAssignment>();
        this.teacherUsername = teacherUsername;
    }
    
    public HashMap<Integer, HomeworkAssignment> getHomeworkAssignments()
    {
        return HomeworkAssignments;
    }  
    
    public HomeworkAssignment getHomeworkAssignment(int assignmentNumber)
    {
        return HomeworkAssignments.get(assignmentNumber);
    }
    
    public String getTeacherUsername()
    {
        return teacherUsername;
    }
    
    public StudentStatistics getStatistics()
    {
        return statistics;
    }
    
    public Response synchronizeAndGenerateHomeworkAssignments()
    {
        ProfileRepository profiles = new ProfileRepository();
        Response loadProfilesResponse = profiles.loadProfiles();
        TeacherProfile teacher = profiles.getTeacher(this.teacherUsername);
        if(loadProfilesResponse.success == false)
        {
            return new Response(false, "Failed to load profiles. " + loadProfilesResponse.failureMessage);
        }
        if(profiles.teacherExists(teacher.username) == false)
        {
            return new Response(false, "That teacher does not exist.");
        }
  
        //Remove any assignments that the student has but the teacher hasn't assigned
        Iterator studentKeySetIterator = this.HomeworkAssignments.keySet().iterator();
        while(studentKeySetIterator.hasNext())
        {
            if(teacher.AssignedHomeworkAssignments.containsKey((Integer)studentKeySetIterator.next()) == false)
            {
                studentKeySetIterator.remove();
            }
        }
        
        //Add any assignments that the teacher has assigned but the student doesn't have
        for (Integer key : teacher.AssignedHomeworkAssignments.keySet()) {
            if(this.HomeworkAssignments.containsKey(key) == false)
            {
                int assignmentNumber = teacher.AssignedHomeworkAssignments.get(key).assignmentNumber;
                int numOfProblems = teacher.AssignedHomeworkAssignments.get(key).numOfProblems;
                int minOperand = teacher.AssignedHomeworkAssignments.get(key).minOperand;
                int maxOperand = teacher.AssignedHomeworkAssignments.get(key).maxOperand;
                boolean add = teacher.AssignedHomeworkAssignments.get(key).add;
                boolean sub = teacher.AssignedHomeworkAssignments.get(key).sub;
                boolean mul = teacher.AssignedHomeworkAssignments.get(key).mul;
                boolean div = teacher.AssignedHomeworkAssignments.get(key).div;
                HomeworkAssignment assignment = new HomeworkAssignment();
                assignment.createHomeworkAssignment(assignmentNumber, numOfProblems, minOperand, maxOperand, add, sub, mul, div);
                assignment.generateRandomHomeworkProblems();
                this.HomeworkAssignments.put(assignmentNumber, assignment);
            }
        }
        return new Response(true, "");
    }
}
