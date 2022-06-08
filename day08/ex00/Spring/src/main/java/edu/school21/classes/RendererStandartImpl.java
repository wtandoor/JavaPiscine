package edu.school21.classes;

import edu.school21.interfaces.PreProcessor;
import edu.school21.interfaces.Renderer;

public class RendererStandartImpl implements Renderer {
    private final PreProcessor preProcessor;


    public RendererStandartImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void render(String outString) {
        System.out.println(preProcessor.process(outString));
    }
}
