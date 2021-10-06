package factory.priority;


import datastorage.db.PriorityService;
import datastorage.db.model.PriorityQueueModel;


public class Producer {


    public static PriorityFactory GetFactory() {

        PriorityQueueModel queueModel = new PriorityService().GetSelectedPriorityQueue();


        switch (queueModel.getType()) {
            case RESOURCE_IDS:
                return resourcePriorityFactory();
            case RESOURCE_GROUPS:
                return resourceGroupFactory();
            case COMBINED:
                return combinedFactory();
        }

        return null;
    }

    private static ResourcePriorityFactory resourcePriorityFactory() {
        ResourcePriorityFactory resourcePriorityFactory = new ResourcePriorityFactory();
        resourcePriorityFactory.InitFactory();

        return resourcePriorityFactory;
    }


    private static ResourceGroupFactory resourceGroupFactory() {
        ResourceGroupFactory resourceGroupFactory = new ResourceGroupFactory();
        resourceGroupFactory.InitFactory();

        return resourceGroupFactory;
    }

    private static CombinedFactory combinedFactory() {


        CombinedFactory combinedFactory = new CombinedFactory();

        combinedFactory.InitFactory();

        return combinedFactory;
    }


}
