import java.util.Observable;
import java.util.Observer;;


public class ConcreteWeatherObserver implements Observer {

	//观察者名称
	private String observerName;
	@Override
	public void update(Observable o, Object arg) {
		//推送方式
		System.out.println(observerName+"收到了消息，目标推送过来的是"+arg);;
		//拉取方式
		System.out.println(observerName+"收到了消息，主动到目标对象中去拉，拉的内容是"+((ConcreteWeatherSubject)o).getContent());
	}
	public String getObserverName() {
		return observerName;
	}
	public void setObserverName(String observerName) {
		this.observerName = observerName;
	}


}
