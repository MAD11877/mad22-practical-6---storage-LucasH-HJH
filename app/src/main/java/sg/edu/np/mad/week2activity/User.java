package sg.edu.np.mad.week2activity;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String description;
    private int id;
    private static boolean followed;

    public User() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public static boolean isFollowed() {
        return followed;
    }
}
