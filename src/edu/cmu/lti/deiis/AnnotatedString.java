package edu.cmu.lti.deiis;

import java.util.List;

public class AnnotatedString {
    String text;
    AnnotationIndex index;

    public AnnotatedString(String text) {
        this.text = text;
        this.index = new ListAnnotationIndex();
    }

    public String getText() {
        return text;
    }

    public AnnotationIndex getIndex() {
        return index;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIndex(AnnotationIndex index) {
        this.index = index;
    }

    public boolean exactMatchCompare(AnnotatedString targetString) {
        AnnotationIndex targetIndex = targetString.getIndex();
        List<Annotation> targetAnnotations = targetIndex.getAnnotations();
        for (Annotation a : index.getAnnotations()) {
            if (!targetAnnotations.contains(a)) return false;
        }
        return true;
    }

    public double softMatchCompare(AnnotatedString targetString) {
        AnnotationIndex targetIndex = targetString.getIndex();
        List<Annotation> targetAnnotations = targetIndex.getAnnotations();
        int found = 0;
        for (Annotation a : index.getAnnotations()) {
            if (targetAnnotations.contains(a)) {
                found++;
            }
        }
        return found / (double) index.getAnnotations().size();
    }


    public static void main(String[] args) {
        AnnotatedString s = new AnnotatedString("Alan W Black is a Scottish computer scientist, known for his research on speech synthesis. "
                                                + "I love computer science. ");
        Annotator a = new RegexAnnotator("edu.cmu.lti.deiis.Sentence", "([^\\.]+\\.)\\s+");
        a.process(s);
        Annotator a2 = new RegexAnnotator("edu.cmu.lti.deiis.Token", "([A-Za-z]+)");
        Annotator a3 = new NGramAnnotator(2, "edu.cmu.lti.deiis.Token", "edu.cmu.lti.deiis.TokenBigram");
        for (Annotation sentence : s.index.getAnnotations("edu.cmu.lti.deiis.Sentence")) {
            System.out.println(sentence + " " + s.text.substring(sentence.begin, sentence.end));
            a2.process(s, sentence.begin, sentence.end);
            a3.process(s, sentence.begin, sentence.end);
            for (Annotation token : s.index.getAnnotations("edu.cmu.lti.deiis.Token", sentence.begin, sentence.end)) {
                System.out.println(token + " " + s.text.substring(token.begin, token.end));
            }
            for (Annotation ngram : s.index.getAnnotations("edu.cmu.lti.deiis.TokenBigram", sentence.begin, sentence.end)) {
                System.out.println(ngram + " " + s.text.substring(ngram.begin, ngram.end));
            }
        }
    }
}
