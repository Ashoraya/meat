/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Ashoraya
 */
public class HomeworkAssignment implements Serializable {
    public boolean add, sub, mul, div;
    public int assignmentNumber;
    public int numOfProblems = 0;
    public int numOfAdditionProblems = 0;
    public int numOfSubtractionProblems = 0;
    public int numOfMultiplicationProblems = 0;
    public int numOfDivisionProblems = 0;
    public int numOfCorrectAdditionAnswers = 0;
    public int numOfCorrectSubtractionAnswers = 0;
    public int numOfCorrectMultiplicationAnswers = 0;
    public int numOfCorrectDivisionAnswers = 0;
    public int numOfCompletedProblems = 0;
    public int minOperand = 0;
    public int maxOperand = 0;
    public boolean isGenerated = false;
    public HashMap<Integer, HomeworkProblem> HomeworkProblems;
    
    public HomeworkAssignment()
    {
        this.HomeworkProblems = new HashMap<Integer, HomeworkProblem>();
    }
    
    public void createHomeworkAssignment(int assignmentNumber, int numOfProblems, int minOperand, int maxOperand, boolean add, boolean sub, boolean mul, boolean div)
    {
        this.assignmentNumber = assignmentNumber;
        this.numOfProblems = numOfProblems;
        this.add = add;
        this.sub = sub;
        this.mul = mul;
        this.div = div;
    }
    
    public boolean getAdd()
    {
        return add;
    }
    
    public boolean getSub()
    {
        return sub;
    }
    
    public boolean getMul()
    {
        return mul;
    }
    
    public boolean getDiv()
    {
        return div;
    }
    
    public int getNumOfProblems()
    {
        return numOfProblems;
    }
    
    public int getAssignmentNumber()
    {
        return assignmentNumber;
    }
    
    public int getNumOfCompletedProblems()
    {
        return numOfCompletedProblems;
    }
    
    public void incrementNumOfCompletedProblems()
    {
        numOfCompletedProblems += 1;
    }
    
    public HashMap<Integer, HomeworkProblem> getHomeworkProblems()
    {
        return HomeworkProblems;
    }
    
    public void generateRandomHomeworkProblems()
    {      
        for(int i = 0; i < this.numOfProblems; i++)
        {
            Random rand = new Random();
            Operator randomOperator;
            do {
                randomOperator = Operator.values()[rand.nextInt(Operator.values().length)];
            } while((randomOperator == Operator.ADD && add == false) ||
                    (randomOperator == Operator.SUBTRACT && sub == false) || 
                    (randomOperator == Operator.MULTIPLY && mul == false) || 
                    (randomOperator == Operator.DIVIDE && div == false));
            switch(randomOperator)
            {
                case ADD:
                    this.numOfAdditionProblems++;
                    break;
                case SUBTRACT:
                    this.numOfSubtractionProblems++;
                    break;
                case MULTIPLY:
                    this.numOfMultiplicationProblems++;
                    break;
                case DIVIDE:
                    this.numOfDivisionProblems++;
                    break;
            }
            HomeworkProblem problem = new HomeworkProblem();
            problem.generateRandomHomeworkProblem(randomOperator, this.minOperand, this.maxOperand, i);
            this.HomeworkProblems.put(i, problem);
        }
        this.isGenerated = true;
    }
    
    @Override
    public boolean equals(Object object)
    {
        boolean equal = false;
        if (object != null && object instanceof HomeworkAssignment)
        {
            equal = this.assignmentNumber == ((HomeworkAssignment) object).assignmentNumber;
        }
        return equal;
    }
}
