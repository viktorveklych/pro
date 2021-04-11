import java.util.Random;
import java.util.Scanner;

public class TicTackToeWithLogic {
    private static final int X = 1;
    private static final int O = 2;

    public static void main(String[] args) {

        showInvitation();
        int[] signs = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        boolean usersTurn = whoTurnsFirst();
        while (true) {
            if (usersTurn) {
                usersTurn(signs);
                displayGameField(signs);
                if (checkOfVictory(signs, X)) {
                    break;
                }
                if (checkForDraw(signs)) {
                    break;
                }
                computersTurn(signs);
                displayGameField(signs);
                if (checkOfVictory(signs, O)) {
                    break;
                }
            } else {
                computersTurn(signs);
                displayGameField(signs);
                if (checkOfVictory(signs, O)) {
                    break;
                }
                if (checkForDraw(signs)) {
                    break;
                }
                usersTurn(signs);
                displayGameField(signs);
                if (checkOfVictory(signs, X)) {
                    break;
                }
            }
            if (checkForDraw(signs)) {
                break;
            }
        }
        if (checkOfVictory(signs, O)) {
            System.out.println("Computer WIN!!!");
        } else if (checkOfVictory(signs, X)) {
            System.out.println("Congratulations!!! You WIN!!!");
        }
        exit();
    }

    public static boolean checkOfVictory(int[] array, int sign) {
        return checkOfVictoryByRows(array, sign) ||
                checkOfVictoryByColumns(array, sign) ||
                checkOfVictoryByDiagonals(array, sign);
    }

    public static boolean checkOfVictoryByRows(int[] array, int sign) {
        return array[0] == sign && array[1] == sign && array[2] == sign ||
                array[3] == sign && array[4] == sign && array[5] == sign ||
                array[6] == sign && array[7] == sign && array[8] == sign;
    }

    public static boolean checkOfVictoryByColumns(int[] array, int sign) {
        return array[0] == sign && array[3] == sign && array[6] == sign ||
                array[1] == sign && array[4] == sign && array[7] == sign ||
                array[2] == sign && array[5] == sign && array[8] == sign;
    }

    public static boolean checkOfVictoryByDiagonals(int[] array, int sign) {
        return array[6] == sign && array[4] == sign && array[2] == sign ||
                array[0] == sign && array[4] == sign && array[8] == sign;
    }

    public static void showInvitation() {
        System.out.println("Welcome to TicTackToe");
        System.out.println();
        System.out.println("You are going to play with sign \"X\".");
        System.out.println("Use buttons with digits (from 1 to 9) to make your turn");
        System.out.println();
        System.out.println("___________________");
        System.out.println("|  7  |  8  |  9  |");
        System.out.println("-------------------");
        System.out.println("|  4  |  5  |  6  |");
        System.out.println("-------------------");
        System.out.println("|  1  |  2  |  3  |");
        System.out.println("___________________");
        System.out.println();
        System.out.println("Put three X-es inline to win! GooD LucK!)");
    }

    public static void displayGameField(int[] array) {
        char[] g = new char[9];
        for (int i = 0; i < array.length; i++) {
            if (array[i] == X) {
                g[i] = 'X';
            } else if (array[i] == O) {
                g[i] = 'O';
            } else g[i] = ' ';
        }
        System.out.println("___________________");
        System.out.println("|  " + g[6] + "  |  " + g[7] + "  |  " + g[8] + "  |");
        System.out.println("-------------------");
        System.out.println("|  " + g[3] + "  |  " + g[4] + "  |  " + g[5] + "  |");
        System.out.println("-------------------");
        System.out.println("|  " + g[0] + "  |  " + g[1] + "  |  " + g[2] + "  |");
        System.out.println("___________________");
    }


    public static void usersTurn(int[] array) {
        while (true) {
            System.out.println("Make your turn, please:");
            int usersEnter = new Scanner(System.in).nextInt();
            usersEnter = usersEnter - 1;
            if (array[usersEnter] == O || array[usersEnter] == X) {
                System.out.println("This position is busy, choose other position");
            } else if (usersEnter < 0 || usersEnter > 9) { // why can't I  cross this conditions ?
                System.out.println("Your number MUST be from 1 to 9! Try again");
            } else {
                array[usersEnter] = X;
                return;
            }
        }
    }


