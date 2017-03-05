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
		//1.创建天气目标
		ConcreteWeatherSubject weather = new ConcreteWeatherSubject();
		//2.创建观察者
		ConcreteWeatherObserver observerGirl = new ConcreteWeatherObserver();
		observerGirl.setObserverName("Mary");
		//observerGirl.setRemindThing("约会地点后海，不见不散");
		
		ConcreteWeatherObserver observerMom = new ConcreteWeatherObserver();
		observerMom.setObserverName("老妈");
		//observerMom.setRemindThing("是一个购物的好日子，适合逛街");
		//3.注册观察者
		weather.addObserver(observerGirl);
		weather.addObserver(observerMom);
		//4.目标处发布天气
		weather.setContent("明天天气晴朗，气温25度");
	}

}
