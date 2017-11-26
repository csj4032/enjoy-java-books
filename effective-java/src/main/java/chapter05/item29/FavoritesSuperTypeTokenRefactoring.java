package chapter05.item29;

import org.springframework.core.ResolvableType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavoritesSuperTypeTokenRefactoring {

	private Map<Type, Object> favorites = new HashMap<>();

	public <T> void putFavorite(TypeReference<T> type, T instance) {
		if (type == null) throw new NullPointerException("Type is null");
		favorites.put(type.type, instance);
	}

	public <T> T getFavorite(TypeReference<T> type) {
		if(type.type instanceof Class<?>)
			return ((Class<T>) type.type).cast(favorites.get(type.type));
		else
			return ((Class<T>)((ParameterizedType) type.type).getRawType()).cast(favorites.get(type.type));
	}

	static class TypeReference<T> {
		Type type;

		public TypeReference() {
			Type sType = getClass().getGenericSuperclass();
			if (sType instanceof ParameterizedType) {
				this.type = ((ParameterizedType) sType).getActualTypeArguments()[0];
			} else throw new RuntimeException();
		}
	}

	public static void main(String[] args) {
		FavoritesSuperTypeTokenRefactoring f = new FavoritesSuperTypeTokenRefactoring();

		f.putFavorite(new TypeReference<String>(){}, "Java");
		f.putFavorite(new TypeReference<Integer>(){}, 1);
		f.putFavorite(new TypeReference<List<Integer>>(){}, Arrays.asList(1,2,3));
		f.putFavorite(new TypeReference<List<String>>(){}, Arrays.asList("a","b","c"));
		f.putFavorite(new TypeReference<List<List<String>>>(){}, Arrays.asList(Arrays.asList("a","b","c"), Arrays.asList("d","e","f"), Arrays.asList("g","h","i")));

		System.out.println(f.getFavorite(new TypeReference<String>(){}));
		System.out.println(f.getFavorite(new TypeReference<Integer>(){}));
		System.out.println(f.getFavorite(new TypeReference<List<Integer>>(){}));
		System.out.println(f.getFavorite(new TypeReference<List<String>>(){}));
		System.out.println(f.getFavorite(new TypeReference<List<List<String>>>(){}));

		ResolvableType resolvableType = ResolvableType.forInstance(new TypeReference<List<String>>(){});
		System.out.println(resolvableType.getSuperType().getGeneric(0).getType());
		System.out.println(resolvableType.getSuperType().getGeneric(0).getNested(2).getType());
		System.out.println(resolvableType.getSuperType().hasUnresolvableGenerics());
	}
}