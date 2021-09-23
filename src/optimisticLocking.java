
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.map.IMap;
public class optimisticLocking {

    public static void main(String [] args){

        // create a hazelcast instance
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        // Distributed Map called Transaction with key id and money as value
        IMap<String, Integer> map = hz.getMap( "Transaction" );
        String key = "1";
        map.put( key, 1000 );
        Integer old_amount = map.get(key);
        // here instead of obtaining a lock on the given key we can use replace function
        // to replace old value with new when condition is met.
        if(map.containsKey("1")) {
            Integer new_amount = old_amount +=50;
            map.replace( key, old_amount, new_amount);
        }
        System.out.println( "Final Amount = " + map.get( key ));
    }


    }

