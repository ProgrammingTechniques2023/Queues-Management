package GUI;

import BusinessLogic.SelectionPolicy;
import BusinessLogic.SimulationManager;

public class Controller {
    private View view;

    public Controller(View view) {
        this.view = view;
    }

    public void startBtnPressed() {

        SelectionPolicy policy=SelectionPolicy.SHORTEST_QUEUE;
        if(view.getTimeRdBtn().isSelected()){
            policy=SelectionPolicy.SHORTEST_TIME;
        }
        SimulationManager gen=new SimulationManager(Integer.parseInt(view.gettLimitTxtField().getText()), Integer.parseInt(view.getMaxProcessingTxtField().getText()),Integer.parseInt(view.getMinProcessingTxtField().getText()),Integer.parseInt(view.getServerTxtField().getText()),Integer.parseInt(view.getTaskTxtField().getText()),Integer.parseInt(view.getMinArrivalTxtField().getText()),Integer.parseInt(view.getMaxArrivalTxtField().getText()),policy);
        gen.setView(view);
        Thread t=new Thread(gen);
        t.start();
    }

    public void restartBtnPressed() {
        view.getTextArea().setText("");
        view.getMaxArrivalTxtField().setText("");
        view.getMinArrivalTxtField().setText("");
        view.getMaxProcessingTxtField().setText("");
        view.getMinProcessingTxtField().setText("");
        view.getTaskTxtField().setText("");
        view.getServerTxtField().setText("");
        view.gettLimitTxtField().setText("");
    }
}
