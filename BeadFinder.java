import java.awt.*;
import java.util.ArrayList;
public class BeadFinder {
    private int beadcount;
    ArrayList<Blob> blobList = new ArrayList<Blob>();

    private  boolean[][] visited;


    public BeadFinder(Picture picture, double tau) {

        visited = new boolean[picture.height()][picture.width()];
        for (int i = 0; i < picture.height(); i++) {
            for (int j = 0; j < picture.width(); j++) {
                visited[i][j] = false;
            }
        }

        for (int i = 0; i < picture.height(); i++) {
            for (int j = 0; j < picture.width(); j++) {
                if (!visited[i][j] && Luminance.intensity(picture.get(j, i)) > tau) {
                    Blob newBlob = new Blob();

                    blobList.add(DFS(picture, tau, newBlob, i, j));

                }
                visited[i][j] = true;
            }
        }
    }


    private Blob DFS(Picture picture, double tau, Blob blob, int i, int j) {


        if(i>0 && j>0 && j+1< picture.width() && i+1 < picture.height()) {
            visited[i][j] = true;
            blob.add(j, i);
            if (!visited[i + 1][j] && Luminance.intensity(picture.get(j, i + 1)) > tau) {
                visited[i + 1][j] = true;
                blob = DFS(picture, tau, blob, i + 1, j);
            }

            if (!visited[i - 1][j] && Luminance.intensity(picture.get(j, i - 1)) > tau) {
                visited[i - 1][j] = true;
                blob = DFS(picture, tau, blob, i - 1, j);
            }

            if (!visited[i][j + 1] && Luminance.intensity(picture.get(j + 1, i)) > tau) {
                visited[i][j + 1] = true;
                blob = DFS(picture, tau, blob, i, j + 1);
            }

            if (!visited[i][j - 1] && Luminance.intensity(picture.get(j - 1, i)) > tau) {
                visited[i][j - 1] = true;
                blob = DFS(picture, tau, blob, i, j - 1);
            }
        }
        return blob;
    }

    public Blob[] getBeads(int min) {
        beadcount = 0;
        Blob[] temp =new Blob[blobList.size()];
        for (int i = 0; i < blobList.size(); i++) {
            Blob current = blobList.get(i);

            if (current.mass() >= min) {
                temp[beadcount] = current;
                beadcount += 1;
            }
        }

        Blob[] beadList = new Blob[beadcount];
        System.arraycopy(temp, 0, beadList, 0, beadcount);
        return beadList;
    }

    public static void main(String[] args) {
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        Picture picture = new Picture(args[2]);
        BeadFinder beadFinder = new BeadFinder(picture, tau);
        Blob[] beadList = beadFinder.getBeads(min);

        for (int i = 0; i < beadFinder.beadcount; i++) {
            System.out.println(beadList[i]);
        }
    }}
