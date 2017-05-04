/**
 * Created by GozdeDogan on 30.04.2017.
 */
import java.util.*;

public class BinaryNavMap<K extends Comparable<K>,V> extends AbstractMap<K ,V> implements NavigableMap<K,V>, Cloneable, java.io.Serializable {
    /**
     * BinarySearchTree nedeni ile comparable Entry'e ihtiyac duydugum icin implement ettim
     * @param <K>
     * @param <V>
     */
    protected static class Entry <K, V> implements Comparable<Entry<K, V>>, Map.Entry<K,V> {
        private K key;
        private V value;

        /**
         * Creates a new key-value pair
         * @param key   The key
         * @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key.
         * @return The key
         */
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value.
         * @return The value
         */
        public V getValue() {
            return value;
        }

        /**
         * Sets the value.
         * @param val The new value
         * @return The old value
         */
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

        /**
         * Entry'nin kendisini return eden metot.
         * @return
         */
        public Map.Entry<K, V> getData() {
            return this;
        }

        /**
         * entry'i stringe cevirir
         * @return
         */
        public String toString(){
            return "(" + key.toString() + ", " + value.toString() + ")";
        }
        /**
         * comparable'in zorladigi ama ihtiyac duymadigim icin implement etmedigim metot
         * @param o
         * @return
         */
        public int compareTo(Entry<K, V> o) {
            return key.hashCode() - o.key.hashCode();
        }
    }

    /**
     * attribute, Entry (key,value) tutar
     */
    private BinarySearchTree<Entry<K, V>> BNM;
    /**
     * tree de bulunan eleman sayisi
     */
    int numOfElements;

    /**
     * No-parameter constructor
     */
    public BinaryNavMap() {
        this.BNM = new BinarySearchTree<Entry<K, V>>();
        numOfElements = 0;
    }

    /**
     * BNM private bir deger oldugu icin disardan direk ulasilamaz!
     * @return
     */
    public BinarySearchTree<Entry<K, V>> getTree(){
        return BNM;
    }

    /**
     * eleman sayisini return eder.
     * @return
     */
    public int size(){
        return numOfElements;
    }

    /**
     * ??????????
     * @return
     */
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    /**
     * Returns a key-value mapping associated with the greatest key
     * strictly less than the given key, or {@code null} if there is
     * no such key.
     *
     * @param key the key
     * @return an entry with the greatest key less than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */

    public Map.Entry<K, V> lowerEntry(K key) {
        if(key != null)
            return lowerEntry(BNM.root, key, null);
        else
            return null;
    }

    /**
     *
     * @param lRoot
     * @param key
     * @param lower
     * @return
     */
    private Map.Entry<K, V> lowerEntry(BinarySearchTree.Node<Entry<K, V>> lRoot, K key, Entry<K, V> lower){
        if(lRoot == null)
            return null;

        if(lRoot.data.key.compareTo(key)==0) {
            if(lRoot.left == null)
                return lower;
            return findLower(lRoot.left, key, lower);
        }
        else if (lRoot.data.key.compareTo(key)<0)
            return lowerEntry(lRoot.left, key, new Entry<K, V>(lRoot.data.key, lRoot.data.value));
        else
            return lowerEntry(lRoot.right, key, new Entry<K, V>(lRoot.data.key, lRoot.data.value));
    }

    /**
     * BinarySearchTree de dolastigim icin Node yapisini kullaniyorum.
     * Node yapisi mainde kullanilamaz.
     * @param lRoot
     * @param key
     * @param lower
     * @return
     */
    private Map.Entry<K, V> findLower(BinarySearchTree.Node<Entry<K, V>> lRoot, K key, Entry<K, V> lower){
        if(lRoot.left == null)
            return new Entry<K, V>(lRoot.left.data.key, lRoot.left.data.value);
        else
            return findLower(lRoot.left.left, key, lower);
    }
    /**
     * Returns the greatest key strictly less than the given key, or
     * {@code null} if there is no such key.
     *
     * @param key the key
     * @return the greatest key less than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */

    public K lowerKey(K key) {
        if(key != null)
            return lowerEntry(key).getKey();
        return null;
    }

    /**
     * Returns a key-value mapping associated with the greatest key
     * less than or equal to the given key, or {@code null} if there
     * is no such key.
     *
     * @param key the key
     * @return an entry with the greatest key less than or equal to
     * {@code key}, or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */

