/**
 * 具体的观察者对象，实现更新方法，是自身的状态和目标的状态保持一致
 * @author Anatasia
 *
 */
public class ConcreteObserver implements Observer {

	//观察者状态
	private String observerState;
	
	/**
	 * 获取目标类的状态同步到观察者的状态中
	 */
	@Override
	public void update(Subject subject) {
		// TODO Auto-generated method stub
		observerState = ((ConcreteSubject)subject).getSubjectState();
	}

}
