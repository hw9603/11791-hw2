package edu.cmu.lti.deiis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexAnnotator implements Annotator {
    String newType;
    String regex;

    public RegexAnnotator(String targetType, String regex) {
        this.newType = targetType;
        this.regex = regex;
    }

    @Override
    public void process(AnnotatedString annotatedString) {
        String wholeString = annotatedString.getText();
        process(annotatedString, 0, wholeString.length());
    }

    @Override
    public void process(AnnotatedString annotatedString, int begin, int end) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(annotatedString.getText().substring(begin, end));
        while (matcher.find()) {
            AnnotationIndex index = annotatedString.getIndex();
            int sBegin = matcher.start();
            int sEnd = matcher.end();
            index.addAnnotation(newType, begin + sBegin, begin + sEnd);
            annotatedString.setIndex(index);
        }
    }
}
