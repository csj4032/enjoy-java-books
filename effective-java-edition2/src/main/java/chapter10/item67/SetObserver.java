package chapter10.item67;

public interface SetObserver <E> {
	void added(ObservableSet<E> set, E element);
}