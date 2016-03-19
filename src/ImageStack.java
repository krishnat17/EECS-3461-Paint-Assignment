import java.util.Stack;

public class ImageStack<I> extends Stack<I> {

	private static final long serialVersionUID = 1L;
	private final int maxSize;

	public ImageStack(int size) {
		super();
		this.maxSize = size;
	}

	@SuppressWarnings("unchecked")
	public Object push(Object o) {
		while (this.size() > maxSize) {
			this.remove(0);
		}
		return super.push((I) o);

	}

}
