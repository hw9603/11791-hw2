package edu.cmu.lti.deiis;

public class Annotation {
    String type;
    int begin;
    int end;

    public Annotation(String type, int begin, int end) {
        this.type = type;
        this.begin = begin;
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "[" + begin + "," + end + " " + type + "]";
    }
}
