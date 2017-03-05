/**
 * 
 * @author Anatasia
 *具体的目标对象，负责把有关状态存入到相应的观察者对象中
 */
public class ConcreteWeatherSubject extends WeatherSubject {
	private String weatherContent;//目标对象的状态

	public String getWeatherContent() {
		return weatherContent;
	}

	public void setWeatherContent(String weatherContent) {
		this.weatherContent = weatherContent;
		//通知所有订阅了天气的观察者
		this.notifyObservers(weatherContent);
	}

}
