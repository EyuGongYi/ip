package diomon;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static diomon.Parser.DATEFORMATTER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parseStoredDeadlineTest1(){
        assertEquals(Parser.parseStoredDeadline(new String[]{Deadline.TYPEICON,
                        " ",
                        "deadlineTask",
                        "01-09-2024"}),
                new Deadline(false,
                        "deadlineTask",
                        LocalDate.parse("01-09-2024", DATEFORMATTER)));
    }

    @Test
    public void parseStoredDeadlineTest2(){
        assertEquals(Parser.parseStoredDeadline(new String[]{Deadline.TYPEICON,
                        Deadline.COMPLETEICON,
                        "deadlineTask",
                        "01-09-2024"}),
                new Deadline(true,
                        "deadlineTask",
                        LocalDate.parse("01-09-2024", DATEFORMATTER)));
    }

    @Test
    public void parseStoredDeadlineTest3(){
        DateTimeParseException e = assertThrows(DateTimeParseException.class,
                () -> Parser.parseStoredDeadline(new String[]{Deadline.TYPEICON,
                        Deadline.COMPLETEICON,
                        "deadlineTask",
                        "01-09-202"}));
        assertEquals("Text '01-09-202' could not be parsed at index 6",e.getMessage());
    }

    @Test
    public void parseStoredDeadlineTest4(){
        RuntimeException e = assertThrows(RuntimeException.class,
                () -> Parser.parseStoredDeadline(new String[]{Deadline.TYPEICON,
                        "Y",
                        "deadlineTask",
                        "01-09-2024"}));
        assertEquals("CompletionStatus seem to be wrong",e.getMessage());
    }

    @Test
    public void parseStoredDeadlineTest5(){
        RuntimeException e = assertThrows(RuntimeException.class,
                () -> Parser.parseStoredDeadline(new String[]{Deadline.TYPEICON,
                        "X", "deadlineTask",
                        "01-09-2024",
                        "aaa"}));
        assertEquals("Error loading deadline task, data stored is wrong",e.getMessage());
    }

    @Test
    public void parseStoredTodoTest1(){
        assertEquals(Parser.parseStoredEvent(new String[]{Event.TYPEICON,
                        " ",
                        "eventTask",
                        "01-09-2024",
                        "02-09-2024"}),
            new Event(false,
                    "eventTask",
                    LocalDate.parse("01-09-2024", DATEFORMATTER),
                    LocalDate.parse("02-09-2024", DATEFORMATTER)));
    }

    @Test
    public void parseStoredTodoTest2(){
        assertEquals(Parser.parseStoredEvent(new String[]{Event.TYPEICON, "X", "eventTask", "01-09-2024", "02-09-2024"}),
                new Event(true,
                        "eventTask",
                        LocalDate.parse("01-09-2024", DATEFORMATTER),
                        LocalDate.parse("02-09-2024", DATEFORMATTER)));
    }

    @Test
    public void parseStoredTodoTest3(){
        DateTimeParseException e = assertThrows(DateTimeParseException.class,
                () -> Parser.parseStoredEvent(new String[]{Event.TYPEICON,
                        "X",
                        "eventTask",
                        "01-09-2024",
                        "02-9-2024"}));
        assertEquals("Text '02-9-2024' could not be parsed at index 3",e.getMessage());
    }

    @Test
    public void parseStoredTodoTest4(){
        RuntimeException e = assertThrows(RuntimeException.class,
                () -> Parser.parseStoredEvent(new String[]{Event.TYPEICON,
                        "Y",
                        "eventTask",
                        "01-09-2024",
                        "02-09-2024"}));
        assertEquals("CompletionStatus seem to be wrong",e.getMessage());
    }

    @Test
    public void parseStoredEventTest5(){
        RuntimeException e = assertThrows(RuntimeException.class,
                () -> Parser.parseStoredEvent(new String[]{Event.TYPEICON,
                        "X",
                        "eventTask",
                        "01-09-2024",
                        "02-09-2024",
                        "ss"}));
        assertEquals("Error loading event task, data stored is wrong",e.getMessage());
    }

}