    public Map.Entry<K, V> floorEntry(K key) {
        if(key != null)
            return floorEntry(BNM.root, key, null);
        return null;
    }

    /**
     * BinarySearchTree de dolastigim icin Node yapisini kullaniyorum.
     * Node yapisi mainde kullanilamaz.
     * @param lRoot
     * @param key
     * @param floor
     * @return
     */
    private Map.Entry<K, V> floorEntry(BinarySearchTree.Node<Entry<K, V>> lRoot, K key, Entry<K, V> floor){
        if(lRoot == null)
            return null;

        if(lRoot.data.key.compareTo(key)==0) {
            return floor;

        }else if (lRoot.data.key.compareTo(key)<0)
            return lowerEntry(lRoot.left, key, new Entry<K, V>(lRoot.data.key, lRoot.data.value));
        else
            return lowerEntry(lRoot.right, key, new Entry<K, V>(lRoot.data.key, lRoot.data.value));
    }
    /**
     * Returns the greatest key less than or equal to the given key,
     * or {@code null} if there is no such key.
     *
     * @param key the key
     * @return the greatest key less than or equal to {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */

    public K floorKey(K key) {
        if(key != null)
            return floorEntry(key).getKey();
        return null;
    }

    /**
     * Returns a key-value mapping associated with the least key
     * greater than or equal to the given key, or {@code null} if
     * there is no such key.
     *
     * @param key the key
     * @return an entry with the least key greater than or equal to
     * {@code key}, or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */

    public Map.Entry<K, V> ceilingEntry(K key) {
        if(key != null)
            return ceilingEntry(BNM.root, key, null);
        return null;
    }

    /**
     * BinarySearchTree de dolastigim icin Node yapisini kullaniyorum.
     * Node yapisi mainde kullanilamaz.
     * @param lRoot
     * @param key
     * @param lower
     * @return
     */
    private Map.Entry<K, V> ceilingEntry(BinarySearchTree.Node<Entry<K, V>> lRoot, K key, Map.Entry<K, V> lower){
        if(lRoot == null)
            return null;

        if(lRoot.data.key.compareTo(key)==0) {
            if(lRoot.right == null)
                return lower;
            return findHigher(lRoot.right, key, lower);
        }
        else if (lRoot.data.key.compareTo(key)<0)
            return ceilingEntry(lRoot.left, key, new Entry<K, V>(lRoot.data.key, lRoot.data.value));
        else
            return ceilingEntry(lRoot.right, key, new Entry<K, V>(lRoot.data.key, lRoot.data.value));
    }

    /**
     * BinarySearchTree de dolastigim icin Node yapisini kullaniyorum.
     * Node yapisi mainde kullanilamaz.
     * ceilingEntry'e yardimci olur.
     * Girilen elemanin buyugunu return eder
     * @param lRoot
     * @param key
     * @param lower
     * @return
     */
    private Map.Entry<K, V> findHigher(BinarySearchTree.Node<Entry<K, V>> lRoot, K key, Map.Entry<K, V> lower){
        if(lRoot.left == null)
            return new Entry<K, V>(lRoot.right.data.key, lRoot.right.data.value);
        else
            return findHigher(lRoot.right.right, key, lower);
    }

    /**
     * Returns the least key greater than or equal to the given key,
     * or {@code null} if there is no such key.
     *
     * @param key the key
     * @return the least key greater than or equal to {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */

    public K ceilingKey(K key) {
        if(key != null)
            return ceilingEntry(key).getKey();
        return null;
    }

    /**
     * Returns a key-value mapping associated with the least key
     * strictly greater than the given key, or {@code null} if there
     * is no such key.
     *
     * @param key the key
     * @return an entry with the least key greater than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */

    public Map.Entry<K, V> higherEntry(K key) {
        if(key != null)
            return higherEntry(BNM.root, key, null);
        return null;
    }

    /**
     * BinarySearchTree de dolastigim icin Node yapisini kullaniyorum.
     * Node yapisi mainde kullanilamaz.
     * @param lRoot
     * @param key
     * @param floor
     * @return
     */
    private Map.Entry<K, V> higherEntry(BinarySearchTree.Node<Entry<K, V>> lRoot, K key, Entry<K, V> floor){
        if(lRoot == null)
            return null;

        if(lRoot.data.key.compareTo(key)==0)
            return floor;
        else if (lRoot.data.key.compareTo(key)<0)
            return higherEntry(lRoot.left, key, new Entry<K, V>(lRoot.data.key, lRoot.data.value));
        else
            return higherEntry(lRoot.right, key, new Entry<K, V>(lRoot.data.key, lRoot.data.value));
    }

