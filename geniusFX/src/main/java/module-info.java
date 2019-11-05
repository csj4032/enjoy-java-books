module geniusFX {
	requires javafx.controls;
	requires javafx.fxml;

	opens com.genius.fx to javafx.fxml;
	exports com.genius.fx;
}