package greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table COMPUTER.
 */
public class Computer {

    private Long id;
    private String title;
    private String userid;
    private String password;

    public Computer() {
    }

    public Computer(Long id) {
        this.id = id;
    }

    public Computer(Long id, String title, String userid, String password) {
        this.id = id;
        this.title = title;
        this.userid = userid;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
