/**
 * Student number: 20079431
 * @author  Conor Askins
 * @version 1.0
 * @since   2018-11-11
 */
package application;

public class user {
    private String uname;
    private String pword;
    private int uId;
    private boolean isAdmin;


    public user(String uname, String pword, int uId, boolean isAdmin) {
        this.uname = uname;
        this.pword = pword;
        this.uId = uId;
        this.isAdmin = isAdmin;
    }

    //Get and set accessors

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPword() {
        return pword;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    //toString for use in displaying the information in the TextArea's

    public String toString() {
        return "username = "+getUname()+"\npassword = "+getPword()+"\nid = "+getuId();
    }

}
