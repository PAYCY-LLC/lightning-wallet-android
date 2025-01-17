package io.horizontalsystems.lightningwallet.managers

import io.horizontalsystems.core.ILanguageManager
import io.horizontalsystems.lightningwallet.App
import java.util.*

class LanguageManager : ILanguageManager {

    override var currentLocale: Locale = App.instance.getLocale()
        set(value) {
            field = value
            App.instance.setLocale(currentLocale)
        }

    override var currentLanguage: String
        get() = currentLocale.language
        set(value) {
            currentLocale = Locale(value)
        }

    override val currentLanguageName: String
        get() = currentLocale.displayLanguage.capitalize()

    override fun getName(language: String): String {
        return Locale(language).displayLanguage.capitalize()
    }

    override fun getNativeName(language: String): String {
        val locale = Locale(language)
        return locale.getDisplayLanguage(locale).capitalize()
    }
}
