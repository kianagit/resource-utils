package de.chloedev.resourceutils;

public class ResourceUtilsClient {

    private static ResourceUtilsClient INSTANCE;

    public void preInit() {

    }

    public void init() {
    }

    public static ResourceUtilsClient getInstance() {
        if (INSTANCE == null) INSTANCE = new ResourceUtilsClient();
        return INSTANCE;
    }
}
