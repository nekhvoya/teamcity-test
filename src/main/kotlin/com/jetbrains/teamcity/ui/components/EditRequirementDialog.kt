package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.data.Requirement
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class EditRequirementDialog: BaseComponent(id("editRequirementDialog"), "Edit Requirement Dialog") {
    private val parameterNameInput: SelenideElement = `$`(id("parameterName"))
    private val parameterValueInput: SelenideElement = `$`(id("parameterValue"))
    private val conditionSelect: SelenideElement = `$`(id("requirementType"))
    private val saveButton: SelenideElement = `$`("[value=Save]")

    companion object {
        val log: Logger = getLogger(EditRequirementDialog::class.java.simpleName)
    }

    fun createRequirement(requirement: Requirement) {
        typeParameterName(requirement.parameter)
        selectCondition(requirement.condition)
        requirement.value?.let { typeParameterValue(it) }
        clickSave()
    }

    fun typeParameterName(parameterName: String) {
        log.info("Typing parameter name $parameterName in $componentName")
        parameterNameInput.type(parameterName)
    }

    fun selectCondition(condition: String) {
        log.info("Selecting condition $condition in $componentName")
        conditionSelect.selectOption(condition)
    }

    fun typeParameterValue(value: String) {
        log.info("Typing parameter value $value in $componentName")
        parameterValueInput.type(value)
    }

    fun clickSave() {
        log.info("Clicking Save button in $componentName")
        saveButton.click()
    }
}