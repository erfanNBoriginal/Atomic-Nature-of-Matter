public class Blob {
    private int mass;
    private double m_x;
    private double m_y;

    public Blob() {
        mass = 0;
        m_x = 0;
        m_y = 0;
    }

    public void add(int x, int y) {
        m_x = (m_x * mass + x) /(mass + 1);
        m_y = (m_y * mass + y) /(mass + 1);
        mass ++;
    }

    public int mass() {
        return mass;
    }

    public double distanceTo(Blob b) {
        double xDelta = m_x - b.m_x;
        double yDelta = m_y - b.m_y;
        return Math.sqrt(Math.pow(xDelta, 2.0) + Math.pow(yDelta, 2.0));
    }

    public String toString() {
        return String.format("%2d (%8.4f, %8.4f)", mass, m_x, m_y);
    }

    public static void main(String[] args) {
        Blob myBlob = new Blob();
        System.out.println(myBlob);
        int blobMass = myBlob.mass();
        System.out.println(blobMass);
        Blob thatBlob = new Blob();
        System.out.println(myBlob.distanceTo(thatBlob));
    }
}
