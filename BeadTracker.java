
public class BeadTracker {
    public static void main(String[] args) {
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);

        for (int i = 0; i < args.length - 4; i++) {

            Picture nextPic = new Picture(args[i + 4]);
            Picture previousPic = new Picture(args[i + 3]);
            BeadFinder next = new BeadFinder(nextPic, tau);
            BeadFinder previous = new BeadFinder(previousPic, tau);
            Blob[] nextBeads = next.getBeads(min);
            Blob[] previousBeads = previous.getBeads(min);

            for (int j = 0; j < nextBeads.length; j++) {

                double minimum = Double.POSITIVE_INFINITY;
                int pointer = 0;
                for (int k = 0; k < previousBeads.length; k++) {
                    if (nextBeads[j].distanceTo(previousBeads[k]) < minimum && nextBeads[j].distanceTo(previousBeads[k]) < delta) {
                        minimum = nextBeads[j].distanceTo(previousBeads[k]);
                        pointer ++;
                    }
                }
                if (pointer != 0) {
                    System.out.printf("%.4f%n", minimum);
                }
            }
            System.out.println();
        }
    }
}
