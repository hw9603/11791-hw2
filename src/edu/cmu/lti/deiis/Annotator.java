package edu.cmu.lti.deiis;

public interface Annotator {
    public void process(AnnotatedString string);
    public void process(AnnotatedString string, int begin, int end);
}
