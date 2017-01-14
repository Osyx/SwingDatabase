import java.sql.Date;

/**
 * Created by Oscar on 17-01-14.
 */
public class Tournament {
    private int id;
    private String name;
    private Date startdate;
    private Date enddate;

    Tournament(int id, String name, Date startdate, Date enddate) {
        this.id = id;
        this.name = name;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public int getId() {
        return id;
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

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    @Override
    public String toString() {
        return String
                .format("Tournament [id=%s, name=%s, startdate=%s, enddate=%s]",
                        id, name, startdate, enddate);
    }
}
