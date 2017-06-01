import java.util.Collection;
import java.util.Comparator;

public abstract class AbstractNode<E>{

	AbstractNode<E> parent;
	//Collection<AbstractNode<E>> children;
	int costFromStart;
	E state;
	
	public AbstractNode(AbstractNode <E> parent,int costFromStart, E state){
		this.parent = parent;
		this.costFromStart=costFromStart;
		this.state = state;
	}
	
	/*
	public void addChild(AbstractNode<E> node){
		children.add(node);
	}
	
	public void addChildren(Collection<? extends AbstractNode<E>> nodes){
		children.addAll(nodes);
	}*/
	
	public float evaluate(){
		return this.costFromStart + getHeuristic();
	}
	
	public abstract Collection<AbstractNode<E>> expand();
	public abstract float getHeuristic();
	public abstract boolean isGoal();
}

final class NodeEvaluationComparator implements Comparator<AbstractNode<?>>
{
    @Override
    public int compare(AbstractNode<?> x, AbstractNode<?> y)
    {
        if (x.evaluate() < y.evaluate())
        {
            return -1;
        }
        if (x.evaluate() > y.evaluate())
        {
            return 1;
        }
        return 0;
    }
}
