package zw.ac.cut.soterio.sble.storage;

import java.lang.System;

@androidx.room.Database(entities = {zw.ac.cut.soterio.sble.storage.entities.EKey.class}, version = 1, exportSchema = false)
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\'\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lzw/ac/cut/soterio/sble/storage/DatabaseManager;", "Landroidx/room/RoomDatabase;", "()V", "Companion", "sble_debug"})
public abstract class DatabaseManager extends androidx.room.RoomDatabase {
    private static volatile zw.ac.cut.soterio.sble.storage.DatabaseManager INSTANCE;
    public static final zw.ac.cut.soterio.sble.storage.DatabaseManager.Companion Companion = null;
    
    public DatabaseManager() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lzw/ac/cut/soterio/sble/storage/DatabaseManager$Companion;", "", "()V", "INSTANCE", "Lzw/ac/cut/soterio/sble/storage/DatabaseManager;", "getDatabase", "context", "Landroid/content/Context;", "sble_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final zw.ac.cut.soterio.sble.storage.DatabaseManager getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}