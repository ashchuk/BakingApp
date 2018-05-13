package com.ashchuk.bakingapp.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(
        authority = BakingAppContentProvider.AUTHORITY,
        database = BakingAppDatabase.class
)
public class BakingAppContentProvider {
    static final String AUTHORITY = "com.ashchuk.bakingapp.data.provider";

    @TableEndpoint(table = BakingAppDatabase.TABLE_NAME)
    public static class BakingIngredients {

        @ContentUri(
                path = "recipes",
                type = "vnd.android.cursor.dir/recipes")
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/recipes");
    }
}
