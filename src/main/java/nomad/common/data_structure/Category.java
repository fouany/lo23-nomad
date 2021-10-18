package nomad.common.data_structure;

import java.io.Serializable;

public class Category implements Serializable {

    private String name;
    private boolean checkInfos;
    private boolean rightToWatch;
    private boolean rightToParticipate;
    private boolean accessAllowed;

    public Category(String name, boolean checkInfos, boolean rightToWatch, boolean rightToParticipate, boolean accessAllowed) {
        this.name = name;
        this.checkInfos = checkInfos;
        this.rightToWatch = rightToWatch;
        this.rightToParticipate = rightToParticipate;
        this.accessAllowed = accessAllowed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheckInfos() {
        return checkInfos;
    }

    public void setCheckInfos(boolean checkInfos) {
        this.checkInfos = checkInfos;
    }

    public boolean isRightToWatch() {
        return rightToWatch;
    }

    public void setRightToWatch(boolean rightToWatch) {
        this.rightToWatch = rightToWatch;
    }

    public boolean isRightToParticipate() {
        return rightToParticipate;
    }

    public void setRightToParticipate(boolean rightToParticipate) {
        this.rightToParticipate = rightToParticipate;
    }

    public boolean isAccessAllowed() {
        return accessAllowed;
    }

    public void setAccessAllowed(boolean accessAllowed) {
        this.accessAllowed = accessAllowed;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", checkInfos=" + checkInfos +
                ", rightToWatch=" + rightToWatch +
                ", rightToParticipate=" + rightToParticipate +
                ", accessAllowed=" + accessAllowed +
                '}';
    }
}
