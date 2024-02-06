package ChatbotRan;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String contents, String from, String to) {
        this(contents, LocalDate.parse(from), LocalDate.parse(to));
    }

    public Event(String contents, LocalDate from, LocalDate to) {
        super(contents);
        this.from = from;
        this.to = to;
    }

    public static Event parse(String line, int space) {
        String[] texts = Util.parse(line, space, "/from", "/to");
        if (texts != null) {
            return new Event(texts[0], texts[1], texts[2]);
        }
        return null;
    }

    @Override
    String getType() {
        return "E";
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    String writeTask() {
        return String.format("E\\%b\\%s\\%s\\%s", completed, contents, from, to);
    }
}
