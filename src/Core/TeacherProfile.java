/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Ashoraya
 */
public class TeacherProfile extends Profile implements Serializable {
    public HashMap<Integer, HomeworkAssignment> AssignedHomeworkAssignments;
    
    public TeacherProfile(String firstName, String lastName, String username, String password)
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.AssignedHomeworkAssignments = new HashMap<Integer, HomeworkAssignment>();
    }
    
    public HashMap<Integer, HomeworkAssignment> getAssignedHomeworkAssignments()
    {
        return AssignedHomeworkAssignments;
    }
    
    public Response addHomeworkAssignment(int assignmentNumber, int numOfProblems, int minOperand, int maxOperand, boolean add, boolean sub, boolean mul, boolean div)
    {
        HomeworkAssignment assignment = new HomeworkAssignment();
        assignment.createHomeworkAssignment(assignmentNumber, numOfProblems, minOperand, maxOperand, add, sub, mul, div);
        if(this.AssignedHomeworkAssignments.containsKey(assignmentNumber) == false)
        {
            this.AssignedHomeworkAssignments.put(assignmentNumber, assignment);
            return new Response(true, "");
        }
        else
        {
            return new Response(false, "That assignment number has already been taken.");
        }
    }
}
