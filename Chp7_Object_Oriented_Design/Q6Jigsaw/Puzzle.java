package Q6Jigsaw;

import java.util.LinkedList;

public class Puzzle {
    private LinkedList<Piece> pieces; // Remaining pieces to put away.
    private Piece[][] solution;
    private int size;

    public Puzzle(int size, LinkedList<Piece> pieces){
        this.pieces = pieces;
        this.size = size;
    }

    /* Put piece into the solution, turn it appropriately, and remove from list.*/
    private void setEdgeInSolution(LinkedList<Piece> pieces, Edge edge, int row,
                                   int column, Orientation orientation){
        Piece piece = edge.getParentPiece();
        piece.setEdgeAsOrientation(edge, orientation);
        pieces.remove(piece);
        solution[row][column] = piece;
    }

    /* Find the matching piece in pieceToSearch and insert it at row, column. */
    private boolean fitNextEdge(LinkedList<Piece> piecesToSearch, int row, int col){
        if (row == 0 && col == 0){ // On top left corner, just put in a piece
            Piece p = piecesToSearch.remove();
            this.orientTopLeftCorner(p);
            solution[0][0] = p;
        } else{
            /* get the right edge and list to match. */
            Piece pieceTooMatch = col == 0 ? solution[row - 1][0] : solution[row][col - 1];
            Orientation orientationToMatch = col == 0 ? Orientation.BOTTOM : Orientation.RIGHT;
            Edge edgeToMatch = pieceTooMatch.getEdgeWithOrientation(orientationToMatch);

            /* Get matching edge. */
            Edge edge = getMatchingEdge(edgeToMatch, piecesToSearch);
            if (edge == null) return false;

            /* Insert piece and edge. */
            Orientation orientation = orientationToMatch.getOpposite();
            this.setEdgeInSolution(piecesToSearch, edge, row, col, orientation);
        }
        return true;
    }

    /* Solce puzzle. */
    public boolean solve(){
        /* Group pieces. */
        LinkedList<Piece> cornerPieces = new LinkedList<>();
        LinkedList<Piece> borderPieces = new LinkedList<>();
        LinkedList<Piece> insidePieces = new LinkedList<>();
        this.groupPieces(cornerPieces, borderPieces, insidePieces);

        /* Walk through puzzle, finding the piece that joins the previous one. */
        solution = new Piece[size][size];
        for (int row = 0; row < size; row++){
            for (int column = 0; column < size; column++){
                LinkedList<Piece> piecesToSearch = this.getPieceListToSearch(cornerPieces,
                        borderPieces, insidePieces, row, column);
                if (!fitNextEdge(piecesToSearch, row, column))
                    return false;
            }
        }
        return true;
    }

    /* Group pieces into border pieces (including corners) and inside pieces. */
    public void groupPieces(LinkedList<Piece> cornerPieces, LinkedList<Piece> borderPieces,
                            LinkedList<Piece> insidePieces){
        for (Piece p : pieces){
            if (p.isCorner())
                cornerPieces.add(p);
            else if (p.isBorder())
                borderPieces.add(p);
            else
                insidePieces.add(p);
        }
    }

    public void orientTopLeftCorner(Piece piece){
        if (!piece.isCorner())
            return;
        Orientation[] orientations = Orientation.values();
        for (int i=0; i<orientations.length; i++){
            Edge current = piece.getEdgeWithOrientation(orientations[i]);
            Edge next = piece.getEdgeWithOrientation(orientations[(i + 1) % orientations.length]);
            if (current.getShape() == Shape.FLAT && next.getShape() == Shape.FLAT){
                piece.setEdgeAsOrientation(current, Orientation.LEFT);
                return;
            }

        }
    }

    /* Bounds check. Check if this index is on a border (0 or size - 1) */
    public boolean isBorderIndex(int location) {
        return location == 0 || location == size - 1;
    }

    /* Given a list of pieces, check if any have an edge that matches this piece. Return the edge*/
    private Edge getMatchingEdge(Edge targetEdge, LinkedList<Piece> pieces) {
        for (Piece piece : pieces) {
            Edge matchingEdge = piece.getMatchingEdge(targetEdge);
            if (matchingEdge != null) {
                return matchingEdge;
            }
        }
        return null;
    }

    /* Return the list where a piece with this index would be found. */
    private LinkedList<Piece> getPieceListToSearch(LinkedList<Piece> cornerPieces, LinkedList<Piece> borderPieces,
                                                   LinkedList<Piece> insidePieces, int row, int column) {
        if (isBorderIndex(row) && isBorderIndex(column)) {
            return cornerPieces;
        } else if (isBorderIndex(row) || isBorderIndex(column)) {
            return borderPieces;
        } else {
            return insidePieces;
        }
    }

    public Piece[][] getCurrentSolution() {
        return solution;
    }

}