    public static boolean fillFreePositionBetweenOO(int[] array) {
        boolean isFound = false;
        if (array[0] == O && array[2] == O && isEmpty(array, 1)) {
            array[1] = O;
            isFound = true;
        } else if (array[3] == O && array[5] == O && isEmpty(array, 4)) {
            array[4] = O;
            isFound = true;
        } else if (array[6] == O && array[8] == O && isEmpty(array, 7)) {
            array[7] = O;
            isFound = true;
        } else if (array[0] == O && array[6] == O && isEmpty(array, 3)) {
            array[3] = O;
            isFound = true;
        } else if (array[1] == O && array[7] == O && isEmpty(array, 4)) {
            array[4] = O;
            isFound = true;
        } else if (array[2] == O && array[8] == O && isEmpty(array, 5)) {
            array[5] = O;
            isFound = true;
        } else if (array[0] == O && array[8] == O && isEmpty(array, 4)) {
            array[4] = O;
            isFound = true;
        } else if (array[6] == O && array[2] == O && isEmpty(array, 4)) {
            array[4] = O;
            isFound = true;
        }
        return isFound;
    }

    public static boolean defenceFreePositionBetweenXX(int[] array) {
        boolean isFound = false;
        if (array[0] == X && array[2] == X && isEmpty(array, 1)) {
            array[1] = O;
            isFound = true;
        } else if (array[3] == X && array[5] == X && isEmpty(array, 4)) {
            array[4] = O;
            isFound = true;
        } else if (array[6] == X && array[8] == X && isEmpty(array, 7)) {
            array[7] = O;
            isFound = true;
        } else if (array[0] == X && array[6] == X && isEmpty(array, 3)) {
            array[3] = O;
            isFound = true;
        } else if (array[1] == X && array[7] == X && isEmpty(array, 4)) {
            array[4] = O;
            isFound = true;
        } else if (array[2] == X && array[8] == X && isEmpty(array, 5)) {
            array[5] = O;
            isFound = true;
        } else if (array[0] == X && array[8] == X && isEmpty(array, 4)) {
            array[4] = O;
            isFound = true;
        } else if (array[6] == X && array[2] == X && isEmpty(array, 4)) {
            array[4] = O;
            isFound = true;
        }
        return isFound;
    }

    public static boolean defenceFromXXTogether(int[] array) {
        boolean isFound = false;
        if (array[0] == X && array[1] == X && isEmpty(array, 2)) {
            array[2] = O;
            isFound = true;
        } else if (array[1] == X && array[2] == X && isEmpty(array, 0)) {
            array[0] = O;
            isFound = true;
        } else if (array[3] == X && array[4] == X && isEmpty(array, 5)) {
            array[5] = O;
            isFound = true;
        } else if (array[4] == X && array[5] == X && isEmpty(array, 3)) {
            array[3] = O;
            isFound = true;
        } else if (array[6] == X && array[7] == X && isEmpty(array, 8)) {
            array[8] = O;
            isFound = true;
        } else if (array[7] == X && array[8] == X && isEmpty(array, 6)) {
            array[6] = O;
            isFound = true;
        } else if (array[0] == X && array[3] == X && isEmpty(array, 6)) {
            array[6] = O;
            isFound = true;
        } else if (array[3] == X && array[6] == X && isEmpty(array, 0)) {
            array[0] = O;
            isFound = true;
        } else if (array[1] == X && array[4] == X && isEmpty(array, 7)) {
            array[7] = O;
            isFound = true;
        } else if (array[4] == X && array[7] == X && isEmpty(array, 1)) {
            array[1] = O;
            isFound = true;
        } else if (array[2] == X && array[5] == X && isEmpty(array, 8)) {
            array[8] = O;
            isFound = true;
        } else if (array[5] == X && array[8] == X && isEmpty(array, 2)) {
            array[2] = O;
            isFound = true;
        } else if (array[0] == X && array[4] == X && isEmpty(array, 8)) {
            array[8] = O;
            isFound = true;
        } else if (array[4] == X && array[8] == X && isEmpty(array, 0)) {
            array[0] = O;
            isFound = true;
        } else if (array[2] == X && array[4] == X && isEmpty(array, 6)) {
            array[6] = O;
            isFound = true;
        } else if (array[6] == X && array[4] == X && isEmpty(array, 2)) {
            array[2] = O;
            isFound = true;
        }
        return isFound;
    }

