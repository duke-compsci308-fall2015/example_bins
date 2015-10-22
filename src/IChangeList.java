import java.util.List;

@FunctionalInterface
public interface IChangeList<T> {
	public void changeList(List<T> list);
}
