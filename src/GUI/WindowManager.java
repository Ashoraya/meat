/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;


public class WindowManager {
    private static Map<Integer, JFrame> allwins = new HashMap<Integer, JFrame>();

    public static void RegisterWin(JFrame win, WINDOWS id)
    {
        allwins.put(id.getId(), win);
    }

    public static JFrame GetWin(WINDOWS win)
    {
        if (allwins.containsKey(win.getId()) == false)
        {
            RenewWin(win);
        }
        return allwins.get(win.getId());
    }

    public static void RenewWin(WINDOWS win)
    {
        JFrame newWindow;
        switch (win)
        {
            case STUDENTMAINMENU:
                newWindow = new StudentMainMenuUI();
                break;
            case LOGIN:
                newWindow = new LoginUI();
                break;
            case CREATEUSER:
                newWindow = new CreateUserUI();
                break;
            case STUDY:
                newWindow = new StudyUI();
                break;
            case HOMEWORK:
                newWindow = new HomeworkUI();
                break;
            case HOMEWORKPROBLEMS:
                newWindow = new HomeworkProblemsUI();
                break;
            case STUDYPROBLEMS:
                newWindow = new StudyProblemsUI();
                break;
            case TEXTBOOK:
                newWindow = new TextbookUI();
                break;
            case CREATEHOMEWORK:
                newWindow = new CreateHomeworkUI();
                break;
            case ADDITIONHINT:
                newWindow = new AdditionHintUI();
                break;
            case SUBTRACTIONHINT:
                newWindow = new SubtractionHintUI();
                break;
            case MULTIPLICATIONHINT:
                newWindow = new MultiplicationHintUI();
                break;
            case DIVISIONHINT:
                newWindow = new DivisionHintUI();
                break;
            case STUDENTPROGRESSREPORT:
                newWindow = new StudentProgressReportUI();
                break;
            default:
                newWindow = new LoginUI();
                break;
        }
        allwins.put(win.getId(), newWindow);
    }

    public enum WINDOWS
    {
        LOGIN(0),
        CREATEUSER(1),
        STUDENTMAINMENU(2),
        HOMEWORK(3),
        STUDY(4),
        HOMEWORKPROBLEMS(6),
        STUDYPROBLEMS(7),
        TEXTBOOK(8),
        ADDITIONHINT(9),
        SUBTRACTIONHINT(10),
        MULTIPLICATIONHINT(11),
        DIVISIONHINT(12),
        STUDENTPROGRESSREPORT(13),
        CREATEHOMEWORK(14);

        WINDOWS(int id)
        {
            this.id = id;
        }
        private int id;

        public int getId()
        {
            return this.id;
        }
    }
}
