package nomad.common.data_structure;

public class Skip extends Move {

    private boolean isSkipped;

    public Skip(boolean isSkipped) {
        this.isSkipped = isSkipped;
    }

    public boolean isSkipped() {
        return isSkipped;
    }

    public void setSkipped(boolean skipped) {
        isSkipped = skipped;
    }

    @Override
    public String toString() {
        return "Skip{" +
                "isSkipped=" + isSkipped +
                '}';
    }
}
