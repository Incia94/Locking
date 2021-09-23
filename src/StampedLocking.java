
import java.util.*;
import java.util.concurrent.locks.StampedLock;
public class StampedLocking {

    StampedLock lock = new StampedLock();

    //lock acquisition methods return a stamp that is used to release a lock
    // or to check if the lock is still valid
    public void write(Map map, String key, String value){
        long stamp = lock.writeLock();
        try {
            map.put(key, value);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public void read(Map map, String key)  {
        long stamp = lock.readLock();
        try {
            System.out.println("Money In Account -" +map.get("1"));
        } finally {
            lock.unlockRead(stamp);
        }
    }
    public static void main(String [] args) {

        Map<String, String> map = new HashMap<>();
        map.put("1","1000");
        map.put("2","2000");
        StampedLocking S = new StampedLocking();
        // read before performing write
        S.read(map,"1");
        // write
        S.write(map,"1","3000");
        // read after performing write to change 1000 to 3000
        S.read(map,"1");

    }
    }




