package com.jetbrains.teamcity.ui.components

import org.openqa.selenium.By.cssSelector

class BuildOverviewComponent: BaseComponent(cssSelector("[data-test=ring-tab][class*=OverviewTab]"), "Build Log Form") {
}