/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;


public class Response {
    public boolean success;
    public String failureMessage;

    public Response(boolean success, String msg)
    {
        this.success = success;
        this.failureMessage = msg;
    }
}