    /**
     * Returns the least key strictly greater than the given key, or
     * {@code null} if there is no such key.
     *
     * @param key the key
     * @return the least key greater than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */

    public K higherKey(K key) {
        if(key != null)
            return higherEntry(key).getKey();
        return null;
    }

    /**
     * Returns a key-value mapping associated with the least
     * key in this map, or {@code null} if the map is empty.
     *
     * @return an entry with the least key,
     * or {@code null} if this map is empty
     */

    public Map.Entry<K, V> firstEntry() {
        return firstEntry(BNM.root);
    }

    /**
     * BinarySearchTree de dolastigim icin Node yapisini kullaniyorum.
     * Node yapisi mainde kullanilamaz.
     * @param lRoot
     * @return
     */
    private Map.Entry<K, V> firstEntry(BinarySearchTree.Node<Entry<K, V>> lRoot){
        if(lRoot == null)
            return null;

        if(lRoot.left == null) {
            return new Entry<K, V>(lRoot.data.key, lRoot.data.value);
        }
        else
            return firstEntry(lRoot.left);
    }

    /**
     * Returns a key-value mapping associated with the greatest
     * key in this map, or {@code null} if the map is empty.
     *
     * @return an entry with the greatest key,
     * or {@code null} if this map is empty
     */

    public Map.Entry<K, V> lastEntry() {
        return lastEntry(BNM.root);
    }

    /**
     * BinarySearchTree de dolastigim icin Node yapisini kullaniyorum.
     * Node yapisi mainde kullanilamaz.
     * @param lRoot
     * @return
     */
    private Map.Entry<K, V> lastEntry(BinarySearchTree.Node<Entry<K, V>> lRoot){
        if(lRoot == null)
            return null;

        if(lRoot.right == null)
            return new Entry<K, V>(lRoot.data.key, lRoot.data.value);
        return lastEntry(lRoot.right);
    }

    /**
     * Removes and returns a key-value mapping associated with
     * the least key in this map, or {@code null} if the map is empty.
     *
     * @return the removed first entry of this map,
     * or {@code null} if this map is empty
     */

    public Map.Entry<K, V> pollFirstEntry() {
        Entry<K, V> deletedElement = (Entry<K, V>)firstEntry();
        BNM.delete(deletedElement);
        return deletedElement;
    }

    /**
     * Removes and returns a key-value mapping associated with
     * the greatest key in this map, or {@code null} if the map is empty.
     *
     * @return the removed last entry of this map,
     * or {@code null} if this map is empty
     */
    public Map.Entry<K, V> pollLastEntry() {
        Entry<K, V> deletedElement = (Entry<K, V>)lastEntry();
        BNM.delete(deletedElement);
        return deletedElement;
    }

    /**
     * Returns a reverse order view of the mappings contained in this map.
     * The descending map is backed by this map, so changes to the map are
     * reflected in the descending map, and vice-versa.  If either map is
     * modified while an iteration over a collection view of either map
     * is in progress (except through the iterator's own {@code remove}
     * operation), the results of the iteration are undefined.
     * <p>
     * <p>The returned map has an ordering equivalent to
     * <tt>{@link Collections#reverseOrder(Comparator) Collections.reverseOrder}(comparator())</tt>.
     * The expression {@code m.descendingMap().descendingMap()} returns a
     * view of {@code m} essentially equivalent to {@code m}.
     *
     * @return a reverse order view of this map
     */
    public NavigableMap<K, V> descendingMap() {
        BinaryNavMap<K, V> descending = new BinaryNavMap<K, V>();

        Iterator<Entry<K, V>> iter = BNM.iterator();
        while(iter.hasNext()){
            Entry<K, V> entry = iter.next();
            descending.put(entry.key, entry.value);
        }
        return descending;
    }

    /**
     * override edilen put metodu,
     * BinarySearchTree'ye uayarlanarak tekrar yazildi
     * @param key
     * @param value
     * @return
     */
    @Override
    public V put(K key, V value){
        if(key != null && value != null)
            return put(BNM.root, key, value);
        return null;
    }

