package edu.cmu.lti.deiis;

import java.util.List;

public class NGramAnnotator implements Annotator {
    int ngram;
    String targetType;
    String newType;

    public NGramAnnotator(int ngram, String targetType, String newType) {
        this.ngram = ngram;
        this.targetType = targetType;
        this.newType = newType;
    }

    @Override
    public void process(AnnotatedString annotatedString) {
        String wholeString = annotatedString.getText();
        process(annotatedString, 0, wholeString.length());
    }

    @Override
    public void process(AnnotatedString annotatedString, int begin, int end) {
        AnnotationIndex annotationIndex = annotatedString.getIndex();
        List<Annotation> filteredIndex = annotationIndex.getAnnotations(targetType, begin, end);
        for (int i = 0; i < filteredIndex.size() - ngram + 1; i++) {
            int beginIndex = filteredIndex.get(i).getBegin();
            int endIndex = filteredIndex.get(i + ngram - 1).getEnd();
            annotationIndex.addAnnotation(newType, beginIndex, endIndex);
        }
    }
}
