package appllication.model;


import java.time.LocalDateTime;

public class RequestPage {


    private int index;

    private int size;

    private String title;

    private String description;

    private LocalDateTime firsTime;

    private LocalDateTime secondTime;

    private String userName;

    public RequestPage(){
        firsTime= LocalDateTime.now();
        secondTime= LocalDateTime.now().plusDays(1);
    }



    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getFirsTime() {
        return firsTime;
    }

    public void setFirsTime(LocalDateTime firsTime) {
        this.firsTime = firsTime;
    }

    public LocalDateTime getSecondTime() {
        return secondTime;
    }

    public void setSecondTime(LocalDateTime secondTime) {
        this.secondTime = secondTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
