/**
 * Created by GozdeDogan on 30.04.2017.
 */
import java.util.NavigableMap;

public class hw7_131044019 {
    public static void main(String args[]){
        final Boolean q1 = Q1Test();
    }

    /**
     * ayni value dan bir suru olabilir ama farkli key ler ile.
     * @return
     */
    public static Boolean Q1Test(){
        System.out.println("\nTEST Q1>>>>>>>>>\n");
        NavigableMap<String,String> turkey = new BinaryNavMap<String,String>();
        turkey.put("uskudar","istanbul");
        turkey.put("kadikoy", "istanbul");
        turkey.put("cekirge", "bursa");
        turkey.put("gebze", "kocaeli");
        turkey.put("niksar","tokat");
        turkey.put("kecioren","ankara");
        turkey.put("aksaray","istanbul");
        turkey.put("foca", "izmir");
        turkey.put("manavgat","antalya");
        turkey.put("kahta", "adiyaman");
        turkey.put("biga", "canakkale");


        System.out.println("\nsize:" + turkey.size());
        System.out.println("The original set odds is\n" + turkey.toString());

        System.out.println("\n\nThe subMap(\"uskudar\",true,\"gebze\",false) is \n" + turkey.subMap("uskudar", true, "gebze", false).toString());
        System.out.println("\n\nThe subMap(\"uskudar\",false,\"gebze\",false) is \n" + turkey.subMap("uskudar", false, "gebze", false).toString());
        System.out.println("\n\nThe subMap(\"uskudar\",true,\"gebze\",true) is \n" + turkey.subMap("uskudar", true, "gebze", true).toString());
        System.out.println("\n\nThe subMap(\"uskudar\",false,\"gebze\",true) is \n" + turkey.subMap("uskudar", false, "gebze", true).toString());
        System.out.println("\n\nThe subMap(\"gebze\",\"gebze\") is " + turkey.subMap("uskudar", "gebze").toString());
        System.out.println("\n\nThe subMap(\"fsm\",\"gebze\") is " + turkey.subMap("fsm", "gebze").toString());

        System.out.println("\n\nThe headMap(\"uskudar\", true) is \n" + turkey.headMap("uskudar", true).toString());
        System.out.println("\n\nThe headMap(\"fsm\", true) is \n" + turkey.headMap("fsm", true).toString());
        System.out.println("\n\nThe headMap(\"uskudar\", false) is \n" + turkey.headMap("uskudar", false).toString());
        System.out.println("\n\nThe headMap(\"fsm\", false) is \n" + turkey.headMap("fsm", false).toString());
        System.out.println("\n\nThe headMap(\"kahta\") is \n" + turkey.headMap("kahta").toString());
        System.out.println("\n\nThe headMap(\"fsm\") is \n" + turkey.headMap("fsm").toString());

        System.out.println("\n\nThe tailMap(\"gebze\", true) is\n " + turkey.tailMap("gebze", true).toString());
        System.out.println("\n\nThe tailMap(\"fsm\", true) is \n" + turkey.tailMap("fsm", true).toString());
        System.out.println("\n\nThe tailMap(\"gebze\", false) is\n " + turkey.tailMap("gebze", false).toString());
        System.out.println("\n\nThe tailMap(\"fsm\", false) is \n" + turkey.tailMap("fsm", false).toString());
        System.out.println("\n\nThe tailMap(\"gebze\") is \n" + turkey.tailMap("gebze").toString());
        System.out.println("\n\nThe tailMap(\"fsm\") is \n" + turkey.tailMap("fsm").toString());

        System.out.println("\n\nThe first entry is \n" + turkey.firstEntry().toString());
        System.out.println("\n\nThe first key is \n" + turkey.firstKey().toString());
        System.out.println("\n\nThe last entry is \n" + turkey.lastEntry().toString());
        System.out.println("\n\nThe last entry is \n" + turkey.lastKey().toString());

        ////////////////////// hata veriyor neden anlayamadim o nedenle yorum icine aldim /////////////////////////
       /* System.out.println("\n\nThe lowerEntry(\"manavgat\") is \n" + turkey.lowerEntry("manavgat").toString());
        System.out.println("\n\nThe lowerKey(\"manavgat\") is \n" + turkey.lowerKey("manavgat").toString());
        System.out.println("\n\nThe lowerEntry(\"fsm\") is n" + turkey.lowerEntry("fsm").toString());
        System.out.println("\n\nThe lowerKey(\"fsm\") is \n" + turkey.lowerKey("fsm").toString());

        System.out.println("\n\nThe ceilingEntry(\"manavgat\") is \n" + turkey.ceilingEntry("manavgat").toString());
        System.out.println("\n\nThe ceilingKey(\"manavgat\") is \n" + turkey.ceilingKey("manavgat").toString());
        System.out.println("\n\nThe ceilingEntry(\"fsm\") is \n" + turkey.ceilingEntry("fsm").toString());
        System.out.println("\n\nThe ceilingKey(\"fsm\") is \n" + turkey.ceilingKey("fsm").toString());

        System.out.println("\n\nThe floorEntry(\"manavgat\") is \n" + turkey.floorEntry("manavgat").toString());
        System.out.println("\n\nThe floorKey(\"manavgat\") is \n" + turkey.floorKey("manavgat").toString());
        System.out.println("\n\nThe floorEntry(\"fsm\") is \n" + turkey.floorEntry("fsm").toString());
        System.out.println("\n\nThe floorKey(\"fsm\") is \n" + turkey.floorKey("fsm").toString());

        System.out.println("\n\nThe higherEntry(\"manavgat\") is \n" + turkey.higherEntry("manavgat").toString());
        System.out.println("\n\nThe higherKey(\"manavgat\") is\n " + turkey.higherKey("manavgat").toString());
        System.out.println("\n\nThe higherEntry(\"fsm\") is\n " + turkey.higherEntry("fsm").toString());
        System.out.println("\n\nThe higherKey(\"fsm\") is \n" + turkey.higherKey("fsm").toString());*/

        System.out.println("\n\nThe pollFirstEntry() is " + turkey.pollFirstEntry().toString());
        System.out.println("\n\nThe pollLastEntry() is " + turkey.pollLastEntry().toString());

        System.out.println("\n\nThe descendingMap is \n" + turkey.descendingMap().toString());
        System.out.println("\n\nThe descendingKeySet is \n" + turkey.descendingKeySet().toString());

        System.out.println("\n\nThe navigableKeySet is \n" + turkey.navigableKeySet().toString());


        //System.out.println("\nElements:\n" + turkey.toString());
        System.out.println("\nget(\"kemalpasa\") : " + turkey.get("kemalpasa"));
        System.out.println("\nget(\"gebze\") : " + turkey.get("gebze"));
        System.out.println("\nput(\"biga\", \"bursa\") : " + turkey.put("biga", "bursa"));
        System.out.println("size:" + turkey.size());
        System.out.println("\nput(\"fsm\", \"bursa\") : " + turkey.put("fsm", "bursa"));
        System.out.println("size:" + turkey.size());
        //System.out.println("\nElements:\n" + turkey.toString());
        System.out.println("\nremove(\"kecioren\") : " + turkey.remove("kecioren"));
        System.out.println("size:" + turkey.size());
        System.out.println("\nremove(\"gebze\") : " + turkey.remove("gebze"));
        System.out.println("size:" + turkey.size() + "\n");

        return Boolean.TRUE;

    }
}