    /**
     * BinarySearchTree de dolastigim icin Node yapisini kullaniyorum.
     * Node yapisi mainde kullanilamaz.
     * @param lRoot
     * @param key
     * @param value
     * @return
     */
    private V put(BinarySearchTree.Node<Entry<K, V>> lRoot , K key, V value){
        if(lRoot == null){
            BNM.add(new Entry<K, V>(key, value));
            numOfElements++;
            return null;
        }

        int comp = key.compareTo(lRoot.data.key);
        if(comp == 0){
            lRoot.data.value = value;
            return lRoot.data.value;
        }
        else if (comp < 0)
            return put(lRoot.left, key, value);
        else
            return put(lRoot.right, key, value);
    }

    /**
     * override edilen remove metodu.
     * BinarySearchTree'ye uyarlandi.
     * @param key
     * @return
     */
    @Override
    public V remove(Object key) {
        if(key != null)
            return remove(BNM.root, (K) key);
        else
            return null;
    }

    /**
     * BinarySearchTree de dolastigim icin Node yapisini kullaniyorum.
     * Node yapisi mainde kullanilamaz.
     * @param lRoot
     * @param key
     * @return
     */
    private V remove(BinarySearchTree.Node<Entry<K, V>> lRoot , K key){
        if(lRoot == null)
            return null;

        int comp = key.compareTo(lRoot.data.key);
        if(comp == 0){
            V old = lRoot.data.value;
            BNM.delete(new Entry<K, V>(lRoot.data.key, lRoot.data.value));
            numOfElements--;
            return old;
        }
        else if (comp < 0)
            return remove(lRoot.left, key);
        else
            return remove(lRoot.right, key);
    }


    /**
     * override edilen get metodu.
     * BinarySearchTree'ye uyarlandi.
     * @param key
     * @return
     */
    @Override
    public V get(Object key) {
        if(key != null)
            return get(BNM.root, (K) key);
        else
            return null;
    }

    /**
     * BinarySearchTree de dolastigim icin Node yapisini kullaniyorum.
     * Node yapisi mainde kullanilamaz.
     * @param lRoot
     * @param key
     * @return
     */
    private V get(BinarySearchTree.Node<Entry<K, V>> lRoot , K key){
        if(lRoot == null)
            return null;

        int comp = key.compareTo(lRoot.data.key);
        if(comp == 0)
            return lRoot.data.value;
        else if (comp < 0)
            return remove(lRoot.left, key);
        else
            return remove(lRoot.right, key);
    }

    /**
     * Returns a {@link NavigableSet} view of the keys contained in this map.
     * The set's iterator returns the keys in ascending order.
     * The set is backed by the map, so changes to the map are reflected in
     * the set, and vice-versa.  If the map is modified while an iteration
     * over the set is in progress (except through the iterator's own {@code
     * remove} operation), the results of the iteration are undefined.  The
     * set supports element removal, which removes the corresponding mapping
     * from the map, via the {@code Iterator.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retainAll}, and {@code clear} operations.
     * It does not support the {@code add} or {@code addAll} operations.
     *
     * @return a navigable set view of the keys in this map
     */
    public NavigableSet<K> navigableKeySet() {
        NavigableSet<K> navSet = new TreeSet<K>();

        Iterator<Entry<K, V>> iter = BNM.iterator();
        while(iter.hasNext()){
            Entry<K, V> entry = iter.next();
            navSet.add(entry.key);
        }

        return navSet;
    }

    /**
     * Returns a reverse order {@link NavigableSet} view of the keys contained in this map.
     * The set's iterator returns the keys in descending order.
     * The set is backed by the map, so changes to the map are reflected in
     * the set, and vice-versa.  If the map is modified while an iteration
     * over the set is in progress (except through the iterator's own {@code
     * remove} operation), the results of the iteration are undefined.  The
     * set supports element removal, which removes the corresponding mapping
     * from the map, via the {@code Iterator.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retainAll}, and {@code clear} operations.
     * It does not support the {@code add} or {@code addAll} operations.
     *
     * @return a reverse order navigable set view of the keys in this map
     */
    public NavigableSet<K> descendingKeySet() {
        NavigableMap<K, V> map = descendingMap();
        BinarySearchTree<Entry<K, V>> tree= ((BinaryNavMap)map).getTree();
        NavigableSet<K> navSet = new TreeSet<K>();

        Iterator<Entry<K, V>> iter = tree.iterator();
        while(iter.hasNext()){
            Entry<K, V> entry = iter.next();
            navSet.add(entry.key);
        }

        return navSet;
    }

