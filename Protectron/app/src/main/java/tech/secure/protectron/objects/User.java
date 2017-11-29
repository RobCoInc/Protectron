package tech.secure.protectron.objects;

/**
 * Created by Morgan on 2017-11-12.
 */

public class User {

    private long id;
    private String name;
    private String email;
    private String password;
    private String isAdmin;
    private int secureNum;

    public long getId() {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setIsAdmin(String isAdmin)
    {
        this.isAdmin = isAdmin;
    }

    public String getIsAdmin()
    {
        return isAdmin;
    }

    public void setSecureNum(int secNum){
        this.secureNum = secNum;
    }

    public int getSecureNum(){
        return secureNum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
