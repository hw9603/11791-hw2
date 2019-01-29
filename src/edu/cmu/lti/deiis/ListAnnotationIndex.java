package edu.cmu.lti.deiis;

import java.util.ArrayList;
import java.util.List;

public class ListAnnotationIndex implements AnnotationIndex {
    public List<Annotation> annotations;

    public ListAnnotationIndex() {
        annotations = new ArrayList<>();
    }

    @Override
    public void addAnnotation(String type, int begin, int end) {
        annotations.add(new Annotation(type, begin, end));
    }

    @Override
    public List<Annotation> getAnnotations(String type) {
        List<Annotation> res = new ArrayList<>();
        for (Annotation a : annotations) {
            if (a.getType().equals(type)) {
                res.add(a);
            }
        }
        return res;
    }

    @Override
    public List<Annotation> getAnnotations(int begin, int end) {
        List<Annotation> res = new ArrayList<>();
        for (Annotation a : annotations) {
            if (a.getBegin() >= begin && a.getEnd() <= end) {
                res.add(a);
            }
        }
        return res;
    }

    @Override
    public List<Annotation> getAnnotations() {
        return annotations;
    }

    @Override
    public List<Annotation> getAnnotations(String type, int begin, int end) {
        List<Annotation> res = new ArrayList<>();
        for (Annotation a : annotations) {
            if (a.getType().equals(type) && a.getBegin() >= begin && a.getEnd() <= end) {
                res.add(a);
            }
        }
        return res;
    }
}
