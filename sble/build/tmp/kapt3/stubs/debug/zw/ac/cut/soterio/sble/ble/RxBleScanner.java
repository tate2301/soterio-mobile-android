package zw.ac.cut.soterio.sble.ble;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u00017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J \u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u0005H\u0003J(\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00132\u0006\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0013H\u0002J\u0016\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010-\u001a\u00020,H\u0002J\u0010\u0010.\u001a\u00020\u001f2\u0006\u0010/\u001a\u000200H\u0002J\n\u00101\u001a\u0004\u0018\u00010\rH\u0002J\b\u00102\u001a\u00020\u001fH\u0007J\u0006\u00103\u001a\u00020\u001fJ\u0010\u00104\u001a\u00020\u001f2\u0006\u00105\u001a\u000206H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00110\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n \u0016*\u0004\u0018\u00010\u00150\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\n \u0016*\u0004\u0018\u00010\u00180\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n \u0016*\u0004\u0018\u00010\u001d0\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00068"}, d2 = {"Lzw/ac/cut/soterio/sble/ble/RxBleScanner;", "", "context", "Landroid/content/Context;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;)V", "TAG", "", "appDatabase", "Landroidx/room/RoomDatabase;", "connections", "", "Lio/reactivex/disposables/Disposable;", "getContext", "()Landroid/content/Context;", "devices", "Lkotlin/Pair;", "Lcom/polidea/rxandroidble2/scan/ScanResult;", "", "monitorFilter", "Lcom/polidea/rxandroidble2/scan/ScanFilter;", "kotlin.jvm.PlatformType", "rxBleClient", "Lcom/polidea/rxandroidble2/RxBleClient;", "scanDisposable", "scanJob", "Lkotlinx/coroutines/Job;", "settings", "Lcom/polidea/rxandroidble2/scan/ScanSettings;", "connectToDevice", "", "scanResult", "txPowerAdvertised", "coroutineScope", "createOrUpdateContactEvent", "identifier", "", "rssi", "timestamp", "", "txPower", "negotiateMTU", "Lio/reactivex/Single;", "Lcom/polidea/rxandroidble2/RxBleConnection;", "connection", "onConnectionError", "e", "", "scan", "start", "stop", "storeEvent", "event", "Lzw/ac/cut/soterio/sble/ble/RxBleScanner$Event;", "Event", "sble_debug"})
public final class RxBleScanner {
    private final com.polidea.rxandroidble2.RxBleClient rxBleClient = null;
    private java.util.List<kotlin.Pair<com.polidea.rxandroidble2.scan.ScanResult, java.lang.Integer>> devices;
    private final java.util.List<io.reactivex.disposables.Disposable> connections = null;
    private androidx.room.RoomDatabase appDatabase;
    private final java.lang.String TAG = "RxBleScanner";
    private final com.polidea.rxandroidble2.scan.ScanFilter monitorFilter = null;
    private io.reactivex.disposables.Disposable scanDisposable;
    private kotlinx.coroutines.Job scanJob;
    private final com.polidea.rxandroidble2.scan.ScanSettings settings = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private final kotlinx.coroutines.CoroutineScope scope = null;
    
    @kotlinx.coroutines.InternalCoroutinesApi()
    public final void start() {
    }
    
    private final io.reactivex.disposables.Disposable scan() {
        return null;
    }
    
    public final void stop() {
    }
    
    @android.annotation.SuppressLint(value = {"CheckResult"})
    private final void connectToDevice(com.polidea.rxandroidble2.scan.ScanResult scanResult, int txPowerAdvertised, kotlinx.coroutines.CoroutineScope coroutineScope) {
    }
    
    private final io.reactivex.Single<com.polidea.rxandroidble2.RxBleConnection> negotiateMTU(com.polidea.rxandroidble2.RxBleConnection connection) {
        return null;
    }
    
    private final void onConnectionError(java.lang.Throwable e) {
    }
    
    private final void storeEvent(zw.ac.cut.soterio.sble.ble.RxBleScanner.Event event) {
    }
    
    private final void createOrUpdateContactEvent(byte[] identifier, int rssi, long timestamp, int txPower) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    public RxBleScanner(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope scope) {
        super();
    }
    
    @kotlin.Suppress(names = {"ArrayInDataClass"})
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\bH\u00c6\u0003J\t\u0010\u0019\u001a\u00020\nH\u00c6\u0003J;\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f\u00a8\u0006!"}, d2 = {"Lzw/ac/cut/soterio/sble/ble/RxBleScanner$Event;", "", "identifier", "", "rssi", "", "txPower", "scope", "Lkotlinx/coroutines/CoroutineScope;", "timestamp", "", "([BIILkotlinx/coroutines/CoroutineScope;J)V", "getIdentifier", "()[B", "getRssi", "()I", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "getTimestamp", "()J", "getTxPower", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "sble_debug"})
    static final class Event {
        @org.jetbrains.annotations.NotNull()
        private final byte[] identifier = null;
        private final int rssi = 0;
        private final int txPower = 0;
        @org.jetbrains.annotations.NotNull()
        private final kotlinx.coroutines.CoroutineScope scope = null;
        private final long timestamp = 0L;
        
        @org.jetbrains.annotations.NotNull()
        public final byte[] getIdentifier() {
            return null;
        }
        
        public final int getRssi() {
            return 0;
        }
        
        public final int getTxPower() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.coroutines.CoroutineScope getScope() {
            return null;
        }
        
        public final long getTimestamp() {
            return 0L;
        }
        
        public Event(@org.jetbrains.annotations.NotNull()
        byte[] identifier, int rssi, int txPower, @org.jetbrains.annotations.NotNull()
        kotlinx.coroutines.CoroutineScope scope, long timestamp) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final byte[] component1() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int component3() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.coroutines.CoroutineScope component4() {
            return null;
        }
        
        public final long component5() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final zw.ac.cut.soterio.sble.ble.RxBleScanner.Event copy(@org.jetbrains.annotations.NotNull()
        byte[] identifier, int rssi, int txPower, @org.jetbrains.annotations.NotNull()
        kotlinx.coroutines.CoroutineScope scope, long timestamp) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object p0) {
            return false;
        }
    }
}