import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import java.util.concurrent.locks.Lock;
import java.lang.Thread;


//While java.util.concurrent.ConcurrentMap extends this to support thread safety
// in a single JVM with multiple threads.
//Similarly, IMap extends the ConcurrentHashMap and provides an interface
// which makes the map thread safe across JVMs.

public class LockingCode  {

    public static void main(String args[]){

        // Start the Embedded Hazelcast Cluster Member
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        // created a distributed map Transaction of key as version ID and values as amount
        IMap<String, Integer> map = hz.getMap("Transaction");
        String key = "1";
        map.put( key, 1000 );
        if(map.containsKey("1")) {

            //pessimistic lock applied on the map to increase initial amount by 50
            map.lock( key );
            try {
                Integer new_amount = map.get( key );
                Thread.sleep(1000,1);
                new_amount+=50;
                map.put( key, new_amount );
            } catch (InterruptedException e){
                map.unlock(key);
                System.out.println(e);
            }
        }
        System.out.println( "Final Amount = " + map.get( key ));

        // perform a graceful shutdown
        hz.shutdown();
    }


    }
