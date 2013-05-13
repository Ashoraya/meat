/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Ashoraya
 */
public class StudentProgressReportLoader extends Thread {
    @Override
    public void run()
    {
        WindowManager.GetWin(WindowManager.WINDOWS.STUDENTPROGRESSREPORT);
    }
}
