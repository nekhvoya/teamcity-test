package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class PowerShellForm: BaseBuildStepForm(id("powershell_scriptFile"), "Power Shell Form") {
    private val scriptInput: SelenideElement = `$`(id("jetbrains_powershell_script_file"))

    companion object {
        val log: Logger = getLogger(PowerShellForm::class.java.simpleName)
    }

    fun setScriptFile(scriptFilePath: String) {
        log.info("Typing script file path$scriptFilePath")
        scriptInput.type(scriptFilePath)
    }
}