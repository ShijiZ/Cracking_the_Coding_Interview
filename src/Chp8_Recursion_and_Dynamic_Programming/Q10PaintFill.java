package Chp8_Recursion_and_Dynamic_Programming;

public class Q10PaintFill {
    enum Color {Black, White, Red, Yellow, Green}

    public static void PaintFill(Color[][] screen, int r, int c, Color newColor){
        if (screen[r][c] == newColor)
            return;
        PaintFill(screen, r, c, screen[r][c], newColor);
    }

    private static void PaintFill(Color[][] screen, int r, int c, Color oldColor, Color newColor){
        if (r < 0 || r >= screen.length || c < 0 || c >= screen[0].length)
            return;

        if (screen[r][c] == oldColor) {
            screen[r][c] = newColor;
            PaintFill(screen, r - 1, c, oldColor, newColor); // up
            PaintFill(screen, r + 1, c, oldColor, newColor); // down
            PaintFill(screen, r, c - 1, oldColor, newColor); // left
            PaintFill(screen, r, c + 1, oldColor, newColor); // right
        }
    }

    /*** Helper methods below ***/
    public static String PrintColor(Color c) {
        switch(c) {
            case Black:
                return "B";
            case White:
                return "W";
            case Red:
                return "R";
            case Yellow:
                return "Y";
            case Green:
                return "G";
        }
        return "X";
    }

    public static void PrintScreen(Color[][] screen) {
        for (int r = 0; r < screen.length; r++) {
            for (int c = 0; c < screen[0].length; c++) {
                System.out.print(PrintColor(screen[r][c]));
            }
            System.out.println();
        }
    }

    public static int randomInt(int n) {
        return (int) (Math.random() * n);
    }
    /*** Helper methods above ***/

    public static void main(String[] args) {
        int N = 10;
        Color[][] screen = new Color[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                screen[i][j] = Color.Black;
            }
        }
        for (int i = 0; i < 100; i++) {
            screen[randomInt(N)][randomInt(N)] = Color.Green;
        }
        PrintScreen(screen);
        PaintFill(screen, 2, 2, Color.White);
        System.out.println();
        PrintScreen(screen);
    }
}
