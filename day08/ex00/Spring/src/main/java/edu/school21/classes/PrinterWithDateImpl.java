package edu.school21.classes;

import edu.school21.interfaces.Printer;
import edu.school21.interfaces.Renderer;
import java.time.LocalDateTime;

public class PrinterWithDateImpl implements Printer {
    private LocalDateTime dateTime;
    private final Renderer renderer;

    public PrinterWithDateImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public void print(String outString) {
        if (dateTime != null){
            renderer.render(dateTime + outString);
        }
        renderer.render(outString);
    }
}
