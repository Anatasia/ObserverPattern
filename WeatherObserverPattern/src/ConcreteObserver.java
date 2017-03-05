/**
 * 具体的观察者对象，实现更新方法，是自身的状态和目标的状态保持一致
 * @author Anatasia
 *
 */
public class ConcreteObserver implements Observer {
	//观察者名字
	private String observerName;
	//天气内容情况
	private String weatherContent;
	//提醒的内容
	private String remindThing;
	
	/**
	 * 获取目标类的状态同步到观察者的状态中
	 */
	@Override
	public void update(String content) {
		// TODO Auto-generated method stub
		//weatherContent = ((ConcreteWeatherSubject)subject).getWeatherContent();
		weatherContent = content;
		System.out.println(observerName+"收到了"+weatherContent+","+remindThing);
	}

	public String getObserverName() {
		return observerName;
	}

	public void setObserverName(String observerName) {
		this.observerName = observerName;
	}

	public String getWeatherContent() {
		return weatherContent;
	}

	public void setWeatherContent(String weatherContent) {
		this.weatherContent = weatherContent;
	}

	public String getRemindThing() {
		return remindThing;
	}

	public void setRemindThing(String remindThing) {
		this.remindThing = remindThing;
	}

}
