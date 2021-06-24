package com.linkener.crudtest.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public abstract class Http {

        public abstract class Path {
            public static final String TESTENTITY = "/testentity";
        }
    }

    public abstract class Database {

        public abstract class Table {

            public abstract class TestEntity {
                public static final String NAME = "area";

                public abstract class Column {
                    public static final String ID = "id";
                    public static final String NAME = "name";
                    public static final String PARENT = "parent";
                }
            }
        }
    }
}
