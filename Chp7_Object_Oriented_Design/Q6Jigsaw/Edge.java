package Q6Jigsaw;

public class Edge {
    private Shape shape;
    private String code; // used to mock how pieces would fit together.
    private Piece parentPiece;

    public Edge(Shape shape, String code){
        this.shape = shape;
        this.code = code;
    }

    public boolean fitsWith(Edge edge){
        return edge.getCode().equals(getCode());
    }

    private String getCode(){
        return code;
    }

    public Edge createMatchingEdge(){
        if (shape == Shape.FLAT) return null;
        return new Edge(shape.getOpposite(), getCode());
    }

    public void setParentPiece(Piece parentPiece){
        this.parentPiece = parentPiece;
    }

    public Piece getParentPiece(){
        return parentPiece;
    }

    public Shape getShape(){
        return shape;
    }

    public String toString(){
        return code;
    }
}
