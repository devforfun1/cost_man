package datastorage.db.model;

public class PriorityQueuePlaceModel {

    private int elementId;
    private int childId;
    private int parentId;

    public PriorityQueuePlaceModel(int elementId) {
        this.elementId = elementId;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getElementId() {
        return elementId;
    }
}
