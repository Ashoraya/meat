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
            case MAINSCREEN:
                newWindow = new MainMenuUI();
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
            case ADMIN:
                newWindow = new LoginUI();
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
        MAINSCREEN(2),
        HOMEWORK(3),
        STUDY(4),
        HOMEWORKPROBLEMS(6),
        STUDYPROBLEMS(7),
        ADMIN(8);

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
