import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Anatasia
 *功能：目标对象，它知道观察它的观察者，并提供注册和删除观察者接口。
 */
public class WeatherSubject {
	//用来保存注册者观察对象
	private List<Observer> observers = new ArrayList<Observer>();
	
	//添加观察者
	//把订阅天气的人添加到订阅者集合
	public void attach(Observer observer){
		observers.add(observer);
	}
	
	//删除指定观察者，删除集合中的指定订阅天气的人
	public void dettach(Observer observer) {
		observers.remove(observer);
	}
	
	//向所有注册观察者发布消息
	protected void notifyObservers() {
		for(Observer observer:observers){
			observer.update(this);
		}
	}

}
