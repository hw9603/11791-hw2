package edu.cmu.lti.deiis;

public abstract class BigramRanker implements IndependentRanker {
    AnnotatedString questionAnnotatedString;
    AnnotatedString answerAnnotatedString;

    @Override
    public double rankAnswer(String question, String answer) {
        questionAnnotatedString = new AnnotatedString(question);
        answerAnnotatedString = new AnnotatedString(answer);
        questionAnnotatedString = bigramAnnotate(questionAnnotatedString);
        answerAnnotatedString = bigramAnnotate(answerAnnotatedString);
        return doSoftMatchRanking();
    }

    public AnnotatedString bigramAnnotate(AnnotatedString annotatedString) {
        Annotator a = new RegexAnnotator("edu.cmu.lti.deiis.Sentence", "([^\\.]+\\.)\\s+");
        a.process(annotatedString);
        Annotator a2 = new RegexAnnotator("edu.cmu.lti.deiis.Token", "([A-Za-z]+)");
        Annotator a3 = new NGramAnnotator(2, "edu.cmu.lti.deiis.Token", "edu.cmu.lti.deiis.TokenBigram");
        for (Annotation sentence : annotatedString.index.getAnnotations("edu.cmu.lti.deiis.Sentence")) {
            a2.process(annotatedString, sentence.begin, sentence.end);
            a3.process(annotatedString, sentence.begin, sentence.end);
        }
        return annotatedString;
    }

    public abstract double doSoftMatchRanking();
}
