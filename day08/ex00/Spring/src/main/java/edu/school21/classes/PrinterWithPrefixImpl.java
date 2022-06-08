package edu.school21.classes;

import edu.school21.interfaces.*;
import edu.school21.interfaces.Renderer;

import javax.swing.*;

public class PrinterWithPrefixImpl implements Printer {
    private final Renderer renderer;
    private String prefix = "";

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(String outString) {
        if (!prefix.isEmpty()){
            prefix += " ";
        }
        renderer.render(prefix + outString);
    }
}