    /**
     * Returns a view of the portion of this map whose keys range from
     * {@code fromKey} to {@code toKey}.  If {@code fromKey} and
     * {@code toKey} are equal, the returned map is empty unless
     * {@code fromInclusive} and {@code toInclusive} are both true.  The
     * returned map is backed by this map, so changes in the returned map are
     * reflected in this map, and vice-versa.  The returned map supports all
     * optional map operations that this map supports.
     * <p>
     * <p>The returned map will throw an {@code IllegalArgumentException}
     * on an attempt to insert a key outside of its range, or to construct a
     * submap either of whose endpoints lie outside its range.
     *
     * @param fromKey       low endpoint of the keys in the returned map
     * @param fromInclusive {@code true} if the low endpoint
     *                      is to be included in the returned view
     * @param toKey         high endpoint of the keys in the returned map
     * @param toInclusive   {@code true} if the high endpoint
     *                      is to be included in the returned view
     * @return a view of the portion of this map whose keys range from
     * {@code fromKey} to {@code toKey}
     * @throws ClassCastException       if {@code fromKey} and {@code toKey}
     *                                  cannot be compared to one another using this map's comparator
     *                                  (or, if the map has no comparator, using natural ordering).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code fromKey} or {@code toKey}
     *                                  cannot be compared to keys currently in the map.
     * @throws NullPointerException     if {@code fromKey} or {@code toKey}
     *                                  is null and this map does not permit null keys
     * @throws IllegalArgumentException if {@code fromKey} is greater than
     *                                  {@code toKey}; or if this map itself has a restricted
     *                                  range, and {@code fromKey} or {@code toKey} lies
     *                                  outside the bounds of the range
     */
    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        BinaryNavMap<K, V> sub = new BinaryNavMap<K, V>();
        if(fromKey != null && toKey != null) {
            boolean okey = false;
            Iterator<Entry<K, V>> iter = BNM.iterator();
            while (iter.hasNext()) {
                Entry<K, V> entry = iter.next();

                if (okey == true)
                    sub.put(entry.key, entry.value);

                if (entry.key.equals(fromKey)) {
                    okey = true;
                    if (fromInclusive == true)
                        sub.put(entry.key, entry.value);
                }

                if (entry.key.equals(toKey)) {
                    okey = false;
                    if (toInclusive == true)
                        sub.put(entry.key, entry.value);
                }
            }
        }
        return sub;
    }

    /**
     * Returns a view of the portion of this map whose keys are less than (or
     * equal to, if {@code inclusive} is true) {@code toKey}.  The returned
     * map is backed by this map, so changes in the returned map are reflected
     * in this map, and vice-versa.  The returned map supports all optional
     * map operations that this map supports.
     * <p>
     * <p>The returned map will throw an {@code IllegalArgumentException}
     * on an attempt to insert a key outside its range.
     *
     * @param toKey     high endpoint of the keys in the returned map
     * @param inclusive {@code true} if the high endpoint
     *                  is to be included in the returned view
     * @return a view of the portion of this map whose keys are less than
     * (or equal to, if {@code inclusive} is true) {@code toKey}
     * @throws ClassCastException       if {@code toKey} is not compatible
     *                                  with this map's comparator (or, if the map has no comparator,
     *                                  if {@code toKey} does not implement {@link Comparable}).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code toKey} cannot be compared to keys
     *                                  currently in the map.
     * @throws NullPointerException     if {@code toKey} is null
     *                                  and this map does not permit null keys
     * @throws IllegalArgumentException if this map itself has a
     *                                  restricted range, and {@code toKey} lies outside the
     *                                  bounds of the range
     */
    public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
        BinaryNavMap<K, V> sub = new BinaryNavMap<K, V>();
        if(toKey != null) {
            boolean okey = true;
            Iterator<Entry<K, V>> iter = BNM.iterator();
            while (iter.hasNext()) {
                Entry<K, V> entry = iter.next();

                if (entry.key.equals(toKey)) {
                    okey = false;
                    if (inclusive == true)
                        sub.put(entry.key, entry.value);
                }

                if (okey == true)
                    sub.put(entry.key, entry.value);
            }
        }
        return sub;
    }

    /**
     * Returns a view of the portion of this map whose keys are greater than (or
     * equal to, if {@code inclusive} is true) {@code fromKey}.  The returned
     * map is backed by this map, so changes in the returned map are reflected
     * in this map, and vice-versa.  The returned map supports all optional
     * map operations that this map supports.
     * <p>
     * <p>The returned map will throw an {@code IllegalArgumentException}
     * on an attempt to insert a key outside its range.
     *
     * @param fromKey   low endpoint of the keys in the returned map
     * @param inclusive {@code true} if the low endpoint
     *                  is to be included in the returned view
     * @return a view of the portion of this map whose keys are greater than
     * (or equal to, if {@code inclusive} is true) {@code fromKey}
     * @throws ClassCastException       if {@code fromKey} is not compatible
     *                                  with this map's comparator (or, if the map has no comparator,
     *                                  if {@code fromKey} does not implement {@link Comparable}).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code fromKey} cannot be compared to keys
     *                                  currently in the map.
     * @throws NullPointerException     if {@code fromKey} is null
     *                                  and this map does not permit null keys
     * @throws IllegalArgumentException if this map itself has a
     *                                  restricted range, and {@code fromKey} lies outside the
     *                                  bounds of the range
     */
    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
        BinaryNavMap<K, V> sub = new BinaryNavMap<K, V>();
        if(fromKey != null) {
            boolean okey = false;
            Iterator<Entry<K, V>> iter = BNM.iterator();
            while (iter.hasNext()) {
                Entry<K, V> entry = iter.next();

                if (okey == true)
                    sub.put(entry.key, entry.value);

                if (entry.key.equals(fromKey)) {
                    okey = true;
                    if (inclusive == true)
                        sub.put(entry.key, entry.value);
                }
            }
        }
        return sub;
    }

    /**
     * Returns the comparator used to order the keys in this map, or
     * {@code null} if this map uses the {@linkplain Comparable
     * natural ordering} of its keys.
     *
     * @return the comparator used to order the keys in this map,
     * or {@code null} if this map uses the natural ordering
     * of its keys
     */
    public Comparator<? super K> comparator() {
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>Equivalent to {@code subMap(fromKey, true, toKey, false)}.
     *
     * @param fromKey
     * @param toKey
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        BinaryNavMap<K, V> sub = new BinaryNavMap<K, V>();
        if(fromKey != null && toKey != null) {
            boolean okey = false;
            Iterator<Entry<K, V>> iter = BNM.iterator();
            while (iter.hasNext()) {
                Entry<K, V> entry = iter.next();

                if (okey == true)
                    sub.put(entry.key, entry.value);

                if (entry.key.equals(fromKey))
                    okey = true;

                if (entry.key.equals(toKey))
                    okey = false;
            }
        }
        return sub;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>Equivalent to {@code headMap(toKey, false)}.
     *
     * @param toKey
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    public SortedMap<K, V> headMap(K toKey) {
        BinaryNavMap<K, V> sub = new BinaryNavMap<K, V>();
        if(toKey != null) {
            boolean okey = true;
            Iterator<Entry<K, V>> iter = BNM.iterator();
            while (iter.hasNext()) {
                Entry<K, V> entry = iter.next();

                if (entry.key.equals(toKey))
                    okey = false;

                if (okey == true)
                    sub.put(entry.key, entry.value);
            }
        }
        return sub;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>Equivalent to {@code tailMap(fromKey, true)}.
     *
     * @param fromKey
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    public SortedMap<K, V> tailMap(K fromKey) {
        BinaryNavMap<K, V> sub = new BinaryNavMap<K, V>();
        if(fromKey != null) {
            boolean okey = false;
            Iterator<Entry<K, V>> iter = BNM.iterator();
            while (iter.hasNext()) {
                Entry<K, V> entry = iter.next();

                if (okey == true)
                    sub.put(entry.key, entry.value);

                if (entry.key.equals(fromKey))
                    okey = true;
            }
        }
        return sub;
    }

    /**
     * Returns the first (lowest) key currently in this map.
     *
     * @return the first (lowest) key currently in this map
     * @throws NoSuchElementException if this map is empty
     */
    public K firstKey() {
        return firstEntry().getKey();
    }

    /**
     * Returns the last (highest) key currently in this map.
     *
     * @return the last (highest) key currently in this map
     * @throws NoSuchElementException if this map is empty
     */
    public K lastKey() {
        return lastEntry().getKey();
    }

    /**
     * BinaryNavMap'i stringe cevirir.
     * BinarySearchTree'nin toString metodunu kullanir.
     * @return
     */
    public String toString(){
        return BNM.toString();
    }
}
