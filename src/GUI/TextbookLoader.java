/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Ashoraya
 */
public class TextbookLoader extends Thread{
    @Override
    public void run()
    {
        WindowManager.GetWin(WindowManager.WINDOWS.TEXTBOOK);
    }
}
