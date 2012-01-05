import models.LogModel;
import view.AppLayoutViewImpl;
import controller.AppController;

public class LogAnalyser {

	public static void main(String arg[]) {

		String dbPath = "./src/traffic_10.10.1.txt";
		LogModel model = new LogModel();
		AppLayoutViewImpl findView = new AppLayoutViewImpl(model);
		AppController controller = new AppController(model, findView, dbPath);
		findView.setVisible(true);

	}
}
