import java.sql.Date;

/**
 * Created by Oscar on 17-01-14.
 */
public class Arena {
    private final String name;
    private final String place;
    private final int size;
    private final Date builddate;
    private final boolean active;

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

    public String getPlace() {
        return place;
    }

    public int getSize() {
        return size;
    }

    public Date getBuilddate() {
        return builddate;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return String
                .format("Arena [name=%s, place=%s, size=%s, builddate=%s, active=%s]",
                        name, place, size, builddate, active);
    }
}
