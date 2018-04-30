package com.ashchuk.bakingapp.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.ConflictResolutionType;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;
import net.simonvt.schematic.annotation.UniqueConstraint;

@UniqueConstraint(columns = {BakingAppContract.COLUMN_INGREDIENT_NAME}, onConflict = ConflictResolutionType.ABORT)
public class BakingAppContract {
    @DataType(DataType.Type.INTEGER)
    @PrimaryKey(onConflict = ConflictResolutionType.REPLACE)
    @AutoIncrement
    public static final String COLUMN_ID = "_id";

    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String COLUMN_INGREDIENT_NAME = "ingredientName";

    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String COLUMN_INGREDIENT_QUANTITY = "ingredientQuantity";
}
