package chapter07.item43;

public class GenericFunctionType {

	public static void main(String[] args) {
		GenericFunctionType genericFunctionType = new GenericFunctionType();

		// 함수형 인터페이스를 위한 제네릭 함수 타입은 메서드 참조 표현식으로는 구현 할 수 있음
		// 왜 ?
		genericFunctionType.methodReferenceExpression(String::new);

		// 메서드 시그니처
		//genericFunctionType.methodReferenceExpression(() -> "A");

		// 메서드 타입
		//genericFunctionType.methodReferenceExpression(<F extends Exception> ()->String throws F);

		genericFunctionType.methodReferenceExpression(new G() {
			@Override
			public <F extends Exception> String m() throws F {
				return "A";
			}
		});
	}

	public String methodReferenceExpression(G g) {
		String str = "";
		try {
			str = g.m();
		} catch (Exception e) {
		}
		return str;
	}
}
