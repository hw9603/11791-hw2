package edu.cmu.lti.deiis;

public class SoftMatchRanker extends BigramRanker {
    @Override
    public String getName() {
        return "SoftMatchRanker";
    }

    @Override
    public double doSoftMatchRanking() {
        return answerAnnotatedString.softMatchCompare(questionAnnotatedString);
    }
}
