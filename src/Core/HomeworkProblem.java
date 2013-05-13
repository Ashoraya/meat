/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Ashoraya
 */
public class HomeworkProblem implements Serializable {
    public int leftOperand, rightOperand, correctAnswer, problemNumber;
    public Operator operator;
    public boolean isGenerated = false;
    
    public int getLeftOperand()
    {
        return this.leftOperand;
    }
    
    public int getRightOperand()
    {
        return this.rightOperand;
    }
    
    public Operator getOperator()
    {
        return this.operator;
    }
    
    public int getCorrectAnswer()
    {
        return this.correctAnswer;
    }
    
    public int getProblemNumnber()
    {
        return this.problemNumber;
    }
    
    public boolean getIsGenerated()
    {
        return this.isGenerated;
    }
    
    public void generateRandomHomeworkProblem(Operator operator, int minOperandValue, int maxOperandValue, int problemNumber)
    {
        Random rand = new Random();
        this.operator = operator;
        this.problemNumber = problemNumber;
        if(operator == Operator.DIVIDE)
        {
            do {
                this.leftOperand = rand.nextInt(maxOperandValue - minOperandValue + 1) + minOperandValue;
                this.rightOperand = rand.nextInt(maxOperandValue - minOperandValue + 1) + minOperandValue;
            } while(this.leftOperand % this.rightOperand != 0);
        }
        else
        {
            this.leftOperand = rand.nextInt(maxOperandValue - minOperandValue + 1) + minOperandValue;
            this.rightOperand = rand.nextInt(maxOperandValue - minOperandValue + 1) + minOperandValue;
        }
        switch(operator)
        {
            case ADD:
                this.correctAnswer = this.leftOperand + this.rightOperand;
                break;
            case SUBTRACT:
                this.correctAnswer = this.leftOperand - this.rightOperand;
                break;
            case MULTIPLY:
                this.correctAnswer = this.leftOperand * this.rightOperand;
                break;
            case DIVIDE:
                this.correctAnswer = this.leftOperand / this.rightOperand;
                break;
        }
        this.isGenerated = true;
    }
    
    public void generateNonRandomHomeworkProblem(Operator operator, int leftOperand, int rightOperand, int problemNumber)
    {
        this.operator = operator;
        this.problemNumber = problemNumber;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        switch(operator)
        {
            case ADD:
                this.correctAnswer = this.leftOperand + this.rightOperand;
                break;
            case SUBTRACT:
                this.correctAnswer = this.leftOperand - this.rightOperand;
                break;
            case MULTIPLY:
                this.correctAnswer = this.leftOperand * this.rightOperand;
                break;
            case DIVIDE:
                this.correctAnswer = this.leftOperand / this.rightOperand;
                break;
        }
        this.isGenerated = true;
    }
}
