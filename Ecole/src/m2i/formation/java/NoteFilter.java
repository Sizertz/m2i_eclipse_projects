package m2i.formation.java;

import java.util.List;

public interface NoteFilter<T> {
	/**
	 * 
	 * @param note
	 * @return whether note passes filter
	 */
	boolean filter(T t);
}
