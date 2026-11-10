package com.hassan.cfg2pda.controller;

import com.hassan.cfg2pda.converter.CFGToPDAConverter;
import com.hassan.cfg2pda.model.PDA;
import com.hassan.cfg2pda.model.PDATransition;
import com.hassan.cfg2pda.parse.CFGParser;
import com.hassan.cfg2pda.simulate.PDASimulator;
import com.hassan.cfg2pda.util.JsonUtil;
import com.hassan.cfg2pda.validate.CFGValidator;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {

    @FXML private TextArea cfgInputArea;
    @FXML private TextField testStringField;
    @FXML private TableView<PDATransition> transitionTable;
    @FXML private TableColumn<PDATransition, String> colFrom, colInput, colPop, colPush, colTo;

    @FXML private TableView<PDASimulator.Step> simStepsTable;
    @FXML private TableColumn<PDASimulator.Step, String> colStep, colSimState, colSimInput, colSimStack, colSimTrans;

    @FXML private Label statusLabel;

    private PDA currentPDA;

    @FXML
    public void initialize() {
        colFrom.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFrom()));
        colInput.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getInput()));
        colPop.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPop()));
        colPush.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPush()));
        colTo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTo()));

        colStep.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().stepNo)));
        colSimState.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().state));
        colSimInput.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().remainingInput));
        colSimStack.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().stack));
        colSimTrans.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().transition));
    }

    @FXML
    public void onConvert() {
        try {
            var parser = new CFGParser();
            var cfg = parser.parse(cfgInputArea.getText());
            new CFGValidator().validate(cfg);

            var converter = new CFGToPDAConverter();
            currentPDA = converter.convert(cfg);

            transitionTable.getItems().setAll(currentPDA.getTransitions());
            statusLabel.setText("Converted: " + currentPDA.getTransitions().size() + " transitions.");
        } catch (Exception e) {
            showError("Convert error", e.getMessage());
        }
    }

    @FXML
    public void onSimulate() {
        if (currentPDA == null) {
            showError("Simulate", "Please convert a CFG first.");
            return;
        }
        String s = testStringField.getText().trim();
        PDASimulator sim = new PDASimulator();
        var result = sim.run(currentPDA, s, 800);
        simStepsTable.getItems().setAll(result.steps);
        statusLabel.setText(result.accepted ? "Accepted" : "Rejected");
    }

    @FXML
    public void onExportJson() {
        if (currentPDA == null) {
            showError("Export", "Nothing to export: convert first.");
            return;
        }
        String json = JsonUtil.toJson(currentPDA.getTransitions().stream().map(PDATransition::toString).toArray());
        TextArea ta = new TextArea(json);
        ta.setEditable(false);
        ta.setWrapText(true);
        Dialog<Void> dlg = new Dialog<>();
        dlg.setTitle("PDA JSON");
        dlg.getDialogPane().setContent(ta);
        dlg.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dlg.showAndWait();
    }

    private void showError(String header, String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        a.setHeaderText(header);
        a.showAndWait();
    }
}
