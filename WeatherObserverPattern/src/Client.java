/**
 * 测试类
 * @author Anatasia
 *
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1.创建目标
		ConcreteWeatherSubject weather = new ConcreteWeatherSubject();
		//2.创建观察者
		ConcreteObserver observerGirl = new ConcreteObserver();
		observerGirl.setObserverName("Mary");
		observerGirl.setRemindThing("约会地点后海，不见不散");
		
		ConcreteObserver observerMom = new ConcreteObserver();
		observerMom.setObserverName("老妈");
		observerMom.setRemindThing("是一个购物的好日子，适合逛街");
		//3.注册观察者
		weather.attach(observerGirl);
		weather.attach(observerMom);
		//4.目标处发布天气
		weather.setWeatherContent("明天天气晴朗，气温25度");
	}

}
