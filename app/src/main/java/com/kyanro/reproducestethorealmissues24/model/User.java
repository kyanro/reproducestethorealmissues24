package com.kyanro.reproducestethorealmissues24.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    @PrimaryKey
    public long id = 0;
    public String name = null;
    public Integer age = null;
}
