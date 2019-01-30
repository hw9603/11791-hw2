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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Annotation)) {
            return false;
        }
        Annotation annotation = (Annotation) o;
        if (annotation.getType().equals(type) && annotation.getEnd() == end && annotation.getBegin() == begin) {
            return true;
        } else {
            return false;
        }
    }
}
