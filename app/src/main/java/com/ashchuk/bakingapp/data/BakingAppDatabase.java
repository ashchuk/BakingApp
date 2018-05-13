package com.ashchuk.bakingapp.data;

import com.ashchuk.bakingapp.tools.Constants;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

@Database(fileName = Constants.DATABASE_NAME, version = BakingAppDatabase.VERSION)
class BakingAppDatabase {
    static final int VERSION = 1;

    @Table(BakingAppContract.class)
    static final String TABLE_NAME = "recipes";
}