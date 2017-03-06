/**
 * @Anatasia on 2017/3/6
 * 功能：ArrayList源码学习
 * 来源：http://blog.csdn.net/jzhf2012/article/details/8540410
 */
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

import javax.tools.JavaCompiler;


public class ArrayList<E> extends AbstractList<E> implements List<E>, Cloneable, java.io.Serializable, RandomAccess{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//两个私有属性
	//transient关键字表明该属性不需要序列化
	private transient Object[] elementData;//存储ArrayList内的元素
	private int size;//ArrayList包含的元素数目，即ArrayList大小
	
	//三个构造方法
	public ArrayList(int initialCapacity){
		super();
		if(initialCapacity<0){
			throw new IllegalArgumentException("Illegal Capacity:"+initialCapacity);
		}
		this.elementData = new Object[initialCapacity];
	}
	
	public ArrayList(){
		this(10);
	}
	
	public ArrayList(Collection<? extends E> c){
		elementData = c.toArray();
		size = elementData.length;
		//c.toArray might (incorrectly) not return Object[];
		if(elementData.getClass() != Object[].class){
			elementData = Arrays.copyOf(elementData, size, Object[].class);
		}
	}
	
	//在尾部添加元素
	public boolean add(E e){
		ensureCapacity(size+1);
		elementData[size++] = e;
		return true;
	}
	
	//在指定位置插入元素
	public void add(int index, E e){
		if(index>size||index<0){
			throw new IndexOutOfBoundsException("Index:"+index+",size:"+size);
		}
		ensureCapacity(size+1);
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index] = e;
		size++;
	}
	
	public boolean addAll(Collection<? extends E> c) {
		Object[] a = c.toArray();
		int numNew = a.length;
		ensureCapacity(size+numNew);
		System.arraycopy(a, 0, elementData, size, numNew);
		size += numNew;
		return numNew != 0;
	}
	
	public boolean addAll(int index, Collection<? extends E> c){
		if(index>size||index<0){
			throw new IndexOutOfBoundsException("Index:"+index+",Size:"+size);
		}
		Object[] a = c.toArray();
		int numNew = a.length;
		ensureCapacity(size+numNew);
		int numMoved = size-index;
		if(numMoved > 0){
			System.arraycopy(elementData, index, elementData, index+numNew, numMoved);
		}
		
		System.arraycopy(a, 0, elementData, index, numNew);
		size += numNew;
		return numNew!=0;
	}
	
	public void clear() {
		modCount++;
		for(int i=0;i<size;i++){
			elementData[i]=null;
		}
		size = 0;
	}
	
	//返回此ArrayList实例的浅表副本
	public Object clone(){
		try {
			ArrayList<E> v = (ArrayList<E>)super.clone();
			v.elementData = Arrays.copyOf(elementData, size);
			v.modCount = 0;
			return v;
		} catch (CloneNotSupportedException e) {
			// TODO: handle exception
			throw new InternalError();
		}
		
	}
	
	public boolean contains(Object o){
		return indexOf(o) >= 0;
	}
	
	//通过遍历elementData数组来判断对象是否在list中，
	//若存在，返回index（[0,size-1]），若不存在则返回-1。
	//所以contains方法可以通过indexOf(Object)方法的返回值来判断对象是否被包含在list中。
	public int indexOf(Object o) {
		if(o==null){
			for(int i=0;i<size;i++){
				if(elementData[i]==null){
					return i;
				}
			}
		}else{
			for(int i=0;i<size;i++){
				if(o.equals(elementData[i])){
					return i;
				}
			}
		}
		return -1;
	}
	
	public int lastIndexOf(Object o){
		if(o==null){
			for(int i=size-1;i>=0;i--){
				if(elementData[i]==null){
					return i;
				}
			}
		}else{
			for(int i=size-1;i>=0;i--){
				if(o.equals(elementData[i])){
					return i;
				}
			}
		}
		return -1;
	}
	
	public E get(int index){
		RangeCheck(index);
		
		return (E) elementData[index];
	}
	
	public E remove(int index){
		RangeCheck(index);
		modCount++;
		E oldValue = (E) elementData[index];
		int numMoved = size-index-1;
		if(numMoved>0){
			System.arraycopy(elementData, index+1, elementData, index, numMoved);
		}
		elementData[--size]=null;
		return oldValue;
	}
	
	public boolean remove(Object o){
		if(o==null){
			for(int i=0;i<size;i++){
				if(elementData[i]==null){
					fastRemove(i);
					return true;
				}
			}
		}else{
			for(int i=0;i<size;i++){
				if(o.equals(elementData[i])){
					fastRemove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	protected void removeRange(int fromIndex, int toIndex){
		modCount++;
		int numMoved = size -toIndex;
		System.arraycopy(elementData, toIndex, elementData, fromIndex,numMoved);
		
		int newSize = size - (toIndex-fromIndex);
		while(size!=newSize){
			elementData[--size]=null;
		}
	}
	
	private void fastRemove(int index){
		modCount++;
		int numMoved = size - index -1;
		if(numMoved>0){
			System.arraycopy(elementData, index+1, elementData, index, numMoved);
		}
		elementData[--size]=null;
	}
	
	//检查是否越界
	public void RangeCheck(int index){
		if(index>=size){
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
		}
	}
	
	//调用该方法，至少将elementData的容量加1，所以elementData[size]不会出现越界
	public void ensureCapacity(int minCapacity){
		modCount++;//记录list结构被改变的次数，这个解释有点问题？个人猜测和数组元素个数一致？
		int oldCapacity = elementData.length;
		if(minCapacity > oldCapacity){
			Object[] oldData = elementData;
			int newCapacity = (oldCapacity*3)/2+1;
			if(newCapacity < minCapacity){
				newCapacity = minCapacity;
			}
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
		
	}
	
	//首先检查范围，然后用新元素替换旧元素
	public E set(int index, E element){
		RangeCheck(index);
		E oldValue = (E) elementData[index];
		elementData[index] = element;
		return oldValue;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	public Object[] toArray(){
		return Arrays.copyOf(elementData, size);
	}
	
	// 如果传入数组的长度小于size，返回一个新的数组，大小为size，类型与传入数组相同。所传入数组长度与size相等，则将elementData复制到传入数组中并返回传入的数组。若传入数组长度大于size，除了复制elementData外，还将把返回数组的第size个元素置为空。
	public <T> T[] toArray(T[] a){
		if(a.length<size){
			return (T[]) Arrays.copyOf(elementData, size,a.getClass());
		}
		
		System.arraycopy(elementData, 0, a, 0, size);
		if(a.length>size){
			a[size]=null;
		}
		return a;
	}
	
	//由于elementData的长度会被拓展，size标记的是其中包含的元素的个数。所以会出现size很小但elementData.length很大的情况，将出现空间的浪费。trimToSize将返回一个新的数组给elementData，元素内容保持不变，length很size相同，节省空间。
	public void trimToSize(){
		modCount++;
		int oldCapacity = elementData.length;
		if(size<oldCapacity){
			elementData = Arrays.copyOf(elementData, size);
		}
	}

}
