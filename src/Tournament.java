import java.sql.Date;

/**
 * Created by Oscar on 17-01-14.
 */
public class Tournament {
    private final int id;
    private final String name;
    private final Date startdate;
    private final Date enddate;

    Tournament(int id, String name, Date startdate, Date enddate) {
        this.id = id;
        this.name = name;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public String getName() {
        return name;
    }

    public Date getStartdate() {
        return startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    @Override
    public String toString() {
        return String
                .format("Tournament [id=%s, name=%s, startdate=%s, enddate=%s]",
                        id, name, startdate, enddate);
    }
}
