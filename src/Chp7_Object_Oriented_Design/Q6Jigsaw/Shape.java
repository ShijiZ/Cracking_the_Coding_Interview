package Chp7_Object_Oriented_Design.Q6Jigsaw;

public enum Shape {
    INNER, OUTER, FLAT;

    public Shape getOpposite(){
        switch (this){
            case INNER: return OUTER;
            case OUTER: return INNER;
            default: return null;
        }
    }
}
