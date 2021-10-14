package priority.model;

import Enum.priority.ResourceType;

public class ResourcePriorityQueueElement {

    private String instanceId;
    private ResourceType resourceType;

    public ResourcePriorityQueueElement(String instanceId, ResourceType resourceType) {
        this.instanceId = instanceId;
        this.resourceType = resourceType;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }
}