    public static boolean fillPositionOOTogether(int[] array) {
        boolean isFound = false;
        if (array[0] == O && array[1] == O && isEmpty(array, 2)) {
            array[2] = O;
            isFound = true;
        } else if (array[1] == O && array[2] == O && isEmpty(array, 0)) {
            array[0] = O;
            isFound = true;
        } else if (array[3] == O && array[4] == O && isEmpty(array, 5)) {
            array[5] = O;
            isFound = true;
        } else if (array[4] == O && array[5] == O && isEmpty(array, 3)) {
            array[3] = O;
            isFound = true;
        } else if (array[6] == O && array[7] == O && isEmpty(array, 8)) {
            array[8] = O;
            isFound = true;
        } else if (array[7] == O && array[8] == O && isEmpty(array, 6)) {
            array[6] = O;
            isFound = true;
        } else if (array[0] == O && array[3] == O && isEmpty(array, 6)) {
            array[6] = O;
            isFound = true;
        } else if (array[3] == O && array[6] == O && isEmpty(array, 0)) {
            array[0] = O;
            isFound = true;
        } else if (array[1] == O && array[4] == O && isEmpty(array, 7)) {
            array[7] = O;
            isFound = true;
        } else if (array[4] == O && array[7] == O && isEmpty(array, 1)) {
            array[1] = O;
            isFound = true;
        } else if (array[2] == O && array[5] == O && isEmpty(array, 8)) {
            array[8] = O;
            isFound = true;
        } else if (array[5] == O && array[8] == O && isEmpty(array, 2)) {
            array[2] = O;
            isFound = true;
        } else if (array[0] == O && array[4] == O && isEmpty(array, 8)) {
            array[8] = O;
            isFound = true;
        } else if (array[4] == O && array[8] == O && isEmpty(array, 0)) {
            array[0] = O;
            isFound = true;
        } else if (array[2] == O && array[4] == O && isEmpty(array, 6)) {
            array[6] = O;
            isFound = true;
        } else if (array[6] == O && array[4] == O && isEmpty(array, 2)) {
            array[2] = O;
            isFound = true;
        }
        return isFound;
    }

    public static boolean defenceFromXInRowOrColumn(int[] array) {
        boolean isFound = false;
        if (array[0] == X && isEmpty(array, 2)) {
            array[2] = O;
            isFound = true;
        } else if (array[2] == X && isEmpty(array, 0)) {
            array[0] = O;
            isFound = true;
        } else if (array[3] == X && isEmpty(array, 5)) {
            array[5] = O;
            isFound = true;
        } else if (array[5] == X && isEmpty(array, 3)) {
            array[3] = O;
            isFound = true;
        } else if (array[6] == X && isEmpty(array, 8)) {
            array[8] = O;
            isFound = true;
        } else if (array[8] == X && isEmpty(array, 6)) {
            array[6] = O;
            isFound = true;
        } else if (array[7] == X && isEmpty(array, 1)) {
            array[1] = O;
            isFound = true;
        } else if (array[1] == X && isEmpty(array, 7)) {
            array[7] = O;
            isFound = true;
        } else if (array[8] == X && isEmpty(array, 2)) {
            array[2] = O;
            isFound = true;
        } else if (array[2] == X && isEmpty(array, 8)) {
            array[8] = O;
            isFound = true;
        }
        return isFound;
    }


    public static void computersTurn(int[] array) {
        if (fillFreePositionBetweenOO(array)) {
            return;
        }
        if (fillPositionOOTogether(array)) {
            return;
        }
        if (defenceFreePositionBetweenXX(array)) {
            return;
        }
        if (defenceFromXXTogether(array)) {
            return;
        }
        if (defenceFromXInRowOrColumn(array)) {
            return;
        }

        while (true) {
            int computerEnter = new Random().nextInt(8);
            if (isEmpty(array, computerEnter)) {
                array[computerEnter] = O;
                return;
            }
        }
    }

    public static boolean whoTurnsFirst() {
        return new Random().nextBoolean();
    }

    public static void exit() {
        System.out.println("GAME OVER!");
    }

    public static boolean isEmpty(int[] array, int position) {
        return array[position] == 0;
    }

    public static boolean checkForDraw(int[] array) {
        for (int value : array) {
            if (value == 0) {
                return false;
            }
        }
        System.out.println("Sorry, DRAW!");
        return true;
    }
}


