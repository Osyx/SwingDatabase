import java.sql.Date;

/**
 * Created by Oscar on 17-01-14.
 */
public class Arena {
    private String name;
    private String place;
    private int size;
    private Date builddate;
    private boolean active;

    Arena(String name, String place, int size, Date builddate, boolean active) {
        this.name = name;
        this.place = place;
        this.size = size;
        this.builddate = builddate;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Date getBuilddate() {
        return builddate;
    }

    public void setBuilddate(Date builddate) {
        this.builddate = builddate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String
                .format("Arena [name=%s, place=%s, size=%s, builddate=%s, active=%s]",
                        name, place, size, builddate, active);
    }
}
