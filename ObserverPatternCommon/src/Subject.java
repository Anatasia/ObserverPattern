import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Anatasia
 *功能：目标对象，它知道观察它的观察者，并提供注册和删除观察者接口。
 */
public class Subject {
	//用来保存注册者观察对象
	private List<Observer> observers = new ArrayList<Observer>();
	
	//添加观察者
	public void attach(Observer observer){
		observers.add(observer);
	}
	
	//删除指定观察者
	public void dettach(Observer observer) {
		observers.remove(observer);
	}
	
	//向所有注册观察者发布消息
	protected void notifyObservers() {
		for(Observer observer:observers){
			observer.update(this);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
