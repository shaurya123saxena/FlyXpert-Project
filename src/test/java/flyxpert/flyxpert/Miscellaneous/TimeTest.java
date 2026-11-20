package flyxpert.flyxpert.Miscellaneous;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TimeTest {

    @Test
    void formatsAsHourMinutesPeriod() {
        assertEquals("9:30 AM", new Time("9", "30", "AM").toString());
    }

    @Test
    void settersUpdateTheFormattedTime() {
        Time t = new Time("9", "30", "AM");
        t.setHour("11");
        t.setMinutes("05");
        t.setPeriod("PM");
        assertEquals("11:05 PM", t.toString());
    }
}
