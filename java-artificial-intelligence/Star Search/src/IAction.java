
public interface IAction<T extends AbstractNode<?>>{
	public T getChild(T parent);
}
