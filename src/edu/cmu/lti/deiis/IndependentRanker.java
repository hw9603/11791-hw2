package edu.cmu.lti.deiis;

public interface IndependentRanker {
    public double rankAnswer(String question, String answer);
    public String getName();
}
