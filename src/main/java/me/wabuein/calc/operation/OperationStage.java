package me.wabuein.calc.operation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OperationStage {

    SETTING_A("Set your first number"),
    SETTING_B("Set your second number"),
    SETTING_OPERATION_TYPE("Set your operation type");

    private final String title;

}
