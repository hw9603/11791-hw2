package edu.cmu.lti.deiis;

import java.util.List;

public interface AnnotationIndex {
    public void addAnnotation(String type, int begin, int end);
    public List<Annotation> getAnnotations(String type);
    public List<Annotation> getAnnotations(int begin, int end);
    public List<Annotation> getAnnotations();
    public List<Annotation> getAnnotations(String type, int begin, int end);
}
