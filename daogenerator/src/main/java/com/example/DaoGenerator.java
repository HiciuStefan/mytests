package com.example;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoGenerator {
    private static final String PROJECT_DIR = System.getProperty("user.dir").replace("\\", "/");
    private static final String OUT_DIR = PROJECT_DIR + "/app/src/main/java";

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.example.hiciu.apptesting.DaoGenerated");

        addTables(schema);

        new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema, OUT_DIR);
    }

    private static void addTables(Schema schema) {
        createTable(schema);
    }

    public static Entity createTable(Schema schema) {
        Entity createListingEntity = schema.addEntity("loggedInEntity");
        createListingEntity.addIdProperty().primaryKey().autoincrement();
        createListingEntity.addBooleanProperty("loggedIn");
        return createListingEntity;
    }
}
