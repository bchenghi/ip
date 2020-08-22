import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate date;
    protected LocalTime time;
    protected String at;

    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
        this.at = convertDateAndTimeToString();
    }

    String convertDateAndTimeToString() {
        String str = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " + time;
        return str;
    }

    public LocalDate getLocalDate() {
        return date;
    }

    public LocalTime getLocalTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
