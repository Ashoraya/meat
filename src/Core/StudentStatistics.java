/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.io.Serializable;

/**
 *
 * @author Ashoraya
 */
public class StudentStatistics implements Serializable {
    public int numOfCompletedAssignments;
    public int numOfIncorrectAnswers;
    public int numOfAdditionProblems, numOfCorrectAdditionAnswers;
    public int numOfSubtractionProblems, numOfCorrectSubtractionAnswers;
    public int numOfMultiplicationProblems, numOfCorrectMultiplicationAnswers;
    public int numOfDivisionProblems, numOfCorrectDivisionAnswers;
}
