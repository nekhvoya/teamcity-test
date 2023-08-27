package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.utils.setAttribute
import org.openqa.selenium.By.cssSelector
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class CommandLineForm: BaseBuildStepForm(cssSelector("input[value=simpleRunner]"), "Command Line Form") {
    private val scriptInput: SelenideElement = `$`(".CodeMirror textarea")

    companion object {
        val log: Logger = getLogger(CommandLineForm::class.java.simpleName)
    }

    fun typeScript(script: String) {
        log.info("Typing script $script")
        setAttribute(scriptInput.parent(), "style", "none")
        scriptInput.type(script)
    }
}